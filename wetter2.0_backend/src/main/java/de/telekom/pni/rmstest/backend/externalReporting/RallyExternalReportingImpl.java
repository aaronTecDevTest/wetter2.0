package de.telekom.pni.rmstest.backend.externalReporting;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.GetRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.GetResponse;
import com.rallydev.rest.util.Fetch;

import de.telekom.pni.rmstest.backend.config.LoginDataRally;
import de.telekom.pni.rmstest.backend.externalReporting.interfaces.ExternalReporting;
import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import de.telekom.pni.rmstest.backend.reporting.Report;
import de.telekom.pni.rmstest.backend.reporting.Step;
import de.telekom.pni.rmstest.backend.testentities.RallyTestCaseImpl;
import de.telekom.pni.rmstest.backend.testentities.RallyTestSetImpl;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;

/**
 * Die Klasse RallyExternalReportingImpl.java ist für die Kommunikation mit der
 * Projektmanagement Software Rally zuständig. Die Klasse stellt Methoden zur
 * Verfügung um Testsets und Testfälle aus Rally auszulesen und Testergebnisse
 * in Rally zu speichern. Um Kompatibilität mit dem Framework zu gewährleisten
 * implementiert sie das ExternalReporting.java Interface und dessen Methoden.
 *
 * @author M.Forster
 */
public class RallyExternalReportingImpl implements ExternalReporting {

    private static final Logger log = Logger.getLogger(RallyExternalReportingImpl.class);
    private RallyRestApi rallyApi;
    private final String fontColorRally = "rgb(255, 0, 0)";
    private final Properties RALLY = PropertiesManager_New.getInstance().getProperties("rally");

    /**
     * Standard Konstruktor der Klasse.
     */
    public RallyExternalReportingImpl() {
    }

    /**
     * Die Methode connect stellt eine Verbindung zum Rally Server her. Dazu benötigt sie die einem Objekt vom
     * Typ LoginDataRally hinteregten Zugangsdaten.
     *
     * @param loginData - Login Informationen für Rally
     * @return true/false - Information ob der Login erfolgreich oder nicht erfolgreich war
     */
    @Override
    public boolean connect(LoginDataRally loginData) {
        try {
            log.debug("Trying to connect to Rally");
            rallyApi = new RallyRestApi(new URI(RALLY.getProperty("url")), loginData.getUsername(), loginData.getPassword());
//			Vorübergehend wird hier die Proxy-Einstellung gesetzt.
            rallyApi.setProxy(new URI("http://192.168.1.112:3128"));
            rallyApi.setApplicationName(RALLY.getProperty("applicationName"));
            GetRequest getRequest = new GetRequest(RALLY.getProperty("workspace"));
            rallyApi.get(getRequest);
            log.debug("Connection to Rally established");
            return true;
        } catch (Exception ex) {
            log.error("Error while connecting to Rally. " + ex.getMessage());
            return false;
        }
    }

    /**
     * Diese Methode schließt die Verbindung zum Rally Server.
     *
     * @return true/false - Status ob der disconnect erfolgreich oder nicht erfolgreich war
     */
    @Override
    public boolean disconnect() {
        try {
            log.debug("Trying to close Connection to Rally");
            rallyApi.close();
            log.debug("Connection to Rally closed");
            return true;
        } catch (Exception ex) {
            log.error("Error while closing the Rally Connection. " + ex.getMessage());
            return false;
        }
    }

    /**
     * Methode, einen bestimmten TestSet heraussucht und zurückgibt.
     * zugehörige Tests abgerufen werden soll.
     *
     * @return testSetID - Das gefundene TestSet als InternalTestSetImpl Objekt
     */
    @Override
    public TestSet getTestSet(String testSetID)//, String filter)
    {
        log.debug("Trying to get a TestSet from Rally");
        if (testSetID == null || testSetID.length() == 0) {
            log.error("Test set path is null... Aborting.");
            return null;
        }

        JsonObject jsonTestSetObject = getJTestSet(testSetID);
        RallyTestSetImpl rallyTestSet = new RallyTestSetImpl(jsonTestSetObject);
        JsonArray testCases = getTestCasesForJTestSet(rallyTestSet.getTestSetID());//, filter);
        String scriptDeveloper = getCurrentUser();

        for (JsonElement tc : testCases) {
            RallyTestCaseImpl test;
            try {
                test = new RallyTestCaseImpl(getTestCaseFromRally(tc.getAsJsonObject().get("_ref").getAsString()));
                test.setDeveloper(scriptDeveloper);
                test.setTestSet(rallyTestSet);
                rallyTestSet.addTestCase(test);
            } catch (IOException e) {
                log.error("Exception thrown while getting TestCases");
            }
        }
        //its.setFilter(filter);
        log.debug("TestSet received from Rally");
        return rallyTestSet;
    }

    /**
     * Diese Methode ruft alle TestsSets aus Rally ab und gibt sie als JsonArray zurück
     *
     * @return testSets - Alle in Rally vorhandenen TestSets, auf die der angemeldete Benutzer Zugriff hat.
     */
    private JsonArray getTestSets() throws IOException {
        log.debug("Trying to get TestSet");
        QueryRequest getRequest = new QueryRequest("testset");
        getRequest.setWorkspace(RALLY.getProperty("workspace"));
        getRequest.setProject(RALLY.getProperty("project"));
        getRequest.setFetch(new Fetch("FormattedID", "Name", "TestCases"));
        log.debug("TestSet received");
        return rallyApi.query(getRequest).getResults();
    }


    /**
     * Diese Methode extrahiert aus dem getRef String das letze Element. Dieses Element enthält
     * den Namen des TestSet.
     *
     * @param getRef - Aus Rally extrahiertes _ref Element
     * @return getRef - Die aus dem _ref Element extrahierte TestSetID
     */
    private String getTestSetID(String getRef) {
        getRef = getRef.substring(getRef.lastIndexOf("/") + 1, getRef.lastIndexOf("."));
        return getRef;
    }

    /**
     * Diese Methode bereitet die Testergebnisse auf und bringt sie in ein Format, dass in Rally abgespeichert
     * werden kann. Es werden die allgemeinen Informationen zum Test und die einzelnen Schritte gespeichert.
     *
     * @param testCase - Der getestete Testfall
     * @param result   - Das Testergebnis gespeichert in einem Report Objekt
     * @return true/false - Status ob das Speicher erfolgreich oder nicht erfolgreich war
     */
    public boolean writeTestResult(TestCase testCase, Report result) {
        log.debug("Trying to write Testresult to Rally");
        if (result == null) {
            return false;
        }

        String testResults;

        Integer testID = testCase.getTestCaseID();
        Integer testSetID = testCase.getTestSet().getTestSetID();
        String testName = testCase.getExecutableClass();
        String developer = testCase.getDeveloper();
        boolean status = result.getStatus();
        String browser = testCase.getTestSet().getConfig().getBrowser();
        String machine = testCase.getTestSet().getConfig().getMachine();

        Vector<Step> testSteps = result.getSteps();

        testResults = "<b>Test: </b> " + testName + "</br><b>Browser:</b> " + browser + "<b> Machine:</b> " + machine + "<br></br>";

        for (Step step : testSteps) {

            if (step.getStatus() == false) {
                testResults = testResults.concat("<span style=\"color: " + fontColorRally + ";\"><b>Step:</b> " + step.getStepName()
                        + "<b> Expected:</b> " + step.getExpected()
                        + "<b> Actual:</b> " + step.getActual() + "</br>"
                        + "<b> Status: " + statusToString(step.getStatus()) + "</b></span><br></br>");
            } else {
                testResults = testResults.concat("<span><b>Step:</b> " + step.getStepName()
                        + "<b> Expected:</b> " + step.getExpected()
                        + "<b> Actual:</b> " + step.getActual() + "</br>"
                        + "<b> Status: " + statusToString(step.getStatus()) + "</b></span><br></br>");
            }
        }

        if (writeDataToRally(testID, testSetID, testResults, statusToString(status), developer)) {
            log.debug("Writing Testresult to Rally successful");
            return true;
        } else {
            log.debug("Writing Testresult to Rally not successful");
            return false;
        }
    }

    /**
     * Bei dieser Methode handelt es sich um eine Hilfsmethode für die Methode writeTestResult(). Die Methode
     * nimmt die von writeTestResult() aufbereiteten Daten entgegen und speichert sie in Rally als Testergebnis.
     *
     * @param testID          - ID des Tests
     * @param testSetID       - ID des TestSets
     * @param steps           - String mit den TestSteps
     * @param status          - Status des Tests
     * @param scriptDeveloper - Entwickler des Testfalls
     * @return
     */
    private boolean writeDataToRally(int testID, int testSetID, String steps, String status, String scriptDeveloper) {
        log.debug("Trying to write data to Rally");
        try {

            // Create Test Result and than add to TestCASE
            // Create JsonObject To TestCaseResult
            JsonObject newResult = new JsonObject();

            // required fields
            newResult.addProperty("Build", now("dd.MM.yyyy"));
            newResult.addProperty("Date", now("yyyy-MM-dd'T'HH:mm:ssZ"));  // Date ist ISO8601 Format
            newResult.addProperty("Verdict", status);
            newResult.addProperty("TestCase", "/testcase/" + testID);

            // optional
            newResult.addProperty("TestSet", "/testset/" + testSetID);
            newResult.addProperty("Notes", steps);
            newResult.addProperty("Tester", "/user/" + scriptDeveloper);

            CreateRequest createRequest = new CreateRequest("testcaseresult", newResult);
            CreateResponse createResponse = rallyApi.create(createRequest);

            if (!createResponse.wasSuccessful()) {

                String[] createErrors;
                createErrors = createResponse.getErrors();

                log.error("Error occurred creating Test Case: ");

                for (int i = 0; i < createErrors.length; i++) {
                    log.error(createErrors[i]);
                }
            }
            log.debug("Writing data to Rally successful");
            return true;
        } catch (IOException e) {
            log.error("Error while writing data to Rally. " + e.getMessage());
            return false;
        }
    }

    private String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();

        //cal.add(Calendar.MINUTE, -78);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    /**
     * Diese Methode ruft eine Liste der verfügbaren Testsets ab und erstellt daraus einen JTree für die GUI.
     *
     * @return theTree - JTree Objekt, dass eine Liste der TestSets aus Rally enthält.
     */
    @Override
    public JTree getTestlabStructure() {
        log.debug("Trying to get TestlabStructure");
        JsonArray getTestSets = null;
        JTree theTree = null;

        try {
            DefaultMutableTreeNode treeRoot = new DefaultMutableTreeNode("Rally TestSet's");
            getTestSets = getTestSets();
            createJTreeNodes(treeRoot, getTestSets);
            theTree = new JTree(treeRoot);
        } catch (IOException e) {
            log.error("Error while geting TestlabStructure. " + e.getMessage());
        }
        log.debug("Returning TestlabStructure");
        return theTree;
    }

    /**
     * Diese Methode ruft alle TestsSets aus Rally ab und gibt sie als JsonArray zurück
     *
     * @return testSets - Alle in Rally vorhandenen TestSets, auf die der angemeldete Benutzer Zugriff hat.
     * @author a.kutekidila
     */
    @Override
    public List<String> getTestSetList() {

        log.debug("Trying to get TestlabStructure");
        JsonArray getTestSets = null;
        List<String> testSet = new ArrayList<String>();

        try {
            getTestSets = getTestSets();
            testSet = getTestSetToList(getTestSets);
        } catch (IOException e) {
            log.error("Error while geting TestlabStructure. " + e.getMessage());
        }
        log.debug("Returning TestlabStructure");
        return testSet;
    }

    /**
     * Bei dieser Methode handelt es sich um eine Support Methode für getTestlabStructure.
     * Sie fügt die Tests aus einem JsonArray einem DefaultMutableTreeNode hinzu.
     *
     * @param jTestSets
     * @author a.kutekidila
     */

    //@SuppressWarnings("null")
    private List<String> getTestSetToList(JsonArray jTestSets) {
        log.debug("Creating treenodes");

        String testSetFormattedID = null;
        String testSetName = null;
        String testSetID = null;
        List<String> testSet = new ArrayList<String>();

        for (JsonElement m : jTestSets) {

            if (m.getAsJsonObject().get("TestCases").isJsonArray()) {

                testSetFormattedID = m.getAsJsonObject().get("FormattedID").getAsString();
                testSetName = m.getAsJsonObject().get("Name").getAsString();
                testSetID = getTestSetID(m.getAsJsonObject().get("_ref").getAsString());

                //TestSet Name and TestSet ID
                testSet.add(new String(testSetFormattedID + " " + testSetName + " ID:" + testSetID));
                //System.out.println(testSetName);
            }
        }
        log.debug("Treenodes created");
        return testSet;
    }


    /**
     * Bei dieser Methode handelt es sich um eine Support Methode für getTestlabStructure.
     * Sie fügt die Tests aus einem JsonArray einem DefaultMutableTreeNode hinzu.
     *
     * @param parentNode
     * @param jTestSets
     * @author A.Roth
     */
    private void createJTreeNodes(DefaultMutableTreeNode parentNode,
                                  JsonArray jTestSets) {
        log.debug("Creating treenodes");
        DefaultMutableTreeNode testSetNode = null;

        String testSetFormattedID = null;
        String testSetName = null;
        String testSetID = null;

        for (JsonElement m : jTestSets) {

            if (m.getAsJsonObject().get("TestCases").isJsonArray()) {

                testSetFormattedID = m.getAsJsonObject().get("FormattedID").getAsString();
                testSetName = m.getAsJsonObject().get("Name").getAsString();
                testSetID = getTestSetID(m.getAsJsonObject().get("_ref").getAsString());

                //TestSet Name and TestSet ID
                testSetNode = new DefaultMutableTreeNode(testSetFormattedID + " " + testSetName + " ID:" + testSetID);
                parentNode.add(testSetNode);
            }
        }
        log.debug("Treenodes created");
    }


    /**
     * Die Methode konvertiert ein Boolean in den passenden Rally Status als String.
     *
     * @param status - Status eines Tests oder Testschritts
     * @return status - der Wert des übergebenen Status für Rally als String
     */
    private String statusToString(boolean status) {
        if (status == true) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    /**
     * Diese Methode gibt den Benutzernamen des, im Moment am System angemeldeten, Benutzers als String zurück.
     *
     * @return - username - Benutzername des im Moment am System angemeldeten Benutzers als String
     */
    private String getCurrentUser() {
        log.debug("Trying to get current user");
        try {
            GetRequest getRequest = new GetRequest("/user");
            getRequest.setFetch(new Fetch("Name"));

            GetResponse getResponse = rallyApi.get(getRequest);
            log.debug("Current user received");
            return getTestSetID(getResponse.getObject().getAsJsonObject().get("_ref").getAsString());
        } catch (IOException e) {
            log.error("Error while getting current user. " + e.getMessage());
            return null;
        }

    }

    /**
     * Diese Methode ruft einen Testfall aus Rally ab und gibt ihn als JsonObject zurück.
     *
     * @param ref - Die ID anhand der Testfall im Rally identifiziert werdenden kann
     * @return - Testfall aus Rally als JsonObject
     * @throws IOException - Fehlermeldung, falls bei Abrufen des Testfalls  etwas schief gelaufen ist.
     */
    private JsonObject getTestCaseFromRally(String ref) throws IOException {
        log.debug("Entering getTestCaseFromRally Method");
        GetRequest getRequest = new GetRequest("/testcase/" + getTestSetID(ref));
        getRequest.setFetch(new Fetch("Name", "ObjectID", "Method", "Browser", "Executable", "ScriptDeveloper", "Automatable", "LastVerdict", "LastRun"));

        GetResponse getResponse = rallyApi.get(getRequest);
        log.debug("Leaving getTestCaseFromRally Method");
        return getResponse.getObject();
    }

    /**
     * Diese Methode sucht ruft alle Testfälle, die zu einem bestimmten Testset gehörenden
     * Testfälle aus Rally und gibt diese als JsonArray zurück.
     *
     * @param testSet - ID des Testset, dem die Testfälle zugeordnet sind
     * @return testCases - dem TestSet zugeordnete Testfälle als JsonArray
     * @author Ö.Ünal
     */
    private JsonArray getTestCasesForJTestSet(int testSet)//, String filter)
    {
        /*if (filter == null)
			filter = "";*/

        GetRequest getRequest = new GetRequest("/testset/" + String.valueOf(testSet));
        getRequest.setFetch(new Fetch("TestCases"));

        GetResponse getResponse;
        try {

            getResponse = rallyApi.get(getRequest);

            return getResponse.getObject().getAsJsonArray("TestCases");

        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Diese Methode ruft ein TestSet aus Rally ab und gibt ihn als JsonObject zurück.
     *
     * @param testSetID - Die ID des gesuchten TestSets
     * @return testSet - Das in Rally gefundene TestSet als JsonObject
     * @author Ö. Ünal
     */
    private JsonObject getJTestSet(String testSetID) {

        GetRequest getRequest = new GetRequest("/testset/" + testSetID);
        getRequest.setFetch(new Fetch("TestCases"));

        GetResponse getResponse;
        try {

            getResponse = rallyApi.get(getRequest);

            return getResponse.getObject();

        } catch (IOException e) {
            log.error(e.getMessage());
            return null;

        }
    }
}

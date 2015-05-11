package de.telekom.pni.rmstest.frontend.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;


import de.telekom.pni.rmstest.frontend.testentities.FrontendTestCaseImpl;
import de.telekom.pni.rmstest.frontend.testentities.FrontendTestSetImpl;
import de.telekom.pni.rmstest.frontend.testrunner.TestRunnerThread_New;
import de.telekom.pni.rmstest.frontend.view.ApplicationWindows_New;

import de.telekom.pni.rmstest.frontend.view.TestingView_New;
import de.telekom.pni.rmstest.middleware.externalReporting.ExternalReportingFacade;
import de.telekom.pni.rmstest.middleware.properties.PropertiesFacade;
import de.telekom.pni.rmstest.backend.config.LoginDataRally;
import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.config.TestrunConfiguration_New;
import de.telekom.pni.rmstest.backend.reporting.Report;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;


/**
 * Die Klasse ApplicationManager.java ist für die Steuerung der Benutzeroberfläche zuständig.
 * Sie Steuert die internen Funktionen hinter der Benutzeroberfläche und die Kommunikation
 * zwischen Frontend und den anderen Bereichen. Die Klasse wurde vom ursprünglichen Entwickler
 * des Frameworks erstellt. Ich werde mich bei der Protokollierung auf die von mir angepassten
 * Bereiche beschränken.
 *
 * @author A.Roth
 * @author M.Forster
 */

public class ApplicationController_New {

    /**
     * Initialisierung der Properties Variablen für GUI und RALLY Konfigurationsdaten
     */
    private PropertiesFacade propFacade = new PropertiesFacade();
    private final Properties GUI = propFacade.getProperties("gui");
    private final Properties RUN = propFacade.getProperties("testrun");
    private final Properties RALLY = propFacade.getProperties("rally");
    private final Properties APP = propFacade.getProperties("app");


    private final int APP_STATE_IDLE = 0;
    private final int APP_STATE_RUNNING = 1;

    private ApplicationWindows_New app = null;

    private int appState;
    private TestingView_New viewController;
    private ExternalReportingFacade extReporting = new ExternalReportingFacade();
    private List<Integer> activeConfigurations;
    private LoginDataRally loginData;
    private ResourceBundle constants;

    //private String filter;
    private TestRunnerThread_New trt;
    private HashMap<Integer, TestrunConfiguration_New> configurations;
    private HashMap<Integer, TestRunnerThread_New> runningThreads;
    private static int configurationsCount = 0;
    private boolean dbStatus = false;


    public ApplicationController_New() {
        this.configurations = new HashMap<Integer, TestrunConfiguration_New>();
        // get login data
        //RALLYLoginDataForm4 logForm = new RALLYLoginDataForm4(this);
        //logForm.setVisible(true);
        // set application state
        setAppState(APP_STATE_IDLE);
    }

    public LoginDataRally getLoginData() {
        if (this.loginData == null) {
            this.loginData = new LoginDataRally();
        }
        return this.loginData;
    }

    public void setLoginData(LoginDataRally loginData) {
        this.loginData = loginData;
    }

    public boolean connectExternalReporting(LoginDataRally ld) {
        //repository.doLogin(ld);
        return extReporting.connect(ld);
    }

    public void runApplication() {
        if (this.loginData.isDbActive() == true) {
            //TODO hier prüfen ob die DB
            //if(!dataStorageFacade.connect())
            {
                viewController.notifyDBOffline();
                this.dbStatus = true;
            }
        } else {
            viewController.notifyDBOffline();
            this.dbStatus = true;
        }
    }

    /**
     * Diese Methode gibt den Workspace als String zurück.Sie wurde dahingehend angepasst, dass der Workspace nun aus der
     * passenden Property-Datei abgerufen wird.
     *
     * @return Workspace als String
     */
    public String getDefaultWorkspace() {
        return RALLY.getProperty("workspace");
    }

    /**
     * Diese Methode gibt das Projekt als String zurück.Sie wurde dahingehend angepasst, dass des Projekt nun aus der
     * passenden Property-Datei abgerufen wird.
     *
     * @return Projekt als String
     */
    public String getDefaultProject() {
        return RALLY.getProperty("project");
    }

    /**
     * Diese Methode gibt die Url als String zurück. Sie wurde dahingehend angepasst, dass die Rally-Url nun aus der
     * passenden Property-Datei abgerufen wird.
     *
     * @return Rally-Url als String
     */
    public String getRALLYURL() {
        return RALLY.getProperty("url");
    }

    /**
     * Diese Methode gibt das TestingRootPackage als String zurück. Sie wurde dahingehend angepasst, das die Daten
     * aus der passenden Property-Datei abgerufen werden.
     *
     * @return TestingRootPackage als String
     */
    public String getTestingRootPackage() {
        return GUI.getProperty("run.root.package");
    }

    /**
     * Diese Methode gibt die Adresse des Release-Servers als String zurück. Sie wurde dahingehend angepasst, das die Daten
     * aus der passenden Property-Datei abgerufen werden.
     *
     * @return Adresse des Release-Servers als String
     */
    public String getReleaseMachine() {
        return GUI.getProperty("run.testset.release");
    }

    /**
     * Diese Methode gibt die Adresse des Sprint-Servers als String zurück. Sie wurde dahingehend angepasst, das die Daten
     * aus der passenden Property-Datei abgerufen werden.
     *
     * @return Adresse des Sprint-Servers als String
     */
    public String getSprintMachine() {
        return GUI.getProperty("run.testset.sprint");
    }

    /**
     * Diese Methode gibt die Adresse des Wirk-Servers als String zurück. Sie wurde dahingehend angepasst, das die Daten
     * aus der passenden Property-Datei abgerufen werden.
     *
     * @return Adresse des Wirk-Servers als String
     */
    public String getWirkMachine() {
        return GUI.getProperty("run.testset.wirk");
    }

    public TestingView_New getViewController() {
        return viewController;
    }

    public void setViewController(TestingView_New viewController) {
        this.viewController = viewController;
    }


    public List<String> getTestLabList() {

        return extReporting.getTestSetList();
    }

    //public void runTestSet(TestSet testSet, List<String> selected,int confID)
    public void runTestSet(TestSet testSet, List<String> selectedTestCases) {
        Vector<TestCase> tests = testSet.getTestCases();
        if (tests == null) {
            System.err.println("runTestSet: Cann not run test set. Test list within testset is null!");
            return;
        }

        setAppState(APP_STATE_RUNNING);
        // initialize running configuration
        RunningConfiguration_New rc = null;
        if (testSet.getConfig() != null) {
            rc = initRunningConfig(testSet.getConfig().getBrowser(), testSet.getConfig().getMachine());
            //System.out.println("ComboboxMachine :"+testSet.getConfiguration().getMachine());
        }

        //Testcases werden configuriert Aktualisiert 18.09.2013
        for (TestCase test : tests) {
            String browser = testSet.getConfig().getBrowser();
            String machine = testSet.getConfig().getMachine();
            TestrunConfiguration_New trc = new TestrunConfiguration_New(1);
            trc.setBrowser(browser);
            trc.setMachine(machine);
            test.getTestSet().setConfig(trc);
        }

        Collection<TestCase> testsToExectue = tests;

        TestRunnerThread_New runningTestThread = new TestRunnerThread_New(selectedTestCases, testsToExectue, rc, this);
        runningTestThread.start();
    }

    public void refreshTestListFromRepository(TestSet testSet) {
        //if (testSet.getFilter().equals(getFilters()[0])) {
        //	testSet.setFilter("");
        //}
        //extReporting.applyFilterToTestSet(testSet);
    }

    public boolean testNameIsSelected(List<String> selected, String rawName) {
        for (String name : selected) {
            if (rawName.lastIndexOf(name) >= 0)
                return true;
        }
        return false;
    }

    private RunningConfiguration_New initRunningConfig(String browserToRun, String machineToRun) {

        String machine = "";
        //String [] temp = this.getComboServerString();

		/*
		final String testWI 	= temp[0];
		final String wirkWI 	= temp[1];
		final String abnahmeWI 	= temp[2];
		
		final String testTOI 	= temp[3];
		final String wirkTOI 	= temp[4];
		final String abnahmeTOI = temp[5];*/


        switch (machineToRun) {
            case "Test - Wetter.Info":
                machine = RUN.getProperty("run.Test-Wetter");
                break;

            case "Wirktest - Wetter.Info":
                machine = RUN.getProperty("run.Wirktest-Wetter");
                break;

            case "Abnahmetest - Wetter.Info":
                machine = RUN.getProperty("run.Abnahmetest-Wetter");
                break;

            case "Test - T-Online.de":
                machine = RUN.getProperty("run.Test-T-Online");
                break;

            case "Wirktest - T-Online.de":
                machine = RUN.getProperty("run.Wirktest-T-Online");
                break;

            case "Abnahmetest - T-Online.de":
                machine = RUN.getProperty("run.Abnahmetest-T-Online");
                break;

            default:
                return null;
        }
        return new RunningConfiguration_New(browserToRun, machine);
    }

    public List<String> getSpecialPackages() {
        String spn = constants.getString("view.special.package.names");
        return Arrays.asList(spn.split(";"));
    }

    public TestSet filterRunningList(String filter) {
        //this.filter = filter;
        TestSet its = viewController.getDisplayedTestSet();
        if (its == null)
            return null;

        // update the test cases with concerns to the filter
        //its.setFilter(filter);
        //extReporting.applyFilterToTestSet(its);
        return its;
    }

    public String[] extractSimpleTestArray(TestSet testSet) {
        if (testSet == null || testSet.getTestCases() == null)
            return null;
        List<TestCase> itList = testSet.getTestCases();
        int size = itList.size();
        String[] simple = new String[size];
        for (int i = 0; i < size; i++) {
            simple[i] = itList.get(i).getSimpleTestName();
        }
        return simple;
    }


    public int getAppState() {
        return appState;
    }

    public void setAppState(int appState) {
        this.appState = appState;
    }

    public synchronized void writeTestResultToExternalReporting(TestCase test,
                                                                Report runReport) throws Exception {
        extReporting.writeTestResult(test, runReport);
        //repository.writeTestResult(test, runReport);
    }

    // called after running tests in separarte thread has completed.
    public synchronized void testRunCompleted(int confID) {
        setAppState(APP_STATE_IDLE);
        // viewController.enableRunButtons();
        // viewController.disableStopButton();
        // viewController.enableFilterSelect();

        if (confID < 0) {

            //viewController.notifyTestrunCompleted();
            this.trt = null;
        } else {

            //viewController.notifyTestrunCompleted(confID);
        }

    }

    public void stopTestRun(TestRunnerThread_New runner) {
        if (runner == null) {
            runner = trt;
        }
        if (runner != null) {
            runner.setRunningCondition(false);

            runner.interrupt();
            testRunCompleted(runner.getConfigurationID());
        }
    }

    public static TestrunConfiguration_New createConfiguartion() {
        return new TestrunConfiguration_New(++configurationsCount);
    }

    public HashMap<Integer, TestrunConfiguration_New> getConfigurations() {
        return configurations;
    }

    public String[] getSupportedBrowsers() {
        if (this.constants == null)
            return null;
        return GUI.getProperty("run.browsers").split(",");
    }

    public String[] getFilters() {
        if (this.constants == null)
            return null;
        return GUI.getProperty("run.filters").split(",");
    }


    public void addRunConfiguration(TestrunConfiguration_New configuration,
                                    boolean edited) {
        // update the corresponding data structure
        this.configurations.put(configuration.getId(), configuration);

        // update the view
        viewController.notifyRunconfigurationAdded(configuration, edited);

    }

    public void removeRunConfiguration(int confID) {
        this.configurations.remove(confID);
    }

    public TestrunConfiguration_New getTestrunConfiguarition(int id) {
        if (this.configurations == null)
            return null;
        return configurations.get(id);
    }

    public void stopConfigurationRun(int confID) {
        if (this.runningThreads == null) {
            System.err.println("Trying to stop configuartion run for " + confID
                    + " but no cofigs seem to be running.");
            return;
        }
        TestRunnerThread_New t = runningThreads.get(confID);
        stopTestRun(t);
        viewController.notifyTestrunCompleted(confID);
    }

    public void startConfigurationRun(int confID) {
        TestSet ts = configurations.get(confID).getTestSet();

        if (activeConfigurations == null)
            activeConfigurations = new ArrayList<Integer>();
        activeConfigurations.add(confID);

        //extReporting.applyFilterToTestSet(ts);

        runTestSet(ts, null);
    }

    /*
     * @return Array of String comboFilter
     */
    public String[] getComboFilterString() {
        //return GUI.getProperty("run.browsers").split(",");
        return APP.getProperty("comboFilterText").split(",");
    }

    /*
     * @return Array of String comboServer
     */
    public String[] getComboServerString() {
        //return GUI.getProperty("run.browsers").split(",");
        return APP.getProperty("comboServerText").split(",");
    }

    /*
     * @return Array of String comboBrowser
     */
    public String[] getComboBrowserString() {
        //return GUI.getProperty("run.browsers").split(",");
        return APP.getProperty("comboBrowserText").split(",");
    }

    /**
     * Tries to obtain the root directory for the java test cases.
     *
     * @return The root directory for the test cases or null if the root dir
     * could not be identified.
     */
    public String getTestingRootPath() {
        String envvarKey = GUI.getProperty("general.testingroot.path.envvar");
        String wsPath = System.getenv(envvarKey);
        if (wsPath == null || wsPath.length() == 0)
            return null;

        String sufix = GUI.getProperty("general.testingroot.path.suffix");
        String delimiter = GUI.getProperty("general.testingroot.path.separator");
        sufix = sufix.replace(delimiter, File.separator);

        String trPath = wsPath + sufix;
        return trPath;
    }

    public TestSet getTestSetFromExternalReporting(String pathAsString)//, String filter)
    {
        TestSet rallyTestSet = extReporting.getTestSet(pathAsString);//, filter);
        TestSet frontendTestSet = new FrontendTestSetImpl(rallyTestSet.getTestSetName(), rallyTestSet.getTestSetID());
        frontendTestSet.setConfig(rallyTestSet.getConfig());
        for (TestCase tc : rallyTestSet.getTestCases()) {
            FrontendTestCaseImpl tempTest = new FrontendTestCaseImpl(tc.getTestCaseName());
            tempTest.setExecutableClass(tc.getExecutableClass());
            tempTest.setDeveloper(tc.getDeveloper());
            tempTest.setTestCaseID(tc.getTestCaseID());
            tempTest.setTestSteps(tc.getTestSteps());
            tempTest.setTestSet(tc.getTestSet());

            //von Aaron Kutekidila erweitert.
            tempTest.setStatus(tc.getStatus());
            tempTest.getTestSet().setConfig(tc.getTestSet().getConfig());
            tempTest.setCreationDate(tc.getCreationDate());
            tempTest.setAutomatable(tc.getAutomatable());
            tempTest.setLastrun(tc.getLastRun());
            frontendTestSet.addTestCase(tempTest);

        }
        return frontendTestSet;
    }

    public String getDefaultSeparator() {
        return GUI.getProperty("general.testingroot.path.separator");
    }

    public String getTempFolderPath() {

        return GUI.getProperty("general.temp.folder");
    }

    public String getMachine(String partOfMachineName) {
        String releaseM = GUI.getProperty("run.testset.release");
        String sprintM = GUI.getProperty("run.testset.sprint");
        String wirkM = GUI.getProperty("run.testset.wirk");

        if (releaseM.contains(partOfMachineName)) {
            return releaseM;
        } else if (sprintM.contains(partOfMachineName)) {
            return sprintM;
        } else if (wirkM.contains(partOfMachineName)) {
            return wirkM;
        } else { // something went wrong
            System.err.println("getMachine() : could not find suitable machine!");
            return null;
        }
    }

    /**
     * Diese Methode dient dazu Testfälle aus dem Datastorage abzurufen. Anhand des Namens des Testfalls wird nach dem
     * passenden Testfall gesucht. Wird ein Datensatz gefunden, wird er als TestCase-Objekt zurückgegeben.
     *
     * @param testName - Name des gesuchten Testfalls
     *                 //	 * @return testCase - Der gefundene Testfall als TestCase Objekt
     */
    public TestCase getTestCaseFromDataStorage(String testName) {
//		return dataStorageFacade.getTestCase(testName);
        return null;
    }

    /**
     * Diese Methode dient dazu Testfälle zum Datastorage hinzuzufügen. Das an die Methode übergebene TestCase-Objekt
     * wird an das Datastorage übergeben. Bei Erfolgreicher Speicherung wird TRUE, bei einem Fehler FALSE zurückgegeben.
     *
     * @param test - Der hinzuzufügende Testfall als TestCase-Objekt
     * @return TRUE/FALSE - Status, ob das Hinzufügen erfolgreich war
     */
    public boolean addTestCaseToDataStorage(TestCase test) {
//		return dataStorageFacade.addTestCase(test);
        return false;
    }

    /**
     * Diese Methode dient dazu Testfälle aus dem Datastorage zu löschen. Der zu löschende Testfall wird als TestCase-Objekt
     * übergeben. Bei Erfolgreichem Löschen wird TRUE, bei einem Fehler FALSE zurückgegeben.
     *
     * @param test - Der zu löschende Testfall als TestCase-Objekt
     * @return TRUE/FALSE - Status, ob das Löschen erfolgreich war
     */
    public boolean deleteTestCaseFromDataStorage(TestCase test) {
//		return dataStorageFacade.deleteTestCase(test);
        return false;
    }

    /**
     * Diese Methode überprüft, ob das Datastorage erreichbar ist. Ist es verfügbar wird TRUE, ansonsten FALSE zurückgegeben.
     *
     * @return TRUE/FALSE - Status der Verfügbarkeit des Datastorage
     */
    public boolean getDataStorageStatus() {
        return this.dbStatus;
    }
	
	/*
	 * @author: a.Kutekidila 
	 * 
	 */

    public void updateProgressBar(int progress) {
        //this.bar.setSelection(progress);
        ApplicationWindows_New.getProgressBar().setSelection(progress);
    }

    public void updateProgressBarMax(int progressMax) {
        //this.bar.setMaximum(progressMax);
        ApplicationWindows_New.getProgressBar().setMaximum(progressMax);
    }

    /**
     * @return the app
     */
    public ApplicationWindows_New getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(ApplicationWindows_New app) {
        this.app = app;
    }

    public void updateLabelStatus(int progressCounter, int progressMax) {
        ApplicationWindows_New.getLabelStatusTestcase().setText("Testcase: " + progressCounter + "/" + progressMax);
    }

    /**
     * @return the dbStatus
     */
    public boolean isDbStatus() {
        return dbStatus;
    }

    /**
     * @param dbStatus the dbStatus to set
     */
    public void setDbStatus(boolean dbStatus) {
        this.dbStatus = dbStatus;
    }

}
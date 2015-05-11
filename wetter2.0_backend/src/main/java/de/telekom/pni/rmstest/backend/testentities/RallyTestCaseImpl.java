package de.telekom.pni.rmstest.backend.testentities;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestStep;

/**
 * Die Klasse RallyTestCaseImpl.java wird von der Rally Schnittstelle für die Verwaltung
 * von Testfällen verwendet. Es können alle nötigen Daten zum Testfall abgelegt werden.
 * Um die Kompatibilität zum Framework zu gewährleisten, implementiert die Klasse das TestCase
 * interface und seine Methoden.
 *
 * @author M.Forster
 */
public class RallyTestCaseImpl implements TestCase {

    private static final Logger log = Logger.getLogger(RallyTestCaseImpl.class);
    private int testID;
    private String scriptDeveloper;
    private String creationDate;
    private String description;
    private String browser;
    private String automatable;
    private String executableClass;
    private String testCaseName;
    private String lastRun;
    private TestSet testSet;
    private final String jsonSeparator = "\"";
    private final String blank = "";
    private Vector<TestStep> steps;
    private String status;

    /**
     * Konstruktor der Klasse. Er trägt die Daten aus dem ihm übergebenen JsonObject in die passenden Variablen
     * ein und initialisiert den Vector steps.
     *
     * @param testCaseRally - JsonObject mit den Daten zum Testfall
     */
    public RallyTestCaseImpl(JsonObject testCaseRally) {
        log.debug("Creating RallyTestCaseImpl-Object");
        this.testCaseName = testCaseRally.getAsJsonObject().get("_refObjectName").toString().replaceAll(jsonSeparator, blank);
        this.testID = Integer.valueOf(testCaseRally.getAsJsonObject().get("ObjectID").toString());
        this.scriptDeveloper = testCaseRally.getAsJsonObject().get("ScriptDeveloper").toString().replaceAll(jsonSeparator, blank);
        this.browser = testCaseRally.getAsJsonObject().get("Browser").toString().replaceAll(jsonSeparator, blank);
        this.executableClass = testCaseRally.getAsJsonObject().get("Executable").toString().replaceAll(jsonSeparator, blank);
        this.automatable = testCaseRally.getAsJsonObject().get("Automatable").toString().replaceAll(jsonSeparator, blank);
        this.status = testCaseRally.getAsJsonObject().get("LastVerdict").toString().replaceAll(jsonSeparator, blank);
        this.lastRun = testCaseRally.getAsJsonObject().get("LastRun").toString().replaceAll(jsonSeparator, blank);

        this.steps = new Vector<TestStep>();

        this.creationDate = "";
        JsonObject jsonTestSet = new JsonObject();
        jsonTestSet.addProperty("_refObjectName", "dummy");
        jsonTestSet.addProperty("_ref", "testset/544392.js");
        this.testSet = new RallyTestSetImpl(jsonTestSet);
        log.debug("RallyTestCaseImpl-Object created successfully");
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von testID.
     *
     * @return testID - ID des Testfalls als Integer
     */
    public Integer getTestCaseID() {
        return testID;
    }

    /**
     * Setter Merhode zum Setzen des Werts für testID.
     *
     * @param testID - Der zu setzende Wert für die ID des Testfalls
     */
    public void setTestID(int testID) {
        this.testID = testID;
    }

    /**
     * Setter Methode zum Setzen des Werts für developer.
     *
     * @param developer - Der zu setzende Wert für den Developer als String
     */
    public void setDeveloper(String scriptDeveloper) {
        this.scriptDeveloper = scriptDeveloper;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von browser.
     *
     * @return browser - Browser des Testfalls als String
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * Setter Methode zum Setzen des Werts von browser.
     *
     * @param browser - Der zu setzende Wert für den Browser als String
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von automatable.
     *
     * @return automatable - Automatable des Testfalls als String
     */
    public String getAutomatable() {
        return automatable;
    }

    /**
     * Setter Methode zum Setzen des Werts für automatable.
     *
     * @param automatable - Der zu setzende Wert für Automatable als String
     */
    public void setAutomatable(String automatable) {
        this.automatable = automatable;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von executableClass.
     *
     * @return executableClass - executableClass als String
     */
    public String getExecutableClass() {
        return executableClass;
    }

    /**
     * Setter Methode zum Setzen des Werts für executableClass.
     *
     * @param exClass - Der zu setzende Wert für executableClass als String
     */
    public void setExecutableClass(String executableClass) {
        this.executableClass = executableClass;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von testCaseName.
     *
     * @return testCaseName - Name des Testfalls als String
     */
    public String getTestName() {
        return testCaseName;
    }

    /**
     * Setter Methode zum Setzen des Werts vpn testCaseName.
     *
     * @param testCaseName - der zu setzende Wert für den Namen des Testfalls als String
     */
    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testCaseName.
     *
     * @return testCaseName - Name des Testfalls als String
     */
    @Override
    public String getTestCaseName() {
        return testCaseName;
    }

    /**
     * Getter Methode, die aus testCaseName den eigentlichen Namen des Tests extrahiert und ihn als
     * String zurückgibt.
     *
     * @return - simpleTestName - Der eigentliche Name des Testfalls als String
     */
    @Override
    public String getSimpleTestName() {
        return getTestCaseName().substring(getTestCaseName().lastIndexOf(".") + 1);
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von testSet.
     *
     * @return testSet - Testset des Tests als TestSet Objekt
     */
    @Override
    public TestSet getTestSet() {
        return testSet;
    }

    /**
     * Setter Merhode zum Setzen des Werts für testSet.
     *
     * @param testSet - Der zu setzende Wert für testSet als TestSet Objekt
     */
    @Override
    public void setTestSet(TestSet testSet) {
        this.testSet = testSet;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von steps.
     *
     * @return steps - Vector von TestSteps
     */
    @Override
    public Vector<TestStep> getTestSteps() {
        return steps;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von developer.
     *
     * @return developer - Name des Entwicklers als String
     */
    @Override
    public String getDeveloper() {
        return scriptDeveloper;
    }

    /**
     * Methode zum Hinzufügen eines Testschritts zum Vector, der alle
     * Testschritte zum Testfall beinhaltet.
     *
     * @param step - Der hinzuzufügende Testschritt als TestStep Objekt
     */
    public void addTestStep(TestStep step) {
        steps.add(step);
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von creationDate.
     *
     * @return creationDate - Das Erstellungsdatum als String
     */
    @Override
    public String getCreationDate() {
        return this.creationDate;
    }

    /**
     * Setter Merhode zum Setzen des Werts für testID.
     *
     * @param testID - Der zu setzende Wert für die ID des Testfalls
     */
    @Override
    public void setTestCaseID(Integer testCaseId) {
        this.testID = testCaseId;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von description.
     *
     * @return description - Beschreibung des Testfalls als String.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter Methode zum Setzen der Beschreibung des Testfalls.
     *
     * @param description - Zu setzender Wert für die Beschreibung des Testfalls als String.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter Methode zum Setzen des Werts für creationDate.
     *
     * @param creationDate - Der zu setzende Wert für das Erstellungsdatum des Testfalls
     */
    public void setCreationDate(String createDate) {
        this.creationDate = createDate;
    }

    /**
     * Setter Methode zum Setzen des Status des Testfalls.
     *
     * @param status - Zu setzender Wert für den Status des Testfalls als boolean.
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von status.
     *
     * @return status - Status des Testfalls als boolean
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public String getLastRun() {
        return lastRun;
    }

    @Override
    public void setLastrun(String lastRun) {
        this.lastRun = lastRun;

    }


}

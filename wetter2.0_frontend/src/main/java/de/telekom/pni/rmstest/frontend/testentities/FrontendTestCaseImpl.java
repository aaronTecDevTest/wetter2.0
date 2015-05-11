package de.telekom.pni.rmstest.frontend.testentities;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestStep;

/**
 * Die Klasse BackendTestCaseImpl.java wird im Frontend des Frameworks für die Verwaltung
 * von Testfällen verwendet. Es können alle nötigen Daten zum Testfall abgelegt werden.
 * Um die Kompatibilität zum Framework zu gewährleisten, implementiert die Klasse das TestCase
 * interface und seine Methoden.
 *
 * @author M.Forster
 */
public class FrontendTestCaseImpl implements TestCase {

    private static final Logger log = Logger.getLogger(FrontendTestCaseImpl.class);
    private TestSet testSet;
    private String creationDate;
    private String description;
    private Integer testID;
    private String testCaseName;
    private String executableClass;
    private String developer;
    private String lastRun;
    private String automatable;
    private Vector<TestStep> steps;
    private String status;

    /**
     * Konstruktor der Klasse. Der Konstruktor trägt den bei der Erzeugung des Objekts übergebenen Namen
     * des Testfalls ein und intitialisiert die Variablen steps, testSet und status mit initialen Werten.
     *
     * @param testCaseName - Name des Testfalls als String
     */
    public FrontendTestCaseImpl(String testCaseName) {
        log.debug("Creating FrontendTestCaseImpl-Object");
        this.testCaseName = testCaseName;
        this.steps = new Vector<TestStep>();
        this.testSet = new FrontendTestSetImpl("", 0);
        status = "Fail";//false;
        log.debug("FrontendTestCaseImpl-Object created successfully");
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
     * Getter Methode zum Abrufen des aktuellen Wertes von steps.
     *
     * @return steps - Vector von TestSteps
     */
    @Override
    public Vector<TestStep> getTestSteps() {
        return steps;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von testCaseName.
     *
     * @return testCaseName - Name des Testfalls als String
     */
    @Override
    public String getTestCaseName() {
        return testCaseName;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von executableClass.
     *
     * @return executableClass - executableClass als String
     */
    @Override
    public String getExecutableClass() {
        return executableClass;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von developer.
     *
     * @return developer - Name des Entwicklers als String
     */
    @Override
    public String getDeveloper() {
        return developer;
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
     * Getter Methode zum Abrufen des aktuellen Wertes von testID.
     *
     * @return testID - ID des Testfalls als Integer
     */
    public Integer getTestCaseID() {
        return testID;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von creationDate.
     *
     * @return creationDate - Das Erstellungsdatum als String
     */
    @Override
    public String getCreationDate() {
        return creationDate;
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
     * Setter Methode zum Setzen des Werts für executableClass.
     *
     * @param exClass - Der zu setzende Wert für executableClass als String
     */
    @Override
    public void setExecutableClass(String exClass) {
        // TODO Auto-generated method stub
        executableClass = exClass;
    }

    /**
     * Setter Methode zum Setzen des Werts für developer.
     *
     * @param developer - Der zu setzende Wert für Developer als String
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
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
     * Setter Methode zum Setzen des Werts für creationDate.
     *
     * @param creationDate - Der zu setzende Wert für das Erstellungsdatum des Testfalls
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Setter Merhode zum Setzen des Werts für testID.
     *
     * @param testID - Der zu setzende Wert für die ID des Testfalls als Integer
     */
    @Override
    public void setTestCaseID(Integer testCaseId) {
        this.testID = testCaseId;
    }

    /**
     * Methode zum Ersetzen aller Testschritte des Testfalls. Die bereits eingetragenen Testschritte werden
     * gelöscht und mit den Schritten aus der der Methode übergebenen Liste von Testschritten ersetzt.
     *
     * @param steps - Liste von Testschritten
     */
    public void setTestSteps(List<TestStep> steps) {
        for (TestStep step : steps) {
            this.steps.add(step);
        }

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
     * Setter Methode zum Setzen des Status des Testfalls.
     *
     * @param status - Zu setzender Wert für den Status des Testfalls als boolean.
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Diese Methode überschreibt die toString() Methode der Object Klasse.
     * Die Methode prüft ob es sich bei dem Testfall um einen internen oder externen
     * Testfall handelt und prüft anhande des Status, ob der Testfall bereits in der
     * Datenbank existiert. Danach wird ein passender String erzeugt und der dann
     * zurückgegeben.
     *
     * @return testString - String bestehend aus Name und weiteren Informationen zum Testfall
     */
    @Override
    public String toString() {
        if (executableClass.contains("internalTest")) {
            if (status == "Pass") {
                return testCaseName + " (internal - Testdata)";
            } else {
                return testCaseName + " (internal - No Testdata)";
            }
        } else {
            return testCaseName + " (external)";
        }
    }

    /**
     * Methode zum Hinzufügen eines Testschritts zum Vector, der alle
     * Testschritte zum Testfall beinhaltet.
     *
     * @param step - Der hinzuzufügende Testschritt als TestStep Objekt
     */
    public void addTestStep(TestStep step) {
        this.steps.add(step);
    }

    @Override
    public String getLastRun() {

        return lastRun;
    }

    @Override
    public String getAutomatable() {
        return automatable;
    }

    @Override
    public void setAutomatable(String automatable) {
        this.automatable = automatable;

    }

    @Override
    public void setLastrun(String lastRun) {
        this.lastRun = lastRun;

    }


}

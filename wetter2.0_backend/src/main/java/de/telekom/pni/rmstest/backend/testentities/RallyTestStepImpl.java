package de.telekom.pni.rmstest.backend.testentities;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import de.telekom.pni.rmstest.backend.testentities.interfaces.TestStep;

/**
 * Die Klasse RallyTestStepImpl.java wird von der Rally Schnittstelle für die Verwaltung
 * von Testschritten verwendet. Es können alle nötigen Daten zum Testschritt abgelegt werden.
 * Um die Kompatibilität zum Framework zu gewährleisten, implementiert die Klasse das TestStep
 * interface und seine Methoden.
 *
 * @author M.Forster
 */
public class RallyTestStepImpl implements TestStep {

    private static final Logger log = Logger.getLogger(RallyTestStepImpl.class);
    private String testStepAction;
    private String testStepInput;
    private String testStepExpected;
    private Integer testStepID;
    private String testStepName;

    /**
     * Konstruktor der Klasse. Er extrahiert die Daten für die Testschritte aus dem ihm übergebenen
     * JsonObject und trägt die Daten in die passenden Variablen ein.
     *
     * @param testStepRally - JsonObject mit den Testschritt Daten
     */
    public RallyTestStepImpl(JsonObject testStepRally) {
        log.debug("Creating RallyTestStepImpl-Object");
        this.testStepID = Integer.valueOf(testStepRally.getAsJsonObject().get("STEPID").toString());
        this.testStepName = testStepRally.getAsJsonObject().get("STEPNAME").toString();
        this.testStepAction = testStepRally.getAsJsonObject().get("STEPACTION").toString();
        this.testStepExpected = testStepRally.getAsJsonObject().get("STEPEXPECTED").toString();
        this.testStepInput = testStepRally.getAsJsonObject().get("STEPINPUT").toString();
        log.debug("RallyTestStepImpl-Object created");
    }

    /**
     * Leerer Standard Konstruktor der Klasse.
     */
    public RallyTestStepImpl() {

    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testStepID.
     *
     * @return testStepID - ID des Testschritts als int
     */
    @Override
    public int getTestStepID() {
        // TODO Auto-generated method stub
        return testStepID;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testStepName.
     *
     * @return testStepName - Name des Testschritts als String
     */
    @Override
    public String getTestStepName() {
        // TODO Auto-generated method stub
        return testStepName;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testStepAction.
     *
     * @return testStepAction - Action des Testschritts als String
     */
    @Override
    public String getTestStepAction() {
        return testStepAction;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testStepInput.
     *
     * @return testStepInput - Input des Testschritts als String
     */
    @Override
    public String getTestStepInput() {
        return testStepInput;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testStepExpeced.
     *
     * @return testStepExpected - Expected des Testschritts als String
     */
    @Override
    public String getTestStepExpected() {
        return testStepExpected;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testStepTarget.
     *
     * @return testStepTarget - Target des Testschritts als String
     */
    @Override
    public String getTestStepTarget() {
        return null;
    }

    /**
     * Setter Methode zum Setzen des Werts von testStepInput.
     *
     * @param testStep Action - Der zu setzende Wert für testStepAction als String
     */
    public void setTestStepAction(String testStepAction) {
        this.testStepAction = testStepAction;
    }

    /**
     * Setter Methode zum Setzen des Werts von testStepInput.
     *
     * @param testStepInput - Der zu setzende Wert für testStepInput als String
     */
    public void setTestStepInput(String testStepInput) {
        this.testStepInput = testStepInput;
    }

    /**
     * Setter Methode zum Setzen des Werts von testStepExpected.
     *
     * @param testStepExpected - Der zu setzende Wert für testStepExpected als String
     */
    public void setTestStepExpected(String testStepExpected) {
        this.testStepExpected = testStepExpected;
    }

    /**
     * Setter Methode zum Setzen des Werts von testStepName.
     *
     * @param testStepName - Der zu setzende Wert für testStepName als String
     */
    @Override
    public void setTestStepName(String name) {
        this.testStepName = name;
    }

    /**
     * Setter Methode zum Setzen des Werts von testStepID.
     *
     * @param testStepID - Der zu setzende Wert für testStepID als int
     */
    @Override
    public void setTestStepID(int id) {
        this.testStepID = id;
    }

    /**
     * Setter Methode zum Setzen des Werts von testStepTarget.
     *
     * @param testStepTarget - Der zu setzende Wert für testStepTarget als String
     */
    @Override
    public void setTestStepTarget(String target) {
    }
}

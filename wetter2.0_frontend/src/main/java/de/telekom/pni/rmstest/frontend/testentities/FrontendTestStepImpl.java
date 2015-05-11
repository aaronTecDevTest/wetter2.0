package de.telekom.pni.rmstest.frontend.testentities;

import org.apache.log4j.Logger;

import de.telekom.pni.rmstest.backend.testentities.interfaces.TestStep;

/**
 * Die Klasse FrontendTestStepImpl.java wird im Frontend des Frameworks für die Verwaltung
 * von Testschritten verwendet. Es können alle nötigen Daten zum Testschritt abgelegt werden.
 * Um die Kompatibilität zum Framework zu gewährleisten, implementiert die Klasse das TestStep
 * interface und seine Methoden.
 *
 * @author M.Forster
 */
public class FrontendTestStepImpl implements TestStep {

    private static final Logger log = Logger.getLogger(FrontendTestStepImpl.class);
    private String testStepAction;
    private String testStepTarget;
    private String testStepInput;
    private String testStepExpected;
    private int testStepID;
    private String testStepName;

    /**
     * Konstruktor der Klasse. Er trägt die ihm übergebenen Werte für testStepID, testStepName, testStepAction,
     * testSetepInput, testStepExpected und testStepTarget in die passenden Variablen ein.
     *
     * @param testStepID       - ID des Testschritts als int
     * @param testStepName     - Name des Testschritts als String
     * @param testStepAction   - Action des Testschritts als String
     * @param testStepInput    - Input des Testschritts als String
     * @param testStepExpected - Expected des Testschritts als String
     * @param testStepTarget   - Target des Testschritts als String
     */
    public FrontendTestStepImpl(int testStepID, String testStepName, String testStepAction, String testStepInput, String testStepExpected, String testStepTarget) {
        log.debug("Creating FrontedTestStepImpl-Object");
        this.testStepID = testStepID;
        this.testStepName = testStepName;
        this.testStepAction = testStepAction;
        this.testStepExpected = testStepExpected;
        this.testStepInput = testStepInput;
        this.testStepTarget = testStepTarget;
        log.debug("FrontedTestStepImpl-Object created");
    }

    /**
     * Leerer Standard Konstruktor der Klasse.
     */
    public FrontendTestStepImpl() {

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
     * Getter Methode zum Abrufen des aktuellen Werts von testStepID.
     *
     * @return testStepID - ID des Testschritts als int
     */
    @Override
    public int getTestStepID() {
        return testStepID;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testStepName.
     *
     * @return testStepName - Name des Testschritts als String
     */
    @Override
    public String getTestStepName() {
        return testStepName;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testStepTarget.
     *
     * @return testStepTarget - Target des Testschritts als String
     */
    @Override
    public String getTestStepTarget() {
        return testStepTarget;
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
     * Setter Methode zum Setzen des Werts von testStepExpected.
     *
     * @param testStepExpected - Der zu setzende Wert für testStepExpected als String
     */
    public void setTestStepExpected(String testStepExpected) {
        this.testStepExpected = testStepExpected;
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
    public void setTestStepTarget(String testStepTarget) {
        this.testStepTarget = testStepTarget;
    }
}

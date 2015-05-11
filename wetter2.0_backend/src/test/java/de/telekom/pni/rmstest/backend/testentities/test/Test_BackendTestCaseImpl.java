package de.telekom.pni.rmstest.backend.testentities.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.telekom.pni.rmstest.backend.testentities.BackendTestCaseImpl;
import de.telekom.pni.rmstest.backend.testentities.BackendTestStepImpl;

/**
 * Eine JUnit Testklasse, die die Methoden der BackendTestCaseImpl Klasse überprüft.
 *
 * @author M.Forster
 */
public class Test_BackendTestCaseImpl {

    /**
     * Diese TestMethode überprüft ob ein BackendTestCaseImpl Objekt korrekt erzeugt wird.
     */
    @Test
    public void testConstructor() {
        BackendTestCaseImpl test = new BackendTestCaseImpl("Test 1");
        try {
            test.getTestCaseName().getBytes();
        } catch (Exception ex) {
            fail("Error: Test Case Name was not initialized correctly. " + ex.getMessage());
        }
        try {
            test.getTestSet().getConfig();
        } catch (Exception ex) {
            fail("Error: The TestSet of the TestCase was not initializes correctly. " + ex.getMessage());
        }
        try {
            test.getTestSteps().getClass();
        } catch (Exception ex) {
            fail("Error: TestSteps Vector was not initialized correctly. " + ex.getMessage());
        }
    }

    /**
     * Diese Test-Methode überprüft das Hinzufügen eines korrekten Testschritts zum Testfall.
     */
    @Test
    public void testAddTestStep() {
        BackendTestCaseImpl test = new BackendTestCaseImpl("Test 1");
        test.addTestStep(new BackendTestStepImpl(1, "Step Name", "Action", "Input", "Expected", "Target"));
        try {
            test.getTestSteps().get(0).getTestStepID();
        } catch (Exception ex) {
            fail("Error: TestStep was not added correctly. " + ex.getMessage());
        }
    }

}

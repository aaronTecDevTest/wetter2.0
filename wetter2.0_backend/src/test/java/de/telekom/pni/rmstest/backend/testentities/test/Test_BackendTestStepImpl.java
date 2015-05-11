package de.telekom.pni.rmstest.backend.testentities.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.telekom.pni.rmstest.backend.testentities.BackendTestStepImpl;

/**
 * Eine JUnit Testklasse, die die Methoden der BackendTestStepImpl Klasse überprüft.
 *
 * @author M.Forster
 */
public class Test_BackendTestStepImpl {

    /**
     * Diese Test-Merhode überprüft, ob FrontendTestStepImpl Objekte korrekt erzeugt werden.
     */
    @Test
    public void testConstructor() {
        BackendTestStepImpl testStep = new BackendTestStepImpl(1, "Step Name", "Action", "Input", "Expected", "Target");
        try {
            testStep.getTestStepID();
        } catch (Exception ex) {
            fail("Error: TestStep ID was not initialized correctly. " + ex.getMessage());
        }
        try {
            testStep.getTestStepName().getBytes();
        } catch (Exception ex) {
            fail("Error: TestStep Name was not initialized correctly. " + ex.getMessage());
        }
        try {
            testStep.getTestStepAction().getBytes();
        } catch (Exception ex) {
            fail("Error: TestStep Action was not initialized correctly. " + ex.getMessage());
        }
        try {
            testStep.getTestStepExpected().getBytes();
        } catch (Exception ex) {
            fail("Error: TestStep Expected was not initialized correctly. " + ex.getMessage());
        }
        try {
            testStep.getTestStepInput().getBytes();
        } catch (Exception ex) {
            fail("Error: TestStep Input was not initialized correctly. " + ex.getMessage());
        }
    }

}

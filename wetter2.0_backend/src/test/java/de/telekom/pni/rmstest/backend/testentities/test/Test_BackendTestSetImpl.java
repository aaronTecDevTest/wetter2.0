package de.telekom.pni.rmstest.backend.testentities.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.telekom.pni.rmstest.backend.testentities.BackendTestCaseImpl;
import de.telekom.pni.rmstest.backend.testentities.BackendTestSetImpl;

/**
 * Eine JUnit Testklasse, die die Methoden der BackendTestSetImpl Klasse überprüft.
 *
 * @author M.Forster
 */
public class Test_BackendTestSetImpl {

    /**
     * Diese Test-Methode überprüft ob ein BackendTestSetImpl Objekt korrekt erzeugt wird.
     */
    @Test
    public void testConstructor() {
        BackendTestSetImpl testSet = new BackendTestSetImpl("Test1", 1);
        try {
            testSet.getTestSetName().getBytes();
        } catch (Exception ex) {
            fail("Error: TestSet Name was not initialized correctly. " + ex.getMessage());
        }
        try {
            testSet.getTestSetID().intValue();
        } catch (Exception ex) {
            fail("Error: TestSet ID was not initialized correctly. " + ex.getMessage());
        }
        try {
            testSet.getTestCases().getClass();
        } catch (Exception ex) {
            fail("Error: TestCases Vector was not initialized correctly. " + ex.getMessage());
        }
    }

    /**
     * Diese Test-Merhode überprüft das Hinzufügen eines korrekten Testfalls.
     */
    @Test
    public void testAddTestCase() {
        BackendTestSetImpl testSet = new BackendTestSetImpl("Test1", 1);
        testSet.addTestCase(new BackendTestCaseImpl("Test 1"));
        try {
            testSet.getTestCases().get(0).getTestCaseName();
        } catch (Exception ex) {
            fail("Error: TestCase was not added correctly. " + ex.getMessage());
        }
    }

}

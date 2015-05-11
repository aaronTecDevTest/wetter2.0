package de.telekom.pni.rmstest.backend.core.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.telekom.pni.rmstest.backend.testcases.TC46_TestCaseUS001;

/**
 * Eine JUnit Testklasse, die das Erzeugen von konkreten JUnitTest.java Klassen testet.
 *
 * @author M.Forster
 */
public class Test_JUnitTest_createObject {

    private TC46_TestCaseUS001 test;

    /**
     * Diese Methode wird vor jedem einzelnen Test aufgerufen, und erzeugt jedes mal ein neues konkretes
     * JUnitTest.java Objekt.
     */
    @Before
    public void setUpBeforeClass() throws Exception {
        test = new TC46_TestCaseUS001();
    }

    /**
     * Diese Methode wird nach jedem Test aufgerufen und sorgt dafür, dass der Webbrowser geschlossen wird.
     *
     * @throws Exception
     */
    @After
    public void tearDownAfterClass() throws Exception {
        test.closeBrowser();
    }

    /**
     * Dieser Test überprüft, ob alle nötigen Variabeln des Objektes initialisiert wurden.
     */
    @Test
    public void testCreateObject() {
        try {
            test.getErrorString().getBytes();
        } catch (NullPointerException ex) {
            fail("errorString was not initialized. " + ex.getMessage());
        }
        try {
            test.getFailureString().getBytes();
        } catch (NullPointerException ex) {
            fail("failureString was not initialized. " + ex.getMessage());
        }
        try {
            test.getReporter().getStatus();
        } catch (NullPointerException ex) {
            fail("Reporter was not created. " + ex.getMessage());
        }
    }
}
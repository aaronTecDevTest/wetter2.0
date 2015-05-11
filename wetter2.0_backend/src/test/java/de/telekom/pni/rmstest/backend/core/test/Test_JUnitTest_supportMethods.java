package de.telekom.pni.rmstest.backend.core.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.telekom.pni.rmstest.backend.testcases.TC46_TestCaseUS001;

/**
 * Eine JUnit Testklasse, die die Methoden für den Zugriff auf den Reporting Mechanismus testet.
 *
 * @author M.Forster
 */
public class Test_JUnitTest_supportMethods {

    private static TC46_TestCaseUS001 test;

    /**
     * Diese Methode wird vor dem ersten Test ausgeführt und erzeugt ein konkretes JUnitTest Objekt.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        test = new TC46_TestCaseUS001();
    }

    /**
     * Diese Methode wird nach dem letzten Test ausgeführt und sorgt dafür, dass der Webbrowser geschlossen wird.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        test.closeBrowser();
    }

    /**
     * Dieser Test prüft, ob die appendFailure Methode auch mit ungültigen
     * Werten als Übergabewert zurecht kommt.
     */
    @Test
    public void testAppendFailureNull() {
        try {
            test.appendFailure(null);
        } catch (NullPointerException ex) {
            fail("Exception thrown. " + ex.getMessage());
        }
    }

    /**
     * Dieser Test prüft, ob die appendFailure Methode ordnungsgemäß funktioniert.
     */
    @Test
    public void testAppendFailure() {
        test.appendFailure("test");
        assertEquals("test", test.getFailureString());
        test.appendFailure("test");
        assertEquals("testtest", test.getFailureString());
    }

    /**
     * Dieser Test prüft, ob die appendError Methode auch mit ungültigen
     * Werten als Übergabewert zurecht kommt.
     */
    @Test
    public void testAppendErrorNull() {
        try {
            test.appendError(null);
        } catch (NullPointerException ex) {
            fail("Exception thrown. " + ex.getMessage());
        }
    }

    /**
     * Dieser Test prüft, ob die appendError Methode ordnungsgemäß funktioniert.
     */
    @Test
    public void testAppendError() {
        test.appendError("test");
        assertEquals("test", test.getErrorString());
        test.appendError("test");
        assertEquals("testtest", test.getErrorString());
    }

}

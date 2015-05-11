package de.telekom.pni.rmstest.backend.core.test;

import static org.junit.Assert.*;

import java.util.Properties;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.testcases.TC46_TestCaseUS001;

/**
 * Eine JUnit Testklasse, die die Testmethoden der JUnitTest Klasse testet.
 *
 * @author M.Forster
 */
public class Test_JUnitTest_testingMethods {

    private static TC46_TestCaseUS001 test;
    public final static Properties TESTRUN = PropertiesManager_New.getInstance().getProperties("testrun");

    /**
     * Diese Methode wird vor der Durchführung der Tests ausgeführt und initilisiert alle benötigten Komponenten.
     */
    @BeforeClass
    public static void beforAll() {
        String URL = TESTRUN.getProperty("run.standard.machine") + "/rms/app";
        test = new TC46_TestCaseUS001();
        test.before();
        test.getPage(URL);
    }

    /**
     * Diese Methode wird vor jedem Testfall ausgeführt und initialisiert die benötigten Komponenten.
     */
    @Before
    public void beforTest() {
        test.getReporter().createReport("Test", "FF");
    }

    /**
     * Diese Methode wird nach der Durchführung aller Tests ausgeführt und sorgt für die Bereinigung.
     */
    @AfterClass
    public static void afterAll() {
        test.after();
    }

    /**
     * Diese Test-Methode überprüft die getElement Methode der getesteten Klasse. Es wird die Reaktion
     * auf ungültige Übergabeparameter geprüft.
     */
    @Test
    public void testGetElement_Negative() {
        try {
            @SuppressWarnings("unused")
            WebElement element = test.getElement("xpath", "");
            fail("No Error thrown");
        } catch (Error er) {
            System.out.println("Correct Error thrown");
        } catch (Exception ex) {
            fail("Exception thrown");
        }
    }

    /**
     * Diese Test-Methode überprüft die getElement Methode der getesteten Klasse. Es wird geprüft,
     * ob die Korrekten Daten zurückgegeben werden.
     */
    @Test
    public void testGetElement_Positive() {
        WebElement element = test.getElement("xpath", ".//*[contains(text(),'Login')]");
        if (!element.getText().equals("Login")) {
            fail("Wrong Element");
        }
        if (test.getReport().getSteps().size() == 0) {
            fail("No step added to report");
        }

    }

    /**
     * Diese Test-Methode überprüft die testEquals Methode der getesteten Klasse. Es wird das Verhalten
     * bei fehlerhaften Übergabeparametern geprüft.
     */
    @Test
    public void testTestEquals_wrongParamExpected() {
        try {
            WebElement element = null;
            test.testEquals(element, "Login", "Step 1");
            if (test.getReport().getSteps().size() == 0) {
                throw new Error("No step added to report");
            }
        } catch (Exception e) {
            fail("Exception thrown" + e.getMessage());
        } catch (Error er) {
            fail(er.getMessage());
        }
    }

    /**
     * Diese Test-Methode überprüft die testEquals Methode der getesteten Klasse. Es wird geprüft,
     * ob die Methode korrekt erkennt, dass die geprüften Daten übereinstimmen.
     */
    @Test
    public void testTestEquals_Negative() {
        try {
            WebElement element = test.getElement("xpath", ".//*[@id='T-login-part-right-login-button']");
            test.testEquals(element, "", "Step 1");
            if (test.getReport().getSteps().size() == 0) {
                throw new Error("No step added to report");
            }
            fail("No Exception thrown");
        } catch (AssertionError ae) {
            System.out.println("Assert exception thrown. " + ae.getMessage());
        } catch (Exception ex) {

            fail(ex.getMessage());
        }
    }

    /**
     * Diese Test-Methode überprüft die elementPresent Methode der getesteten Klasse. Es wird das
     * Verhalten bei fehlerhaften Übergabeparametern geprüft.
     */
    @Test
    public void testElementPresent_Negative() {
        try {
            test.testElementPresent("", "Step 1");
            if (test.getReport().getSteps().size() == 0) {
                throw new Error("No step added to report");
            }
            fail("No Exception thrown");
        } catch (AssertionError ae) {
            System.out.println("Exception thrown: " + ae.getMessage());
        } catch (Exception er) {
            fail(er.getMessage());
        }
    }

    /**
     * Diese Test-Methode überprüft die elementPresent Merhode der getesteten Klasse. Es wird geprüft,
     * ob die Klasse korrekt erkennt, dass ein Element vorhanden ist.
     */
    @Test
    public void testElementPresent_Positive() {
        try {
            test.testElementPresent(".//*[@id='T-login-part-right-login-button", "Step 1");
            if (test.getReport().getSteps().size() == 0) {
                throw new Error("No step added to report");
            }
        } catch (Exception e) {
            fail("Exception thrown");
        } catch (Error er) {
            fail(er.getMessage());
        }
    }

    /**
     * Diese Test-Methode überprüft die Methode elementNotPresent der getesteten Klasse. Es wird geprüft,
     * ob korrekt erkannt wird, dass ein Element nicht vorhanden ist.
     */
    @Test
    public void testElementNotPresent_Positive() {
        try {
            test.testElementPresent("", "Step 1");
            if (test.getReport().getSteps().size() == 0) {
                throw new Error("No step added to report");
            }
        } catch (Exception e) {
            fail("Exception thrown");
        } catch (Error er) {
            fail(er.getMessage());
        }
    }

    /**
     * Diese Test-Methode überprüft die Methode elementNotPresent der getesteten Klasse. Es wird das
     * Verhalten auf fehlerhafte Übergabeparameter geprüft.
     */
    @Test
    public void testElementNotPresent_Negative() {
        try {
            test.testElementPresent(".//*[@id='T-login-part-right-login-button", "Step 1");
            if (test.getReport().getSteps().size() == 0) {
                throw new Error("No step added to report");
            }
        } catch (Exception e) {
            fail("Exception thrown");
        } catch (Error er) {
            fail(er.getMessage());
        }
    }
}

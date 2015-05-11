package de.telekom.pni.rmstest.backend.testcases;

import java.util.Properties;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import de.telekom.pni.rmstest.backend.core.JUnitTest_New;


/**
 * Konkreter Beispiel Testfall, der auf Basis der Klasse JUnitTest.java erstellt wurde.
 * Die Klasse führt einen konkreten Testfall durch und Dient zum Test des Frameworks.
 *
 * @author M.Forster
 */
public class TC46_TestCaseUS001 extends JUnitTest_New {

    public static final Properties TESTRUN = PropertiesManager_New.getInstance().getProperties("TESTRUN");

    @Test
    public void runTest() {
        try {
            getPage(TESTRUN.getProperty("run.standard.machine") + "/rms/app");

			/*
			 * Test Description
			 *	Steps
			 * 
			 */
            testElementPresent(PAGE.getProperty("button.login"), "Check if login button exists");
            WebElement login = getElement("xpath", PAGE.getProperty("button.login"));
            login.click();

        } catch (AssertionError ae) {
            appendFailure("Assertion Error: " + ae.getMessage());
        } catch (Error ex) {
            appendError("Internal Error while running the Test. Please check the implementation. \r\n" + ex.getMessage());
        }
    }
}
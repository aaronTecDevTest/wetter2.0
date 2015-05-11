package de.telekom.pni.rmstest.backend.testcases;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.core.JUnitTest_New;

/**
 * Konkreter Beispiel Testfall, der auf Basis der Klasse JUnitTest.java erstellt wurde.
 * Die Klasse führt einen konkreten Testfall durch und Dient zum Test des Frameworks.
 *
 * @author M.Forster
 */
public class TC47_TestCaseUS002 extends JUnitTest_New {

    @Test
    public void runTest() {
        try {
            getPage(getRunningConfiguration());

			/*
			 * Test Description
			 *	Steps
			 * 
			 */
            WebElement button = getElement("xpath", PAGE.getProperty("button.login"));
            button.click();

            WebElement username = getElement("xpath", PAGE.getProperty("input.user"));
            username.clear();
            username.sendKeys("m.kurth");

            WebElement password = getElement("xpath", PAGE.getProperty("input.pwd"));
            password.sendKeys("start123");

            WebElement login = getElement("xpath", PAGE.getProperty("button.logindialog"));
            login.click();

            testElementPresent(PAGE.getProperty("lable.headline"), "Header present");

            testEquals(button, "Log", "Check Text Login Button");
        } catch (AssertionError ae) {
            appendFailure("Assertion Error: " + ae.getMessage());
        } catch (Error ex) {
            appendError("Internal Error while running the Test. Please check the implementation. \r\n" + ex.getMessage());
        }
    }

}
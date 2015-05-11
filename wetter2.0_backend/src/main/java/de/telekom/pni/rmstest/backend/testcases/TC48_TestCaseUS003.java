package de.telekom.pni.rmstest.backend.testcases;

import java.util.Properties;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;


/**
 * Konkreter Beispiel Testfall, der auf Basis der Klasse GenericTest.java erstellt wurde.
 * Die Klasse führt einen konkreten Testfall durch und Dient zum Test der Kompatibilität
 * des neuen Frameworks mit den alten Testfällen.
 *
 * @author M.Forster
 */
public class TC48_TestCaseUS003 extends GenericTest_New {

    private final String testCaseShortName = "RMS";
    public static final Properties PAGE = PropertiesManager_New.getInstance().getProperties("page");

    @Override
    public void runTest() {

        getBrowser();

        String navi = getNavigation(testCaseShortName);
        navigate(navi);

		/*
         * Test Description
		 * 		Steps
		 * 
		 */

        WebElement login = getByXPath(PAGE.getProperty("button.login"));
        checkExists(login, "Check if login button exists");

        clickOnElement(PAGE.getProperty("button.login"), "");

        // close browser
        closeBrowser(); // closes the browser and catches any errors
    }

    public static void main(String[] args) {
        TC48_TestCaseUS003 test = new TC48_TestCaseUS003();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);

    }

}
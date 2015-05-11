package de.telekom.pni.rmstest.backend.core;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import junit.framework.TestResult;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New;
import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import de.telekom.pni.rmstest.backend.manager.WebDriverManager;
import de.telekom.pni.rmstest.backend.reporting.Report;
import de.telekom.pni.rmstest.backend.reporting.Reporter;
import de.telekom.pni.rmstest.backend.utilities.TestingUtil;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Die Klasse GenericTest.java ist eine alternative Implementierung für Testfälle.
 * Die Klasse wurde vom ursprünglichen Entwickler des Frameworks implemtntiert, aber leider
 * nicht dokumentiert. Ich werde mich bei der Dokumentation dieser Klasse auf die von mir
 * durchgeführten Anpassungen beschränken.
 * Da für die Einbindun von JUnit im von der Klasse implementierten Interface Änderungen
 * nötig waren, musten auch in diese Klasse Änderungen vorgenommen werden. Die Änderungen
 * sind bei den jeweiligen Methoden näher beschrieben.
 *
 * @author A.Roth
 * @author M.Forster
 */
public abstract class GenericTestMF implements RMSTest_New {

    private static final Logger log = Logger.getLogger(GenericTest_New.class);
    public final Properties URL = PropertiesManager_New.getInstance().getProperties("url");
    public final int COMPARE_DIRECTION_LOWER_THAN = -1;
    public final int COMPARE_DIRECTION_BIGGER_THAN = 1;
    public final int COMPARE_DIRECTION_EQUAL = 0;

    private final String TAG_LINK = "a";
    protected final String LOG_FILES_URL = "AktiveURLLogFiles";

    private TestingUtil utils;
    private HashMap<String, String> urlValues = null;
    private RunningConfiguration_New runningConfiguration;
    private Reporter reporter;
    protected WebDriver browser;
    WebDriverManager driverManager = new WebDriverManager();

    public GenericTestMF() {
        this.utils = new TestingUtil();
        this.reporter = new Reporter();
    }

    public TestingUtil getUtils() {
        return utils;
    }

    public void setUtils(TestingUtil utils) {
        this.utils = utils;
    }

    public HashMap<String, String> getUrlValues() {
        return urlValues;
    }

    public void setUrlValues(HashMap<String, String> urlValues) {
        this.urlValues = urlValues;
    }

    public RunningConfiguration_New getRunningConfiguration() {
        return runningConfiguration;
    }

    public void setRunningConfiguration(
            RunningConfiguration_New runningConfiguration) {
        this.runningConfiguration = runningConfiguration;
    }

    public void setFailed() {
        logFailureCheckpoint(
                "Testrun failed",
                "Set failed method",
                "Testrun failed due to an unexpected runtime issue.");
    }

    /**
     * Setter method for the CMS-URL hash map.
     */
    public void setURLValues(HashMap<String, String> urlValues) {
        this.urlValues = urlValues;
    }

    /**
     * Getter for the CMS-URL hash map.
     *
     * @return Parses the CMS-URL.xml file and returns
     */
    /*
	public HashMap<String, String> getURLValues() {
		if (this.urlValues == null) {
			// try to create them
			try {
				return TestingUtil.parseRMSURL();
			} catch (SAXException e) {
				e.printStackTrace();
				return null;
			}
		}
		return this.urlValues;
	}*/

    /**
     * Get values from the CMS-URL.xml
     *
     * @param name
     *            The key for the value.
     * @return Value corresponding to the key.
     */
	/*public String getURLForName(String name) {
		try {
			HashMap<String, String> urls = getURLValues();
			return urls.get(name);
		} catch (Exception ex) {
			getStepLogger().logStep(
					new Step(false, "Obtaining URL value",
							"Corresponding value",
							"Error occured while getting value for " + name, ex
									.getStackTrace().toString()));
			return null;
		}
	}*/

    /**
     * Get the reporter for this test run.
     *
     * @return reporter (existing or new).
     */

    public Reporter getReporter() {
        if (this.reporter == null)
            this.reporter = new Reporter();
        return reporter;
    }
	/*public StepLogger getStepLogger() {
		if (this.stepLogger == null)
			this.stepLogger = new StepLogger();
		if (this.report == null) // prepare the report
			this.report = new Report();
		return stepLogger;
	}

	public void setStepLogger(StepLogger stepLog) {
		this.stepLogger = stepLog;
	}*/

    /**
     * Initializes and provides the report object for this test run.
     *
     * @return Report object (existing or new).
     */
    public Report getReport() {
        return this.getReporter().getReport();
    }

    /**
     * Get WebDriver object for a specified configuration or for the default
     * configuration.
     *
     * @return Browser (WebDriver object). Catches all potential exceptions and
     * logs them to a test step.
     */
    public WebDriver getBrowser() {
        if (this.browser != null)
            return this.browser;
        if (this.runningConfiguration == null) {
            this.runningConfiguration = RunningConfiguration_New.defaultFFOnSP();
            logFailureCheckpoint(
                    "Running configuration",
                    "USING DEFAULT CONFIGURATION!!!! MUST BE DISABLED FOR REGULAR RUNS.",
                    "USING DEFAULT CONFIGURATION!!!! MUST BE DISABLED FOR REGULAR RUNS.");
        }
        try {
//			this.browser = utils.getBrowser(getRunningConfiguration());
            this.browser = driverManager.setUpDriver(runningConfiguration);
            logSuccessCheckpoint(
                    "Open browser",
                    "Browser is opened successfully");
        } catch (Exception ex) {
            logFailureCheckpoint("Open browser",
                    "Browser is opened",
                    "Error occured while opening browser." + ex.getMessage());
            return null;
        }
        return this.browser;
    }

    public WebDriver getBrowserForCleanup() {
        if (this.browser != null)
            return this.browser;
        if (this.runningConfiguration == null) {
            this.runningConfiguration = RunningConfiguration_New.defaultFFOnSP();
        }
        try {
            this.browser = driverManager.setUpDriver(runningConfiguration);
        } catch (Exception ex) {
            log.error("Error while getting the Browser for cleanup: " + ex.getMessage());
            return null;
        }
        return this.browser;
    }

    /**
     * Only Firefox profile settings are set.
     *
     * @param profile
     * @return Firefox browser
     */
    public WebDriver setBrowserFirefox(FirefoxProfile profile) {

        WebDriver driver = new FirefoxDriver(profile);

        // this.browser=driver;

        if (this.browser != null)
            return this.browser;
        if (this.runningConfiguration == null) {
            this.runningConfiguration = RunningConfiguration_New.defaultFFOnSP();
            logFailureCheckpoint(
                    "Running configuration",
                    "USING DEFAULT CONFIGURATION!!!! MUST BE DISABLED FOR REGULAR RUNS.",
                    "USING DEFAULT CONFIGURATION!!!! MUST BE DISABLED FOR REGULAR RUNS.");
        }
        try {
            this.browser = driver;
            // utils.getBrowser(getRunningConfiguration());
            logSuccessCheckpoint(
                    "Open browser",
                    "Browser is opened successfully");

        } catch (Exception ex) {
            logFailureCheckpoint(
                    "Open browser",
                    "Browser is opened",
                    "Error occured while opening browser." + ex.getMessage());
            return null;
        }
        return this.browser;
    }

    /**
     * Get the machine (server) to test on.
     *
     * @return Machine to test on. Catches any exceptions and logs them to a
     * step.
     */
    public String getMachine() {
        try {
            return getRunningConfiguration().getMachine();

        } catch (Exception ex) {
            logFailureCheckpoint(
                    "Trying to get machine",
                    "Machine was returned",
                    "Error occured. " + ex.getMessage());
            return null;
        }
    }

    /**
     * Navigates to the specified URL (GET). Catches all exceptions and logs
     * them to a step.
     *
     * @param url Navigation target.
     */
    public void navigate(String url) {

        try {
            WebDriver wd = getBrowser();
            String mainWindowHandle = wd.getWindowHandle();
            getBrowser().get(url);
            if (this.getRunningConfiguration().getBrowser().equals("IE")) {
                getBrowser().get("javascript:document.getElementById('overridelink').click();");
            }
            getBrowser().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            logSuccessCheckpoint(
                    "Trying to navigate to " + url,
                    "Target location was opened successfully.");
            try {
                Thread.sleep(2000);

            } catch (Exception e) {
            }
            wd.switchTo().window(mainWindowHandle); // assure we get back to the
            // window
            // out of which the navigate call has been made.
        } catch (Exception ex) {
            logFailureCheckpoint(
                    "Trying to navigate to " + url,
                    "Target site was opened.",
                    "Error occured while navigating. " + ex.getMessage());
        }
    }

    /**
     * Tries to localize the WebElement using the specified XPath.
     *
     * @param xPath To search for.
     * @return UNIQUE WebElement identified by the specified XPathXPath.
     */
    public WebElement getByXPath(String xPath) {
        try {
            return getBrowser().findElement(By.xpath(xPath));
        } catch (Exception ex) {
            logFailureCheckpoint(
                    "Getting element by xPath " + xPath,
                    "Element should be identified.",
                    "Error occured while getting element. " + ex.getMessage());
            return null;
        }
    }

    /**
     * Tries to localize a list of WebElements using the specified XPath.
     *
     * @param xPath To search for.
     * @return List of WebElements identified by the specified XPathXPath.
     */
    public List<WebElement> getListByXPath(String xPath) {
        try {
            return getBrowser().findElements(By.xpath(xPath));
        } catch (Exception ex) {
            logFailureCheckpoint(
                    "Getting element by xPath " + xPath,
                    "Element should be identified.",
                    "Error occured while getting element. " + ex.getMessage());
            return null;
        }
    }

    /**
     * Closes the browser and catches eventual exceptions. It appends an
     * appropriate step to the report.
     */
    public void closeBrowser() {
        try {
            if (this.browser != null) {
                browser.close();
                browser.quit();
                this.browser = null;
                logSuccessCheckpoint(
                        "Closing browser",
                        "Browser successfully closed.");
            }

        } catch (Exception ex) {
            logFailureCheckpoint(
                    "Closing browser",
                    "Browser was closed",
                    "Error occured while closing browser. " + ex.getMessage());
        }
    }

    /**
     * * Aborts the running test: closes the Browser, finalizes the report,
     * loggs a new error and returns the finalized report.
     *
     * @param cause The reason for the abort.
     * @return Finalized Report.
     */
    public Report abortTest(String cause) {
        closeBrowser(); // closes the browser and catches any errors
        logFailureCheckpoint(
                "Test wird abgebrochen",
                "Ursache: " + cause,
                "");
        Report report = finalizeReport(this.getClass().getSimpleName());
        return report;
    }

/*	public static void printReport(Report rep) {
		if (rep == null)
			return;
		Report printReport = (Report) rep;
		// get report data
		String testName = (printReport.getTestName() == null) ? "" : printReport.getTestName();
		String startTime = (printReport.getStartTime() == null) ? "" : printReport
				.getStartTime();
		String endTime = (printReport.getEndTime() == null) ? "" : printReport.getEndTime();
		int duration = ((Integer) printReport.getDuration() == null) ? 0 : printReport
				.getDuration();
		String executionDate = (printReport.getExecutionDate() == null) ? "" : printReport
				.getExecutionDate();
		Vector<Step> steps = rep.getSteps();

		log.info("Report for test " + testName);
		log.info("\tExecution date: " + executionDate);
		log.info("\tStarted at: " + startTime);
		log.info("\tEnded at: " + endTime);
		log.info("\tDuration: " + duration);
		log.info("\tSteps: ");

		Step step = null;
		for (int i = 0; i < steps.size(); i++) {
			step = steps.elementAt(i);
			log.info("\t\t" + step.getStepName());
			if (step.getStatus()) {
				log.info("\t\t+++++  STEP PASSED  +++++");
			} else {
				log.info("\t\t-----  STEP FAILED  -----");
			}
			log.info("\t\t   Expected: " + step.getExpected());
			log.info("\t\t   Actual: " + step.getActual());
		}

	}*/

    public String getNavigation(String urlID) {
        String machine = getMachine(); // gets the machine; logs any errors
        String url = URL.getProperty(urlID); // logs any errors
        return machine + "/" + url;
    }

    /**
     * Finalizes the report by adding the steps, setting the test name and
     * computing the entire test case status.
     *
     * @param testName The test name.
     * @return Finalized report.
     */
    public Report finalizeReport(String testName) {
        return getReporter().finalizeReport(testName);
    }

    /**
     * Takes a link description as xPath and tries to click it. Catches all
     * encountered exceptions and logs them to report steps.
     *
     * @param xPath XPath identifying the link
     * @return True if the identified item is a link that could be clicked,
     * false otherwise.
     */
    public boolean clickLink(String xPath) {
        WebDriver wd = getBrowser();
        String wh = wd.getWindowHandle();
        WebElement link = getByXPath(xPath); // exceptions will be catched
        if (link == null) {
            return false;
        }
        if (link != null
                && !link.getTagName().toLowerCase()
                .equalsIgnoreCase(TAG_LINK.toLowerCase())) {
            // wrong tag
            logFailureCheckpoint(
                    "Click link method",
                    "Supplied xPath must identify a link (html tag \"a\")",
                    "Supplied xPath doesn't identify a link. Identified element's tag is: " + link.getTagName().toLowerCase()
            );
            return false;
        }
        // click the link
        logSuccessCheckpoint(
                "Click link method",
                "Supplied xPath identifies a link. Link has been clicked");
        // link.click(); // doesn't always work in ie
        link.sendKeys("\n");
        try {
            Thread.sleep(2000);

        } catch (Exception e) {
        }
        wd.switchTo().window(wh);
        return true;
    }

    /**
     * Takes a link description as xPath and tries to click it. Click should
     * cause a popup to open. Method gets a reference (WebElement object) to
     * this new popup window. Catches all encountered exceptions and logs them
     * to report steps.
     *
     * @param xPath XPath identifying the link
     * @return A reference to the popup window. If errors occur the method will
     * return null.
     */
    public WebDriver clickLinkAndSwitchToPopup(String linkXPath) {
        // get all window before clicking link
        List<String> windows = new ArrayList<String>(getBrowser().getWindowHandles());

        // click the link which should launch a popup window
        if (!clickLink(linkXPath))
            return null;

        // check for new handles
        List<String> windowsNew = new ArrayList<String>(getBrowser().getWindowHandles());
        Iterator<String> it = windowsNew.iterator();
        String item = null;
        WebDriver newBrowser = null;
        while (it.hasNext()) {
            if (!windows.contains((item = it.next()))) {
                // we found new handle
                try {
                    newBrowser = getBrowser().switchTo().window(item);
                    logSuccessCheckpoint(
                            "Get reference on popup window",
                            "Popup window opened and the WebDriver could be switched.");
                    return newBrowser;

                } catch (Exception ex) {
                    logFailureCheckpoint(
                            "Get reference on popup window",
                            "Popup window opened and the WebDriver could be switched.",
                            "Error occured: " + ex.getMessage());
                    return null;
                }
            }
        }
        return null; // there isn't a new window which can be referenced
    }

    /**
     * Reads the logfile under the specified URL to a list where each entry in
     * the file results to a list entry. This method uses the default
     * credentials (username and password) and the default url for log files
     * provided by the TestingUtil class.
     *
     * @param logFileName
     *            The name of the desired log file.
     * @return List containing all entries in the log file.
     */
	/*public List<String> readLogFile(String logFileName) {
		String url = getURLForName(LOG_FILES_URL).trim();

		url += logFileName;
		String username = getUtils().getDefaultUsername();
		String password = getUtils().getDefaultPassword();

		return readLogFile(url, username, password);
	}*/

    /**
     * Reads the logfile under the specified URL to a list where each entry in
     * the file results to a list entry.
     *
     * @param url
     * @param username If required, null otherwise
     * @param password If required, null otherwise
     * @return List containing all entries in the log file.
     */
    public List<String> readLogFile(String url, String username, String password) {

        // 1. Get the URL
        if (url == null || url.length() == 0)
            return null;
        String http = "http://";
        String https = "https://";
        String prefix = null;
        String baseUrl = null;
        String finalUrl = null;

        HtmlUnitDriver drv = getUnitDriverWithCredentials(username, password);

        // FirefoxDriver drv = new FirefoxDriver();

        int httpPos = 0;

        if ((httpPos = url.indexOf(http)) >= 0) {
            httpPos += http.length();
            baseUrl = url.substring(httpPos);
            prefix = http;
        } else if ((httpPos = url.indexOf(https)) >= 0) {
            httpPos += https.length();
            baseUrl = url.substring(httpPos);
            prefix = https;
        } else {
            prefix = "";
            baseUrl = url;
        }

        if (username != null && password != null) {
            finalUrl = prefix + username + ":" + password + "@" + baseUrl;
        } else {
            finalUrl = url;
        }

        // 2. get the file
        drv.get(finalUrl);
        String[] entries = drv.getPageSource().split("\n");
        drv.close();
        if (entries == null)
            return null;
        return Arrays.asList(entries);
    }

    /**
     * For authentication purposes on servers using HTTP Basic Authentication it
     * is necessary to specify the login data within the URL. This method
     * returns a browser independent HtmlUnitDriver with the necessary
     * DefaultCredentialProvider with the specified username and password. !!
     * UNSAFE USAGE OF THE DATA PROVIDED !!
     *
     * @param username
     * @param password
     * @return Browser independent WebDriver: HtmlUnitDriver
     */
    public HtmlUnitDriver getUnitDriverWithCredentials(final String username,
                                                       final String password) {
        HtmlUnitDriver driver = new HtmlUnitDriver() {
            protected WebClient modifyWebClient(WebClient client) {
                // This class ships with HtmlUnit itself
                DefaultCredentialsProvider creds = new DefaultCredentialsProvider();

                // initialize credentials
                creds.addCredentials(username, password);

                // And now add the provider to the webClient instance
                client.setCredentialsProvider(creds);

                return client;
            }
        };
        return driver;
    }

    /**
     * Finds the parent element to a given element and returns it.
     *
     * @param el Element whose parent is to search.
     * @return The parent element or null if el has no parent (is itself the top
     * level element of the DOM)
     */
    public WebElement getParent(WebElement el) {
        try {
            return el.findElement(By.xpath(".."));
        } catch (Exception e) {
            return null;
        }
    }

    public Date getServerTime(String machine) throws ParseException,
            IOException {

        URL url = new URL(machine);
        URLConnection conn = url.openConnection();
        String dateString = conn.getHeaderField("Date");
        SimpleDateFormat sdf = new SimpleDateFormat(
                "EEE', 'dd MMM yyyy HH:mm:ss z", Locale.US);
        return sdf.parse(dateString);
    }

    // /**
    // * Retrieves the date for the server described by the specified string.
    // *
    // * @param server
    // * The requested server (like: http://cmsdevbl10.ada.t-online.de)
    // * @return The current server time and date.
    // */
    // public Date getServerTime(String server) {
    // String dev05 =
    // "http://cmsdevbl05.ada.t-online.de:8040/sitemaps/date.shtml";
    // String dev10 = "http://cmsdevbl10.ada.t-online.de:85/date.shtml";
    //
    // String dev05_port8021 =
    // "http://cmsdevbl05.ada.t-online.de:8021/toiPortal/servlet";
    // if (server == null || server.length() == 0)
    // return null;
    // HtmlUnitDriver ud = new HtmlUnitDriver();
    // if (Pattern.matches(".*" + server + ".*", dev05)) {
    // ud.get(dev05);
    // return parseDateOn05(ud);
    // } else if (Pattern.matches(".*" + server + ".*", dev10)) {
    // ud.get(dev10);
    // //System.out.println("server: " +server);
    // return parseDateOn10(ud);
    // }
    // else if (Pattern.matches(".*" + server + ".*", dev05_port8021)) {
    // ud.get(dev10);
    // //System.out.println("server: " +server);
    // return parseDateOn10(ud);
    // }else {
    // System.err.println("Getting Srever time: Could not identify url "
    // + server);
    // return null;
    // }
    // }

	/*private Date parseDateOn05(HtmlUnitDriver ud) {
		
		return null;
	}*/

	/*private Date parseDateOn10(HtmlUnitDriver ud) {
		try {
			WebElement body = ud.findElement(By.xpath("html/body"));
			String dateString = body.getText().trim();
			SimpleDateFormat sdf = new SimpleDateFormat(
					"EEEE', 'dd-MMM-yyyy HH:mm:ss z", Locale.US);
			return sdf.parse(dateString);
		} catch (Exception ex) {
			System.err.println("Error parsing date on devbl 10");
			ex.printStackTrace();
			return null;
		}
	}*/

    // ------------ CHECKPOINTS ----------------------------------

    /**
     * Trys to obtain the webelement's text and compares it with the expected
     * text.
     *
     * @param webel         Webelement to compare.
     * @param expectedText  Expected value for the webelement's text.
     * @param chekPointName A name for the checkpoint used for logging.
     * @return True if webelement's text corresponds to the expected value,
     * false otherwise.
     */
    @SuppressWarnings("unused")
    public boolean checkEqual(WebElement webel, String expectedText,
                              String chekPointName) {
        String weText = null;

        try {
            weText = webel.getText().trim();
            if (webel == null)
                throw new Exception("Konnte Webelement nicht bestimmen (NULL)");
        } catch (Exception ex) {
            logFailureCheckpoint(
                    "Checkpoint " + chekPointName,
                    expectedText,
                    "Error occured while getting webelement's text property. " + ex.getMessage());
            return false;
        }
        // we have the strings; compare
        if (weText != null && expectedText != null && expectedText.length() > 0
                && weText.equalsIgnoreCase(expectedText)) {
            logSuccessCheckpoint(
                    "Checkpoint " + chekPointName,
                    expectedText);
            return true;
        } else {
            logFailureCheckpoint(
                    chekPointName,
                    expectedText,
                    weText);
            return false;
        }
    }

    /**
     * Compaires the texts of the WebElements from the list to the expected text
     * If at least one match is found the test is successful.
     *
     * @param elements      List of WebElements
     * @param expectedText  Expected value for the webelement's text.
     * @param chekPointName A name for the checkpoint used for logging.
     * @return True if at least one webelement's text corresponds to the expected value,
     * false otherwise.
     */
    public boolean checkEquals(List<WebElement> elements, String equalString, String checkPoint) {
        boolean result = false;
        for (WebElement element : elements) {
            if (element.getText().equals(equalString)) {
                logSuccessCheckpoint(
                        checkPoint,
                        "The text of at least on element equals to the expected text " + equalString);
                result = true;
                break;
            }
        }
        if (result == false) {
            logFailureCheckpoint(
                    checkPoint,
                    "The text of at least on element equals to the expected text " + equalString,
                    "No text equals to the expected text");
        }
        return result;
    }

    /**
     * Trys to obtain a specific CSS value of a WebElement and compaires it with the expected value.
     *
     * @param webel          Webelement to compare.
     * @param expectedCSS    Expected value for the webelement's CSS.
     * @param CSSType        Type of the CSS
     * @param checkPointName A name for the checkpoint used for logging.
     * @return True if webelement's CSS corresponds to the expected value,
     * false otherwise.
     */
    public boolean checkCSS(WebElement webel, String CSSType, String expectedCSS, String checkPointName) {

        try {
            String actualCSS = webel.getCssValue(CSSType).replaceAll(" ", "");
            expectedCSS = expectedCSS.replaceAll(" ", "");
            if (expectedCSS.equals("400")) {
                expectedCSS = "normal";
            }
            if (expectedCSS.equals("700")) {
                expectedCSS = "bold";
            }
            if (actualCSS.equals("400")) {
                actualCSS = "normal";
            }
            if (actualCSS.equals("700")) {
                actualCSS = "bold";
            }
            if (actualCSS.equals(expectedCSS)) {
                logSuccessCheckpoint(
                        checkPointName,
                        "Actual CSS value (" + actualCSS + ") equals the expected value (" + expectedCSS + ")");
                return true;
            } else {
                logFailureCheckpoint(
                        checkPointName,
                        "Actual CSS value equals the expected value ",
                        "Actual CSS value (" + actualCSS + ") does not equal the expected value (" + expectedCSS + ")");
                return false;
            }
        } catch (Exception ex) {
            logFailureCheckpoint(
                    "Checkpoint " + checkPointName,
                    expectedCSS,
                    "Error occured while getting webelement's text property. " + ex.getMessage());
            return false;
        }
    }

    /**
     * @param expectedString First String to compare.
     * @param actualString   Second String to compare.
     * @param checkPointName A name for the checkpoint used for logging.
     * @return True if text corresponds to the expected value, false otherwise
     */

    public boolean checkEqualStrings(String expectedString,
                                     String actualString, String checkPointName) {

        if (expectedString.equalsIgnoreCase(actualString)) {
            logSuccessCheckpoint(checkPointName, "The strings are equal: "
                    + expectedString);
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "The strings are equal.",
                    "Specified strings are not equal. Expected: " + expectedString + "-> Actual: " + actualString);
            return false;
        }
    }

    public boolean checkIsPartOfString(String subjectString, String subString,
                                       String checkPointName) {

        if (subjectString.contains(subString)) {
            logSuccessCheckpoint(
                    checkPointName,
                    "The subject string " + subjectString + " contains the substring " + subString + ".");
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "The subject string " + subjectString + " should contain the substring " + subString + ".",
                    "The subject string " + subjectString + " DOES NOT contain the substring " + subString + ".");
            return false;
        }

    }

    /**
     * The picture size are compared.
     *
     * @param webel            Size of picture to compare
     * @param DefaultDimension Expected value for the size of picture.
     * @param chekPointName    A name for the checkpoint used for logging.
     * @return True if size of picture not corresponds to the expected value,
     * false otherwise
     */
    public boolean isScaled(WebElement webel, Dimension DefaultDimension,
                            String chekPointName) {
        Dimension weDimension = null;
        try {
            weDimension = webel.getSize();

        } catch (Exception ex) {
            logFailureCheckpoint(
                    "Checkpoint " + chekPointName,
                    DefaultDimension.toString(),
                    "Error occured while getting webelement's Size property. " + ex.getMessage());
            return false;
        }
        // we have the strings; compare
        if (weDimension != null
                && DefaultDimension != null
                && !weDimension.toString().equalsIgnoreCase(
                DefaultDimension.toString())) {

            logSuccessCheckpoint(
                    chekPointName,
                    "Bild ist skaliert vom " + DefaultDimension.toString() + " auf " + weDimension.toString());
            return true;
        } else {
            logFailureCheckpoint(
                    chekPointName,
                    "Bild wird skaliert Dimension ungleich: " + DefaultDimension.toString(),
                    "Bild wurde nicht skaliert, Actual Dimension: " + weDimension.toString());
            return false;
        }
    }

    /**
     * Check if Image exist or not.
     *
     * @param img            Image to check
     * @param checkPointName Checkpoint name
     * @return True if element does not exist, false otherwise
     */
    public boolean imgIsNOTDisplayed(WebElement img, String checkPointName) {

        try {

            // Internet Exploerer zeigt die nicht vorhandene Bilder mit
            // Platzhalter
            // Unter FF funktioniert die isDisplayed() -Funktion richtig.
            // Es ist noch nicht optimal aber es funktioniert.
            String fileSizeAsString = img.getAttribute("fileSize");
            int fileSize;
            //	System.out.println("Filesize: " + fileSizeAsString);

            if (runningConfiguration.getBrowser().equals("FF")) {
                // Für FF
                if (!img.isDisplayed()) {
                    logSuccessCheckpoint(
                            checkPointName,
                            "Image does not exists.");
                    return true;
                } else {
                    logFailureCheckpoint(
                            checkPointName,
                            "Image does not exists.",
                            "Image is exist");
                    return false;
                }
            } else {

                if (fileSizeAsString == null)
                    fileSize = 0;
                else
                    fileSize = Integer.valueOf(fileSizeAsString);

                if (fileSize <= 0) {
                    logSuccessCheckpoint(
                            checkPointName,
                            "Image does not exists.");
                    return true;
                } else {
                    logFailureCheckpoint(
                            checkPointName,
                            "Image does not exists.", "Image is exist");
                    return false;
                }
            }

        } catch (NumberFormatException e) {

            // Für FF
            if (!img.isDisplayed()) {
                logSuccessCheckpoint(
                        checkPointName,
                        "Image does not exists.");
                return true;
            } else {
                logFailureCheckpoint(
                        checkPointName,
                        "Image does not exists.",
                        "Image is exist");
                return false;
            }

        }

    }

    /**
     * Check if Image exist or not.
     *
     * @param img            Image to check
     * @param checkPointName Checkpoint name
     * @return True if element exist, false otherwise
     */
    public boolean imgIsDisplayed(WebElement img, String checkPointName) {
        if (img == null) {
            logFailureCheckpoint(
                    checkPointName,
                    "Image exists.",
                    "Image does not exists (provided WebElement is null)");
            return false;
        }
        if (img.isDisplayed()) {
            logSuccessCheckpoint(
                    checkPointName,
                    "Image exists.");
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "Image exists.",
                    "Image does not exists (is null)");
            return false;
        }
    }

    /**
     * Checks if element exists or not.
     *
     * @param element        Element to check.
     * @param checkPointName Checkpoint name.
     * @return True if element exists false otherwise.
     */
    public boolean checkExists(WebElement element, String checkPointName) {
        // System.out.println(element.getText());
        if (element != null) {
            logSuccessCheckpoint(
                    checkPointName,
                    "Webelement exists.");
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "Webelement exists.",
                    "Webelement does not exist (is null).");
            return false;
        }
    }

    /**
     * Compares two integers and logs the result.
     *
     * @param baseValue      The base value to compare.
     * @param compareValue   The second value to compare.
     * @param direction      Direction lower zero -> expected that baseValue < compareValue
     *                       Direction bigger zero -> expected that baseValue >
     *                       compareValue Direction equal zero -> expected that baseValue
     *                       == compareValue (just like checkEqual(int, int, String)
     * @param checkPointName The name of the checkpoint for logging purposes
     */
    public void checkCompareInteger(int baseValue, int compareValue,
                                    int direction, String checkPointName) {
        if (direction == 0) {
            checkEqual(baseValue, compareValue, checkPointName);
            return;
        }
        if (direction < 0) {
            // expected that baseValue < compareValue
            if (baseValue < compareValue) {
                logSuccessCheckpoint(
                        checkPointName,
                        "Base value is lower than the compare value.");
            } else {
                logFailureCheckpoint(
                        checkPointName,
                        "Base value is lower than the compare value.",
                        "Base value is NOT lower than the compare value.");
            }
            return;
        }
        if (direction > 0) {
            // expected that baseValue > compareValue
            // expected that baseValue < compareValue
            if (baseValue > compareValue) {
                logSuccessCheckpoint(
                        checkPointName,
                        "Base value is bigger than the compare value.");
            } else {
                logFailureCheckpoint(
                        checkPointName,
                        "Base value is bigger than the compare value.",
                        "Base value is NOT bigger than the compare value.");
            }
            return;
        }
    }

    /**
     * Checks wether the specified string to search is PART (substring) of one
     * of the elements in the list.
     *
     * @param theList        The list of strings to search in.
     * @param toSearch       The string to search for.
     * @param shouldExist    Is the string supposed to be found or not.
     * @param checkPointName Checkpoint name.
     * @return
     */
    public boolean checkMatchesInList(List<String> theList, String toSearch,
                                      boolean shouldExist, String checkPointName) {
        boolean found = false;
        for (String inList : theList) {
            if (inList.contains(toSearch)) {
                found = true;
                break;
            }
        }
        boolean success = found && shouldExist;
        String shouldMaybeExist = null;
        if (shouldExist) {
            shouldMaybeExist = " to be ";
        } else {
            shouldMaybeExist = " NOT to be ";
        }
        if (!success) {
            logFailureCheckpoint(
                    checkPointName,
                    "Searched for " + toSearch + " in the specified list and expected it " + shouldMaybeExist + " in the list.",
                    "Check failed.");
            return false;
        } else {
            logSuccessCheckpoint(
                    checkPointName,
                    "Searched for " + toSearch + " in the specified list and expected it " + shouldMaybeExist + " in the list.");
            return true;
        }
    }

    public boolean checkIsTrue(boolean subject, String subjectName,
                               String checkPointName) {
        if (subject) {
            logSuccessCheckpoint(
                    checkPointName,
                    subjectName
                            + " has boolean value TRUE.");
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    subjectName + " must have boolean value TRUE.",
                    subjectName + " has boolean value FALSE.");
            return false;
        }
    }

    /**
     * @param element        Element to check.
     * @param expected       expected logoutput
     * @param actual_failure failure logoutput
     */
    public void Logoutput_element_is_not_displayed(WebElement element,
                                                   String expected, String actual_failure) {
        try {
            if (element.isDisplayed() == false) {
                logSuccessCheckpoint(
                        "Element is not displayed",
                        expected);

            } else {
                logFailureCheckpoint(
                        "Element is displayed",
                        expected,
                        actual_failure);
            }
        } catch (Exception e) {
            logFailureCheckpoint(
                    "Is a element visible ?",
                    "Element should NOT be identified.",
                    "Error occured: " + e.getStackTrace().toString());
        }
    }

    /**
     * @param element        Element to check.
     * @param expected       expected logoutput
     * @param actual_failure failure logoutput
     */

    public void Logoutput_element_is_displayed(WebElement element, String expected, String actual_failure) {
        try {
            if (element.isDisplayed() == true) {
                logSuccessCheckpoint(
                        "Element is displayed",
                        expected);

            } else {
                logFailureCheckpoint(
                        "Element is not displayed",
                        expected,
                        actual_failure);
            }
        } catch (Exception e) {
            logFailureCheckpoint(
                    "Is a element visible ?",
                    "Element should NOT be identified.",
                    "Error occured: " + e.getStackTrace().toString());
        }

    }

    /**
     * @param element1       is first element
     * @param element2       is the second element
     * @param element3       is the third element in sequence
     * @param for            Example: on the same row the tabs: tab1,tab2,tab3 are
     *                       sequentiel listed
     * @param
     * @param checkPointName Checkpoint name.
     * @return boolean
     */
    public boolean checkpoint_three_ElementsInSequence(WebElement element1,
                                                       WebElement element2, WebElement element3, String checkPointName) {

        // String elementName1, elementName2, elementName3;
        // elementName1 = elementName2 = elementName3 = "";

        Point location1, location2, location3;

        location1 = location2 = location3 = null;

        // elementName1 = element1.getText();
        // elementName2 = element2.getText();
        // elementName3 = element3.getText();

        location1 = element1.getLocation();
        location2 = element2.getLocation();
        location3 = element3.getLocation();

        if ((location1.getX() < location2.getX())
                && (location2.getX() < location3.getX())
                && (location1.getY() == location2.getY())
                && (location2.getY() == location3.getY())) {
            logSuccessCheckpoint(
                    checkPointName,
                    "Elements are in the right sequence" + '\n' + "Element "
                            + element1.getText() + " is first Element" + '\n'
                            + "Element " + element2.getText()
                            + " is second Element" + '\n' + "Element "
                            + element3.getText() + " is third Element");
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "Elements are in the right sequence.",
                    "Elements are not in the right sequence" + '\n'
                            + "Element " + element1.getText()
                            + " is first Element" + '\n' + "Element "
                            + element2.getText() + " is second Element" + '\n'
                            + "Element " + element3.getText()
                            + " is third Element");
            return false;
        }

    }

    /**
     * @param element1       is first element
     * @param element2       is the second element
     * @param for            Example: on the same row the tabs: tab1,tab2 are sequentiel
     *                       listed
     * @param
     * @param checkPointName Checkpoint name.
     * @return boolean
     */
    public boolean checkpoint_two_ElementsInSequence(WebElement element1,
                                                     WebElement element2, String checkPointName) {

        // String elementName1, elementName2;
        // elementName1 = elementName2 = "";

        Point location1, location2;
        int location1_y = 0;
        location1 = location2 = null;

        // elementName1 = element1.getText();
        // elementName2 = element2.getText();

        try {
            location1 = element1.getLocation();
            location2 = element2.getLocation();
            location1_y = location1.getY(); // Toleranz 1px auf y-Koordinate
        } catch (Exception e) {
            logFailureCheckpoint(
                    checkPointName,
                    "Elements are in the right sequence",
                    "An Error occured. " + e.getMessage());
            return false;
        }

        // System.out.println("Element1: "+location1);
        // System.out.println("Element2: "+location2);
        // System.out.println("location1_y: "+location1_y);

        if ((location1.getX() < location2.getX()) // y-Koordinate Tolleranz +2px
                && (location2.getY() == location1_y
                || location2.getY() == location1_y + 1 || location2
                .getY() == location1_y + 2)) {
            logSuccessCheckpoint(
                    checkPointName,
                    "Elements are in the right sequence" + '\n' + "Element "
                            + element1.getText() + " is first Element" + '\n'
                            + "Element " + element2.getText()
                            + " is second Element");
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "Elements are in the right sequence.",
                    "Elements are not in the right sequence" + '\n'
                            + "Element " + element1.getText()
                            + " is first Element" + '\n' + "Element "
                            + element2.getText() + " is second Element");
            return false;
        }

    }

    /**
     * @param aktualday_from_3day_forecast
     * @param element2                     is next tab aktualday+1
     * @param element3                     is next tab aktualday+2
     * @param checkPointName               Checkpoint name.
     * @return boolean
     */
    public boolean checkpoint_aktualDay_AndnextTwoDays(
            WebElement aktualday_from_3day_forecast, WebElement element2,
            WebElement element3, String checkPointName) {

        String aktualDay, nextday_plus_one, nextday_plus_two, tmp_day1, tmp_day2, tmp_day3;

        ArrayList<Object> days = new ArrayList<Object>();

        days.add(0, "Mo");
        days.add(1, "Di");
        days.add(2, "Mi");
        days.add(3, "Do");
        days.add(4, "Fr");
        days.add(5, "Sa");
        days.add(6, "So");

        aktualDay = aktualday_from_3day_forecast.getText(); // for Element
        // aktualday_from_3day_forecast
        // is the Label
        // "actual day" of
        // the
        // "3-Tage-Vorhersage"
        // is required for
        // Example
        // "Di. 06.12.:"
        nextday_plus_one = element2.getText();
        nextday_plus_two = element3.getText();

        tmp_day1 = aktualDay.substring(0, 2);
        tmp_day2 = nextday_plus_one.substring(0, 2);
        tmp_day3 = nextday_plus_two.substring(0, 2);

        if ((((days.indexOf(tmp_day1) + 1) % 7) == days.indexOf(tmp_day2))
                && (((days.indexOf(tmp_day1) + 2) % 7) == days
                .indexOf(tmp_day3))) {

            logSuccessCheckpoint(
                    checkPointName,
                    "Elements are in the right sequence" + '\n' + "Element "
                            + tmp_day1 + " is first Element" + '\n'
                            + "Element " + tmp_day2 + " is second Element"
                            + '\n' + "Element " + tmp_day3
                            + " is third Element");
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "Elements are in the right sequence.",
                    "Elements are not in the right sequence" + '\n'
                            + "Element "
                            + aktualday_from_3day_forecast.getText()
                            + " is first Element" + '\n' + "Element "
                            + element2.getText() + " is second Element" + '\n'
                            + "Element " + element3.getText()
                            + " is third Element");
            return false;
        }
    }

    /**
     * @param aktualday_from_3day_forecast the actual day where you begin to start
     * @param navigated_day                the day to navigate to
     * @param naviSteps                    are the steps for forward or backward navigation
     * @param checkPointName               Checkpoint name.
     * @return boolean
     */

    public boolean checkpoint_aktualDay_And_navigate_forward_or_backward(
            WebElement aktualday_from_3day_forecast, WebElement navigated_day,
            int naviSteps, String checkPointName) {

        String aktualDay, nextday;

        ArrayList<Object> days = new ArrayList<Object>();

        days.add(0, "Mo");
        days.add(1, "Di");
        days.add(2, "Mi");
        days.add(3, "Do");
        days.add(4, "Fr");
        days.add(5, "Sa");
        days.add(6, "So");

        aktualDay = aktualday_from_3day_forecast.getText(); // for Element
        // aktualday_from_3day_forecast
        // is the Label
        // "actual day" of
        // the
        // "3-Tage-Vorhersage"
        // is required for
        // Example
        // "Di. 06.12.:"
        nextday = navigated_day.getText();

        // System.out.println("nextday: "+nextday);

        aktualDay = aktualDay.substring(0, 2);
        nextday = nextday.substring(0, 2);

        if ((((days.indexOf(aktualDay) + 2 + naviSteps) % 7) == days
                .indexOf(nextday))) {

            // System.out.println("nextday_index "+((days.indexOf(aktualDay) + 2
            // + naviSteps) % 7));
            logSuccessCheckpoint(
                    checkPointName,
                    "Elements are in the right sequence"
                            + '\n'
                            + "Element "
                            + aktualDay
                            + " is aktual day"
                            + '\n'
                            + "Element "
                            + days.get((days.indexOf(aktualDay) + 2 + naviSteps) % 7)
                            + " is " + naviSteps + " klicks in the future");
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "Elements are in the right sequence.",
                    "Elements are not in the right sequence" + '\n'
                            + "Element " + aktualDay + " is aktual day" + '\n'
                            + "Element " + days.get(days.indexOf(nextday))
                            + " is " + naviSteps + " klicks in the future");

            return false;
        }
    }

    /**
     * Checks if the expected text is included in the page's source code.
     *
     * @param expectedText   Text to search for.
     * @param checkPointName Name of the checkpoint (for logging purposes).
     * @return True if the expected text could be found within the source code,
     * false otherwise.
     */
    public boolean checkWithinSourceCode(String expectedText,
                                         String checkPointName) {
        String source = getBrowser().getPageSource();
        int lio = source.lastIndexOf(expectedText);
        if (lio > 0) {
            logSuccessCheckpoint(checkPointName, expectedText);
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    expectedText,
                    "String '" + expectedText + "' could not be found in the Source Code of the page.");
            return false;
        }

    }

    /**
     * Checks if the expected text is NOT included in the page's source code.
     *
     * @param expectedText   Text to search for.
     * @param checkPointName Name of the checkpoint (for logging purposes).
     * @return True if the expected text could NOT be found within the source
     * code, false otherwise.
     */
    public boolean checkNOTWithinSourceCode(String expectedText,
                                            String checkPointName) {
        String source = getBrowser().getPageSource();
        int lio = source.lastIndexOf(expectedText);
        if (lio < 0) {
            logSuccessCheckpoint(
                    checkPointName,
                    expectedText);
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    expectedText,
                    "String '"
                            + expectedText
                            + "' has unexpectedly been found in the Source Code of the page.");
            return false;
        }

    }

    /**
     * Tries to identify the element by the specified xPath.
     *
     * @param xPath          XPath to the element.
     * @param checkPointName Name of the checkpoint (for logging purposes).
     * @return True if the element is NOT identified on the page, false
     * otherwise.
     */
    public boolean checkDoesNOTExist(String xPath, String checkPointName) {
        @SuppressWarnings("unused")
        WebElement el = null;
        try {
            el = getBrowser().findElement(By.xpath(xPath));
        } catch (NoSuchElementException nsee) {
            // that's what we expected
            logSuccessCheckpoint(
                    checkPointName,
                    "Element should not be NOT identified.");
            return true;

        } catch (Exception ex) {
            logFailureCheckpoint(
                    checkPointName,
                    "Element should NOT be identified.",
                    "Error occured: " + ex.getMessage());
            return false;
        }
        // element has unexpectedly been identified
        logFailureCheckpoint(
                checkPointName,
                "Element should NOT be identified.",
                "Element has unexpectedly been identified on the page!!!");
        return false;
    }

    public boolean checkRegExp(String patternString, String stringToSearchIn,
                               boolean positiveSearch, String checkPointName) {
        try {
            if (Pattern.matches(patternString, stringToSearchIn)) {
                if (positiveSearch) {
                    // searched and found
                    logSuccessCheckpoint(
                            checkPointName,
                            patternString + " matched " + stringToSearchIn);
                    return true;
                } else {
                    // unexpectedly found
                    logFailureCheckpoint(
                            checkPointName,
                            "Pattern " + patternString + " should not match " + stringToSearchIn + ".",
                            "Pattern " + patternString + " unexpectedly matches " + stringToSearchIn + ".");
                    return false;
                }
            } else { // no match
                if (positiveSearch) {
                    // searched and NOT found
                    logFailureCheckpoint(checkPointName,
                            "Pattern " + patternString + " should match " + stringToSearchIn + ".",
                            "Pattern " + patternString + " doesn't match " + stringToSearchIn + ".");
                    return false;
                } else {
                    // not found as expected
                    logSuccessCheckpoint(
                            checkPointName,
                            patternString + " doesn't match " + stringToSearchIn);
                    return true;
                }
            }
        } catch (Exception ex) {
            logFailureCheckpoint(
                    checkPointName,
                    "Trying to compile RegExp Pattern.",
                    "Error occured: " + ex.getMessage());
            return false;
        }
    }

    /**
     * @param actualValue    First value to compare.
     * @param expectedValue  Second value to compare.
     * @param checkPointName A name for the checkpoint used for logging.
     * @return True if the two values are equal, false otherwise.
     */

    public boolean checkEqual(int actualValue, int expectedValue,
                              String checkPointName) {

        if (expectedValue == actualValue) {
            logSuccessCheckpoint(
                    checkPointName,
                    "Compared integer values are equal. Both values are " + actualValue);
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "Compared integer values are equal.",
                    "Compared integer values are NOT equal. Expected: " + expectedValue + "; Actual Value " + actualValue);
            return false;
        }
    }

    public boolean checkIsClickable(String xPath, boolean positive,
                                    String checkPointName) {
        WebElement el = getByXPath(xPath);
        if (el == null)
            return false;
        WebElement parent = null;
        while ((parent = getParent(el)) != null) {
            try {
                if (parent.getTagName().equalsIgnoreCase(TAG_LINK)) {
                    // it's a link
                    if (positive) { // searched for a link and found a link
                        logSuccessCheckpoint(
                                checkPointName,
                                "WebElement denoted by " + xPath + " is clickable as expected.");
                        return true;
                    } else {
                        logFailureCheckpoint(
                                checkPointName,
                                "WebElement denoted by " + xPath + " is clickable.",
                                "WebElement is NOT clickable.");
                        return false;
                    }
                }
                el = parent;
            } catch (Exception e) {
                // we reached the root without a link
                // it's NOT a link
                if (!positive) {
                    logSuccessCheckpoint(
                            checkPointName,
                            "WebElement denoted by " + xPath + " is NOT clickable as expected.");
                    return true;
                } else {
                    logFailureCheckpoint(
                            checkPointName,
                            "WebElement denoted by " + xPath + " is clickable.",
                            "WebElement is NOT clickable.");
                    return false;
                }

            }
        }
        // we never should reach this point...
        // it's NOT a link
        if (!positive) {
            logSuccessCheckpoint(
                    checkPointName,
                    "WebElement denoted by " + xPath + " is NOT clickable as expected.");
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "WebElement denoted by " + xPath + " is clickable.",
                    "WebElement is NOT clickable.");
            return false;
        }
    }

    public boolean isInputfieldEditable(String xpathToInputfield, String checkPointName) {

        try {
            WebElement element = getByXPath(xpathToInputfield);

            String currentValue;
            String newValue;

            if (element != null && element.getTagName().equalsIgnoreCase("input")) {

                currentValue = element.getAttribute("value");
                element.sendKeys("EDITABLE");
                newValue = element.getAttribute("value");

                if (!currentValue.equalsIgnoreCase(newValue)) {
                    return true;
                } else {
                    return false;
                }

                //This element is NULL or NOT an Input Field
            } else {

                logFailureCheckpoint(
                        checkPointName, "",
                        "This element is NULL or NOT an Input Field");
                return false;
            }
        } catch (NoSuchElementException e) {

            logFailureCheckpoint(
                    checkPointName,
                    "Element should NOT be identified.",
                    "Error occured: " + e.getStackTrace().toString());
            return false;
        }

    }

    public void logSuccessCheckpoint(String checkPointName, String expected) {
        getReporter().reportSuccess(checkPointName, expected);
    }

    public void logFailureCheckpoint(String checkPointName, String expected, String actual) {
        getReporter().reportFailure(checkPointName, expected, actual);
    }

    /**
     * fff
     *
     * @param metadata
     * @param Attribute
     * @param Attributevalue
     * @return
     */
    public boolean metadataexists(String metadata, String Attribute,
                                  String Attributevalue) {

        List<WebElement> Meta = new ArrayList<WebElement>();
        Meta = browser.findElements(By.tagName(metadata));
        boolean Result = false;
        if (Meta.size() > 0) {
            for (int i = 0; i < Meta.size(); i++) {
                Result = true;
                if (Meta.get(i).getAttribute(Attribute) == null
                        || !Meta.get(i).getAttribute(Attribute)
                        .equals(Attributevalue)) {
                    Result = false;
                }
                if (Result)
                    break;

            }
            if (Result) {
                logSuccessCheckpoint(
                        "check if metadata \"" + metadata + "\" with the  Atribute \"" + Attribute + "\" equals \"" + Attributevalue + "\"",
                        Attributevalue);
            } else {
                logFailureCheckpoint("check if metadata \"" + metadata + "\" with the  Atribute \"" + Attribute + "\" equals \"" + Attributevalue + "\"",
                        "\"" + Attributevalue + "\"",
                        "\"" + Attributevalue + "\"" + " doesn't exsist");

            }
        } else {
            logFailureCheckpoint(
                    "check if metadata \"" + metadata + "\" " + "exists",
                    metadata,
                    "Metadata doesn't exist");

        }
        return Result;
    }

    String ArrayToString(String[] Array) {
        if (Array.length == 0) {
            return "{}";
        } else {
            String result = "{";
            for (int i = 0; i < Array.length; i++) {
                result = result + Array[i] + ";";

            }
            return result.substring(0, result.length() - 1) + "}";
        }
    }

    /**
     * @param metadata
     * @param Attribute
     * @param Attributevalue
     */
    public void metadataexists(String metadata, String[] Attribute,
                               String[] Attributevalue) {

        List<WebElement> Meta = new ArrayList<WebElement>();
        Meta = browser.findElements(By.tagName(metadata));
        boolean Result = false;
        if (Meta.size() > 0) {
            for (int i = 0; i < Meta.size(); i++) {
                Result = true;
                for (int j = 0; j < Attribute.length; j++) {

                    if (Meta.get(i).getAttribute(Attribute[j]) == null
                            || !Meta.get(i).getAttribute(Attribute[j])
                            .equals(Attributevalue[j])) {
                        Result = false;
                        break;
                    }
                }
                if (Result)
                    break;
            }
            if (Result) {
                logSuccessCheckpoint(
                        "Check if metadata \"" + metadata
                                + "\" with the  Atribute \""
                                + ArrayToString(Attribute) + "\" equals \""
                                + ArrayToString(Attributevalue) + "\"",
                        ArrayToString(Attributevalue));
            } else {
                logFailureCheckpoint(
                        "Check if metadata \"" + metadata
                                + "\" with the  Atribute \""
                                + ArrayToString(Attribute) + "\" equals \""
                                + ArrayToString(Attributevalue) + "\"",
                        "\"" + ArrayToString(Attributevalue) + "\"",
                        "\"" + ArrayToString(Attributevalue) + "\"" + " doesn't exsist");
            }
        } else {
            logFailureCheckpoint(
                    "Check if metadata \"" + metadata + "\" "
                            + "exists",
                    metadata,
                    "Metadata doesn't exist");
        }
    }

    public boolean checkValueOfAttribute(WebElement webel, String attribute, String expected, String checkPointName) {
        try {
            if (webel.getAttribute(attribute).equals(expected)) {
                logSuccessCheckpoint(
                        checkPointName,
                        "The value of the attribute " + attribute + " of the element matches the expected value " + expected);
                return true;
            } else {
                logFailureCheckpoint(
                        checkPointName,
                        "The value of the attribute should match the expected value",
                        "The value of the attribute " + attribute + " of the element does NOT match the expected value " + expected);
                return false;
            }
        } catch (Exception e) {
            logFailureCheckpoint(
                    checkPointName,
                    "Error occured while getting webelement's attribute",
                    e.getMessage());
            return false;
        }
    }

    public boolean checkCountElements(String xPath, int expectedCount, String checkPointName) {
        List<WebElement> elements = getBrowser().findElements(By.xpath(xPath));
        int actualCount = elements.size();
        if (actualCount == expectedCount) {
            logSuccessCheckpoint(
                    checkPointName,
                    "The number of Newsitems displayed is " + expectedCount);
            return true;
        } else {
            logFailureCheckpoint(
                    checkPointName,
                    "The number of Newsitems displayed is " + expectedCount,
                    actualCount + " Newsitems are displayed. It should be " + expectedCount);
            return false;
        }
    }

    public boolean clickOnElement(String xPath, String checkPointName) {
        try {
            WebElement element = getByXPath(xPath);
            if (element != null) {
                element.click();
                logSuccessCheckpoint(
                        checkPointName,
                        "Element has been clicked");
                return true;
            } else {
                logFailureCheckpoint(
                        checkPointName,
                        "Element has been clicked",
                        "Element has not been clicked");
                return false;
            }
        } catch (Exception ex) {
            logFailureCheckpoint(
                    checkPointName,
                    "Element should NOT be identified.",
                    "Error occured: " + ex.getMessage());
            return false;

        }
    }

    public void selectFilter(String filter, String element, String checkpont) {
        Select select = null;
        try {
            select = new Select(getByXPath(filter));
            select.selectByValue(element);
            logSuccessCheckpoint(
                    checkpont,
                    "Filter was changed");

        } catch (NoSuchElementException e) {
            logFailureCheckpoint(
                    checkpont,
                    "",
                    e.getMessage());
        }
        pauseTest(2000);
    }

    public void pauseTest(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int countTestCases() {
        return 0;
    }

    /**
     * Eine vom Interface vorgegebene Methode. Da die Methode von der Klasse nicht verwendet wird,
     * handelt es sich hierbei nur um ein Dummy.
     *
     * @author M.Forster
     */
    @Override
    public void run(TestResult result) {
        // TODO Auto-generated method stub
    }


    /**
     * Eine vom Interface vorgegebene Methode. Da die Methode von der Klasse nicht verwendet wird,
     * handelt es sich hierbei nur um ein Dummy.
     *
     * @author M.Forster
     */
    @Override
    public void before() {
        getReporter().createReport(this.getClass().getName(), "");
        // TODO Auto-generated method stub

    }

    /**
     * Eine vom Interface vorgegebene Methode. Da die Methode von der Klasse nicht verwendet wird,
     * handelt es sich hierbei nur um ein Dummy.
     *
     * @author M.Forster
     */
    @Override
    public void after() {
        closeBrowser();
        finalizeReport(this.getClass().getSimpleName());
        this.reporter.printReport();//printReport(getReport());
    }

}
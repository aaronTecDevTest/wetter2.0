package de.telekom.pni.rmstest.backend.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import junit.framework.AssertionFailedError;
import junit.framework.TestResult;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New;
import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import de.telekom.pni.rmstest.backend.manager.WebDriverManager;
import de.telekom.pni.rmstest.backend.reporting.Report;
import de.telekom.pni.rmstest.backend.reporting.Reporter;
import de.telekom.pni.rmstest.backend.reporting.Step;

/**
 * JUnitTest.java ist eine abstrakte Klasse, die das Interface RMSTest.java implementiert.
 * Aus dieser abstrakten Klasse können konkrete JUnit Tests erzeugt werden. Diese  Tests
 * können mit den von JUnit zur Verfügung gestellten Methoden ausgeführt werden, können aber
 * von der GUI des Test-Frameworks verwendet werden. Die Klasse verfügt weiterhin über Methoden
 * und Werkzeuge um das erstellen von Selunium 2 (WebDriver) Tests zu erstellen.
 *
 * @author M.Forster
 */
public abstract class JUnitTest_New implements RMSTest_New {

    private static final Logger log = Logger.getLogger(JUnitTest_New.class);
    private WebDriver driver;
    private Reporter reporter;
    private RunningConfiguration_New runConfig;
    public final Properties PAGE = PropertiesManager_New.getInstance().getProperties("page");
    private final Properties TESTRUN = PropertiesManager_New.getInstance().getProperties("testrun");
    private String failureString;
    private String errorString;

    /**
     * Konstruktor der Klasse. Der Konstruktor initialisiert die für die Tests nötigen Komponenten.
     * Es wird der zum Arbeiten mit dem Web-Browser benötige WebDriver und der fürs Reporting benötigte
     * Reporter und der error und failure String initialisiert.
     */
    public JUnitTest_New() {
        reporter = new Reporter();
        reporter.createReport(this.getClass().getName(), "");
        failureString = new String();
        errorString = new String();
    }

    public RunningConfiguration_New getRunningConfiguration() {
        return this.runConfig;
    }

    /**
     * Getter Methode für den Failure String
     *
     * @return failureString - String in dem "failures" des Testdurchlaufs gesammelt werden
     */
    public String getFailureString() {
        return failureString;
    }

    /**
     * Getter Methode für den Error String
     *
     * @return errorString - String in dem "errors" des Testdurchlaufs gesammelt werden
     */
    public String getErrorString() {
        return errorString;
    }

    /**
     * Mit dieser Methode wird an den existierenden failureString ein beliebiger String angehängt.
     * Der vorhandene String wird dabei nicht überschrieben.
     *
     * @param string - String, der an failureString angefügt wird
     */
    public void appendFailure(String string) {
        if (string != null)
            failureString = failureString.concat(string);
    }

    /**
     * Mit dieser Methode wird an den existierenden errorString ein beliebiger String angehängt.
     * Der vorhandene String wird dabei nicht überschrieben.
     *
     * @param string - String, der an errorString angefügt wird
     */
    public void appendError(String string) {
        if (string != null)
            errorString = errorString.concat(string);
        log.debug("Error appended to error String");
    }

    /**
     * Diese Methode wird hier nicht verwendet. Da sie vom Interface vorgegeben ist, wird sie
     * hier als dummy implementiert
     */
    public void setURLValues(HashMap<String, String> urlValues) {
        // TODO Auto-generated method stub
    }

    /**
     * Methode zum setzen der RunningConfiguration für den Testfall
     *
     * @param runningConfiguration - RunningConfiguration für den Testfall
     */
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        runConfig = runningConfiguration;
        reporter.getReport().setBrowser(runConfig.getBrowser());
    }

    /**
     * Methode um den Testfall auf fehlgeschlagen zu setzen.
     */
    public void setFailed() {
        log.info("Test stopped by user");
        fail("Test stopped by user");
    }

    /**
     * Methode zum schlißen des Browsers beziehungsweise des WebDrivers
     */
    public void closeBrowser() {
        if (this.driver != null) {
            driver.close();
            log.debug("Browser closed");
        }
    }

    /**
     * Getter zum erhalten des zum Test gehörenden Reports. Die Methode
     * ruft vom zugehörigen Reporter den erstellten Report ab und gibt ihn zurück.
     *
     * @return reporter.getReport()
     */
    @Override
    public Report getReport() {
        return reporter.getReport();
    }

    /**
     * Methode die vor dem Durchführen des Testfalls automatisch ausgeführt wird.
     * Im Moment ist sie leer, da keine Aktionen vor dem Starten des Testfalls benötigt werden.
     */
    @Before
    public void setUp() {
        log.debug("Entering setUp Method");
        log.debug("Leaving setUp Method");
    }

    /**
     * Ähnlich wie die setUp() Methode, wird diese Methode vor dem Start des Testfalls aufgerufen.
     * Sie wird allerdings nur dann verwendet, wenn der Test von der GUI, einem JUnit
     * Testrunner oder einer JUnit TestSuite verwendet wird. Im Moment ist die Methode leer, da
     * keine Aktionen vor dem Starten des Testfalls benötigt werden.
     */
    public void before() {
    }

    /**
     * Methode, die nach dem Beenden des Testfalls automatisch ausgeführt wird. über diese Methode
     * wird das Schreiben des Reports ins Logfile und bei negativem Abschluss des Testfalls ein
     * error beziehungsweise failure geworfen, der von JUnit ausgewertet werden kann.
     */
    @After
    public void tearDown() {
        reporter.printReport();
        closeBrowser();
        if (getReporter().getStatus() == false) {
            throw new Error("Error: The test has no steps to execute or one or more steps are corrupted.");
        }
        if (!errorString.isEmpty()) {
            throw new Error(errorString);
        }
        if (!failureString.isEmpty()) {
            fail(failureString);
        }
    }

    /**
     * Ähnlich wie die setUp() Methode, wird diese Methode nach dem Beenden des Testfalls automatisch
     * ausgeführt. Sie wird allerdings nur dann verwendet, wenn der Test von der GUI, einem JUnit
     * Testrunner oder einer JUnit TestSuite verwendet wird.
     * über diese Methode wird das Schreiben des Reports ins Logfile und bei negativem Abschluss des
     * Testfalls eine passende Ausgabe auf der Konsole erzeugt.
     */
    public void after() {
        log.debug("Entering after Method");
        log.info("Test: " + reporter.getReport().getTestName());
        reporter.printReport();
        if (getReporter().getStatus() == false) {
            errorString = "Error: The test has no steps to execute or one or more steps are corrupted.";
        }
        if (!errorString.isEmpty()) {
            log.error("Testing finished with errors");
            log.error(errorString);
        } else {
            if (failureString.isEmpty()) {
                log.info("Testing finished. Status = Passed");
            } else {
                log.error("Testing finished. Status = Failed");
                log.error(failureString);
            }
        }
        closeBrowser();
        log.debug("Leaving after Method");
    }

    /**
     * Getter Methode für den  Webdriver. Wenn der WebDriver ordnungsgemäß initiaisiert wurde und nicht
     * null ist, wird er zurückgegeben und dem Report ein positiver Step hinzugefügt.
     * Andernfalls wird im Report ein negativer Step hinzugefügt und ein Error geworfen, der den
     * Test sofort beendet.
     *
     * @return driver - WebDriver über den der WebBrowser angesprochen werden kann
     * @throws Error - Fehlermeldung, dass beim zurückgeben des WebDrivers ein Fehler aufgetreten ist
     */
    public WebDriver getBrowser() throws Error {
        try {
            if (this.driver == null) {
                WebDriverManager driverManager = new WebDriverManager();
                driver = driverManager.setUpDriver(runConfig);
                reporter.reportSuccess("Getting browser", "Browser should be returned");
                log.debug("New WebDriver created");
                return this.driver;
            } else {
                reporter.reportSuccess("Getting browser", "Browser should be returned");
                log.debug("WebDriver received");
                return this.driver;
            }
        } catch (Exception ex) {
            reporter.reportFailure("Getting Browser", "Browser should be returned", "Browser was not returned. " + ex.getMessage());
            log.error("Error while getting the WebDriver. " + ex.getMessage());
            throw new Error("Error while geting the Browser\r\n");
        }
    }

    /**
     * Getter methode für den reporter. Diese Methode gibt den dem Testfall ugeordneten Reporter zurück.
     *
     * @return reporter - Der dem Test zugeordnete Reporter
     */
    public Reporter getReporter() {
        return reporter;
    }

    /**
     * Methode zum aufrufen einer beliebigen WebSeite im Browser. Die Methode ruft mit hilfe des WebDriver
     * die in url angegebene Web-Seite auf und fügt dem Report einen positiven Step hinzu. Sollte bei dem
     * Aufruf ei Fehler auftreten, wird dieser abgefangen, ein negativer Step im Report hinzugefügt und
     * dann ein passender Error geworfen, der den Test sofort beendet.
     *
     * @param rc - (Url)String in dem die Adresse der aufzurufenden Web-Seite angegeben ist
     * @throws Error - Fehlermeldung, dass beim Aufrufen der Web-Seite ein Fehler aufgetreten ist
     */
    public void getPage(RunningConfiguration_New rc) throws Error {
        try {
            if (getRunningConfiguration() == null) {
                runConfig = new RunningConfiguration_New(TESTRUN.getProperty("run.standard.browser"), TESTRUN.getProperty("run.standard.machine"));
            }
            getBrowser().get(runConfig.getMachine() + TESTRUN.getProperty("run.standard.suffix"));
            reporter.reportSuccess("Getting webpage", "Page should be opened");
            log.debug("Webpage received");
        } catch
                (Exception e) {
            reporter.reportFailure("Getting webpage", "Page should be opened", "Page not found. " + e.getMessage());
            log.error("Error while getting the Webpage " + e.getMessage());
            throw new Error("Error while getting the Page ");
        }
    }

    public void getPage(String url) {
        try {
            if (getRunningConfiguration() == null) {
                runConfig = new RunningConfiguration_New(TESTRUN.getProperty("run.standard.browser"), TESTRUN.getProperty("run.standard.machine"));
            }
            if (this.getRunningConfiguration().getBrowser().equals("IE")) {
                getBrowser().get("javascript:document.getElementById('overridelink').click();");
            }
            if (url.isEmpty()) {
                getBrowser().get(runConfig.getMachine() + TESTRUN.getProperty("run.standard.suffix"));
                log.debug("Webpage received by running configuration");
            } else {
                getBrowser().get(url);
                log.debug("Webpage received by url:" + url);
            }
            reporter.reportSuccess("Getting webpage", "Page should be opened");
        } catch
                (Exception e) {
            reporter.reportFailure("Getting webpage", "Page should be opened", "Page not found. " + e.getMessage());
            throw new Error("Error while getting the Page ");
        }
    }

    /**
     * Methode die ein spezielles WebElement auf der getesteten Web-Seite sucht und zurückgibt.
     * Anhand des angegebenen xpath, css oder der id wird dass element mit Hilfe der passenden
     * findElement Methode des WenDriver gesucht. Im erfolgsfall wird ein positiver Step zum
     * Report hinzugefügt. Tritt ein Fehler auf, wird dieser abgefangen, ein negativer Step
     * dem Report hinzugefügt und ein Error geworfen, der den Test sofort beendet.
     *
     * @param type  - legt fest ob es sich beim ident String um xpath, css oder eine id handelt
     * @param ident - identifiziert das WebElement mit hilfe seines xpath, css oder der id
     * @return element - das gefundene WebElement
     * @throws Error - Fehlermeldung, dass beim Suchen nach dem WebElement ein Fehler aufgetreten ist
     */
    public WebElement getElement(String type, String ident) throws AssertionError {
        WebElement element = null;
        try {
            switch (type) {
                case "css":
                    element = driver.findElement(By.cssSelector(ident));
                    break;
                case "id":
                    element = driver.findElement(By.id(ident));
                    break;
                default:
                    element = driver.findElement(By.xpath(ident));
                    break;
            }
            reporter.reportSuccess("Getting Element", "Element should be found");
        } catch (NoSuchElementException ex) {
            reporter.reportFailure("Getting Element", "Element should be found", "Error while getting element " + ident + ". " + ex.getMessage());
            throw new AssertionError("Error while getting element" + ident + ". " + ex.getMessage());
        }
        return element;
    }

    /**
     * Methode, die anhand des übergebenen String nach einem WebElement auf der Webseite sucht und
     * dieses Element dann mit Hilfe der click() Methode von WebElement anklickt. Tritt ein Fehler auf,
     * wird dieser abgefangen und ein Eintrag im Report des Testfalls erstellt.
     *
     * @param ident - String anhand das WebElement identifiziert werden kann
     */
    public void clickElement(String ident) {
        WebElement element = null;
        try {
            element = this.getElement("xpath", ident);
            element.click();
            reporter.reportSuccess("Click Element", "Element" + ident + " was clicked.");
        } catch (Exception ex) {
            reporter.reportFailure("Click Element", "Element can be clicked", "Element " + ident + " could not be clicked. " + ex.getMessage());
            appendFailure("Error while clicking the element " + ident + "." + ex.getMessage());
        }
    }

    /**
     * Methode, die anhand des übergebenen String nach einem WebElement auf der Webseite sucht und
     * diesem Element dann mit Hilfe der sendKeys() Methode von WebElement den String aus der Variable keys
     * übergibt. Tritt ein Fehler auf, wird dieser abgefangen und ein Eintrag im Report des Testfalls erstellt.
     *
     * @param ident - String anhand das WebElement identifiziert werden kann
     * @param keys  - String mit dem Text der an das WebElement übergeben werden soll
     */
    public void sendKeys(String ident, String keys) {
        WebElement element = null;
        try {
            element = this.getElement("xpath", ident);
            element.sendKeys(keys);
            reporter.reportSuccess("Send keys", "Keys were sent to the input field " + ident);
        } catch (Exception ex) {
            reporter.reportFailure("Send keys", "Keys were sent to the input field", "Keys were not sent to the input field " + ident + ". " + ex.getMessage());
            appendFailure("Error while sending text to the input field " + ident + ". " + ex.getMessage());
        }
    }

    /**
     * Diese Methode überprüft, ob der Test des übergebenen WebElement dem erwarteten Text entspricht.
     * Mit Hilfe der asserEquals Methode werden die beiden Objekte verglichen. Ist der Test erfolgreich,
     * wird ein positiver Step dem Report hinzugefügt. Sind die Texte nicht identisch, wird ein negativer
     * step zum Report hinzugefügt und ein Eintrag im failureString hinzugefügt.
     *
     * @param expected - Das zu prüfende WebElement
     * @param actual   - der erwartete Text als String
     * @param step     - Kurze Beschreibung des Steps für den Report
     */
    public void testEquals(WebElement expected, String actual, String step) {
        try {
            assertEquals(actual, expected.getText());
            reporter.reportSuccess(step, expected + " equals " + actual);
        } catch (AssertionError e) {
            reporter.reportFailure(step, "Elements should be equal", "Elemens are not equal. " + e.getMessage());
            appendFailure("Elements not equal" + e.getMessage());
        } catch (Exception ex) {
            reporter.reportFailure(step, "Elements should be equal", "Error while compairing. " + ex.getMessage());
            appendError("Error while checking the equality. " + ex.getMessage());
        }
    }

    /**
     * Diese Methode überprüft, ob auf der Web-Seite ein WebElement existiert, dass anhand des
     * angegebenen xpath gefunden werden kann. Ist kein solches Element vorhanden, wird ein Error
     * geworfen. Danach wird ein negativer Step im Report eingefügt und ein Fehler in der filureString
     * geschrieben. Wird ein Element gefunden, wird ein positiver Step zum Report hinzugefügt.
     *
     * @param xPath - Identifiziert das WebElement auf der Webseite
     * @param step  - Kurze Beschreibung des Steps für den Report
     */
    public void testElementPresent(String xPath, String step) {
        try {
            List<WebElement> elements;
            elements = driver.findElements(By.xpath(xPath));
            if (elements.isEmpty()) {
                throw new Exception("Element ( " + xPath + " ) inexistent.");
            } else {
                reporter.reportSuccess(step, "Element " + xPath + " exists.");
            }
        } catch (Exception e) {
            reporter.reportFailure(step, "Element should exist", "Element " + xPath + " does not exist.");
            appendFailure("Element " + xPath + " does not exist.");
        }
    }

    /**
     * Diese Methode überprüft, ob ein WebElement auf der Webseite nicht existiert. Anhand des angegebenen
     * xpath wird nach dem Element gesucht. Wird ein passendes Element gefunden, wird eine Error geworfen
     * und dann ein negativer Step im Report hinzugefügt und der Error im failure String hinzugefügt.
     * Wird kein passendes Element gefunden wird ein positiver Step in den Report eingetragen und die Methode
     * ohne Fehler beendet
     *
     * @param xPath - Identifiziert das WebElement auf der Webseite
     * @param step  - Kurze Beschreibung des Steps für den Report
     */
    public void testElementNotPresent(String xPath, String step) {
        try {
            List<WebElement> elements;
            elements = driver.findElements(By.xpath(xPath));
            if (elements.isEmpty()) {
                reporter.reportSuccess(step, "Element " + xPath + " does not exist.");
            } else {
                throw new Error("Element ( " + xPath + " ) inexistent");
            }
        } catch (Error e) {
            reporter.reportFailure(step, "Element should not exist", "Element " + xPath + " exists.");
            appendFailure("Element " + xPath + " exists.");
        }
    }

    /**
     * Diese Methode zählt die anhand des xpath identifizierten WebElements und vergleicht sie mit der
     * übergebenen Anzahl. Stimmen die beiden Zahlen überein, wird im Report ein positiver Step hinzugefügt.
     * Simmen die Zahlen nicht überein, wird ein negativer Step im Report hinzugefügt und eine Fehlermeldung
     * in den Failure String geschrieben.
     *
     * @param countExpected - Die Anzahl an Elementen, die vom Tester erwartet wird
     * @param xPath         - Identifiziert das WebElement auf der Webseite
     */
    public void testCountElements(int countExpected, String xPath) {
        try {
            int countActual = driver.findElements(By.xpath(xPath)).size();
            assertEquals(countActual, countExpected);
            reporter.reportSuccess("Count Elements", "Number of elements is " + countActual);
        } catch (AssertionError e) {
            reporter.reportFailure("Count Elements", "Number of elements should be" + countExpected, "Number of elements are not equal. " + e.getMessage());
            appendFailure("Number of Elements not equal " + e.getMessage());
        }
    }

    /**
     * Eine vom Interface vorgegebene Methode. Da die Methode von der Klasse nicht verwendet wird,
     * handelt es sich hierbei nur um ein Dummy.
     */
    @Override
    public int countTestCases() {
        // TODO Auto-generated method stub
        return 0;
    }


    /**
     * Bei dieser Methode handelt es sich um eine von JUnit Test verbten Methode. Da die Methode
     * intern anders verwendet wird, als von JUnit implementiert, wird sie hier überschrieben.
     * Die Methode wird von JUnit verwendet um Testfälle in einem TestRunner oder einer TestSuite
     * auszuführen. Die Methode stellt als quasi eine Schnittstelle zu diesen Implementationen von JUnit
     * dar.
     */
    @Override
    public void run(TestResult result) {
        before();
        runTest();
        after();
        convertReportToTestResult(result);
    }

    /**
     * Diese Methode konvertiert die vom Reporter erzeugten Testergebnisse in ein von JUnit verarbeitbares
     * TestResult. Der Status des Reports wird vom Reporter abgefragt. Ist der Status "False" wird im
     * TestResult ein failure hinzugefügt.
     *
     * @return testResult - gibt ein TestResult zurück, in dem die Ergebnisse des Tests eingetragen sind
     */
    public TestResult convertReportToTestResult(TestResult testResult) {
        Report rep = reporter.getReport();
        if (reporter.getReport().getStatus() == false) {
            for (Step step : rep.getSteps()) {
                if (step.getStatus() == false) {
                    String failureString = "\n\tStep:" + step.getStepName() + "\n\tExpected:" + step.getExpected() + "\n\tActual:" + step.getActual();
                    testResult.addFailure(this, new AssertionFailedError(failureString));
                }
            }
        }
        return testResult;
    }

    /**
     * Die Methode runTest() ist eine abstrakte Methode, die an dieser Stelle nicht implementiert wird.
     * Die Methode wird in den konkreten Testklassen ausprogrammiert.
     */
    @Override
    public abstract void runTest();

    /**
     * Eine vom Interface vorgegebene Methode. Die Methode wird nur von der Schwesterklasse GenericTest
     * verwendet, für diese Klasse jedoch nicht benötigt. Deshalb handelt es sich hier nur um einen Dummy.
     */
    @Override
    public Report finalizeReport(String testName) {
        // TODO Auto-generated method stub
        return null;
    }

}
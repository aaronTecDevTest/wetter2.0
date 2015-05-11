package de.telekom.pni.rmstest.backend.application;

import java.util.Properties;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import org.apache.log4j.Logger;

import de.telekom.pni.rmstest.backend.config.LoginDataRally;
import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.config.TestrunConfiguration_New;
import de.telekom.pni.rmstest.backend.core.JUnitTestImpl_New;
import de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New;
import de.telekom.pni.rmstest.backend.externalReporting.RallyExternalReportingImpl;
import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import de.telekom.pni.rmstest.backend.reporting.Report;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;

/**
 * Autorun.java wird für die Ausführung von mehreren Testfällen aus einem bestimmten TestSet aus Rally
 * verwendet. In der Main Methode der Klasse werden alle für den Testdurchlauf benötigten Komponenten
 * erzeugt, die nötigen Daten aus Rally geladen und dann der Test durchgeführt. Die Tesergebnisse werden
 * mit Hilfe des internen und externen Reporting protokolliert.
 *
 * @author M.Forster
 */
public class Autorun {

    private static final Logger log = Logger.getLogger(Autorun.class);
    private static RallyExternalReportingImpl rallyRep;
    private static final Properties AUTORUN = PropertiesManager_New.getInstance().getProperties("autorun");
    private static final Properties TESTRUN = PropertiesManager_New.getInstance().getProperties("testrun");
    private static RunningConfiguration_New runConfig;
    private static TestrunConfiguration_New testConfig;

    /**
     * Main Funktion der Klasse. Sie führt den Testdurchlauf durch und protokolliert die Ergebnisse
     * mit Hilfe der internen und externen Reporting Funktionen.
     */
    public static void main(String[] args) {
        log.info("Starting automatic Testrun");
        String machineIp = "";
        if (AUTORUN.getProperty("machine").equals("Abnahmetest")) {
            machineIp = TESTRUN.getProperty("run.machine.release");
        }
        if (AUTORUN.getProperty("machine").equals("Sprinttest")) {
            machineIp = TESTRUN.getProperty("run.machine.sprint");
        }
        if (AUTORUN.getProperty("machine").equals("Wirktest")) {
            machineIp = TESTRUN.getProperty("run.machine.wirk");
        }
        runConfig = new RunningConfiguration_New(AUTORUN.getProperty("browser"), machineIp);
        testConfig = new TestrunConfiguration_New(1);
        testConfig.setBrowser(AUTORUN.getProperty("browser"));
        testConfig.setMachine(machineIp);
        log.debug("Testrun configured");
        LoginDataRally login = new LoginDataRally();
        login.setUsername(AUTORUN.getProperty("user"));
        login.setPassword(AUTORUN.getProperty("password"));
        rallyRep = new RallyExternalReportingImpl();
        rallyRep.connect(login);
        log.debug("Rally connection established");
        TestSet testSet = rallyRep.getTestSet(AUTORUN.getProperty("testSet"));//, "");
        testSet.setConfig(testConfig);
        for (TestCase testCase : testSet.getTestCases()) {

            if ((testCase.getExecutableClass().substring(0, testCase.getExecutableClass().indexOf('.'))).equals("internalTest")) {
                try {
                    //runConfig.setMachine(runConfig.getMachine() + testConfig.)
                    /*StorageManager tm = new MySQLStorageManagerImpl();
					Integer id = testCase.getTestCaseID();
					TestSet intTestSet = testCase.getTestSet();
					testCase = tm.getTestCase(testCase.getTestCaseName());
					testCase.setTestCaseID(id);
					testCase.setTestSet(intTestSet);*/
                    RMSTest_New jTest = new JUnitTestImpl_New(testCase);
                    jTest.setRunningConfiguration(runConfig);
                    log.info("Staring Test " + testCase.getTestCaseName());
                    jTest.before();
                    jTest.runTest();
                    jTest.after();
                    Report report = jTest.getReport();
                    log.info("Writing Testresult to external Reporting");
                    rallyRep.writeTestResult(testCase, report);
                    log.info("Test " + testCase.getTestCaseName() + " Finished");
                } catch (Exception ex) {
                    log.error("Error while running the Test " + testCase.getTestCaseName() + " " + ex.getMessage());
                }
            } else {
                RMSTest_New rmstest = null;
                try {
                    String execClass = testCase.getExecutableClass();
                    execClass = execClass.substring(execClass.lastIndexOf("testing.") + 8, execClass.length());
                    execClass = TESTRUN.getProperty("rootfolder") + execClass;
                    log.info("Staring Test " + testCase.getTestCaseName());
                    rmstest = (RMSTest_New) Class.forName(execClass).newInstance();
                    rmstest.setRunningConfiguration(runConfig);
                    rmstest.before();
                    rmstest.runTest();
                    rmstest.after();
                    Report report = rmstest.getReport();
                    log.info("Writing Testresult to external Reporting");
                    rallyRep.writeTestResult(testCase, report);
                    log.info("Test " + testCase.getTestCaseName() + " Finished");
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    log.error("Error while running the Test " + testCase.getTestCaseName() + " " + e.getMessage());
                } catch (Exception ex) {
                    log.error("Error while running the Test " + testCase.getTestCaseName() + " " + ex.getMessage());
                    rmstest.closeBrowser();
                }
            }
        }
        log.info("Automatic Testrun finished");
    }
}

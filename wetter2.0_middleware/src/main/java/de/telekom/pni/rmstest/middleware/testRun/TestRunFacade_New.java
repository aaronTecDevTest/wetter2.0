package de.telekom.pni.rmstest.middleware.testRun;

import org.apache.log4j.Logger;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.JUnitTestImpl_New;
import de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New;
//import de.telekom.pni.rmstest.backend.dataStorage.MySQLStorageManagerImpl;
import de.telekom.pni.rmstest.backend.reporting.Report;
import de.telekom.pni.rmstest.backend.reporting.Step;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;

/**
 * TestRunFacade.java ist für das Ausführen von Testfällen über die Benutzeroberfläche zustädig.
 * Sie stellt Methoden zur Verfügung, die das Ausführen von internen und externen Testfällen ermöglicht.
 *
 * @author M.Forster
 */
public class TestRunFacade_New {

    private static final Logger log = Logger.getLogger(TestRunFacade_New.class);

    /**
     * Diese Methode ist für die Ausführung von externen Testfällen zuständig.
     *
     * @param testName  - Name des auszuführenden Testfalls als String
     * @param runConfig - RunningConfiguration des Testfalls
     * @return - report - Testergebniss als Report Objekt
     */
    public synchronized Report runExternalTest(String testName, RunningConfiguration_New runConfig) {
        log.debug("Entering runExternalTest Method");
        RMSTest_New rmstest = null;

        try {
            rmstest = (RMSTest_New) Class.forName(testName).newInstance();
            rmstest.setRunningConfiguration(runConfig);
            rmstest.before();
            rmstest.runTest();
            rmstest.after();
            log.debug("Leaving runExternalTest Method");
            return rmstest.getReport();
        } catch (ClassNotFoundException ce) {
            log.error("Test could not be executed. No Class found for Testcase " + testName);
            Report dummyRep = new Report();
            dummyRep.setTestName(testName);
            dummyRep.addStep(new Step(false, "Test does not exist", "", "", "No Class found for Testcase " + testName));
            return dummyRep;
        } catch (Exception ex) {
            if (rmstest != null) {
                rmstest.getReport().addStep(new Step(false, "Test crashed", "", "", "Test crashed during Testrun. " + ex.getMessage()));
                rmstest.getReport().setStatus(false);
                rmstest.after();
                return rmstest.getReport();
            } else {
                log.error("Could not execute test: " + testName + " " + ex.getMessage());
                Report dummyRep = new Report();
                dummyRep.setTestName(testName);
                dummyRep.addStep(new Step(false, "Test does not exist", "", "", "No Class found for Testcase " + testName));
                return dummyRep;
            }
        }
    }

    /**
     * Diese Methode ist für die Ausführung von internen Testfällen zuständig.
     *
     * @param test      - Der auszuführende Tests als TestCase Objekt
     * @param runConfig - RunningConfiguration des Testfalls
     * @return - report - Testergebniss als Report Objekt
     */
    public synchronized Report runInternalTest(TestCase test, RunningConfiguration_New runConf) {
        log.debug("Entering runInternalTest  Method");
        try {
            /*StorageManager storageManager = new MySQLStorageManagerImpl();
			Integer id = test.getTestCaseID();
			TestSet testSet = test.getTestSet();
			test = storageManager.getTestCase(test.getTestCaseName());
			test.setTestCaseID(id);
			test.setTestSet(testSet);*/
            RMSTest_New internalTest = new JUnitTestImpl_New(test);
            //JUnitTestImpl_New  testTest = new JUnitTestImpl_New(test);
            //RMSTest_New internalTest  = (RMSTest_New) testTest;

            internalTest.setRunningConfiguration(runConf);
            internalTest.before();
            internalTest.runTest();
            internalTest.after();
            log.debug("Leaving runInternalTest  Method");
            if (internalTest.getReport().getSteps().size() < 1) {
                internalTest.getReport().setTestName("null");
            }
            return internalTest.getReport();
        } catch (Exception ex) {
            log.error("Could not execute test: " + test.getTestCaseName() + " " + ex.getMessage());
            Report dummyRep = new Report();
            dummyRep.setTestName("null");
            log.debug("Leaving runInternalTest  Method");
            return dummyRep;
        }
    }
}

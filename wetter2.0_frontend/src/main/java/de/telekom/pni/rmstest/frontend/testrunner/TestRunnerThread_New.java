package de.telekom.pni.rmstest.frontend.testrunner;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.reporting.Report;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.frontend.controller.ApplicationController_New;
import de.telekom.pni.rmstest.frontend.view.ApplicationWindows_New;
import de.telekom.pni.rmstest.middleware.properties.PropertiesFacade;
import de.telekom.pni.rmstest.middleware.testRun.TestRunFacade_New;


/**
 * TestRunnerThread wird vom Framework verwendet um mehrere Testfälle nacheinander durchzuführen.
 * Die Klasse wurde vom Entwickler des Frameworks A.Roth erzeugt aber leider nicht dokumentiert.
 * Ich werde mich bei der Dokumentation auf die von mir durchgeführten Änderungen beschränken.
 *
 * @author A.Roth
 * @author M.Forster
 */
public class TestRunnerThread_New extends Thread {
    private static final Logger log = Logger.getLogger(TestRunnerThread_New.class);
    private List<String> selectedTests;
    private Collection<TestCase> testsToExecute;
    private RunningConfiguration_New runningConfiguration;
    private ApplicationController_New applicationController;
    private boolean runningCondition;
    private int configurationID;
    private Report report;
    private TestRunFacade_New testRunFacade;
    private PropertiesFacade propFacade = new PropertiesFacade();
    private Properties TESTRUN = propFacade.getProperties("testrun");

    int progressCounter = 0;
    int progressMax = 0;

    public TestRunnerThread_New(List<String> selectedList, Collection<TestCase> tests, RunningConfiguration_New rc, ApplicationController_New appController) {
        this.selectedTests = selectedList;
        this.testsToExecute = tests;
        this.runningConfiguration = rc;
        this.applicationController = appController;
        this.runningCondition = true;
    }

    /**
     * Diese Methode führt die in der GUI Ausgewählten Testfälle nacheinander aus.
     * Die ursprüngliche Methode wurde dahingehend angepasst, dass sowohl Tests basierend
     * auf JUnitTest.java, als auch Tests basierend auf GenericTest.java auführbar sind.
     * Des Weiteren ist es möglich "interne" Testfälle, die direkt über die GUI erstellt
     * und in der Datenbank gespeichert wurden, auszuführen. Anhand der executableClass
     * wird entschieden, um welche Art Teste es sich handelt.
     */
    @Override
    public void run() {
        // run all tests in a new thread

        log.debug("Starting Testrun");
        progressMax = testsToExecute.size();
        testRunFacade = new TestRunFacade_New();

        if (this.testsToExecute == null || testsToExecute.size() == 0) {
            // nothing to do
            return;
        }

        // CMSTest testToRun;
        String cfn = null;

        // we have the testrunner.
        TestCase test = null;
        Iterator<TestCase> it = testsToExecute.iterator();
        boolean filterSelected = selectedTests != null && selectedTests.size() > 0;
        String rawName = null;

        //Beim Starten eines TestCase wird diese Thread/Presse ausgeführt
        Runnable runProgressBar = new Runnable() {
            public void run() {
                applicationController.updateProgressBarMax(progressMax);
                applicationController.updateProgressBar(progressCounter);
                applicationController.updateLabelStatus(progressCounter, progressMax);
            }
        };

        Runnable runStatsLabel = new Runnable() {
            public void run() {
                applicationController.updateLabelStatus(0, progressMax);
                ApplicationWindows_New.getProgressBar().setVisible(false);
            }
        };


        while (it.hasNext()) {
            progressCounter = progressCounter + 1;
            //Beim Starten eines TestCase wird diese Thread/Presse ausgeführt
            if (!ApplicationWindows_New.getProgressBar().isDisposed()) {
                applicationController.getApp().getDisplay().asyncExec(runProgressBar);
            } else {
                break;
            }

            // check wether we have been asked to stop
            if (!getRunningCondition())
                break; // regular stop
            test = it.next();
            // if (!applicationController.matchesFilter(itsTest))
            // continue; // apply filter
            rawName = test.getSimpleTestName();
            System.out.println(rawName);
            if (filterSelected && !applicationController.testNameIsSelected(selectedTests, rawName)) {
                continue;
            }
            if ((test.getExecutableClass().substring(0, test.getExecutableClass().indexOf('.'))).equals("internalTest")) {
                //TODO unter suchen was diese Funktion macht
                log.debug("Staring internal Test");
                report = new Report();
                report = (Report) testRunFacade.runInternalTest(test, this.runningConfiguration);
            } else {
                log.debug("Starting external Test");
                report = new Report();
                cfn = test.getExecutableClass();
                if (cfn == null) {
                    // class could not be found
                    log.error("Could not find class for test " + test.getSimpleTestName());
                    continue;
                }
                cfn = cfn.substring(cfn.lastIndexOf("testing.") + 8, cfn.length());
                cfn = TESTRUN.getProperty("rootfolder") + cfn;

                report = (Report) testRunFacade.runExternalTest(cfn, this.runningConfiguration);
            }
            try {
                applicationController.writeTestResultToExternalReporting(test, report);
                log.info("Testresult transfered to external Reporting");

            } catch (Exception e) {
                log.error("Could not write the run result to the external Reporting. " + e.getMessage());
            }

        } // end while there are some tests
        // notify completion
        applicationController.testRunCompleted(configurationID);

        //Ende es Prozesse
        if (!ApplicationWindows_New.getProgressBar().isDisposed()) {
            applicationController.getApp().getDisplay().syncExec(runStatsLabel);
        }
        log.info("Ending Testrun");
    }

    public synchronized boolean getRunningCondition() {
        return runningCondition;
    }

    public synchronized void setRunningCondition(boolean runningCondition) {
        this.runningCondition = runningCondition;
    }

    public int getConfigurationID() {
        return configurationID;
    }

    public void setConfigurationID(int configurationID) {
        this.configurationID = configurationID;
    }
}

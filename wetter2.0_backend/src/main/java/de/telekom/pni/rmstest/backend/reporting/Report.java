package de.telekom.pni.rmstest.backend.reporting;

import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * In der Klasse ReportImpl.java werden die Testergebnisse eines Testfalls eingetragen, damit sie mit den anderen
 * Teilen des Frameworks verwendet werden können. Die Klasse wurde vom Entwickler des Frameworks A.Roth
 * erzeugt aber leider nicht dokumentiert. Ich werde mich bei der Dokumentation auf die von mir durchgeführten
 * Änderungen beschränken. Die Klasse implementiert das Intervace Report.java, um Kompatibilität mit dem
 * Framework zu gewährleisten.
 *
 * @author A.Roth
 * @author M.Forster
 */

public class Report {

    private static final Logger log = Logger.getLogger(Report.class);
    private String testName;
    private String startTime;
    private String endTime;
    private String executionDate;
    private int duration;
    private Vector<Step> steps;
    private boolean status;
    private String browser;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * Konstruktor der Klasse. Der Konstruktor initialisiert die privaten Variablen status,
     * steps. startTime, endTime, executionDate und duration.
     */
    public Report() {
        log.debug("Creating Report");
        status = true;
        steps = new Vector<Step>();
        startTime = "";
        endTime = "";
        executionDate = "";
        browser = "";
        duration = 0;
        log.debug("Report created");
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Vector<Step> getSteps() {
        if (steps.isEmpty()) {
            return null;
        } else {
            return steps;
        }
    }

    public Step getStep(int stepId) {
        return this.steps.get(stepId);
    }

    public void addStep(Step step) {
        this.steps.add(step);
    }

    public void setSteps(Vector<Step> steps) {
        this.steps = steps;
    }

    ;
}

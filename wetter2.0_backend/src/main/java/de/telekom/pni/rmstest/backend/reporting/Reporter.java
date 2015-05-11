package de.telekom.pni.rmstest.backend.reporting;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * Die Klasse Reporter.java ist für das Protokollieren der Testergebnisse zuständig. Sie erzeugt
 * zu jedem durchgeführten Test einen Report und trägt dort die Testergebnisse ein. Des Weiteren stellt
 * Sie Methoden zur Verfügung um das Testergebnis in ein Logfile zu schreiben.
 *
 * @author M.Forster
 */
public class Reporter {

    private static final Logger log = Logger.getLogger(Reporter.class);
    private Report report;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    //private final SimpleDateFormat logDateFormat = new SimpleDateFormat ("MMddyyyy");
    //private final String logpath = "log/";
    //private String logfile = "";
    private boolean status;

    /**
     * Konstruktor der Klasse. Er initialisiert die privaten Variablen report, lofileDate,
     * logfile und setzte den Status des Report auf false.
     */
    public Reporter() {
        log.debug("Creating Reporter");
        report = new Report();
        //String logfileDate = new StringBuilder(logDateFormat.format(new Date())).toString();
        //logfile = logfileDate + "_RMS_Test.log";
        status = false;
        log.debug("Reporter Created");
    }

    /**
     * Die Methode createReport() erzeugt einen neuen Report mit dem übergebenen Namen. Des Weiteren
     * legt er Start-Datum und -Uhrzeit fest und trägt sie in den Report ein.
     *
     * @param name - Der Name für den neuen Report
     */
    public void createReport(String name, String browser) {
        log.debug("Initializing Report");
        Date testDate = new Date();
        String dateString = (new StringBuilder(dateFormat.format(testDate))).toString();
        String timeString = (new StringBuilder(timeFormat.format(testDate))).toString();
        report = new Report();
        report.setTestName(name);
        report.setExecutionDate(dateString);
        report.setStartTime(timeString);
        report.setBrowser(browser);
        status = false;
        log.debug("Report initialized sucessfully");
    }

    /**
     * Die Metgode reportFailure wird verwendet um einen fehlgeschlagenen Testschritt zu protokollieren.
     * Dafür wird im zugehörigen Report ein passender Step mit dem Resultat aus dem Testfall hinzugefügt.
     * Im Report wird Name des Testschritts, erwartetes Ergebnis, erhaltenes Ergebnis und Status eingetragen.
     *
     * @param name     - Name des Testschritts
     * @param expected - Erwartetes Ergebnis
     * @param actual   - Erhaltenes Ergebnis
     */
    public void reportFailure(String name, String expected, String actual) {
        log.debug("Reporting failure");
        status = true;
        report.setStatus(false);
        report.addStep(new Step(false, name, expected, actual));
        log.debug("Failure reported successfully");
    }


    /**
     * Die Metgode reportSuccess wird verwendet um einen erfolgreichen Testschritt zu protokollieren.
     * Dafür wird im zugehörigen Report ein passender Step mit dem Resultat aus dem Testfall hinzugefügt.
     * Im Report wird Name des Testschritts, erwartetes Ergebnis, erhaltenes Ergebnis und Status eingetragen.
     *
     * @param name     - Name des Testschritts
     * @param expected - Erwartetes Ergebnis
     * @param actual   - Erhaltenes Ergebnis
     */
    public void reportSuccess(String name, String expected) {
        log.debug("Reporting Success");
        status = true;
        report.addStep(new Step(true, name, expected, expected));
        log.debug("Success reported successfully");
    }

    /**
     * Getter um den Status des Reporters zu erhalten
     *
     * @return status - Status des Reporters
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Setter Methode zum Eintragen des Status des Reporters.
     *
     * @param status - Status des Reporters als Boolean
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Getter, der einen bestimmten Step des aktuellen Reports zurück gibt.
     * Die Methode benötigt die Nummer des Steps um ihn im Report identifizieren zu können.
     *
     * @param nr - Nummer des Steps
     * @return step - Der anhand der Nummer identifizierte Step aus dem Report
     */
    public Step getStep(int nr) {
        return report.getStep(nr);
    }

    /**
     * Die Methode printReport() ist dafür zuständig, das Testergebnis ins Logfile zu schreiben.
     * Sie extrahiert die Ergebnisse aus dem zugehörigen Report und schreibt sie dann zeilenweise
     * mit einem FileWriter in das Logfile.
     * Zuerst wird der Header bestehend aus Name, Datum, Startzeit, Endzeit und Status geschrieben.
     * Danach werden die Ergebnisse der einzelnen Testschritte bestehenden aus Status, erwartetes
     * Ergebnis und erhaltenes Ergebnis eingetragen.
     */

    public void printReport() {
        Report printReport = this.report;
        // get report data
        String testName = (printReport.getTestName() == null) ? "" : printReport.getTestName();
        String startTime = (printReport.getStartTime() == null) ? "" : printReport
                .getStartTime();
        String endTime = (printReport.getEndTime() == null) ? "" : printReport.getEndTime();
        int duration = ((Integer) printReport.getDuration() == null) ? 0 : printReport
                .getDuration();
        String executionDate = (printReport.getExecutionDate() == null) ? "" : printReport
                .getExecutionDate();
        Vector<Step> steps = printReport.getSteps();

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

    }

    public Report finalizeReport(String testName) {
        this.report.setTestName(testName);
        Date testDate = new Date();
        String timeString = (new StringBuilder(timeFormat.format(testDate))).toString();
        boolean stat = true;
        // iterate over steps status to compute entire test case status
        Vector<Step> steps = this.report.getSteps();
        if (steps == null || steps.size() == 0) {
            this.report.setStatus(false);
        } else {
            for (int i = 0; i < steps.size(); i++) {
                stat &= steps.elementAt(i).getStatus();
            }
            this.report.setStatus(stat);
        }

        this.report.setEndTime(timeString);
        return report;
    }

	/*public void printReport()
	{
		try {
			log.debug("Starting to print the Report");
			//FileWriter fwDet = new FileWriter(logpath + logfile, true);
			Date testDate = new Date();
			String timeString = (new StringBuilder(timeFormat.format(testDate))).toString();
			String testName = report.getTestName().substring(report.getTestName().lastIndexOf('.')+1);
			report.setEndTime(timeString);
			log.info("****************************");
			log.info("Printing Testresult");
			log.info("Test: " + testName);
			log.info("Browser: " + report.getBrowser());
			log.info("Date: " + report.getExecutionDate());
			log.info("Start: " + report.getStartTime());
			log.info("End: " + report.getEndTime());
			if (report.getSteps() == null) {
				report.setStatus(false);
				log.info("Result: FAILED\r\n");
				log.error("Error, no Steps found\r\n");
			}
			else
			{
				if (report.getStatus() == true)
				{ log.info("Result: PASSED\r\n"); }
				else
				{ log.info("Result: FAILED\r\n"); }
				Vector<Step> steps = report.getSteps();
				for(int i=0; i < steps.size(); i++)
				{
					Step step = steps.get(i);
					log.info("Step " + i + " " + step.getStepName());
					if(step.getStatus() == true)
					{ 
						log.info("***** PASSED *****"); 
					}	
					else
					{ 
						log.info("***** FAILED *****"); 
					}	
					log.info("Expected: " + step.getExpected());
					log.info("Actual: " + step.getActual() + "\r\n");
				}
			}
			log.info("Finished printing Testresult");
			log.info("****************************\r\n");
			log.debug("Finished printing the Report");
		} catch (Exception e) {
			log.error("Error while printing the Report. " + e.getMessage());
		}
	}*/


    /**
     * Getter, der den gearade vom Reporter bearbeiteten Report zurückgibt.
     *
     * @return report - Aktuell vom Reporter bearbeiteter Report
     */
    public Report getReport() {
        return report;
    }
}

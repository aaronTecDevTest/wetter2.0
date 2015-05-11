package de.telekom.pni.rmstest.backend.reporting.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.telekom.pni.rmstest.backend.reporting.Reporter;

/**
 * Eine JUnit Testklasse, die die Methoden der Reporter Klasse überprüft.
 *
 * @author M.Forster
 */
public class Test_Reporter {

    /**
     * Diese Test-Methode überprüft das Hinzufügen eines während einem Testschritt
     * aufgetretenen Fehlers zum Report.
     */
    @Test
    public void testReportFailure() {
        Reporter reporter = new Reporter();
        reporter.reportFailure("name", "expected", "actual");
        assertEquals("name", reporter.getStep(0).getStepName());
        assertEquals("expected", reporter.getStep(0).getExpected());
        assertEquals("actual", reporter.getStep(0).getActual());
        assertEquals(false, reporter.getStep(0).getStatus());
        assertNotNull(reporter.getReport().getSteps());
    }


    /**
     * Diese Test-Methode überprüft das Hinzufügen einer Erfolgsmeldung zu einem erfolgreich
     * durchgeführten Testschritts zum Report.
     */
    @Test
    public void testReportSuccess() {
        Reporter reporter = new Reporter();
        reporter.reportSuccess("name", "expected");
        assertEquals("name", reporter.getStep(0).getStepName());
        assertEquals("expected", reporter.getStep(0).getExpected());
        assertEquals("actual", reporter.getStep(0).getActual());
        assertEquals(true, reporter.getStep(0).getStatus());
        assertNotNull(reporter.getReport().getSteps());
    }

    /**
     * Diese Test-Methode überprüft die Erzeugung eines Reporter Objekts.
     */
    @Test
    public void testReporter_Creation() {
        Reporter reporter = new Reporter();
        assertNotNull(reporter.getReport());
        assertNotNull(reporter);
        assertEquals(false, reporter.getStatus());
    }

    /**
     * Diese Test-Merhode überprüft die Erzeugung eines Report über die Methode createReport des Reporters.
     */
    @Test
    public void testCreateReport() {
        Reporter reporter = new Reporter();
        reporter.reportSuccess("name", "expeted");
        reporter.createReport("newName", "FF");
        assertNull(reporter.getReport().getSteps());
        assertEquals("newName", reporter.getReport().getTestName());
        assertEquals(true, reporter.getReport().getStatus());
        assertEquals(false, reporter.getStatus());
    }

    /**
     * Diese Test-Methode überprüft das Verhalten bei der Ausgabe eines leeren Reports.
     */
    @Test
    public void testPrintReport_emptyReport() {
        Reporter reporter = new Reporter();
        try {
            reporter.printReport();
        } catch (Exception ex) {
            fail("Exception thrown while printing an empty Report. " + ex.getMessage());
        }
    }

    /**
     * Diese Test-Methode überprüft die Ausgabe eine Reports mit einem erfolgreichen Testschritt.
     */
    @Test
    public void testPrintReport_oneSuccesfullStep() {
        Reporter reporter = new Reporter();
        reporter.reportSuccess("Name", "expected");
        try {
            reporter.printReport();
        } catch (Exception ex) {
            fail("Exception thrown while printing the Report. " + ex.getMessage());
        }
    }

    /**
     * Diese Test-Methode überprüft die Ausgabe eine Reports mit einem fehlgeschlagenen Testschritt.
     */
    @Test
    public void testPrintReport_oneFailedStep() {
        Reporter reporter = new Reporter();
        reporter.reportFailure("Name", "expected", "actual");
        try {
            reporter.printReport();
        } catch (Exception ex) {
            fail("Exception thrown while printing the Report. " + ex.getMessage());
        }
    }
}

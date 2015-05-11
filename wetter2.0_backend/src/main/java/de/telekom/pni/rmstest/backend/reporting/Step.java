package de.telekom.pni.rmstest.backend.reporting;

/**
 * Die Klasse wurde vom ursprünglichen Entwickler des Frameworks erstellt und wird ohne Änderung
 * weiterverwendet.
 *
 * @author A.Roth
 */
public class Step {

    private String stepName = null;
    private String expected = null;
    private String actual = null;
    private boolean status;

    public Step(boolean status, String stepName, String expected,
                String actual) {
        this.stepName = stepName;
        this.expected = expected;
        this.actual = actual;
        this.status = status;
    }

    public Step(boolean status, String stepName, String expected,
                String actual, String description) {
        this.stepName = stepName;
        this.expected = expected;
        this.actual = actual;
        this.status = status;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}

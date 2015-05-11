package de.telekom.pni.rmstest.frontend.exceptions;

/**
 * Eine spezielle Exception die in bestimmten Bereichen der Benutzeroberfläche geworfen
 * wird. Diese Klasse wurde vom ursprünglichen Entwickler des Frameworks erstellt und
 * wird ohne Änderung weiterverwendet.
 *
 * @author A.Roth
 */
public class TestRunnerInstantiationException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TestRunnerInstantiationException(String msg) {
        this.message = msg;
    }
}

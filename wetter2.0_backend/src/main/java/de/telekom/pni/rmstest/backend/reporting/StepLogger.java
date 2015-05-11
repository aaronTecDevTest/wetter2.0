package de.telekom.pni.rmstest.backend.reporting;

import java.util.Vector;

/**
 * Die Klasse wurde vom ursprünglichen Entwickler des Frameworks erstellt und wird ohne Änderung
 * weiterverwendet.
 *
 * @author A.Roth
 */
public class StepLogger {

    private Vector<Step> steps;

    public StepLogger() {
        this.steps = new Vector<Step>();
    }

    public void logStep(Step step) {
        steps.add(step);
    }

    public Vector<Step> getSteps() {
        return steps;
    }

}

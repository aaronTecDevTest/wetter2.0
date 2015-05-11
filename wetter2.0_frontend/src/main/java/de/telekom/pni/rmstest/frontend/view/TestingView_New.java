package de.telekom.pni.rmstest.frontend.view;

import org.eclipse.swt.widgets.Shell;

import de.telekom.pni.rmstest.backend.config.TestrunConfiguration_New;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;

/**
 * Bei TestingView.java handelt es sich um ein Interface. Es gibt die Methoden vor, die
 * von einer Klassem die eine TestingView implemnentieren wollen implementiert werden müssen.
 * Die Klasse wurde vom ursprünglichen Entwickler des Frameworks erstellt und wird ohne Anpassung
 * weiterverwendet.
 *
 * @author A.Roth
 */
public interface TestingView_New {

    Shell getFrame();

    void setTestSetLabel(String string);

    void notifyTestrunCompleted();

    void notifyTestrunStarted();

    void notifyDBOffline();

    void notifyRunconfigurationAdded(TestrunConfiguration_New configuration,
                                     boolean edited);

    TestSet getDisplayedTestSet();

    void notifyTestrunStarted(int confID);

    void notifyTestrunCompleted(int confID);

}

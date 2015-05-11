/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4434;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;

/**
 * @author a.kutekidila
 */
public class TC2858_Favoritenmodul_Prüfen_der_Überschrift_Wettervorhersage extends GenericTest_New {

    /**
     *
     */
    public TC2858_Favoritenmodul_Prüfen_der_Überschrift_Wettervorhersage() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {
        logFailureCheckpoint("miöö", "null", "null");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2858_Favoritenmodul_Prüfen_der_Überschrift_Wettervorhersage test = new TC2858_Favoritenmodul_Prüfen_der_Überschrift_Wettervorhersage();
        test.before();
        test.runTest();
        test.after();

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.GenericTest_New#setRunningConfiguration(de.telekom.pni.rmstest.backend.config.RunningConfiguration_New)
     */
    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }

}

/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5480;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;


/**
 * @author a.kutekidila
 */
public class TC2698_Suchbegriffe_mit_gleiche_Gewichtung extends GenericTest_New {

    //private GlobalVar globalVar = null;
    @Override
    public void runTest() {
        String fehler = "Test erfolgreich!";
        this.logSuccessCheckpoint(fehler, "");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2698_Suchbegriffe_mit_gleiche_Gewichtung test = new TC2698_Suchbegriffe_mit_gleiche_Gewichtung();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

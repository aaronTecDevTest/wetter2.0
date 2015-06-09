/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US9998;


import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;


/**
 * @author a.kutekidila
 */
public class TC9998_Manuell_Testing extends GenericTest_New {

    @Override
    public void runTest() {
        String fehler = "Anforderung bitte manuell testen!";
        this.logSuccessCheckpoint(fehler, "");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC9998_Manuell_Testing test = new TC9998_Manuell_Testing();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US9999;


import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;


/**
 * @author a.kutekidila
 */
public class TC9999_Not_Implemented extends GenericTest_New {

    @Override
    public void runTest() {

        String fehler = "Anforderung ist noch nicht umgesetzt!";
        this.logFailureCheckpoint(fehler, "", "");

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC9999_Not_Implemented test = new TC9999_Not_Implemented();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

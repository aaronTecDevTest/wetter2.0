/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US9995;


import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;


/**
 * @author a.kutekidila
 */
public class TC9995_External_Test extends GenericTest_New {

    @Override
    public void runTest() {

        String fehler = "Anforderung wird von einen anderen Tester/Stakeholder getestet";
        logSuccessCheckpoint("User-Story:", fehler);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC9995_External_Test test = new TC9995_External_Test();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

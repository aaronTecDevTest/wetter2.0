/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4436;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;

/**
 * @author a.kutekidila
 */
public class TC2770_Suche_nach_Wetterkarten extends GenericTest_New {

    /**
     *
     */
    public TC2770_Suche_nach_Wetterkarten() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {
        logFailureCheckpoint("1", "nicht umgesetzt", "nicht umgesetzt");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2770_Suche_nach_Wetterkarten test = new TC2770_Suche_nach_Wetterkarten();
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

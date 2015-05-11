/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4299;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;

/**
 * @author a.kutekidila
 */
public class TC3104_SEO_Text_Ausland_Orte extends GenericTest_New {

    /**
     *
     */
    public TC3104_SEO_Text_Ausland_Orte() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {
        logFailureCheckpoint("1", "No Implementation", "Manuell Testing");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC3104_SEO_Text_Ausland_Orte test = new TC3104_SEO_Text_Ausland_Orte();
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

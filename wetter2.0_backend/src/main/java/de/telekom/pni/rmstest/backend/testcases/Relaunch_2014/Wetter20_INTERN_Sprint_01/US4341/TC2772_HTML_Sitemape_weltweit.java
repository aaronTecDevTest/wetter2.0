/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4341;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;

/**
 * @author a.kutekidila
 */
public class TC2772_HTML_Sitemape_weltweit extends GenericTest_New {

    /**
     *
     */
    public TC2772_HTML_Sitemape_weltweit() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {
        logFailureCheckpoint("1", "Not Implemented", "External testing Test");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2772_HTML_Sitemape_weltweit test = new TC2772_HTML_Sitemape_weltweit();
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

/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4341;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;

/**
 * @author a.kutekidila
 */
public class TC3166_XML_Sitemaps extends GenericTest_New {

    /**
     *
     */
    public TC3166_XML_Sitemaps() {

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
        TC3166_XML_Sitemaps test = new TC3166_XML_Sitemaps();
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

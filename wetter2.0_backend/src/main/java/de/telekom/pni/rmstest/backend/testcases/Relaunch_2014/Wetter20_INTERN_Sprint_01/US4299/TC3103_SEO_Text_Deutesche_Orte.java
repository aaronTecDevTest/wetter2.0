package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4299;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;

/**
 * @author a.kutekidila
 */
public class TC3103_SEO_Text_Deutesche_Orte extends GenericTest_New {

    /**
     *
     */
    public TC3103_SEO_Text_Deutesche_Orte() {
        logFailureCheckpoint("1", "No Implementation", "Manuell Testing");
    }

    @Override
    public void runTest() {
        String fehler = "Bitte manuell Test!!";
        this.logFailureCheckpoint(fehler, null, null);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC3103_SEO_Text_Deutesche_Orte test = new TC3103_SEO_Text_Deutesche_Orte();
        test.before();
        test.runTest();
        test.after();

    }


    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }

}

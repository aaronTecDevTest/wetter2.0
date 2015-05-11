/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5480;

//import java.util.List;

//import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
//import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */
public class TC2701_Arheilgen_Datenbankranking extends GenericTest_New {
    //private GlobalVar globalVar = null;

    @Override
    public void runTest() {
        String fehler = "Anforderung bitte manuell testen!";
        this.logFailureCheckpoint(fehler, "", "");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2701_Arheilgen_Datenbankranking test = new TC2701_Arheilgen_Datenbankranking();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }

}

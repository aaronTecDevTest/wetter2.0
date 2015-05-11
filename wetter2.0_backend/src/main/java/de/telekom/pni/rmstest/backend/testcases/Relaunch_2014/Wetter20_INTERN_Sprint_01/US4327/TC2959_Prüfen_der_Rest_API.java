package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4327;


import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/*
 * Prüfen, ob die CSS-Formatierun des Komplette AutoSuggess mit Hilfe von Bildervergleich. Hier wird Sikuli verwendet.
 * Es wird unterschieden zwischen T-Online und Wetter.Info Seite.
 */
public class TC2959_Prüfen_der_Rest_API extends GenericTest_New {
    private GlobalVar globalVar = null;


    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        //Step 1
        getBrowser();

        //Step 3
        try {
            if (checkRestSuche()) {
                logSuccessCheckpoint("RestSuche", "Seite gefunden, Schnittstelle verfügbar");
            } else {
                logFailureCheckpoint("RestSuche", "Seite nicht gefunden", "Body = null");
            }

            if (checkRestWetterdaten()) {
                logSuccessCheckpoint("RestWetterdaten", "Seite gefunden, Schnittstelle verfügbar");
            } else {
                logFailureCheckpoint("RestWetterdaten", "Seite nicht gefunden", "Body = null");
            }
        } catch (Exception e) {
            logFailureCheckpoint("Rest", "null ", e.getStackTrace().toString());
        }
    }

    private boolean checkRestSuche() {
        boolean check = true;
        WebElement we = null;

        navigate(globalVar.__Schnittstelle_Wirk__);
        we = getBrowser().findElement(By.tagName("body"));

        return we.getText().isEmpty() ? false : true;
    }

    private boolean checkRestWetterdaten() {
        boolean check = true;
        WebElement we = null;

        navigate(globalVar.__Schnitstelle_Wetterdaten_Showall__);
        we = getBrowser().findElement(By.tagName("body"));

        return we.getText().isEmpty() ? false : true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2959_Prüfen_der_Rest_API test = new TC2959_Prüfen_der_Rest_API();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

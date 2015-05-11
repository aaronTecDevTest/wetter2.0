package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4327;

import java.io.File;
import java.util.EmptyStackException;

import org.sikuli.api.*;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import de.telekom.pni.rmstest.backend.global.GlobalVarTOnline_Preview;
import de.telekom.pni.rmstest.backend.global.GlobalVarWetterInfo;


/**
 * Prüfen, ob die CSS-Formatierun des Komplette AutoSuggess mit Hilfe von Bildervergleich. Hier wird Sikuli verwendet.
 * Es wird unterschieden zwischen T-Online und Wetter.Info Seite.
 */
public class TC2870_Prüfen_des_Look_Feel_Visualiseirung_Formatierung extends GenericTest_New {
    private GlobalVar globalVar = null;
    public ScreenRegion screenRegion = new DesktopScreenRegion(1);
    public String picOrdner = "U:/01_Dev/Wetter_2.0/Testcode/workspace/wetter2.0/wetter2.0_backend/src/main/resources/sikuli.images/";

    public Target wetterInfoSuchfeld = new ImageTarget(new File(picOrdner + "wetter_info/TC2870_Suchfeld.PNG"));
    public Target wetterInfoAutoSuggest = new ImageTarget(new File(picOrdner + "wetter_info/TC2870_AutoSuggest.PNG"));

    public Target tonlineSuchfeld = new ImageTarget(new File(picOrdner + "t_online/TC2870_Suchfeld.PNG"));
    public Target tonlineAutoSuggest = new ImageTarget(new File(picOrdner + "t_online/TC2870_AutoSuggest.PNG"));

    @Override
    public void runTest() {
        globalVar = getGlobalVar(); // GlobalVarTOnline oder
        boolean testResult = false;
        RunningConfiguration_New r = null;
        r = getRunningConfiguration();

        //Step 1
        getBrowser();

        //Step 1
        navigate(globalVar.__StartSeiteFavoriten__);

        //Step 2
        if (r != null && (globalVar instanceof GlobalVarWetterInfo || globalVar instanceof GlobalVarTOnline_Preview)) {
            testResult = (this.checkAutoSuggest("SuchFeld", "", tonlineSuchfeld) &&
                    this.checkAutoSuggest("AutoSuggest", "Darmstadt", tonlineAutoSuggest));

        } else {

            testResult = (this.checkAutoSuggest("SuchFeld", "", wetterInfoSuchfeld) &&
                    this.checkAutoSuggest("AutoSuggest", "Darmstadt", wetterInfoAutoSuggest));
        }


        if (testResult) {

            this.logSuccessCheckpoint("Testergebnis:", "Alle CSS-Einstellungen sind korrekt");
        } else {
            this.logFailureCheckpoint("Testergebnis:", "Fehler in CSS-Ergebnis ", "Aktuell: Ergebnisprüfen");
        }
    }


    private boolean checkAutoSuggest(String targetString, String searchString, Target pic) {
        boolean check = false;

        try {
            if (!searchString.isEmpty()) {
                this.setInputFeldValue(globalVar._INPUT_BOX, searchString);
            }

            ScreenRegion result = screenRegion.wait(pic, 2000);

            if (result == null) {
                throw new EmptyStackException();
            } else {
                // Display "Hello World" next to the found target for 3 seconds
                Canvas canvas = new DesktopCanvas(1);
                canvas.addLabel(result, targetString).display(3);
                canvas.addBox(result).display(3);

                // Click the center of the found target
                //Mouse mouse = new DesktopMouse();
                //mouse.click(result.getCenter());
                check = true;
            }
        } catch (Exception e) {
            check = false;
            //System.err.println(e.getStackTrace());
        }
        return check;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2870_Prüfen_des_Look_Feel_Visualiseirung_Formatierung test = new TC2870_Prüfen_des_Look_Feel_Visualiseirung_Formatierung();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

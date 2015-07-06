package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4327;


import java.io.File;
import java.util.EmptyStackException;

import org.sikuli.api.*;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import de.telekom.pni.rmstest.backend.global.GlobalVarTOnline;
import de.telekom.pni.rmstest.backend.global.GlobalVarTOnline_Preview;

/**
 * Prüfen, ob die CSS-Formatierun des AutoSuggest (Balken) mit Hilfe von Bildervergleich. Hier wird Sikuli verwendet.
 * Es wird unterschieden zwischen T-Online und Wetter.Info Seite.
 */
public class TC2869_Prüfen_des_Look_Feel_des_AutoSuggest extends GenericTest_New {
    private GlobalVar globalVar = null;
    public ScreenRegion screenRegion = new DesktopScreenRegion(1, 0, 0, 1920, 1080);
    public String picOrdner = "../wetter2.0/wetter2.0_backend/src/main/resources/sikuli.images/";
   // public String picOrdner = "C:\\Users\\a.kutekidila\\Dev\\GitHub\\wetter2.0\\wetter2.0_backend\\src\\main\\resources\\sikuli.images";

    public Target wetterInfoBest = new ImageTarget(new File(picOrdner + "wetter_info/TC2869_Best_Treffer.PNG"));
    public Target wetterInfoDeutsch = new ImageTarget(new File(picOrdner + "wetter_info/TC2869_Deutschland.PNG"));
    public Target wetterWelt = new ImageTarget(new File(picOrdner + "wetter_info/TC2869_Welt.PNG"));

    public Target tonlineInfoBest = new ImageTarget(new File(picOrdner + "wetter_info/TC2869_Best_Treffer.PNG"));
    public Target tonlineInfoDeutsch = new ImageTarget(new File(picOrdner + "wetter_info/TC2869_Deutschland.PNG"));
    public Target tonlineWelt = new ImageTarget(new File(picOrdner + "wetter_info/TC2869_Welt.PNG"));

    @Override
    public void runTest() {

        globalVar = getGlobalVar();
        boolean testResult = false;
        RunningConfiguration_New r = null;
        r = getRunningConfiguration();

        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3
        if (r != null && (globalVar instanceof GlobalVarTOnline || globalVar instanceof GlobalVarTOnline_Preview)) {
            testResult = (checkTitelOnClickAbel("Best", tonlineInfoBest) &&
                    checkTitelOnClickAbel("Deutschland", tonlineInfoDeutsch) &&
                    checkTitelOnClickAbel("Weltweid", tonlineWelt));
        } else {
            testResult = (checkTitelOnClickAbel("Weltweid", wetterWelt) &&
                    checkTitelOnClickAbel("Deuchland", wetterInfoDeutsch) &&
                    checkTitelOnClickAbel("Best", wetterInfoBest));
        }

        if (testResult) {

            this.logSuccessCheckpoint("Testergebnis:", "Alle CSS-Einstellungen sind korrekt");
        } else {
            this.logFailureCheckpoint("Testergebnis:", "Fehler in CSS-Ergebnis ", "Aktuell: Ergebnisprüfen ");
        }
    }

    private boolean checkTitelOnClickAbel(String elementName, Target imageTarget) {
        boolean check = false;
        String currtenUrl = getBrowser().getCurrentUrl();
        try {
            getByXPath(globalVar._INPUT_BOX).clear();

            setInputFeldValue(globalVar._INPUT_BOX, "Darmstadt");
            ScreenRegion result = screenRegion.wait(imageTarget, 2000);

            if (result == null) {
                throw new EmptyStackException();
            } else {
                // Display "Hello World" next to the found target for 3 seconds
                Canvas canvas = new DesktopCanvas(1);//,0,0,1920,1080);
                canvas.addLabel(result, elementName).display(1);
                canvas.addBox(result).display(1);

                // Click the center of the found target
                Mouse mouse = new DesktopMouse();
                mouse.click(result.getCenter());

                if (currtenUrl.contains(getBrowser().getCurrentUrl())) {
                    check = true;
                }
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
        TC2869_Prüfen_des_Look_Feel_des_AutoSuggest test = new TC2869_Prüfen_des_Look_Feel_des_AutoSuggest();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}
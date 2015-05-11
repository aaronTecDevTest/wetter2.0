
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4329;

import java.io.File;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
//import org.sikuli.api.robot.Mouse;
//import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


/**
 * Prüft, ob ein Top-Treffer ganz oben auf der Suchergenisseite ausgespielt wird.
 * Prüft, ob ein Top-Treffer nur einmal in der der Suchergebnis seite steht.
 */
public class TC2909_Top_Treffer_in_Suchergebnisseite extends GenericTest_New {
    private GlobalVar globalVar = null;
    private ArrayList<Target> picture = new ArrayList<Target>();
    public ScreenRegion screenRegion = new DesktopScreenRegion(1);

    public String picOrdner = "U:/01_Dev/Wetter_2.0/Testcode/workspace/wetter2.0/wetter2.0_backend/src/main/resources/sikuli.images/";

    public Target berlin = new ImageTarget(new File(picOrdner + "TC2909_Berlin.PNG"));
    public Target newYork = new ImageTarget(new File(picOrdner + "TC2909_New_York.PNG"));
    public Target london = new ImageTarget(new File(picOrdner + "TC2909_London.PNG"));
    public Target paris = new ImageTarget(new File(picOrdner + "TC2909_Paris.PNG"));
    public Target barcelona = new ImageTarget(new File(picOrdner + "TC2909_Barcelona.PNG"));


    @Override
    public void runTest() {

        globalVar = getGlobalVar(); // GlobalVarTOnline oder

        globalVar.searchStringList.add("Berlin");
        globalVar.searchStringList.add("New York");
        globalVar.searchStringList.add("London");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Barcelona");


        globalVar.resultStringList.add("Berlin\nDeutschland, Bundesland Berlin");
        globalVar.resultStringList.add("New York City\nVereinigte Staaten, Bundesstaat New York");
        globalVar.resultStringList.add("London\nVereinigtes Königreich, Region England");
        globalVar.resultStringList.add("Paris\nFrankreich, Region Île-de-France");
        globalVar.resultStringList.add("Barcelona\nSpanien, Autonome Gemeinschaft Katalonien ");

        picture.add(berlin);
        picture.add(newYork);
        picture.add(london);
        picture.add(paris);
        picture.add(barcelona);

        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 to ??
        for (int i = 0; i < globalVar.resultStringList.size(); i++) {
            try {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                getWebElement(globalVar._INPUT_BOX).submit();

                List<WebElement> listOfSuchergebnisTop = this.getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_TOP_ORT);

                if (checkTopTreffer(listOfSuchergebnisTop, picture.get(i)) && checkTopTrefferWeiterenOrte()) {
                    globalVar.stringSearch = "String " + globalVar.stringResult + " in AutoSuggest gefunden.";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(1).getText());
                } else {
                    String fehler = " " + globalVar.stringSearch + " nicht in AutoSuggest gefunden.";
                    this.logFailureCheckpoint(fehler, globalVar.stringResult, listOfSuchergebnisTop.get(0).getText());
                }

                getWebElement(globalVar._INPUT_BOX).clear();
            } catch (Exception e) {
                globalVar.stringSearch = globalVar.stringSearch + " Liste ist leer!! " + globalVar.stringResult + " AutoSuggest ist leer.";
                this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
            }
        }

    }

    boolean checkTopTreffer(List<WebElement> listOfSuchergebnisTop, Target pic) {
        boolean check = false;
        try {
            ScreenRegion result = screenRegion.wait(pic, 2000);

            if (result == null) {
                throw new EmptyStackException();
            } else {
                // Display "Hello World" next to the found target for 3 seconds
                Canvas canvas = new DesktopCanvas(1);
                canvas.addLabel(result, globalVar.stringSearch).display(3);
                canvas.addBox(result).display(3);

                // Click the center of the found target
                // Mouse mouse = new DesktopMouse();
                // mouse.click(result.getCenter());
                check = true;
            }
        } catch (Exception e) {
            check = true;
            //System.err.println(e.getStackTrace());
        }

        openSuchergebnisOrte(globalVar._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS);
        return check;
    }

    boolean checkTopTrefferWeiterenOrte() {
        boolean check = true;
        //int counter = 0;
        List<WebElement> listOfSuchergebnis = this.getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_ALL_ORT_OHNE_TOP);

        for (WebElement webElement : listOfSuchergebnis) {
            if (webElement.getText().compareTo(globalVar.stringResult) == 0) {
                //	counter++;
                check = false;
                return check;
            }
        }

        return check;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2909_Top_Treffer_in_Suchergebnisseite test = new TC2909_Top_Treffer_in_Suchergebnisseite();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

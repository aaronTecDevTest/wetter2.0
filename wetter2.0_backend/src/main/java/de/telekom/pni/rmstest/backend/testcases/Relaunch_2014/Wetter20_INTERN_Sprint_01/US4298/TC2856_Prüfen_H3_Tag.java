/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4298;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */
public class TC2856_Prüfen_H3_Tag extends GenericTest_New {
    GlobalVar globalVar = null;


    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("64283 Darmstadt");
        globalVar.searchStringList.add("64673 Zwingenberg (Bergstraße)");
        globalVar.searchStringList.add("27327 Kleinenborstel (Martfeld)");
        globalVar.searchStringList.add("12683 Berlin Biesdorf");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Postkamp (Altenholz)");
        globalVar.searchStringList.add("New York");

        globalVar.resultStringList.add("Das aktuelle Wetter für Darmstadt (Hessen)");
        globalVar.resultStringList.add("Das aktuelle Wetter für Darmstadt (Hessen)");
        globalVar.resultStringList.add("Das aktuelle Wetter für Darmstadt (Hessen)");
        globalVar.resultStringList.add("Das aktuelle Wetter für Darmstadt (Hessen)");
        globalVar.resultStringList.add("Das aktuelle Wetter für Darmstadt (Hessen)");
        globalVar.resultStringList.add("Das aktuelle Wetter für Darmstadt (Hessen)");
        globalVar.resultStringList.add("Das aktuelle Wetter für Darmstadt (Hessen)");

        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 ....
        try {
            for (int i = 0; i < 1; i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(500);
                getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);
                setInputFeldValue(globalVar._INPUT_BOX, "\n");

                WebElement h3Tag = getWebElement(globalVar._BOX_WETTER_h3);

                if (checkH3Tag(h3Tag)) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");//listOfAutoSuggest.get(i).getText());
                }
                navigate(globalVar.__StartSeiteFavoriten__);
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " AD-Elements could be not found in Web";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    public boolean checkH3Tag(WebElement we) {
        boolean check = false;

        if (we.getText().contains(globalVar.resultStringList.get(0))) {
            check = true;
        }
        return check;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2856_Prüfen_H3_Tag test = new TC2856_Prüfen_H3_Tag();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }
}

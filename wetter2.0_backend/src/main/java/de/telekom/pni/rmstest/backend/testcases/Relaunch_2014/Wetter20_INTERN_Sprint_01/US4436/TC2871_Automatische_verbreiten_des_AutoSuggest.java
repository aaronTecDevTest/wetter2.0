package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4436;


import java.util.List;
//import java.util.Random;


import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/*
 * Prüfen, ob die Breite des Suchfeld+AutoSuggest vergrößert wird, wenn der Suchstring über 
 * den Suchfeldgröße hinausgeht.
 */

public class TC2871_Automatische_verbreiten_des_AutoSuggest extends GenericTest_New {
    private GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 to ??
        try {
            globalVar.stringSearch = "Rom";
            setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
            pauseTest(1000);
            List<WebElement> listOfAutoSugges1 = getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);
            int widgth1 = listOfAutoSugges1.get(2).getSize().getWidth();
            getWebElement(globalVar._INPUT_BOX).clear();

            //globalVar.stringSearch = "Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch";
            globalVar.stringSearch = "Llanfairpwllgwyng";
            setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
            pauseTest(1000);
            List<WebElement> listOfAutoSugges2 = getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);
            int widgth2 = listOfAutoSugges2.get(0).getSize().getWidth();
            getWebElement(globalVar._INPUT_BOX).clear();

            if (widgth2 >= widgth1) {

                this.logSuccessCheckpoint("Breite AutoSuggest:", "AutoSuggest-Breite ist größer als " + widgth2 + " Breiter.");
            } else {
                this.logFailureCheckpoint("Breite AutoSuggest:", "AutoSuggest-Breite ist > " + widgth2, "Aktuell " + widgth1);
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " Liste ist leer!! " + globalVar.stringResult + " AutoSuggest ist leer.";
            this.logFailureCheckpoint(globalVar.stringSearch, "AutoSuggest Breite", "null");
        }
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2871_Automatische_verbreiten_des_AutoSuggest test = new TC2871_Automatische_verbreiten_des_AutoSuggest();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

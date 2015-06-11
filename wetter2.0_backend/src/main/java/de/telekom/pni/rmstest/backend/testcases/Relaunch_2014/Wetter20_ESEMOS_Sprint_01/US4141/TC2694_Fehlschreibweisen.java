package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_ESEMOS_Sprint_01.US4141;


import java.util.List;

//import java.util.Random;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


/**
 * Prüft, ob die falschen eingegebene String (Orte) eine Ergebnis liefertn.
 */

public class TC2694_Fehlschreibweisen extends GenericTest_New {
    private GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("München");
        globalVar.searchStringList.add("Munchen");
        globalVar.searchStringList.add("Frankurt");
        globalVar.searchStringList.add("Frank furt");
        globalVar.searchStringList.add("Hanniver");
        globalVar.searchStringList.add("Koel");
        globalVar.searchStringList.add("Zweibrück");
        globalVar.searchStringList.add("Sarbrücken");
        globalVar.searchStringList.add("Wiesdaden");
        globalVar.searchStringList.add("Stut tgart");
        globalVar.searchStringList.add("Dressden");
        globalVar.searchStringList.add("Eßen");
        globalVar.searchStringList.add("Heßen");
        globalVar.searchStringList.add("hamburg");
        globalVar.searchStringList.add("Lübeca");
        globalVar.searchStringList.add("Düsburg");

        globalVar.resultStringList.add("München");
        globalVar.resultStringList.add("München");
        globalVar.resultStringList.add("Frankfurt am Main");
        globalVar.resultStringList.add("Frankfurt am Main");
        globalVar.resultStringList.add("Hannover");
        globalVar.resultStringList.add("Köln");
        globalVar.resultStringList.add("Zweibrücken");
        globalVar.resultStringList.add("Saarbrücken");
        globalVar.resultStringList.add("Wiesbaden");
        globalVar.resultStringList.add("Stuttgart");
        globalVar.resultStringList.add("Dresden");
        globalVar.resultStringList.add("Essen");
        globalVar.resultStringList.add("Hessen");
        globalVar.resultStringList.add("Hamburg");
        globalVar.resultStringList.add("Lübeck");
        globalVar.resultStringList.add("Duisburg");

        //Um IE zu testen!!
       // setRunningConfiguration(new RunningConfiguration_New("CH", globalVar.__StartSeiteFavoriten__));

        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);


        //Step 3 to

        for (int i = 0; i < globalVar.resultStringList.size(); i++) {
            try {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

				/*
			 	* Input
			 	*/
                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                /**
                 * Result
                 */
                pauseTest(2000);

                List<WebElement> listOfAutoSuggest = getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);


                if (checkStringInList(listOfAutoSuggest, globalVar.stringResult)) {
                    globalVar.stringSearch = "String " + globalVar.stringResult + " in AutoSuggest gefunden.";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    String fehler = " " + globalVar.stringSearch + " nicht in AutoSuggest gefunden.";
                    logFailureCheckpoint(fehler, globalVar.stringResult, globalVar.stringResult);
                }
                getWebElement(globalVar._INPUT_BOX).clear();
            } catch (Exception e) {
                globalVar.stringSearch = globalVar.stringSearch + " Liste ist leer!! " + globalVar.stringResult + " AutoSuggest ist leer.";
                this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
            }
        }

    }

    public boolean checkStringInList(List<WebElement> listOfAutoSuggest, String search) {
        boolean check = false;

        for (WebElement webElement : listOfAutoSuggest) {

            String elementText = webElement.getText();
            if (elementText.contains(search)) {
                check = true;
                break;
            }
        }
        return check;
    }

    /*
     * Main
     */
    public static void main(String[] args) {
        TC2694_Fehlschreibweisen test = new TC2694_Fehlschreibweisen();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

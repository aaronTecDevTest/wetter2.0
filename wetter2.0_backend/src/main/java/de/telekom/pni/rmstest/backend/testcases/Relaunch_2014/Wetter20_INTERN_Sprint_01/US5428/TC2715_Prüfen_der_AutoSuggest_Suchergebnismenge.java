package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5428;


import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/*
 * Prüft, ob die Anzahl der ausgespielten Orte in AutoSuggest laut ANF eingehaltete wird.
 */

public class TC2715_Prüfen_der_AutoSuggest_Suchergebnismenge extends GenericTest_New {
    private GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("Barcelona");
        globalVar.searchStringList.add("Frankfurt Main");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Köln");
        globalVar.searchStringList.add("London");
        globalVar.searchStringList.add("Los Angeles");
        globalVar.searchStringList.add("New York");
        globalVar.searchStringList.add("Nizza");
        globalVar.searchStringList.add("Kopenhagen");
        globalVar.searchStringList.add("Tokyo");


        globalVar.resultStringList.add("11");
        globalVar.resultStringList.add("11");
        globalVar.resultStringList.add("11");
        globalVar.resultStringList.add("14");
        globalVar.resultStringList.add("11");
        globalVar.resultStringList.add("11");
        globalVar.resultStringList.add("11");
        globalVar.resultStringList.add("6");
        globalVar.resultStringList.add("5");
        globalVar.resultStringList.add("3");

        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 to ??
        try {
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                this.pauseTest(1000);

                List<WebElement> listOfAutoSuggest = this.getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);
                if (checkAnzahlElementInAutoSuggest(listOfAutoSuggest, Integer.valueOf(globalVar.stringResult))) {
                    globalVar.stringSearch = "String " + globalVar.stringResult + " in AutoSuggest gefunden.";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(1).getText());
                } else {
                    String fehler = " " + globalVar.stringSearch + " nicht in AutoSuggest gefunden.";
                    this.logFailureCheckpoint(fehler, globalVar.stringResult, listOfAutoSuggest.get(1).getText());
                }
                getWebElement(globalVar._INPUT_BOX).clear();
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " Liste ist leer!! " + globalVar.stringResult + " AutoSuggest ist leer.";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    public static void main(String[] args) {
        TC2715_Prüfen_der_AutoSuggest_Suchergebnismenge test = new TC2715_Prüfen_der_AutoSuggest_Suchergebnismenge();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }

    public boolean checkAnzahlElementInAutoSuggest(List<WebElement> listOfAutoSuggest, int anzahlVonElemente) {
        boolean check = false;
        int topDeutschlandWelt = listOfAutoSuggest.size();

        if (topDeutschlandWelt == anzahlVonElemente)
            check = true;
        return check;
    }
}

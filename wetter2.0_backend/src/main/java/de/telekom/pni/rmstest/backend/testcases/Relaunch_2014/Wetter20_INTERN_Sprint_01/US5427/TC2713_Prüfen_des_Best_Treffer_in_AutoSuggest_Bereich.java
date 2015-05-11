package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5427;


import java.util.List;

import org.openqa.selenium.WebElement;
import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/*
 * Prüf, ob die Top-Treffer nur in dem Bereich Best Ore " ausgespielt wird. Unter "Orte in Deutschland"
 * und "Orte weltweit" sollte es nicht mehr auftreten.
 */

public class TC2713_Prüfen_des_Best_Treffer_in_AutoSuggest_Bereich extends GenericTest_New {
    private GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("Barcelona");
        globalVar.searchStringList.add("Frankfurt Main");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Ankara");
        globalVar.searchStringList.add("London");
        globalVar.searchStringList.add("Rio");
        globalVar.searchStringList.add("New York");
        globalVar.searchStringList.add("Nizza");
        globalVar.searchStringList.add("Kopenhagen");
        globalVar.searchStringList.add("Tokyo");


        globalVar.resultStringList.add("Barcelona (Spanien, Katalonien)");
        globalVar.resultStringList.add("Frankfurt am Main (Deutschland, Hessen)");
        globalVar.resultStringList.add("Paris (Frankreich, Île-de-France)");
        globalVar.resultStringList.add("Ankara (Türkei, Ankara)");
        globalVar.resultStringList.add("London (Vereinigtes Königreich, England)");
        globalVar.resultStringList.add("Rio de Janeiro (Brasilien, Rio de Janeiro)");
        globalVar.resultStringList.add("New York City (Vereinigte Staaten, New York)");
        globalVar.resultStringList.add("Nizza (Frankreich, Provence-Alpes-Côte d’Azur)");
        globalVar.resultStringList.add("Kopenhagen (Dänemark, Hovedstaden)");
        globalVar.resultStringList.add("Tokio (Japan, Tokio)");

        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 to ??
        try {
            for (int i = 0; i < globalVar.resultStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                this.pauseTest(1000);

                List<WebElement> listOfAutoSuggest = this.getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);
                if (checkMultiStringDisplay(listOfAutoSuggest)) {
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
        TC2713_Prüfen_des_Best_Treffer_in_AutoSuggest_Bereich test = new TC2713_Prüfen_des_Best_Treffer_in_AutoSuggest_Bereich();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }

    public boolean checkMultiStringDisplay(List<WebElement> listOfAutoSuggest) {
        boolean cheack = false;
        int found = 0;

        for (WebElement webElement : listOfAutoSuggest) {
            String city = webElement.getText();
            if (city.compareTo(globalVar.stringResult) == 0) {
                found++;
            }
        }

        if (found == 1) {
            cheack = true;
        }
        return cheack;
    }
}

package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5427;


import java.util.List;

import org.openqa.selenium.WebElement;
import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/*
 * Prüft, ob die Topt-Treffer in AutoSuggest ausgespielt wird.
 * 
 */
public class TC2711_Prüfen_der_Top_Treffer extends GenericTest_New {
    private GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar(); // GlobalVarTOnline oder

        globalVar.searchStringList.add("Munchen");
        globalVar.searchStringList.add("Frankfurt Main");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Ankara");
        globalVar.searchStringList.add("London");
        globalVar.searchStringList.add("Los Angeles");
        globalVar.searchStringList.add("New York");
        globalVar.searchStringList.add("Nizza");
        globalVar.searchStringList.add("Kopenhagen");
        globalVar.searchStringList.add("Tokyo");

        globalVar.resultStringList.add("München (Deutschland, Bayern)");
        globalVar.resultStringList.add("Frankfurt am Main (Deutschland, Hessen)");
        globalVar.resultStringList.add("Paris (Frankreich, Île-de-France)");
        globalVar.resultStringList.add("Ankara (Türkei, Ankara)");
        globalVar.resultStringList.add("London (Vereinigtes Königreich, England)");
        globalVar.resultStringList.add("Los Angeles (Vereinigte Staaten, Kalifornien)");
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

                String listItem = listOfAutoSuggest.get(0).getText();
                if (listItem.contains(globalVar.stringResult)) {
                    globalVar.stringSearch = "String " + globalVar.stringResult + " in AutoSuggest gefunden.";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(0).getText());
                } else {
                    String fehler = " " + globalVar.stringSearch + " nicht in AutoSuggest gefunden.";
                    this.logFailureCheckpoint(fehler, globalVar.stringResult, listOfAutoSuggest.get(0).getText());
                }
                getWebElement(globalVar._INPUT_BOX).clear();
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " Liste ist leer!! " + globalVar.stringResult + " AutoSuggest ist leer.";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    public static void main(String[] args) {
        TC2711_Prüfen_der_Top_Treffer test = new TC2711_Prüfen_der_Top_Treffer();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

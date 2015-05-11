/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4329;

import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


/**
 * @author a.kutekidila
 */

/*
 * Prüft, ob der erste Ort nach dem Top-Treffer in  der Suchergebnisseite ausgeklappt ausgespiet wird.
 */
public class TC2911_Top_Treffer_Ausgeklappt extends GenericTest_New {
    private GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar();

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

        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 to ??
        for (int i = 0; i < globalVar.resultStringList.size(); i++) {
            try {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

				/*
                 * Input
				 */
                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                getWebElement(globalVar._INPUT_BOX).submit();

                List<WebElement> listOfSuchergebnisTop = this.getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_TOP_ORT);
                if (checkOrtAufgeklappt() && checkOrtEingeklappt()) {
                    globalVar.stringSearch = "String " + globalVar.stringResult + " in AutoSuggest gefunden.";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(1).getText());
                } else {
                    String fehler = " " + globalVar.stringSearch + " nicht in AutoSuggest gefunden.";
                    this.logFailureCheckpoint(fehler, globalVar.stringResult, listOfSuchergebnisTop.get(0).getText());
                }

                openSuchergebnisOrte(globalVar._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS);
                getWebElement(globalVar._INPUT_BOX).clear();
            } catch (Exception e) {
                globalVar.stringSearch = globalVar.stringSearch + " Liste ist leer!! " + globalVar.stringResult + " AutoSuggest ist leer.";
                this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
            }
        }

    }

    public boolean checkOrtAufgeklappt() {
        boolean check = false;

        List<WebElement> listOfSuchergebisUeberschrifft = this.getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_LIST_TITEL);

        for (int i = 0; i < listOfSuchergebisUeberschrifft.size(); i++) {
            WebElement webElement = listOfSuchergebisUeberschrifft.get(i);
            if ((webElement.getAttribute("class").contains("isOpen")) && (i == 0)) {
                check = true;
            }
        }

        return check;
    }

    public boolean checkOrtEingeklappt() {
        boolean check = false;

        List<WebElement> listOfSuchergebisUeberschrifft = this.getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_LIST_TITEL);

        for (int i = 0; i < listOfSuchergebisUeberschrifft.size(); i++) {
            WebElement webElement = listOfSuchergebisUeberschrifft.get(i);
            if ((webElement.getAttribute("class").contains("")) && (i >= 1)) {
                check = true;
            }

        }
        return check;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2911_Top_Treffer_Ausgeklappt test = new TC2911_Top_Treffer_Ausgeklappt();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

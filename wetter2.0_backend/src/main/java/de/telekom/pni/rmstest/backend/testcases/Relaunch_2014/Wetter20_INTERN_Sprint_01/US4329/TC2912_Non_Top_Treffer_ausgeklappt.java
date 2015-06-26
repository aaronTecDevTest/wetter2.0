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
 * Prüft, ob der erst Non-Top-Treffer in  der Suchergebnisseite ausgeklappt ausgespiet wird.
 */
public class TC2912_Non_Top_Treffer_ausgeklappt extends GenericTest_New {
    private GlobalVar globalVar = null;


    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("Zwingen");
        globalVar.searchStringList.add("Zwei");
        globalVar.searchStringList.add("Dorf");
        globalVar.searchStringList.add("Longn");
        globalVar.searchStringList.add("Parda");
        globalVar.searchStringList.add("Baria");

        globalVar.resultStringList.add("Zwingen");
        globalVar.resultStringList.add("Zweifall");
        globalVar.resultStringList.add("Dorf im Warndt");
        globalVar.resultStringList.add("Longny-Au-Perche");
        globalVar.resultStringList.add("Maria Parda");
        globalVar.resultStringList.add("Baria");

        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);
        //navigate("http://www.wetter.info");

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
        TC2912_Non_Top_Treffer_ausgeklappt test = new TC2912_Non_Top_Treffer_ausgeklappt();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

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
 * Prüft, ob die Anzahl der Element mit dem der in dem Sucheergegebnisseite ausgespielt wird übereinstimmt.
 */
public class TC2913_Land_und_Anzahl_in_Suchergebnisseite extends GenericTest_New {
    private GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar(); // GlobalVarTOnline oder

        globalVar.searchStringList.add("Rom");
        globalVar.searchStringList.add("Berlin");
        globalVar.searchStringList.add("New");
        globalVar.searchStringList.add("London");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Barcelona");


        globalVar.resultStringList.add("Rom\nItalien, ");
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
        try {
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

				/*
                 * Input
				 */
                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                getWebElement(globalVar._INPUT_BOX).submit();

                pauseTest(1000);

                //List <WebElement> listOfSuchergebnisOrteMitTop = this.getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_ALL_ORT_MIT_TOP);
                //if(listOfSuchergebnisOrteMitTop!= null)
                if (checkDisplayedAnzahl()) {
                    globalVar.stringSearch = "String " + globalVar.stringResult + " in AutoSuggest gefunden.";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(1).getText());
                } else {
                    String fehler = " " + globalVar.stringSearch + " nicht in AutoSuggest gefunden.";
                    this.logFailureCheckpoint(fehler, globalVar.stringResult, globalVar.stringResult);
                }
                getWebElement(globalVar._INPUT_BOX).clear();
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " Liste ist leer!! " + globalVar.stringResult
                    + " AutoSuggest ist leer.";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    public boolean checkDisplayedAnzahl() {
        boolean check = false;
        openSuchergebnisOrte(globalVar._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS);
        List<WebElement> temp1 = getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_ALL_ORT_MIT_TOP);
        List<WebElement> temp2 = getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_ALL_ORT_OHNE_TOP);

        int anzahlOrten = temp1.size() + temp2.size();

        int anzahlFilter = sumAnzahlOrte();

        if (anzahlFilter == anzahlOrten) {
            check = true;
        }
        return check;
    }

    private int sumAnzahlOrte() {
        int counter = 0;

        List<WebElement> temp = getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_Filter_AnzahlOrt);

        counter = Integer.parseInt(temp.get(temp.size() - 1).getText());

        return counter;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2913_Land_und_Anzahl_in_Suchergebnisseite test = new TC2913_Land_und_Anzahl_in_Suchergebnisseite();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

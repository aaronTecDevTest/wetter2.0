package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5430;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

public class TC2717_Aufbau_der_Suchergebnisseite extends GenericTest_New {


    private GlobalVar globalVar = getGlobalVar();     // GlobalVarTOnline oder
    private List<WebElement> listOfSuchergebnis = new ArrayList<WebElement>();

    @Override
    public void runTest() {

        boolean isSuchergebnisOK = false;

        globalVar.searchStringList.add("Dorf");

        globalVar.resultStringList.add("66352 Dorf im Warndt\nSaarland");
        globalVar.resultStringList.add("01829 Dorf Wehlen (Stadt Wehlen)\nSachsen, Kreis Sächsische Schweiz-Osterzgebirge");
        globalVar.resultStringList.add("23948 Dorf Gutow (Damshagen)\nMecklenburg-Vorpommern, Kreis Nordwestmecklenburg");
        globalVar.resultStringList.add("94550 Dorf (Künzing)\nBayern, Kreis Deggendorf");
        globalVar.resultStringList.add("16837 Dorf Zechlin (Rheinsberg)\nBrandenburg, Kreis Ostprignitz-Ruppin");
        globalVar.resultStringList.add("97904 Dorfprozelten\nBayern, Kreis Miltenberg");
        globalVar.resultStringList.add("73450 Dorfmerkingen\nBaden-Württemberg");
        globalVar.resultStringList.add("52223 Dorff (Stolberg (Rhld))\nNordrhein-Westfalen");
        globalVar.resultStringList.add("91367 Dorfhaus (Weißenohe)\nBayern, Kreis Forchheim");
        globalVar.resultStringList.add("07338 Dorfilm (Leutenberg)\nThüringen, Kreis Saalfeld-Rudolstadt");


        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);
        try {

            globalVar.stringSearch = globalVar.searchStringList.get(0);

			/*
             * Input
			 */
            setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
            getWebElement(globalVar._INPUT_BOX).submit();
            pauseTest(2000);

            listOfSuchergebnis = getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_LIST_DE);

            for (int i = 0; i < globalVar.resultStringList.size(); i++) {

                globalVar.stringResult = globalVar.resultStringList.get(i);
                isSuchergebnisOK = checkSuchergebnis(listOfSuchergebnis, globalVar.stringResult);
                if (isSuchergebnisOK) {
                    //this.logSuccessCheckpoint("Prüfen des Suchergebnisseite (" + globalVar.resultStringList.get(i)+":","Die gesuchten Suchergebnissen sind korrekt.");
                } else {
                    this.logFailureCheckpoint("Fehler (" + globalVar.resultStringList.get(i) + "):", "Gesuchten Suchergebnissen nicht gefunden bitte prüfen.", "null");
                }
            }
            openSuchergebnisOrte(globalVar._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS);
        } catch (Exception e) {
            this.logFailureCheckpoint("Fehler:", "Suchergebnissen nicht gefunden bitte prüfen.", "");
        }
    }

    private boolean checkSuchergebnis(List<WebElement> listOfSuchergebnis, String stringResult) {
        boolean isOK = false;

        for (WebElement element : listOfSuchergebnis) {
            if (element.getText().contains(stringResult)) {
                isOK = true;
                return isOK;
            }
        }
        return isOK;
    }

    public static void main(String[] args) {
        TC2717_Aufbau_der_Suchergebnisseite test = new TC2717_Aufbau_der_Suchergebnisseite();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

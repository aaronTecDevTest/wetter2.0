package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5430;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

public class TC2722_Kreisfeien_Städten extends GenericTest_New {


    private GlobalVar globalVar = getGlobalVar();
    private List<WebElement> listOfSuchergebnis = new ArrayList<WebElement>();

    @Override
    public void runTest() {
        boolean isSuchergebnisOK = false;

        globalVar.searchStringList.add("Darmstadt");

        globalVar.resultStringList.add("64283 Darmstadt\nHessen");
        globalVar.resultStringList.add("64297 Eberstadt (Darmstadt)\nHessen");
        globalVar.resultStringList.add("64291 Arheilgen (Darmstadt)\nHessen");
        globalVar.resultStringList.add("64380 Roßdorf (bei Darmstadt)\nHessen, Kreis Darmstadt-Dieburg");
        globalVar.resultStringList.add("64291 Wixhausen (Darmstadt)\nHessen");
        globalVar.resultStringList.add("64289 Kranichstein (Darmstadt)\nHessen");
        globalVar.resultStringList.add("64380 Gundernhausen (Roßdorf b Darmstadt)\nHessen, Kreis Darmstadt-Dieburg");


        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);

        try {
            globalVar.stringSearch = globalVar.searchStringList.get(0);


            setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
            getWebElement(globalVar._INPUT_BOX).submit();
            pauseTest(1000);

            listOfSuchergebnis = getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_ALL_ORT_OHNE_TOP);

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
        TC2722_Kreisfeien_Städten test = new TC2722_Kreisfeien_Städten();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }

}

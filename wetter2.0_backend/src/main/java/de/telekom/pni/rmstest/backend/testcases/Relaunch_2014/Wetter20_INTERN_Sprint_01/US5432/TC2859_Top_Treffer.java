package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5432;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

public class TC2859_Top_Treffer extends GenericTest_New {

    private GlobalVar globalVar = getGlobalVar();
    private List<WebElement> listOfSuchergebnis = new ArrayList<WebElement>();
    private List<WebElement> listOfTopTrefer = new ArrayList<WebElement>();

    @Override
    public void runTest() {

        globalVar.searchStringList.add("Rom");
        globalVar.searchStringList.add("Antwerp");
        globalVar.searchStringList.add("Athen");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Dort");
        globalVar.searchStringList.add("Lima");

        globalVar.resultStringList.add("Rom (Roma)\n"
                + "Italien, Region Latium");
        globalVar.resultStringList.add("Antwerpen\n"
                + "Belgien, Region Flämische Region");
        globalVar.resultStringList.add("Athen (Athínai)\n"
                + "Griechenland, Region Attika");
        globalVar.resultStringList.add("Paris\n"
                + "Frankreich, Region Île-de-France");
        globalVar.resultStringList.add("Dortmund\n"
                + "Deutschland, Bundesland Nordrhein-Westfalen");
        globalVar.resultStringList.add("Lima\n"
                + "Peru, Provinz Lima");

        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__AutoSuggest__);

        for (int i = 0; i < globalVar.searchStringList.size(); i++) {
            try {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                getWebElement(globalVar._INPUT_BOX).submit();
                pauseTest(1000);


                openSuchergebnisOrte(globalVar._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS);
                listOfSuchergebnis = getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_ALL_ORT_OHNE_TOP);
                listOfTopTrefer = getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_TOP_ORT);

                if (checkTopTreffer(listOfTopTrefer)) {
                    globalVar.stringSearch = "String " + globalVar.stringResult + " in AutoSuggest gefunden.";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(1).getText());
                } else {
                    String fehler = " " + globalVar.stringSearch + " nicht in AutoSuggest gefunden.";
                    this.logFailureCheckpoint(fehler, globalVar.stringResult, listOfSuchergebnis.get(i).getText());
                }

                getWebElement(globalVar._INPUT_BOX).clear();
            } catch (Exception e) {
                this.logFailureCheckpoint("Fehler:", "Suchergebnissen nicht gefunden bitte prüfen.", "");
            }
        }
    }

    private boolean checkTopTreffer(List<WebElement> listOfTopTrefer) {
        boolean isOK = false;
        for (WebElement element : listOfTopTrefer) {
            isOK = element.getText().contains(globalVar.stringResult);
        }

        return isOK;
    }

    public static void main(String[] args) {
        TC2859_Top_Treffer test = new TC2859_Top_Treffer();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

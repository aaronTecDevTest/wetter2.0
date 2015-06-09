package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5431;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

public class TC2774_Ausländische_Orte extends GenericTest_New {

    private GlobalVar globalVar = getGlobalVar();
    private List<WebElement> listOfSuchergebnis = new ArrayList<WebElement>();

    @Override
    public void runTest() {
        globalVar.searchStringList.add("Maila");
        globalVar.searchStringList.add("Marseille");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Barcelona");
        globalVar.searchStringList.add("Madrid");

        globalVar.resultStringList.add(""
                + "Maila\n"
                + "Demokratische Republik Kongo, Provinz Kasai-Occidental"
                + "");
        globalVar.resultStringList.add(""
                + "Marseiller\n"
                + "Italien, Region Aostatal"
                + "");
        globalVar.resultStringList.add(""
                + "Paris\n"
                + "Frankreich, Region Rhône-Alpes"
                + "");
        globalVar.resultStringList.add(""
                + "Barcelona\n"
                + "Peru, Region Madre de Dios"
                + "");
        globalVar.resultStringList.add(""
                + "Madrid\n"
                + "Äquatorialguinea, Provinz Litoral"
                + "");

        //setRunningConfiguration(new RunningConfiguration_New("CH","Test"));
        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);

        for (int i = 0; i < globalVar.searchStringList.size(); i++) {
            try {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                getWebElement(globalVar._INPUT_BOX).submit();


                openSuchergebnisOrte(globalVar._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS);
                pauseTest(1000);
                listOfSuchergebnis = getListSuchergebnis(globalVar._SUCHERGEBNISSEITE_LIST_ORTE3);

                if (checkTopTreffer(listOfSuchergebnis)) {
                    globalVar.stringSearch = "String " + globalVar.stringResult + " in AutoSuggest gefunden.";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(1).getText());
                } else {

                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, listOfSuchergebnis.get(i).getText());
                }
                pauseTest(1000);
                getWebElement(globalVar._INPUT_BOX).clear();
                pauseTest(1000);
            } catch (Exception e) {
                this.logFailureCheckpoint("Fehler:", "Suchergebnissen nicht gefunden bitte prüfen:" + globalVar.stringResult, e.getStackTrace().toString());
            }
        }
    }

    private boolean checkTopTreffer(List<WebElement> listOfSuchergebnis) {
        boolean isOK = false;
        for (WebElement element : listOfSuchergebnis) {
            isOK = element.getText().contains(globalVar.stringResult);
            if (isOK) {
                return isOK;
            }
        }
        return isOK;
    }

    public static void main(String[] args) {
        TC2774_Ausländische_Orte test = new TC2774_Ausländische_Orte();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

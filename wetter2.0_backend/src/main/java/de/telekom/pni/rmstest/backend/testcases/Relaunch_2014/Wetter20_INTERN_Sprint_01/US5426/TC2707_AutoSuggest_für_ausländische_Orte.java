package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5426;

import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/*
 * Prüft, ob in der AutoSuggest die ausländische Orte ausgespielt wird.
 */
public class TC2707_AutoSuggest_für_ausländische_Orte extends GenericTest_New {

    private GlobalVar globalVar = null;

    @Override
    public void runTest() {

        globalVar = getGlobalVar();
        globalVar.searchStringList.add("Semmering");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Mainland");
        globalVar.searchStringList.add("Vura");
        globalVar.searchStringList.add("Kinshasa");

        globalVar.resultStringList.add("Semmering (Österreich, Niederösterreich)");
        globalVar.resultStringList.add("Paris (Frankreich, Île-de-France)");
        globalVar.resultStringList.add("Mainland (Australien, Western Australia)");
        globalVar.resultStringList.add("Vura (Uganda, Arua)");
        globalVar.resultStringList.add("Kinshasa (Demokratische Republik Kongo, Kinshasa)");

        //Step 1
        getBrowser();
        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3
        try {
            for (int i = 0; i < globalVar.resultStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(1000);

					/*
                     * Result
					 */
                List<WebElement> listOfAutoSuggest = this.getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);

                if (checkItemInAutoSuggest(listOfAutoSuggest, globalVar.stringResult)) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in AutoSuggest";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in AutoSuggest";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
                getWebElement(globalVar._INPUT_BOX).clear();
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " could be not found in AutoSuggest";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    public boolean checkItemInAutoSuggest(List<WebElement> listOfAutoSuggest, String resultStringList) {
        boolean check = false;

        for (int j = 0; j < listOfAutoSuggest.size(); j++) {
            boolean isFound = listOfAutoSuggest.get(j).getText().contains(resultStringList);
            if (isFound) {
                return isFound;
            }
        }
        return check;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2707_AutoSuggest_für_ausländische_Orte test = new TC2707_AutoSuggest_für_ausländische_Orte();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }

}

package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5426;

import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

public class TC2710_Suche_mit_Ort_alternativ_Name extends GenericTest_New {

    private GlobalVar globalVar = null;

    @Override
    public void runTest() {

        globalVar = getGlobalVar();
        globalVar.searchStringList.add("Milano");
        globalVar.searchStringList.add("Curunha"); //La Corogne
        globalVar.searchStringList.add("Anvers");
        globalVar.searchStringList.add("Tehran");

        globalVar.resultStringList.add("Mailand (Italien, Lombardei)");
        globalVar.resultStringList.add("La Coruña (Spanien, Galicien)");
        globalVar.resultStringList.add("Antwerpen (Belgien, Flämische Region)");
        globalVar.resultStringList.add("Teheran (Iran, Teheran)");

        //Step 1
        getBrowser();
        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 to ??
        try {
            for (int i = 0; i < globalVar.resultStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(1000);
                /**
                 * Result
                 */
                List<WebElement> listOfAutoSuggest = this.getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);
                if (cheackItemInAutoSuggest(listOfAutoSuggest, globalVar.stringResult)) {
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

    public boolean cheackItemInAutoSuggest(List<WebElement> listOfAutoSuggest, String resultStringList) {
        boolean check = false;

        for (int j = 0; j < listOfAutoSuggest.size(); j++) {

            boolean isFound = listOfAutoSuggest.get(j).getText().contains(globalVar.stringResult);
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
        TC2710_Suche_mit_Ort_alternativ_Name test = new TC2710_Suche_mit_Ort_alternativ_Name();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

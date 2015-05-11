/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5480;

import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */
public class TC2695_Mallorca_Suche_mit_Teilstring extends GenericTest_New {
    private GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar();
        globalVar.searchStringList.add("Mallorca");

        globalVar.resultStringList.add("Mallorca (Spanien, Balearische Inseln)");
        globalVar.resultStringList.add("Palma de Mallorca (Mexiko, Campeche)");
        globalVar.resultStringList.add("Palma de Mallorca (Spanien, Balearische Inseln");

        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 to ??
        for (int i = 0; i < globalVar.searchStringList.size(); i++) {
            try {
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
                    this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(i).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in AutoSuggest";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");//listOfAutoSuggest.get(i).getText());
                }
                getWebElement(globalVar._INPUT_BOX).clear();
            } catch (Exception e) {
                globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " could be not found in AutoSuggest";
                this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
            }
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
        TC2695_Mallorca_Suche_mit_Teilstring test = new TC2695_Mallorca_Suche_mit_Teilstring();
        test.before();
        test.runTest();
        test.after();
    }


    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}


package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_ESEMOS_Sprint_01.US4152;


import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


/**
 * @author a.kutekidila
 */
public class TC2702_Validen_Ergebnis_durch_PLZ_suche extends GenericTest_New {

    private GlobalVar globalVar = null;

    @Override
    public void runTest() {

        globalVar = getGlobalVar(); // GlobalVarTOnline oder
        globalVar.searchStringList.add("64283");
        globalVar.searchStringList.add("10115");
        globalVar.searchStringList.add("60528");
        globalVar.searchStringList.add("50670");
        globalVar.searchStringList.add("80999");
        globalVar.searchStringList.add("01187");

        globalVar.resultStringList.add("Darmstadt");
        globalVar.resultStringList.add("Berlin");
        globalVar.resultStringList.add("Frankfurt am Main");
        globalVar.resultStringList.add("Köln");
        globalVar.resultStringList.add("München");
        globalVar.resultStringList.add("Dresden Naußlitz");

        //Step 1
        getBrowser();
        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);
        //navigate("http://www.wetter.info");
        //Step 3 to ??
        for (int i = 0; i < globalVar.searchStringList.size(); i++) {
            globalVar.stringSearch = globalVar.searchStringList.get(i);
            globalVar.stringResult = globalVar.resultStringList.get(i);

			/*
			 * Input
			 */
            setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
            pauseTest(1000);

            /**
             * Result
             */
            List<WebElement> listOfAutoSuggest = this.getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);

            try {
                if (checkStringInList(listOfAutoSuggest, globalVar.stringResult)) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in AutoSuggest";
                    this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(0).getText());

                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in AutoSuggest";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");//listOfAutoSuggest.get(i).getText());
                }
            } catch (Exception e) {
                globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " could be not found in AutoSuggest";
                this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
            }

            //getWebElement(_SEARCH_BUTTON).click();
            getWebElement(globalVar._INPUT_BOX).sendKeys("\n");
            pauseTest(1000);

            getWebElement(globalVar._INPUT_BOX).clear();
        }


    }

    public boolean checkStringInList(List<WebElement> listOfAutoSuggest, String search) {
        boolean check = false;

        for (WebElement webElement : listOfAutoSuggest) {

            String elementText = webElement.getText();
            if (elementText.contains(search)) {
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2702_Validen_Ergebnis_durch_PLZ_suche test = new TC2702_Validen_Ergebnis_durch_PLZ_suche();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }


}

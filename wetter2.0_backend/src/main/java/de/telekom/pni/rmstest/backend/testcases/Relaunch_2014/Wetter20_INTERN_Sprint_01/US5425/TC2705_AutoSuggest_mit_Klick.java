package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5425;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */

/*
 *Prüft, ob im AutoSuggest eine Orte anklickbar ist und anschließend die drei TagesWetterSeite 
 *aufgerufen wird. 
 */
public class TC2705_AutoSuggest_mit_Klick extends GenericTest_New {

    private GlobalVar globalVar = null;
    private ArrayList<String> dreiTageWetterSeite = new ArrayList<String>();

    @Override
    public void runTest() {

        globalVar = getGlobalVar(); // GlobalVarTOnline oder
        globalVar.searchStringList.add("Zwingenberg");

        globalVar.resultStringList.add("69439 Zwingenberg (Baden)");

        dreiTageWetterSeite.add("wetter-zwingenberg-baden/K08225113");

        //Step 1
        getBrowser();
        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);
        try {
            //Step 3 to ??
            for (int i = 0; i < globalVar.resultStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(1000);
                /**
                 * Result
                 */
                List<WebElement> listOfAutoSuggest = this.getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);

                if (listOfAutoSuggest != null) {

                    //If webElmentString gleich stringResult dann ist das Ergebnis -1

                    if (isAutoSuggestClickabel(listOfAutoSuggest, globalVar.stringResult, dreiTageWetterSeite.get(i))) {
                        globalVar.stringSearch = "String" + globalVar.stringResult + " was found in AutoSuggest";
                        //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                    } else {
                        globalVar.stringSearch = " " + globalVar.stringResult + " was not found in AutoSuggest";
                        this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");//listOfAutoSuggest.get(i).getText());
                    }
                }
            }
            //	getWebElement(globalVar._INPUT_BOX).clear();
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " could be not found in AutoSuggest";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2705_AutoSuggest_mit_Klick test = new TC2705_AutoSuggest_mit_Klick();
        test.before();
        test.runTest();
        test.after();
    }

    public boolean isAutoSuggestClickabel(List<WebElement> listOfAutoSuggest, String searchString, String link3Tage) {
        boolean check = false;
        int indexIteamFound = -1;

        for (int i = 0; i < listOfAutoSuggest.size(); i++) {
            String tempString = listOfAutoSuggest.get(i).getText();
            if (tempString.contains(searchString)) {
                indexIteamFound = i + 2;
                break;
            }
        }

        WebElement iteamWithATag = getByXPath(".//*[@id='searchPattern_ac']/ul/li[" + indexIteamFound + "]/a");
        //iteamWithATag.click();

        String temp = iteamWithATag.getAttribute("href");
        boolean check1 = temp.contains(link3Tage);
        boolean check2 = clickLink(".//*[@id='searchPattern_ac']/ul/li[" + indexIteamFound + "]/a");

        if (check1 && check2) {
            check = true;
        }
        pauseTest(2000);
        getBrowser().navigate().back();
        return check;
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

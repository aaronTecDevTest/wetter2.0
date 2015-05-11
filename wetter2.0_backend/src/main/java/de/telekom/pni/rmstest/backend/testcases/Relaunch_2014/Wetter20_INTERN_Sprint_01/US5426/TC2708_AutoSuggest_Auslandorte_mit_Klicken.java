package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5426;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


public class TC2708_AutoSuggest_Auslandorte_mit_Klicken extends GenericTest_New {

    private GlobalVar globalVar = null;
    private ArrayList<String> dreiTageWetterSeite = new ArrayList<String>();

    @Override
    public void runTest() {

        globalVar = getGlobalVar();
        globalVar.searchStringList.add("Mainar");
        globalVar.searchStringList.add("New York");
        globalVar.searchStringList.add("Kinshasa");

        globalVar.resultStringList.add("Mainar (Spanien, Aragonien)");
        globalVar.resultStringList.add("New York City (Vereinigte Staaten, New York)");
        globalVar.resultStringList.add("Kinshasa (Demokratische Republik Kongo, Kinshasa)");

        dreiTageWetterSeite.add("wetter-mainar/N-579722");
        dreiTageWetterSeite.add("wetter-new-york-city/U03690c3c");
        dreiTageWetterSeite.add("wetter-kinshasa/N-2843631");


        //Step 1
        getBrowser();
        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

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

                if (isAutoSuggestClickabel(listOfAutoSuggest, globalVar.stringResult, dreiTageWetterSeite.get(i))) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in AutoSuggest";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in AutoSuggest";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " could be not found in AutoSuggest";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }


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

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2708_AutoSuggest_Auslandorte_mit_Klicken test = new TC2708_AutoSuggest_Auslandorte_mit_Klicken();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

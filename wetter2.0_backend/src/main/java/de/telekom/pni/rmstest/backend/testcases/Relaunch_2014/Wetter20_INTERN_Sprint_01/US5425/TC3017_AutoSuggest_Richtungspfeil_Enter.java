package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5425;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */

/*
 *Prüft, ob in der AutoSuggest die Richtungstast+Enter funktioniert.
 */
public class TC3017_AutoSuggest_Richtungspfeil_Enter extends GenericTest_New {

    private GlobalVar globalVar = null;
    private ArrayList<String> dreiTageWetterSeite = new ArrayList<String>();

    @Override
    public void runTest() {

        globalVar = getGlobalVar(); // GlobalVarTOnline oder
        globalVar.searchStringList.add("Frankfurt Main");
        globalVar.resultStringList.add("60435 Frankfurt am Main Preungesheim");

        dreiTageWetterSeite.add("wetter-frankfurt-am-main-preungesheim/K90064432");

        //Step 1
        getBrowser();
        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 to ??
        try {
            for (int i = 0; i < globalVar.resultStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);
                int index = 0;

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(1000);

                /**
                 * Result
                 */
                List<WebElement> listOfAutoSuggest = this.getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);
                //Suchen den RsultString in der AutoSuggestListe
                for (int j = 0; j < listOfAutoSuggest.size(); j++) {
                    String tempString = listOfAutoSuggest.get(j).getText();
                    if (tempString.contains(globalVar.stringResult)) {
                        index = j;
                        System.out.println(tempString);
                        break;
                    }
                }

                //Navigation mit Pfeilunten
                for (int k = 0; k <= index; k++) {
                    getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);
                    pauseTest(1000);
                    if (k == index) {
                        setInputFeldValue(globalVar._INPUT_BOX, "\n");
                    }
                }

                //If webElmentString gleich stringResult dann ist das Ergebnis -1
                if (isAutoSuggestClickabel(dreiTageWetterSeite.get(i))) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in AutoSuggest";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in AutoSuggest";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");//listOfAutoSuggest.get(i).getText());
                }
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " could be not found in AutoSuggest";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    public boolean isAutoSuggestClickabel(String link3Tage) {
        boolean check = false;
        String url = getBrowser().getCurrentUrl();
        if (url.contains(link3Tage)) {
            check = true;
        }
        getBrowser().navigate().back();
        return check;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC3017_AutoSuggest_Richtungspfeil_Enter test = new TC3017_AutoSuggest_Richtungspfeil_Enter();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

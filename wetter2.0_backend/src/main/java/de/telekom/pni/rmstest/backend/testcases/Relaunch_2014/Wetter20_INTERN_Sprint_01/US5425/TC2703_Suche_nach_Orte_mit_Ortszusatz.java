package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5425;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.global.GlobalVar;
import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;


/**
 * @author a.kutekidila
 */
/*
 * Prüft, ob die Orte mit Ortszusatz ausgespietl wird.
 */
public class TC2703_Suche_nach_Orte_mit_Ortszusatz extends GenericTest_New {
    private GlobalVar globalVar = null;

    ArrayList<String> zwingenberg = new ArrayList<String>();
    ArrayList<String> zschorna = new ArrayList<String>();
    ArrayList<String> emhof = new ArrayList<String>();
    ArrayList<String> buchsee = new ArrayList<String>();

    @Override
    public void runTest() {
        globalVar = getGlobalVar(); // GlobalVarTOnline oder

        globalVar.searchStringList.add("Zwingenberg");
        globalVar.searchStringList.add("Zschorna");
        globalVar.searchStringList.add("Emhof");
        globalVar.searchStringList.add("Buchsee");


        zwingenberg.add("69439 Zwingenberg (Baden)");
        zwingenberg.add("64673 Zwingenberg (Bergstraße)");
        zwingenberg.add("64673 Rodau (Zwingenberg Bergstr)");

        zschorna.add("04808 Zschorna (Lossatal)");
        zschorna.add("02627 Zschorna (Hochkirch)");
        zschorna.add("01561 Zschorna (Tauscha b Großenhain)");
        zschorna.add("01917 Zschornau (Kamenz)");
        zschorna.add("07570 Zschorta (Wünschendorf/Elster)");

        emhof.add("92287 Emhof (Schmidmühlen)");
        emhof.add("29614 Emhof (Soltau)");

        buchsee.add("88273 Buchsee (Fronreute)");
        buchsee.add("82541 Buchsee (Münsing)");
        buchsee.add("94136 Buchsee (Thyrnau)");
        buchsee.add("83564 Buchsee (Soyen)");

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);

        //Step 3 to ??
        try {
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                // globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(1000);
                /**
                 * Result
                 */
                List<WebElement> listOfAutoSuggest = this.getListAutoSuggest(globalVar._AUTOSUGGEST, globalVar._AUTOSUGGEST_LIST);
                List<String> tempList = new ArrayList<String>();

                for (WebElement element : listOfAutoSuggest) {
                    tempList.add(element.getText());
                }

                if (checkIteamInAutoSuggest(i, globalVar.stringSearch, tempList)) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in AutoSuggest";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(i).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in AutoSuggest";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");//listOfAutoSuggest.get(i).getText());
                }
                getWebElement(globalVar._INPUT_BOX).clear();
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " could be not found in AutoSuggest";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    private boolean checkIteamInAutoSuggest(int i, String stringSearch, List<String> list) {
        ArrayList<String> temp = new ArrayList<String>();
        boolean check = true;

        switch (i) {
            case 0:
                temp = zwingenberg;
                break;
            case 1:
                temp = zschorna;
                break;
            case 2:
                temp = emhof;
                break;
            case 3:
                temp = buchsee;
                break;

            default:
                return check;
        }

        for (int j = 0; j < list.size(); j++) {
            String searchString = temp.get(j);
            String foundString = list.get(j);
            boolean isFound = true;

            if (searchString == foundString) {
                isFound = false;
            }

            check = isFound && check;
        }

        return check;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2703_Suche_nach_Orte_mit_Ortszusatz test = new TC2703_Suche_nach_Orte_mit_Ortszusatz();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

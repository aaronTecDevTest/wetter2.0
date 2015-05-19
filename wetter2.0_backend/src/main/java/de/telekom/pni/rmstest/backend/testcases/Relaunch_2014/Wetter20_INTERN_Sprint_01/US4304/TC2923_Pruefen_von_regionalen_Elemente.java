package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4304;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


public class TC2923_Pruefen_von_regionalen_Elemente extends GenericTest_New {
    private GlobalVar globalVar;

    public TC2923_Pruefen_von_regionalen_Elemente() {
    }

    @Override
    public void runTest() {

        globalVar = getGlobalVar();

        List<String> bundesland = new ArrayList<String>();

        globalVar.searchStringList.add("Darmstadt");
        globalVar.searchStringList.add("Berlin");
        globalVar.searchStringList.add("Bonn");
        globalVar.searchStringList.add("München");
        globalVar.searchStringList.add("Saarbrücken");
        globalVar.searchStringList.add("Kiel");

        globalVar.resultStringList.add("64283 Darmstadt");
        globalVar.resultStringList.add("12355 Berlin Rudow");
        globalVar.resultStringList.add("53227 Oberkassel (Bonn)");
        globalVar.resultStringList.add("94116 München (Hutthurm)");
        globalVar.resultStringList.add("6611 Saarbrücken");
        globalVar.resultStringList.add("24103 Kiel");

        bundesland.add("HESSEN");
        bundesland.add("BERLIN");
        bundesland.add("NRW");
        bundesland.add("BAYERN");
        bundesland.add("SAARLAND");
        bundesland.add("SCHLESWIG-HOLSTEIN");


        getBrowser();

        List<WebElement> listOfAutoSuggest = new ArrayList<WebElement>();
        try {
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                //Step 2
                navigate(globalVar.__AutoSuggest__);

                //Step 3 ....
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                //setInputFeldValue(globalVar._INPUT_BOX, "\n");
                listOfAutoSuggest = getWebElements(globalVar._AUTOSUGGEST_LIST);
                pauseTest(1000);
                /**
                 * Result
                 */
                for (WebElement webElement : listOfAutoSuggest) {
                    //System.out.println(webElement.getText());
                    if (webElement.getText().contains(globalVar.stringResult)) {
                        webElement.click();
                        break;
                    }
                }

                if (false) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " AD-Elements could be not found in Web";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }


    }

    private boolean CheckKarte() {
        boolean check = true;

        if (getWebElement(globalVar._BOX_WETTER__WETER_KARTE) == null)
            check = false;

        return check;
    }

    private boolean checkRegionText(String bundesland) {
        boolean check = true;

        if (getBrowser().findElement(By.tagName("h6")).getText().contains(bundesland))
            check = false;

        return check;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2923_Pruefen_von_regionalen_Elemente test = new TC2923_Pruefen_von_regionalen_Elemente();
        test.before();
        test.runTest();
        test.after();
    }


    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

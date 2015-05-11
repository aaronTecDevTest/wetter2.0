package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4304;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


public class TC2924_Pruefen_von_auslaendischen_Nachrichtenbox extends GenericTest_New {
    private GlobalVar globalVar;

    public TC2924_Pruefen_von_auslaendischen_Nachrichtenbox() {
    }

    @Override
    public void runTest() {

        globalVar = getGlobalVar();

        globalVar.searchStringList.add("<div id=\"vmsky\">");
        globalVar.searchStringList.add("<div id=\"vmpop\">");
        globalVar.searchStringList.add("<div id=\"vmsb\">");
        globalVar.searchStringList.add("<div id=\"vmcadsl\">");
        globalVar.searchStringList.add("<div id=\"vmcadsr\">");

        getBrowser();

        //Step 2
        navigate("http://portal.wetter.info/winfo/ausland/wetter-vereinigte-staaten/new-york/wetter-new-york-city/U03690c3c");//(globalVar.__SUCHWEBSIDE_3TageAnsicht_Preview__);
        //setInputFeldValue(globalVar._INPUT_BOX, "Frankfurt am Main");
        //getWebElement(globalVar._INPUT_BOX).submit();

        //Step 3 ....

        List<WebElement> listOfADRectangles = new ArrayList<WebElement>();

        listOfADRectangles.add(getWebElement(globalVar._Werbung_0));
        listOfADRectangles.add(getWebElement(globalVar._Werbung_1));
        listOfADRectangles.add(getWebElement(globalVar._Werbung_2));
        listOfADRectangles.add(getWebElement(globalVar._Werbung_3));
        listOfADRectangles.add(getWebElement(globalVar._Werbung_4));

        try {
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);

                /**
                 * Result
                 */
                if (listOfADRectangles != null) {
                    if (checkADTag(listOfADRectangles.get(i))) {
                        globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                        //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                    } else {
                        globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                        this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");//listOfAutoSuggest.get(i).getText());
                    }
                }
                getWebElement(globalVar._INPUT_BOX).clear();
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " AD-Elements could be not found in Web";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }


    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2924_Pruefen_von_auslaendischen_Nachrichtenbox test = new TC2924_Pruefen_von_auslaendischen_Nachrichtenbox();
        test.before();
        test.runTest();
        test.after();
    }


    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }


    public boolean checkADTag(WebElement adRectangle) {
        boolean check = false;

        if (adRectangle != null) {
            check = true;
        }
        return check;
    }

}

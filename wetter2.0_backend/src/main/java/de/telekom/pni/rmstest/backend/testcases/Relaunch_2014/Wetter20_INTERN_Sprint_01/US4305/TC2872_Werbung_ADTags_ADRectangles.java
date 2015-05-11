package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4305;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


public class TC2872_Werbung_ADTags_ADRectangles extends GenericTest_New {

    private GlobalVar globalVar = null;

    public TC2872_Werbung_ADTags_ADRectangles() {
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
        navigate(globalVar.__StartSeiteFavoriten__);

        //Step 2
        try {
            setInputFeldValue(globalVar._INPUT_BOX, "Frankfurt Main");
            pauseTest(500);
            getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);
            setInputFeldValue(globalVar._INPUT_BOX, "\n");

            //Step 3 ....
            List<WebElement> listOfADRectangles = new ArrayList<WebElement>();

			/*listOfADRectangles.add(getWebElement(".//*[@id='Twnav']/ul[1]/li[2]/s/a"));
            listOfADRectangles.add(getWebElement(".//*[@id='Twnav']/ul[1]/li[3]/s/a"));
			listOfADRectangles.add(getWebElement(".//*[@id='Twnav']/ul[1]/li[4]/s/a"));
			listOfADRectangles.add(getWebElement(".//*[@id='Twnav']/ul[1]/li[5]/s/a"));*/

            listOfADRectangles.add(getWebElement(globalVar._Werbung_0));
            listOfADRectangles.add(getWebElement(globalVar._Werbung_1));
            listOfADRectangles.add(getWebElement(globalVar._Werbung_2));
            listOfADRectangles.add(getWebElement(globalVar._Werbung_3));
            listOfADRectangles.add(getWebElement(globalVar._Werbung_4));

            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);

                /**
                 * Result
                 */
                if (checkADTag(listOfADRectangles.get(i))) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");//listOfAutoSuggest.get(i).getText());
                }
                getWebElement(globalVar._INPUT_BOX).clear();
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " AD-Elements could be not found in Web";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }


    }

    public boolean checkADTag(WebElement adRectangle) {
        //Actions action = new Actions(getBrowser());
        boolean check = false;

        //action.moveToElement(adRectangle).build().perform();
        //this.pauseTest(2000);

        if (adRectangle != null) {
            check = true;
        }
        return check;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2872_Werbung_ADTags_ADRectangles test = new TC2872_Werbung_ADTags_ADRectangles();
        test.before();
        test.runTest();
        test.after();
    }


    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4294;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


/**
 * @author a.kutekidila
 */
public class TC2838_Tab_Logik_Deutschland extends GenericTest_New {
    GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("64283 Darmstadt");
        globalVar.searchStringList.add("64673 Zwingenberg");
        globalVar.searchStringList.add("27327 Kleinenborstel");
        globalVar.searchStringList.add("23823 Berlin");


        globalVar.resultStringList.add("hessen/kreisfreie-stadt-darmstadt/wetter-darmstadt/K06411000");
        globalVar.resultStringList.add("hessen/kreis-bergstrasse/wetter-zwingenberg-bergstrasse/K06431022");
        globalVar.resultStringList.add("niedersachsen/kreis-diepholz/wetter-kleinenborstel-martfeld/K90000001");
        globalVar.resultStringList.add("kreis-segeberg/wetter-berlin-seedorf-b-bad-segeberg/K90006849");


        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        /**
         * Result
         */
        try {
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(1000);
                getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);
                setInputFeldValue(globalVar._INPUT_BOX, "\n");

                if (checkH1Tag(globalVar.stringResult)) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
                navigate(globalVar.__StartSeiteFavoriten__);
                pauseTest(1000);
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " AD-Elements could be not found in Web";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "Test Fail");
        }
    }

    /**
     * @param args String Array
     */
    public static void main(String[] args) {
        TC2838_Tab_Logik_Deutschland test = new TC2838_Tab_Logik_Deutschland();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }


    public boolean checkH1Tag(String resultTemp) {

        boolean check = true;
        String result;
        String search;
        WebDriver url;

        for (int i = 0; i < 12; i++) {
            url = getBrowser();
            switch (i) {
                case 0:
                    result = resultTemp;
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='T-self.parsedId-1']/div[2]/p[1]/a").click();
                    break;

                case 1:
                    result = resultTemp + "?wetter-heute";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[2]/a").click();

                    break;

                case 2:
                    result = resultTemp + "?wetter-morgen";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[3]/a").click();
                    break;

                case 3:
                    result = resultTemp + "?wetter-uebermorgen";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[4]/a").click();
                    break;

                case 4:
                    result = resultTemp + "?wetter-3-tage";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[5]/a").click();
                    break;

                case 5:
                    result = resultTemp + "?wetter-4-tage";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[6]/a").click();
                    break;

                case 6:
                    result = resultTemp + "?wetter-5-tage";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[7]/a").click();
                    break;

                case 7:
                    result = resultTemp + "?wetter-6-tage";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[8]/a").click();
                    break;

                case 8:
                    result = resultTemp + "?wetter-7-tage";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[9]/a").click();
                    break;

                case 9:
                    result = resultTemp + "?wetter-8-tage";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[10]/a").click();
                    break;

                case 10:
                    result = resultTemp + "?wetter-9-tage";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]").click();
                    break;

                case 11:
                    result = resultTemp + "?wetter-10-tage";
                    search = url.getCurrentUrl();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    break;

                default:
                    break;
            }
        }
        return check;
    }
}

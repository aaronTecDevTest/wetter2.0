package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4297;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */
public class TC2927_Breadcrumb_für_Orte_im_Deutschland extends GenericTest_New {
    GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("64283 Darmstadt");

        globalVar.resultStringList.add("Sie sind hier: Wetter.info > Wetter Deutschland > Wetter Hessen > Wetter Darmstadt");

       // setRunningConfiguration(new RunningConfiguration_New("CH", ""));
        //Step 1
        getBrowser();

        //Step 2
       // navigate("http://www.wetter.info");
       navigate(globalVar.__StartSeiteFavoriten__);

        /**
         * Result
         */
        try {
            for (int i = 0; i < 1; i++) {
                //Step 3 ....
                globalVar.stringResult = globalVar.resultStringList.get(i);
                globalVar.stringSearch = globalVar.searchStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(500);
                getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);
                setInputFeldValue(globalVar._INPUT_BOX, "\n");

                if (checkBreadcrumb(globalVar.stringResult)) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
                navigate(globalVar.__StartSeiteFavoriten__);
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " Elements could be not found in Web";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "Test Fail");
        }
    }

    /**
     * @param args String Array
     */
    public static void main(String[] args) {
        TC2927_Breadcrumb_für_Orte_im_Deutschland test = new TC2927_Breadcrumb_für_Orte_im_Deutschland();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }


    public boolean checkBreadcrumb(String resultTemp) {

        boolean check = true;
        String result;
        String search;
        WebElement breadcrumb;

        for (int i = 0; i < 12; i++) {
            pauseTest(1000);
            breadcrumb = getWebElement(globalVar.__BREADCRUMB_ALL__);

            switch (i) {
                case 0:
                    result = resultTemp;
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='T-self.parsedId-1']/div[2]/p[1]/a").click();
                    break;

                case 1:
                    result = resultTemp + " heute";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Treitsci']/ul/li[2]/a").click();
                    break;

                case 2:
                    result = resultTemp + " morgen";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Treitsci']/ul/li[3]/a").click();
                    break;

                case 3:
                    result = resultTemp + " übermorgen";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Treitsci']/ul/li[4]/a").click();
                    break;

                case 4:
                    result = resultTemp + " in 3 Tagen";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Treitsci']/ul/li[5]/a").click();
                    break;

                case 5:
                    result = resultTemp + " in 4 Tagen";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Treitsci']/ul/li[6]/a").click();
                    break;

                case 6:
                    result = resultTemp + " in 5 Tagen";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Treitsci']/ul/li[7]/a").click();
                    break;

                case 7:
                    result = resultTemp + " in 6 Tagen";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Treitsci']/ul/li[8]/a").click();
                    break;

                case 8:
                    result = resultTemp + " in 7 Tagen";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Treitsci']/ul/li[9]/a").click();
                    break;

                case 9:
                    result = resultTemp + " in 8 Tagen";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Treitsci']/ul/li[10]/a").click();
                    break;

                case 10:
                    result = resultTemp + " in 9 Tagen";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    pauseTest(1000);
                    getWebElement(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]").click();
                    break;

                case 11:
                    result = resultTemp + " für 10 Tage";
                    search = breadcrumb.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    checkBreadcrumbLink(search);
                    break;

                default:
                    break;
            }

        }
        return check;
    }


    public void checkBreadcrumbLink(String mainTitel) {

        String titel;
        pauseTest(1000);
        getWebElement(globalVar.__BREADCRUMB_LINK1__).click();
        pauseTest(1000);
        titel = getBrowser().getTitle();
        if (titel.isEmpty()) {
            this.logFailureCheckpoint("Link1", mainTitel, "Page not found!");
        }
        getBrowser().navigate().back();
        pauseTest(1000);
        //titel = "";
        getWebElement(globalVar.__BREADCRUMB_LINK2__).click();
        pauseTest(1000);
        titel = getBrowser().getTitle();
        if (titel.isEmpty()) {
            this.logFailureCheckpoint("Link2", mainTitel, "Page not found!");
        }
        getBrowser().navigate().back();
        pauseTest(1000);
        //titel = "";
        getWebElement(globalVar.__BREADCRUMB_LINK3__).click();
        pauseTest(1000);
        titel = getBrowser().getTitle();
        if (titel.isEmpty()) {
            this.logFailureCheckpoint("Link3", mainTitel, "Page not found!");
        }
        getBrowser().navigate().back();
        pauseTest(1000);
        /*
        titel = "";
        getWebElement(globalVar.__BREADCRUMB_LINK4__).click();
        pauseTest(500);
        titel = getBrowser().getTitle();
        if(titel.isEmpty()) {
            this.logFailureCheckpoint("Link4", mainTitel,"Page not found!");
        }
        getBrowser().navigate().back();*/
    }
}


package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4303;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * @author a.kutekidila
 */
public class TC2906_Prüfen_von_Canonical_Tag extends GenericTest_New {
    private GlobalVar globalVar;

    /**
     *
     */
    public TC2906_Prüfen_von_Canonical_Tag() {

    }

    @Override
    public void runTest() {

        globalVar = getGlobalVar();

        globalVar.searchStringList.add("Groß-Gerau");
        globalVar.searchStringList.add("Paris");

        globalVar.resultStringList.add("www.wetter.info/wetter-deutschland/hessen/kreis-gross-gerau/wetter-gross-gerau/K06433006");
        globalVar.resultStringList.add("www.wetter.info/ausland/wetter-frankreich/ile-de-france/wetter-paris/N-2085971");


        try {
            //Spet 1
            getBrowser();

            //Spet 2
            navigate(globalVar.__StartSeiteFavoriten__);

            //Spet 3
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {

                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(1500);
                getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);

                getWebElement(globalVar._INPUT_BOX).sendKeys("\n");
                pauseTest(1500);


                if (checkCononicalTag(i)) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
                navigate(globalVar.__StartSeiteFavoriten__);
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
        TC2906_Prüfen_von_Canonical_Tag test = new TC2906_Prüfen_von_Canonical_Tag();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }

    boolean checkCononicalTag(int offset) {
        boolean check = true;
        String result = "";
        String search = "";
        WebElement cononicalTag = null;


        for (int i = 0; i < 11; i++) {
            cononicalTag = getWebElement(globalVar.__META_CONONICAL_TAG__);
            pauseTest(1000);
            switch (i) {
                case 0:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='T-self.parsedId-1']/div[2]/p[1]/a").click();
                    break;

                case 1:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[2]/a").click();

                    break;

                case 2:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[3]/a").click();
                    break;

                case 3:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[4]/a").click();
                    break;

                case 4:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[5]/a").click();
                    break;

                case 5:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[6]/a").click();
                    break;

                case 6:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[7]/a").click();
                    break;

                case 7:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[8]/a").click();
                    break;

                case 8:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[9]/a").click();
                    break;

                case 9:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[10]/a").click();
                    break;

                case 10:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]").click();
                    break;

                case 11:
                    result = globalVar.resultStringList.get(offset);
                    search = cononicalTag.getAttribute("href");

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
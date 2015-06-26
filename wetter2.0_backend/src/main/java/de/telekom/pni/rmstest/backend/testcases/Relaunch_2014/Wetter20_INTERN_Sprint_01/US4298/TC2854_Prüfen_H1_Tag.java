/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4298;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */
public class TC2854_Prüfen_H1_Tag extends GenericTest_New {
    GlobalVar globalVar = null;

    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("64283 Darmstadt");
        globalVar.searchStringList.add("64673 Zwingenberg (Bergstraße)");
        globalVar.searchStringList.add("27327 Kleinenborstel (Martfeld)");
        globalVar.searchStringList.add("12683 Berlin Biesdorf");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Postkamp (Altenholz)");
        globalVar.searchStringList.add("New York");

        globalVar.resultStringList.add("Wetter Darmstadt");
        globalVar.resultStringList.add("Wetter Zwingenberg (Bergstraße)");
        globalVar.resultStringList.add("Wetter Kleinenborstel (Martfeld)");
        globalVar.resultStringList.add("Wetter Berlin");
        globalVar.resultStringList.add("Wetter Paris");
        globalVar.resultStringList.add("Wetter Postkamp (Altenholz)");
        globalVar.resultStringList.add("Wetter New York");

       // setRunningConfiguration(new RunningConfiguration_New("CH", ""));
        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        /**
         * Result
         */
        try {
            for (int i = 0; i < 1; i++) {
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
                pauseTest(1000);
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
        TC2854_Prüfen_H1_Tag test = new TC2854_Prüfen_H1_Tag();
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
        WebElement h1_Tag;

        for (int i = 0; i < 12; i++) {
            pauseTest(1000);
            h1_Tag = getWebElement(globalVar._BOX_WETTER_h1);

            switch (i) {
                case 0:
                    result = resultTemp;
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='T-self.parsedId-1']/div[2]/p[1]/a").click();
                    break;

                case 1:
                    result = resultTemp + " heute";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[2]/a").click();

                    break;

                case 2:
                    result = resultTemp + " morgen";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[3]/a").click();
                    break;

                case 3:
                    result = resultTemp + " übermorgen";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[4]/a").click();
                    break;

                case 4:
                    result = resultTemp + " in 3 Tagen";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[5]/a").click();
                    break;

                case 5:
                    result = resultTemp + " in 4 Tagen";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[6]/a").click();
                    break;

                case 6:
                    result = resultTemp + " in 5 Tagen";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[7]/a").click();
                    break;

                case 7:
                    result = resultTemp + " in 6 Tagen";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[8]/a").click();
                    break;

                case 8:
                    result = resultTemp + " in 7 Tagen";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[9]/a").click();
                    break;

                case 9:
                    result = resultTemp + " in 8 Tagen";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[10]/a").click();
                    break;

                case 10:
                    result = resultTemp + " in 9 Tagen";
                    search = h1_Tag.getText();

                    if (!search.contains(result)) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), search, result);
                    }
                    //getWebElement(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]").click();
                    getWebElement("//*[@id=\"Tcontbox\"]/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]/i").click();
                    //*[@id="Tcontbox"]/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]
                    //*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]
                    break;

                case 11:
                    result = resultTemp + " für 10 Tage";
                    search = h1_Tag.getText();

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

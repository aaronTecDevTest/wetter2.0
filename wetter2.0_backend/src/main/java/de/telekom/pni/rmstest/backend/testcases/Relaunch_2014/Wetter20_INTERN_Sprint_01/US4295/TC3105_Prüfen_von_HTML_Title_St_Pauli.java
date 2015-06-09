package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4295;

import java.util.ArrayList;
import java.util.List;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import org.openqa.selenium.Keys;

/**
 * @author a.kutekidila
 */
public class TC3105_Prüfen_von_HTML_Title_St_Pauli extends GenericTest_New {

    GlobalVar globalVar = null;
    List<String> resultString2 = null;

    /**
     *
     */
    public TC3105_Prüfen_von_HTML_Title_St_Pauli() {
    }

    @Override
    public void runTest() {
        globalVar = getGlobalVar();
        resultString2 = new ArrayList<>();

     //   setRunningConfiguration(new RunningConfiguration_New("CH",""));

        globalVar.searchStringList.add("Hamburg Pauli");

        globalVar.resultStringList.add("Hamburt St. Pauli");

        resultString2.add("Hamburg St. Pauli");


        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        try {

            for (int i = 0; i < 1; i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(500);
                getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);
                setInputFeldValue(globalVar._INPUT_BOX, "\n");
                /**
                 * Result
                 */
                pauseTest(1000);
                if (checkTitle()) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
                getBrowser().navigate().back();
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
        TC3105_Prüfen_von_HTML_Title_St_Pauli test = new TC3105_Prüfen_von_HTML_Title_St_Pauli();
        test.before();
        test.runTest();
        test.after();

    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }

    public boolean checkTitle() {
        boolean check = true;
        String result = null;
        String search = null;

        for (int i = 0; i < 11; i++) {
            pauseTest(1000);
            switch (i) {
                case 0:
                    result = "Wetter Hamburg St. Pauli – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='T-self.parsedId-1']/div[2]/span[6]/a").click();
                    break;

                case 1:
                    result = "Wetter Hamburg St. Pauli heute – Wettervorhersage aktuell für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[2]/a").click();
                    break;

                case 2:
                    result = "Wetter Hamburg St. Pauli morgen – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[3]/a").click();
                    break;

                case 3:
                    result = "Wetter Hamburg St. Pauli übermorgen – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[4]/a").click();
                    break;

                case 4:
                    result = "Wetter Hamburg St. Pauli in 3 Tagen – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[5]/a").click();
                    break;

                case 5:
                    result = "Wetter Hamburg St. Pauli in 4 Tagen – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[6]/a").click();
                    break;

                case 6:
                    result = "Wetter Hamburg St. Pauli in 5 Tagen – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[7]/a").click();
                    break;

                case 7:
                    result = "Wetter Hamburg St. Pauli in 6 Tagen – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[8]/a").click();
                    break;

                case 8:
                    result = "Wetter Hamburg St. Pauli in 7 Tagen – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[9]/a").click();
                    break;

                case 9:
                    result = "Wetter Hamburg St. Pauli in 8 Tagen – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[10]/a").click();
                    break;

                case 10:
                    result = "Wetter Hamburg St. Pauli in 9 Tagen – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/" +
                            "div[3]/div[2]/div[3]/div[1]/div/a[2]/i").click();
                    break;

                case 11:
                    result = "Wetter Hamburg St. Pauli für 10 Tage – Wettervorhersage für Hamburg St. Pauli bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    break;

                default:
                    break;
            }
        }
        return check;
    }
}

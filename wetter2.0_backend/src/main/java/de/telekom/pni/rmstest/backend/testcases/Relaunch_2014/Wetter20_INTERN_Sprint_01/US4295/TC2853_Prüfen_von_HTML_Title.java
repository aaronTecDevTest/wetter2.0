/**
 *
 */
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
public class TC2853_Prüfen_von_HTML_Title extends GenericTest_New {

    GlobalVar globalVar = null;
    List<String> resultString2 = null;

    /**
     *
     */
    public TC2853_Prüfen_von_HTML_Title() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {

        globalVar = getGlobalVar();
        resultString2 = new ArrayList<String>();

        globalVar.searchStringList.add("64283 Darmstadt");
        globalVar.searchStringList.add("Zwingenberg (Bergstraße)");
        globalVar.searchStringList.add("Kleinenborstel (Martfeld)");
        globalVar.searchStringList.add("Berlin");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Postkamp (Altenholz)");
        globalVar.searchStringList.add("New York");
        globalVar.searchStringList.add("Zwingenberg (Bergstraße)");
        globalVar.searchStringList.add("Zwingenberg (Bergstraße)");

        globalVar.resultStringList.add("Darmstadt");
        globalVar.resultStringList.add("Zwingenberg (Bergstraße)");
        globalVar.resultStringList.add("Kleinenborstel (Martfeld)");
        globalVar.resultStringList.add("Berlin");
        globalVar.resultStringList.add("Paris");
        globalVar.resultStringList.add("Postkamp (Altenholz)");
        globalVar.resultStringList.add("New York");
        globalVar.resultStringList.add("Zwingenberg (Bergstraße)");
        globalVar.resultStringList.add("Zwingenberg (Bergstraße)");

        resultString2.add("Darmstadt");
        resultString2.add("wetter-zwingenberg-bergstraße");
        resultString2.add("wetter-kleinenborstel-martfeld");
        resultString2.add("wetter-berlin");
        resultString2.add("wetter-paris");
        resultString2.add("wetter-postkamp-altenholz");
        resultString2.add("wetter-new-york");
        resultString2.add("wetter-zwingenberg-bergstraße");
        resultString2.add("wetter-zwingenberg-bergstraße");

      //  setRunningConfiguration(new RunningConfiguration_New("CH",globalVar.__StartSeiteFavoriten__));
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

                if (checkTitle()) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
            }
            getBrowser().navigate().back();
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " AD-Elements could be not found in Web";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2853_Prüfen_von_HTML_Title test = new TC2853_Prüfen_von_HTML_Title();
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
            switch (i) {
                case 0:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='T-self.parsedId-1']/div[2]/span[6]/a").click();
                    break;

                case 1:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " heute – Wettervorhersage aktuell für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[2]/a").click();
                    break;

                case 2:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " morgen – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[3]/a").click();
                    break;

                case 3:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " übermorgen – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[4]/a").click();
                    break;

                case 4:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " in 3 Tagen – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[5]/a").click();
                    break;

                case 5:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " in 4 Tagen – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[6]/a").click();
                    break;

                case 6:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " in 5 Tagen – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[7]/a").click();
                    break;

                case 7:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " in 6 Tagen – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[8]/a").click();
                    break;

                case 8:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " in 7 Tagen – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[9]/a").click();
                    break;

                case 9:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " in 8 Tagen – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[10]/a").click();
                    break;

                case 10:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " in 9 Tagen – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    getWebElement(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]").click();
                    break;

                case 11:
                    result = "Wetter " + globalVar.resultStringList.get(0) +
                            " für 10 Tage – Wettervorhersage für " + resultString2.get(0) +
                            " bei wetter.info";
                    search = getBrowser().getTitle();
                    if (!search.contains(result)) {
                        check = check && false;
                        logFailureCheckpoint(((Integer.toString(i))), search, result);
                    }
                    break;

                default:
                    break;
            }
            pauseTest(1000);
        }
        return check;
    }
}

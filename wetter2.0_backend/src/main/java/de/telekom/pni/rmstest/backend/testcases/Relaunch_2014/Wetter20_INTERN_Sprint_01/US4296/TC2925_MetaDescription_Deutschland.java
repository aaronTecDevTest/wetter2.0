/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4296;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import org.openqa.selenium.Keys;

/**
 * @author a.kutekidila
 */
public class TC2925_MetaDescription_Deutschland extends GenericTest_New {

    GlobalVar globalVar = null;
    List<String> kreis = null;


    public TC2925_MetaDescription_Deutschland() {

    }


    @Override
    public void runTest() {

        globalVar = getGlobalVar();
        kreis = new ArrayList<String>();

        globalVar.searchStringList.add("Darmstadt");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Zwingenberg (Bergstraße)");
        globalVar.searchStringList.add("Kleinenborstel (Martfeld)");
        globalVar.searchStringList.add("Berlin");
        globalVar.searchStringList.add("Postkamp (Altenholz)");
        globalVar.searchStringList.add("New York");
        globalVar.searchStringList.add("Zwingenberg (Bergstraße)");
        globalVar.searchStringList.add("Zwingenberg (Bergstraße)");


        globalVar.resultStringList.add("  Darmstadt");
        globalVar.resultStringList.add("Paris");
        globalVar.resultStringList.add("Kreis Bergstraße");
        globalVar.resultStringList.add("Kleinenborstel (Martfeld)");
        globalVar.resultStringList.add("Berlin");
        globalVar.resultStringList.add("Postkamp (Altenholz)");
        globalVar.resultStringList.add("New York");
        globalVar.resultStringList.add("Zwingenberg (Bergstraße)");
        globalVar.resultStringList.add("Zwingenberg (Bergstraße)");


        kreis.add(" Darmstadt in Hessen");
        kreis.add("wetter-paris");
        kreis.add("Zwingenberg (Bergstraße) in Hessen.");
        kreis.add("wetter-kleinenborstel-martfeld");
        kreis.add("wetter-berlin");
        kreis.add("wetter-postkamp-altenholz");
        kreis.add("wetter-new-york");
        kreis.add("wetter-zwingenberg-bergstraße");
        kreis.add("wetter-zwingenberg-bergstraße");

        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);
        try {

            for (int i = 0; i < 1; i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                pauseTest(1000);
                getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);
                pauseTest(1000);
                getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);
                pauseTest(1000);
                setInputFeldValue(globalVar._INPUT_BOX, "\n");

                if (checkTitle(globalVar.searchStringList.get(i),
                        globalVar.resultStringList.get(i),
                        kreis.get(i))) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
            }
            navigate(globalVar.__StartSeiteFavoriten__);
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " AD-Elements could be not found in Web";
            logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2925_MetaDescription_Deutschland test = new TC2925_MetaDescription_Deutschland();
        test.before();
        test.runTest();
        test.after();

    }


    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }

    public boolean checkTitle(String stadt, String kreis, String bundesland) {
        boolean check = true;
        String result;
        String descriptionResult;
        String description = "/html/head/meta[9]";

        for (int i = 0; i < 12; i++) {
            switch (i) {
                case 0:
                    result = "Das Wetter aktuell für "
                            + stadt
                            + ", der Wetterbericht sowie die 9-Tages Wettervorhersage für den"
                            + kreis
                            + ". So wird das Wetter in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='T-self.parsedId-1']/div[2]/span[6]/a").click();
                    break;

                case 1:
                    //  Wetter Darmstadt heute  – Wettervorhersage aktuell für wetter-darmstadt bei wetter.info
                    result = "Das Wetter heute für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter heute in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[2]/a").click();
                    break;

                case 2:
                    //  Wetter Darmstadt morgen  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter morgen für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter morgen in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[3]/a").click();
                    break;

                case 3:
                    //  Wetter Darmstadt übermorgen  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter übermorgen für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter übermorgen in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[4]/a").click();
                    break;

                case 4:
                    //  Wetter Darmstadt in 3 Tagen  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter in 3 Tagen für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter in 3 Tagen in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[5]/a").click();
                    break;

                case 5:
                    //  Wetter Darmstadt in 4 Tagen  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter in 4 Tagen für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter in 4 Tagen in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[6]/a").click();
                    break;

                case 6:
                    //  Wetter Darmstadt in 5 Tagen  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter in 5 Tagen für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter in 5 Tagen in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[7]/a").click();
                    break;

                case 7:
                    //    Wetter Darmstadt in 6 Tagen  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter in 6 Tagen für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter in 6 Tagen in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[8]/a").click();
                    break;

                case 8:
                    //   Wetter Darmstadt in 7 Tagen  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter in 7 Tagen für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter in 7 Tagen in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[9]/a").click();
                    break;

                case 9:
                    //   Wetter Darmstadt in 8 Tagen  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter in 8 Tagen für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter in 8 Tagen in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[10]/a").click();
                    break;

                case 10:
                    //    Wetter Darmstadt in 9 Tagen  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter in 9 Tagen für "
                            + stadt + " im" + kreis
                            + ": So wird das Wetter in 9 Tagen in"
                            + bundesland + ".";
                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
                    }
                    getWebElement(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]").click();
                    break;

                case 11:
                    //      Wetter Darmstadt für 10 Tage  – Wettervorhersage für wetter-darmstadt bei wetter.info
                    result = "Das Wetter in "
                            + stadt + " im" + kreis
                            + " für 10 Tage: So wird das Wetter in den nächsten 10 Tagen in"
                            + bundesland + ".";

                    descriptionResult = getBrowser().findElement(By.xpath(description)).getAttribute("content");
                    if (!descriptionResult.contains(result)) {
                        check = false;
                        logFailureCheckpoint(Integer.toString(i), descriptionResult, result);
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

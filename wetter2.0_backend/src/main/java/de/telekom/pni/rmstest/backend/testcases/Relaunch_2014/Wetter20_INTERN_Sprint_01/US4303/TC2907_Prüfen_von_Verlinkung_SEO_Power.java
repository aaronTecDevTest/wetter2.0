/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4303;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a.kutekidila
 */
public class TC2907_Prüfen_von_Verlinkung_SEO_Power extends GenericTest_New {
    private GlobalVar globalVar;
    private RunningConfiguration_New rc;

    public TC2907_Prüfen_von_Verlinkung_SEO_Power() {

    }

    @Override
    public void runTest() {

        globalVar = getGlobalVar();
        rc = getRunningConfiguration();

        //Gilt nur für t-online.de/wetter
        String seoPower;
        WebElement seoPowerLinke = null;

        if (rc == null) {
            globalVar.__StartSeiteFavoriten__ = "http://cm7prev.ada.t-online.de/toiPortal/servlet/wetter/sp_moskau/12347708";
            globalVar._INPUT_BOX = "//*[@id=\"searchPattern\"]";
            seoPower = "//*[@id=\"Twtosearch_pow\"]/a";
        } else {
            globalVar.__StartSeiteFavoriten__ = "www.t-online.de/wetter";
            globalVar._INPUT_BOX = "//*[@id=\"searchPattern\"]]";
            seoPower = "//*[@id=\"Twtosearch_pow\"]/a";
        }

        globalVar.searchStringList.add("Groß-Gerau");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Zweibrücken");
        globalVar.searchStringList.add("Offenbach");
        globalVar.searchStringList.add("Chicago");
        globalVar.searchStringList.add("Kinshasa");
        globalVar.searchStringList.add("London");
        globalVar.searchStringList.add("Kapstadt");

        globalVar.resultStringList.add("wetter-deutschland/hessen/kreis-gross-gerau/wetter-gross-gerau/K06433006");
        globalVar.resultStringList.add("wetter-frankreich/ile-de-france/wetter-paris/N-2085971");
        globalVar.resultStringList.add("wetter-deutschland/rheinland-pfalz/kreisfreie-stadt-zweibruecken/wetter-zweibruecken/K07320000");
        globalVar.resultStringList.add("wetter-deutschland/hessen/kreisfreie-stadt-offenbach-am-main/wetter-offenbach-am-main/K06413000");
        globalVar.resultStringList.add("wetter-vereinigte-staaten/illinois/wetter-chicago/Ufee8ca65");
        globalVar.resultStringList.add("wetter-demokratische-republik-kongo/kinshasa/wetter-kinshasa/N-2843631");
        globalVar.resultStringList.add("wetter-vereinigtes-konigreich/england/wetter-london/N-3578172");
        globalVar.resultStringList.add("wetter-sudafrika/westkap/wetter-kapstadt/N-1785105");

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
                pauseTest(500);
                getWebElement(globalVar._INPUT_BOX).sendKeys(Keys.ARROW_DOWN);

                getWebElement(globalVar._INPUT_BOX).sendKeys("\n");
                pauseTest(500);

                seoPowerLinke = getWebElement(seoPower);

                if (checkSEOLink(seoPowerLinke)) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
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
        TC2907_Prüfen_von_Verlinkung_SEO_Power test = new TC2907_Prüfen_von_Verlinkung_SEO_Power();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }

    boolean checkSEOLink(WebElement we) {
        boolean check = true;
        String href = we.getAttribute("href");
        String preLink = "www.wetter.info/";
        if (rc != null) {
            globalVar.stringResult = preLink + globalVar.stringResult;
            return href.contains(globalVar.stringResult) ? check : false;
        }
        return href.contains(globalVar.stringResult) ? check : false;
    }
}


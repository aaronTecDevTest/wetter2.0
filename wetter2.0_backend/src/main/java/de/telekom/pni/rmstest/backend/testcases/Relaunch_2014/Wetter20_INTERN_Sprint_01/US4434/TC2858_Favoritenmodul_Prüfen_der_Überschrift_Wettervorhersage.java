/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4434;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * @author a.kutekidila
 */
public class TC2858_Favoritenmodul_Prüfen_der_Überschrift_Wettervorhersage extends GenericTest_New {
    private GlobalVar globalVar;
    /**
     *
     */
    public TC2858_Favoritenmodul_Prüfen_der_Überschrift_Wettervorhersage() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("Riedstadt");
        globalVar.searchStringList.add("Crumstadt");
        globalVar.searchStringList.add("Zwingenberg");
        globalVar.searchStringList.add("Burow");
        globalVar.searchStringList.add("Stuttgart Bad Cannstatt");
        globalVar.searchStringList.add("Berlin Mitte");
        globalVar.searchStringList.add("Frankfurt am Main");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("New York");
        globalVar.searchStringList.add("London");
    //    globalVar.searchStringList.add("Tokio");
        globalVar.searchStringList.add("Peking");


        globalVar.resultStringList.add("Riedstadt");
        globalVar.resultStringList.add("Crumstadt");
        globalVar.resultStringList.add("Zwingenberg");
        globalVar.resultStringList.add("Burow");
        globalVar.resultStringList.add("Stuttgart");
        globalVar.resultStringList.add("Berlin");
        globalVar.resultStringList.add("Frankfurt am Main");
        globalVar.resultStringList.add("Paris");
        globalVar.resultStringList.add("New York (NY)");
        globalVar.resultStringList.add("London");
      //  globalVar.resultStringList.add("Tokyo Narita");
        globalVar.resultStringList.add("Peking");

        setRunningConfiguration(new RunningConfiguration_New("CH", globalVar.__StartSeiteFavoriten__));
        //Step1
        getBrowser();
        //Step2
        navigate(globalVar.__StartSeiteFavoriten__);
        //Step3
        try {
            checkClickeElement(".//*[@id='Tsetfav2_ac']/ul/li[2]/a", globalVar.__FAVORETEN_MODUL_MeineFavoriten_Suchfeld1__, ".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/ul/li[1]/a");

            checkClickeElement(".//*[@id='Tsetfav3_ac']/ul/li[2]/a", globalVar.__FAVORETEN_MODUL_MeineFavoriten_Suchfeld2__, ".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/ul/li[2]/a");

            checkClickeElement(".//*[@id='Tsetfav4_ac']/ul/li[2]/a", globalVar.__FAVORETEN_MODUL_MeineFavoriten_Suchfeld3__,".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/ul/li[3]/a");


            checkClickButton(globalVar.__FAVORETEN_MODUL_MeineFavoriten_Speicher1__, globalVar.__FAVORETEN_MODUL_MeineFavoriten_Suchfeld1__, "//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/ul/li[1]/a");

            checkClickButton(globalVar.__FAVORETEN_MODUL_MeineFavoriten_Speicher2__, globalVar.__FAVORETEN_MODUL_MeineFavoriten_Suchfeld2__, "//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/ul/li[2]/a");

            checkClickButton(globalVar.__FAVORETEN_MODUL_MeineFavoriten_Speicher3__, globalVar.__FAVORETEN_MODUL_MeineFavoriten_Suchfeld3__, "//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/ul/li[3]/a");
        } catch (Exception e) {
            logFailureCheckpoint("Try", globalVar.stringSearch, e.getStackTrace().toString());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2858_Favoritenmodul_Prüfen_der_Überschrift_Wettervorhersage test = new
        TC2858_Favoritenmodul_Prüfen_der_Überschrift_Wettervorhersage();
        test.before();
        test.runTest();
        test.after();

    }

    public void checkClickeElement(String elementToClick, String inputFeld, String outPutFeld) {
        String city;
        WebElement element;

        for(int i =8; i<globalVar.searchStringList.size(); i++ ) {
            globalVar.stringSearch = globalVar.searchStringList.get(i);

            getWebElement(globalVar.__FAVORETEN_MODUL__Zahnrad__).click();
            pauseTest(500);
            getWebElement(globalVar.__FAVORETEN_MODUL_MeineFavoriten__).click();

            setInputFeldValue(inputFeld, globalVar.stringSearch);
            pauseTest(500);

            element = getWebElement(elementToClick);
            element.click();
            pauseTest(2000);

            city = getWebElement(outPutFeld).getText();

            if(!city.contains(globalVar.resultStringList.get(i))) {
                logFailureCheckpoint(Integer.toString(i)+" Click on AutoSuggest",globalVar.resultStringList.get(i), city);
            }
        }
    }

    public void checkClickButton(String buttonToclick, String inputFeld, String outPutFeld) {
        String city;

        for(int i =8; i<globalVar.searchStringList.size(); i++ ) {
            globalVar.stringSearch = globalVar.searchStringList.get(i);

            getWebElement(globalVar.__FAVORETEN_MODUL__Zahnrad__).click();
            pauseTest(500);
            getWebElement(globalVar.__FAVORETEN_MODUL_MeineFavoriten__).click();

            setInputFeldValue(inputFeld, globalVar.stringSearch);
            pauseTest(500);

            getWebElement(inputFeld).sendKeys(Keys.ARROW_DOWN);
            pauseTest(500);

            getWebElement(buttonToclick).click();
            pauseTest(2000);

            city = getWebElement(outPutFeld).getText();

            if(!city.contains(globalVar.resultStringList.get(i))) {
                logFailureCheckpoint(Integer.toString(i)+ " Click on Button \"Speichern\"",globalVar.resultStringList.get(i), city);
            }
        }
    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.GenericTest_New#setRunningConfiguration(de.telekom.pni.rmstest.backend.config.RunningConfiguration_New)
     */
    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }
}

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
public class TC3167_Favoritenmodul_Suche_Nach_Stadt_Favoriten extends GenericTest_New {
    private GlobalVar globalVar;

    /**
     *
     */
    public TC3167_Favoritenmodul_Suche_Nach_Stadt_Favoriten() {

    }

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
        globalVar.searchStringList.add("Tokio");
        globalVar.searchStringList.add("Peking");


        globalVar.resultStringList.add("Wetter Riedstadt");
        globalVar.resultStringList.add("Wetter Crumstadt");
        globalVar.resultStringList.add("Wetter Zwingenberg (Baden)");
        globalVar.resultStringList.add("Wetter Burow");
        globalVar.resultStringList.add("Wetter Stuttgart");
        globalVar.resultStringList.add("Wetter Berlin");
        globalVar.resultStringList.add("Wetter Frankfurt am Main");
        globalVar.resultStringList.add("Wetter Paris");
        globalVar.resultStringList.add("Wetter New York (NY)");
        globalVar.resultStringList.add("Wetter London");
        globalVar.resultStringList.add("Wetter Tokyo Narita/New Intl");
        globalVar.resultStringList.add("Wetter Peking");

        setRunningConfiguration(new RunningConfiguration_New("CH", globalVar.__StartSeiteFavoriten__));
        //Step1
        getBrowser();
        //Step2
        navigate(globalVar.__StartSeiteFavoriten__);
        //Step3
        try {
            checkClickeElement();
            checkClickButton();
        } catch (Exception e) {
            logFailureCheckpoint("Try", globalVar.stringSearch, e.getStackTrace().toString());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC3167_Favoritenmodul_Suche_Nach_Stadt_Favoriten test = new TC3167_Favoritenmodul_Suche_Nach_Stadt_Favoriten();
        test.before();
        test.runTest();
        test.after();
    }

    public void checkClickeElement() {
        String city;
        WebElement element;

        for(int i =0; i<globalVar.searchStringList.size(); i++ ) {
            globalVar.stringSearch = globalVar.searchStringList.get(i);

            getWebElement(globalVar.__FAVORETEN_MODUL__Zahnrad__).click();
            pauseTest(500);
            getWebElement(globalVar.__FAVORETEN_MODUL_MeinStadt__).click();

            setInputFeldValue(globalVar.__FAVORETEN_MODUL_MeinStadt_Suchfeld___, globalVar.stringSearch);
            pauseTest(500);

            element = getWebElement(".//*[@id='Tsetfav1_ac']/ul/li[2]/a/span[1]");
            element.click();
            pauseTest(2000);

            city = getWebElement(globalVar._WETTER_ORT_H1).getText();

            if(!city.contains(globalVar.resultStringList.get(i))) {
                logFailureCheckpoint(Integer.toString(i)+" Click on AutoSuggest",globalVar.resultStringList.get(i), city);
            }
        }
    }

    public void checkClickButton() {
        String city;

        for(int i =0; i<globalVar.searchStringList.size(); i++ ) {
            globalVar.stringSearch = globalVar.searchStringList.get(i);

            getWebElement(globalVar.__FAVORETEN_MODUL__Zahnrad__).click();
            pauseTest(500);
            getWebElement(globalVar.__FAVORETEN_MODUL_MeinStadt__).click();

            setInputFeldValue(globalVar.__FAVORETEN_MODUL_MeinStadt_Suchfeld___, globalVar.stringSearch);
            pauseTest(500);

            getWebElement(globalVar.__FAVORETEN_MODUL_MeinStadt_Suchfeld___).sendKeys(Keys.ARROW_DOWN);
            pauseTest(500);

            getWebElement(globalVar.__FAVORETEN_MODUL_MeinStadt_Speichern___).click();
            pauseTest(2000);

            city = getWebElement(globalVar._WETTER_ORT_H1).getText();

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


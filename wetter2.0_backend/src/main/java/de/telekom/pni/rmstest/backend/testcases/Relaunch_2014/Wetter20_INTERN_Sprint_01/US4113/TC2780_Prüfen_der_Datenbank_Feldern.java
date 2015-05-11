/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4113;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */
public class TC2780_Prüfen_der_Datenbank_Feldern extends GenericTest_New {

    private GlobalVar globalVar = null;

    @Override
    public void runTest() {

        globalVar = getGlobalVar(); // GlobalVarTOnline oder

        globalVar.searchStringList.add("COUNTRY_SHORT");
        globalVar.searchStringList.add("COUNTRY");
        globalVar.searchStringList.add("COUNTRY_RANKING");
        globalVar.searchStringList.add("BUNDESLAND_NAME");
        globalVar.searchStringList.add("BUNDESLAND_TYP");
        globalVar.searchStringList.add("LANDKREIS_BEZEICHNUNG");
        globalVar.searchStringList.add("LANDKREIS_NAME");
        globalVar.searchStringList.add("LANDKREIS_NAMENSZUSATZ");
        globalVar.searchStringList.add("POSTLEITZAHL");
        globalVar.searchStringList.add("WOHNORT_NAME");
        globalVar.searchStringList.add("WOHNORT_NAMENSZUSATZ");
        globalVar.searchStringList.add("WOHNORT_SYNONYM");
        globalVar.searchStringList.add("WOHNORT_LOKAL");
        globalVar.searchStringList.add("WOHNORT_ZENTRUM");
        globalVar.searchStringList.add("WOHNORT_STADTTEIL_NAME");
        globalVar.searchStringList.add("WOHORT_STADTEIL_ZENTRUM");
        globalVar.searchStringList.add("TOP_WOHNORT");
        globalVar.searchStringList.add("POPULATION");
        globalVar.searchStringList.add("CODE_UNI");
        globalVar.searchStringList.add("LAT");
        globalVar.searchStringList.add("LON");
        globalVar.searchStringList.add("URL_DEFAULT");
        globalVar.searchStringList.add("URL_TOI");
        globalVar.searchStringList.add("URL_ALT");
        globalVar.searchStringList.add("LOCATION_ROMAN");
        globalVar.searchStringList.add("WOHNORT_GEMEINDESCHLUESSEL");
        globalVar.searchStringList.add("POSTLEITZAHL_LISTE");
        globalVar.searchStringList.add("WOHNORT_POPULATION");
        globalVar.searchStringList.add("POSTLEITZAHL_GENAUIGKEIT");
        globalVar.searchStringList.add("DARSTELLUNG");
        globalVar.searchStringList.add("LAR_RANGE");
        globalVar.searchStringList.add("LONG_RANGE");
        globalVar.searchStringList.add("PLACE_ID");
        globalVar.searchStringList.add("USE_STATION");


        File file = new File("U:\\01_Dev\\Wetter_2.0\\Testcode\\workspace\\wetter2.0\\wetter2.0_backend\\src\\main\\resources\\jsonview-0.9-fx.xpi");
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        try {
            firefoxProfile.addExtension(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Step 1
        this.setBrowserFirefox(firefoxProfile);
        //this.getBrowser();
        //Step 2
        this.navigate("http://suche.wetter.info/ariadne/wsuggestion?location=Darmstadt&limitNumber=10&limitCountries=DE,AT&countryPriority=AT,DE");
        this.pauseTest(2000);

        //Step 3 to ??
        List<WebElement> dataBaseFelds = getWebElements(".//*[@id='json']/ul/li[1]/ul/li/ul/li[1]/ul/li[*]/span[1]");

        for (String searchString : globalVar.searchStringList) {
            String foundedField = searchDBField(searchString, dataBaseFelds);
            if (!(foundedField == null)) {
                this.logSuccessCheckpoint("Prüfen des Feld: " + searchString, foundedField);
            } else {
                foundedField = "Feld nicht gefunden.";
                this.logFailureCheckpoint("Prüfen des Feld: " + searchString, searchString, foundedField);
            }
        }
    }


    private String searchDBField(String searchString, List<WebElement> listOfWebElment) {

        String iteamFound = null;
        for (WebElement webElementString : listOfWebElment) {
            if (webElementString.getText().contains(searchString)) {
                return iteamFound = webElementString.getText();
            }
        }
        return iteamFound;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2780_Prüfen_der_Datenbank_Feldern test = new TC2780_Prüfen_der_Datenbank_Feldern();
        test.before();
        test.runTest();
        test.after();

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#setRunningConfiguration(de.telekom.pni.rmstest.backend.config.RunningConfiguration_New)
     */
    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }

}

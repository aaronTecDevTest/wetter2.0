package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US5430;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

public class TC3016_Direkte_Verlinkung_Wetterdetailsseite extends GenericTest_New {


    private GlobalVar globalVar = null;


    @Override
    public void runTest() {

        boolean isSuchergebnisOK = false;
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("Berlin");
        globalVar.searchStringList.add("London");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Adelaide");
        globalVar.searchStringList.add("Albuquerque");
        globalVar.searchStringList.add("Belfast");
        globalVar.searchStringList.add("New York");

        globalVar.resultStringList.add("kreisfreie-stadt-berlin/wetter-berlin/K11000000");
        globalVar.resultStringList.add("wetter-vereinigtes-konigreich/england/wetter-london/N-3578172");
        globalVar.resultStringList.add("wetter-frankreich/ile-de-france/wetter-paris/N-2085971");
        globalVar.resultStringList.add("wetter-australien/south-australia/wetter-adelaide/S946750");
        globalVar.resultStringList.add("wetter-vereinigte-staaten/new-mexico/wetter-albuquerque/S723650");
        globalVar.resultStringList.add("wetter-vereinigtes-konigreich/nordirland/wetter-belfast/N-356297");
        globalVar.resultStringList.add("wetter-vereinigte-staaten/new-york/wetter-new-york-city/U03690c3c");

        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate(globalVar.__StartSeiteFavoriten__);

        try {
            for (int i = 0; i < globalVar.resultStringList.size(); i++) {

                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);

                setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
                getWebElement(globalVar._INPUT_BOX).submit();
                openSuchergebnisOrte(globalVar._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS);

                WebElement elementToClick = getWebElement(globalVar._SUCHERGEBNISSEITE_TOP_ORT_P);
                isSuchergebnisOK = checkDirectLink(elementToClick, globalVar.stringResult);

                if (isSuchergebnisOK) {
                    this.logSuccessCheckpoint("Prüfen des Verlinkung:", "Wetterseite für die 3-Tage-Ansicht wird angezeigt.");
                } else {
                    this.logFailureCheckpoint("Fehler (" + globalVar.searchStringList.get(i) + "):", "Gesuchten Suchergebnissen nicht gefunden bitte prüfen.", "null");
                }
            }

            openSuchergebnisOrte(globalVar._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS);
        } catch (Exception e) {
            this.logFailureCheckpoint("Fehler:", "Link nicht gefunden.", "");
        }
    }

    private boolean checkDirectLink(WebElement elementToClick, String url) {
        boolean isOK = false;
        /*String teilURL = "wetter/wetter-deutschland/berlin/kreisfreie-stadt-berlin/wetter-berlin/K11000000";
		elementLink.click();
		String currentURL = getBrowser().getCurrentUrl();
		if(currentURL.contains(teilURL)){
			isOK = true;
		}*/
        String elementUrl = elementToClick.getAttribute("href");
        if (elementUrl.contains(url)) {
            isOK = true;
        }
        return isOK;
    }

    public static void main(String[] args) {
        TC3016_Direkte_Verlinkung_Wetterdetailsseite test = new TC3016_Direkte_Verlinkung_Wetterdetailsseite();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

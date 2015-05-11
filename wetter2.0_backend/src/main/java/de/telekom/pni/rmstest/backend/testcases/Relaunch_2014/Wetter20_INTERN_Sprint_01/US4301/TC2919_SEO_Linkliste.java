/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4301;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */
public class TC2919_SEO_Linkliste extends GenericTest_New {
    private GlobalVar globalVar;

    /**
     *
     */
    public TC2919_SEO_Linkliste() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {

        globalVar = getGlobalVar();

        //TODO ein andere beispiel nehemen.
        globalVar.searchStringList.add("Wetter La Cadière");

        globalVar.resultStringList.add("Wetter Paris");
        globalVar.resultStringList.add("Wetter Marseille");
        globalVar.resultStringList.add("Wetter Lyon");
        globalVar.resultStringList.add("Wetter Toulouse");
        globalVar.resultStringList.add("Wetter Nizza");
        globalVar.resultStringList.add("Wetter Nantes");

        globalVar.resultStringList.add("Wetter Straßburg");
        globalVar.resultStringList.add("Wetter Montpellier");
        globalVar.resultStringList.add("Wetter Bordeaux");
        globalVar.resultStringList.add("Wetter Lille");
        globalVar.resultStringList.add("Wetter Rennes");
        globalVar.resultStringList.add("Wetter Reims");

        globalVar.resultStringList.add("Wetter Le Havre");
        globalVar.resultStringList.add("Wetter Toulon");
        globalVar.resultStringList.add("Wetter Angers");
        globalVar.resultStringList.add("Wetter Grenoble");
        globalVar.resultStringList.add("Wetter Dijon");
        globalVar.resultStringList.add("Wetter Aix-en-Provence");

        globalVar.stringSearch = globalVar.searchStringList.get(0);
        getBrowser();

        //Step 2
        navigate("http://portal.wetter.info/winfo/ausland/wetter-frankreich/provence-alpes-cote-dazur/wetter-la-cadiere-dazur/N-2055722");//globalVar.__SUCHWEBSIDE_3TageAnsicht_Preview__);
        //setInputFeldValue(globalVar._INPUT_BOX, globalVar.searchStringList.get(0));
        //setInputFeldValueClick(globalVar._INPUT_BOX, globalVar.stringSearch, globalVar.stringSearch, globalVar);

        //Step 3 ....
        List<WebElement> listOfSeoLink = new ArrayList<WebElement>();
        List<WebElement> lsitOfSpalten = new ArrayList<WebElement>();


        listOfSeoLink = getWebElements(globalVar._BOX_WETTER__WETTER_SEO_LINKS_Kreis);
        lsitOfSpalten = getWebElements(globalVar._BOX_WETTER__WETTER_SEO_LINK_SPALTEN);
        try {
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);

                /**
                 * Result
                 */
                if (listOfSeoLink != null) {
                    if (checkAnzahlOrte(listOfSeoLink) && checkOrteLndkreis(listOfSeoLink) && checkSpaltenAnzahl(lsitOfSpalten)) {
                        globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                        //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                    } else {
                        globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                        this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                    }
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
        TC2919_SEO_Linkliste test = new TC2919_SEO_Linkliste();
        test.before();
        test.runTest();
        test.after();

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.GenericTest_New#setRunningConfiguration(de.telekom.pni.rmstest.backend.config.RunningConfiguration_New)
     */
    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }

    boolean checkAnzahlOrte(List<WebElement> listOfOrte) {
        boolean check = false;
        int anzahlMax = 18;
        int anzahlOrte = listOfOrte.size();

        if (anzahlOrte <= anzahlMax) {
            check = true;
        }
        return check;
    }

    boolean checkOrteLndkreis(List<WebElement> listOfOrte) {
        boolean check = false;
        List<String> temp = new ArrayList<String>();

        for (WebElement we : listOfOrte) {
            temp.add(we.getText());
        }

        temp.removeAll(globalVar.resultStringList);

        if (temp.size() <= 0) {
            check = true;
        }

        return check;
    }

    boolean checkSpaltenAnzahl(List<WebElement> listOfSpalte) {
        boolean check = false;
        int anzahlSpalte = listOfSpalte.size();
        final int maxSpalte = 3;

        if (anzahlSpalte <= maxSpalte) {
            check = true;
        }
        return check;
    }
}

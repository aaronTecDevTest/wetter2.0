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
public class TC2915_SEO_Linkliste extends GenericTest_New {
    private GlobalVar globalVar;

    /**
     *
     */
    public TC2915_SEO_Linkliste() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {

        globalVar = getGlobalVar();

        //TODO ein andere beispiel nehemen.
        globalVar.searchStringList.add("64521 Groß-Gerau");

        globalVar.resultStringList.add("Wetter Rüsselsheim");
        globalVar.resultStringList.add("Wetter Groß-Gerau");
        globalVar.resultStringList.add("Wetter Walldorf");
        globalVar.resultStringList.add("Wetter Mörfelden");
        globalVar.resultStringList.add("Wetter Raunheim");
        globalVar.resultStringList.add("Wetter Gernsheim");
        globalVar.resultStringList.add("Wetter Bischofsheim");
        globalVar.resultStringList.add("Wetter Kelsterbach");
        globalVar.resultStringList.add("Wetter Ginsheim");
        globalVar.resultStringList.add("Wetter Nauheim");
        globalVar.resultStringList.add("Wetter Königstädten");
        globalVar.resultStringList.add("Wetter Trebur");
        globalVar.resultStringList.add("Wetter Gustavsburg");
        globalVar.resultStringList.add("Wetter Biebesheim am Rhein");
        globalVar.resultStringList.add("Wetter Büttelborn");
        globalVar.resultStringList.add("Wetter Goddelau");
        globalVar.resultStringList.add("Wetter Bauschheim");
        globalVar.resultStringList.add("Wetter Stockstadt am Rhein");
        globalVar.resultStringList.add("Wetter Frankfurt am Main");
        globalVar.resultStringList.add("Wetter Kassel");
        globalVar.resultStringList.add("Wetter Wiesbaden");
        globalVar.resultStringList.add("Wetter Offenbach am Main");
        globalVar.resultStringList.add("Wetter Darmstadt");
        globalVar.resultStringList.add("Wetter Rüsselsheim");
        globalVar.resultStringList.add("Wetter Wetzlar");
        globalVar.resultStringList.add("Wetter Gießen");
        globalVar.resultStringList.add("Wetter Fulda");
        globalVar.resultStringList.add("Wetter Hanau");
        globalVar.resultStringList.add("Wetter Viernheim");
        globalVar.resultStringList.add("Wetter Bad Homburg vor der Höhe");
        globalVar.resultStringList.add("Wetter Bad Vilbel");
        globalVar.resultStringList.add("Wetter Langen");
        globalVar.resultStringList.add("Wetter Bensheim");
        globalVar.resultStringList.add("Wetter Marburg");
        globalVar.resultStringList.add("Wetter Lampertheim");
        globalVar.resultStringList.add("Wetter Griesheim");

        globalVar.stringSearch = globalVar.searchStringList.get(0);
        getBrowser();

        //Step 2
        navigate(globalVar.__SUCHWEBSIDE_3TageAnsicht_Preview__);
        //setInputFeldValue(globalVar._INPUT_BOX, globalVar.searchStringList.get(0));
        setInputFeldValueClick(globalVar._INPUT_BOX, globalVar.stringSearch, globalVar.stringSearch, globalVar);

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
        TC2915_SEO_Linkliste test = new TC2915_SEO_Linkliste();
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
        int anzahlOrte = listOfOrte.size() / 2;

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
        int anzahlSpalte = listOfSpalte.size() / 2;
        final int maxSpalte = 3;

        if (anzahlSpalte <= maxSpalte) {
            check = true;
        }
        return check;
    }
}

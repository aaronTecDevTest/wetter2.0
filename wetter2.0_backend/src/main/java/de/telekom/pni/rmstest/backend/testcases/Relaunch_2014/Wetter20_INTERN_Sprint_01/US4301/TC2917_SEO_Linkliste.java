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
public class TC2917_SEO_Linkliste extends GenericTest_New {
    private GlobalVar globalVar;

    /**
     *
     */
    public TC2917_SEO_Linkliste() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {

        globalVar = getGlobalVar();

        //TODO ein andere beispiel nehemen.
        globalVar.searchStringList.add("64521 Groß-Gerau");

        globalVar.resultStringList.add("Wettervorhersage Rüsselsheim");
        globalVar.resultStringList.add("Wettervorhersage Groß-Gerau");
        globalVar.resultStringList.add("Wettervorhersage Walldorf");
        globalVar.resultStringList.add("Wettervorhersage Mörfelden");
        globalVar.resultStringList.add("Wettervorhersage Raunheim");
        globalVar.resultStringList.add("Wettervorhersage Gernsheim");
        globalVar.resultStringList.add("Wettervorhersage Bischofsheim");
        globalVar.resultStringList.add("Wettervorhersage Kelsterbach");
        globalVar.resultStringList.add("Wettervorhersage Ginsheim");
        globalVar.resultStringList.add("Wettervorhersage Nauheim");
        globalVar.resultStringList.add("Wettervorhersage Königstädten");
        globalVar.resultStringList.add("Wettervorhersage Trebur");
        globalVar.resultStringList.add("Wettervorhersage Gustavsburg");
        globalVar.resultStringList.add("Wettervorhersage Biebesheim am Rhein");
        globalVar.resultStringList.add("Wettervorhersage Büttelborn");
        globalVar.resultStringList.add("Wettervorhersage Goddelau");
        globalVar.resultStringList.add("Wettervorhersage Bauschheim");
        globalVar.resultStringList.add("Wettervorhersage Stockstadt am Rhein");
        globalVar.resultStringList.add("Wettervorhersage Frankfurt am Main");
        globalVar.resultStringList.add("Wettervorhersage Kassel");
        globalVar.resultStringList.add("Wettervorhersage Wiesbaden");
        globalVar.resultStringList.add("Wettervorhersage Offenbach am Main");
        globalVar.resultStringList.add("Wettervorhersage Darmstadt");
        globalVar.resultStringList.add("Wettervorhersage Rüsselsheim");
        globalVar.resultStringList.add("Wettervorhersage Wetzlar");
        globalVar.resultStringList.add("Wettervorhersage Gießen");
        globalVar.resultStringList.add("Wettervorhersage Fulda");
        globalVar.resultStringList.add("Wettervorhersage Hanau");
        globalVar.resultStringList.add("Wettervorhersage Viernheim");
        globalVar.resultStringList.add("Wettervorhersage Bad Homburg vor der Höhe");
        globalVar.resultStringList.add("Wettervorhersage Bad Vilbel");
        globalVar.resultStringList.add("Wettervorhersage Langen");
        globalVar.resultStringList.add("Wettervorhersage Bensheim");
        globalVar.resultStringList.add("Wettervorhersage Marburg");
        globalVar.resultStringList.add("Wettervorhersage Lampertheim");
        globalVar.resultStringList.add("Wettervorhersage Griesheim");

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
        TC2917_SEO_Linkliste test = new TC2917_SEO_Linkliste();
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

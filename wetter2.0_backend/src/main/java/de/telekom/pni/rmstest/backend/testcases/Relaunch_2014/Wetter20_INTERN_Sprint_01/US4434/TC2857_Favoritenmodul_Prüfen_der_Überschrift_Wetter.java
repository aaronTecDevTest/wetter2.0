/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4434;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

/**
 * @author a.kutekidila
 */
public class TC2857_Favoritenmodul_Prüfen_der_Überschrift_Wetter extends GenericTest_New {
    private GlobalVar globalVar;

    /**
     *
     */
    public TC2857_Favoritenmodul_Prüfen_der_Überschrift_Wetter() {

    }

    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("Frank");
        globalVar.resultStringList.add("null");

        //Step1
        getBrowser();
        //Step2
        navigate(globalVar.__StartSeiteFavoriten__);
        //Step3
        try {
            globalVar.stringSearch = globalVar.searchStringList.get(0);
            getWebElement(globalVar.__FAVORETEN_MODUL__Zahnrad__).click();
            getWebElement(globalVar.__FAVORETEN_MODUL_MeinStadt__).click();
            setInputFeldValue(globalVar.__FAVORETEN_MODUL_MeinStadt_Suchfeld___, globalVar.stringSearch);
            pauseTest(5000);
        } catch (Exception e) {
            logFailureCheckpoint("Try", globalVar.stringSearch, e.getStackTrace().toString());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2857_Favoritenmodul_Prüfen_der_Überschrift_Wetter test = new TC2857_Favoritenmodul_Prüfen_der_Überschrift_Wetter();
        test.before();
        test.runTest();
        test.after();
    }

    boolean checkClickeElement() {
        boolean check = true;

        return check;
    }

    boolean checkClickButton() {
        boolean check = true;

        return check;
    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.GenericTest_New#setRunningConfiguration(de.telekom.pni.rmstest.backend.config.RunningConfiguration_New)
     */
    @Override
    public void setRunningConfiguration(RunningConfiguration_New runningConfiguration) {
        super.setRunningConfiguration(runningConfiguration);
    }
}

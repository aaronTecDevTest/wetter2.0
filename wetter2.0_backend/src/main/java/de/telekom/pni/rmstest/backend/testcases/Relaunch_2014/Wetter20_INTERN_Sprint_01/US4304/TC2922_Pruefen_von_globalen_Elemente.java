package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4304;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


public class TC2922_Pruefen_von_globalen_Elemente extends GenericTest_New {
    private GlobalVar globalVar;

    public TC2922_Pruefen_von_globalen_Elemente() {
    }

    @Override
    public void runTest() {

        globalVar = getGlobalVar();

        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Darmstadt");
        globalVar.searchStringList.add("New York");
        globalVar.searchStringList.add("Kagul");
        globalVar.searchStringList.add("Kairo");


        //Step 1
        getBrowser();

        //Step 2
        navigate(globalVar.__StartSeiteFavoriten__);

        try {
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                globalVar.stringResult = globalVar.resultStringList.get(i);


                if (checkAllElement()) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                    navigate(globalVar.__StartSeiteFavoriten__);
                }
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " AD-Elements could be not found in Web";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }


    /**
     * @param args String Array
     */
    public static void main(String[] args) {
        TC2922_Pruefen_von_globalen_Elemente test = new TC2922_Pruefen_von_globalen_Elemente();
        test.before();
        test.runTest();
        test.after();
    }


    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }


    public boolean checkAllElement() {
        boolean check = true;

        for (int i = 0; i < 12; i++) {

            switch (i) {
                case 0:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='T-self.parsedId-1']/div[2]/p[1]/a").click();
                    break;

                case 1:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[2]/a").click();

                    break;

                case 2:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[3]/a").click();
                    break;

                case 3:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[4]/a").click();
                    break;

                case 4:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[5]/a").click();
                    break;

                case 5:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[6]/a").click();
                    break;

                case 6:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[7]/a").click();
                    break;

                case 7:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[8]/a").click();
                    break;

                case 8:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[9]/a").click();
                    break;

                case 9:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[10]/a").click();
                    break;

                case 10:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]").click();
                    break;

                case 11:
                    if (!checkTageAnsichtElement(i,"","")) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    break;

                default:
                    break;
            }
        }
        return check;
    }

    private boolean checkTageAnsichtElement(Integer i, String xPath, String elementName) {
        boolean check = true;

        //Element 1
        if (getWebElement(xPath).isEnabled()) {
            check = false;
            this.logFailureCheckpoint(
                    i.toString(),
                    "xPath: " + globalVar._BOX_WETTER__LOGO + elementName,
                    getWebElement(globalVar._BOX_WETTER__LOGO).getTagName() + " Element not found"
            );
        }
        return check;
    }
}

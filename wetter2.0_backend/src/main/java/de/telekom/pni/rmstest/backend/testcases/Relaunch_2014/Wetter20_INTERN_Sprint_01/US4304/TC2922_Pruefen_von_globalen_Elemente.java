package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4304;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import org.openqa.selenium.WebDriver;


public class TC2922_Pruefen_von_globalen_Elemente extends GenericTest_New {
    private GlobalVar globalVar;

    public TC2922_Pruefen_von_globalen_Elemente() {
    }

    @Override
    public void runTest() {

        globalVar = getGlobalVar();

        globalVar.searchStringList.add("<div id=\"vmsky\">");
        globalVar.searchStringList.add("<div id=\"vmpop\">");
        globalVar.searchStringList.add("<div id=\"vmsb\">");
        globalVar.searchStringList.add("<div id=\"vmcadsl\">");
        globalVar.searchStringList.add("<div id=\"vmcadsr\">");

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
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='T-self.parsedId-1']/div[2]/p[1]/a").click();
                    break;

                case 1:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[2]/a").click();

                    break;

                case 2:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[3]/a").click();
                    break;

                case 3:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[4]/a").click();
                    break;

                case 4:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[5]/a").click();
                    break;

                case 5:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[6]/a").click();
                    break;

                case 6:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[7]/a").click();
                    break;

                case 7:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[8]/a").click();
                    break;

                case 8:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[9]/a").click();
                    break;

                case 9:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Treitsci']/ul/li[10]/a").click();
                    break;

                case 10:
                    if (!checkTageAnsichtElement()) {
                        check = false;
                        this.logFailureCheckpoint(Integer.toString(i), "Element not Found", "Element not Found");
                    }
                    getWebElement(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]").click();
                    break;

                case 11:
                    if (!checkTageAnsichtElement()) {
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

    private boolean checkTageAnsichtElement() {
        boolean check = true;

        //Element 1
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 2
        if (getWebElement(globalVar._BOX_WETTER__LOGO_POWERED_BY).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO_POWERED_BY, getWebElement(globalVar._BOX_WETTER__LOGO_POWERED_BY).getTagName(), "Element not Found");
        }

        //Element 3
        if (getWebElement(globalVar._BOX_WETTER__MENÜLEISTE).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__MENÜLEISTE, getWebElement(globalVar._BOX_WETTER__MENÜLEISTE).getTagName(), "Element not Found");
        }

        //Element 4
        if (getWebElement(globalVar._BOX_WETTER__THERE_BIG).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__THERE_BIG, getWebElement(globalVar._BOX_WETTER__THERE_BIG).getTagName(), "Element not Found");
        }

        //Element 5
        if (getWebElement(globalVar._BOX_WETTER__EINSTELLUNG_BUTTON).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__EINSTELLUNG_BUTTON, getWebElement(globalVar._BOX_WETTER__EINSTELLUNG_BUTTON).getTagName(), "Element not Found");
        }

        //Element 6
        if (getWebElement(globalVar._BOX_WETTER__BREADCRUMB).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__BREADCRUMB, getWebElement(globalVar._BOX_WETTER__BREADCRUMB).getTagName(), "Element not Found");
        }

        //Element 7
        if (getWebElement(globalVar._BOX_WETTER__BREADCRUMB_aLink).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__BREADCRUMB_aLink, getWebElement(globalVar._BOX_WETTER__BREADCRUMB_aLink).getTagName(), "Element not Found");
        }

        //Element 8
        if (getWebElement(globalVar._BOX_WETTER_h2).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER_h2, getWebElement(globalVar._BOX_WETTER_h2).getTagName(), "Element not Found");
        }

        //Element 9
        if (getWebElement(globalVar._BOX_WETTER_h3).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER_h3, getWebElement(globalVar._BOX_WETTER_h3).getTagName(), "Element not Found");
        }

        //Element 10
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 11
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 12
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 13
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 14
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 15
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 16
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 17
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 18
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 19
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 20
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 21
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 22
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 23
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 24
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 25
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 26
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 27
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 28
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 29
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }

        //Element 30
        if (getWebElement(globalVar._BOX_WETTER__LOGO).isEnabled()) {
            check = false;
            this.logFailureCheckpoint("xPath: " + globalVar._BOX_WETTER__LOGO, getWebElement(globalVar._BOX_WETTER__LOGO).getTagName(), "Element not Found");
        }
        return check;
    }

}

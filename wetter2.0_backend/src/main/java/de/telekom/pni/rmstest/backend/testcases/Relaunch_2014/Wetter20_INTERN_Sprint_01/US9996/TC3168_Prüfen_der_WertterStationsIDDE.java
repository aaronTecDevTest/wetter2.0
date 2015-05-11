/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US9996;

import java.util.ArrayList;
import java.util.Arrays;


import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.openqa.selenium.By;


/**
 * @author a.kutekidila
 */
public class TC3168_Prüfen_der_WertterStationsIDDE extends GenericTest_New {

    private GlobalVar globalVar = null;

    /**
     *
     */
    public TC3168_Prüfen_der_WertterStationsIDDE() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {
        int savePoint = 99;
        int savePointStep = 100;

        globalVar = getGlobalVar();
        fileToArry();

        //Step 1
        getBrowser();

        for (int i = 0; i < globalVar.searchStringList.size(); i++) {
            globalVar.stringSearch = globalVar.searchStringList.get(i);
            //Step 2
            navigate(globalVar.__SUCHESTATION_ID__ + globalVar.stringSearch);
            //navigate("http://rest.wetter.info/webservice/v1_0/rest/place&t=td39ef23d36bbcf35c7f6607a3ab239be&id="+globalVar.stringSearch);
            //Step 3 ....
            /**
             * Result
             */
            if (globalVar.searchStringList != null) {
                if (check_Breadcrumb()) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                    //globalVar.resultStringList.add(globalVar.stringSearch);
                } else {
                    globalVar.stringResult = globalVar.stringSearch + ":" + " Not found in Web!!";
                    logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                    globalVar.resultStringList.add(globalVar.stringResult);
                }
            } else {
                globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + "File Emty";
                logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
            }

            if (i == savePoint) {
                arrayToFile();
                savePoint = savePoint + savePointStep;
            }
        }

        // Step n-1 (last step) and close browser
        this.closeBrowser(); // closes the browser and catches any error
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        TC3168_Prüfen_der_WertterStationsIDDE test = new TC3168_Prüfen_der_WertterStationsIDDE();
        test.before();
        test.runTest();
        test.after();
    }

    /*
     * Methoden
     *
     */
    private void fileToArry() {
        String content;
        String[] temp = null;

        String dateiOrdner = "U:/01_Dev/Wetter_2.0/Testcode/workspace/wetter2.0/wetter2.0_backend/src/main/resources/";
        String dateiName = dateiOrdner + "stationsIDDE.properties";

        try {
            content = new String(Files.readAllBytes(Paths.get(dateiName)));
            temp = content.split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }

        globalVar.searchStringList = new ArrayList<String>(Arrays.asList(temp));
    }

    private void arrayToFile() {
        String dateiOrdner = "U:/01_Dev/Wetter_2.0/Testcode/workspace/wetter2.0/wetter2.0_backend/src/main/resources/";
        String dateiName = "stationsIDDE.result";
        Path path = Paths.get(dateiOrdner, dateiName);
        Charset charset = Charset.forName("UTF-8");
        OpenOption op = StandardOpenOption.WRITE;

        try {
            Files.write(path, globalVar.resultStringList, charset, op);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean check_Breadcrumb() {
        boolean check = false;
        String tempString = "";
        try {
            tempString = getWebElement(globalVar._BOX_WETTER__BREADCRUMB).getText();
            if (tempString.contains("Wetter")) {
                check = true;
            }
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    public boolean check_H2_Tag() {
        boolean check = false;
        String tempString = "";
        try {
            tempString = getWebElement(globalVar._BOX_WETTER_h2).getText();
            if (tempString.contains("Wetter")) {
                check = true;
            }
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    public boolean check_Titel() {
        boolean check = false;
        String tempString = "";
        try {
            tempString = getBrowser().findElement(By.tagName("title")).getText();
            if (tempString.contains("Wetter")) {
                check = true;
            }
        } catch (Exception e) {
            check = false;
        }
        return check;
    }
}

/**
 *
 */
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US9996;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author a.kutekidila
 */
public class TC0000_DynamischerURLPer301 extends GenericTest_New {

    private GlobalVar globalVar = null;

    /**
     *
     */
    public TC0000_DynamischerURLPer301() {
    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {
        int savePoint = 99;
        int savePointStep = 100;

        globalVar = getGlobalVar();
        globalVar.searchStringList = fileToArry();

        setRunningConfiguration(new RunningConfiguration_New("CH", "only on chrome"));
        //Step 1
        getBrowser();

        for (int i = 0; i < globalVar.searchStringList.size(); i++) {
            globalVar.stringSearch = globalVar.searchStringList.get(i);
            //Step 2
            navigate(globalVar.stringSearch);
            //Step 3 ....
            /**
             * Result
             */
            if (globalVar.searchStringList != null) {
                if (checkURL()) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
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
        arrayToFile();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        TC0000_DynamischerURLPer301 test = new TC0000_DynamischerURLPer301();
        test.before();
        test.runTest();
        test.after();
    }

    /*
     * Methoden
     *
     */
    private ArrayList<String> fileToArry() {
        String content;
        String[] temp = null;

        String dateiOrdner = "../wetter2.0/wetter2.0_backend/src/main/resources/";
        String dateiName = dateiOrdner + "dynamischerURLAll.properties";

        try {
            content = new String(Files.readAllBytes(Paths.get(dateiName)));
            temp = content.split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<String>(Arrays.asList(temp));
    }

    private void arrayToFile() {
        String dateiOrdner = "../wetter2.0/wetter2.0_backend/src/main/resources/";
        String dateiName = "dynamischerURLAll.result";
        Path path = Paths.get(dateiOrdner, dateiName);
        Charset charset = Charset.forName("UTF-8");
        OpenOption op = StandardOpenOption.WRITE;

        try {
            Files.write(path, globalVar.resultStringList, charset, op);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkURL() {
        boolean check = false;
        String tempString = "";

        try {
            pauseTest(2000);
            tempString = getBrowser().getCurrentUrl();
            boolean wetterInfo = tempString.contains("http://www.wetter.info/deutschland-wetter/");
            boolean toiWetter =  tempString.contains("http://www.t-online.de/wetter/deutschland-wetter/");
            if (wetterInfo || toiWetter) {
                check = true;
            }
        } catch (Exception e) {
            check = false;
        }
        return check;
    }
}

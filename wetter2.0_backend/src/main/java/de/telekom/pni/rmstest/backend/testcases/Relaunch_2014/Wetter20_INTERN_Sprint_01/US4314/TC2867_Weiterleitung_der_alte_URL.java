package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4314;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;
import org.openqa.selenium.WebDriver;


/**
 * @author a.kutekidila
 */
public class TC2867_Weiterleitung_der_alte_URL extends GenericTest_New {
    private GlobalVar globalVar;


    @Override
    public void runTest() {
        globalVar = getGlobalVar();

        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-aach/17746910");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-aalen/17746962");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-abstatt/17746768");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-abtsgmuend/17746774");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-achberg/17746938");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-achern/17746912");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-achstetten/17746940");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-wieden/17748434");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-wiernsheim/17748388");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-wies/17748436");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-wiesenbach/17748390");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-wiesensteig/17748348");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-wieslet/17748438");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-wiesloch/17748392");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-wildberg/17748394");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/baden-wuerttemberg/wetter-wilhelmsdorf/17748464");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/bayern/wetter-spatzenhausen/17751380");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/bayern/wetter-speichersdorf/17751254");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/bayern/wetter-spiegelau/17751392");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/bayern/wetter-stadelhofen/17751256");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/bayern/wetter-stadlern/17751228");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/bayern/wetter-stadtbergen/17751348");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/bayern/wetter-stadtlauringen/17751322");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/niedersachsen/wetter-esens/17754900");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/niedersachsen/wetter-essen-oldenburg/17754890");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/niedersachsen/wetter-estorf/17754850");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/niedersachsen/wetter-estorf/17754874");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/niedersachsen/wetter-everode/17754852");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/niedersachsen/wetter-eversmeer/17754902");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/niedersachsen/wetter-eyendorf/17754864");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/niedersachsen/wetter-eystrup/17754854");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/nordrhein-westfalen/wetter-wenden/17756634");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/nordrhein-westfalen/wetter-werdohl/17756636");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/nordrhein-westfalen/wetter-werl/17756654");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/nordrhein-westfalen/wetter-wermelskirchen/17756610");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/nordrhein-westfalen/wetter-werne/17756656");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/nordrhein-westfalen/wetter-werther/17756638");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/nordrhein-westfalen/wetter-wesel/17756662");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/nordrhein-westfalen/wetter-wesseling/17756612");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/rheinland-pfalz/wetter-ersfeld/17757082");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/rheinland-pfalz/wetter-erzenhausen/17757128");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/rheinland-pfalz/wetter-esch/17757108");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/rheinland-pfalz/wetter-eschfeld/17757104");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/rheinland-pfalz/wetter-essingen/17748732");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/rheinland-pfalz/wetter-essweiler/17757130");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/rheinland-pfalz/wetter-esthal/17757140");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/saarland/wetter-gersheim/17758062");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/saarland/wetter-grossrosseln/17758064");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/saarland/wetter-heusweiler/17758066");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/saarland/wetter-homburg/17758068");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/saarland/wetter-illingen/17758070");
        globalVar.searchStringList.add("http://wetter.info/wetter-deutschland/saarland/wetter-kirkel/17758072");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_1/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_2/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_3/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_4/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_5/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_6/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_7/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_8/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_9/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/wetter-deutschland/hessen/wetter-darmstadt/tab_10/17753306");
        globalVar.searchStringList.add("http://www.wetter.info/europawetter/spanien/wetter-barcelona/17844170");
        globalVar.searchStringList.add("http://www.wetter.info/europawetter/frankreich/wetter-paris/17837866");
        globalVar.searchStringList.add("http://www.wetter.info/europawetter/holland/wetter-amsterdam/17838116");
        globalVar.searchStringList.add("http://www.wetter.info/europawetter/griechenland/wetter-athen/17837880");
        globalVar.searchStringList.add("http://www.wetter.info/weltwetter/asien/thailand/wetter-bangkok/17846198");
        globalVar.searchStringList.add("http://www.wetter.info/weltwetter/amerika/nordamerika/usa/wetter-new-york-ny-/17845362");
        globalVar.searchStringList.add("http://www.wetter.info/europawetter/tschechien/wetter-prag/17844206");
        globalVar.searchStringList.add("http://www.wetter.info/europawetter/schweiz/kanton-zuerich/wetter-zuerich/17844116");
        globalVar.searchStringList.add("http://www.wetter.info/europawetter/oesterreich/wien/wetter-wien/17840832");
        globalVar.searchStringList.add("http://www.wetter.info/europawetter/schweden/wetter-stockholm/17840938");
        globalVar.searchStringList.add("http://www.wetter.info/weltwetter/amerika/nordamerika/usa/wetter-san-francisco-ca-/17845446 ");

        getBrowser();
        navigate(globalVar.__StartSeiteFavoriten__);

        try {
            for (int i = 0; i < globalVar.searchStringList.size(); i++) {
                globalVar.stringSearch = globalVar.searchStringList.get(i);
                navigate(globalVar.stringSearch);

                if (checkWeb()) {
                    globalVar.stringSearch = "String" + globalVar.stringResult + " was found in Web";
                    //this.logSuccessCheckpoint(globalVar.stringResult, listOfAutoSuggest.get(j).getText());
                } else {
                    globalVar.stringSearch = " " + globalVar.stringResult + " was not found in Web";
                    this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "No String match!");
                }
                getBrowser().navigate().back();
            }
        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " empty list!! " + globalVar.stringResult + " AD-Elements could be not found in Web";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }
    }

    private boolean checkWeb() {
        WebDriver driver = getBrowser();
        String title = driver.getTitle();
        return title.contains("Wetter") ? true : false;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC2867_Weiterleitung_der_alte_URL test = new TC2867_Weiterleitung_der_alte_URL();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}

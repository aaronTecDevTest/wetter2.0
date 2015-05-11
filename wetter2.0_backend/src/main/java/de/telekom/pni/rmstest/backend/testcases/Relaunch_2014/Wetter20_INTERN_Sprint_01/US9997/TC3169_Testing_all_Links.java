package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Wetter20_INTERN_Sprint_01.US9997;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.global.GlobalVar;


public class TC3169_Testing_all_Links extends GenericTest_New {

    private GlobalVar globalVar = null;
//	private List <String> aLinkResult = null;


    //private String fileName = "C:/Users/a.kutekidila/Desktop/linkResult.csv";
    //private String prefixAnahmenTest = "http://portal.wetter.info/winfo/deutschland";
    //private String prefixWikumgebung = "http://www.wetter.info/";

    public List<Links> aLinks = null;

    public TC3169_Testing_all_Links() {

    }

    @Override
    public void runTest() {
        globalVar = getGlobalVar();
        globalVar.searchStringList.add("Barcelona");
        globalVar.searchStringList.add("Frankfurt am main");
        globalVar.searchStringList.add("Paris");
        globalVar.searchStringList.add("Köln");

		/*
		globalVar.resultStringList.add("Barcelona (Spanien, Katalonien)");				globalVar.resultStringList.add("Frankfurt am Main (Deutschland, Hessen)");
		globalVar.resultStringList.add("Paris (Frankreich, Île-de-France)");			globalVar.resultStringList.add("Köln (Deutschland, Nordrhein-Westfalen)"); 						
		*/

        //this.setRunningConfiguration(new RunningConfiguration_New("IE", globalVar.__WEBSIDE__));
        //Step 1
        this.getBrowser();

        //Step 2
        this.navigate("http://portal.wetter.info/winfo/wetter-deutschland/berlin/kreisfreie-stadt-berlin/wetter-berlin/K11000000");

        try {
            //Step 3 to ??

            //globalVar.stringSearch =  globalVar.searchStringList.get(1);
            //globalVar.stringResult =  globalVar.resultStringList.get1i);
				
			/*
			 * Input
			 */
            //setInputFeldValue(globalVar._INPUT_BOX, globalVar.stringSearch);
            //this.pauseTest(1000);

            List<WebElement> listOfLinks = this.getListALink(globalVar.__All_Links__);
            tesingLink(listOfLinks);
            printToFile(aLinks);

        } catch (Exception e) {
            globalVar.stringSearch = globalVar.stringSearch + " Liste ist leer!! " + globalVar.stringResult + " AutoSuggest ist leer.";
            this.logFailureCheckpoint(globalVar.stringSearch, globalVar.stringResult, "null");
        }

    }

    public void tesingLink(List<WebElement> listOfLinks) {
        aLinks = new ArrayList<Links>();
        String linkText = "";
        String linkHREF = "";
        for (WebElement webElement : listOfLinks) {
            linkText = webElement.getText();
            linkHREF = webElement.getAttribute("href");
            Links temp = new Links(linkHREF, linkText);
            aLinks.add(temp);
        }
    }


    public void printToFile(List<Links> links) {
        for (Links aLink : links) {
            aLink.clickLink(getBrowser());
            System.out.println(aLink.toString());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TC3169_Testing_all_Links test = new TC3169_Testing_all_Links();
        test.before();
        test.runTest();
        test.after();
    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#setRunningConfiguration(de.telekom.pni.rmstest.backend.config.RunningConfiguration_New)
     */
    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}


class Links {

    private String linkUrl = "null";
    private String linkText = "null";
    private boolean isRealURL = false;

    private String linkTitelBoby = "null";
    private String linkError = "null";


    public Links(String url, String text) {
        this.linkUrl = url;
        this.linkText = text;
        checkUrl(url);
    }

    public void setLinks(String url, String text) {
        this.linkUrl = url;
        this.linkText = text;
        checkUrl(url);
    }


    public void clickLink(WebDriver wd) {
        String tempBodyText = null;
        if (this.isRealURL == true) {
            wd.get(this.linkUrl);
            try {
                tempBodyText = wd.findElement(By.tagName("body")).getText();
                linkTitelBoby = wd.findElement(By.tagName("title")).getText();

                if (tempBodyText.contains("404") || tempBodyText.contains("Error") ||
                        linkTitelBoby.contains("404") || linkTitelBoby.contains("Error")) {
                    linkError = linkTitelBoby + "  " + tempBodyText;
                }
            } catch (Exception e) {
                System.err.println("");
            }
        }
    }

    private void checkUrl(String url) {
        //String patter1 = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]";
        //String patter2 = "{2,256}\\.[a-z]{2,6}\\b";
        //String patter3 = "([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
        String patter = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

        if (url != null) {
            if (url.matches(patter))
                isRealURL = true;
        }
    }

    /**
     * @return the linkUrl
     */
    public String getLinkUrl() {
        return linkUrl;
    }


    /**
     * @return the linkText
     */
    public String getLinkText() {
        return linkText;
    }


    /**
     * @return the isRealURL
     */
    public boolean isLinkOK() {
        return isRealURL;
    }


    /**
     * @param linkUrl the linkUrl to set
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }


    /**
     * @param linkText the linkText to set
     */
    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }


    /**
     * @param isLinkOK the isRealURL to set
     */
    public void setLinkOK(boolean isLinkOK) {
        this.isRealURL = isLinkOK;
    }

    private String isRealUrlToString(boolean isRealUrl) {
        if (isRealUrl)
            return "Is a real Url";

        return "Not a real URl";
    }

    @Override
    public String toString() {
        return linkText + ";" +
                isRealUrlToString(isRealURL) + ";" +
                linkUrl + ";" +
                linkTitelBoby + ";" +
                linkError;
    }

}

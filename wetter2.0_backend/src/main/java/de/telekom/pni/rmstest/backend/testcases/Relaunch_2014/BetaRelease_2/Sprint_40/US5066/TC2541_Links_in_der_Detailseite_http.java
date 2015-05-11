package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.BetaRelease_2.Sprint_40.US5066;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;

/**
 * Ein konkreter Testfall, der auf Basis der Klasse GenericTest.java erstellt wurde.
 * Die Klasse führt einen konkreten Testfall durch, der einen bestimmten Bereich des Testobjekts überprüft.
 *
 * @author M.Forster
 */
public class TC2541_Links_in_der_Detailseite_http extends GenericTest_New {

    private final String testCaseShortName = "RMS";

    public final Properties PAGE = PropertiesManager_New.getInstance().getProperties("page");
    public final Properties SEARCH = PropertiesManager_New.getInstance().getProperties("search");

    private final String pathToLogin = PAGE.getProperty("button.login");

    private final String pathToSearchResult = PAGE.getProperty("newsline.firstElement");


    //Suche
    private final String pathToSuche = PAGE.getProperty("input.simple.searchinput");
    private final String pathToLos = PAGE.getProperty("button.simple.search");

    private final String searchString = "http";
    private final String pathToContent = ".//*[@id='article-content_contentwrapper']//*[contains(@href,'" + searchString + "')]";
    private final String expectedAttribute = "href";
    private final String expectedColor = "rgba(0,0,255,1)";
    private final String expectedDec = "underline";

    private final String pathToCounter = PAGE.getProperty("filter.counter");
    private final Matcher date_matcher = Pattern.compile("(\\d*)\\s\\w*").matcher("");

    private int treffer(String str) {
        date_matcher.reset(str);
        if (date_matcher.find()) {

            int treffer = Integer.parseInt(date_matcher.group(1));
            return treffer;
        }

        return 0;
    }


    public void checkSearchResult(String pathToSearchResult, String searchString) {

        try {
            if (pathToSearchResult != null & searchString != null) {

                clickOnElement(pathToSearchResult, "Klick auf News");
                pauseTest(2000);

                WebElement content = getByXPath(pathToContent);
                // Scrollen bis der Inhalt sichbar ist
                ((JavascriptExecutor) getBrowser()).executeScript("arguments[0].scrollIntoView();", content);


                if (!content.getAttribute(expectedAttribute).isEmpty()) {
                    logSuccessCheckpoint("Prüfe ob der Link auf der Seite klickbar ist", "Der Link: " + searchString + " ist klickbar");
                } else {
                    logFailureCheckpoint("Prüfe ob der Link auf der Seite klickbar ist", "Der Link: " + searchString + " ist klickbar", "Der Link ist nicht klickbar");
                }

                checkCSS(content, "color", expectedColor, "Prüfe ob die Links blaue Schrift haben");
                checkCSS(content, "text-decoration", expectedDec, "Prüfe ob die Links unterschrichen sind");
            }

        } catch (Exception e) {
            logFailureCheckpoint("Check if news are available", "An unexpected error has occurred: ", "Error occured: " + e.getMessage());
        }

    }

    @Override
    public void runTest() {
        getBrowser();
        String navi = getNavigation(testCaseShortName);
        navigate(navi);

		/*
		 * Test Description
		 * 		Die Detailseite einer beliebigen Nachricht aufrufen.
		 * 		In der Detailseite auf die Links mit http klicken
		 * 		
		 * Die Links mit http sind innerhalb der Meldung auf der Detailseite klickbar. 
		 * Bei Klick darauf öffnet sich die Url in neuem Browserfenster. 
		 * Hinweis: Die Links/Mailadressen sind gekennzeichnet durch Unterstrich und blaue Schrift.
		 */

        //Warte bis der Seite voll geladen ist
        WebDriverWait wait = new WebDriverWait(getBrowser(), 10);
        @SuppressWarnings("unused")
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathToLogin)));

        pauseTest(2000);

        //Treffer vor der Suche Lesen
        WebElement counter = getByXPath(pathToCounter);
        checkExists(counter, "Pürfe ob Counter existiert");
        int first = treffer(counter.getText());

        // Suchbegriff eintragen
        WebElement suche = getByXPath(pathToSuche);
        suche.clear();
        suche.sendKeys(searchString);

        //Enter Taste klicken
        Actions keyActions = new Actions(getBrowser());
        keyActions.sendKeys(Keys.ENTER).perform();
        pauseTest(1000);

        // Suche Starten
        WebElement buttonLos = getByXPath(pathToLos);
        buttonLos.click();
        pauseTest(3000);


        //Treffer nach der Suche lesen
        counter = getByXPath(pathToCounter);
        int second = treffer(counter.getText());

        //Such Ergebnis vergleichen
        if (first != second)
            logSuccessCheckpoint("Treffer prüfen", "Treffer hat sich aktualisiert");
        else
            logFailureCheckpoint("Treffer prüfen", "Treffer hat sich aktualisiert", "Treffer hat sich NICHT aktualisier");

        if (second == 0)
            logFailureCheckpoint("Treffer prüfen", "Treffer hat sich aktualisiert", "Treffer ist null");

        pauseTest(1000);

        // Anzahl Elemente prüfen
        checkSearchResult(pathToSearchResult, searchString);

        // close browser
        closeBrowser(); // closes the browser and catches any errors

    }


    public static void main(String[] args) {
        TC2541_Links_in_der_Detailseite_http test = new TC2541_Links_in_der_Detailseite_http();
        test.before();
        test.runTest();
        test.after();
    }


    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }
}
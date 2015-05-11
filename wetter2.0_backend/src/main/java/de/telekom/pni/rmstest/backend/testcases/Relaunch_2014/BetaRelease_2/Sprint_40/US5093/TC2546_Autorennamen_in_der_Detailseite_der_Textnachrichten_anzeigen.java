package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.BetaRelease_2.Sprint_40.US5093;

import java.util.Properties;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;


/**
 * Ein konkreter Testfall, der auf Basis der Klasse GenericTest.java erstellt wurde.
 * Die Klasse führt einen konkreten Testfall durch, der einen bestimmten Bereich des Testobjekts überprüft.
 *
 * @author Ö.Ünal
 */
public class TC2546_Autorennamen_in_der_Detailseite_der_Textnachrichten_anzeigen extends GenericTest_New {

    private final String testCaseShortName = "RMS";
    private final String prefixAutoren = URL.getProperty("RMS.Prefix.Autoren");

    public final Properties PAGE = PropertiesManager_New.getInstance().getProperties("page");
    private final String pathToLogin = PAGE.getProperty("button.login");


    private final String pathToAutor = ".//*[starts-with(text(),'Autor:')]";
    private final String pattern_Autor = "Autor:\\s\\w.*";

    private final String pathToNewsline = ".//*[@id='news']//tr[";
    private final String pathToNewsline2 = "]/td[4]/div";//PAGE.getProperty("newsline.firstElement");
    private final String pathToDetailseite = PAGE.getProperty("newsline.details.window");


    @Override
    public void runTest() {

        getBrowser();
        String navi = getNavigation(testCaseShortName);
        navigate(navi + prefixAutoren);

		
		/*
		 * 	Die Detailseite einer beliebigen Textnachricht mit Autorennamen aufrufen.
		 *	Der Autor der Nachricht wird auf der Detailseite unter der Überschrift angezeigt. (Autor: Max Mustermann)
		 * 
		 */

        //Warte bis der Seite voll geladen ist
        WebDriverWait wait = new WebDriverWait(getBrowser(), 10);
        @SuppressWarnings("unused")
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathToLogin)));

        for (int i = 1; i < 4; i++) {

            // Detailseite durch Single Klick auf Ueberschrift aufrufen
            WebElement listItem = getByXPath(pathToNewsline + i + pathToNewsline2);
            checkExists(listItem, "Prüfe ob Nachricht existiert");
            clickOnElement(pathToNewsline + i + pathToNewsline2, "Klick auf News");

            //Prüfe ob die Detailseite geöffnet ist
            WebElement detailseite = getByXPath(pathToDetailseite);
            checkExists(detailseite, "Check if detailpage is displayed");

            pauseTest(2000);

            //Prüfe ob Autoren in der Detailseite angezeigt wird
            WebElement newsWithAutor = getByXPath(pathToAutor);
            if (newsWithAutor.isDisplayed()) {
                checkExists(newsWithAutor, "Prüfe ob Autor angezeigt wird");
                checkRegExp(pattern_Autor, newsWithAutor.getText(), true, "Check if author is displayed");
            } else {
                logFailureCheckpoint("Prüfe ob Autor angezeigt wird", "Autorennamen sind vorhanden", "Autorennamen werden nicht angezeigt");
            }

            pauseTest(1000);

        }
        // close browser
        closeBrowser(); // closes the browser and catches any errors

    }

    public static void main(String[] args) {
        TC2546_Autorennamen_in_der_Detailseite_der_Textnachrichten_anzeigen test = new TC2546_Autorennamen_in_der_Detailseite_der_Textnachrichten_anzeigen();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);

    }
}
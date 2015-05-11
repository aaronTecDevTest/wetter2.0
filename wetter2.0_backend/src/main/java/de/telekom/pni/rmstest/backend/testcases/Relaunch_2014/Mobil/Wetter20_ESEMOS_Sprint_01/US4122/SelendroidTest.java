//Packagename (Namensraum)
package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Mobil.Wetter20_ESEMOS_Sprint_01.US4122;


//JUnit Anbindung

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

//Sendriod Anbindung
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

//Seleninum Anbindung
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


//Test bzw. Testclase
public class SelendroidTest {
    // Server Deklaration
    private static SelendroidLauncher selendroidServer = null;
    // Client Deklaration
    private static WebDriver driver = null;


    @BeforeClass
    public static void startSelendroidServer() throws Exception {
        //Prüft, ob eine Server in Windows gestartet ist. Falls ja, dann wird der Server gestoppt bzw. Programm beendet.
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }

        //
        SelendroidConfiguration config = new SelendroidConfiguration();
        config.addSupportedApp("U:/02_Libraries/Selendroid-0.10.0/selendroid-test-app-0.10.0.apk");


        //Starten des Severs mit App
        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.lauchSelendroid();

        //Starten der App und abholen der View von Server und speicher es als driver (Client Objekt)
        SelendroidCapabilities caps = new SelendroidCapabilities("io.selendroid.testapp:0.10.0");
        driver = new SelendroidDriver(caps);
    }


    @Test
    public void testShouldBeAbleToEnterText() {
        //InputFeld ini.
        WebElement inputField = driver.findElement(By.id("my_text_field"));

        //In Feld was schreiben...
        inputField.sendKeys("Testautomatisierung 9.2014");

        //Vergelichen der in Feld Input eingetragende Wert
        Assert.assertEquals("Selendroid", inputField.getText());
    }


    @AfterClass
    public static void stopSelendroidServer() {
        // Client und Server schließen
        if (driver != null) {
            driver.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
    }
}

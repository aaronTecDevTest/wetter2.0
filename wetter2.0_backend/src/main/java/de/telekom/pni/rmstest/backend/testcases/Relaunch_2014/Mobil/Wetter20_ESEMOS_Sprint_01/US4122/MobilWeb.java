package de.telekom.pni.rmstest.backend.testcases.Relaunch_2014.Mobil.Wetter20_ESEMOS_Sprint_01.US4122;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MobilWeb {
    private SelendroidLauncher selendroidServer = null;
    private WebDriver driver = null;

    @Test
    public void shouldSearchWithEbay() {
        // And now use this to visit ebay
        driver.get("https://m.ebay.de");

        // Find the text input element by its id
        //WebElement element = driver.findElement(By.id("kw"));
        try {
            Thread.sleep(3000L);
        } catch (Exception e) {
            System.out.println(e);
        }
        WebElement element = driver.findElement(By.id("kw"));

        // Enter something to search for
        element.sendKeys("Nexus 5");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }

    @Before
    public void startSelendroidServer() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();

        selendroidServer = new SelendroidLauncher(config);
        config.addSupportedApp("U:/02_Libraries/Selendroid-0.10.0/selendroid-test-app-0.10.0.apk");
        selendroidServer.lauchSelendroid();


        System.out.println("Server gestartet!!!");
        Thread.sleep(1000);


        DesiredCapabilities caps = SelendroidCapabilities.android();
        //DesiredCapabilities caps = SelendroidCapabilities.emulator(SelendroidCapabilities.EMULATOR);
        //caps.emulator(true);

        driver = new SelendroidDriver(caps);

    }

    @After
    public void stopSelendroidServer() {
        if (driver != null) {
            driver.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
    }
}

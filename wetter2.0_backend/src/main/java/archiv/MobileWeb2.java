package archiv;


import java.net.URL;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.api.robot.Key;


public class MobileWeb2 {
    private static SelendroidLauncher selendroidServer = null;
    private static WebDriver driver = null;


    @BeforeClass
    public static void startSelendroidServer() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();
        config.setPort(5556);
        config.setProxy("192.168.1.112:3128");
        config.setDeviceScreenshot(true);

        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.lauchSelendroid();

        DesiredCapabilities caps = SelendroidCapabilities.android();
        URL url = new URL("http://localhost:5556/wd/hub");
        driver = new SelendroidDriver(url, caps);

        driver.get("http://google.com");
        System.out.println("Web-Browser gestartet!!!");
        Thread.sleep(10000);
    }


    @Test
    public void testShouldBeAbleToEnterText() throws InterruptedException {
        WebElement inputField = driver.findElement(By.id("gs_tti0"));
        inputField.sendKeys("Selendorid");
        inputField.sendKeys(Key.ENTER);
        Thread.sleep(10000);
    }


    @AfterClass
    public static void stopSelendroidServer() {
        if (driver != null) {
            driver.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
    }

}

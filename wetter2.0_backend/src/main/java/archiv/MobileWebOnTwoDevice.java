package archiv;


import java.util.concurrent.CyclicBarrier;

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


public class MobileWebOnTwoDevice {
    private static SelendroidLauncher selendroidServer = null;
    private WebDriver driver1 = null;
    private WebDriver driver2 = null;
    private WebDriver driver3 = null;
    final CyclicBarrier gate = new CyclicBarrier(4);

    //private static String LG_G_FLEX 		= "06510ebe170ef362";
    //private static String SASUMG_Note3 	= "20715382";
    //private static String LG_G_2 			= "018f5cf89c8c39ca";


    @Before
    public void startSelendroidServer() {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }

        SelendroidConfiguration config = new SelendroidConfiguration();

        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.lauchSelendroid();
        System.out.println("Server gestartet!!!");

        DesiredCapabilities caps1 = SelendroidCapabilities.android();
        try {
            driver1 = new SelendroidDriver(caps1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("WebBrowser gestartet!!!");

        DesiredCapabilities caps2 = SelendroidCapabilities.android();
        try {
            driver2 = new SelendroidDriver(caps2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("WebBrowser gestartet!!!");

        DesiredCapabilities caps3 = SelendroidCapabilities.android();
        try {
            driver3 = new SelendroidDriver(caps3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void shouldSearchWithEbay() {
        // And now use this to visit ebay
        driver1.get("https://m.ebay.de");

        // Find the text input element by its id
        //WebElement element = driver.findElement(By.id("kw"));

        WebElement element1 = driver1.findElement(By.id("kw"));

        // Enter something to search for
        element1.sendKeys("Nexus 5");

        // Now submit the form. WebDriver will find the form for us from the element
        element1.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver1.getTitle());
        driver1.quit();


        // And now use this to visit ebay
        driver2.get("https://m.ebay.de");
        // Find the text input element by its id
        //WebElement element = driver.findElement(By.id("kw"));
        WebElement element2 = driver2.findElement(By.id("kw"));

        // Enter something to search for
        element2.sendKeys("Nexus 5");

        // Now submit the form. WebDriver will find the form for us from the element
        element2.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver2.getTitle());
        driver2.quit();


        // And now use this to visit ebay
        driver3.get("https://m.ebay.de");
        // Find the text input element by its id
        //WebElement element = driver.findElement(By.id("kw"));
        WebElement element3 = driver3.findElement(By.id("kw"));

        // Enter something to search for
        element3.sendKeys("Nexus 5");

        // Now submit the form. WebDriver will find the form for us from the element
        element3.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver3.getTitle());
        driver3.quit();
    }

    @After
    public void stopSelendroidServer() {
        if (driver1 != null) {
            driver1.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
    }
}

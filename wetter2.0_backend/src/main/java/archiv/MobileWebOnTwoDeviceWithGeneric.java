package archiv;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.core.GenericTest_New;

public class MobileWebOnTwoDeviceWithGeneric extends GenericTest_New {
    private static SelendroidLauncher selendroidServer = null;
    private static WebDriver driver1 = null;
    private static WebDriver driver2 = null;
    private static WebDriver driver3 = null;


    @Override
    public void runTest() {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        try {
            SelendroidConfiguration config = new SelendroidConfiguration();

            selendroidServer = new SelendroidLauncher(config);
            selendroidServer.lauchSelendroid();
            System.out.println("Server gestartet!!!");

            DesiredCapabilities caps = SelendroidCapabilities.android();
            driver1 = new SelendroidDriver(caps);
            System.out.println("WebBrowser gestartet!!!");

            DesiredCapabilities caps2 = SelendroidCapabilities.android();
            driver2 = new SelendroidDriver(caps2);
            System.out.println("WebBrowser gestartet!!!");

            DesiredCapabilities caps3 = SelendroidCapabilities.android();
            driver3 = new SelendroidDriver(caps3);
            System.out.println("WebBrowser gestartet!!!");

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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        MobileWebOnTwoDeviceWithGeneric test = new MobileWebOnTwoDeviceWithGeneric();
        test.before();
        test.runTest();
        test.after();
    }

}

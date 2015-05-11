package archiv;

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

public class MobileWeb1 {
    private SelendroidLauncher selendroidServer = null;
    private WebDriver driver = null;


    @Before
    public void startSelendroidServer() throws Exception {

        //Prüft, ob eine Server gestartet wurde, wenn ja dann wird es erst geschlossen
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();

        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.lauchSelendroid();

        System.out.println("Server gestartet!!!");

        DesiredCapabilities caps = SelendroidCapabilities.android();

        driver = new SelendroidDriver(caps);
        System.out.println("WebBrowser gestartet!!!");
    }


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

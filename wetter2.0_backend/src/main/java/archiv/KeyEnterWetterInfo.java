package archiv;


import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Keys;

public class KeyEnterWetterInfo {
    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.

        File file = new File("C:/Users/a.kutekidila/Driver/IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());


//        WebDriver driver = new FirefoxDriver();
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability("ignoreZoomSetting", true);
        //ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        WebDriver driver = new InternetExplorerDriver(ieCapabilities);

        // And now use this to visit Google
        driver.get("http://cm7prev.ada.t-online.de/toiPortal/servlet/suche/sp_Rom/62174516");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");
        //Pause
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Hier wird eine Seite aufgerunfen und gepr�ft ob die seiete da ist.
        // Find the text input element by its name
        //WebElement element = driver.findElement(By.name("q"));
        try {
            Thread.sleep(3000);
            // WebElement element = driver.findElement(By. xpath(".//*/input[contains(@id,'searchPattern')]"));
            WebElement element = driver.findElement(By.xpath(".//*[@id='searchPattern']"));

            // Enter something to search for
            if (element != null) {

                element.sendKeys("Romdfsdfgasdfg");

                element.sendKeys(Keys.ENTER);
                //element.sendKeys("\n");

//	        	 System.out.println(element.getTagName());


                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Seite nicht erfolgreich geladen!");
            }
        } catch (NoSuchElementException ex) {

            System.out.println(ex);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Close the browser
        driver.quit();
    }
}
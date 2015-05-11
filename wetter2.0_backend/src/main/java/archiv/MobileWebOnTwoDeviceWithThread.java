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


public class MobileWebOnTwoDeviceWithThread {
    private static SelendroidLauncher selendroidServer = null;
    private WebDriver driver1 = null;
    private WebDriver driver2 = null;
    private WebDriver driver3 = null;
    private DesiredCapabilities caps1 = null;
    private DesiredCapabilities caps2 = null;
    private DesiredCapabilities caps3 = null;
//  final CyclicBarrier gate = new CyclicBarrier(4);

    private static String LG_G_FLEX = "06510ebe170ef362";
    private static String SASUMG_Note3 = "20715382";
    private static String LG_G_2 = "018f5cf89c8c39ca";
    ;


    @Before
    public void startSelendroidServer() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }

        SelendroidConfiguration config = new SelendroidConfiguration();

        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.lauchSelendroid();
        System.out.println("Server gestartet!!!");

        caps1 = SelendroidCapabilities.android();
        driver1 = new SelendroidDriver(caps1);
        System.out.println("WebBrowser gestartet1!!!");

        caps2 = SelendroidCapabilities.android();
        driver2 = new SelendroidDriver(caps2);
        System.out.println("WebBrowser gestartet2!!!");

        caps3 = SelendroidCapabilities.android();
        driver3 = new SelendroidDriver(caps3);
        System.out.println("WebBrowser gestartet3!!!");
    }


    @Test
    public void shouldSearchWithEbay() {

        Thread t1 = new Thread() {
            public void run() {
            /*WebDriver driver1 =null;
			SelendroidConfiguration config = new SelendroidConfiguration();

			SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
		    selendroidServer.lauchSelendroid();
		    
		    System.out.println("Server gestartet!!!");*/

                //caps1 =  SelendroidCapabilities.android();
                caps1.setCapability(SelendroidCapabilities.SERIAL, SASUMG_Note3);
			/*try {
				driver1 = new SelendroidDriver(caps1);
			} catch (Exception e) {
			
				e.printStackTrace();
			}*/

                System.out.println("WebBrowser gestartet!!!" + "SASUMG_Note3");

                driver1.get("https://m.ebay.de");

                // Find the text input element by its id
                //WebElement element = driver.findElement(By.id("kw"));
                WebElement element = driver1.findElement(By.id("kw"));

                // Enter something to search for
                element.sendKeys("Nexus 5");

                // Now submit the form. WebDriver will find the form for us from the element

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                element.submit();

                driver1.get("https://google.com");
                element = driver1.findElement(By.name("q"));
                element.sendKeys("Solenium");
                element.submit();

                // Check the title of the page
                System.out.println("Page title is: " + driver1.getTitle() + caps1.getCapability(SelendroidCapabilities.SERIAL).toString());
//		    driver1.quit();
            }
        };

        Thread t2 = new Thread() {

            public void run() {
			/*SelendroidConfiguration config = new SelendroidConfiguration();

	    	SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
	        selendroidServer.lauchSelendroid();
	        WebDriver driver2 = null;
	        */

//	        caps2 = SelendroidCapabilities.android();
                caps2.setCapability(SelendroidCapabilities.SERIAL, LG_G_FLEX);
	         /*try {
	    		driver2 = new SelendroidDriver(caps2);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}*/
                System.out.println("WebBrowser gestartet!!!" + "LG_G_FLEX");

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
                System.out.println("Page title is: " + driver2.getTitle() + caps2.getCapability(SelendroidCapabilities.SERIAL).toString());

                //driver2.quit();
            }
        };


        Thread t3 = new Thread() {
            public void run() {
			/*SelendroidConfiguration config = new SelendroidConfiguration();

			SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
		    selendroidServer.lauchSelendroid();
		    
		    System.out.println("Server gestartet!!!");
		   */

//		    caps3 = SelendroidCapabilities.android();
                caps3.setCapability(SelendroidCapabilities.SERIAL, LG_G_2);
		    /*try {
				driver3 = new SelendroidDriver(caps3);
			} catch (Exception e) {
				e.printStackTrace();
			}*/

                System.out.println("WebBrowser gestartet!!!" + "LG_G_2");

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
                System.out.println("Page title is: " + driver3.getTitle() + caps3.getCapability(SelendroidCapabilities.SERIAL).toString());
                // driver3.quit();
            }
        };

        t1.run();
        t2.run();
        t3.run();

    }

    @After
    public void stopSelendroidServer() {
        if (driver1 != null) {
//      driver1.quit();
        }
        if (selendroidServer != null) {
//      selendroidServer.stopSelendroid();
        }
    }
}

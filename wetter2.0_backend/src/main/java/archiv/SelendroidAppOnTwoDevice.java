package archiv;


import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SelendroidAppOnTwoDevice {
    private static SelendroidLauncher selendroidServer = null;
    private static WebDriver driver = null;
    private static WebDriver driver2 = null;
    private static WebDriver driver3 = null;

    //private static String LG_G_FLEX 		= "06510ebe170ef362";
    //private static String SASUMG_Note3 	= "20715382";
    //private static String LG_G_2 			= "018f5cf89c8c39ca";

    @BeforeClass
    public static void startSelendroidServer() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();
        config.addSupportedApp("U:/02_Libraries/Selendroid-0.10.0/selendroid-test-app-0.11.0.apk");
        //config.setForceReinstall(true);

        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.lauchSelendroid();

        /**
         * LG:SetUp
         */
        SelendroidCapabilities caps = new SelendroidCapabilities("io.selendroid.testapp:0.11.0");

        System.out.println("Client gestartet!!!" + caps.getSerial());
        driver = new SelendroidDriver(caps);

        System.out.println("App gestartet1!!!" + caps.getSerial());
        driver.switchTo().window("NATIVE_APP");
        System.out.println("App gestartet2!!!" + caps.getSerial());
        driver.get("and-activity://io.selendroid.testapp.HomeScreenActivity");
        System.out.println("App gestartet!!!" + caps.getSerial());


        /**
         * Samsung: SetUP
         */
        SelendroidCapabilities caps2 = new SelendroidCapabilities("io.selendroid.testapp:0.11.0");

        System.out.println("Client gestartet!!!" + caps2.getSerial());
        driver2 = new SelendroidDriver(caps2);
        System.out.println("App gestartet1!!!" + caps2.getSerial());
        driver2.switchTo().window("NATIVE_APP");
        System.out.println("App gestartet2!!!" + caps2.getSerial());
        driver2.get("and-activity://io.selendroid.testapp.HomeScreenActivity");
        System.out.println("App gestartet!!!" + caps2.getSerial());


        /**
         * Samsung: SetUP
         */
        SelendroidCapabilities caps3 = new SelendroidCapabilities("io.selendroid.testapp:0.11.0");
        //caps.setBrowserName("android");
        //caps.setPlatform(Platform.ANDROID);;
        //caps.setSerial(SASUMG_Note3);
        //caps.setCapability("serial" , LG_G_FLEX);
        //caps.setEmulator(false);
        //caps.setSerial(LG_G_FLEX);
        //caps.setVersion("4.3");
        //aps.setVersion("4.4.2");

        System.out.println("Client gestartet!!!" + caps3.getSerial());
        driver3 = new SelendroidDriver(caps3);
        System.out.println("App gestartet1!!!" + caps3.getSerial());
        driver3.switchTo().window("NATIVE_APP");
        System.out.println("App gestartet2!!!" + caps3.getSerial());
        driver3.get("and-activity://io.selendroid.testapp.HomeScreenActivity");
        System.out.println("App gestartet!!!" + caps3.getSerial());
    }


    @Test
    public void testShouldBeAbleToEnterText() throws InterruptedException {

	/*
	 * LG
	 */
        WebElement inputField1 = driver.findElement(By.id("my_text_field"));
        inputField1.sendKeys("Selendroid");
        Assert.assertEquals("Selendroid", inputField1.getText());

        WebElement inputField2 = driver.findElement(By.id("waitingButtonTest"));
        // WebElement inputField2 = driver.findElement(By.linkText(""));
        inputField2.click();
        driver.manage().timeouts();

        /**
         * Samsung
         */
        WebElement inputField3 = driver2.findElement(By.id("my_text_field"));
        inputField3.sendKeys("Selendroid");
        Assert.assertEquals("Selendroid", inputField3.getText());

        WebElement inputField4 = driver2.findElement(By.id("waitingButtonTest"));
        inputField4.click();
        driver2.manage().timeouts();

        /**
         * Samsung
         */
        WebElement inputField5 = driver3.findElement(By.id("my_text_field"));
        inputField5.sendKeys("Selendroid");
        Assert.assertEquals("Selendroid", inputField5.getText());

        WebElement inputField6 = driver3.findElement(By.id("waitingButtonTest"));
        inputField6.click();
        driver3.manage().timeouts();
        Thread.sleep(30000);
    }


    @AfterClass
    public static void stopSelendroidServer() {
        if (driver != null) {
            //driver.quit();
        }
        if (selendroidServer != null) {
            //selendroidServer.stopSelendroid();
        }
    }
}

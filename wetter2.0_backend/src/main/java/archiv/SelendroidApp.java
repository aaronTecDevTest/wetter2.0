package archiv;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import org.openqa.selenium.Platform;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SelendroidApp {
    private static SelendroidLauncher selendroidServer = null;
    private static WebDriver driver = null;


    @BeforeClass
    public static void startSelendroidServer() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();
        config.addSupportedApp("U:/02_Libraries/Selendroid-0.10.0/selendroid-test-app-0.10.0.apk");
        //config.setForceReinstall(true);
        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.lauchSelendroid();

        SelendroidCapabilities caps = new SelendroidCapabilities("io.selendroid.testapp:0.10.0");
        //caps.setPlatform(Platform.ANDROID);;

        System.out.println("Client gestartet!!! +" + caps.getSerial());

        driver = new SelendroidDriver(caps);
        driver.switchTo().window("NATIVE_APP");
        driver.get("and-activity://io.selendroid.testapp.HomeScreenActivity");
        System.out.println("App gestartet!!!" + caps.getSerial());
    }


    @Test
    public void testShouldBeAbleToEnterText() throws InterruptedException {
        WebElement inputField = driver.findElement(By.id("my_text_field"));
        inputField.sendKeys("Selendroid");
        Assert.assertEquals("Selendroid", inputField.getText());

        WebElement inputField2 = driver.findElement(By.id("waitingButtonTest"));
        inputField2.click();
        driver.manage().timeouts();
        Thread.sleep(30000);
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
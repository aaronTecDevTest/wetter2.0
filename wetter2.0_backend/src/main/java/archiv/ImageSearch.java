
package archiv;

import java.io.File;
import java.util.EmptyStackException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;

import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;

public class ImageSearch {

    public static void main(String[] args) {

        //Neue IE-Driver-Instance (Browser) erstellen
        //File file = new File("C:/Users/a.kutekidila/Driver/chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        WebDriver ffDriver = new FirefoxDriver();

        //WebDriver chDriver = new ChromeDriver();
        //chDriver.manage().window().maximize();

        ffDriver.manage().window().maximize();


        //FF-Driver init.
        ffDriver.get("https://10.224.220.92/rms/app/#/news");
//	ffDriver.get("https://code.google.com/p/sikuli-api/wiki/Canvas");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create a screen region object that corresponds to the default monitor in full screen
        ScreenRegion s = new DesktopScreenRegion(1, 0, 0, 1920, 1080);

        // Specify an image as the target to find on the screen
        Target imageTarget1 = new ImageTarget(new File("C:/Users/a.kutekidila/Pictures/Login_Icon.PNG"));
        Target imageTarget2 = new ImageTarget(new File("C:/Users/a.kutekidila/Pictures/Suche.PNG"));
        Target imageTarget3 = new ImageTarget(new File("C:/Users/a.kutekidila/Pictures/T-Online.PNG"));
        Target imageTarget4 = new ImageTarget(new File("C:/Users/a.kutekidila/Pictures/User_Icon.PNG"));

        try {
            // Wait for the target to become visible on the screen for at most 5 seconds
            // Once the target is visible, it returns a screen region object corresponding
            // to the region occupied by this target
            ScreenRegion r1 = s.wait(imageTarget1, 5000);

            if (r1 == null) {
                System.out.println("bild nicht gefunden");
                throw new EmptyStackException();
            } else {
                // Display "Hello World" next to the found target for 3 seconds
                Canvas canvas = new DesktopCanvas();
                canvas.addLabel(r1, "Login_Icon").display(3);
                canvas.addBox(r1).display(3);


                // Click the center of the found target
                Mouse mouse = new DesktopMouse();
                mouse.click(r1.getCenter());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        {
            // Wait for the target to become visible on the screen for at most 5 seconds
            // Once the target is visible, it returns a screen region object corresponding
            // to the region occupied by this target
            ScreenRegion r2 = s.wait(imageTarget2, 5000);

            // Display "Hello World" next to the found target for 3 seconds
            Canvas canvas = new DesktopCanvas();
            canvas.addLabel(r2, "Suche").display(3);
            canvas.addBox(r2).display(3);

            // Click the center of the found target
            Mouse mouse = new DesktopMouse();
            mouse.click(r2.getCenter());
        }

        {
            // Wait for the target to become visible on the screen for at most 5 seconds
            // Once the target is visible, it returns a screen region object corresponding
            // to the region occupied by this target
            ScreenRegion r3 = s.wait(imageTarget3, 5000);

            // Display "Hello World" next to the found target for 3 seconds
            Canvas canvas = new DesktopCanvas();
            canvas.addLabel(r3, "T-Online").display(3);
            canvas.addBox(r3).display(3);

            // Click the center of the found target
            Mouse mouse = new DesktopMouse();
            mouse.click(r3.getCenter());
        }

        {
            // Wait for the target to become visible on the screen for at most 5 seconds
            // Once the target is visible, it returns a screen region object corresponding
            // to the region occupied by this target
            ScreenRegion r4 = s.wait(imageTarget4, 5000);

            // Display "Hello World" next to the found target for 3 seconds
            Canvas canvas = new DesktopCanvas();
            canvas.addLabel(r4, "User_Icon").display(3);
            canvas.addBox(r4).display(3);

            // Click the center of the found target
            Mouse mouse = new DesktopMouse();
            mouse.click(r4.getCenter());
        }


        ffDriver.close();
    }
}

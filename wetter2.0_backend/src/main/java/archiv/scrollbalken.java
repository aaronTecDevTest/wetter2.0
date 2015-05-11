package archiv;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;

public class scrollbalken extends GenericTest_New {

    private final String testCaseShortName = "RMS";

    public final Properties PAGE = PropertiesManager_New.getInstance().getProperties("page");


    @Override
    public void runTest() {

        getBrowser();
        String navi = getNavigation(testCaseShortName);

        navigate(navi);


        WebElement scrollbar = getByXPath(".//*[@id='news_vscrollerbar']");

        System.out.println(scrollbar.getLocation());


        Actions dragger = new Actions(getBrowser());

        WebElement draggablePartOfScrollbar = getByXPath(".//*[@id='news_vscrollerbar']");

        // drag downwards
        int numberOfPixelsToDragTheScrollbarDown = 50;
        for (int i = 10; i < 500; i = i + numberOfPixelsToDragTheScrollbarDown) {
            try {
                // this causes a gradual drag of the scroll bar, 10 units at a time
                dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release().perform();
                Thread.sleep(1000L);
            } catch (Exception e1) {
            }
        }


        // now drag opposite way (downwards)
        numberOfPixelsToDragTheScrollbarDown = -50;
        for (int i = 500; i > 10; i = i + numberOfPixelsToDragTheScrollbarDown) {
            // this causes a gradual drag of the scroll bar, -10 units at a time
            dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release().perform();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // close browser
        closeBrowser(); // closes the browser and catches any errors

    }

    public static void main(String[] args) {
        scrollbalken test = new scrollbalken();
        test.before();
        test.runTest();
        test.after();
    }

    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        super.setRunningConfiguration(runConfig);
    }

}

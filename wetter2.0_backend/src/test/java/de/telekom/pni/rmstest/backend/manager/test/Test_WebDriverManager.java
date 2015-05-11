package de.telekom.pni.rmstest.backend.manager.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.manager.WebDriverManager;

/**
 * Eine JUnit Testklasse, die die Methoden der WebDriverManager Klasse überprüft.
 *
 * @author M.Forster
 */
public class Test_WebDriverManager {

    /**
     * Diese Test-Methode überprüft das Konfigurieren des WebDriver mit fehlerhafter RunningConfiguration.
     */
    @Test
    public void testSetUpDriver_wrongConfig() {
        RunningConfiguration_New runConfig = new RunningConfiguration_New("", "Pretest");
        WebDriverManager manager = new WebDriverManager();
        WebDriver driver = manager.setUpDriver(runConfig);
        try {
            driver.getClass();
            driver.close();
        } catch (Exception e) {
            fail("Driver not functional" + e.getMessage());
        }
    }
}

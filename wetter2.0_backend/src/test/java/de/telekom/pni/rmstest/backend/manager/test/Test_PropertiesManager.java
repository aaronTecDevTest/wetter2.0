package de.telekom.pni.rmstest.backend.manager.test;

import static org.junit.Assert.fail;

import java.util.Properties;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import org.junit.Test;


/**
 * Eine JUnit Testklasse, die die Methoden der PropertiesManager Klasse überprüft.
 *
 * @author M.Forster
 */
public class Test_PropertiesManager {

    /**
     * Diese Test-Methode überprüft die Methode getProperties der getesteten Klasse. Es wird das Verhalten
     * bei null als Übergabeparameter getestet.
     */
    @Test
    public void testGetProperties_paramNull() {
        try {
            PropertiesManager_New.getInstance().getProperties(null);
        } catch (ExceptionInInitializerError ex) {
            System.out.println("Initialisation error thrown");
        } catch (Exception ex) {
            fail("Exception thrown because of corrupted return value. " + ex.getMessage());
        }
    }

    /**
     * Diese Test-Methode überprüft die Methode getProperties der getesteten Klasse. Es wird das
     * positive Abrufen einer Property geprüft.
     */
    @Test
    public void testGetProperties_positive() {
        try {
            Properties prop = PropertiesManager_New.getInstance().getProperties("page");
            String string = prop.getProperty("button.login");
            string.getBytes();
        } catch (ExceptionInInitializerError ex) {
            fail("Initialisation error thrown");
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }
}


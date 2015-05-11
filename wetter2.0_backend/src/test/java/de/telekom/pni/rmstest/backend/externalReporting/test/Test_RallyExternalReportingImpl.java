package de.telekom.pni.rmstest.backend.externalReporting.test;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.telekom.pni.rmstest.backend.config.LoginDataRally;
import de.telekom.pni.rmstest.backend.externalReporting.RallyExternalReportingImpl;
import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;

/**
 * Eine JUnit Testklasse, die die Methoden der RallyExternalReportingImpl Klasse überprüft.
 *
 * @author M.Forster
 */
public class Test_RallyExternalReportingImpl {

    private static RallyExternalReportingImpl rally;
    public static final Properties AUTORUN = PropertiesManager_New.getInstance().getProperties("autorun");

    /**
     * Diese Methode wird vor dem Durchführen der Tests einmalig ausgeführt und sorgt für die Initialisierung
     * der benötigten Komponenten.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rally = new RallyExternalReportingImpl();
    }

    /**
     * Diese Methode wird nach dem Durchführen aller Tests ausgeführt. Sie ist für die Bereinigung des
     * Systems zuständigt.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * Diese Test-Methode überprüft die connect Methode der getesteten Klasse. Es wird das Verhalten bei
     * der Anmeldung ohne Benutzernamen geprüft.
     */
    @Test
    public void testConnect_NoUser() {
        LoginDataRally login = new LoginDataRally();
        login.setDomain("workspace=/workspace/27444");
        login.setProject("project=project/29402");
        login.setPassword("12345");
        try {
            rally.connect(login);
        } catch (Exception ex) {
            fail("Error: Exception was thrown" + ex.getMessage());
        }
        if (rally.connect(login)) {
            fail("Error Return value was true but should be false");
        }

    }

    /**
     * Diese Test-Methode überprüft die connect Methode der getesteten Klasse. Es wird das Verhalten
     * bei der Anmeldung mit falschem Passwort geprüft.
     */
    @Test
    public void testConnect_WrongPwd() {

        LoginDataRally login = new LoginDataRally();
        login.setDomain("workspace=/workspace/27444");
        login.setProject("project=project/29402");
        login.setUsername(AUTORUN.getProperty("user"));
        login.setPassword("12345");
        try {
            rally.connect(login);
        } catch (Exception ex) {
            fail("Error: Exception was thrown" + ex.getMessage());
        }
        if (rally.connect(login)) {
            fail("Error Return value was true but should be false");
        }
    }

    /**
     * Diese Test-Methode überprüft die Methode connect der getesteten Klasse. Es wird die Anmeldung
     * mit korrekten Benutzerdaten geprüft.
     */
    @Test
    public void testConnect_Successfull() {
        LoginDataRally login = new LoginDataRally();
        login.setDomain("workspace=/workspace/27444");
        login.setProject("project=project/29402");
        login.setUsername(AUTORUN.getProperty("user"));
        login.setPassword(AUTORUN.getProperty("password"));
        try {
            rally.connect(login);
        } catch (Exception ex) {
            fail("Error: Exception was thrown" + ex.getMessage());
        }
        if (!rally.connect(login)) {
            fail("Error Return value was false but should be true");
        }
    }

}

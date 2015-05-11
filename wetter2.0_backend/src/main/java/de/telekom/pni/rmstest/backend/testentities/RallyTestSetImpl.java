package de.telekom.pni.rmstest.backend.testentities;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import de.telekom.pni.rmstest.backend.config.TestrunConfiguration_New;
import de.telekom.pni.rmstest.backend.reporting.Reporter;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;

/**
 * Die Klasse BackendTestSetImpl.java wird vom Rally Interface für die Verwaltung
 * von TestSets verwendet. Es können alle nötigen Daten zum TestSet abgelegt werden.
 * Um die Kompatibilität zum Framework zu gewährleisten, implementiert die Klasse das TestSet
 * interface und seine Methoden.
 *
 * @author M.Forster
 */
public class RallyTestSetImpl implements TestSet {

    private static final Logger log = Logger.getLogger(Reporter.class);
    private Vector<TestCase> tests;
    private int testSetID;
    private String testSetName;
    private TestrunConfiguration_New config;

    /**
     * Konstruktor der Klasse. Er extrahiert Name und ID aus dem ihm übergebenen JsonObject und trägt sie in
     * die passenden Variablen ein. Des Weiteren initialisiert er den Vector tests.
     *
     * @param testSetRally
     */
    public RallyTestSetImpl(JsonObject testSetRally) {
        log.debug("Creating RallyTestSetImpl-Object");
        this.testSetName = testSetRally.getAsJsonObject().get("_refObjectName").toString();
        String refString = testSetRally.getAsJsonObject().get("_ref").toString();
        String idString = refString.substring(refString.lastIndexOf("/") + 1, refString.lastIndexOf("."));
        this.testSetID = (Integer.valueOf(idString));
        tests = new Vector<TestCase>();
        log.debug("RallyTestSetImpl-Object created");
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Wertes von config.
     *
     * @return config - Konfiguration des TestSet als TestrunConfiguration Objekt
     */
    @Override
    public TestrunConfiguration_New getConfig() {
        return config;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testSetName.
     *
     * @return testSetName - Name des TestSet als String
     */
    @Override
    public String getTestSetName() {
        return testSetName;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testSetID.
     *
     * @return testSetID - ID des TestSet als Integer
     */
    @Override
    public Integer getTestSetID() {
        return testSetID;
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testCases.
     *
     * @return testCases - Ein Vector, der alls Testfälle als TestCase Objekte beinhaltet.
     */
    public Vector<TestCase> getTestCases() {
        return tests;
    }

    /**
     * Setter Methode zum Setzen des Werts für config.
     *
     * @param config - Der zu setzende Wert für die Konfiguration des TestSet als TestrunConfiguration Objekt
     */
    @Override
    public void setConfig(TestrunConfiguration_New config) {
        this.config = config;
    }

    /**
     * Methode zum hinzufügen eines Testfalls zum Kontainer, der alle dem TestSet zugeordneten Testfälle
     * enthält.
     *
     * @param test - Der hinzuzufügende Testfall als TestCase Objekt
     */
    @Override
    public void addTestCase(TestCase testCase) {
        tests.add(testCase);
    }
}

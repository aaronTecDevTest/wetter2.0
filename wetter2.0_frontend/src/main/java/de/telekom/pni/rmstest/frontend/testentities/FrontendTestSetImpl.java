package de.telekom.pni.rmstest.frontend.testentities;

import java.util.Vector;

import org.apache.log4j.Logger;

import de.telekom.pni.rmstest.backend.config.TestrunConfiguration_New;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;

/**
 * Die Klasse FrontendTestSetImpl.java wird im Frontend des Frameworks für die Verwaltung
 * von TestSets verwendet. Es können alle nötigen Daten zum TestSet abgelegt werden.
 * Um die Kompatibilität zum Framework zu gewährleisten, implementiert die Klasse das TestSet
 * interface und seine Methoden.
 *
 * @author M.Forster
 */
public class FrontendTestSetImpl implements TestSet {

    private static final Logger log = Logger.getLogger(FrontendTestSetImpl.class);
    private Vector<TestCase> testCases;
    private String testSetName;
    private Integer testSetID;
    private TestrunConfiguration_New config = null;

    /**
     * Konstruktor der Klasse. Er trägt die ihm übergebenen Werte für testSetName und testSetID in
     * die passenden Variablen ein und initialisiert den testCases Vector.
     *
     * @param testSetName - Der zu setzende Wert für den Namen des Testset als String
     * @param id          - Der zu setzende Wert für die ID des TestSet als Integer
     */
    public FrontendTestSetImpl(String testSetName, Integer id) {
        log.debug("Creating FrontedTestSetImpl-Object");
        this.testSetName = testSetName;
        this.testSetID = id;
        this.testCases = new Vector<TestCase>();
        log.debug("FrontendTestSetImpl-Object created");
    }

    /**
     * Getter Methode zum Abrufen des aktuellen Werts von testCases.
     *
     * @return testCases - Ein Vector, der alls Testfälle als TestCase Objekte beinhaltet.
     */
    public Vector<TestCase> getTestCases() {
        // TODO Auto-generated method stub
        return testCases;
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
     * Setter Methode zum Setzen des Werts für config.
     *
     * @param config - Der zu setzende Wert für die Konfiguration des TestSet als TestrunConfiguration Objekt
     */
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
        testCases.add(testCase);
    }

}

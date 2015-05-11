package de.telekom.pni.rmstest.backend.testentities.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonObject;

import de.telekom.pni.rmstest.backend.testentities.RallyTestCaseImpl;
import de.telekom.pni.rmstest.backend.testentities.RallyTestSetImpl;

/**
 * Eine JUnit Testklasse, die die Methoden der RallyTestSetImpl Klasse überprüft.
 *
 * @author M.Forster
 */
public class Test_RallyTestSetImpl {

    /**
     * Diese Test-Methode überprüft ob ein BackendTestSetImpl Objekt korrekt erzeugt wird.
     */
    @Test
    public void testConstructor() {
        JsonObject jsonTestSet = new JsonObject();
        jsonTestSet.addProperty("_refObjectName", "Beispiel Abnahmetest Build:1.67 FF");
        jsonTestSet.addProperty("_ref", "https://rally-pui.telekom.de/slm/webservice/1.37/testset/544392.js");
        RallyTestSetImpl testSet = new RallyTestSetImpl(jsonTestSet);
        try {
            testSet.getTestSetName().getBytes();
        } catch (Exception ex) {
            fail("Error: TestSet Name was not initialized correctly. " + ex.getMessage());
        }
        try {
            testSet.getTestSetID().intValue();
        } catch (Exception ex) {
            fail("Error: TestSet ID was not initialized correctly. " + ex.getMessage());
        }
        try {
            testSet.getTestCases().getClass();
        } catch (Exception ex) {
            fail("Error: TestCases Vector was not initialized correctly. " + ex.getMessage());
        }
    }

    /**
     * Diese Test-Merhode überprüft das Hinzufügen eines korrekten Testfalls.
     */
    @Test
    public void testAddTestCase() {
        JsonObject jsonTestSet = new JsonObject();
        jsonTestSet.addProperty("_refObjectName", "Beispiel Abnahmetest Build:1.67 FF");
        jsonTestSet.addProperty("_ref", "https://rally-pui.telekom.de/slm/webservice/1.37/testset/544392.js");
        RallyTestSetImpl testSet = new RallyTestSetImpl(jsonTestSet);
        JsonObject jsonTest = new JsonObject();
        jsonTest.addProperty("_refObjectName", "Test Case US002");
        jsonTest.addProperty("ObjectID", 544268);
        jsonTest.addProperty("ScriptDeveloper", "Özgür Ünal");
        jsonTest.addProperty("Browser", "IE-FF");
        jsonTest.addProperty("Executable", "de.telekom.pni.rmstest.backend.testing.testcases.TC47_TestCaseUS002 ");
        jsonTest.addProperty("Automatable", "3. ist automatisiert");
        testSet.addTestCase(new RallyTestCaseImpl(jsonTest));
        try {
            testSet.getTestCases().get(0).getTestCaseName();

        } catch (Exception ex) {
            fail("Error: TestCase was not added correctly. " + ex.getMessage());
        }
    }

}

package de.telekom.pni.rmstest.backend.testentities.interfaces;

import java.util.Vector;

import de.telekom.pni.rmstest.backend.config.TestrunConfiguration_New;

/**
 * TestSet.java ist ein Interface. Es legt fest welche Methoden für eine Klasse
 * zum Verwalten von TestSet Daten zwingend erforderlich sind, um vom Framework
 * verwendet werden zu können. Alle Klassen, die einen TestSet implementieren sollen,
 * und mit dem Framework kompatibel sein wollen, müssen dieses Interface implementieren.
 *
 * @author M.Forster
 */
public interface TestSet {

    public String getTestSetName();

    public Vector<TestCase> getTestCases();

    public TestrunConfiguration_New getConfig();

    public void setConfig(TestrunConfiguration_New config);

    public void addTestCase(TestCase testCase);

    public Integer getTestSetID();
}
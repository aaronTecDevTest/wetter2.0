package de.telekom.pni.rmstest.backend.testentities.interfaces;

import java.util.Vector;

/**
 * TestCase.java ist ein Interface. Es legt fest welche Methoden für eine Klasse
 * zum Verwalten von TestCase Daten zwingend erforderlich sind, um vom Framework
 * verwendet werden zu können. Alle Klassen, die einen TestCase implementieren sollen,
 * und mit dem Framework kompatibel sein wollen, müssen dieses Interface implementieren.
 *
 * @author M.Forster
 */
public interface TestCase {

    public String getTestCaseName();

    public String getSimpleTestName();

    public TestSet getTestSet();

    public Vector<TestStep> getTestSteps();

    public String getExecutableClass();

    public String getDeveloper();

    public Integer getTestCaseID();

    public String getCreationDate();

    public String getDescription();

    public String getStatus();

    public String getLastRun();

    public String getAutomatable();

    public void setExecutableClass(String execClass);

    public void setTestCaseID(Integer testCaseId);

    public void setTestSet(TestSet testSet);

    public void setStatus(String status);

    public void setAutomatable(String automatable);

    public void setLastrun(String lastRun);

    @Override
    public String toString();
}
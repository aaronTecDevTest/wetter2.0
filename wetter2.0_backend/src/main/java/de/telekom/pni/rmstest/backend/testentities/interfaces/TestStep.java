package de.telekom.pni.rmstest.backend.testentities.interfaces;

/**
 * TestCase.java ist ein Interface. Es legt fest welche Methoden für eine Klasse
 * zum Verwalten von TestStep Daten zwingend erforderlich sind, um vom Framework
 * verwendet werden zu können. Alle Klassen, die einen TestStep implementieren sollen,
 * und mit dem Framework kompatibel sein wollen, müssen dieses Interface implementieren.
 *
 * @author M.Forster
 */

public interface TestStep {

    public int getTestStepID();

    public String getTestStepName();

    public String getTestStepAction();

    public String getTestStepExpected();

    public String getTestStepInput();

    public String getTestStepTarget();

    public void setTestStepName(String name);

    public void setTestStepID(int id);

    public void setTestStepAction(String action);

    public void setTestStepInput(String input);

    public void setTestStepExpected(String expected);

    public void setTestStepTarget(String target);
}
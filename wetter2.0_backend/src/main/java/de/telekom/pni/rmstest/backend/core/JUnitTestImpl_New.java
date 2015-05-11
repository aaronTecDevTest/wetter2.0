package de.telekom.pni.rmstest.backend.core;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestStep;

/**
 * JUnitTestImpl.java ist die konkrete Implementierung der abstrakten Klasse JUnitTest.java
 * Die Klasse wird verwendet die internen, in der Datenbank gespeicherten Testfälle auszuführen.
 * Die Klasse verarbeitet das vom TestRunnerThread übergebene TestCase Objekt und führt die darin
 * eingetragenen Testschritte nacheinander aus.
 *
 * @author M.Forster
 */
public class JUnitTestImpl_New extends JUnitTest_New {

    private static final Logger log = Logger.getLogger(JUnitTestImpl_New.class);
    private TestCase testCase;

    /**
     * Konstruktor der Klasse. Er führt den Konstruktor der Mutterklasse aus und verarbeitet die
     * übergebenen Variablen.
     *
     * @param test - TestCase Objekt, das die Daten zum auszuführenden Testfall beinhaltet.
     */
    public JUnitTestImpl_New(TestCase test) {
        super();
        log.debug("Entering Constructor");
        this.testCase = test;
        log.debug("Leaving Constructor");
    }


    /**
     * Diese Methode führt die einzelnen Testschritte des Tests durch. Aus dem TestCase Ojekt
     * werden die TestStep Objekte ausgelesen und die dort beschrieben Schritte nacheinander
     * ausgeführt. Je nachdem, welche Aktion in der Variable testStepAction eingetragen ist, wird
     * die passende Methode aus JUnitTest.java ausgeführt.
     * Während dem Test aufgetretende Fehler werden am aufgefangen und passend verarbeitet.
     */
    @Test
    public void runTest() {
        try {
            log.debug("Starting internal test " + testCase.getTestCaseName());
            for (TestStep step : testCase.getTestSteps()) {
                try {
                    switch (step.getTestStepAction()) {
                        case "getPage":
                            getPage(step.getTestStepTarget());
                            break;
                        case "sendKeys":
                            sendKeys(PAGE.getProperty(step.getTestStepTarget()), step.getTestStepInput());
                            break;
                        case "click":
                            clickElement(PAGE.getProperty(step.getTestStepTarget()));
                            break;
                        case "testEquals":
                            WebElement equalElement = getElement("xpath", PAGE.getProperty(step.getTestStepTarget()));
                            testEquals(equalElement, step.getTestStepExpected(), step.getTestStepName());
                            break;
                        case "testNotExist":
                            testElementNotPresent(PAGE.getProperty(step.getTestStepTarget()), step.getTestStepTarget());
                            break;
                        case "testExist":
                            testElementPresent(PAGE.getProperty(step.getTestStepTarget()), step.getTestStepTarget());
                            break;
                        case "testNotNull":
                            String notNullElementString = PAGE.getProperty(step.getTestStepTarget());
                            String notNullStep = step.getTestStepTarget();
                            testElementPresent(notNullElementString, notNullStep);
                            break;
                        default:
                            break;
                    }
                } catch (AssertionError ae) {
                    appendFailure("Assertion Error: " + ae.getMessage());
                } catch (Error er) {
                    appendError("Internal Error while running the Test. Please check the implementation. \r\n" + er.getMessage());
                } catch (Exception ex) {
                    getReporter().setStatus(false);
                    appendError("Internal Error while running the Test. Please check the implementation. \r\n" + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            getReporter().setStatus(false);
            appendError("Internal Error while running the Test. Please check the implementation. \r\n" + ex.getMessage());
        }
        log.debug("Internal Test finished");
    }

    /**
     * Diese Methode überschreibt die standart toString() Methode. Anstatt des Standard Strings wird
     * ein neuer String erzeugt, der aus dem String "JUnitTestImpl [testCase= ]" und dem Namen des Testfalls
     * zusammengesetzt wird.
     */
    @Override
    public String toString() {
        return "JUnitTestImpl [testCase=" + testCase.getTestCaseName() + "]";
    }


    @Override
    public void setRunningConfiguration(RunningConfiguration_New runConfig) {
        //super.setRunningConfiguration(runConfig);
    }


}

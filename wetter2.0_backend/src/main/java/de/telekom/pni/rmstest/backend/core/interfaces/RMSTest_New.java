package de.telekom.pni.rmstest.backend.core.interfaces;

import java.util.HashMap;

import junit.framework.Test;
import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;
import de.telekom.pni.rmstest.backend.reporting.Report;

/**
 * RMSTest.java ist ein Interface, mit dem automatisierte Testfälle erstellt werden können.
 * Es gibt das Grundgerüst für diese Testfälle vor und benennt die Methoden, die von den
 * das Interface implementierenden Klassen zwingend implementiert werden müssen.
 * Durch die Verwendung eines Interface wird es ermöglicht, unterschiedliche Implementierungen
 * von automatisierten Testfällen im Framework zu verwenden.
 * Das Interface erbt von der JUnit Klasse Test, damit dessen Funktionalitäten von den implementierenden
 * Klassen verwendet werden können. Des Weiteren können diese Klassen dadurch im JUnit Framework
 * verwendet werden.
 * Die einzelnen Methoden sind hier nicht weiter implementiert, da sie erst in den konkreten
 * Klassen ausprogramiert und dort genau beschrieben werden.
 *
 * @author A.Roth
 * @author M.Forster
 */
public interface RMSTest_New extends Test {

    public void runTest();

    public void before();

    public void after();

    public Report getReport();

    public void setURLValues(HashMap<String, String> urlValues);

    public void setRunningConfiguration(RunningConfiguration_New runConf);

    public Report finalizeReport(String testName);


    public void closeBrowser();

}

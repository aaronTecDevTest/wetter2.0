package de.telekom.pni.rmstest.backend.externalReporting.interfaces;

import java.util.List;

import javax.swing.JTree;

import de.telekom.pni.rmstest.backend.reporting.Report;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;
import de.telekom.pni.rmstest.backend.config.LoginDataRally;

/**
 * ExternalReporting.java ist ein Interface. Es Beschreibt eine Schnittstelle für den Zugriff auf
 * eine externe Reporting Komponente, in der Testergebnisse gespeichert werden können.
 * Um Kompatibilität zum Framework zu gewährleisten, muss dieses Interface von allen
 * konkreten Klassen für den Zugeriff auf ein externes Reporting System implementiert werden.
 *
 * @author M.Forster
 */
public interface ExternalReporting {
    public boolean connect(LoginDataRally loginData);

    public boolean disconnect();

    public boolean writeTestResult(TestCase test, Report result);

    public TestSet getTestSet(String testSetID);//, String filter);

    public JTree getTestlabStructure();

    public List<String> getTestSetList();
}
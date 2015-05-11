package de.telekom.pni.rmstest.backend.dataStorage.interfaces;

import java.util.List;

import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;

/**
 * StorageManager.java ist ein Interface. Es Beschreibt eine Schnittstelle für den Zugriff auf
 * ein Speichermedlium, in dem Testfälle abgelegt werden können. Um Kompatibilität zum Framework zu
 * gewährleisten, muss dieses Interface von allen Speichermedien implementiert werden.
 *
 * @author M.Forster
 */
public interface StorageManager {

    public boolean connect();

    public boolean disconnect();

    public boolean addTestCase(TestCase testCase);

    public boolean updateTestCase(TestCase testCase, List<Integer> delStepsList);

    public boolean deleteTestCase(TestCase testCase);

    public TestCase getTestCase(String name);
}

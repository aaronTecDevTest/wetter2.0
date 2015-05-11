package de.telekom.pni.rmstest.backend.config;

import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;

/**
 * Die Klasse TestrunConfiguration.java wird fpr die Speicherung der Konfigurationsdaten zum Testlauf aus Rally abgelegt.
 * Die Klasse wurde vom ursprünglichen Entwickler des Frameworks entwicklet und wird ohne Änderungen weiter verwendet.
 * Da die Klasse nicht von mir verändert wurde, ist sie nicht weiter Dokumentiert.
 *
 * @author A.Roth
 */
public class TestrunConfiguration_New {


    private TestSet testSet;
    private int id;
    private String name;
    private String machine;
    private String browser;

    public TestrunConfiguration_New(int confID) {
        this.id = confID;
    }

    public TestSet getTestSet() {
        return testSet;
    }

    public void setTestSet(TestSet testSet) {
        this.testSet = testSet;
    }

    public int getId() {
        return id;
    }

//	public void setId(int id) {
//		this.id = id;
//	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

}

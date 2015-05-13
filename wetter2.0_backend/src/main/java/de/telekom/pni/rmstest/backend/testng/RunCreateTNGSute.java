package de.telekom.pni.rmstest.backend.testng;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.kutekidila on 13.05.2015.
 */

public class RunCreateTNGSute {

    XmlSuite suite = new XmlSuite();
    List<XmlSuite> suites = new ArrayList<XmlSuite>();

    TestNG tng = new TestNG();
    List<XmlClass> classes = new ArrayList<XmlClass>();

    XmlTest testXML = new XmlTest(suite);

    public void runVirtualSuit() {
        createVirtualSuite();
        suites.add(suite);
        tng.setXmlSuites(suites);
        tng.run();
    }

    private void createVirtualSuite() {
        suite.setName("TmpSuite");
        testXML.setName("TmpTest");


        classes.add(new XmlClass("de.telekom.pni.rmstest.backend.testng.test.SimpleTest1"));
        classes.add(new XmlClass("de.telekom.pni.rmstest.backend.testng.test.SimpleTest2"));
        testXML.setXmlClasses(classes) ;


       // System.out.println(testXML.getSuite().toXml());
    }

    public static void main(String[] args) {
        RunCreateTNGSute runTest = new RunCreateTNGSute();
        runTest.runVirtualSuit();
    }
}

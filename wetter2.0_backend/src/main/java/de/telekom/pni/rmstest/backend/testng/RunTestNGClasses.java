package de.telekom.pni.rmstest.backend.testng;

/**
 * Created by a.kutekidila on 13.05.2015.
 */

import de.telekom.pni.rmstest.backend.testng.test.SimpleTest1;
import de.telekom.pni.rmstest.backend.testng.test.SimpleTest2;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class RunTestNGClasses {
    TestListenerAdapter tla;
    TestNG testng;


    RunTestNGClasses(){
        tla = new TestListenerAdapter();
        testng = new TestNG();
    }


    public void run() {
        testng.setTestClasses(new Class[]{SimpleTest1.class, SimpleTest2.class});
        testng.addListener(tla);
        testng.run();
    }


    public static void main(String[] args) {
        RunTestNGClasses runTest= new RunTestNGClasses();
        runTest.run();
    }
}

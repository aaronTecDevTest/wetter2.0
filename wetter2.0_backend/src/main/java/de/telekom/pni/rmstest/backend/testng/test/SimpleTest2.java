package de.telekom.pni.rmstest.backend.testng.test;

/**
 * Created by a.kutekidila on 04.05.2015.
 */

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.log4j.Logger;

public class SimpleTest2 {

    private static final Logger log= Logger.getLogger(SimpleTest2.class);

    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Test(groups = {"fast"})
    public void aFastTest() {

        System.out.println("Fast test 20");
        Reporter.log("test Reporter",true);
    }

    @Test(groups = {"slow"})
    public void aSlowTest() {
        System.out.println("Slow test 30");
    }
}

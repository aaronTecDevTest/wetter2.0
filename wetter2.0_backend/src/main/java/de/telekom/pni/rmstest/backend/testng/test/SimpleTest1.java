package de.telekom.pni.rmstest.backend.testng.test;

/**
 * Created by a.kutekidila on 04.05.2015.
 */


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

//import java.util.logging.Logger;

import org.apache.log4j.Logger;


public class SimpleTest1 {

    private static final Logger log= Logger.getLogger(SimpleTest1.class.getName());
    public SimpleTest1(){
    }


    //@BeforeClass
    @BeforeTest
    public void setUp() {
    }

    @Test(groups = {"fast"})
    public void aFastTest() {
        System.out.println("Fast test 2");
        Reporter.log("Test Reporter",true);
        Reporter.log("Test Reporter", true);
        log.info("asfasdfasdfadf");
        Assert.assertFalse(true);
    }

    @Test(groups = {"slow"})
    public void aSlowTest() {
        System.out.println("Slow test 3");
        Assert.assertFalse(false);
    }
}

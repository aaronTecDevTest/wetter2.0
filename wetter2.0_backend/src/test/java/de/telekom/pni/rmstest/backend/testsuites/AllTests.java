package de.telekom.pni.rmstest.backend.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//import de.telekom.pni.rmstest.backend.core.test.Test_JUnitTestImpl_RunningBackendTests;
import de.telekom.pni.rmstest.backend.core.test.Test_JUnitTest_supportMethods;
import de.telekom.pni.rmstest.backend.core.test.Test_JUnitTest_testingMethods;
import de.telekom.pni.rmstest.backend.manager.test.Test_PropertiesManager;
import de.telekom.pni.rmstest.backend.manager.test.Test_WebDriverManager;
import de.telekom.pni.rmstest.backend.reporting.test.Test_Reporter;

@RunWith(Suite.class)
@SuiteClasses({
        //Test_JUnitTestImpl_RunningBackendTests.class,
        Test_JUnitTest_supportMethods.class,
        Test_JUnitTest_testingMethods.class,
        Test_PropertiesManager.class,
        Test_WebDriverManager.class,
        Test_Reporter.class
})
public class AllTests {

}

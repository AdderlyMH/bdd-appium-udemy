package com.qa.runners;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import io.cucumber.testng.*;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

/**
 * An example of using TestNG when the test class does not inherit from
 * AbstractTestNGCucumberTests but still executes each scenario as a separate
 * TestNG test.
 */
@CucumberOptions(plugin = {"pretty", "html:target/RedmiNote11/cucumber.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//        tags = "@Login",
        snippets = CAMELCASE,
        monochrome = true,
        features = "src/test/resources",
        glue = "com/qa")
public class MyRedmiNote11TestNGRunnerTest extends TestBaseRunner {

}
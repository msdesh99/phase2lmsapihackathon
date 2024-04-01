package phase2lmsapihackathon.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features"},
		glue= {"phase2lmsapihackathon.steps"},
		plugin= {"pretty", "html:target/cucumberreport.html"},
		//strict=true,   //only juint?
		dryRun=false 
)

public class Runner extends AbstractTestNGCucumberTests { 

}

package phase2lmsapihackathon.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		tags=("@Bgetusermodule"),
		features = {"src/test/resources/features"},
		glue= {"phase2lmsapihackathon.steps"},
		plugin= {"pretty", "summary",
				 "timeline: target/cucumber/endtoend",
				 "html:target/html/endtoend-output.html",
				 "json:target/cucumber/endtoend.json",
				 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome=false
		//strict=true,   //only juint?
		//dryRun=false 
)

public class GetUserRunner extends AbstractTestNGCucumberTests { 

}

package phase2lmsapihackathon.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		tags=("@AusermoduleCRUD"), //@Bgetuserbyuserid1 or @putuserroleprogrambatchstatus"), //@putuserlogin"), //@putuserroleprogrambatchstatus"),//@putuserlogin"), //@putuupdateuserrolestatus2"), //@putupdateuserroleid"), //@putuserbyuseridpositive"), //@AusermoduleCRUD"), //@AcreateUser or  or @Cputuserrequest"),//  or or  or @Ddeleteuserbyuserid"), //@putuserroleprogrambatchstatus"), //@createuserpositive"), //@getuserbyuserid1 or @putuserlogin"), //@putuserrequest"), //@putuupdateuserrolestatus2"), //@putupdateuserroleid"), // or @getuserbyuserid1"), // @getusersbyid"), //@putuserbyuseridpositive"), //@putupdateuserroleid"), //@putuserbyuseridpositive"), //@putupdateuserroleid"), //@putuserbyuseridpositive"), //@createuserpositive") @getusermodule"), //@getallusers or @getusersbyid" or @createuserpositive or @createUsers or  @getAllPrograms),
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

public class EndtoEndRunner extends AbstractTestNGCucumberTests { 

}

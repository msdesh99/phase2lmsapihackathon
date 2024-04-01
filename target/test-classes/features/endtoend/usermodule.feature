@AusermoduleCRUD
Feature: End to End Testing using RestAssured

@AcreateuserPositive
Scenario Outline: Positive scenario TestCases to create users with Role in LMS
Given User creates "POST" Request for UserAPI module "AuthStatus"
When user sends request Body with details for "positive" scenario from excel for "<RowNo>".
#Then User receives 201 Create Status with response body.
Then user receives Status with response body.
Examples:
| RowNo |
| 1 |

@Bgetuserbyuserid1
Scenario: Checking Endpoints [GetUserByUserId]
Given User creates "GET" Request for UserAPI module "AuthStatus"
When User sends HTTPS Request "GetUserByUserId" with userId 
Then User receives "200" OK Status with response body 

 @putuserbyuseridpositive
  Scenario: user updating using positive scenario 
  Given User creates "PUT" Request for UserAPI module "AuthStatus"
  When User sends "Positive" scenarios "PUTUserByUserId" for user reads data from excel "UserPositivePutScenario"
  #Then validate response
	
	@putupdateuserroleid
	Scenario: update user role id
  Given User creates "PUT" Request for UserAPI module "AuthStatus"
  #When User sends "Positive" scenarios "PUTUpdateUserRoleId" for RoleId reads data from json "UserPositivePutScenario"
	When User sends "updateroleid" scenarios "PUTUpdateUserRoleId" for RoleId reads data from json "PutUpdateUserRoleByUserId"
	
	#Then validate response
	
	@putuupdateuserrolestatus2
	Scenario: update user by role status
	Given User creates "PUT" Request for UserAPI module "AuthStatus"
	When User sends "updateroleStatus" scenarios "PUTUpdateUserRoleStatus" for RoleStatus reads data from excel "PutUpdateUserRoleByUserId"
	#Then validate Status response
	
	@putuserlogin  # BUG
	Scenario: Update User Login by userid
	Given User creates "PUT" Request for UserAPI module "AuthStatus"
	When User sends "updateuserlogin" scenarios "PUTUpdateLoginStatus" for loginStatus reads data from excel "PutUserLogin"
	#Then validate loginStatus response
	
	@putuserroleprogrambatchstatus
	Scenario: Update User ProgramBatch Status
	Given User creates "PUT" Request for UserAPI module "AuthStatus"
	When User sends "updateuserprogrambatch" scenarios "PUTUpdateRoleProgramBatch" for programBatchStatus reads data from excel "UserRoleProgramBatchStatus"
	#Then validate programBatchStatus response
	
	@Ddeleteuserbyuserid
Scenario: Delete User 
Given User creates "DELETE" Request for UserAPI module "AuthStatus"
When User sends HTTP Request "DeleteUserByUserId" with userId
Then User receives "200" OK Status with message
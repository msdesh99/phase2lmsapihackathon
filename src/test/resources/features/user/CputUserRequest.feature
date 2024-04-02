@Cputuserrequest
Feature: Title of your feature
  Put requests for user in user module

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
@DdeleteUser
Feature: User Module

@Ddeleteuserbyuserid
Scenario: Delete User 
Given User creates "DELETE" Request for UserAPI module "AuthStatus"
When User sends HTTP Request "DeleteUserByUserId" with userId
Then User receives "200" OK Status with message
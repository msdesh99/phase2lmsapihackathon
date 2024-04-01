@AcreateUser
Feature: Create User
#@createUsers
#Scenario: Check if user able to Create users withb Role
#Given User creates GET Request for UserAPI module "AuthStatus"
#When User sends HTTPS POST Request 
#Then User receives 201 Create Status with response body.    
 
@Acreateuserpositive
Scenario Outline: Positive scenario TestCases to create users with Role in LMS
Given User creates "POST" Request for UserAPI module "AuthStatus"
When user sends request Body with details for "positive" scenario from excel for "<RowNo>".
#Then User receives 201 Create Status with response body.
Then user receives Status with response body.
Examples:
| RowNo |
| 1 |

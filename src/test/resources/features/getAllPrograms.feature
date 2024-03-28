@programModule
Feature: Check Users

@getAllPrograms
Scenario: Check if user able to retrieve all Programs
Given User creates GET Request for UserAPI module "AuthStatus"
When User sends HTTPS Request 
Then User receives 200 OK Status with response body.     


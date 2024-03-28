@programModule
Feature: Check Users

@getAllPrograms
Scenario: Check if user able to retrieve all Programs
Given User creates GET Request for UserAPI module "AuthStatus"
When User sends HTTPS Request to get all Programs
Then User receives 200 OK Status with response body for get Programs.     


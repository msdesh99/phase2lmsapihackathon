@batchModule
Feature: Perform Batch module

  @getBatch
  Scenario Outline: Get all the available batch modules
    Given User sends a request with "<Sheetname>" and <rownumber>
    When User sends HTTPS Request to get batches with "<Sheetname>" and <rownumber>
    Then User receives response with "<Sheetname>" and <rownumber>
    Examples:
      |Sheetname|rownumber|
      |getBatch|0|
      |getBatch|1|
      |getBatch|2|


  @getBatchById
  Scenario Outline: Get all the available batch modules by using BatchId
    Given User sends a request with "<Sheetname>" and <rownumber>
    When User sends GET Request to get batch info with "<Sheetname>" and <rownumber>
    Then User receives response with "<Sheetname>" and <rownumber>
    Examples:
      |Sheetname|rownumber|
      |getBatchById|0|
      |getBatchById|1|
      |getBatchById|2|
      |getBatchById|3|
      |getBatchById|4|



  @getBatchByName
  Scenario Outline: Get all the available batch modules by using BatchName
    Given User sends a request with "<Sheetname>" and <rownumber>
    When User sends GET Request to get batch info by Name with "<Sheetname>" and <rownumber>
    Then User receives response with "<Sheetname>" and <rownumber>
    Examples:
      |Sheetname|rownumber|
      |getBatchByName|0|
      |getBatchByName|1|
      |getBatchByName|2|
      |getBatchByName|3|
      |getBatchByName|4|

@postBatch
Scenario Outline: Check if user able to create a Batch with valid endpoint and request body (non existing values)
  Given User creates POST Request "<Sheetname>" and <rownumber> for the LMS API batch endpoint with mandatory field.
  When User sends HTTPS Request and  request Body with mandatory , additional  fields
  Then User receives 201 Created Status with response body.
  Examples:
    |Sheetname|rownumber|
    |postBatch|0|
    |postBatch|1|
    |postBatch|2|
    |postBatch|3|

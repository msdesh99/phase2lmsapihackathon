@batchModule
Feature: Perform Batch module

#  @getBatch
#  Scenario Outline: Get all the available batch modules
#    Given User sends a request with "<Sheetname>" and <rownumber>
#    When User sends HTTPS Request to get batches with "<Sheetname>" and <rownumber>
#    Then User receives response with "<Sheetname>" and <rownumber>
#    Examples:
#      |Sheetname|rownumber|
#      |getBatch|0|
#      |getBatch|1|
#      |getBatch|2|
#
#
#  @getBatchById
#  Scenario Outline: Get all the available batch modules by using BatchId
#    Given User sends a request with "<Sheetname>" and <rownumber>
#    When User sends GET Request to get batch info with "<Sheetname>" and <rownumber>
#    Then User receives response with "<Sheetname>" and <rownumber>
#    Examples:
#      |Sheetname|rownumber|
#      |getBatchById|0|
#      |getBatchById|1|
#      |getBatchById|2|
#      |getBatchById|3|
#
#
#
#  @getBatchByName
#  Scenario Outline: Get all the available batch modules by using BatchName
#    Given User sends a request with "<Sheetname>" and <rownumber>
#    When User sends GET Request to get batch info by Name with "<Sheetname>" and <rownumber>
#    Then User receives response with "<Sheetname>" and <rownumber>
#    Examples:
#      |Sheetname|rownumber|
#      |getBatchByName|0|
#      |getBatchByName|1|
#      |getBatchByName|2|
#      |getBatchByName|3|
#
#  @batch
#  Scenario Outline: Check if user able to create a Batch with valid endpoint and request body (non existing values)
#    Given User creates POST Request "<Sheetname>" and <rownumber> for the LMS API batch endpoint with mandatory field.
#    When User sends HTTPS Request and  request Body with mandatory , additional  fields
#    Then User receives 201 Created Status with response body.
#    Examples:
#      |Sheetname|rownumber|
#      |postBatch|0|
#      |postBatch|1|
#      |postBatch|2|
#      |postBatch|3|

#  @getBatchByProgramId
#  Scenario Outline: Check if admin able to retrieve a batch without authorization
#  Given User creates GET Request with "<Sheetname>" and <rownumber>
#  When User sends GET Request to get batch info by programId with "<Sheetname>" and <rownumber>
#  Then User receives response with "<Sheetname>" and <rownumber>
#
#  Examples:
#    | Sheetname           | rownumber |
#    | getBatchByProgramId | 0         |
#    | getBatchByProgramId | 1         |
#    | getBatchByProgramId | 2         |
#    | getBatchByProgramId | 3         |

#    @updateBatchByBatchID
#    Scenario Outline: Check if admin able to update a Batch with valid batchID and mandatory fields in request body without authorization
#      Given User creates PUT Request "<Sheetname>" and <rownumber> for the LMS API batch endpoint with valid BatchId and Data
#      When User sends HTTPS Request for update batch with "<Sheetname>" and <rownumber>
#      Then User receives response with "<Sheetname>" and <rownumber>
#
#      Examples:
#        | Sheetname       | rownumber |
#        | updateByBatchId | 0         |
#        | updateByBatchId | 1         |
#        | updateByBatchId | 2         |
#        | updateByBatchId | 3         |
#        | updateByBatchId | 4         |
#        | updateByBatchId | 5         |
#        | updateByBatchId | 6         |
#        | updateByBatchId | 7         |

  @deleteBatchByBatchId
  Scenario Outline: Check if admin able to delete a Batch with valid Batch ID and mandatory fields
    Given User creates DELETE Request "<Sheetname>" and <rownumber> for the LMS API batch endpoint with valid BatchId and Data
    When User sends HTTPS Request for delete batch with "<Sheetname>" and <rownumber
    Then User receives response with "<Sheetname>" and <rownumber>

    Examples:
      | Sheetname            | rownumber |
      | deleteBatchByBatchId | 0         |
#      | deleteBatchByBatchId | 1         |
#      | deleteBatchByBatchId | 2         |
#      | deleteBatchByBatchId | 3         |

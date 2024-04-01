## from here it is getRequest by program id
#Scenario Outline:Check if admin able to retrieve a batch without authorization
#Admin sets Authorization to No Auth
#Given Admin creates GET Request with program id	Admin sends
#When HTTPS Request with endpoint
#Then Admin receives 401  Status with error message unauthorized.
#
#Examples:
#  |SheetName|RowNumber|
#  | batch   |1        |
#
#
#
#Scenario Outline:	Check if admin able to retrieve a batch with valid Program ID
#Admin sets Authorization to Bearer Token.
#Given	Admin creates GET Request with valid Program Id
#When	Admin sends HTTPS Request with endpoint
#Then	Admin receives 200 OK Status with response body.
#
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#Scenario Outline:	Check if admin able to retrieve a batch with invalid Program Id
#Admin sets Authorization to Bearer Token.
#Given	Admin creates GET Request with invalid Program Id
#When	Admin sends HTTPS Request with endpoint
#Then	Admin receives 404 Not Found Status with message and boolean success details
#
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#
#Scenario Outline:	Check if admin able to retrieve a batch with invalid endpoint
#Admin sets Authorization to Bearer Token.
#Given	Admin sets Authorization to Bearer Token. 	Admin sends HTTPS Request with invalid endpoint
#When	Admin creates GET Request with invalid endpoint
#Then	Admin receives 404  Status with  error message Not Found.
#
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#Scenario Outline:	Check if admin able to retrive a batch after the programID is deleted
#Admin sets Authorization to Bearer Token.
#Given	Admin creates GET Request with program id
#When	Admin sends HTTPS Request with endpoint
#Then	Admin receives 404 Not Found Status with message and boolean success details
#
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#  #PUT REQUEST  (Update Batch by batchID)
#Scenario Outline:	Check if admin able to update a Batch with valid batchID and mandatory fields in request body without authorization
#Admin sets Authorization to no auth
#Given	Admin creates PUT Request with valid BatchId and Data
#When	Admin sends HTTPS Request  with endpoint
#Then	Admin receives 401 unauthorized
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#Scenario Outline:	Check if admin able to update a Batch with valid batchID and mandatory fields in request body
#Admin sets Authorization to bearer token
#Given	Admin creates PUT Request with valid BatchId and Data
#When	Admin sends HTTPS Request  with endpoint
#Then	Admin receives 200 OK Status with updated value in response body.
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#Scenario Outline:	Check if admin able to update a Batch with invalid batchID and mandatory fields in request body
#Admin sets Authorization to bearer token
#Given	Admin creates PUT Request with invalid BatchId and valid Data
#When	Admin sends HTTPS Request  with endpoint
#Then	Admin receives 404 Not Found Status with message and boolean success details
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#Scenario Outline:	Check if admin able to update a Batch with valid batchID and missing mandatory fields request body
#Admin sets Authorization to bearer token
#Given	Admin creates PUT Request with valid batch Id and missing mandatory fields
#When	Admin sends HTTPS Request  with endpoint
#Then	Admin receives 400 Bad Request Status with message and boolean success details
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#Scenario Outline:	Check if admin able to update a batch with invalid data
#Admin sets Authorization to bearer token
#Given	Admin creates PUT Request with invalida data
#When	Admin sends HTTPS Request  with endpoint
#Then	Admin receives 400 Bad Request Status with message and boolean success details
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#Scenario Outline:	Check if admin able to update a Batch with invalid enpoint
#Admin sets Authorization to bearer token
#Given	Admin creates PUT Request with Valid batch Id
#When	Admin sends HTTPS Request  with invalid endpoint
#Then	Admin receives 404 not found
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#Scenario Outline:	Check if admin able to update a Batch with a valid batchID and deleted programID field  in the request body with other mandatory fields
#Admin sets Authorization to bearer token
#Given	Admin creates PUT Request with Valid batch Id
#When	Admin sends HTTPS Request  with endpoint
#Then	Admin receives 400 Bad Request Status with message and boolean success details
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#
#Scenario Outline:	Check if admin able to update a Batch with a deleted batchID in the endpoint
#Admin sets Authorization to bearer token
#Given	Admin creates PUT Request with deleted batch Id
#When	Admin sends HTTPS Request  with endpoint
#Then	Admin receives 200 Ok status with message
#Examples:
#|SheetName|RowNumber|
#| batch   |1        |
#

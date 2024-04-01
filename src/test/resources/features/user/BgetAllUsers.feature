@Bgetusermodule
Feature: Check Users

@getallusers
Scenario Outline: Checking EndPoints: [GetAllUsers,GetAllRoles,GetAllUsersWithRole,GetUsersByRoleIdV2]
Given User creates "GET" Request for UserAPI module "AuthStatus"
When User sends HTTPS Request "<EndPoint>" for allUsers
Then User receives "200" OK Status with response body 
Examples:
| EndPoint |
| GetAllUsers |
| GetAllRoles |
| GetAllUsersWithRole | #Bug found. Displays only roles. doesn't display users
| GetUsersByRoleIdV2 |

@Bgetuserbyuserid1
Scenario: Checking Endpoints [GetUserByUserId]
Given User creates "GET" Request for UserAPI module "AuthStatus"
When User sends HTTPS Request "GetUserByUserId" with userId 
Then User receives "200" OK Status with response body 


@getusersbyid
Scenario Outline: Checking Endpoints: [GetUsersByProgramBatchId,GetUserByProgramId,GetUsersByStatus,GetUsersByRoleId] 
Given User creates "GET" Request for UserAPI module "AuthStatus"
When User sends HTTP GET Request roleId "<EndPointUrl>" "<Id>"
Then Users receives "<Status>" with response Body
Examples:
| EndPointUrl              | Id     | Status |
| GetUsersByProgramBatchId | 8486   | 404    |
| GetUsersByProgramBatchId | 0000   | 404    |
| GetUserByProgramId       | 16592  | 404    | 
| GetUserByProgramId       | 00000  | 404    | 
| GetUsersByStatus         | all    | 200    |
| GetUsersByStatus         | R01    | 200    |
| GetUsersByStatus         | R02    | 200    |
| GetUsersByStatus         | R03    | 200    |
| GetUsersByRoleId         | R01    | 200    |
| GetUsersByRoleId         | R02    | 200		 |
| GetUsersByRoleId         | RR	    | 404    | 
package phase2lmsapihackathon.requestbody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import phase2lmsapihackathon.payload.PutUserLogin;
import phase2lmsapihackathon.payload.ResponseModel;
import phase2lmsapihackathon.payload.User;
import phase2lmsapihackathon.payload.UserLogin;
import phase2lmsapihackathon.payload.UserRoleMaps;
import phase2lmsapihackathon.payload.UserProgramBatch;
import phase2lmsapihackathon.payload.UserRoleProgramBatches;
import phase2lmsapihackathon.utils.TestBase;
import phase2lmsapihackathon.utils.XLUtility;
public class UserRequestBody extends XLUtility{

	public static Map<String, String[]> getUpdateRoleRequestBody(String sheetName, String file) throws Exception {
		    Map<String, String[]> dataMap = new HashMap<String, String[]>();
		    dataMap.put("userRoleList",new String[]{"R01"});
		    dataMap.put("userRoleList",new String[]{"R02"});
		    dataMap.put("userRoleList",new String[]{"R03"});
		    dataMap.put("userRoleList",new String[]{"RRR"});
            
			return dataMap;
			
		}
	public static Object[] getRequestBody(String sheetName, String file, int rowNumber) throws Exception {
	       //System.out.println("in userrequest to get xlshhee");
			Map<String, String> requestMap;
			User user = new User();
			ResponseModel responseModel = new ResponseModel();
		  try {	
			requestMap = XLUtility.getData(sheetName,file,rowNumber);	
			if(sheetName.contentEquals("UserPositivePutScenario"))
              user.setUserId(requestMap.get("userId"));
			user.setUserComments(requestMap.get("userComments"));
			user.setUserEduPg(requestMap.get("userEduPg"));
			user.setUserEduUg(requestMap.get("userEduUg"));
			user.setUserMiddleName(requestMap.get("userMiddleName"));
			user.setUserLinkedinUrl(requestMap.get("userLinkedinUrl"));
			user.setUserLocation(requestMap.get("userLocation"));
			user.setUserTimeZone(requestMap.get("userTimeZone"));
			user.setUserVisaStatus(requestMap.get("userVisaStatus"));
			user.setUserFirstName(requestMap.get("userFirstName"));
			user.setUserLastName(requestMap.get("userLastName"));
			System.out.println("intou pho: "+requestMap.get("userPhoneNumber"));
			user.setUserPhoneNumber(Integer.valueOf(requestMap.get("userPhoneNumber")));
			

	        responseModel.setReqStatus(requestMap.get("reqStatus"));
	        responseModel.setStatusCode(requestMap.get("statusCode"));
	        responseModel.setMessage(requestMap.get("message"));
	        Object[] obj = {user,responseModel};
			return obj;
		  }catch(Exception e) {
			  e.printStackTrace();
		  }	
		return null;
		}
	public static Object[] getUpdateRoleStatusRequestBody(String sheetName, String file, int rowNumber) throws Exception {
	       //System.out.println("in userrequest to get xlshhee");
			Map<String, String> requestMap;
			UserRoleMaps userRoleMaps = new UserRoleMaps();
			ResponseModel responseModel = new ResponseModel();
		try {	
			requestMap = XLUtility.getData(sheetName,file,rowNumber);	
	        
	        userRoleMaps.setRoleId(requestMap.get("userRoleList"));

	        userRoleMaps.setUserRoleStatus(requestMap.get("userRoleStatus"));
	        responseModel.setReqStatus(requestMap.get("reqStatus"));
	        responseModel.setStatusCode(requestMap.get("statusCode"));
	        responseModel.setMessage(requestMap.get("message"));
	        responseModel.setUserIdForUpdateUrl(requestMap.get("userId"));
	        Object[] obj = {userRoleMaps,responseModel};
			return obj;
		}catch(Exception e) {
			  e.printStackTrace();
		 }		
		return null;

		}
	public static Object[] getUserRoleProgramBatch(String sheetName,String file, int rowNumber) {
		Map<String,String> requestMap;
		UserProgramBatch userProgramBatch = new UserProgramBatch();
		UserRoleProgramBatches userRoleProgramBatches = new UserRoleProgramBatches();
		ResponseModel responseModel = new ResponseModel();
		try {
			requestMap = XLUtility.getData(sheetName, file, rowNumber);
			userProgramBatch.setProgramId(Integer.valueOf(requestMap.get("programId")));
			userProgramBatch.setRoleId(requestMap.get("roleId"));
			userProgramBatch.setUserId((String)TestBase.dataMap.get("userId"));
			userRoleProgramBatches.setBatchId(Integer.valueOf(requestMap.get("batchId")));
			userRoleProgramBatches.setUserRoleProgramBatchStatus(requestMap.get("userRoleProgramBatchStatus"));
			List<UserRoleProgramBatches> userRoleProgram = new ArrayList<UserRoleProgramBatches>();
			userRoleProgram.add(userRoleProgramBatches);
			userProgramBatch.setUserRoleProgramBatches(userRoleProgram);
			
			responseModel.setReqStatus(requestMap.get("reqStatus"));
		    responseModel.setStatusCode(requestMap.get("statusCode"));
		    responseModel.setMessage(requestMap.get("message"));
		    Object[] obj = {userProgramBatch,responseModel};
		    return obj;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static Object[] getUserLoginRequestBody(String sheetName, String file, int rowNumber) {
               Map<String,String> requestMap;
               PutUserLogin putUserLogin = new PutUserLogin();
   			   ResponseModel responseModel = new ResponseModel();
   			   System.out.println("sheetName: "+sheetName);
   			   try {  				   
				requestMap = XLUtility.getData(sheetName,file,rowNumber);
				putUserLogin.setLoginStatus(requestMap.get("loginStatus"));
				putUserLogin.setPassword(requestMap.get("password"));
				String[] roleIdsArr = requestMap.get("roleIds").split(",");

				putUserLogin.setRoleIds(roleIdsArr); 
				putUserLogin.setStatus(requestMap.get("status"));
				putUserLogin.setUserLoginEmail(requestMap.get("userLoginEmail"));
				
				responseModel.setReqStatus(requestMap.get("reqStatus"));
			    responseModel.setStatusCode(requestMap.get("statusCode"));
			    responseModel.setMessage(requestMap.get("message"));
			    Object[] obj = {putUserLogin,responseModel};
			    return obj;
			   } catch (Exception e) {
				e.printStackTrace();
			   }
			return null;	

	}
	public static Object[] GetPostRequestBody(String sheetname, String file, int rowNumber) throws Exception {
       //System.out.println("in userrequest to get xlshhee");
		Map<String, String> requestMap;
		User user = new User();
		UserLogin userLogin = new UserLogin();
		UserRoleMaps userRoleMaps = new UserRoleMaps();
		ResponseModel responseModel = new ResponseModel();
		try {
		requestMap = XLUtility.getData(sheetname,file,rowNumber);	

		user.setUserComments(requestMap.get("userComments"));
		user.setUserEduPg(requestMap.get("userEduPg"));
		user.setUserEduUg(requestMap.get("userEduUg"));
		user.setUserMiddleName(requestMap.get("userMiddleName"));
		user.setUserLinkedinUrl(requestMap.get("userLinkedinUrl"));
		user.setUserLocation(requestMap.get("userLocation"));
		user.setUserTimeZone(requestMap.get("userTimeZone"));
		user.setUserVisaStatus(requestMap.get("userVisaStatus"));
		user.setUserFirstName(requestMap.get("userFirstName"));
		user.setUserLastName(requestMap.get("userLastName"));
		user.setUserPhoneNumber(Integer.valueOf(requestMap.get("userPhoneNumber")));
		
		userLogin.setLoginStatus(requestMap.get("loginStatus"));
		userLogin.setPassword(requestMap.get("password"));
		userLogin.setStatus(requestMap.get("status"));
		userLogin.setUserLoginEmail(requestMap.get("userLoginEmail"));
		
		List<String> roleIdsList = new ArrayList<String>();
		roleIdsList.add(requestMap.get("roleIds"));
		userLogin.setRoleIds(roleIdsList);
        user.setUserLogin(userLogin);
        
        userRoleMaps.setRoleId(requestMap.get("roleId"));
        userRoleMaps.setUserRoleStatus(requestMap.get("userRoleStatus"));
        List<UserRoleMaps> roleMaps = new ArrayList<UserRoleMaps>();
        roleMaps.add(userRoleMaps);
        user.setUserRoleMaps(roleMaps);
        
        responseModel.setReqStatus(requestMap.get("reqStatus"));
        responseModel.setStatusCode(requestMap.get("statusCode"));
        responseModel.setMessage(requestMap.get("message"));
        Object[] obj = {user,responseModel};
		return obj;
		 }catch(Exception e) {
			  e.printStackTrace();
		  }	
		return null;
		
	}

}


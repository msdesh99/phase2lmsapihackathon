package phase2lmsapihackathon.payload;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {	

	  
	  @JsonSetter("userId")
      private String userId;
	  private String userComments;
	  private String userEduPg;
	  private String userEduUg;
	  private String userFirstName;
	  private String userLastName;
	  private String userLinkedinUrl;
	  private String userLocation;
	  private String userMiddleName;    
	  private int userPhoneNumber;
	  private String userTimeZone;
	  private String userVisaStatus;

	  UserLogin userLogin; 

	  public List<UserRoleMaps> userRoleMaps;



public static void main(String[] args) throws JsonProcessingException {
	User user = new User();
	user.setUserComments("comment");
	user.setUserEduPg("BE");
	user.setUserEduUg("ME");
	user.setUserFirstName("Meenakshi");
	user.setUserLastName("Lastname");
	user.setUserMiddleName("middle");
	user.setUserLinkedinUrl("www.linkedin.com/sss");
	user.setUserLocation("France");
	user.setUserPhoneNumber(1234503333);
	user.setUserTimeZone("EST");
	user.setUserVisaStatus("H4");
		
	
	UserRoleMaps roleMaps= new UserRoleMaps();
	roleMaps.setRoleId("R02");
	roleMaps.setUserRoleStatus("ADMIN_STAFF");
	
	user.userRoleMaps = new ArrayList<UserRoleMaps>();
    user.userRoleMaps.add(roleMaps);
	user.setUserRoleMaps(user.userRoleMaps);
	
	UserLogin userLogin = new UserLogin();
	userLogin.setLoginStatus("active");
	userLogin.setPassword("password");
	List<String> role = new ArrayList<String>();
    role.add("R02");
   // role.add("R0");
    userLogin.setRoleIds(role);
    userLogin.setStatus("active");
    userLogin.setUserLoginEmail("user3434@gmail.com");
    user.setUserLogin(userLogin);
    
   
    ObjectMapper mapper = new ObjectMapper();
    String nestedJsonPayload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
    System.out.println(nestedJsonPayload);
}
}
/* {
  "userComments" : "comment",
  "userEduPg" : "BE",
  "userEduUg" : "ME",
  "userFirstName" : "Meenakshi",
  "userLastName" : "Lastname",
  "userLinkedinUrl" : "www.linkedin.com/sss",
  "userLocation" : "France",
  "userMiddleName" : "middle",
  "userPhoneNumber" : 1234503333,
  "userTimeZone" : "EST",
  "userVisaStatus" : "H4",
  "userLogin" : {
    "loginStatus" : "active",
    "password" : "password",
     "userLoginEmail" : "user3434@gmail.com"
  },
  "userRoleMaps" : [ {
    "roleId" : "R02",
    "userRoleStatus" : "Active"
  } ]
}*/
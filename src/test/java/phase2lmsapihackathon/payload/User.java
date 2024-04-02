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
	    //POJO Plain old java object
	/*  @ExcelCellName("FirstName")
	  @ExcelCellName("LastName")
	  @ExcelCellName("MiddleName")
	  @ExcelCellName("PhoneNumber")
	  
	  @ExcelCellName("userLocation") 
	  @JsonSetter("userId")*/
	  
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
	 // private String userLoginEmail;
	  //@JsonIgnore
	  UserLogin userLogin; 
	 // UserRoleMaps userRoleMaps;
	  //@JsonIgnore
	  public List<UserRoleMaps> userRoleMaps;
	//  String creation_time;
	 // String last_mod_time;

/*
	  Map<String, String> userAddress  = new HashMap<String, String>();
	
	public int getUser_id() {
		return user_id;
	}
	@JsonSetter("user_id")
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_first_name() {
		return user_first_name;
	}
	@JsonSetter("user_first_name")

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}
	@JsonSetter("user_last_name")
	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	public String getUser_contact_number() {
		return user_contact_number;
	}
	
	@JsonSetter("user_contact_number")
	public void setUser_contact_number(String user_contact_number) {
		this.user_contact_number = user_contact_number;
	}
	public String getUser_email_id() {
		return user_email_id;
	}
	@JsonSetter("user_email_id")
	public void setUser_email_id(String user_email_id) {
		this.user_email_id = user_email_id;
	}
	public Map<String, String> getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(Map<String, String> userAddress) {
		this.userAddress = userAddress;
	}
	public String getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
	public String getLast_mod_time() {
		return last_mod_time;
	}
	public void setLast_mod_time(String last_mod_time) {
		this.last_mod_time = last_mod_time;
	}
*/


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
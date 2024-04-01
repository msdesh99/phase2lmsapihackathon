package phase2lmsapihackathon.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserLogin {
		    private String loginStatus;
		    private String password;
		    private List<String> roleIds;		   
		    private String status;
		    private String userLoginEmail;
		  
}

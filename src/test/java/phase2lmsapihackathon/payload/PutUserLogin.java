package phase2lmsapihackathon.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class PutUserLogin {	
	private String loginStatus;
	private String password;
	private String[]roleIds;
	private String status;
	private String userLoginEmail;
}

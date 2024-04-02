package phase2lmsapihackathon.payload;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class UserProgramBatch {
	private int programId;
	private String roleId;
	private String userId;
	private List<UserRoleProgramBatches> userRoleProgramBatches;
}
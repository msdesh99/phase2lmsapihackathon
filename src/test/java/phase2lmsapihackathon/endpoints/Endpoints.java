package phase2lmsapihackathon.endpoints;


public enum Endpoints {
	BASEURL ("https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms"),
    AddNewProgram("/saveprogram"),
    GETALLPROGRAMS("/allPrograms"),
	GETALLPORGRAMSWITHUSERS("/allProgramsWithUsers"),
	GETPROGRAMBYPROGRAMID("/programs/");
    
	//private final String url; for lombok
	public String url;
	
    private Endpoints(String url) {
    	this.url = url;
    }
    
   public String getUrl() {
	   return this.url;
   }
}

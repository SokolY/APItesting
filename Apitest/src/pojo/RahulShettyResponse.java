package pojo;

public class RahulShettyResponse {
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private CoursesResp courses; 
	private String linkedIn;
	
	public void setInstructor(String instructor) {
		this.instructor = instructor;		
	}
	public String getInstructor() {
		return instructor;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	public String getServices() {
		return services;
	}
	
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public CoursesResp getCourses() {
		return courses;
	}
	public void setCourses(CoursesResp courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
}

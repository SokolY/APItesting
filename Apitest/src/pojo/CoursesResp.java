package pojo;

import java.util.List;

public class CoursesResp {
	
	private List<webAutomation> webAtomation;
	private List<api> api;
	private List<mobile> mobile;
	
	public List<webAutomation> getWebAtomation() {
		return webAtomation;
	}
	public void setWebAtomation(List<webAutomation> webAtomation) {
		this.webAtomation = webAtomation;
	}
	public List<api> getApi() {
		return api;
	}
	public void setApi(List<api> api) {
		this.api = api;
	}
	public List<mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<mobile> mobile) {
		this.mobile = mobile;
	}
}

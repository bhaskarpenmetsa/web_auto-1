package com.falcon.model.response;

public class PageResponse {
	
	String pageName = "";
	String ResponseTime = "";
	String startTime= "";
	String endTime= "";
	String excutedtime= "";
	

	public PageResponse() {
		super();
	}

	public String getexcutedtime() {
		return excutedtime;
	}

	public void setexcutedtime(String excutedtime) {
		this.excutedtime = excutedtime;
	}
	
	
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getResponseTime() {
		return ResponseTime;
	}

	public void setResponseTime(String ResponseTime) {
		this.ResponseTime = ResponseTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}

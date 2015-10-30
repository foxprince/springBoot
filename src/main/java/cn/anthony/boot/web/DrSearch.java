package cn.anthony.boot.web;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DrSearch extends PageRequest{
	String spId;
	String channelId;
	String phoneStr;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	Date beginTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	Date endTime;
//	PageRequest pageRequest  = new PageRequest();
//	
//	public PageRequest getPageRequest() {
//		return pageRequest;
//	}
//	public void setPageRequest(PageRequest pageRequest) {
//		this.pageRequest = pageRequest;
//	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getPhoneStr() {
		return phoneStr;
	}
	public void setPhoneStr(String phoneStr) {
		this.phoneStr = phoneStr;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}

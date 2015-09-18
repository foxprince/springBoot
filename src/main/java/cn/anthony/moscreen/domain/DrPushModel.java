package cn.anthony.moscreen.domain;

public class DrPushModel {
	private String phone;
    private String msg;
    private String spno;
    private String linkid;
    private String time;
    private String status = "DELIVRD";
    private int fee;
	
    /**
	 * 
	 */
	public DrPushModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param phone
	 * @param msg
	 * @param spno
	 * @param linkid
	 * @param time
	 * @param status
	 * @param fee
	 */
	public DrPushModel(String phone, String msg, String spno, String linkid,
			String time, String status, int fee) {
		super();
		this.phone = phone;
		this.msg = msg;
		this.spno = spno;
		this.linkid = linkid;
		this.time = time;
		this.status = status;
		this.fee = fee;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSpno() {
		return spno;
	}
	public void setSpno(String spno) {
		this.spno = spno;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
    
	
}

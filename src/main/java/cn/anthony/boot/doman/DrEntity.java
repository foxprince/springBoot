package cn.anthony.boot.doman;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dr_entity")
public class DrEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "sp_id", nullable = false, length = 20)
	private String spId;
	
	@Column(name = "phone", nullable = false, length = 11)
	private String phone;
	
	@Column(name = "msg", nullable = false, length = 50)
	private String msg;
	
	@Column(name = "sp_no", nullable = false, length = 21)
	private String spNo;
	
	@Column(name = "link_id", nullable = false, length = 16)
	private String linkId;
	
	@Column(name = "status", nullable = true, length = 10)
	private String status;
	//产生状态报告的时间
	@Column(name = "dr_time", nullable = false, length = 30)
	private String drTime;
	
	@Column(name = "fee", nullable = true)
	private Integer fee;
	
	@Column(name = "channel_id", nullable = true, length = 20)
	private String channelId;
	
	//收到状态报告的时间
	@Column(name = "recv_time", nullable = false)
	private Date recvTime;
	
	//转发状态报告的时间
	@Column(name = "forward_time", nullable = true)
	private Date forwardTime;
	
	//转发状态,0:成功
	@Column(name = "forward_status", nullable = true, length = 3)
	private Integer forwardStatus;
	
	//本条记录入库时间
	@Column(name = "creation_time", nullable = false)
	private Date creationTime;

	//扣量标志 1:表示扣除
	@Column(name = "deduct_flag",nullable = true,length = 1)
	private Integer deductFlag;
	/**
	 * 
	 */
	public DrEntity() {
		super();
	}

	/**
	 * @param spId
	 * @param phone
	 * @param msg
	 * @param spNo
	 * @param linkId
	 * @param status
	 * @param drTime
	 * @param fee
	 * @param channelId
	 * @param recvTime
	 * @param forwardTime
	 * @param forwardStatus
	 * @param creationTime
	 */
	public DrEntity(String spId, String phone, String msg, String spNo,
			String linkId, String status, String drTime, Integer fee,
			String channelId, Date recvTime, Date forwardTime,
			Integer forwardStatus, Date creationTime) {
		super();
		this.spId = spId;
		this.phone = phone;
		this.msg = msg;
		this.spNo = spNo;
		this.linkId = linkId;
		this.status = status;
		this.drTime = drTime;
		this.fee = fee;
		this.channelId = channelId;
		this.recvTime = recvTime;
		this.forwardTime = forwardTime;
		this.forwardStatus = forwardStatus;
		this.creationTime = creationTime;
	}

	public DrEntity(String spId, String phone, String msg, String spNo,
			String linkId, String status, String drTime, Integer fee) {
		super();
		this.spId = spId;
		this.phone = phone;
		this.msg = msg;
		this.spNo = spNo;
		this.linkId = linkId;
		this.status = status;
		this.drTime = drTime;
		this.fee = fee;
		this.recvTime = Calendar.getInstance().getTime();
		this.creationTime = Calendar.getInstance().getTime();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
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

	public String getSpNo() {
		return spNo;
	}

	public void setSpNo(String spNo) {
		this.spNo = spNo;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDrTime() {
		return drTime;
	}

	public void setDrTime(String drTime) {
		this.drTime = drTime;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Date getRecvTime() {
		return recvTime;
	}

	public void setRecvTime(Date recvTime) {
		this.recvTime = recvTime;
	}

	public Date getForwardTime() {
		return forwardTime;
	}

	public void setForwardTime(Date forwardTime) {
		this.forwardTime = forwardTime;
	}

	public Integer getForwardStatus() {
		return forwardStatus;
	}

	public Integer getDeductFlag() {
		return deductFlag;
	}

	public void setDeductFlag(Integer deduct_flag) {
		this.deductFlag = deduct_flag;
	}

	public void setForwardStatus(Integer forwardStatus) {
		this.forwardStatus = forwardStatus;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
}

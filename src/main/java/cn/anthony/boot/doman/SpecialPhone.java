package cn.anthony.boot.doman;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "special_phone")
public class SpecialPhone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "类型不能空")
	@Column(name = "sp_id", nullable = false, length = 20)
	private String stype;
	@Column(nullable = true)
	private String phone;
	@Column
	private String clevel;
	@Column(name = "relate_id")
	private String relateId;
	@Column(name = "relate_object")
	private String relateObject;
	@Column
	private Integer adminId;
	@Column
	private Timestamp ctime;

	public SpecialPhone() {
		this.ctime = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getClevel() {
		return clevel;
	}

	public void setClevel(String clevel) {
		this.clevel = clevel;
	}

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}

	public String getRelateObject() {
		return relateObject;
	}

	public void setRelateObject(String relateObject) {
		this.relateObject = relateObject;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

}

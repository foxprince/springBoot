package cn.anthony.boot.doman;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "special_phone")
public class SpecialPhone extends GenericEntity {
    private static final long serialVersionUID = -8034209740718906977L;
    @NotEmpty(message = "类型不能空")
    @Column(nullable = false, length = 20)
    private String stype;
    @Column(nullable = true)
    private String phone;
    @Column
    private String clevel;
    @Column
    private String relateId;
    @Column
    private String relateObject;
    @Column
    private Integer adminId;

    @JsonIgnore
    transient private String stypeDesc;

    public SpecialPhone() {
	this.ctime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public SpecialPhone(String stype, String phone) {
	super();
	this.stype = stype;
	this.phone = phone;
	this.ctime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public String getStypeDesc() {
	if (stype.equalsIgnoreCase("B"))
	    return "黑名单";
	else if (stype.equalsIgnoreCase("W"))
	    return "白名单";
	else
	    return stype;
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

}

package cn.anthony.boot.doman;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BusiCode")
public class BusiCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "fee", length = 4)
    private Integer fee;

    @Column(name = "ctime", nullable = false)
    private Timestamp ctime;

    private boolean active;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "busi_id")
    private Busi busi;
    transient private Boolean create = false;
    transient private String action;
    transient private String actionDesc;
    transient private String activeDesc;

    public String getActiveDesc() {
	if (active)
	    return "开";
	else
	    return "关";
    }

    public String getAction() {
	if (isCreate())
	    return "add";
	else
	    return "edit";
    }

    public String getActionDesc() {
	if (isCreate())
	    return "添加";
	else
	    return "修改";
    }

    public boolean isCreate() {
	if (id == null)
	    create = true;
	return create;
    }

    public Busi getBusi() {
	return busi;
    }

    public void setBusi(Busi busi) {
	this.busi = busi;
    }

    public BusiCode() {
	super();
	this.ctime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public Integer getFee() {
	return fee;
    }

    public void setFee(Integer fee) {
	this.fee = fee;
    }

    public Timestamp getCtime() {
	return ctime;
    }

    public void setCtime(Timestamp ctime) {
	this.ctime = ctime;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

}

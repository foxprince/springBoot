package cn.anthony.boot.doman;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "busi")
public class Busi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "ctime", nullable = false)
    private Timestamp ctime;

    private boolean active = true;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "busi")
    transient private List<BusiCode> codeList;
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

    public void setCreate(boolean create) {
	this.create = create;
    }

    public List<BusiCode> getCodeList() {
	return codeList;
    }

    public void setCodeList(List<BusiCode> codeList) {
	this.codeList = codeList;
    }

    public Busi(String title, String description) {
	super();
	this.name = title;
	this.description = description;
	this.ctime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public Busi() {
	super();
	this.ctime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
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

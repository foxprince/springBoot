package cn.anthony.boot.doman;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "ctime", nullable = false)
    protected Timestamp ctime;

    transient protected Boolean create = false;
    transient protected String action;
    transient protected String actionDesc;

    public GenericEntity() {
	this.ctime = new Timestamp(Calendar.getInstance().getTimeInMillis());
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

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Timestamp getCtime() {
	return ctime;
    }

    public void setCtime(Timestamp ctime) {
	this.ctime = ctime;
    }
}

package cn.anthony.boot.doman;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BusiCode")
public class BusiCode extends GenericEntity {

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "fee", length = 4)
    private Integer fee;

    private boolean active = true;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "busi_id")
    private Busi busi;
    transient private String activeDesc;

    public String getActiveDesc() {
	if (active)
	    return "开";
	else
	    return "关";
    }

    public Busi getBusi() {
	return busi;
    }

    public void setBusi(Busi busi) {
	this.busi = busi;
    }

    public BusiCode() {
	super();
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

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

}

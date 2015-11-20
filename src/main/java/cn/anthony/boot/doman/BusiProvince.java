package cn.anthony.boot.doman;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "busi_province")
public class BusiProvince extends GenericEntity {
    @NotNull
    private Long busiId;
    @NotNull
    private String province;

    public BusiProvince() {
	super();
    }

    public BusiProvince(Long busiId, String province) {
	super();
	this.busiId = busiId;
	this.province = province;
    }

    public String getProvince() {
	return province;
    }

    public void setProvince(String province) {
	this.province = province;
    }

    public Long getBusiId() {
	return busiId;
    }

    public void setBusiId(Long busiId) {
	this.busiId = busiId;
    }

}
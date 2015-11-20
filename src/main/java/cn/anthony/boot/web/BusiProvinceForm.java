package cn.anthony.boot.web;

import java.util.List;

public class BusiProvinceForm {
    Long busiId;
    List<String> provinceList;

    public BusiProvinceForm() {
	super();
    }

    public BusiProvinceForm(Long busiId, List<String> provinceList) {
	super();
	this.busiId = busiId;
	this.provinceList = provinceList;
    }

    public Long getBusiId() {
	return busiId;
    }

    public void setBusiId(Long busiId) {
	this.busiId = busiId;
    }

    public List<String> getProvinceList() {
	return provinceList;
    }

    public void setProvinceList(List<String> provinceList) {
	this.provinceList = provinceList;
    }

}

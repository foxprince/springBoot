package cn.anthony.boot.web;

import java.util.List;

public class ChannelCodeProvinceForm {
    Long channelId;
    Long codeId;
    List<String> provinceList;

    public ChannelCodeProvinceForm() {
	super();
    }

    public ChannelCodeProvinceForm(Long channelId) {
	super();
	this.channelId = channelId;
    }

    public ChannelCodeProvinceForm(Long channelId, Long codeId, List<String> provinceList) {
	super();
	this.channelId = channelId;
	this.codeId = codeId;
	this.provinceList = provinceList;
    }

    public Long getCodeId() {
	return codeId;
    }

    public void setCodeId(Long codeId) {
	this.codeId = codeId;
    }

    public Long getChannelId() {
	return channelId;
    }

    public void setChannelId(Long channelId) {
	this.channelId = channelId;
    }

    public List<String> getProvinceList() {
	return provinceList;
    }

    public void setProvinceList(List<String> provinceList) {
	this.provinceList = provinceList;
    }

}

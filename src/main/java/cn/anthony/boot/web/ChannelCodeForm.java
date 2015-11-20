package cn.anthony.boot.web;

import java.util.List;

public class ChannelCodeForm {
    Long channelId;
    List<Long> codeList;

    public ChannelCodeForm() {
	super();
    }

    public ChannelCodeForm(Long channelId, List<Long> codeList) {
	super();
	this.channelId = channelId;
	this.codeList = codeList;
    }

    public Long getChannelId() {
	return channelId;
    }

    public void setChannelId(Long channelId) {
	this.channelId = channelId;
    }

    public List<Long> getCodeList() {
	return codeList;
    }

    public void setCodeList(List<Long> codeList) {
	this.codeList = codeList;
    }

}

package cn.anthony.boot.doman;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "channel_code_province")
public class ChannelCodeProvince extends GenericEntity {
    private static final long serialVersionUID = 2548163240631996793L;
    @NotNull
    private Long channelId;
    @NotNull
    private Long codeId;
    @NotNull
    private String province;

    public ChannelCodeProvince(Long channelId, Long codeId, String province) {
	super();
	this.channelId = channelId;
	this.codeId = codeId;
	this.province = province;
    }

    public ChannelCodeProvince() {
	super();
    }

    public Long getChannelId() {
	return channelId;
    }

    public String getProvince() {
	return province;
    }

    public void setProvince(String province) {
	this.province = province;
    }

    public void setChannelId(Long channelId) {
	this.channelId = channelId;
    }

    public Long getCodeId() {
	return codeId;
    }

    public void setCodeId(Long codeId) {
	this.codeId = codeId;
    }

}
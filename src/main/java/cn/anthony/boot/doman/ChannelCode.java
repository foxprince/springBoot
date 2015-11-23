package cn.anthony.boot.doman;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "channel_code")
public class ChannelCode extends GenericEntity {
    private static final long serialVersionUID = 3035420689291223055L;
    @NotNull
    private Long channelId;
    @NotNull
    private Long codeId;

    public ChannelCode(Long channelId, Long codeId) {
	super();
	this.channelId = channelId;
	this.codeId = codeId;
    }

    public ChannelCode() {
	super();
    }

    public Long getChannelId() {
	return channelId;
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
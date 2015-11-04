package cn.anthony.boot.doman;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "code_config")
public class CodeConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "sp名称不能空")
    @Column(name = "sp_id", nullable = false, length = 20)
    private String spId;

    @NotEmpty
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @NotEmpty
    @Column(name = "sp_no", nullable = false, length = 21)
    private String spNo;

    // 匹配类型 ，0：双模糊，1：代码精确，2：指令精确，3：双精确
    @NotEmpty
    @Column(name = "match_type", nullable = false, length = 1)
    private String matchType;

    @Column(name = "fee", nullable = true)
    private Integer fee;

    @Column(name = "channel_id", nullable = true, length = 20)
    private String channelId;

    // 本条记录入库时间
    @Column(name = "creation_time", nullable = false)
    private Timestamp creationTime;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getSpId() {
	return spId;
    }

    public void setSpId(String spId) {
	this.spId = spId;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getSpNo() {
	return spNo;
    }

    public void setSpNo(String spNo) {
	this.spNo = spNo;
    }

    public String getMatchType() {
	return matchType;
    }

    public void setMatchType(String matchType) {
	this.matchType = matchType;
    }

    public Integer getFee() {
	return fee;
    }

    public void setFee(Integer fee) {
	this.fee = fee;
    }

    public Timestamp getCreationTime() {
	return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
	this.creationTime = creationTime;
    }

    public String getChannelId() {
	return channelId;
    }

    public void setChannelId(String channelId) {
	this.channelId = channelId;
    }
}

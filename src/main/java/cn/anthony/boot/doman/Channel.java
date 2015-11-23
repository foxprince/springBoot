package cn.anthony.boot.doman;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "channel")
public class Channel extends GenericEntity {
    private static final long serialVersionUID = 5302438006705951524L;

    @NotNull
    @NotEmpty
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    private boolean active = true;
    private String corp;
    private String linkman;
    private String phone;
    private String qq;
    @Email
    private String email;
    @JsonIgnore
    transient private String activeDesc;

    public String getActiveDesc() {
	if (active)
	    return "开";
	else
	    return "关";
    }

    public String getCorp() {
	return corp;
    }

    public void setCorp(String corp) {
	this.corp = corp;
    }

    public String getLinkman() {
	return linkman;
    }

    public void setLinkman(String linkman) {
	this.linkman = linkman;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getQq() {
	return qq;
    }

    public void setQq(String qq) {
	this.qq = qq;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Channel() {
	super();
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

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

}

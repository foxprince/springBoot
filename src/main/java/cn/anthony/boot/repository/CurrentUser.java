package cn.anthony.boot.repository;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import cn.anthony.boot.doman.Role;

public class CurrentUser extends User {

    private static final long serialVersionUID = 5989509015507466633L;
    private cn.anthony.boot.doman.User user;

    public CurrentUser(cn.anthony.boot.doman.User user) {
	super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
	this.user = user;
    }

    public cn.anthony.boot.doman.User getUser() {
	return user;
    }

    public Long getId() {
	return user.getId();
    }

    public Role getRole() {
	return user.getRole();
    }

}

package com.wh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tUSER")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue
    private Long userId;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ROLES_ID")
    private UserRoles userRoles;

    public User() {
    }

    public Long getUserId() {

	return userId;
    }

    public void setUserId(Long userId) {

	this.userId = userId;
    }

    public String getLogin() {

	return login;
    }

    public void setLogin(String login) {

	this.login = login;
    }

    public String getPassword() {

	return password;
    }

    public void setPassword(String password) {

	this.password = password;
    }

    public String getEmail() {

	return email;
    }

    public void setEmail(String email) {

	this.email = email;
    }

    public UserRoles getUserRoles() {
	return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
	this.userRoles = userRoles;
    }
}

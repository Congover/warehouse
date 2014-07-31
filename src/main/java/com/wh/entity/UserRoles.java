package com.wh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tUSER_ROLES")
public class UserRoles implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_ROLES_ID")
	@GeneratedValue
	private Long userRolesId;
	
	@OneToMany(mappedBy = "userRoles", fetch = FetchType.LAZY)
	private List<User> userList;
	
	@Column(name = "AUTHORITY")
	private String authority;
	
	public UserRoles(){		
	}

	public Long getUserRolesId() {
		return userRolesId;
	}

	public void setUserRolesId(Long userRolesId) {
		this.userRolesId = userRolesId;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
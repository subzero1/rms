package com.rms.dataObjects.form;

import java.io.Serializable;

public class Td52_aqys implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4897644566544419262L;

	private Long id;

	private String ipa;

	private String port_num;
	
	private String login_protocol;

	private String username;

	private String userpwd;

	private String sup_username;

	private String sup_userpwd;

	private String device_type;

	private Long project_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIpa() {
		return ipa;
	}

	public void setIpa(String ipa) {
		this.ipa = ipa;
	}

	
	public String getLogin_protocol() {
		return login_protocol;
	}

	public void setLogin_protocol(String login_protocol) {
		this.login_protocol = login_protocol;
	}

	public String getPort_num() {
		return port_num;
	}

	public void setPort_num(String port_num) {
		this.port_num = port_num;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}


	public String getSup_username() {
		return sup_username;
	}

	public void setSup_username(String sup_username) {
		this.sup_username = sup_username;
	}

	public String getSup_userpwd() {
		return sup_userpwd;
	}

	public void setSup_userpwd(String sup_userpwd) {
		this.sup_userpwd = sup_userpwd;
	}


	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

}

package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * 操作日志表
 * @class name:com.netsky.base.dataObjects.Tz03_login_log
 * @hibernate.class table="Tz03_login_log"
 * @author Administrator Mar 9, 2010
 * 
 */

public class Tz03_login_log implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5342684598971242767L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 登录人员工号
	 */
	private String login_id;
	
	/**
	 * 登录人员姓名
	 */
	private String user_name;
	
	/**
	 * 登录人员IP
	 */
	private String login_ip;
	
	/**
	 * 登录时间
	 */
	private Date login_date ;
	
	/**
	 * 登录时间
	 */
	private Date logout_date ;	
	/**
	 *  当前系统信息
	 */
	private String systeminfo;
	/**
	 * @hibernate.property column="systeminfo"
	 * @return systeminfo
	 */
	public String getSysteminfo() {
		return systeminfo;
	}
	/**
	 * @param systeminfo
	 */
	public void setSysteminfo(String systeminfo) {
		this.systeminfo = systeminfo;
	}

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tz03_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="login_id"
	 * @return Returns the login_id.
	 */
	public String getLogin_id() {
		return login_id;
	}

	/**
	 * @param login_id The login_id to set.
	 */
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	/**
	 * @hibernate.property column="user_name"
	 * @return Returns the user_name.
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name The user_name to set.
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @hibernate.property column="login_ip"
	 * @return Returns the login_ip.
	 */
	public String getLogin_ip() {
		return login_ip;
	}

	/**
	 * @param login_ip The login_ip to set.
	 */
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	/**
	 * @hibernate.property column="login_date"
	 * @return Returns the login_date.
	 */
	public Date getLogin_date() {
		return login_date;
	}

	/**
	 * @param login_date The login_date to set.
	 */
	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}
	
	/**
	 * @hibernate.property column="logout_date"
	 * @return Returns the login_date.
	 */
	public Date getLogout_date() {
		return logout_date;
	}

	/**
	 * @param login_date The login_date to set.
	 */
	public void setLogout_date(Date logout_date) {
		this.logout_date = logout_date;
	}
}

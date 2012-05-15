package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.*;
/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Te03_online"
 */

public class Te03_online implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4435453911362399431L;

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private Long up_id;
	
	/**
	 * 工号
	 */
	private String login_id;
	
	/**
	 * 用户名
	 */
	private String aq_name;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * TA04.ID,在线提问 15、权限申请 17、系统公告 601
	 */
	private Long role_id;
	
	/**
	 * 发布日期
	 */
	private Date aq_date;
	
	/**
	 * 发布人ip地址
	 */
	private String aq_ip;
	
	/**
	 * 状态：未处理、已处理
	 */
	private String status;
	
	/**
	 * 阅读次数
	 */
	private Long ydcs;
	
	/**
	 * 提出人id
	 */
	private Long aq_id;
	
	/**
	 * 提出人电话
	 */
	private String aq_tel;
	
	/**
	 * 置顶标记位
	 */
	private Long flag;
	
	/**
	 * @hibernate.property column="flag"
	 * @return the flag
	 */
	public Long getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Long flag) {
		this.flag = flag;
	}

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Te03_NUM"
	 * @return Returns the id.
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="up_id"
	 * @return Returns the up_id.
	 */
	public Long getUp_id() {
		return up_id;
	}

	/**
	 * @param up_id The up_id to set.
	 */
	public void setUp_id(Long up_id) {
		this.up_id = up_id;
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
	 * @hibernate.property column="aq_name"
	 * @return Returns the aq_name.
	 */
	public String getAq_name() {
		return aq_name;
	}

	/**
	 * @param aq_name The aq_name to set.
	 */
	public void setAq_name(String aq_name) {
		this.aq_name = aq_name;
	}

	/**
	 * @hibernate.property column="title"
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @hibernate.property column="content"
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @hibernate.property column="role_id"
	 * @return Returns the role_id.
	 */
	public Long getRole_id() {
		return role_id;
	}

	/**
	 * @param role_id The role_id to set.
	 */
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	/**
	 * @hibernate.property column="aq_date"
	 * @return Returns the aq_date.
	 */
	public Date getAq_date() {
		return aq_date;
	}

	/**
	 * @param aq_date The aq_date to set.
	 */
	public void setAq_date(Date aq_date) {
		this.aq_date = aq_date;
	}

	/**
	 * @hibernate.property column="aq_ip"
	 * @return Returns the aq_ip.
	 */
	public String getAq_ip() {
		return aq_ip;
	}

	/**
	 * @param aq_ip The aq_ip to set.
	 */
	public void setAq_ip(String aq_ip) {
		this.aq_ip = aq_ip;
	}

	/**
	 * @hibernate.property column="status"
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status The status to set.
	 */

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @hibernate.property column="ydcs"
	 * @return Returns the ydcs.
	 */
	public Long getYdcs() {
		return ydcs;
	}

	/**
	 * @param ydcs The ydcs to set.
	 */
	public void setYdcs(Long ydcs) {
		this.ydcs = ydcs;
	}
	
	/**
	 * @hibernate.property column="aq_id"
	 * @return Returns the aq_id.
	 */
	public Long getAq_id() {
		return aq_id;
	}

	/**
	 * @param aq_id The aq_id to set.
	 */
	public void setAq_id(Long aq_id) {
		this.aq_id = aq_id;
	}
	
	/**
	 * @hibernate.property column="aq_tel"
	 * @return Returns the aq_tel.
	 */
	public String getAq_tel() {
		return aq_tel;
	}

	/**
	 * @param aq_tel The aq_tel to set.
	 */
	public void setAq_tel(String aq_tel) {
		this.aq_tel = aq_tel;
	}
}
	
	
package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志表
 * 
 * @author Chiang
 * @hibernate.class table="Tz02_oper_log"
 */
public class Tz02_oper_log implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6033441917721262348L;
	/**
	 * ID NUMBER(10),
	 * 
	 */
	private Long id;
	/**
	 * 工号
	 */
	private String login_id;
	/**
	 * OPTIME DATE,
	 * 
	 */
	private Date optime;
	/**
	 * 描述
	 */
	private String opdesc;
	/**
	 * OPUSER VARCHAR2(20),
	 * 
	 */
	private String opuser;
	/**
	 * OPTYPE VARCHAR2(10),
	 * 
	 */
	private String optype;
	/**
	 * OPTABLE VARCHAR2(100),
	 * 
	 */
	private String optable;
	/**
	 * OLD_DATA VARCHAR2(4000),
	 * 
	 */
	private String old_data;
	/**
	 * NEW_DATA VARCHAR2(4000)
	 * 
	 */
	private String new_data;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tz02_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="optime"
	 * @return the optime
	 */
	public Date getOptime() {
		return optime;
	}

	/**
	 * @param optime
	 *            the optime to set
	 */
	public void setOptime(Date optime) {
		this.optime = optime;
	}

	/**
	 * @hibernate.property column="opuser"
	 * @return the opuser
	 */
	public String getOpuser() {
		return opuser;
	}

	/**
	 * @param opuser
	 *            the opuser to set
	 */
	public void setOpuser(String opuser) {
		this.opuser = opuser;
	}

	/**
	 * @hibernate.property column="optype"
	 * @return the optype
	 */
	public String getOptype() {
		return optype;
	}

	/**
	 * @param optype
	 *            the optype to set
	 */
	public void setOptype(String optype) {
		this.optype = optype;
	}

	/**
	 * @hibernate.property column="optable"
	 * @return the optable
	 */
	public String getOptable() {
		return optable;
	}

	/**
	 * @param optable
	 *            the optable to set
	 */
	public void setOptable(String optable) {
		this.optable = optable;
	}

	/**
	 * @hibernate.property column="old_data"
	 * @return the old_data
	 */
	public String getOld_data() {
		return old_data;
	}

	/**
	 * @param old_data
	 *            the old_data to set
	 */
	public void setOld_data(String old_data) {
		this.old_data = old_data;
	}

	/**
	 * @hibernate.property column="new_data"
	 * @return the new_data
	 */
	public String getNew_data() {
		return new_data;
	}

	/**
	 * @param new_data
	 *            the new_data to set
	 */
	public void setNew_data(String new_data) {
		this.new_data = new_data;
	}
	/**
	 * @hibernate.property column="login_id"
	 * @return login_id
	 */
	public String getLogin_id() {
		return login_id;
	}
	/**
	 * @param login_id
	 */
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	/**
	 * @hibernate.property column="opdesc"
	 * @return opdesc
	 */
	public String getOpdesc() {
		return opdesc;
	}
	/**
	 * @param opdesc
	 */
	public void setOpdesc(String opdesc) {
		this.opdesc = opdesc;
	}

}

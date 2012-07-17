package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf14_jlrj"
 */

public class Tf14_jlrj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 437237503692780672L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf14_num"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 工程标识
	 */
	private Long project_id;

	/**
	 * @hibernate.property column="project_id"
	 * @return Returns the project_id.
	 */
	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * 用户标识
	 */
	private Long user_id;

	/**
	 * @hibernate.property column="user_id"
	 * @return Returns the user_id.
	 */
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * 记录日期
	 */
	private Date create_date;

	/**
	 * @hibernate.property column="create_date"
	 * @return Returns the create_date.
	 */
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	private String gczt;
	private String xcms;

	/**
	 * @hibernate.property column="gczt"
	 * @return Returns the gczt.
	 */
	public String getGczt() {
		return gczt;
	}

	/**
	 * @param gczt
	 *            The gczt to set.
	 */
	public void setGczt(String gczt) {
		this.gczt = gczt;
	}

	/**
	 * @hibernate.property column="xcms"
	 * @return Returns the xcms.
	 */
	public String getXcms() {
		return xcms;
	}

	/**
	 * @param xcms
	 *            The xcms to set.
	 */
	public void setXcms(String xcms) {
		this.xcms = xcms;
	}

}

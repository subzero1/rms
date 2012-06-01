package com.rms.dataObjects.wxdw;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="tf04_wxdw_user"
 */
public class Tf04_wxdw_user implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6958067704854242044L;

	/**
	 * 标识
	 */
	private Long id;
	private Long user_id;
	private Long wxdw_id;
	private String area;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TF04_NUM"
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
	 * @hibernate.property column="user_id"
	 * @return Returns the user_id.
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            The user_id to set.
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @hibernate.property column="wxdw_id"
	 * @return Returns the wxdw_id.
	 */
	public Long getWxdw_id() {
		return wxdw_id;
	}

	/**
	 * @param wxdw_id
	 *            The wxdw_id to set.
	 */
	public void setWxdw_id(Long wxdw_id) {
		this.wxdw_id = wxdw_id;
	}

	/**
	 * @hibernate.property column="area"
	 * @return Returns the area.
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            The area to set.
	 */
	public void setArea(String area) {
		this.area = area;
	}

}

package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf04_wxdw_user"
 */

public class Tf04_wxdw_user implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 977528224991644032L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf04_num"
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
 * 外协单位tf01.ID
 */
 private Long wxdw_id;

 /**
 * @hibernate.property column="wxdw_id"
 * @return Returns the wxdw_id.
 */
 public Long getWxdw_id() {
    return wxdw_id;
 }

 public void setWxdw_id(Long wxdw_id) {
    this.wxdw_id = wxdw_id;
  }

 /**
 * 用户标识ta03.id
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
 * 作业区域
 */
 private String area;

 /**
 * @hibernate.property column="area"
 * @return Returns the area.
 */
 public String getArea() {
    return area;
 }

 public void setArea(String area) {
    this.area = area;
  }


}

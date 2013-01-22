package com.rms.dataObjects.wxdw;

import java.io.Serializable;

/**
 * @author cmp_auto 2012-12-21
 * @hibernate.class table="Tf20_khxxmx"
 */

public class Tf20_khxxmx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 51766147535702304L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf20_num"
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
 * 考核标识 TC09.ID
 */
 private Long kh_id;

 /**
 * @hibernate.property column="kh_id"
 * @return Returns the kh_id.
 */
 public Long getKh_id() {
    return kh_id;
 }

 public void setKh_id(Long kh_id) {
    this.kh_id = kh_id;
  }

 /**
 * 考核项标识TC10.ID
 */
 private Long khx_id;

 /**
 * @hibernate.property column="khx_id"
 * @return Returns the khx_id.
 */
 public Long getKhx_id() {
    return khx_id;
 }

 public void setKhx_id(Long khx_id) {
    this.khx_id = khx_id;
  }

 /**
 * 外协单位标识
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
 * 打分人员标识
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
 * 最高分值
 */
 private Long fz;

 /**
 * @hibernate.property column="fz"
 * @return Returns the fz.
 */
 public Long getFz() {
    return fz;
 }

 public void setFz(Long fz) {
    this.fz = fz;
  }

 /**
 * 备注
 */
 private String bz;

 /**
 * @hibernate.property column="bz"
 * @return Returns the bz.
 */
 public String getBz() {
    return bz;
 }

 public void setBz(String bz) {
    this.bz = bz;
  }


}

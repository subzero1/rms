package com.rms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-12-21
 * @hibernate.class table="Tc11_khpzmx"
 */

public class Tc11_khpzmx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 567689105619562176L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc11_num"
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
 * 考核项
 */
 private String khx;

 /**
 * @hibernate.property column="khx"
 * @return Returns the khx.
 */
 public String getKhx() {
    return khx;
 }

 public void setKhx(String khx) {
    this.khx = khx;
  }

 /**
 * 评估内容
 */
 private String pgnr;

 /**
 * @hibernate.property column="pgnr"
 * @return Returns the pgnr.
 */
 public String getPgnr() {
    return pgnr;
 }

 public void setPgnr(String pgnr) {
    this.pgnr = pgnr;
  }

 /**
 * 最高分值
 */
 private Long zgfz;

 /**
 * @hibernate.property column="zgfz"
 * @return Returns the zgfz.
 */
 public Long getZgfz() {
    return zgfz;
 }

 public void setZgfz(Long zgfz) {
    this.zgfz = zgfz;
  }

 /**
 * 评估办法
 */
 private String pgbf;

 /**
 * @hibernate.property column="pgbf"
 * @return Returns the pgbf.
 */
 public String getPgbf() {
    return pgbf;
 }

 public void setPgbf(String pgbf) {
    this.pgbf = pgbf;
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

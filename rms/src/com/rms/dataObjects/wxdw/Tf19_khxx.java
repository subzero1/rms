package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-12-21
 * @hibernate.class table="Tf19_khxx"
 */

public class Tf19_khxx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 318091499185916032L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf19_num"
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
 * 考核名称
 */
 private String khmc;

 /**
 * @hibernate.property column="khmc"
 * @return Returns the khmc.
 */
 public String getKhmc() {
    return khmc;
 }

 public void setKhmc(String khmc) {
    this.khmc = khmc;
  }

 /**
 * 考核开始时间
 */
 private Date khkssj;

 /**
 * @hibernate.property column="khkssj"
 * @return Returns the khkssj.
 */
 public Date getKhkssj() {
    return khkssj;
 }

 public void setKhkssj(Date khkssj) {
    this.khkssj = khkssj;
  }

 /**
 * 考核结束时间
 */
 private Date khjssj;

 /**
 * @hibernate.property column="khjssj"
 * @return Returns the khjssj.
 */
 public Date getKhjssj() {
    return khjssj;
 }

 public void setKhjssj(Date khjssj) {
    this.khjssj = khjssj;
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

 /**
 * TC09.ID
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


}

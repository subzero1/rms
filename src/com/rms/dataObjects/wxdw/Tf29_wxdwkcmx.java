package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf29_wxdwkcmx"
 */

public class Tf29_wxdwkcmx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 357505181655943808L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf29_num"
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
 * 材料ID
 */
 private Long tf28_id;

 /**
 * @hibernate.property column="tf28_id"
 * @return Returns the tf28_id.
 */
 public Long getTf28_id() {
    return tf28_id;
 }

 public void setTf28_id(Long tf28_id) {
    this.tf28_id = tf28_id;
  }

 /**
 * 出库人
 */
 private String ckr;

 /**
 * @hibernate.property column="ckr"
 * @return Returns the ckr.
 */
 public String getCkr() {
    return ckr;
 }

 public void setCkr(String ckr) {
    this.ckr = ckr;
  }

 /**
 * 出库数量
 */
 private Long cksl;

 /**
 * @hibernate.property column="cksl"
 * @return Returns the cksl.
 */
 public Long getCksl() {
    return cksl;
 }

 public void setCksl(Long cksl) {
    this.cksl = cksl;
  }

 /**
 * 出库日期
 */
 private Date ckrq;

 /**
 * @hibernate.property column="ckrq"
 * @return Returns the ckrq.
 */
 public Date getCkrq() {
    return ckrq;
 }

 public void setCkrq(Date ckrq) {
    this.ckrq = ckrq;
  }

 /**
 * 是否调拨 0，否；1是
 */
 private String sfdb;

 /**
 * @hibernate.property column="sfdb"
 * @return Returns the sfdb.
 */
 public String getSfdb() {
    return sfdb;
 }

 public void setSfdb(String sfdb) {
    this.sfdb = sfdb;
  }

 /**
 * 相关工程ID
 */
 private Long xggc;

 /**
 * @hibernate.property column="xggc"
 * @return Returns the xggc.
 */
 public Long getXggc() {
    return xggc;
 }

 public void setXggc(Long xggc) {
    this.xggc = xggc;
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
 * 调拨库存id（光有相关工程id不能唯一）
 */
 private Long dbkc_id;

 /**
 * @hibernate.property column="dbkc_id"
 * @return Returns the dbkc_id.
 */
 public Long getDbkc_id() {
    return dbkc_id;
 }

 public void setDbkc_id(Long dbkc_id) {
    this.dbkc_id = dbkc_id;
  }


}

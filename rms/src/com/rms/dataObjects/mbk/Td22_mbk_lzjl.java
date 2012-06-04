package com.rms.dataObjects.mbk;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Td22_mbk_lzjl"
 */

public class Td22_mbk_lzjl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 267716521146912576L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td22_num"
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
 * 相关人标识
 */
 private Long xgr_id;

 /**
 * @hibernate.property column="xgr_id"
 * @return Returns the xgr_id.
 */
 public Long getXgr_id() {
    return xgr_id;
 }

 public void setXgr_id(Long xgr_id) {
    this.xgr_id = xgr_id;
  }

 /**
 * 开始时间
 */
 private Date kssj;

 /**
 * @hibernate.property column="kssj"
 * @return Returns the kssj.
 */
 public Date getKssj() {
    return kssj;
 }

 public void setKssj(Date kssj) {
    this.kssj = kssj;
  }

 /**
 * 结束时间
 */
 private Date jssj;

 /**
 * @hibernate.property column="jssj"
 * @return Returns the jssj.
 */
 public Date getJssj() {
    return jssj;
 }

 public void setJssj(Date jssj) {
    this.jssj = jssj;
  }

 /**
 * 相关人部门
 */
 private String xgr_bm;

 /**
 * @hibernate.property column="xgr_bm"
 * @return Returns the xgr_bm.
 */
 public String getXgr_bm() {
    return xgr_bm;
 }

 public void setXgr_bm(String xgr_bm) {
    this.xgr_bm = xgr_bm;
  }

 /**
 * 相关人
 */
 private String xgr;

 /**
 * @hibernate.property column="xgr"
 * @return Returns the xgr.
 */
 public String getXgr() {
    return xgr;
 }

 public void setXgr(String xgr) {
    this.xgr = xgr;
  }

 /**
 * 说明
 */
 private String sm;

 /**
 * @hibernate.property column="sm"
 * @return Returns the sm.
 */
 public String getSm() {
    return sm;
 }

 public void setSm(String sm) {
    this.sm = sm;
  }

 /**
 * 目标库标识
 */
 private Long mbk_id;

 /**
 * @hibernate.property column="mbk_id"
 * @return Returns the mbk_id.
 */
 public Long getMbk_id() {
    return mbk_id;
 }

 public void setMbk_id(Long mbk_id) {
    this.mbk_id = mbk_id;
  }


}

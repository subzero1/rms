package com.rms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tc03_gczy"
 */

public class Tc03_gczy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 224065713589862368L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc03_num"
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
 * 编号
 */
 private String bh;

 /**
 * @hibernate.property column="bh"
 * @return Returns the bh.
 */
 public String getBh() {
    return bh;
 }

 public void setBh(String bh) {
    this.bh = bh;
  }

 /**
 * 专业名称
 */
 private String zymc;

 /**
 * @hibernate.property column="zymc"
 * @return Returns the zymc.
 */
 public String getZymc() {
    return zymc;
 }

 public void setZymc(String zymc) {
    this.zymc = zymc;
  }

 /**
 * 有效年度
 */
 private Long yxnd;

 /**
 * @hibernate.property column="yxnd"
 * @return Returns the yxnd.
 */
 public Long getYxnd() {
    return yxnd;
 }

 public void setYxnd(Long yxnd) {
    this.yxnd = yxnd;
  }

 /**
 * 是否市控
 */
 private Long sfsk;

 /**
 * @hibernate.property column="sfsk"
 * @return Returns the sfsk.
 */
 public Long getSfsk() {
    return sfsk;
 }

 public void setSfsk(Long sfsk) {
    this.sfsk = sfsk;
  }


}

package com.rms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tc06_tzqk"
 */

public class Tc06_tzqk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 718705593683727488L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc06_num"
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
 * 名称
 */
 private String qkmc;

 /**
 * @hibernate.property column="qkmc"
 * @return Returns the qkmc.
 */
 public String getQkmc() {
    return qkmc;
 }

 public void setQkmc(String qkmc) {
    this.qkmc = qkmc;
  }

 /**
 * 所属年度
 */
 private Long nd;

 /**
 * @hibernate.property column="nd"
 * @return Returns the nd.
 */
 public Long getNd() {
    return nd;
 }

 public void setNd(Long nd) {
    this.nd = nd;
  }

 /**
 * 是否有效
 */
 private Long flag;

 /**
 * @hibernate.property column="flag"
 * @return Returns the flag.
 */
 public Long getFlag() {
    return flag;
 }

 public void setFlag(Long flag) {
    this.flag = flag;
  }


}

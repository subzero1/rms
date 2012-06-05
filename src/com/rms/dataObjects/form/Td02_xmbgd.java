package com.rms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-05
 * @hibernate.class table="Td02_xmbgd"
 */

public class Td02_xmbgd implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 211616372759390720L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td02_num"
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
 * 工程ID
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
 * 发文部门
 */
 private String fwbm;

 /**
 * @hibernate.property column="fwbm"
 * @return Returns the fwbm.
 */
 public String getFwbm() {
    return fwbm;
 }

 public void setFwbm(String fwbm) {
    this.fwbm = fwbm;
  }

 /**
 * 表单编号
 */
 private String bdbh;

 /**
 * @hibernate.property column="bdbh"
 * @return Returns the bdbh.
 */
 public String getBdbh() {
    return bdbh;
 }

 public void setBdbh(String bdbh) {
    this.bdbh = bdbh;
  }

 /**
 * 创建人
 */
 private String cjr;

 /**
 * @hibernate.property column="cjr"
 * @return Returns the cjr.
 */
 public String getCjr() {
    return cjr;
 }

 public void setCjr(String cjr) {
    this.cjr = cjr;
  }

 /**
 * 创建人电话
 */
 private String cjrdh;

 /**
 * @hibernate.property column="cjrdh"
 * @return Returns the cjrdh.
 */
 public String getCjrdh() {
    return cjrdh;
 }

 public void setCjrdh(String cjrdh) {
    this.cjrdh = cjrdh;
  }

 /**
 * 创建日期
 */
 private Date cjrq;

 /**
 * @hibernate.property column="cjrq"
 * @return Returns the cjrq.
 */
 public Date getCjrq() {
    return cjrq;
 }

 public void setCjrq(Date cjrq) {
    this.cjrq = cjrq;
  }

 /**
 * 变更类别
 */
 private String bglb;

 /**
 * @hibernate.property column="bglb"
 * @return Returns the bglb.
 */
 public String getBglb() {
    return bglb;
 }

 public void setBglb(String bglb) {
    this.bglb = bglb;
  }

 /**
 * 变更种类
 */
 private String bgzl;

 /**
 * @hibernate.property column="bgzl"
 * @return Returns the bgzl.
 */
 public String getBgzl() {
    return bgzl;
 }

 public void setBgzl(String bgzl) {
    this.bgzl = bgzl;
  }

 /**
 * 变更金额
 */
 private Long bgje;

 /**
 * @hibernate.property column="bgje"
 * @return Returns the bgje.
 */
 public Long getBgje() {
    return bgje;
 }

 public void setBgje(Long bgje) {
    this.bgje = bgje;
  }

 /**
 * 原设计内容
 */
 private String ysjnr;

 /**
 * @hibernate.property column="ysjnr"
 * @return Returns the ysjnr.
 */
 public String getYsjnr() {
    return ysjnr;
 }

 public void setYsjnr(String ysjnr) {
    this.ysjnr = ysjnr;
  }

 /**
 * 变更后内容
 */
 private String bghnr;

 /**
 * @hibernate.property column="bghnr"
 * @return Returns the bghnr.
 */
 public String getBghnr() {
    return bghnr;
 }

 public void setBghnr(String bghnr) {
    this.bghnr = bghnr;
  }

 /**
 * 原设计工作量
 */
 private String ysjgzl;

 /**
 * @hibernate.property column="ysjgzl"
 * @return Returns the ysjgzl.
 */
 public String getYsjgzl() {
    return ysjgzl;
 }

 public void setYsjgzl(String ysjgzl) {
    this.ysjgzl = ysjgzl;
  }

 /**
 * 变更后工作量
 */
 private String bghgzl;

 /**
 * @hibernate.property column="bghgzl"
 * @return Returns the bghgzl.
 */
 public String getBghgzl() {
    return bghgzl;
 }

 public void setBghgzl(String bghgzl) {
    this.bghgzl = bghgzl;
  }

 /**
 * 变更原因说明
 */
 private String bgyysm;

 /**
 * @hibernate.property column="bgyysm"
 * @return Returns the bgyysm.
 */
 public String getBgyysm() {
    return bgyysm;
 }

 public void setBgyysm(String bgyysm) {
    this.bgyysm = bgyysm;
  }

 /**
 * 有效
 */
 private String yxbz;

 /**
 * @hibernate.property column="yxbz"
 * @return Returns the yxbz.
 */
 public String getYxbz() {
    return yxbz;
 }

 public void setYxbz(String yxbz) {
    this.yxbz = yxbz;
  }


}

package com.rms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-12-27
 * @hibernate.class table="Td06_xqs"
 */

public class Td06_xqs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 533416064057216192L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td06_num"
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
 * 
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
 * 
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
 * 
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
 * 
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
 * 
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
 * 
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
 *  资源名称
 */
 private String xqmc;

 /**
 * @hibernate.property column="xqmc"
 * @return Returns the xqmc.
 */
 public String getXqmc() {
    return xqmc;
 }

 public void setXqmc(String xqmc) {
    this.xqmc = xqmc;
  }

 /**
 *  建设性质（基站、室分）
 */
 private String jsxz;

 /**
 * @hibernate.property column="jsxz"
 * @return Returns the jsxz.
 */
 public String getJsxz() {
    return jsxz;
 }

 public void setJsxz(String jsxz) {
    this.jsxz = jsxz;
  }

 /**
 *  坐落地点
 */
 private String zldd;

 /**
 * @hibernate.property column="zldd"
 * @return Returns the zldd.
 */
 public String getZldd() {
    return zldd;
 }

 public void setZldd(String zldd) {
    this.zldd = zldd;
  }

 /**
 *  所属地区
 */
 private String ssdq;

 /**
 * @hibernate.property column="ssdq"
 * @return Returns the ssdq.
 */
 public String getSsdq() {
    return ssdq;
 }

 public void setSsdq(String ssdq) {
    this.ssdq = ssdq;
  }

 /**
 *  经度
 */
 private String jd;

 /**
 * @hibernate.property column="jd"
 * @return Returns the jd.
 */
 public String getJd() {
    return jd;
 }

 public void setJd(String jd) {
    this.jd = jd;
  }

 /**
 *  纬度
 */
 private String wd;

 /**
 * @hibernate.property column="wd"
 * @return Returns the wd.
 */
 public String getWd() {
    return wd;
 }

 public void setWd(String wd) {
    this.wd = wd;
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
 * 幢数
 */
 private Long zs;

 /**
 * @hibernate.property column="zs"
 * @return Returns the zs.
 */
 public Long getZs() {
    return zs;
 }

 public void setZs(Long zs) {
    this.zs = zs;
  }

 /**
 * 层数
 */
 private Long cs;

 /**
 * @hibernate.property column="cs"
 * @return Returns the cs.
 */
 public Long getCs() {
    return cs;
 }

 public void setCs(Long cs) {
    this.cs = cs;
  }

 /**
 * 户数
 */
 private Long hs;

 /**
 * @hibernate.property column="hs"
 * @return Returns the hs.
 */
 public Long getHs() {
    return hs;
 }

 public void setHs(Long hs) {
    this.hs = hs;
  }

 /**
 * 覆盖属性
 */
 private String fgsx;

 /**
 * @hibernate.property column="fgsx"
 * @return Returns the fgsx.
 */
 public String getFgsx() {
    return fgsx;
 }

 public void setFgsx(String fgsx) {
    this.fgsx = fgsx;
  }


}

package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf28_wxdwkc"
 */

public class Tf28_wxdwkc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 423489083942488192L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf28_num"
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
 * 材料编码
 */
 private String clbm;

 /**
 * @hibernate.property column="clbm"
 * @return Returns the clbm.
 */
 public String getClbm() {
    return clbm;
 }

 public void setClbm(String clbm) {
    this.clbm = clbm;
  }

 /**
 * 材料名称
 */
 private String clmc;

 /**
 * @hibernate.property column="clmc"
 * @return Returns the clmc.
 */
 public String getClmc() {
    return clmc;
 }

 public void setClmc(String clmc) {
    this.clmc = clmc;
  }

 /**
 * 型号
 */
 private String xh;

 /**
 * @hibernate.property column="xh"
 * @return Returns the xh.
 */
 public String getXh() {
    return xh;
 }

 public void setXh(String xh) {
    this.xh = xh;
  }

 /**
 * 规格
 */
 private String gg;

 /**
 * @hibernate.property column="gg"
 * @return Returns the gg.
 */
 public String getGg() {
    return gg;
 }

 public void setGg(String gg) {
    this.gg = gg;
  }

 /**
 * 单位
 */
 private String dw;

 /**
 * @hibernate.property column="dw"
 * @return Returns the dw.
 */
 public String getDw() {
    return dw;
 }

 public void setDw(String dw) {
    this.dw = dw;
  }

 /**
 * 单价
 */
 private Long dj;

 /**
 * @hibernate.property column="dj"
 * @return Returns the dj.
 */
 public Long getDj() {
    return dj;
 }

 public void setDj(Long dj) {
    this.dj = dj;
  }

 /**
 * 存放地点
 */
 private String cfdd;

 /**
 * @hibernate.property column="cfdd"
 * @return Returns the cfdd.
 */
 public String getCfdd() {
    return cfdd;
 }

 public void setCfdd(String cfdd) {
    this.cfdd = cfdd;
  }

 /**
 * 类别（新料、工余料、拆旧料）
 */
 private String lb;

 /**
 * @hibernate.property column="lb"
 * @return Returns the lb.
 */
 public String getLb() {
    return lb;
 }

 public void setLb(String lb) {
    this.lb = lb;
  }

 /**
 * 领用数量
 */
 private Long lysl;

 /**
 * @hibernate.property column="lysl"
 * @return Returns the lysl.
 */
 public Long getLysl() {
    return lysl;
 }

 public void setLysl(Long lysl) {
    this.lysl = lysl;
  }

 /**
 * 已用数量
 */
 private Long yysl;

 /**
 * @hibernate.property column="yysl"
 * @return Returns the yysl.
 */
 public Long getYysl() {
    return yysl;
 }

 public void setYysl(Long yysl) {
    this.yysl = yysl;
  }

 /**
 * 是否是调拨记录标志
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
 * 施工单位ID 
 */
 private Long sgdw_id;

 /**
 * @hibernate.property column="sgdw_id"
 * @return Returns the sgdw_id.
 */
 public Long getSgdw_id() {
    return sgdw_id;
 }

 public void setSgdw_id(Long sgdw_id) {
    this.sgdw_id = sgdw_id;
  }

 /**
 * 工程名称
 */
 private String gcmc;

 /**
 * @hibernate.property column="gcmc"
 * @return Returns the gcmc.
 */
 public String getGcmc() {
    return gcmc;
 }

 public void setGcmc(String gcmc) {
    this.gcmc = gcmc;
  }

 /**
 * 工程编号
 */
 private String gcbh;

 /**
 * @hibernate.property column="gcbh"
 * @return Returns the gcbh.
 */
 public String getGcbh() {
    return gcbh;
 }

 public void setGcbh(String gcbh) {
    this.gcbh = gcbh;
  }

 /**
 * 入库时间
 */
 private Date rksj;

 /**
 * @hibernate.property column="rksj"
 * @return Returns the rksj.
 */
 public Date getRksj() {
    return rksj;
 }

 public void setRksj(Date rksj) {
    this.rksj = rksj;
  }


}

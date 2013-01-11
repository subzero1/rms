package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf07_kcb"
 */

public class Tf07_kcb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 432481037250412864L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf07_num"
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
 * 库存数量
 */
 private Long kcsl;

 /**
 * @hibernate.property column="kcsl"
 * @return Returns the kcsl.
 */
 public Long getKcsl() {
    return kcsl;
 }

 public void setKcsl(Long kcsl) {
    this.kcsl = kcsl;
  }

 /**
 * 施工单位标识
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
 * 工程标识
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
 * 材料类型
 */
 private String cllx;

 /**
 * @hibernate.property column="cllx"
 * @return Returns the cllx.
 */
 public String getCllx() {
    return cllx;
 }

 public void setCllx(String cllx) {
    this.cllx = cllx;
  }

 /**
 * 入库数量
 */
 private Long rksl;

 /**
 * @hibernate.property column="rksl"
 * @return Returns the rksl.
 */
 public Long getRksl() {
    return rksl;
 }

 public void setRksl(Long rksl) {
    this.rksl = rksl;
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
 * 缴料数量
 */
 private Long jlsl;

 /**
 * @hibernate.property column="jlsl"
 * @return Returns the jlsl.
 */
 public Long getJlsl() {
    return jlsl;
 }

 public void setJlsl(Long jlsl) {
    this.jlsl = jlsl;
  }

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
}

package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf08_clmxb"
 */

public class Tf08_clmxb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 582428008143496320L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf08_num"
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
 * 数量
 */
 private Long sl;

 /**
 * @hibernate.property column="sl"
 * @return Returns the sl.
 */
 public Long getSl() {
    return sl;
 }

 public void setSl(Long sl) {
    this.sl = sl;
  }

 /**
 * 项目标识
 */
 private Long zhxx_id;

 /**
 * @hibernate.property column="zhxx_id"
 * @return Returns the zhxx_id.
 */
 public Long getZhxx_id() {
    return zhxx_id;
 }

 public void setZhxx_id(Long zhxx_id) {
    this.zhxx_id = zhxx_id;
  }

 /**
 * 出入库动作（0：入库、1：出库）
 */
 private Long dz;

 /**
 * @hibernate.property column="dz"
 * @return Returns the dz.
 */
 public Long getDz() {
    return dz;
 }

 public void setDz(Long dz) {
    this.dz = dz;
  }

 /**
 * 是否出入库标识 1：已更新库存
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

 /**
 * 操作时间
 */
 private Date czsj;

 /**
 * @hibernate.property column="czsj"
 * @return Returns the czsj.
 */
 public Date getCzsj() {
    return czsj;
 }

 public void setCzsj(Date czsj) {
    this.czsj = czsj;
  }

 /**
 * 记录人员
 */
 private String czry;

 /**
 * @hibernate.property column="czry"
 * @return Returns the czry.
 */
 public String getCzry() {
    return czry;
 }

 public void setCzry(String czry) {
    this.czry = czry;
  }

 /**
 * 施工单位id
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
 * tc01的材料类型
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

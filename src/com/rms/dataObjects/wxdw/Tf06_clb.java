package com.rms.dataObjects.wxdw;

import java.io.Serializable;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf06_clb"
 */

public class Tf06_clb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 538947671959887104L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf06_num"
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
 * 材料类型,从TC01中取值（TYPE=材料类型）
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

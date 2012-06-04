package com.rms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tc05_xznl"
 */

public class Tc05_xznl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 143257429329493696L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc05_num"
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
 * 年度
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
 * 专业大类ID,TC02.ID
 */
 private Long gczy_id;

 /**
 * @hibernate.property column="gczy_id"
 * @return Returns the gczy_id.
 */
 public Long getGczy_id() {
    return gczy_id;
 }

 public void setGczy_id(Long gczy_id) {
    this.gczy_id = gczy_id;
  }

 /**
 * 专业小类ID,TC03.ID
 */
 private Long zyxx_id;

 /**
 * @hibernate.property column="zyxx_id"
 * @return Returns the zyxx_id.
 */
 public Long getZyxx_id() {
    return zyxx_id;
 }

 public void setZyxx_id(Long zyxx_id) {
    this.zyxx_id = zyxx_id;
  }

 /**
 * 新增能力名称
 */
 private String xznl_mc;

 /**
 * @hibernate.property column="xznl_mc"
 * @return Returns the xznl_mc.
 */
 public String getXznl_mc() {
    return xznl_mc;
 }

 public void setXznl_mc(String xznl_mc) {
    this.xznl_mc = xznl_mc;
  }

 /**
 * 新增单位成本
 */
 private String xznl_dw;

 /**
 * @hibernate.property column="xznl_dw"
 * @return Returns the xznl_dw.
 */
 public String getXznl_dw() {
    return xznl_dw;
 }

 public void setXznl_dw(String xznl_dw) {
    this.xznl_dw = xznl_dw;
  }

 /**
 * 标准单位成本
 */
 private Long bzdwcb;

 /**
 * @hibernate.property column="bzdwcb"
 * @return Returns the bzdwcb.
 */
 public Long getBzdwcb() {
    return bzdwcb;
 }

 public void setBzdwcb(Long bzdwcb) {
    this.bzdwcb = bzdwcb;
  }

 /**
 * 是否建设容量1：是
 */
 private Long sfjsrl;

 /**
 * @hibernate.property column="sfjsrl"
 * @return Returns the sfjsrl.
 */
 public Long getSfjsrl() {
    return sfjsrl;
 }

 public void setSfjsrl(Long sfjsrl) {
    this.sfjsrl = sfjsrl;
  }


}

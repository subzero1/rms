package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf26_wxdwjkf"
 */

public class Tf26_wxdwjkf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 974434974424451200L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf26_num"
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
 * 加扣分原因
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
 * 创建人地区
 */
 private String area_name;

 /**
 * @hibernate.property column="area_name"
 * @return Returns the area_name.
 */
 public String getArea_name() {
    return area_name;
 }

 public void setArea_name(String area_name) {
    this.area_name = area_name;
  }

 /**
 * 外协类别（设计，施工，监理，审计）
 */
 private String wxdw_lb;

 /**
 * @hibernate.property column="wxdw_lb"
 * @return Returns the wxdw_lb.
 */
 public String getWxdw_lb() {
    return wxdw_lb;
 }

 public void setWxdw_lb(String wxdw_lb) {
    this.wxdw_lb = wxdw_lb;
  }

 /**
 * 外协单位ID
 */
 private Long wxdw_id;

 /**
 * @hibernate.property column="wxdw_id"
 * @return Returns the wxdw_id.
 */
 public Long getWxdw_id() {
    return wxdw_id;
 }

 public void setWxdw_id(Long wxdw_id) {
    this.wxdw_id = wxdw_id;
  }

 /**
 * 外协单位名称
 */
 private String wxdw_name;

 /**
 * @hibernate.property column="wxdw_name"
 * @return Returns the wxdw_name.
 */
 public String getWxdw_name() {
    return wxdw_name;
 }

 public void setWxdw_name(String wxdw_name) {
    this.wxdw_name = wxdw_name;
  }

 /**
 * 创建人员 
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
 * 加扣分
 */
 private Long jkf;

 /**
 * @hibernate.property column="jkf"
 * @return Returns the jkf.
 */
 public Long getJkf() {
    return jkf;
 }

 public void setJkf(Long jkf) {
    this.jkf = jkf;
  }


}

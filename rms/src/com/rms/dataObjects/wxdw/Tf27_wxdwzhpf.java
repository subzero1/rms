package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf27_wxdwzhpf"
 */

public class Tf27_wxdwzhpf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 847429790738750208L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf27_num"
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
 * 外协单位标识
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
 private String wxdw_mc;

 /**
 * @hibernate.property column="wxdw_mc"
 * @return Returns the wxdw_mc.
 */
 public String getWxdw_mc() {
    return wxdw_mc;
 }

 public void setWxdw_mc(String wxdw_mc) {
    this.wxdw_mc = wxdw_mc;
  }

 /**
 * 录入人员
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
 * 录入时间
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
 * 地区名称
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
 * 公司规模得分
 */
 private Long gsgmdf;

 /**
 * @hibernate.property column="gsgmdf"
 * @return Returns the gsgmdf.
 */
 public Long getGsgmdf() {
    return gsgmdf;
 }

 public void setGsgmdf(Long gsgmdf) {
    this.gsgmdf = gsgmdf;
  }

 /**
 * 管理水平得分
 */
 private Long glspdf;

 /**
 * @hibernate.property column="glspdf"
 * @return Returns the glspdf.
 */
 public Long getGlspdf() {
    return glspdf;
 }

 public void setGlspdf(Long glspdf) {
    this.glspdf = glspdf;
  }

 /**
 * 应急能力得分
 */
 private Long yjnldf;

 /**
 * @hibernate.property column="yjnldf"
 * @return Returns the yjnldf.
 */
 public Long getYjnldf() {
    return yjnldf;
 }

 public void setYjnldf(Long yjnldf) {
    this.yjnldf = yjnldf;
  }

 /**
 * 工程质量得分
 */
 private Long gczldf;

 /**
 * @hibernate.property column="gczldf"
 * @return Returns the gczldf.
 */
 public Long getGczldf() {
    return gczldf;
 }

 public void setGczldf(Long gczldf) {
    this.gczldf = gczldf;
  }

 /**
 * 材料管理得分
 */
 private Long clgldf;

 /**
 * @hibernate.property column="clgldf"
 * @return Returns the clgldf.
 */
 public Long getClgldf() {
    return clgldf;
 }

 public void setClgldf(Long clgldf) {
    this.clgldf = clgldf;
  }

 /**
 * 综合得分（前五项得分，平均）
 */
 private Long zhdf;

 /**
 * @hibernate.property column="zhdf"
 * @return Returns the zhdf.
 */
 public Long getZhdf() {
    return zhdf;
 }

 public void setZhdf(Long zhdf) {
    this.zhdf = zhdf;
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


}

package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf03_yhda"
 */

public class Tf03_yhda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 137640927340271664L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf03_num"
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
 * 外协单位tf01.ID
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
 * 姓名
 */
 private String xm;

 /**
 * @hibernate.property column="xm"
 * @return Returns the xm.
 */
 public String getXm() {
    return xm;
 }

 public void setXm(String xm) {
    this.xm = xm;
  }

 /**
 * 性别
 */
 private String xb;

 /**
 * @hibernate.property column="xb"
 * @return Returns the xb.
 */
 public String getXb() {
    return xb;
 }

 public void setXb(String xb) {
    this.xb = xb;
  }

 /**
 * 民族
 */
 private String mz;

 /**
 * @hibernate.property column="mz"
 * @return Returns the mz.
 */
 public String getMz() {
    return mz;
 }

 public void setMz(String mz) {
    this.mz = mz;
  }

 /**
 * 籍贯
 */
 private String jg;

 /**
 * @hibernate.property column="jg"
 * @return Returns the jg.
 */
 public String getJg() {
    return jg;
 }

 public void setJg(String jg) {
    this.jg = jg;
  }

 /**
 * 生日
 */
 private Date csrq;

 /**
 * @hibernate.property column="csrq"
 * @return Returns the csrq.
 */
 public Date getCsrq() {
    return csrq;
 }

 public void setCsrq(Date csrq) {
    this.csrq = csrq;
  }

 /**
 * 家庭住址
 */
 private String jtzz;

 /**
 * @hibernate.property column="jtzz"
 * @return Returns the jtzz.
 */
 public String getJtzz() {
    return jtzz;
 }

 public void setJtzz(String jtzz) {
    this.jtzz = jtzz;
  }

 /**
 * 身份证号
 */
 private String sfzh;

 /**
 * @hibernate.property column="sfzh"
 * @return Returns the sfzh.
 */
 public String getSfzh() {
    return sfzh;
 }

 public void setSfzh(String sfzh) {
    this.sfzh = sfzh;
  }

 /**
 * 学历
 */
 private String xl;

 /**
 * @hibernate.property column="xl"
 * @return Returns the xl.
 */
 public String getXl() {
    return xl;
 }

 public void setXl(String xl) {
    this.xl = xl;
  }

 /**
 * 联系电话
 */
 private String lxdh;

 /**
 * @hibernate.property column="lxdh"
 * @return Returns the lxdh.
 */
 public String getLxdh() {
    return lxdh;
 }

 public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }

 /**
 * 工作经历
 */
 private String gzjl;

 /**
 * @hibernate.property column="gzjl"
 * @return Returns the gzjl.
 */
 public String getGzjl() {
    return gzjl;
 }

 public void setGzjl(String gzjl) {
    this.gzjl = gzjl;
  }

 /**
 * 照片
 */
 private String zp;

 /**
 * @hibernate.property column="zp"
 * @return Returns the zp.
 */
 public String getZp() {
    return zp;
 }

 public void setZp(String zp) {
    this.zp = zp;
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
 * 状态（是否在职：1 在，0 不在）
 */
 private String zt;

 /**
 * @hibernate.property column="zt"
 * @return Returns the zt.
 */
 public String getZt() {
    return zt;
 }

 public void setZt(String zt) {
    this.zt = zt;
  }

 /**
 * 作业能力（工日）
 */
 private Long zynl;

 /**
 * @hibernate.property column="zynl"
 * @return Returns the zynl.
 */
 public Long getZynl() {
    return zynl;
 }

 public void setZynl(Long zynl) {
    this.zynl = zynl;
  }


}

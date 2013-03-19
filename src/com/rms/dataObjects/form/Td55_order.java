package com.rms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-03-18
 * @hibernate.class table="Td55_order"
 */

public class Td55_order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 904178602683075328L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td55_num"
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
 * 订单编码
 */
 private String ddbm;

 /**
 * @hibernate.property column="ddbm"
 * @return Returns the ddbm.
 */
 public String getDdbm() {
    return ddbm;
 }

 public void setDdbm(String ddbm) {
    this.ddbm = ddbm;
  }

 /**
 * 归属地区
 */
 private String gsdq;

 /**
 * @hibernate.property column="gsdq"
 * @return Returns the gsdq.
 */
 public String getGsdq() {
    return gsdq;
 }

 public void setGsdq(String gsdq) {
    this.gsdq = gsdq;
  }

 /**
 * 服務
 */
 private String service;

 /**
 * @hibernate.property column="service"
 * @return Returns the service.
 */
 public String getService() {
    return service;
 }

 public void setService(String service) {
    this.service = service;
  }

 /**
 * 订单状态
 */
 private String ddzt;

 /**
 * @hibernate.property column="ddzt"
 * @return Returns the ddzt.
 */
 public String getDdzt() {
    return ddzt;
 }

 public void setDdzt(String ddzt) {
    this.ddzt = ddzt;
  }

 /**
 * 订单类型
 */
 private String ddlx;

 /**
 * @hibernate.property column="ddlx"
 * @return Returns the ddlx.
 */
 public String getDdlx() {
    return ddlx;
 }

 public void setDdlx(String ddlx) {
    this.ddlx = ddlx;
  }

 /**
 * 订单分类
 */
 private String ddfl;

 /**
 * @hibernate.property column="ddfl"
 * @return Returns the ddfl.
 */
 public String getDdfl() {
    return ddfl;
 }

 public void setDdfl(String ddfl) {
    this.ddfl = ddfl;
  }

 /**
 * 业务开通等级
 */
 private String ywktdj;

 /**
 * @hibernate.property column="ywktdj"
 * @return Returns the ywktdj.
 */
 public String getYwktdj() {
    return ywktdj;
 }

 public void setYwktdj(String ywktdj) {
    this.ywktdj = ywktdj;
  }

 /**
 * 保障服务等级
 */
 private String bzfwdj;

 /**
 * @hibernate.property column="bzfwdj"
 * @return Returns the bzfwdj.
 */
 public String getBzfwdj() {
    return bzfwdj;
 }

 public void setBzfwdj(String bzfwdj) {
    this.bzfwdj = bzfwdj;
  }

 /**
 * 网络服务等级
 */
 private String wlfwdj;

 /**
 * @hibernate.property column="wlfwdj"
 * @return Returns the wlfwdj.
 */
 public String getWlfwdj() {
    return wlfwdj;
 }

 public void setWlfwdj(String wlfwdj) {
    this.wlfwdj = wlfwdj;
  }

 /**
 * 服务订单类型
 */
 private String fwddlx;

 /**
 * @hibernate.property column="fwddlx"
 * @return Returns the fwddlx.
 */
 public String getFwddlx() {
    return fwddlx;
 }

 public void setFwddlx(String fwddlx) {
    this.fwddlx = fwddlx;
  }

 /**
 * 客户类别
 */
 private String khlb;

 /**
 * @hibernate.property column="khlb"
 * @return Returns the khlb.
 */
 public String getKhlb() {
    return khlb;
 }

 public void setKhlb(String khlb) {
    this.khlb = khlb;
  }

 /**
 * 要求业务开通时间
 */
 private String yqywktsj;

 /**
 * @hibernate.property column="yqywktsj"
 * @return Returns the yqywktsj.
 */
 public String getYqywktsj() {
    return yqywktsj;
 }

 public void setYqywktsj(String yqywktsj) {
    this.yqywktsj = yqywktsj;
  }

 /**
 * 受理时间
 */
 private String slsj;

 /**
 * @hibernate.property column="slsj"
 * @return Returns the slsj.
 */
 public String getSlsj() {
    return slsj;
 }

 public void setSlsj(String slsj) {
    this.slsj = slsj;
  }

 /**
 * 受理人
 */
 private String slr;

 /**
 * @hibernate.property column="slr"
 * @return Returns the slr.
 */
 public String getSlr() {
    return slr;
 }

 public void setSlr(String slr) {
    this.slr = slr;
  }

 /**
 * 业务租用到期时间
 */
 private String ywzysj;

 /**
 * @hibernate.property column="ywzysj"
 * @return Returns the ywzysj.
 */
 public String getYwzysj() {
    return ywzysj;
 }

 public void setYwzysj(String ywzysj) {
    this.ywzysj = ywzysj;
  }

 /**
 * 专线号
 */
 private String zxh;

 /**
 * @hibernate.property column="zxh"
 * @return Returns the zxh.
 */
 public String getZxh() {
    return zxh;
 }

 public void setZxh(String zxh) {
    this.zxh = zxh;
  }

 /**
 * 主题
 */
 private String theme;

 /**
 * @hibernate.property column="theme"
 * @return Returns the theme.
 */
 public String getTheme() {
    return theme;
 }

 public void setTheme(String theme) {
    this.theme = theme;
  }

 /**
 * 需求描述
 */
 private String xqms;

 /**
 * @hibernate.property column="xqms"
 * @return Returns the xqms.
 */
 public String getXqms() {
    return xqms;
 }

 public void setXqms(String xqms) {
    this.xqms = xqms;
  }

 /**
 * 联系信息
 */
 private String lxxx;

 /**
 * @hibernate.property column="lxxx"
 * @return Returns the lxxx.
 */
 public String getLxxx() {
    return lxxx;
 }

 public void setLxxx(String lxxx) {
    this.lxxx = lxxx;
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
 * 附件表标识
 */
 private Long slave_id;

 /**
 * @hibernate.property column="slave_id"
 * @return Returns the slave_id.
 */
 public Long getSlave_id() {
    return slave_id;
 }

 public void setSlave_id(Long slave_id) {
    this.slave_id = slave_id;
  }


}

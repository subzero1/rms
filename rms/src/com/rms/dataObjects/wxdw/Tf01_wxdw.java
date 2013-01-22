package com.rms.dataObjects.wxdw;

import java.io.Serializable;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf01_wxdw"
 */

public class Tf01_wxdw implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 597645138724158464L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf01_num"
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
 * 工号前三位
 */
 private String no;

 /**
 * @hibernate.property column="no"
 * @return Returns the no.
 */
 public String getNo() {
    return no;
 }

 public void setNo(String no) {
    this.no = no;
  }

 /**
 * 名称
 */
 private String mc;

 /**
 * @hibernate.property column="mc"
 * @return Returns the mc.
 */
 public String getMc() {
    return mc;
 }

 public void setMc(String mc) {
    this.mc = mc;
  }

 /**
 * 单位地址
 */
 private String dwdz;

 /**
 * @hibernate.property column="dwdz"
 * @return Returns the dwdz.
 */
 public String getDwdz() {
    return dwdz;
 }

 public void setDwdz(String dwdz) {
    this.dwdz = dwdz;
  }

 /**
 * 资质等级
 */
 private String zzdj;

 /**
 * @hibernate.property column="zzdj"
 * @return Returns the zzdj.
 */
 public String getZzdj() {
    return zzdj;
 }

 public void setZzdj(String zzdj) {
    this.zzdj = zzdj;
  }

 /**
 * 营业执照号
 */
 private String yyzzh;

 /**
 * @hibernate.property column="yyzzh"
 * @return Returns the yyzzh.
 */
 public String getYyzzh() {
    return yyzzh;
 }

 public void setYyzzh(String yyzzh) {
    this.yyzzh = yyzzh;
  }

 /**
 * 法人
 */
 private String fr;

 /**
 * @hibernate.property column="fr"
 * @return Returns the fr.
 */
 public String getFr() {
    return fr;
 }

 public void setFr(String fr) {
    this.fr = fr;
  }

 /**
 * 外协类别（设计，施工，监理，审计）
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
 * 承接区域（同
td00_zhxx.ssdq,多选）	
 */
 private String qy;

 /**
 * @hibernate.property column="qy"
 * @return Returns the qy.
 */
 public String getQy() {
    return qy;
 }

 public void setQy(String qy) {
    this.qy = qy;
  }

 /**
 * 承接专业(同
td00_zhxx.gclb，多选)	
 */
 private String zy;

 /**
 * @hibernate.property column="zy"
 * @return Returns the zy.
 */
 public String getZy() {
    return zy;
 }

 public void setZy(String zy) {
    this.zy = zy;
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
 * 状态（有效，无效）
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
 * 营业执照号
 */
 private String zsh;

 /**
 * @hibernate.property column="zsh"
 * @return Returns the zsh.
 */
 public String getZsh() {
    return zsh;
 }

 public void setZsh(String zsh) {
    this.zsh = zsh;
  }

 /**
 * 结算系数
 */
 private Long jsxs;

 /**
 * @hibernate.property column="jsxs"
 * @return Returns the jsxs.
 */
 public Long getJsxs() {
    return jsxs;
 }

 public void setJsxs(Long jsxs) {
    this.jsxs = jsxs;
  }

 /**
 * 最大承接工日
 */
 private Long zdcjgr;

 /**
 * @hibernate.property column="zdcjgr"
 * @return Returns the zdcjgr.
 */
 public Long getZdcjgr() {
    return zdcjgr;
 }

 public void setZdcjgr(Long zdcjgr) {
    this.zdcjgr = zdcjgr;
  }

 /**
 * 施工单位开票名称
 */
 private String realname;

 /**
 * @hibernate.property column="realname"
 * @return Returns the realname.
 */
 public String getRealname() {
    return realname;
 }

 public void setRealname(String realname) {
    this.realname = realname;
  }


}

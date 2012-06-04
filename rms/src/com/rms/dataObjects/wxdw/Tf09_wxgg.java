package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf09_wxgg"
 */

public class Tf09_wxgg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 486727064246464384L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf09_num"
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
 * 发布人标识
 */
 private Long fbr_id;

 /**
 * @hibernate.property column="fbr_id"
 * @return Returns the fbr_id.
 */
 public Long getFbr_id() {
    return fbr_id;
 }

 public void setFbr_id(Long fbr_id) {
    this.fbr_id = fbr_id;
  }

 /**
 * 发布人员名称
 */
 private String fbr_mc;

 /**
 * @hibernate.property column="fbr_mc"
 * @return Returns the fbr_mc.
 */
 public String getFbr_mc() {
    return fbr_mc;
 }

 public void setFbr_mc(String fbr_mc) {
    this.fbr_mc = fbr_mc;
  }

 /**
 * 发布日期
 */
 private Date fbsj;

 /**
 * @hibernate.property column="fbsj"
 * @return Returns the fbsj.
 */
 public Date getFbsj() {
    return fbsj;
 }

 public void setFbsj(Date fbsj) {
    this.fbsj = fbsj;
  }

 /**
 * 公告主体
 */
 private String ggzt;

 /**
 * @hibernate.property column="ggzt"
 * @return Returns the ggzt.
 */
 public String getGgzt() {
    return ggzt;
 }

 public void setGgzt(String ggzt) {
    this.ggzt = ggzt;
  }

 /**
 * 传达范围:
 */
 private String cdfw;

 /**
 * @hibernate.property column="cdfw"
 * @return Returns the cdfw.
 */
 public String getCdfw() {
    return cdfw;
 }

 public void setCdfw(String cdfw) {
    this.cdfw = cdfw;
  }

 /**
 * 紧急程度
 */
 private String jjcd;

 /**
 * @hibernate.property column="jjcd"
 * @return Returns the jjcd.
 */
 public String getJjcd() {
    return jjcd;
 }

 public void setJjcd(String jjcd) {
    this.jjcd = jjcd;
  }

 /**
 * 公告内容
 */
 private String ggnr;

 /**
 * @hibernate.property column="ggnr"
 * @return Returns the ggnr.
 */
 public String getGgnr() {
    return ggnr;
 }

 public void setGgnr(String ggnr) {
    this.ggnr = ggnr;
  }

 /**
 * 待确认奖罚总额
 */
 private Long dqrjfze;

 /**
 * @hibernate.property column="dqrjfze"
 * @return Returns the dqrjfze.
 */
 public Long getDqrjfze() {
    return dqrjfze;
 }

 public void setDqrjfze(Long dqrjfze) {
    this.dqrjfze = dqrjfze;
  }

 /**
 * 目标单位，用“，”串接单位名称
 */
 private String mbdw;

 /**
 * @hibernate.property column="mbdw"
 * @return Returns the mbdw.
 */
 public String getMbdw() {
    return mbdw;
 }

 public void setMbdw(String mbdw) {
    this.mbdw = mbdw;
  }

 /**
 * 哪些单位已读过,用“，”串接单位名称；每个单位只能出现一次。
 */
 private String cydw;

 /**
 * @hibernate.property column="cydw"
 * @return Returns the cydw.
 */
 public String getCydw() {
    return cydw;
 }

 public void setCydw(String cydw) {
    this.cydw = cydw;
  }

 /**
 * 状态，0为新建未发送，1为发送
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
 * 公告接收单位的类别
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

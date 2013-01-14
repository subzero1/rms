package com.rms.dataObjects.mbk;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-01-14
 * @hibernate.class table="Td23_kcsqb"
 */

public class Td23_kcsqb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 337001463409361792L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td23_num"
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
 * 目标库标识
 */
 private Long mbk_id;

 /**
 * @hibernate.property column="mbk_id"
 * @return Returns the mbk_id.
 */
 public Long getMbk_id() {
    return mbk_id;
 }

 public void setMbk_id(Long mbk_id) {
    this.mbk_id = mbk_id;
  }

 /**
 * 创建人
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
 * 创建时间
 */
 private Date cjsj;

 /**
 * @hibernate.property column="cjsj"
 * @return Returns the cjsj.
 */
 public Date getCjsj() {
    return cjsj;
 }

 public void setCjsj(Date cjsj) {
    this.cjsj = cjsj;
  }

 /**
 * 预约勘察时间
 */
 private Date yykcsj;

 /**
 * @hibernate.property column="yykcsj"
 * @return Returns the yykcsj.
 */
 public Date getYykcsj() {
    return yykcsj;
 }

 public void setYykcsj(Date yykcsj) {
    this.yykcsj = yykcsj;
  }

 /**
 * 是否提供机房
 */
 private Long sftgjf;

 /**
 * @hibernate.property column="sftgjf"
 * @return Returns the sftgjf.
 */
 public Long getSftgjf() {
    return sftgjf;
 }

 public void setSftgjf(Long sftgjf) {
    this.sftgjf = sftgjf;
  }

 /**
 * 是否同意在楼顶建房
 */
 private Long sftyld;

 /**
 * @hibernate.property column="sftyld"
 * @return Returns the sftyld.
 */
 public Long getSftyld() {
    return sftyld;
 }

 public void setSftyld(Long sftyld) {
    this.sftyld = sftyld;
  }

 /**
 * 是否要美化天线
 */
 private Long sfmhtx;

 /**
 * @hibernate.property column="sfmhtx"
 * @return Returns the sfmhtx.
 */
 public Long getSfmhtx() {
    return sfmhtx;
 }

 public void setSfmhtx(Long sfmhtx) {
    this.sfmhtx = sfmhtx;
  }

 /**
 * 其它沟通
 */
 private String qtgt;

 /**
 * @hibernate.property column="qtgt"
 * @return Returns the qtgt.
 */
 public String getQtgt() {
    return qtgt;
 }

 public void setQtgt(String qtgt) {
    this.qtgt = qtgt;
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


}

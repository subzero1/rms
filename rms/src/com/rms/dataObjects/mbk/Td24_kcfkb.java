package com.rms.dataObjects.mbk;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-01-16
 * @hibernate.class table="Td24_kcfkb"
 */

public class Td24_kcfkb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 72214371418327424L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td24_num"
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
 * 反馈时间
 */
 private Date fksj;

 /**
 * @hibernate.property column="fksj"
 * @return Returns the fksj.
 */
 public Date getFksj() {
    return fksj;
 }

 public void setFksj(Date fksj) {
    this.fksj = fksj;
  }

 /**
 * 无线中心签字
 */
 private String wxzxqz;

 /**
 * @hibernate.property column="wxzxqz"
 * @return Returns the wxzxqz.
 */
 public String getWxzxqz() {
    return wxzxqz;
 }

 public void setWxzxqz(String wxzxqz) {
    this.wxzxqz = wxzxqz;
  }

 /**
 * 设计院签字
 */
 private String sjyqz;

 /**
 * @hibernate.property column="sjyqz"
 * @return Returns the sjyqz.
 */
 public String getSjyqz() {
    return sjyqz;
 }

 public void setSjyqz(String sjyqz) {
    this.sjyqz = sjyqz;
  }

 /**
 * 反馈确认
 */
 private String fkqr;

 /**
 * @hibernate.property column="fkqr"
 * @return Returns the fkqr.
 */
 public String getFkqr() {
    return fkqr;
 }

 public void setFkqr(String fkqr) {
    this.fkqr = fkqr;
  }

 /**
 * 其它说明
 */
 private String qtsm;

 /**
 * @hibernate.property column="qtsm"
 * @return Returns the qtsm.
 */
 public String getQtsm() {
    return qtsm;
 }

 public void setQtsm(String qtsm) {
    this.qtsm = qtsm;
  }


}

package com.rms.dataObjects.mbk;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-01-16
 * @hibernate.class table="Td25_kcfkmx"
 */

public class Td25_kcfkmx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 726879527509276160L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td25_num"
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
 * 勘察反馈标识TD24.ID
 */
 private Long kcfk_id;

 /**
 * @hibernate.property column="kcfk_id"
 * @return Returns the kcfk_id.
 */
 public Long getKcfk_id() {
    return kcfk_id;
 }

 public void setKcfk_id(Long kcfk_id) {
    this.kcfk_id = kcfk_id;
  }

 /**
 * 反馈项名称 TC01.NAME
 */
 private String fkx;

 /**
 * @hibernate.property column="fkx"
 * @return Returns the fkx.
 */
 public String getFkx() {
    return fkx;
 }

 public void setFkx(String fkx) {
    this.fkx = fkx;
  }

 /**
 * 反馈结果
 */
 private Long jg;

 /**
 * @hibernate.property column="jg"
 * @return Returns the jg.
 */
 public Long getJg() {
    return jg;
 }

 public void setJg(Long jg) {
    this.jg = jg;
  }

 /**
 * 个数
 */
 private Long gs;

 /**
 * @hibernate.property column="gs"
 * @return Returns the gs.
 */
 public Long getGs() {
    return gs;
 }

 public void setGs(Long gs) {
    this.gs = gs;
  }


}

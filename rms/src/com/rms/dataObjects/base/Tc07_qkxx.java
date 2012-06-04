package com.rms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tc07_qkxx"
 */

public class Tc07_qkxx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 753169109790153216L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc07_num"
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
 * 切块大类标识
 */
 private Long qk_id;

 /**
 * @hibernate.property column="qk_id"
 * @return Returns the qk_id.
 */
 public Long getQk_id() {
    return qk_id;
 }

 public void setQk_id(Long qk_id) {
    this.qk_id = qk_id;
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


}

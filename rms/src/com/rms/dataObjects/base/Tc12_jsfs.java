package com.rms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-01-12
 * @hibernate.class table="Tc12_jsfs"
 */

public class Tc12_jsfs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 784053179462437376L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc12_num"
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
 * 建设性质TC01.ID
 */
 private Long jsxz_id;

 /**
 * @hibernate.property column="jsxz_id"
 * @return Returns the jsxz_id.
 */
 public Long getJsxz_id() {
    return jsxz_id;
 }

 public void setJsxz_id(Long jsxz_id) {
    this.jsxz_id = jsxz_id;
  }

 /**
 * 建设方式名称
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

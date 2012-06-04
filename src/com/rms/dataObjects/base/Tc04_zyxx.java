package com.rms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tc04_zyxx"
 */

public class Tc04_zyxx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 569600123831477184L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc04_num"
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
 * 专业大类ID
 */
 private Long gczy_id;

 /**
 * @hibernate.property column="gczy_id"
 * @return Returns the gczy_id.
 */
 public Long getGczy_id() {
    return gczy_id;
 }

 public void setGczy_id(Long gczy_id) {
    this.gczy_id = gczy_id;
  }

 /**
 * 专业小类名称
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

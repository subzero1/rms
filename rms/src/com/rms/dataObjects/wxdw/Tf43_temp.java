package com.rms.dataObjects.wxdw;

import java.io.Serializable;

/**
 * @author cmp_auto 2013-06-05
 * @hibernate.class table="Tf43_temp"
 */

public class Tf43_temp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 931334914804815232L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te11_num"
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
 * 
 */
 private String c1;

 /**
 * @hibernate.property column="c1"
 * @return Returns the c1.
 */
 public String getC1() {
    return c1;
 }

 public void setC1(String c1) {
    this.c1 = c1;
  }

 /**
 * 
 */
 private String c2;

 /**
 * @hibernate.property column="c2"
 * @return Returns the c2.
 */
 public String getC2() {
    return c2;
 }

 public void setC2(String c2) {
    this.c2 = c2;
  }

 /**
 * 
 */
 private String c3;

 /**
 * @hibernate.property column="c3"
 * @return Returns the c3.
 */
 public String getC3() {
    return c3;
 }

 public void setC3(String c3) {
    this.c3 = c3;
  }

 /**
 * 
 */
 private String c4;

 /**
 * @hibernate.property column="c4"
 * @return Returns the c4.
 */
 public String getC4() {
    return c4;
 }

 public void setC4(String c4) {
    this.c4 = c4;
  }

 /**
 * 
 */
 private String c5;

 /**
 * @hibernate.property column="c5"
 * @return Returns the c5.
 */
 public String getC5() {
    return c5;
 }

 public void setC5(String c5) {
    this.c5 = c5;
  }

 /**
 * 
 */
 private String c6;

 /**
 * @hibernate.property column="c6"
 * @return Returns the c6.
 */
 public String getC6() {
    return c6;
 }

 public void setC6(String c6) {
    this.c6 = c6;
  }

 /**
 * 
 */
 private String c7;

 /**
 * @hibernate.property column="c7"
 * @return Returns the c7.
 */
 public String getC7() {
    return c7;
 }

 public void setC7(String c7) {
    this.c7 = c7;
  }

 /**
 * 
 */
 private String c8;

 /**
 * @hibernate.property column="c8"
 * @return Returns the c8.
 */
 public String getC8() {
    return c8;
 }

 public void setC8(String c8) {
    this.c8 = c8;
  }

 /**
 * 
 */
 private String c9;

 /**
 * @hibernate.property column="c9"
 * @return Returns the c9.
 */
 public String getC9() {
    return c9;
 }

 public void setC9(String c9) {
    this.c9 = c9;
  }


}

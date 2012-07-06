package com.rms.dataObjects.gcjs;

import java.io.Serializable;

/**
 * 工程预算--表二
 * @author wlx 2010-04-15
 * @hibernate.class table="Te03_gcgys_b2"
 */
public class Te03_gcgys_b2 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7993310828698533121L;
	/**
	 * id
	 */
	private Long id ;
	/**
	 * 工程ID
	 */
	private Long gc_id;
	/**
	 * 序号
	 */
	private String xh1;
	/**
	 * 费用名称
	 */
	private String fymc1;
	/**
	 * 依据和计算方法
	 */
	private String yjsf1;
	/**
	 * 技工费
	 */
	private Double jgf1;
	/**
	 * 普工费
	 */
	private Double pgf1;
	/**
	 * 合计
	 */
	private Double hj1;
	/**
	 * 序号（右）
	 */
	private String xh2;
	/**
	 * 费用名称（右）
	 */
	private String fymc2;
	/**
	 * 依据和计算方法（右）
	 */
	private String yjsf2;
	/**
	 * 技工费（右）
	 */
	private Double jgf2;
	/**
	 * 普工费（右）
	 */
	private Double pgf2;
	/**
	 * 合计（右）
	 */
	private Double hj2;
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TE03B2NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @hibernate.property column="gc_id"
	 * @return gc_id
	 */
	public Long getGc_id() {
		return gc_id;
	}
	/**
	 * @param gc_id the gc_id to set
	 */
	public void setGc_id(Long gc_id) {
		this.gc_id = gc_id;
	}
	/**
	 * @hibernate.property column="xh1"
	 * @return xh1
	 */
	public String getXh1() {
		return xh1;
	}
	/**
	 * @param xh1 the xh1 to set
	 */
	public void setXh1(String xh1) {
		this.xh1 = xh1;
	}
	/**
	 * @hibernate.property column="fymc1"
	 * @return fymc1
	 */
	public String getFymc1() {
		return fymc1;
	}
	/**
	 * @param fymc1 the fymc1 to set
	 */
	public void setFymc1(String fymc1) {
		this.fymc1 = fymc1;
	}
	/**
	 * @hibernate.property column="yjsf1"
	 * @return yjsf1
	 */
	public String getYjsf1() {
		return yjsf1;
	}
	/**
	 * @param yjsf1 the yjsf1 to set
	 */
	public void setYjsf1(String yjsf1) {
		this.yjsf1 = yjsf1;
	}
	/**
	 * @hibernate.property column="jgf1"
	 * @return jgf1
	 */
	public Double getJgf1() {
		return jgf1;
	}
	/**
	 * @param yjsf1 the yjsf1 to set
	 */
	public void setJgf1(Double jgf1) {
		this.jgf1 = jgf1;
	}
	/**
	 * @hibernate.property column="pgf1"
	 * @return pgf1
	 */
	public Double getPgf1() {
		return pgf1;
	}
	/**
	 * @param pgf1 the pgf1 to set
	 */
	public void setPgf1(Double pgf1) {
		this.pgf1 = pgf1;
	}
	/**
	 * @hibernate.property column="hj1"
	 * @return hj1
	 */
	public Double getHj1() {
		return hj1;
	}
	/**
	 * @param hj1 the hj1 to set
	 */
	public void setHj1(Double hj1) {
		this.hj1 = hj1;
	}
	/**
	 * @hibernate.property column="xh2"
	 * @return xh2
	 */
	public String getXh2() {
		return xh2;
	}
	/**
	 * @param xh2 the xh2 to set
	 */
	public void setXh2(String xh2) {
		this.xh2 = xh2;
	}
	/**
	 * @hibernate.property column="fymc2"
	 * @return fymc2
	 */
	public String getFymc2() {
		return fymc2;
	}
	/**
	 * @param fymc2 the fymc2 to set
	 */
	public void setFymc2(String fymc2) {
		this.fymc2 = fymc2;
	}
	/**
	 * @hibernate.property column="yjsf2"
	 * @return yjsf2
	 */
	public String getYjsf2() {
		return yjsf2;
	}
	/**
	 * @param yjsf2 the yjsf2 to set
	 */
	public void setYjsf2(String yjsf2) {
		this.yjsf2 = yjsf2;
	}
	/**
	 * @hibernate.property column="jgf2"
	 * @return jgf2
	 */
	public Double getJgf2() {
		return jgf2;
	}
	/**
	 * @param jgf2 the jgf2 to set
	 */
	public void setJgf2(Double jgf2) {
		this.jgf2 = jgf2;
	}
	/**
	 * @hibernate.property column="pgf2"
	 * @return pgf2
	 */
	public Double getPgf2() {
		return pgf2;
	}
	/**
	 * @param pgf2 the pgf2 to set
	 */
	public void setPgf2(Double pgf2) {
		this.pgf2 = pgf2;
	}
	/**
	 * @hibernate.property column="hj2"
	 * @return hj2
	 */
	public Double getHj2() {
		return hj2;
	}
	/**
	 * @param pgf2 the pgf2 to set
	 */
	public void setHj2(Double hj2) {
		this.hj2 = hj2;
	}

}

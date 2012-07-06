package com.rms.dataObjects.gcjs;

import java.io.Serializable;

/**
 * 工程预算--表五甲
 * @author wlx 2010-04-15
 * @hibernate.class table="Te03_gcgys_b5j"
 */
public class Te03_gcgys_b5j implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3107074915011056991L;
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
	private Long xh;
	/**
	 * 费用名称
	 */
	private String fymc;
	/**
	 * 单位
	 */
	private String dw;
	/**
	 * 数量
	 */
	private Double sl;
	/**
	 * 单价
	 */
	private Double dj;
	/**
	 * 合计
	 */
	private Double hj;
	/**
	 * 备注
	 */
	private String bz;
	/**
	 * 计算依据和方法
	 */
	private String yjsf;
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TE03B5JNUM"
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
	 * @hibernate.property column="xh"
	 * @return xh
	 */
	public Long getXh() {
		return xh;
	}
	/**
	 * @param xh the xh to set
	 */
	public void setXh(Long xh) {
		this.xh = xh;
	}
	/**
	 * @hibernate.property column="fymc"
	 * @return fymc
	 */
	public String getFymc() {
		return fymc;
	}
	/**
	 * @param fymc the fymc to set
	 */
	public void setFymc(String fymc) {
		this.fymc = fymc;
	}
	/**
	 * @hibernate.property column="dw"
	 * @return dw
	 */
	public String getDw() {
		return dw;
	}
	/**
	 * @param dw the dw to set
	 */
	public void setDw(String dw) {
		this.dw = dw;
	}
	/**
	 * @hibernate.property column="sl"
	 * @return sl
	 */
	public Double getSl() {
		return sl;
	}
	/**
	 * @param sl the sl to set
	 */
	public void setSl(Double sl) {
		this.sl = sl;
	}
	/**
	 * @hibernate.property column="dj"
	 * @return dj
	 */
	public Double getDj() {
		return dj;
	}
	/**
	 * @param dj the dj to set
	 */
	public void setDj(Double dj) {
		this.dj = dj;
	}
	/**
	 * @hibernate.property column="hj"
	 * @return hj
	 */
	public Double getHj() {
		return hj;
	}
	/**
	 * @param hj the hj to set
	 */
	public void setHj(Double hj) {
		this.hj = hj;
	}
	/**
	 * @hibernate.property column="bz"
	 * @return bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @hibernate.property column="yjsf"
	 * @return yjsf
	 */
	public String getYjsf() {
		return yjsf;
	}
	/**
	 * @param yjsf the yjsf to set
	 */
	public void setYjsf(String yjsf) {
		this.yjsf = yjsf;
	}

}

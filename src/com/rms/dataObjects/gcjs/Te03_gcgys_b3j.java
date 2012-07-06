package com.rms.dataObjects.gcjs;

import java.io.Serializable;

/**
 * 工程预算--表三甲
 * @author wlx 2010-04-15
 * @hibernate.class table="Te03_gcgys_b3j"
 */
public class Te03_gcgys_b3j implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6305517178619430839L;
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
	 * 定额编号
	 */
	private String debh;
	/**
	 * 项目名称
	 */
	private String xmmc;
	/**
	 * 单位
	 */
	private String dw;
	/**
	 * 数量
	 */
	private Double sl;
	/**
	 * 单位技工
	 */
	private Double dwjg;
	/**
	 * 单位普工
	 */
	private Double dwpg;
	/**
	 * 技工合计
	 */
	private Double jghj;
	/**
	 * 普工合计
	 */
	private Double pghj;
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TE03B3JNUM"
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
	 * @hibernate.property column="debh"
	 * @return debh
	 */
	public String getDebh() {
		return debh;
	}
	/**
	 * @param debh the debh to set
	 */
	public void setDebh(String debh) {
		this.debh = debh;
	}
	/**
	 * @hibernate.property column="xmmc"
	 * @return xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc the xmmc to set
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
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
	 * @hibernate.property column="dwjg"
	 * @return dwjg
	 */
	public Double getDwjg() {
		return dwjg;
	}
	/**
	 * @param dwjg the dwjg to set
	 */
	public void setDwjg(Double dwjg) {
		this.dwjg = dwjg;
	}
	/**
	 * @hibernate.property column="dwpg"
	 * @return dwpg
	 */
	public Double getDwpg() {
		return dwpg;
	}
	/**
	 * @param dwpg the dwpg to set
	 */
	public void setDwpg(Double dwpg) {
		this.dwpg = dwpg;
	}
	/**
	 * @hibernate.property column="jghj"
	 * @return jghj
	 */
	public Double getJghj() {
		return jghj;
	}
	/**
	 * @param jghj the jghj to set
	 */
	public void setJghj(Double jghj) {
		this.jghj = jghj;
	}
	/**
	 * @hibernate.property column="pghj"
	 * @return pghj
	 */
	public Double getPghj() {
		return pghj;
	}
	/**
	 * @param pghj the pghj to set
	 */
	public void setPghj(Double pghj) {
		this.pghj = pghj;
	}

}

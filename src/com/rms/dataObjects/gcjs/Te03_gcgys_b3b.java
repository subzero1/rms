package com.rms.dataObjects.gcjs;

import java.io.Serializable;

/**
 * 工程预算--表三丙
 * @author wlx 2010-04-15
 * @hibernate.class table="Te03_gcgys_b3b"
 */
public class Te03_gcgys_b3b implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4417986074744387857L;
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
	 * 仪表名称
	 */
	private String ybmc;
	/**
	 * 单位
	 */
	private String dw;
	/**
	 * 数量
	 */
	private Double sl;
	/**
	 * 单位数量
	 */
	private Double dwsl;
	/**
	 * 单价
	 */
	private Double dj;
	/**
	 * 数量合计
	 */
	private Double slhj;
	/**
	 * 金额合计
	 */
	private Double jehj;
	/**
	 * 备注
	 */
	private String bz;
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TE03B3BNUM"
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
	 * @hibernate.property column="ybmc"
	 * @return ybmc
	 */
	public String getYbmc() {
		return ybmc;
	}
	/**
	 * @param ybmc the ybmc to set
	 */
	public void setYbmc(String ybmc) {
		this.ybmc = ybmc;
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
	 * @hibernate.property column="dwsl"
	 * @return dwsl
	 */
	public Double getDwsl() {
		return dwsl;
	}
	/**
	 * @param dwsl the dwsl to set
	 */
	public void setDwsl(Double dwsl) {
		this.dwsl = dwsl;
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
	 * @hibernate.property column="slhj"
	 * @return slhj
	 */
	public Double getSlhj() {
		return slhj;
	}
	/**
	 * @param slhj the slhj to set
	 */
	public void setSlhj(Double slhj) {
		this.slhj = slhj;
	}
	/**
	 * @hibernate.property column="jehj"
	 * @return jehj
	 */
	public Double getJehj() {
		return jehj;
	}
	/**
	 * @param jehj the jehj to set
	 */
	public void setJehj(Double jehj) {
		this.jehj = jehj;
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


}

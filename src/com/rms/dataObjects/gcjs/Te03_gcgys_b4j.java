package com.rms.dataObjects.gcjs;

import java.io.Serializable;

/**
 * 工程预算--表四甲
 * @author wlx 2010-04-15
 * @hibernate.class table="Te03_gcgys_b4j"
 */
public class Te03_gcgys_b4j implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3802732972563212215L;
	/**
	 * id
	 */
	private Long id ;
	/**
	 * 工程ID
	 */
	private Long gc_id;
	/**
	 * 表格编号
	 */
	private String bgbh;
	/**
	 * 序号
	 */
	private Long xh;
	/**
	 * 名称
	 */
	private String mc;
	/**
	 * 规格型号
	 */
	private String xhgg;

	/**
	 * 物品型号
	 */
	private String wpxh;
	
	
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
	 * 编码
	 */
	private String bm;
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TE03B4JNUM"
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
	 * @hibernate.property column="bgbh"
	 * @return bgbh
	 */
	public String getBgbh() {
		return bgbh;
	}
	/**
	 * @param bgbh the bgbh to set
	 */
	public void setBgbh(String bgbh) {
		this.bgbh = bgbh;
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
	 * @hibernate.property column="mc"
	 * @return mc
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * @param mc the mc to set
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * @hibernate.property column="xhgg"
	 * @return xhgg
	 */
	public String getXhgg() {
		return xhgg;
	}
	/**
	 * @param xhgg the xhgg to set
	 */
	public void setXhgg(String xhgg) {
		this.xhgg = xhgg;
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
	 * @hibernate.property column="bm"
	 * @return bm
	 */
	public String getBm() {
		return bm;
	}
	/**
	 * @param bm the bm to set
	 */
	public void setBm(String bm) {
		this.bm = bm;
	}
	/**
	 * @hibernate.property column="wpxh"
	 * @return Returns the wpxh.
	 */
	public String getWpxh() {
		return wpxh;
	}
	/**
	 * @param wpxh The wpxh to set.
	 */
	public void setWpxh(String wpxh) {
		this.wpxh = wpxh;
	}
}

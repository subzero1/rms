package com.rms.dataObjects.gcjs;

import java.io.Serializable;

/**
 * 
 * @author wlx 2010-04-15
 * @hibernate.class table="Te03_gcgys_bhz"
 */
public class Te03_gcgys_bhz implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 545302013071280939L;
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
	private String xh;
	/**
	 * 表单编号
	 */
	private String bgbh;
	/**
	 * 费用名称
	 */
	private String fymc;
	/**
	 * 建筑工程费
	 */
	private String jzgcf;
	/**
	 * 需要安装的设备费
	 */
	private String xasbf;
	/**
	 * 建筑安装工程费用
	 */
	private String azgcf;
	/**
	 * 不需要安装的设备费用
	 */
	private String bxasbf;
	/**
	 * 其它费用
	 */
	private String qtfy;
	/**
	 * 预备费用
	 */
	private String ybf;
	/**
	 * 人民币
	 */
	private String rmbzj;
	/**
	 * 外币
	 */
	private String wbzj;
	/**
	 * 生产准备及开办费
	 */
	private String kbf;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TE03BHZNUM;"
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
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
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
	 * @hibernate.property column="jzgcf"
	 * @return jzgcf
	 */
	public String getJzgcf() {
		return jzgcf;
	}
	/**
	 * @param jzgcf the jzgcf to set
	 */
	public void setJzgcf(String jzgcf) {
		this.jzgcf = jzgcf;
	}
	/**
	 * @hibernate.property column="xasbf"
	 * @return xasbf
	 */
	public String getXasbf() {
		return xasbf;
	}
	/**
	 * @param xasbf the xasbf to set
	 */
	public void setXasbf(String xasbf) {
		this.xasbf = xasbf;
	}
	/**
	 * @hibernate.property column="azgcf"
	 * @return azgcf
	 */
	public String getAzgcf() {
		return azgcf;
	}
	/**
	 * @param azgcf the azgcf to set
	 */
	public void setAzgcf(String azgcf) {
		this.azgcf = azgcf;
	}
	/**
	 * @hibernate.property column="bxasbf"
	 * @return bxasbf
	 */
	public String getBxasbf() {
		return bxasbf;
	}
	/**
	 * @param bxasbf the bxasbf to set
	 */
	public void setBxasbf(String bxasbf) {
		this.bxasbf = bxasbf;
	}
	/**
	 * @hibernate.property column="qtfy"
	 * @return qtfy
	 */
	public String getQtfy() {
		return qtfy;
	}
	/**
	 * @param qtfy the qtfy to set
	 */
	public void setQtfy(String qtfy) {
		this.qtfy = qtfy;
	}
	/**
	 * @hibernate.property column="ybf"
	 * @return ybf
	 */
	public String getYbf() {
		return ybf;
	}
	/**
	 * @param ybf the ybf to set
	 */
	public void setYbf(String ybf) {
		this.ybf = ybf;
	}
	/**
	 * @hibernate.property column="rmbzj"
	 * @return rmbzj
	 */
	public String getRmbzj() {
		return rmbzj;
	}
	/**
	 * @param rmbzj the rmbzj to set
	 */
	public void setRmbzj(String rmbzj) {
		this.rmbzj = rmbzj;
	}
	/**
	 * @hibernate.property column="wbzj"
	 * @return wbzj
	 */
	public String getWbzj() {
		return wbzj;
	}
	/**
	 * @param wbzj the wbzj to set
	 */
	public void setWbzj(String wbzj) {
		this.wbzj = wbzj;
	}
	/**
	 * @hibernate.property column="kbf"
	 * @return kbf
	 */
	public String getKbf() {
		return kbf;
	}
	/**
	 * @param wbzj the kbf to set
	 */
	public void setKbf(String kbf) {
		this.kbf = kbf;
	}
}

package com.rms.dataObjects.gcjs;

import java.io.Serializable;
import java.util.Date;

/**
 * 工程预算--综合信息
 * @author wlx 2010-04-15
 * @hibernate.class table="Te03_gcgys_zhxx"
 */
public class Te03_gcgys_zhxx implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5033818974880853840L;
	/**
	 * id
	 */
	private Long id ;
	/**
	 * 工程ID
	 */
	private Long gc_id;
	/**
	 * 建设项目名称
	 */
	private String jsxmmc;
	/**
	 * 单项工程名称
	 */
	private String dxgcmc;
	/**
	 * 建设单位
	 */
	private String jsdw;
	/**
	 * 表格编号
	 */
	private String bgbh;
	/**
	 * 设计负责人
	 */
	private String sjfzr;
	/**
	 * 审核人
	 */
	private String shr;
	/**
	 * 编制人
	 */
	private String bzr;
	/**
	 * 编制日期
	 */
	private Date bzrq;
	/**
	 * 工程总投资 
	 */
	private Double gcztz;
	/**
	 * 技工工日 
	 */
	private Double jggr;
	/**
	 * 普工工日 
	 */
	private Double pggr;
	/**
	 * 人工费 
	 */
	private Double rgf;
	/**
	 * 材料费 
	 */
	private Double clf;
	/**
	 * 设计费 
	 */
	private Double sjf;
	/**
	 * 表格设置
	 */
	private String bgsz;
	/**
	 * 概预算证号
	 */
	private String gyszh;
	/**
	 * 
	 */
	private String zzdj;
	/**
	 * 设备费
	 */
	private Double sbf;
	/**
	 * 机械费
	 */
	private Double jxf;
	/**
	 * 建筑安装工程费
	 */
	private Double jzazgcf;
	/**
	 * 工程建设其它费
	 */
	private Double gcjsqtf;
	/**
	 * 
	 */
	private String gys_flag;
	/**
	 * 仪表费
	 */
	private Double ybf;
	/**
	 * 监理费
	 */
	private Double jlf;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TE03ZHXXNUM;;"
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
	 * @hibernate.property column="jsxmmc"
	 * @return jsxmmc
	 */
	public String getJsxmmc() {
		return jsxmmc;
	}
	/**
	 * @param jsxmmc the jsxmmc to set
	 */
	public void setJsxmmc(String jsxmmc) {
		this.jsxmmc = jsxmmc;
	}
	/**
	 * @hibernate.property column="dxgcmc"
	 * @return dxgcmc
	 */
	public String getDxgcmc() {
		return dxgcmc;
	}
	/**
	 * @param dxgcmc the dxgcmc to set
	 */
	public void setDxgcmc(String dxgcmc) {
		this.dxgcmc = dxgcmc;
	}
	/**
	 * @hibernate.property column="jsdw"
	 * @return jsdw
	 */
	public String getJsdw() {
		return jsdw;
	}
	/**
	 * @param jsdw the jsdw to set
	 */
	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
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
	 * @hibernate.property column="sjfzr"
	 * @return sjfzr
	 */
	public String getSjfzr() {
		return sjfzr;
	}
	/**
	 * @param sjfzr the sjfzr to set
	 */
	public void setSjfzr(String sjfzr) {
		this.sjfzr = sjfzr;
	}
	/**
	 * @hibernate.property column="shr"
	 * @return shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shr the shr to set
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @hibernate.property column="bzr"
	 * @return bzr
	 */
	public String getBzr() {
		return bzr;
	}
	/**
	 * @param bzr the bzr to set
	 */
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	/**
	 * @hibernate.property column="bzrq"
	 * @return bzrq
	 */
	public Date getBzrq() {
		return bzrq;
	}
	/**
	 * @param bzrq the bzrq to set
	 */
	public void setBzrq(Date bzrq) {
		this.bzrq = bzrq;
	}
	/**
	 * @hibernate.property column="gcztz"
	 * @return gcztz
	 */
	public Double getGcztz() {
		return gcztz;
	}
	/**
	 * @param gcztz the gcztz to set
	 */
	public void setGcztz(Double gcztz) {
		this.gcztz = gcztz;
	}
	/**
	 * @hibernate.property column="jggr"
	 * @return jggr
	 */
	public Double getJggr() {
		return jggr;
	}
	/**
	 * @param jggr the jggr to set
	 */
	public void setJggr(Double jggr) {
		this.jggr = jggr;
	}
	/**
	 * @hibernate.property column="pggr"
	 * @return pggr
	 */
	public Double getPggr() {
		return pggr;
	}
	/**
	 * @param pggr the pggr to set
	 */
	public void setPggr(Double pggr) {
		this.pggr = pggr;
	}
	/**
	 * @hibernate.property column="rgf"
	 * @return rgf
	 */
	public Double getRgf() {
		return rgf;
	}
	/**
	 * @param rgf the rgf to set
	 */
	public void setRgf(Double rgf) {
		this.rgf = rgf;
	}
	/**
	 * @hibernate.property column="clf"
	 * @return clf
	 */
	public Double getClf() {
		return clf;
	}
	/**
	 * @param clf the clf to set
	 */
	public void setClf(Double clf) {
		this.clf = clf;
	}
	/**
	 * @hibernate.property column="sjf"
	 * @return sjf
	 */
	public Double getSjf() {
		return sjf;
	}
	/**
	 * @param sjf the sjf to set
	 */
	public void setSjf(Double sjf) {
		this.sjf = sjf;
	}
	/**
	 * @hibernate.property column="bgsz"
	 * @return bgsz
	 */
	public String getBgsz() {
		return bgsz;
	}
	/**
	 * @param bgsz the bgsz to set
	 */
	public void setBgsz(String bgsz) {
		this.bgsz = bgsz;
	}
	/**
	 * @hibernate.property column="gyszh"
	 * @return gyszh
	 */
	public String getGyszh() {
		return gyszh;
	}
	/**
	 * @param gyszh the gyszh to set
	 */
	public void setGyszh(String gyszh) {
		this.gyszh = gyszh;
	}
	/**
	 * @hibernate.property column="zzdj"
	 * @return zzdj
	 */
	public String getZzdj() {
		return zzdj;
	}
	/**
	 * @param zzdj the zzdj to set
	 */
	public void setZzdj(String zzdj) {
		this.zzdj = zzdj;
	}
	/**
	 * @hibernate.property column="sbf"
	 * @return sbf
	 */
	public Double getSbf() {
		return sbf;
	}
	/**
	 * @param sbf the sbf to set
	 */
	public void setSbf(Double sbf) {
		this.sbf = sbf;
	}
	/**
	 * @hibernate.property column="jxf"
	 * @return jxf
	 */
	public Double getJxf() {
		return jxf;
	}
	/**
	 * @param jxf the jxf to set
	 */
	public void setJxf(Double jxf) {
		this.jxf = jxf;
	}
	/**
	 * @hibernate.property column="jzazgcf"
	 * @return jzazgcf
	 */
	public Double getJzazgcf() {
		return jzazgcf;
	}
	/**
	 * @param jzazgcf the jzazgcf to set
	 */
	public void setJzazgcf(Double jzazgcf) {
		this.jzazgcf = jzazgcf;
	}
	/**
	 * @hibernate.property column="gcjsqtf"
	 * @return gcjsqtf
	 */
	public Double getGcjsqtf() {
		return gcjsqtf;
	}
	/**
	 * @param gcjsqtf the gcjsqtf to set
	 */
	public void setGcjsqtf(Double gcjsqtf) {
		this.gcjsqtf = gcjsqtf;
	}
	/**
	 * @hibernate.property column="gys_flag"
	 * @return gys_flag
	 */
	public String getGys_flag() {
		return gys_flag;
	}
	/**
	 * @param gys_flag the gys_flag to set
	 */
	public void setGys_flag(String gys_flag) {
		this.gys_flag = gys_flag;
	}
	/**
	 * @hibernate.property column="ybf"
	 * @return ybf
	 */
	public Double getYbf() {
		return ybf;
	}
	/**
	 * @param ybf
	 */
	public void setYbf(Double ybf) {
		this.ybf = ybf;
	}
	/**
	 * @hibernate.property column="jlf"
	 * @return jlf
	 */
	public Double getJlf() {
		return jlf;
	}
	/**
	 * @param jlf
	 */
	public void setJlf(Double jlf) {
		this.jlf = jlf;
	}

}

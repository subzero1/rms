package com.jfms.dataObjects.info;

import java.util.Date;

/**
 * @description: 关联机房表
 * @class name:com.jfms.dataObjects.info.Td12_gljf
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Td12_gljf"
 */
public class Td12_gljf {
	/**
	 *
	 */
	private Long id;

	/**
	 *
	 */
	private Long project_id;
	
	/**
	 *申请单标识
	 */
	private Long parent_id;

	/**
	 *机房标识
	 */
	private Long jf_id;

	/**
	 *安装局点名称
	 */
	private String jdmc;

	/**
	 *拟使用机房
	 */
	private String jfmc;

	/**
	 *新增机架数量
	 */
	private Long xzjjsl;

	/**
	 *机架尺寸
	 */
	private String jjcc;

	/**
	 *设备供电方式
	 */
	private String sbgdfs;

	/**
	 *备注
	 */
	private String bz;

	/**
	 *下载时间
	 */
	private Date xzsj;

	/**
	 *下载人
	 */
	//private String xzr;
	
	/**
	 *下载人
	 */
	private String xzrdh;

	/**
	 *最后更新现状图时间
	 */
	private Date zhgxsj;
	
	/**
	 *上传时间
	 */
	private Date scsj;

	/**
	 * @hibernate.property column="jf_id"
	 * @return Returns the jf_id.
	 */
	public Long getJf_id() {
		return jf_id;
	}

	/**
	 * @param jf_id The jf_id to set.
	 */
	public void setJf_id(Long jf_id) {
		this.jf_id = jf_id;
	}

	/**
	 * @hibernate.property column="xzsj"
	 * @return Returns the xzsj.
	 */
	public Date getXzsj() {
		return xzsj;
	}

	/**
	 * @param xzsj The xzsj to set.
	 */
	public void setXzsj(Date xzsj) {
		this.xzsj = xzsj;
	}

	/**
	 * @hibernate.property column="xzr"
	 * @return Returns the xzr.
	 
	public String getXzr() {
		return xzr;
	}
	 */
	/**
	 * @param xzr The xzr to set.
	 
	public void setXzr(String xzr) {
		this.xzr = xzr;
	}
	*/
	
	/**
	 * @hibernate.property column="xzrdh"
	 * @return Returns the xzrdh.
	 */
	public String getXzrdh() {
		return xzrdh;
	}

	public void setXzrdh(String xzrdh) {
		this.xzrdh = xzrdh;
	}

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Td11_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="project_id"
	 * @return the project_id
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id the project_id to set
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}
	
	/**
	 * @hibernate.property column="parent_id"
	 * @return the project_id
	 */
	public Long getParent_id() {
		return parent_id;
	}

	/**
	 * @param parent_id the parent_id to set
	 */
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	/**
	 * @hibernate.property column="jdmc"
	 * @return Returns the jdmc.
	 */
	public String getJdmc() {
		return jdmc;
	}

	/**
	 * @param jdmc
	 *            The jdmc to set.
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	/**
	 * @hibernate.property column="jfmc"
	 * @return Returns the jfmc.
	 */
	public String getJfmc() {
		return jfmc;
	}

	/**
	 * @param jfmc
	 *            The jfmc to set.
	 */
	public void setJfmc(String jfmc) {
		this.jfmc = jfmc;
	}

	/**
	 * @hibernate.property column="xzjjsl"
	 * @return Returns the xzjjsl.
	 */
	public Long getXzjjsl() {
		return xzjjsl;
	}

	/**
	 * @param xzjjsl
	 *            The xzjjsl to set.
	 */
	public void setXzjjsl(Long xzjjsl) {
		this.xzjjsl = xzjjsl;
	}

	/**
	 * @hibernate.property column="jjcc"
	 * @return Returns the jjcc.
	 */
	public String getJjcc() {
		return jjcc;
	}

	/**
	 * @param jjcc
	 *            The jjcc to set.
	 */
	public void setJjcc(String jjcc) {
		this.jjcc = jjcc;
	}

	/**
	 * @hibernate.property column="sbgdfs"
	 * @return Returns the sbgdfs.
	 */
	public String getSbgdfs() {
		return sbgdfs;
	}

	/**
	 * @param sbgdfs
	 *            The sbgdfs to set.
	 */
	public void setSbgdfs(String sbgdfs) {
		this.sbgdfs = sbgdfs;
	}

	/**
	 * @hibernate.property column="bz"
	 * @return Returns the bz.
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz
	 * The bz to set.
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	/**
	 * @hibernate.property column="zhgxsj"
	 * @return Returns the zhgxsj.
	 */
	public Date getZhgxsj() {
		return zhgxsj;
	}

	/**
	 * @param zhgxsj The zhgxsj to set.
	 */
	public void setZhgxsj(Date zhgxsj) {
		this.zhgxsj = zhgxsj;
	}
	
	/**
	 * @hibernate.property column="scsj"
	 * @return Returns the scsj.
	 */
	public Date getScsj() {
		return scsj;
	}

	/**
	 * @param scsj The scsj to set.
	 */
	public void setScsj(Date scsj) {
		this.scsj = scsj;
	}
}

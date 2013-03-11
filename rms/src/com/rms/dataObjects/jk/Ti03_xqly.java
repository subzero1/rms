package com.rms.dataObjects.jk;

/**
 * @description:
 * 需求来源
 * @class name:com.rms.dataObjects.jk.Ti03_xqly
 * @author Administrator May 27, 2010
 * @hibernate.class table="Ti03_xqly"
 */
public class Ti03_xqly {
	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 项目标识
	 */
	private Long project_id;
	
	/**
	 * 需求来源 系统
	 */
	private String lyxt;
	
	/**
	 *需求在其它的系统的标识，例如来源于调度系统，在此存工单号
	 */
	private String xqbs;
	
	/**
	 * 需求在其它系统中关联信息url地址
	 */
	private String url;
	
	/**
	 * 备注
	 */
	private String bz;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="ti03_num"
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
	 * @hibernate.property column="project_id"
	 * @return Returns the project_id.
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id The project_id to set.
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="lyxt"
	 * @return Returns the lyxt.
	 */
	public String getLyxt() {
		return lyxt;
	}

	/**
	 * @param lyxt The lyxt to set.
	 */
	public void setLyxt(String lyxt) {
		this.lyxt = lyxt;
	}

	/**
	 * @hibernate.property column="xqbs"
	 * @return Returns the xqbs.
	 */
	public String getXqbs() {
		return xqbs;
	}

	/**
	 * @param xqbs The xqbs to set.
	 */
	public void setXqbs(String xqbs) {
		this.xqbs = xqbs;
	}

	/**
	 * @hibernate.property column="url"
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @hibernate.property column="bz"
	 * @return Returns the bz.
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz The bz to set.
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
}

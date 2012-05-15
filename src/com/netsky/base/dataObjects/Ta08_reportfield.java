package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author Chiang
 * @hibernate.class table="Ta08_reportfield" 报表字段表
 */
public class Ta08_reportfield implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1525477327960094330L;
	/**
	 * ID标识
	 */
	private Long id;
	/**
	 * 属性名称
	 */
	private String name;
	/**
	 * 属性注释
	 */
	private String comments;
	/**
	 * 属性数据类型
	 */
	private String datatype;
	/**
	 * 名称含包名
	 */
	private String object_name;
	/**
	 * 查询类型 1：关键字； 2：复选 ；3：金额 ；4：人员； 5：日期
	 */
	private Integer searchtype;
	/**
	 * 查询标志位 1：查询条件
	 */
	private Integer searchflag;
	/**
	 * 统计标志位 1：统计条件
	 */
	private Integer reportflag;
	/**
	 * 统计选择项 1：选择项
	 */
	private Integer statisticflag;
	/**
	 * 选择路径，当SEARCHTYPE＝2时有效
	 */
	private String selecturl;
	/**
	 * 综合报表查询列宽度
	 */
	private Integer width;
	/**
	 * 综合报表查询列对齐方式
	 */
	private String align;
	/**
	 * 字段显示顺序
	 */
	private Integer ord;

	/**
	 * 默认查询显示字段
	 */
	private Integer showflag;

	/**
	 * 类型
	 */
	private Long module_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta08_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="name"
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="comments"
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @hibernate.property column="datatype"
	 * @return the datatype
	 */
	public String getDatatype() {
		return datatype;
	}

	/**
	 * @param datatype
	 *            the datatype to set
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	/**
	 * @hibernate.property column="object_name"
	 * @return the object_name
	 */
	public String getObject_name() {
		return object_name;
	}

	/**
	 * @param object_name
	 *            the object_name to set
	 */
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	/**
	 * @hibernate.property column="searchtype"
	 * @return the searchtype
	 */
	public Integer getSearchtype() {
		return searchtype;
	}

	/**
	 * @param searchtype
	 *            the searchtype to set
	 */
	public void setSearchtype(Integer searchtype) {
		this.searchtype = searchtype;
	}

	/**
	 * @hibernate.property column="searchflag"
	 * @return the searchflag
	 */
	public Integer getSearchflag() {
		return searchflag;
	}

	/**
	 * @param searchflag
	 *            the searchflag to set
	 */
	public void setSearchflag(Integer searchflag) {
		this.searchflag = searchflag;
	}

	/**
	 * @hibernate.property column="reportflag"
	 * @return the reportflag
	 */
	public Integer getReportflag() {
		return reportflag;
	}

	/**
	 * @param reportflag
	 *            the reportflag to set
	 */
	public void setReportflag(Integer reportflag) {
		this.reportflag = reportflag;
	}

	/**
	 * @hibernate.property column="statisticflag"
	 * @return the statisticflag
	 */
	public Integer getStatisticflag() {
		return statisticflag;
	}

	/**
	 * @param statisticflag
	 *            the statisticflag to set
	 */
	public void setStatisticflag(Integer statisticflag) {
		this.statisticflag = statisticflag;
	}

	/**
	 * @hibernate.property column="selecturl"
	 * @return the selecturl
	 */
	public String getSelecturl() {
		return selecturl;
	}

	/**
	 * @param selecturl
	 *            the selecturl to set
	 */
	public void setSelecturl(String selecturl) {
		this.selecturl = selecturl;
	}

	/**
	 * @hibernate.property column="width"
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @hibernate.property column="align"
	 * @return the align
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * @param align
	 *            the align to set
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * @hibernate.property column="ord"
	 * @return the ord
	 */
	public Integer getOrd() {
		return ord;
	}

	/**
	 * @param ord
	 *            the ord to set
	 */
	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	/**
	 * @hibernate.property column="showflag"
	 * @return the showflag
	 */
	public Integer getShowflag() {
		return showflag;
	}

	/**
	 * @param showflag
	 *            the showflag to set
	 */
	public void setShowflag(Integer showflag) {
		this.showflag = showflag;
	}

	/**
	 * @hibernate.property column="module_id"
	 * @return the module_id
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id
	 *            the module_id to set
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}

}

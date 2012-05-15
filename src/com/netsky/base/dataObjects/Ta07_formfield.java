package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta07_formfield"
 */

public class Ta07_formfield implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2501043900429289605L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 模块ID
	 */
	private Long module_id;

	/**
	 * dataObject 属性名称
	 */
	private String name;

	/**
	 * dataObject 属性注释
	 */
	private String comments;

	/**
	 * dataObject 属性数据类型
	 */
	private String datatype;

	/**
	 * dataObject 属性长度；如：12.2 表示number(12,2)，10表示number(10)或varchar2(10)
	 */
	private Double datalength;

	/**
	 * N为非空，Y为空
	 */
	private String nullable;

	/**
	 * dataObject 名称含包名
	 */
	private String object_name;

	/**
	 * 表单查询列宽度
	 */
	private Integer width;

	/**
	 * 表单查询列对齐方式
	 */
	private String align;

	/**
	 * 默认显示列：1显示，0不显示
	 */
	private Integer show_flag;

	/**
	 * 查询列：1显示，0不显示
	 */
	private Integer search_flag;

	/**
	 * 排序列：1显示，0不显示
	 */
	private Integer order_flag;

	/**
	 * 日期查询列：1显示，0不显示
	 */
	private Integer date_flag;

	/**
	 * 显示顺序
	 */
	private Integer ord;
	
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta07_NUM"
	 * @return Returns the id.
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="module_id"
	 * @return the module_id
	 */
	public Long getModule_id() {
		return module_id;
	}

	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}

	/**
	 * @hibernate.property column="name"
	 * @return the name
	 */
	public String getName() {
		return name;
	}

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

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	/**
	 * @hibernate.property column="datalength"
	 * @return the datalength
	 */
	public Double getDatalength() {
		return datalength;
	}

	public void setDatalength(Double datalength) {
		this.datalength = datalength;
	}

	/**
	 * @hibernate.property column="nullable"
	 * @return the nullable
	 */
	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	/**
	 * @hibernate.property column="object_name"
	 * @return the object_name
	 */
	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
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
	 * @hibernate.property column="show_flag"
	 * @return the show_flag
	 */
	public Integer getShow_flag() {
		return show_flag;
	}

	/**
	 * @param show_flag
	 *            the show_flag to set
	 */
	public void setShow_flag(Integer show_flag) {
		this.show_flag = show_flag;
	}

	/**
	 * @hibernate.property column="search_flag"
	 * @return the search_flag
	 */
	public Integer getSearch_flag() {
		return search_flag;
	}

	/**
	 * @param search_flag
	 *            the search_flag to set
	 */
	public void setSearch_flag(Integer search_flag) {
		this.search_flag = search_flag;
	}

	/**
	 * @hibernate.property column="order_flag"
	 * @return the order_flag
	 */
	public Integer getOrder_flag() {
		return order_flag;
	}

	/**
	 * @param order_flag
	 *            the order_flag to set
	 */
	public void setOrder_flag(Integer order_flag) {
		this.order_flag = order_flag;
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
	 * @hibernate.property column="date_flag"
	 * @return the date_flag
	 */
	public Integer getDate_flag() {
		return date_flag;
	}

	/**
	 * @param date_flag
	 *            the date_flag to set
	 */
	public void setDate_flag(Integer date_flag) {
		this.date_flag = date_flag;
	}

}

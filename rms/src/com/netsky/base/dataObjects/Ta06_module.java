package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta06_module"
 */
public class Ta06_module implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3041632239853822893L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 模块类型：1：表单；2：主菜单；3：其他
	 */
	private Long type;

	/**
	 * 项目信息表
	 */
	private String project_table;

	/**
	 * 备注
	 */
	private String form_table;

	/**
	 * 表单url
	 */
	private String form_url;

	/**
	 * 表单建成、编号前缀
	 */
	private String form_name;

	/**
	 * 表单图片
	 */
	private String form_pic;

	/**
	 * 表单附件 ta06.id
	 */
	private String slave_module;

	/**
	 * project_id 关联表
	 */
	private String aux_table;

	/**
	 * 主表从表
	 */
	private String detail_table;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta06_NUM"
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
	 * @hibernate.property column="type"
	 * @return the type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/**
	 * @hibernate.property column="project_table"
	 * @return the project_table
	 */
	public String getProject_table() {
		return project_table;
	}

	/**
	 * @param project_table
	 *            the project_table to set
	 */
	public void setProject_table(String project_table) {
		this.project_table = project_table;
	}

	/**
	 * @hibernate.property column="form_table"
	 * @return the form_table
	 */
	public String getForm_table() {
		return form_table;
	}

	/**
	 * @param form_table
	 *            the form_table to set
	 */
	public void setForm_table(String form_table) {
		this.form_table = form_table;
	}

	/**
	 * @hibernate.property column="form_url"
	 * @return the form_url
	 */
	public String getForm_url() {
		return form_url;
	}

	/**
	 * @param form_url
	 *            the form_url to set
	 */
	public void setForm_url(String form_url) {
		this.form_url = form_url;
	}

	/**
	 * @hibernate.property column="form_name"
	 * @return the form_name
	 */
	public String getForm_name() {
		return form_name;
	}

	/**
	 * @param form_name
	 *            the form_name to set
	 */
	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}

	/**
	 * @hibernate.property column="form_pic"
	 * @return the form_pic
	 */
	public String getForm_pic() {
		return form_pic;
	}

	/**
	 * @param form_pic
	 *            the form_pic to set
	 */
	public void setForm_pic(String form_pic) {
		this.form_pic = form_pic;
	}

	/**
	 * @hibernate.property column="slave_module"
	 * @return the slave_module
	 */
	public String getSlave_module() {
		return slave_module;
	}

	/**
	 * @param slave_module
	 *            the slave_module to set
	 */
	public void setSlave_module(String slave_module) {
		this.slave_module = slave_module;
	}

	/**
	 * @hibernate.property column="detail_table"
	 * @return the detail_table
	 */
	public String getDetail_table() {
		return detail_table;
	}

	/**
	 * @param detail_table
	 *            the detail_table to set
	 */
	public void setDetail_table(String detail_table) {
		this.detail_table = detail_table;
	}

	/**
	 * @hibernate.property column="aux_table"
	 * @return the aux_table
	 */
	public String getAux_table() {
		return aux_table;
	}

	/**
	 * @param aux_table
	 *            the aux_table to set
	 */
	public void setAux_table(String aux_table) {
		this.aux_table = aux_table;
	}

}

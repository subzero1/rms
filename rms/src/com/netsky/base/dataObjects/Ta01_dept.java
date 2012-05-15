package com.netsky.base.dataObjects;

import java.io.Serializable;

import com.netsky.base.annotation.LogDiaryType;
import com.netsky.base.annotation.LogDiaryField;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta01_dept"
 */
@LogDiaryType("部门表")
public class Ta01_dept implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4053790773482957288L;

	/**
	 * 标识
	 */
	@LogDiaryField
	private Long id;

	/**
	 * 上一级部门标识
	 */
	@LogDiaryField
	private Long up_id;

	/**
	 * 名称
	 */
	@LogDiaryField
	private String name;

	/**
	 * 部门编码
	 */
	@LogDiaryField
	private String code;

	/**
	 * 备注
	 */
	@LogDiaryField
	private String remark;

	/**
	 * 0:不可用；1：可用
	 */
	@LogDiaryField
	private Long useflag;

	/**
	 * 所属地区
	 */
	@LogDiaryField
	private String area_name;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta01_NUM"
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
	 * @hibernate.property column="up_id"
	 * @return the up_id
	 */
	public Long getUp_id() {
		return up_id;
	}

	/**
	 * @param up_id
	 *            the up_id to set
	 */
	public void setUp_id(Long up_id) {
		this.up_id = up_id;
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
	 * @hibernate.property column="code"
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @hibernate.property column="remark"
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.property column="useflag"
	 * @return the useflag
	 */
	public Long getUseflag() {
		return useflag;
	}

	/**
	 * @param useflag
	 *            the useflag to set
	 */
	public void setUseflag(Long useflag) {
		this.useflag = useflag;
	}

	/**
	 * @hibernate.property column="area_name"
	 * @return the area_name
	 */
	public String getArea_name() {
		return area_name;
	}

	/**
	 * @param area_name
	 *            the area_name to set
	 */
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

}

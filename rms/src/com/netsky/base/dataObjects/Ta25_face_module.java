package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @hibernate.class table="Ta25_face_module"
 * @author CT
 * @create 2010-03-31
 */
public class Ta25_face_module implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5827403323552499446L;

	/**
	 * id
	 */
	private Long id;

	private String module_name;
	/**
	 * 角色id
	 */
	private Long role_id;
	/**
	 * 路径
	 */
	private String module_url;
	/**
	 * 界面类型 1，左下；2，右下
	 */
	private Integer ui_type;
	
	/**
	 * @hibernate.property column="ui_type"
	 * @return ui_type
	 */
	public Integer getUi_type() {
		return ui_type;
	}
	/** 
	 * @param ui_type
	 */
	public void setUi_type(Integer ui_type) {
		this.ui_type = ui_type;
	}
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta25_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @hibernate.property column="module_name"
	 * @return module_name
	 */
	public String getModule_name() {
		return module_name;
	}
	/**
	 * @param module_name
	 */
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	/**
	 * @hibernate.property column="module_url"
	 * @return module_url
	 */
	public String getModule_url() {
		return module_url;
	}
	/**
	 * @param module_url
	 */
	public void setModule_url(String module_url) {
		this.module_url = module_url;
	}
	/**
	 * @hibernate.property column="role_id"
	 * @return role_id
	 */
	public Long getRole_id() {
		return role_id;
	}
	/**
	 * @param role_id
	 */
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	
	

}

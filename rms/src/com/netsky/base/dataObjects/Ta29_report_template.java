package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * 报表模板
 * 
 * @author Chiang
 * 
 * @hibernate.class table="Ta29_report_template"
 */
public class Ta29_report_template implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -23644160352185209L;

	private Long id;

	/**
	 * 模板类型 1：工程报表，2：工程查询
	 */
	private Integer type;

	private Long user_id;

	private String user_name;

	private String name;
	/**
	 * 类型
	 */
	private Long module_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta29_NUM"
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
	 * @hibernate.property column="type"
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @hibernate.property column="user_id"
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @hibernate.property column="user_name"
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name
	 *            the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

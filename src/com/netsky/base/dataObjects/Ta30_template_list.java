package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * 报表模板
 * 
 * @author Chiang
 * 
 * @hibernate.class table="Ta30_template_list"
 */
public class Ta30_template_list implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2600317409846673279L;

	private Long id;

	private Long parent_id;

	private String type;

	private String key;

	private String value;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta30_NUM"
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
	 * @hibernate.property column="parent_id"
	 * @return the parent_id
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
	 * @hibernate.property column="type"
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @hibernate.property column="key"
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @hibernate.property column="value"
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}

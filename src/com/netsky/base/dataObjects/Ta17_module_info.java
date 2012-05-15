package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author Chiang 2009-12-11
 * @hibernate.class table="Ta17_module_info"
 */

public class Ta17_module_info implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5487895527684012930L;

	private Long id;
	
	private Long module_id;
	
	private String type;
	
	private String object_name;
	
	private String object_column;
	
	private String relevance_table;
	
	private String relevance_column;
	
	private String show_column;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta17_NUM"
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
	 * @hibernate.property column="module_id"
	 * @return the module_id
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id the module_id to set
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
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
	 * @hibernate.property column="object_name"
	 * @return the object_name
	 */
	public String getObject_name() {
		return object_name;
	}

	/**
	 * @param object_name the object_name to set
	 */
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	/**
	 * @hibernate.property column="object_column"
	 * @return the object_column
	 */
	public String getObject_column() {
		return object_column;
	}

	/**
	 * @param object_column the object_column to set
	 */
	public void setObject_column(String object_column) {
		this.object_column = object_column;
	}

	/**
	 * @hibernate.property column="relevance_table"
	 * @return the relevance_table
	 */
	public String getRelevance_table() {
		return relevance_table;
	}

	/**
	 * @param relevance_table the relevance_table to set
	 */
	public void setRelevance_table(String relevance_table) {
		this.relevance_table = relevance_table;
	}

	/**
	 * @hibernate.property column="relevance_column"
	 * @return the relevance_column
	 */
	public String getRelevance_column() {
		return relevance_column;
	}

	/**
	 * @param relevance_column the relevance_column to set
	 */
	public void setRelevance_column(String relevance_column) {
		this.relevance_column = relevance_column;
	}

	/**
	 * @hibernate.property column="show_column"
	 * @return the show_column
	 */
	public String getShow_column() {
		return show_column;
	}

	/**
	 * @param show_column the show_column to set
	 */
	public void setShow_column(String show_column) {
		this.show_column = show_column;
	}
	
	
}

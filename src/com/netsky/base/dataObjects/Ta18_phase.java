package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author lee.xiangyu 2009-12-11
 * @hibernate.class table="Ta18_phase"
 */

public class Ta18_phase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3694718270283697007L;

	private Long id;
	
	private String name;
	
	private Long seq;
	
	private String module_ids;
	
	private String node_names;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta18_NUM"
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
	 * @hibernate.property column="name"
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the object_column to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="seq"
	 * @return the seq
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * @param relevance_table the seq to set
	 */
	public void setSeq(Long seq) {
		this.seq = seq;
	}

	/**
	 * @hibernate.property column="module_ids"
	 * @return the module_ids
	 */
	public String getModule_ids() {
		return module_ids;
	}

	/**
	 * @param relevance_table the module_ids to set
	 */
	public void setModule_ids(String module_ids) {
		this.module_ids = module_ids;
	}

	/**
	 * @hibernate.property column="node_names"
	 * @return the node_names
	 */
	public String getNode_names() {
		return node_names;
	}

	/**
	 * @param relevance_table the node_names to set
	 */
	public void setNode_names(String node_names) {
		this.node_names = node_names;
	}
	
	
}


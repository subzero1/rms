package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta16_node_field"
 */

public class Ta16_node_field implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -568982641617623325L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 节点标识tb02.id
	 */
	private Long node_id;
	
	/**
	 * 字段标识ta07.id
	 */
	private Long field_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta16_NUM"
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
	 * @hibernate.property column="node_id"
	 * @return the node_id
	 */
	public Long getNode_id() {
		return node_id;
	}

	/**
	 * @param node_id the node_id to set
	 */
	public void setNode_id(Long node_id) {
		this.node_id = node_id;
	}

	/**
	 * @hibernate.property column="field_id"
	 * @return the field_id
	 */
	public Long getField_id() {
		return field_id;
	}

	/**
	 * @param field_id the field_id to set
	 */
	public void setField_id(Long field_id) {
		this.field_id = field_id;
	}
	
	

}

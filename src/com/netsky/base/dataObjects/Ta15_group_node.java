package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta15_group_node"
 */
public class Ta15_group_node implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2227597673700342948L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 群组标识ta05.id
	 */
	private Long group_id;
	
	/**
	 * 节点标识tb02.id
	 */
	private Long node_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta15_NUM"
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
	 * @hibernate.property column="group_id"
	 * @return the group_id
	 */
	public Long getGroup_id() {
		return group_id;
	}

	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
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
	
	

}

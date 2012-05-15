package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author lee.xiangyu 2009-12-11
 * @hibernate.class table="Ta20_phase_node"
 */

public class Ta20_phase_node implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6355932561451868186L;

	private Long id;
	
	private Long flow_id;
	
	private Long create_node_id;
	
	private Long config_node_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta20_NUM"
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
	 * @hibernate.property column="flow_id"
	 * @return the flow_id
	 */
	public Long getFlow_id() {
		return flow_id;
	}

	/**
	 * @param relevance_table the flow_id to set
	 */
	public void setFlow_id(Long flow_id) {
		this.flow_id = flow_id;
	}

	/**
	 * @hibernate.property column="create_node_id"
	 * @return the create_node_id
	 */
	public Long getCreate_node_id() {
		return create_node_id;
	}

	/**
	 * @param relevance_table the create_node_id to set
	 */
	public void setCreate_node_id(Long create_node_id) {
		this.create_node_id = create_node_id;
	}
	
	/**
	 * @hibernate.property column="config_node_id"
	 * @return the config_node_id
	 */
	public Long getConfig_node_id() {
		return config_node_id;
	}

	/**
	 * @param relevance_table the config_node_id to set
	 */
	public void setConfig_node_id(Long config_node_id) {
		this.config_node_id = config_node_id;
	}
}


package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta13_sta_node"
 */
public class Ta13_sta_node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5682347888904268882L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 岗位标识ta02.id
	 */
	private Long station_id;
	
	/**
	 * 节点标识tb02.id
	 */
	private Long node_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta13_NUM"
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
	 * @hibernate.property column="station_id"
	 * @return the station_id
	 */
	public Long getStation_id() {
		return station_id;
	}

	/**
	 * @param station_id the station_id to set
	 */
	public void setStation_id(Long station_id) {
		this.station_id = station_id;
	}

	/**
	 * @hibernate.property column="node_id"
	 * @return the node_id
	 */
	public Long getNode_id() {
		return node_id;
	}

	/**
	 * @param role_id the role_id to set
	 */
	public void setNode_id(Long node_id) {
		this.node_id = node_id;
	}
}

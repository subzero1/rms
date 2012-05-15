package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author lee.xiangyu 2009-12-11
 * @hibernate.class table="Ta19_flow_phase"
 */

public class Ta19_flow_phase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -270060101445865679L;

	private Long id;
	
	private Long flow_id;
	
	private Long phase_id;
	
	private String isstart;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta19_NUM"
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
	 * @hibernate.property column="phase_id"
	 * @return the phase_id
	 */
	public Long getPhase_id() {
		return phase_id;
	}

	/**
	 * @param relevance_table the phase_id to set
	 */
	public void setPhase_id(Long phase_id) {
		this.phase_id = phase_id;
	}

	/**
	 * @hibernate.property column="isstart"
	 * @return the isstart
	 */
	public String getIsstart() {
		return isstart;
	}

	/**
	 * @param relevance_table the isstart to set
	 */
	public void setIsstart(String isstart) {
		this.isstart = isstart;
	}
	
	
}


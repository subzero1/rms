package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author lee.xiangyu 2009-12-11
 * @hibernate.class table="Ta24_phase_timelimit"
 */

public class Ta24_phase_timelimit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2437714947072675648L;

	private Long id;
	
	private Long phase_id;
	
	private String phase_name;
	
	private String gclb;
	
	private Long timelimit;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta24_NUM"
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
	 * @hibernate.property column="phase_name"
	 * @return the phase_id
	 */
	public String getPhase_name() {
		return phase_name;
	}

	/**
	 * @param relevance_table the phase_name to set
	 */
	public void setPhase_name(String phase_name) {
		this.phase_name = phase_name;
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
	 * @hibernate.property column="gclb"
	 * @return the gclb
	 */
	public String getGclb() {
		return gclb;
	}

	/**
	 * @param relevance_table the gclb to set
	 */
	public void setGclb(String gclb) {
		this.gclb = gclb;
	}

	/**
	 * @hibernate.property column="timelimit"
	 * @return the timelimit
	 */
	public Long getTimelimit() {
		return timelimit;
	}

	/**
	 * @param relevance_table the timelimit to set
	 */
	public void setTimelimit(Long timelimit) {
		this.timelimit = timelimit;
	}
	
}


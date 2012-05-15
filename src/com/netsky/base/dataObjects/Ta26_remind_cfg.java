package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @hibernate.class table="Ta26_remind_cfg"
 * @author lee.xiangyu
 * @create 2010-04-17
 */
public class Ta26_remind_cfg implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1719851718977917638L;

	/**
	 * 标识id
	 */
	private Long id;

	/**
	 * 节点名称【代表岗位】
	 */
	private String node_name;
	
	/**
	 * 模块id串，TA06.ID
	 */
	private String module_ids;
	
	/**
	 * 节点状态
	 */
	private String node_status;
	
	/**
	 * 提前天数
	 */
	private Integer pre_days;
	
	/**
	 * 需求提醒的状态
	 */
	private String project_status;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta26_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @hibernate.property column="module_ids"
	 * @return module_ids
	 */
	public String getModule_ids() {
		return module_ids;
	}
	
	/**
	 * @param module_ids
	 */
	public void setModule_ids(String module_ids) {
		this.module_ids = module_ids;
	}
	
	/**
	 * @hibernate.property column="node_name"
	 * @return node_name
	 */
	public String getNode_name() {
		return node_name;
	}
	
	/** 
	 * @param node_name
	 */
	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}
	
	/**
	 * @hibernate.property column="node_status"
	 * @return node_status
	 */
	public String getNode_status() {
		return node_status;
	}
	
	/**
	 * @param node_status
	 */
	public void setNode_status(String node_status) {
		this.node_status = node_status;
	}
	
	/**
	 * @hibernate.property column="pre_days"
	 * @return pre_days
	 */
	public Integer getPre_days() {
		return pre_days;
	}
	
	/**
	 * @param pre_days
	 */
	public void setPre_days(Integer pre_days) {
		this.pre_days = pre_days;
	}
	
	/**
	 * @hibernate.property column="project_status"
	 * @return project_status
	 */
	public String getProject_status() {
		return project_status;
	}
	
	/**
	 * @param project_status
	 */
	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}
}

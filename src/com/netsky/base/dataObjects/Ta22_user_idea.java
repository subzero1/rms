package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @description:
 * 用户审批意见定义
 * @class name:com.netsky.base.dataObjects.Ta22_user_idea
 * @hibernate.class table="Ta22_user_idea"
 * @author wind Feb 12, 2010
 */
public class Ta22_user_idea implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6812143150551276625L;


	/**
	 * 标识,
	 */
	private Long id;
	
	
	/**
	 * 标识,
	 */
	private Long user_id;	
	
	/**
	 * 同tb17.check_result
	 * 审批意见： 4同意 5 修改 6不发表意见 7暂缓
	 *  5.审批同意时插入，或 回退时插入。
	 *  7.审批暂缓后插入。且流程所有相关表单都转移到办结里.可以恢复
	 */
	private Integer check_result;	
	
	/**
	 * 审批具体意见
	 */
	private String check_idea;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta22_NUM"
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
	 * @hibernate.property column="user_id"
	 * @return Returns the user_id.
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id The user_id to set.
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @hibernate.property column="check_result"
	 * @return Returns the check_result.
	 */
	public Integer getCheck_result() {
		return check_result;
	}

	/**
	 * @param check_result The check_result to set.
	 */
	public void setCheck_result(Integer check_result) {
		this.check_result = check_result;
	}

	/**
	 * @hibernate.property column="check_idea"
	 * @return Returns the check_idea.
	 */
	public String getCheck_idea() {
		return check_idea;
	}

	/**
	 * @param check_idea The check_idea to set.
	 */
	public void setCheck_idea(String check_idea) {
		this.check_idea = check_idea;
	}	
	
}

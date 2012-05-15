package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @hibernate.class table="Ta27_user_remind"
 * @author lx
 * @create 2010-04-30
 */
public class Ta27_user_remind implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6433232395850019191L;

	/**
	 * 标识id
	 */
	private Long id;

	/**
	 * 标识user_id
	 */
	private Long user_id;

	/**
	 * 标识remind_type
	 */
	private Long remind_type;

	/**
	 * 标识 mobile_flag
	 */
	private Long mobile_flag;

	/**
	 * 标识message_flag
	 */
	private Long message_flag;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta27_NUM"
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
	 * @hibernate.property column="user_id"
	 * @return user_id
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @hibernate.property column="remind_type"
	 * @return remind_type
	 */
	public Long getRemind_type() {
		return remind_type;
	}

	/**
	 * @param remind_type the remind_type to set
	 */
	public void setRemind_type(Long remind_type) {
		this.remind_type = remind_type;
	}

	/**
	 * @hibernate.property column="mobile_flag"
	 * @return mobile_flag
	 */
	public Long getMobile_flag() {
		return mobile_flag;
	}

	/**
	 * @param mobile_flag the mobile_flag to set
	 */
	public void setMobile_flag(Long mobile_flag) {
		this.mobile_flag = mobile_flag;
	}

	/**
	 * @hibernate.property column="message_flag"
	 * @return message_flag
	 */
	public Long getMessage_flag() {
		return message_flag;
	}

	/**
	 * @param message_flag the message_flag to set
	 */
	public void setMessage_flag(Long message_flag) {
		this.message_flag = message_flag;
	}


}

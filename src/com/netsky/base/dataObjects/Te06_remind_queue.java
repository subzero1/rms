package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;
/**
 * @author lee.xiangyu 2010-05-17
 * @hibernate.class table="Te06_remind_queue"
 */
public class Te06_remind_queue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -480358012534415677L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 链接
	 */
	private String uri;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 提醒时间
	 */
	private Date time;
	
	/**
	 * 被提醒人标识
	 */
	private String ids;
	
	/**
	 * 被提醒人姓名
	 */
	private String persons;
	
	/**
	 * 被提醒人电话
	 */
	private String tels;
	
	/**
	 * 提醒类别
	 */
	private String type;
	
	/**
	 * 提醒次数
	 */
	private Long times;
	
	/**
	 * 状态
	 */
	private String status;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Te06_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="uri"
	 * @return Returns the uri.
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri The uri to set.
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @hibernate.property column="content"
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @hibernate.property column="time"
	 * @return Returns the time.
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time The time to set.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @hibernate.property column="ids"
	 * @return Returns the ids.
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids The ids to set.
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @hibernate.property column="persons"
	 * @return Returns the persons.
	 */
	public String getPersons() {
		return persons;
	}

	/**
	 * @param persons The persons to set.
	 */
	public void setPersons(String persons) {
		this.persons = persons;
	}

	/**
	 * @hibernate.property column="tels"
	 * @return Returns the tels.
	 */
	public String getTels() {
		return tels;
	}

	/**
	 * @param tels The tels to set.
	 */
	public void setTels(String tels) {
		this.tels = tels;
	}

	/**
	 * @hibernate.property column="type"
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @hibernate.property column="status"
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @hibernate.property column="times"
	 * @return Returns the times.
	 */
	public Long getTimes() {
		return times;
	}
	
	/**
	 * @param times The times to set.
	 */
	public void setTimes(Long times) {
		this.times = times;
	}

}

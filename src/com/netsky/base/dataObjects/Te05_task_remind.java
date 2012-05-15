package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;
/**
 * @author lee.xiangyu 2010-05-17
 * @hibernate.class table="Te05_task_remind"
 */
public class Te05_task_remind implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6156295391306114396L;

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
	 * 下次提醒时间
	 */
	private Date next_time;
	
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
	 * 提醒频度
	 */
	private String frequence;
	
	/**
	 * 提醒次数
	 */
	private Long times;
	
	/**
	 * 已经提醒次数
	 */
	private Long fact_times;
	
	/**
	 * 提醒名称
	 */
	private String remind_key;
	
	/**
	 * 扩展参数，比如【remind_key=表单提醒，则EXT_PARAMS=(project_id=${project_id}&doc_id=${doc_id}&user_id=${user_id})】
	 */
	private String ext_params;
	
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Te05_NUM"
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
	 * @hibernate.property column="next_time"
	 * @return Returns the next_time.
	 */
	public Date getNext_time() {
		return next_time;
	}

	/**
	 * @param next_time The next_time to set.
	 */
	public void setNext_time(Date next_time) {
		this.next_time = next_time;
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
	 * @hibernate.property column="frequence"
	 * @return Returns the frequence.
	 */
	public String getFrequence() {
		return frequence;
	}

	/**
	 * @param frequence The frequence to set.
	 */
	public void setFrequence(String frequence) {
		this.frequence = frequence;
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

	/**
	 * @hibernate.property column="fact_times"
	 * @return Returns the fact_times.
	 */
	public Long getFact_times() {
		return fact_times;
	}

	/**
	 * @param fact_times The fact_times to set.
	 */
	public void setFact_times(Long fact_times) {
		this.fact_times = fact_times;
	}

	/**
	 * @hibernate.property column="remind_key"
	 * @return Returns the remind_key.
	 */
	public String getRemind_key() {
		return remind_key;
	}

	/**
	 * @param remind_key The remind_key to set.
	 */
	public void setRemind_key(String remind_key) {
		this.remind_key = remind_key;
	}

	/**
	 * @hibernate.property column="ext_params"
	 * @return Returns the ext_params.
	 */
	public String getExt_params() {
		return ext_params;
	}

	/**
	 * @param ext_params The ext_params to set.
	 */
	public void setExt_params(String ext_params) {
		this.ext_params = ext_params;
	}

	/**
	 * @hibernate.property column="remark"
	 * @return Returns the remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

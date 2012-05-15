package com.netsky.base.dataObjects;

import java.util.Date;

/**
 * 附件表
 * 
 * @author mengying 2009-12-11
 * @hibernate.class table="Te08_message"
 */
public class Te08_message {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 发送人
	 */
	private String fsr;

	/**
	 * 接收人
	 */
	private String jsr;
	
	/**
	 * 发送时间
	 */
	private Date fssj;
	
	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 发送状态
	 */
	private String state;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Te08_NUM"
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
	 * @hibernate.property column="fsr"
	 * @return the fsr
	 */
	public String getFsr() {
		return fsr;
	}

	/**
	 * @param fsr The fsr to set.
	 */
	public void setFsr(String fsr) {
		this.fsr = fsr;
	}

	/**
	 * @hibernate.property column="jsr"
	 * @return the jsr
	 */
	public String getJsr() {
		return jsr;
	}

	/**
	 * @param jsr The jsr to set.
	 */
	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	/**
	 * @hibernate.property column="fssj"
	 * @return the fssj
	 */
	public Date getFssj() {
		return fssj;
	}

	/**
	 * @param fssj The fssj to set.
	 */
	public void setFssj(Date fssj) {
		this.fssj = fssj;
	}

	/**
	 * @hibernate.property column="title"
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @hibernate.property column="content"
	 * @return the content
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
	 * @hibernate.property column="state"
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
}

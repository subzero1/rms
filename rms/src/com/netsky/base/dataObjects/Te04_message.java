package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;
/**
 * @author wlx 2010-03-12
 * @hibernate.class table="Te04_message"
 */

public class Te04_message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6597073235879375488L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 发送人标识
	 */
	private Long sender_id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 发送日期
	 */
	private Date send_date;
	
	/**
	 * 阅读标示 
	 */
	private String read_flag;
	
	/**
	 * 阅读人标识
	 */
	private String reader_id;
	
	/**
	 * 阅读人标识
	 */
	private String reader_name;
	
	/**
	 * 回复标识
	 */
	private Long repeat_flag;
	
	/**
	 * 发送状态
	 */
	private Long send_flag;
	
	/**
	 * 接收人状态
	 */
	private String receive_flag;
	
	/**
	 * 附件个数
	 */
	private Long fujian_flag;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Te04_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="sender_id"
	 * @return Returns the sender_id.
	 */
	public Long getSender_id() {
		return sender_id;
	}

	/**
	 * @param login_id The sender_id to set.
	 */
	public void setSender_id(Long sender_id) {
		this.sender_id = sender_id;
	}

	/**
	 * @hibernate.property column="title"
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param login_id The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @hibernate.property column="content"
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param login_id The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @hibernate.property column="send_date"
	 * @return Returns the send_date.
	 */
	public Date getSend_date() {
		return send_date;
	}

	/**
	 * @param login_id The send_date to set.
	 */
	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	/**
	 * @hibernate.property column="read_flag"
	 * @return Returns the read_flag.
	 */
	public String getRead_flag() {
		return read_flag;
	}

	/**
	 * @param login_id The read_flag to set.
	 */
	public void setRead_flag(String read_flag) {
		this.read_flag = read_flag;
	}

	/**
	 * @hibernate.property column="reader_id"
	 * @return Returns the reader_id.
	 */
	public String getReader_id() {
		return reader_id;
	}

	/**
	 * @param login_id The reader_id to set.
	 */
	public void setReader_id(String reader_id) {
		this.reader_id = reader_id;
	}

	/**
	 * @hibernate.property column="repeat_flag"
	 * @return Returns the repeat_flag.
	 */
	public Long getRepeat_flag() {
		return repeat_flag;
	}

	/**
	 * @param login_id The repeat_flag to set.
	 */
	public void setRepeat_flag(Long repeat_flag) {
		this.repeat_flag = repeat_flag;
	}

	/**
	 * @hibernate.property column="send_flag"
	 * @return Returns the send_flag.
	 */
	public Long getSend_flag() {
		return send_flag;
	}

	/**
	 * @param login_id The send_flag to set.
	 */
	public void setSend_flag(Long send_flag) {
		this.send_flag = send_flag;
	}

	/**
	 * @hibernate.property column="receive_flag"
	 * @return Returns the receive_flag.
	 */
	public String getReceive_flag() {
		return receive_flag;
	}

	/**
	 * @param login_id The receive_flag to set.
	 */
	public void setReceive_flag(String receive_flag) {
		this.receive_flag = receive_flag;
	}

	/**
	 * @hibernate.property column="fujian_flag"
	 * @return Returns the fujian_flag.
	 */
	public Long getFujian_flag() {
		return fujian_flag;
	}

	/**
	 * @param login_id The fujian_flag to set.
	 */
	public void setFujian_flag(Long fujian_flag) {
		this.fujian_flag = fujian_flag;
	}

	/**
	 * @hibernate.property column="reader_name"
	 * @return Returns the reader_name.
	 */
	public String getReader_name() {
		return reader_name;
	}

	/**
	 * @param login_id The reader_name to set.
	 */
	public void setReader_name(String reader_name) {
		this.reader_name = reader_name;
	}
}

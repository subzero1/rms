package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.*;

import com.netsky.base.dataObjects.interfaces.SlaveObject;

/**
 * 附件表
 * 
 * @author mengying 2009-12-11
 * @hibernate.class table="te01_slave"
 */

public class Te01_slave implements SlaveObject , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1964628758039564912L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 工程ID
	 */
	private Long project_id;

	/**
	 * 表单文档ID
	 */
	private Long doc_id;
	
	/**
	 * 表单模块ID
	 */
	private Long module_id;

	/**
	 * 附件类型：设计说明、工程图纸、普通附件、竣工文件[竣工资料]、相关竣工资料[竣工资料]、工程结算表[竣工资料]、资源调配室表[竣工资料]
	 */
	private String slave_type;

	/**
	 * 文件名
	 */
	private String file_name;

	/**
	 * 扩展名
	 */
	private String ext_name;

	/**
	 * ftp文件路径
	 */
	private String ftp_url;

	/**
	 * 上传用户姓名
	 */
	private String user_name;

	/**
	 * 上传用户ID
	 */
	private Long user_id;

	/**
	 * 上传日期
	 */
	private Date ftp_date;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * @hibernate.property column="remark"
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Te01_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="project_id"
	 * @return the project_id
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id
	 *            the project_id to set
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="doc_id"
	 * @return the doc_id
	 */
	public Long getDoc_id() {
		return doc_id;
	}

	/**
	 * @param doc_id
	 *            the doc_id to set
	 */
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

	/**
	 * @hibernate.property column="slave_type"
	 * @return the slave_type
	 */
	public String getSlave_type() {
		return slave_type;
	}

	/**
	 * @param slave_type
	 *            the slave_type to set
	 */
	public void setSlave_type(String slave_type) {
		this.slave_type = slave_type;
	}

	/**
	 * @hibernate.property column="file_name"
	 * @return the file_name
	 */
	public String getFile_name() {
		return file_name;
	}

	/**
	 * @param file_name
	 *            the file_name to set
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	/**
	 * @hibernate.property column="ext_name"
	 * @return the ext_name
	 */
	public String getExt_name() {
		return ext_name;
	}

	/**
	 * @param ext_name
	 *            the ext_name to set
	 */
	public void setExt_name(String ext_name) {
		this.ext_name = ext_name;
	}

	/**
	 * @hibernate.property column="ftp_url"
	 * @return the ftp_url
	 */
	public String getFtp_url() {
		return ftp_url;
	}

	/**
	 * @param ftp_url
	 *            the ftp_url to set
	 */
	public void setFtp_url(String ftp_url) {
		this.ftp_url = ftp_url;
	}

	/**
	 * @hibernate.property column="user_name"
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name
	 *            the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @hibernate.property column="user_id"
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @hibernate.property column="ftp_date"
	 * @return the ftp_date
	 */
	public Date getFtp_date() {
		return ftp_date;
	}

	/**
	 * @param ftp_date
	 *            the ftp_date to set
	 */
	public void setFtp_date(Date ftp_date) {
		this.ftp_date = ftp_date;
	}

	/**
	 * @hibernate.property column="module_id"
	 * @return the module_id
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id
	 *            the module_id to set
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}

	public String getSlaveIdentifier() {
		return "Slave";
	}

	public void setFileName(String FileName) {
		this.file_name = FileName;
		
	}

	public void setFilePatch(String FilePatch) {
		this.ftp_url = FilePatch;
		
	}

	public String getType() {
		return this.slave_type;
	}
	public String getSaveType() {
		return null;
	}

}

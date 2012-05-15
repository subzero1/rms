package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta03_user"
 */

public class Ta03_user implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2151205279318105229L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 登录名
	 */
	private String login_id;

	/**
	 * 密码
	 */
	private String passwd;

	/**
	 * 固定电话
	 */
	private String fix_tel;

	/**
	 * 移动电话
	 */
	private String mobile_tel;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 部门标识
	 */
	private Long dept_id;

	/**
	 * 部门名称，与人员关联
	 */
	private String dept_name;

	/**
	 * 0:不可用，1:可用
	 */
	private Long useflag;
	/**
	 * 最后一次修改日期
	 */
	private Date last_pwd_date;
	/**
	 * 性别--男，女
	 */
	private String sex;

	/**
	 * 查询级别
	 */
	private Integer search_level;
	
	
	/**
	 * 地区名称
	 */
	private String area_name;
	
	
	/**
	 * @hibernate.property column="area_name"
	 * @return area_name
	 */
	public String getArea_name() {
		return area_name;
	}
	/**
	 * @param area_name
	 */
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta03_NUM"
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
	 * @hibernate.property column="name"
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="login_id"
	 * @return the login_id
	 */
	public String getLogin_id() {
		return login_id;
	}

	/**
	 * @param login_id
	 *            the login_id to set
	 */
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	/**
	 * @hibernate.property column="passwd"
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @hibernate.property column="fix_tel"
	 * @return the fix_tel
	 */
	public String getFix_tel() {
		return fix_tel;
	}

	/**
	 * @param fix_tel
	 *            the fix_tel to set
	 */
	public void setFix_tel(String fix_tel) {
		this.fix_tel = fix_tel;
	}

	/**
	 * @hibernate.property column="mobile_tel"
	 * @return the mobile_tel
	 */
	public String getMobile_tel() {
		return mobile_tel;
	}

	/**
	 * @param mobile_tel
	 *            the mobile_tel to set
	 */
	public void setMobile_tel(String mobile_tel) {
		this.mobile_tel = mobile_tel;
	}

	/**
	 * @hibernate.property column="email"
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @hibernate.property column="remark"
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.property column="dept_id"
	 * @return the dept_id
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @param dept_id
	 *            the dept_id to set
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @hibernate.property column="useflag"
	 * @return the useflag
	 */
	public Long getUseflag() {
		return useflag;
	}

	/**
	 * @param useflag
	 *            the useflag to set
	 */
	public void setUseflag(Long useflag) {
		this.useflag = useflag;
	}

	/**
	 * @return Returns the dept_name.
	 */
	public String getDept_name() {
		return dept_name;
	}

	/**
	 * @param dept_name
	 *            The dept_name to set.
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	/**
	 * @hibernate.property column="last_pwd_date"
	 * @return last_pwd_date
	 */
	public Date getLast_pwd_date() {
		return last_pwd_date;
	}

	/**
	 * 
	 * @param last_pwd_date
	 */
	public void setLast_pwd_date(Date last_pwd_date) {
		this.last_pwd_date = last_pwd_date;
	}

	/**
	 * @hibernate.property column="sex"
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @hibernate.property column="search_level"
	 * @return the search_level
	 */
	public Integer getSearch_level() {
		return search_level;
	}

	/**
	 * @param search_level
	 *            the search_level to set
	 */
	public void setSearch_level(Integer search_level) {
		this.search_level = search_level;
	}
}

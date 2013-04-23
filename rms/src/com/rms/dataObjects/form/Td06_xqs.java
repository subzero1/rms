package com.rms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-12-27
 * @hibernate.class table="Td06_xqs"
 */

public class Td06_xqs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 533416064057216192L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td06_num"
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
 * 
 */
 private Long project_id;

 /**
 * @hibernate.property column="project_id"
 * @return Returns the project_id.
 */
 public Long getProject_id() {
    return project_id;
 }

 public void setProject_id(Long project_id) {
    this.project_id = project_id;
  }

 /**
 * 
 */
 private String fwbm;

 /**
 * @hibernate.property column="fwbm"
 * @return Returns the fwbm.
 */
 public String getFwbm() {
    return fwbm;
 }

 public void setFwbm(String fwbm) {
    this.fwbm = fwbm;
  }

 /**
 * 
 */
 private String bdbh;

 /**
 * @hibernate.property column="bdbh"
 * @return Returns the bdbh.
 */
 public String getBdbh() {
    return bdbh;
 }

 public void setBdbh(String bdbh) {
    this.bdbh = bdbh;
  }

 /**
 * 
 */
 private String cjr;

 /**
 * @hibernate.property column="cjr"
 * @return Returns the cjr.
 */
 public String getCjr() {
    return cjr;
 }

 public void setCjr(String cjr) {
    this.cjr = cjr;
  }

 /**
 * 
 */
 private String cjrdh;

 /**
 * @hibernate.property column="cjrdh"
 * @return Returns the cjrdh.
 */
 public String getCjrdh() {
    return cjrdh;
 }

 public void setCjrdh(String cjrdh) {
    this.cjrdh = cjrdh;
  }

 /**
 * 
 */
 private Date cjrq;

 /**
 * @hibernate.property column="cjrq"
 * @return Returns the cjrq.
 */
 public Date getCjrq() {
    return cjrq;
 }

 public void setCjrq(Date cjrq) {
    this.cjrq = cjrq;
  }

 /**
 *  资源名称
 */
 private String xqmc;

 /**
 * @hibernate.property column="xqmc"
 * @return Returns the xqmc.
 */
 public String getXqmc() {
    return xqmc;
 }

 public void setXqmc(String xqmc) {
    this.xqmc = xqmc;
  }

 /**
 *  建设性质（基站、室分）
 */
 private String jsxz;

 /**
 * @hibernate.property column="jsxz"
 * @return Returns the jsxz.
 */
 public String getJsxz() {
    return jsxz;
 }

 public void setJsxz(String jsxz) {
    this.jsxz = jsxz;
  }

 /**
 *  坐落地点
 */
 private String zldd;

 /**
 * @hibernate.property column="zldd"
 * @return Returns the zldd.
 */
 public String getZldd() {
    return zldd;
 }

 public void setZldd(String zldd) {
    this.zldd = zldd;
  }

 /**
 *  所属地区
 */
 private String ssdq;

 /**
 * @hibernate.property column="ssdq"
 * @return Returns the ssdq.
 */
 public String getSsdq() {
    return ssdq;
 }

 public void setSsdq(String ssdq) {
    this.ssdq = ssdq;
  }

 /**
 *  经度
 */
 private String jd;

 /**
 * @hibernate.property column="jd"
 * @return Returns the jd.
 */
 public String getJd() {
    return jd;
 }

 public void setJd(String jd) {
    this.jd = jd;
  }

 /**
 *  纬度
 */
 private String wd;

 /**
 * @hibernate.property column="wd"
 * @return Returns the wd.
 */
 public String getWd() {
    return wd;
 }

 public void setWd(String wd) {
    this.wd = wd;
  }

 /**
 * 备注
 */
 private String bz;

 /**
 * @hibernate.property column="bz"
 * @return Returns the bz.
 */
 public String getBz() {
    return bz;
 }

 public void setBz(String bz) {
    this.bz = bz;
  }

 /**
 * 幢数
 */
 private Long zs;

 /**
 * @hibernate.property column="zs"
 * @return Returns the zs.
 */
 public Long getZs() {
    return zs;
 }

 public void setZs(Long zs) {
    this.zs = zs;
  }

 /**
 * 层数
 */
 private Long cs;

 /**
 * @hibernate.property column="cs"
 * @return Returns the cs.
 */
 public Long getCs() {
    return cs;
 }

 public void setCs(Long cs) {
    this.cs = cs;
  }

 /**
 * 户数
 */
 private Long hs;

 /**
 * @hibernate.property column="hs"
 * @return Returns the hs.
 */
 public Long getHs() {
    return hs;
 }

 public void setHs(Long hs) {
    this.hs = hs;
  }

 /**
 * 覆盖属性
 */
 private String fgsx;

 /**
 * @hibernate.property column="fgsx"
 * @return Returns the fgsx.
 */
 public String getFgsx() {
    return fgsx;
 }

 public void setFgsx(String fgsx) {
    this.fgsx = fgsx;
  }

 /**
  * 建设方式
  */
  private String jsfs;

  /**
   * @hibernate.property column="jsfs"
   * @return Returns the jsfs.
   */
	public String getJsfs() {
		return jsfs;
	}
	
	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}
	
	/**
	  * 建设场景
	  */
	  private String jscj;
	  
	  /**
	   * 所属网格
	   */
	  private String sswg;

	  /**
	   * @hibernate.property column="jscj"
	   * @return Returns the jscj.
	  */
	  public String getJscj() {
			return jscj;
	  }
	
	  public void setJscj(String jscj) {
			this.jscj = jscj;
	  }
	
	  /**
	   * @hibernate.property column="sswg"
	   * @return Returns the sswg.
	 */
	 public String getSswg() {
			return sswg;
	 }
	
	 public void setSswg(String sswg) {
		this.sswg = sswg;
	 }
	 
	 private String xqshr;
	  
	 private Date xqshsj;

	 /**
	   * @hibernate.property column="xqshr"
	   * @return Returns the xqshr.
	 */
	public String getXqshr() {
		return xqshr;
	}

	public void setXqshr(String xqshr) {
		this.xqshr = xqshr;
	}

	 /**
	   * @hibernate.property column="xqshsj"
	   * @return Returns the xqshsj.
	 */
	public Date getXqshsj() {
		return xqshsj;
	}

	public void setXqshsj(Date xqshsj) {
		this.xqshsj = xqshsj;
	} 
	/**
	 * 规划塔高
	 */
	private String ghtx;
	/**
	 * 塔高
	 */
	private Long tg;
	/**
	 * 天馈挂高
	 */
	private String tkgg;
	/**
	 * 机房共享属性
	 */
	private String jfgxsx;
	/**
	 * 塔桅共享属性
	 */
	private String twgxsx;
	/**
	 * 站间距
	 */
	private Long zjj;
	/**
	 * 规划区域
	 */
	private String ghqy;
	/**
	 * 方位角
	 */
	private String fwj;
	/**
	 * 下倾角
	 */
	private String xqj;
	/**
	 * 建设驱动原因一
	 */
	private String jsqdyy1;
	/**
	 * 建设驱动原因二
	 */
	private String jsqdyy2;
	/**
	 * 建设驱动原因三
	 */
	private String jsqdyy3;

	public String getGhtx() {
		return ghtx;
	}

	public void setGhtx(String ghtx) {
		this.ghtx = ghtx;
	}

	public Long getTg() {
		return tg;
	}

	public void setTg(Long tg) {
		this.tg = tg;
	}

	public String getTkgg() {
		return tkgg;
	}

	public void setTkgg(String tkgg) {
		this.tkgg = tkgg;
	}

	public String getJfgxsx() {
		return jfgxsx;
	}

	public void setJfgxsx(String jfgxsx) {
		this.jfgxsx = jfgxsx;
	}

	public String getTwgxsx() {
		return twgxsx;
	}

	public void setTwgxsx(String twgxsx) {
		this.twgxsx = twgxsx;
	}

	public Long getZjj() {
		return zjj;
	}

	public void setZjj(Long zjj) {
		this.zjj = zjj;
	}

	public String getGhqy() {
		return ghqy;
	}

	public void setGhqy(String ghqy) {
		this.ghqy = ghqy;
	}

	public String getFwj() {
		return fwj;
	}

	public void setFwj(String fwj) {
		this.fwj = fwj;
	}

	public String getXqj() {
		return xqj;
	}

	public void setXqj(String xqj) {
		this.xqj = xqj;
	}

	public String getJsqdyy1() {
		return jsqdyy1;
	}

	public void setJsqdyy1(String jsqdyy1) {
		this.jsqdyy1 = jsqdyy1;
	}

	public String getJsqdyy2() {
		return jsqdyy2;
	}

	public void setJsqdyy2(String jsqdyy2) {
		this.jsqdyy2 = jsqdyy2;
	}

	public String getJsqdyy3() {
		return jsqdyy3;
	}

	public void setJsqdyy3(String jsqdyy3) {
		this.jsqdyy3 = jsqdyy3;
	}
}

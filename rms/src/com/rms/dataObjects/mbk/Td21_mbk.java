package com.rms.dataObjects.mbk;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Td21_mbk"
 */

public class Td21_mbk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 510374883558675520L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td21_num"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 谈点人获得此信息的方式（主动认领、指定下发）
	 */
	private String hdfs;

	/**
	 * @hibernate.property column="hdfs"
	 * @return Returns the hdfs.
	 */
	public String getHdfs() {
		return hdfs;
	}

	public void setHdfs(String hdfs) {
		this.hdfs = hdfs;
	}

	/**
	 * 资源编号
	 */
	private String zybh;

	/**
	 * @hibernate.property column="zybh"
	 * @return Returns the zybh.
	 */
	public String getZybh() {
		return zybh;
	}

	public void setZybh(String zybh) {
		this.zybh = zybh;
	}

	/**
	 * 资源名称
	 */
	private String zymc;

	/**
	 * @hibernate.property column="zymc"
	 * @return Returns the zymc.
	 */
	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	/**
	 * 建设性质（基站、室分）
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
	 * 分类（盲点、关键站点、备用站点、其它）
	 */
	private String lb;

	/**
	 * @hibernate.property column="lb"
	 * @return Returns the lb.
	 */
	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	/**
	 * 坐落地点
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
	 * 所属地区
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
	 * 经度
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
	 * 纬度
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
	 * 建设方式（C+W、合路、补建、扩容）
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
	 * 谈点部门
	 */
	private String tdbm;

	/**
	 * @hibernate.property column="tdbm"
	 * @return Returns the tdbm.
	 */
	public String getTdbm() {
		return tdbm;
	}

	public void setTdbm(String tdbm) {
		this.tdbm = tdbm;
	}

	/**
	 * 谈点人
	 */
	private String tdr;

	/**
	 * @hibernate.property column="tdr"
	 * @return Returns the tdr.
	 */
	public String getTdr() {
		return tdr;
	}

	public void setTdr(String tdr) {
		this.tdr = tdr;
	}

	/**
	 * 谈点人电话
	 */
	private String tdrdh;

	/**
	 * @hibernate.property column="tdrdh"
	 * @return Returns the tdrdh.
	 */
	public String getTdrdh() {
		return tdrdh;
	}

	public void setTdrdh(String tdrdh) {
		this.tdrdh = tdrdh;
	}

	/**
	 * 联系人
	 */
	private String lxr;

	/**
	 * @hibernate.property column="lxr"
	 * @return Returns the lxr.
	 */
	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	/**
	 * 联系人电话
	 */
	private String lxrdh;

	/**
	 * @hibernate.property column="lxrdh"
	 * @return Returns the lxrdh.
	 */
	public String getLxrdh() {
		return lxrdh;
	}

	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}

	/**
	 * 是否共建（是、否）
	 */
	private String sfgj;

	/**
	 * @hibernate.property column="sfgj"
	 * @return Returns the sfgj.
	 */
	public String getSfgj() {
		return sfgj;
	}

	public void setSfgj(String sfgj) {
		this.sfgj = sfgj;
	}

	/**
	 * 谈点协调费
	 */
	private Long tdxtf;

	/**
	 * @hibernate.property column="tdxtf"
	 * @return Returns the tdxtf.
	 */
	public Long getTdxtf() {
		return tdxtf;
	}

	public void setTdxtf(Long tdxtf) {
		this.tdxtf = tdxtf;
	}

	/**
	 * 签约时间
	 */
	private Date qysj;

	/**
	 * @hibernate.property column="qysj"
	 * @return Returns the qysj.
	 */
	public Date getQysj() {
		return qysj;
	}

	public void setQysj(Date qysj) {
		this.qysj = qysj;
	}

	/**
	 * 租金
	 */
	private Double zj;

	/**
	 * @hibernate.property column="zj"
	 * @return Returns the zj.
	 */
	public Double getZj() {
		return zj;
	}

	public void setZj(Double zj) {
		this.zj = zj;
	}

	/**
	 * 年限
	 */
	private Long nx;

	/**
	 * @hibernate.property column="nx"
	 * @return Returns the nx.
	 */
	public Long getNx() {
		return nx;
	}

	public void setNx(Long nx) {
		this.nx = nx;
	}

	/**
	 * 合同编号
	 */
	private String htbh;

	/**
	 * @hibernate.property column="htbh"
	 * @return Returns the htbh.
	 */
	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	/**
	 * 业主姓名
	 */
	private String yzmc;

	/**
	 * @hibernate.property column="yzmc"
	 * @return Returns the yzmc.
	 */
	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	/**
	 * 业主电话
	 */
	private String yzdh;

	/**
	 * @hibernate.property column="yzdh"
	 * @return Returns the yzdh.
	 */
	public String getYzdh() {
		return yzdh;
	}

	public void setYzdh(String yzdh) {
		this.yzdh = yzdh;
	}

	/**
	 * 是否付首款
	 */
	private String sfsfk;

	/**
	 * @hibernate.property column="sfsfk"
	 * @return Returns the sfsfk.
	 */
	public String getSfsfk() {
		return sfsfk;
	}

	public void setSfsfk(String sfsfk) {
		this.sfsfk = sfsfk;
	}

	/**
	 * 建设等级（A、B、C）
	 */
	private String jsdj;

	/**
	 * @hibernate.property column="jsdj"
	 * @return Returns the jsdj.
	 */
	public String getJsdj() {
		return jsdj;
	}

	public void setJsdj(String jsdj) {
		this.jsdj = jsdj;
	}

	/**
	 * 状态
	 */
	private String zt;

	/**
	 * @hibernate.property column="zt"
	 * @return Returns the zt.
	 */
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	/**
	 * 施工单位
	 */
	private String sgdw;

	/**
	 * @hibernate.property column="sgdw"
	 * @return Returns the sgdw.
	 */
	public String getSgdw() {
		return sgdw;
	}

	public void setSgdw(String sgdw) {
		this.sgdw = sgdw;
	}

	/**
	 * 施工管理员
	 */
	private String sggly;

	/**
	 * @hibernate.property column="sggly"
	 * @return Returns the sggly.
	 */
	public String getSggly() {
		return sggly;
	}

	public void setSggly(String sggly) {
		this.sggly = sggly;
	}

	/**
	 * 提交施工管理员时间
	 */
	private Date tjsj;

	/**
	 * @hibernate.property column="tjsj"
	 * @return Returns the tjsj.
	 */
	public Date getTjsj() {
		return tjsj;
	}

	public void setTjsj(Date tjsj) {
		this.tjsj = tjsj;
	}

	/**
	 * 开工时间
	 */
	private Date kgsj;

	/**
	 * @hibernate.property column="kgsj"
	 * @return Returns the kgsj.
	 */
	public Date getKgsj() {
		return kgsj;
	}

	public void setKgsj(Date kgsj) {
		this.kgsj = kgsj;
	}

	/**
	 * 完工时间
	 */
	private Date wgsj;

	/**
	 * @hibernate.property column="wgsj"
	 * @return Returns the wgsj.
	 */
	public Date getWgsj() {
		return wgsj;
	}

	public void setWgsj(Date wgsj) {
		this.wgsj = wgsj;
	}

	/**
	 * 存在问题
	 */
	private String czwt;

	/**
	 * @hibernate.property column="czwt"
	 * @return Returns the czwt.
	 */
	public String getCzwt() {
		return czwt;
	}

	public void setCzwt(String czwt) {
		this.czwt = czwt;
	}

	/**
	 * 工程分配说明（集成商自谈、施工管理员分配、按月度得分派发）
	 */
	private String gcfpsm;

	/**
	 * @hibernate.property column="gcfpsm"
	 * @return Returns the gcfpsm.
	 */
	public String getGcfpsm() {
		return gcfpsm;
	}

	public void setGcfpsm(String gcfpsm) {
		this.gcfpsm = gcfpsm;
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

	private Long tdzq;

	/**
	 * @hibernate.property column="tdzq"
	 * @return Returns the tdzq.
	 */
	public Long getTdzq() {
		return tdzq;
	}

	/**
	 * @param tdzq
	 *            The tdzq to set.
	 */
	public void setTdzq(Long tdzq) {
		this.tdzq = tdzq;
	}

	private Long tdr_id;

	/**
	 * @hibernate.property column="tdr_id"
	 * @return Returns the tdr_id.
	 */
	public Long getTdr_id() {
		return tdr_id;
	}

	/**
	 * @param tdr_id
	 *            The tdr_id to set.
	 */
	public void setTdr_id(Long tdr_id) {
		this.tdr_id = tdr_id;
	}

	private Date cjsj;

	/**
	 * @hibernate.property column="cjsj"
	 * @return Returns the cjsj.
	 */
	public Date getCjsj() {
		return cjsj;
	}

	/**
	 * @param cjsj
	 *            The cjsj to set.
	 */
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	
	
	/**
	 * 
	 */
	private Date kcsj;

	/**
	 * @hibernate.property column="kcsj"
	 * @return Returns the kcsj.
	 */
	public Date getKcsj() {
		return kcsj;
	}

	/**
	 * @param kcsj The kcsj to set.
	 */
	public void setKcsj(Date kcsj) {
		this.kcsj = kcsj;
	}

	/**
	 * 
	 */
	private Date zypfsj;

	/**
	 * @hibernate.property column="zypfsj"
	 * @return Returns the zypfsj.
	 */
	public Date getZypfsj() {
		return zypfsj;
	}

	/**
	 * @param zypfsj The zypfsj to set.
	 */
	public void setZypfsj(Date zypfsj) {
		this.zypfsj = zypfsj;
	}
	
	/**
	 * 幢数
	 */
	private Long zs;
	
	/**
	 * 层数
	 */
	private Long cs;
	
	/**
	 * 户数
	 */
	private Long hs;

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
	 * @hibernate.property column="hs"
	 * @return Returns the hs.
	 */
	public Long getHs() {
		return hs;
	}

	public void setHs(Long hs) {
		this.hs = hs;
	}
	
	/*
	 * 反馈周期
	 */
	private Long fkzq;
	
	/*
	 * 覆盖属性
	 */
	private String fgsx;

	/**
	 * @hibernate.property column="fkzq"
	 * @return Returns the fkzq.
	 */
	public Long getFkzq() {
		return fkzq;
	}

	public void setFkzq(Long fkzq) {
		this.fkzq = fkzq;
	}

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
	
	/*
	 * 最后反馈时间
	 */
	private Date zhfksj;

	/**
	 * @hibernate.property column="zhfksj"
	 * @return Returns the zhfksj.
	 */
	public Date getZhfksj() {
		return zhfksj;
	}

	public void setZhfksj(Date zhfksj) {
		this.zhfksj = zhfksj;
	}
	
	/*
	 * 申请勘察时间
	 */
	private Date sqkcsj;
	
	/*
	 * 申请会审时间
	 */
	private Date sqhssj;
	
	/*
	 * 申请勘察说明
	 */
	private String sqkcsm;

	/**
	 * @hibernate.property column="sqkcsj"
	 * @return Returns the sqkcsj.
	 */
	public Date getSqkcsj() {
		return sqkcsj;
	}

	public void setSqkcsj(Date sqkcsj) {
		this.sqkcsj = sqkcsj;
	}

	/**
	 * @hibernate.property column="sqhssj"
	 * @return Returns the sqhssj.
	 */
	public Date getSqhssj() {
		return sqhssj;
	}

	public void setSqhssj(Date sqhssj) {
		this.sqhssj = sqhssj;
	}

	/**
	 * @hibernate.property column="sqkcsm"
	 * @return Returns the sqkcsm.
	 */
	public String getSqkcsm() {
		return sqkcsm;
	}

	public void setSqkcsm(String sqkcsm) {
		this.sqkcsm = sqkcsm;
	}

	/*
	 * 创建人
	 */
	private String cjr;
	
	/*
	 * 创建人电话
	 */
	private String cjrdh;

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
	
	/*
	 * 创建人标识
	 */
	private Long cjr_id;

	/**
	 * @hibernate.property column="cjr_id"
	 * @return Returns the cjr_id.
	 */
	public Long getCjr_id() {
		return cjr_id;
	}

	public void setCjr_id(Long cjr_id) {
		this.cjr_id = cjr_id;
	}

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
	
	/*
	 * 需求书标识
	 */
	private Long xqs_id;

	/**
	 * @hibernate.property column="xqs_id"
	 * @return Returns the xqs_id.
	 */
	public Long getXqs_id() {
		return xqs_id;
	}

	public void setXqs_id(Long xqs_id) {
		this.xqs_id = xqs_id;
	}
	
	/*
	 * 勘察反馈时间
	 */
	private Date kcfksj;

	/**
	 * @hibernate.property column="kcfksj"
	 * @return Returns the kcfksj.
	 */
	public Date getKcfksj() {
		return kcfksj;
	}

	public void setKcfksj(Date kcfksj) {
		this.kcfksj = kcfksj;
	}
	
	private Date fksj;

	public Date getFksj() {
		return fksj;
	}

	public void setFksj(Date fksj) {
		this.fksj = fksj;
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
		
		/*
		 * 删除标识
		 */
		private Long delflag;

		/**
		   * @hibernate.property column="delflag"
		   * @return Returns the delflag.
		 */
		public Long getDelflag() {
			return delflag;
		}

		public void setDelflag(Long delflag) {
			this.delflag = delflag;
		}
		
		/*
		 * 关键站点
		 */
		private String gjzd;
		
		/*
		 * LTE批次
		 */
		private String ltepc;
		
		/*
		 * 关联过忙载扇
		 */
		private String glgmzs;
		
		/*
		 * 需求评审时间
		 */
		private Date xqpssj;
		
		/*
		 * 网优组BSC包区人
		 */
		private String wyzbqr;
		
		/**
		   * @hibernate.property column="gjzd"
		   * @return Returns the gjzd.
		 */
		public String getGjzd() {
			return gjzd;
		}

		public void setGjzd(String gjzd) {
			this.gjzd = gjzd;
		}

		/**
		   * @hibernate.property column="ltepc"
		   * @return Returns the ltepc.
		 */
		public String getLtepc() {
			return ltepc;
		}

		public void setLtepc(String ltepc) {
			this.ltepc = ltepc;
		}

		/**
		   * @hibernate.property column="glgmzs"
		   * @return Returns the glgmzs.
		 */
		public String getGlgmzs() {
			return glgmzs;
		}

		public void setGlgmzs(String glgmzs) {
			this.glgmzs = glgmzs;
		}

		/**
		   * @hibernate.property column="xqpssj"
		   * @return Returns the xqpssj.
		 */
		public Date getXqpssj() {
			return xqpssj;
		}

		public void setXqpssj(Date xqpssj) {
			this.xqpssj = xqpssj;
		}

		/**
		   * @hibernate.property column="wybqr"
		   * @return Returns the wybqr.
		 */
		public String getWyzbqr() {
			return wyzbqr;
		}

		public void setWyzbqr(String wyzbqr) {
			this.wyzbqr = wyzbqr;
		}
}

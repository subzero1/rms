package com.rms.controller.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.flow.vo.HaltWork;
import com.netsky.base.flow.vo.Vc1_sgpftst;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.rms.dataObjects.base.Tmp_zdxp;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.dataObjects.form.Td01_xmxx;
import com.rms.dataObjects.wxdw.Tf01_wxdw;
import com.rms.dataObjects.wxdw.Tf05_wxdw_dygx;

@Controller
public class Sgpd {

	/**
	 * 异常捕捉
	 */
	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private Dao dao;

	@Autowired
	private QueryService queryService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/sgpd.do")
	public ModelAndView sgpd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(
				request.getParameter("project_id"), -10L);
		Td00_gcxx td00 = (Td00_gcxx) dao.getObject(Td00_gcxx.class, project_id);
		Long xm_id = convertUtil.toLong(request.getParameter("xm_id"));
		Td01_xmxx td01 = (Td01_xmxx) dao.getObject(Td01_xmxx.class, xm_id);
		String gclb = "";
		String dq = "";
		if (td00 != null) {
			gclb = td00.getGclb();
			dq = td00.getSsdq();
		} else if (td01 != null) {
			gclb = td01.getGclb();
			dq = td01.getSsdq();
		} else {
			// System.out.println("找不到工程或项目");
			return new ModelAndView(
					"/WEB-INF/jsp/form/selectSgdw.jsp?errormsg=tdnotfound");
		}
		// 获得 所有相关地区专业 未停工 类别为施工的合作单位
		List<Object[]> wxdwList = (List<Object[]>) dao
				.search("select tf01,tf05 from Tf01_wxdw tf01,Tf05_wxdw_dygx tf05 where tf01.id=tf05.wxdw_id and tf05.zy='"
						+ gclb
						+ "' and tf05.dq='"
						+ dq
						+ "' and tf05.lb='fezb' and tf05.v1>0 and tf05.nd=to_char(sysdate,'yyyy') and tf01.lb='施工' and tf01.zt<>'停工'");
		if (wxdwList == null || wxdwList.size() == 0) {
			// System.out.println("没有符合的合作单位");
			return new ModelAndView(
					"/WEB-INF/jsp/form/selectSgdw.jsp?errormsg=tfnotfound");
		}
		// 建立数组o[11]
		// o[0]:tf01;o[1]:tf05;o[2]:zhdf(综合得分);o[3]:决算率;o[4]:综合得分排名;o[5]:决算率排名;o[6]:计划份额;o[7]:实际份额;o[8]:份额偏差率;o[9]:份额偏差率档级
		List<Object[]> objectsList = new ArrayList<Object[]>();
		for (Object[] objects : wxdwList) {
			Object[] o = new Object[11];
			o[0] = objects[0];
			o[1] = objects[1];
			objectsList.add(o);
		}
		// 判断计划份额占比与实际份额占比
		// 相关地区相关工程类别的所有工程的总工日 zgr 默认为0
		// 黄钢强修改：占比暂由td00.(ys_pggr + ys_jggr)改td01.ys_sgf 为计算依据
		double zgr = convertUtil.toDouble(dao.search(
				"select sum(ys_sgf ) from Td01_xmxx where sgdw is not null and ssdq='"
						+ dq + "' and gclb='" + gclb + "'").get(0), 0D);
		// flag:未通过检测的个数
		int flag = 0;
		// passedList 通过条件的合作单位 暂存入该LIST
		for (Object[] objects : objectsList) {
			Tf01_wxdw tf01 = (Tf01_wxdw) objects[0];
			Tf05_wxdw_dygx tf05 = (Tf05_wxdw_dygx) objects[1];
			// fezb:实际份额占比
			Double fezb = 0D;
			if (zgr != 0) {
				double gr = convertUtil.toDouble(dao.search(
						"select sum(ys_sgf) from Td01_xmxx where sgdw='"
								+ tf01.getMc() + "' and ssdq='" + dq
								+ "' and gclb='" + gclb + "'").get(0), 0D);
				fezb = gr / zgr;
			}
			// tf05.getV1():预定份额占比
			objects[6] = tf05.getV1();
			objects[7] = fezb * 100;
			if (tf05.getV1() <= fezb * 100) {
				objects[10] = 1;
				flag++;
			}
		}
		if (flag == objectsList.size()) {
			for (Object[] objects : objectsList) {
				objects[10] = null;
			}
			flag = 0;
		}
		// 判断在建工程数和最大工程数
		int flag2 = 0;
		for (Object[] objects : objectsList) {
			if (objects[10] != null) {
				continue;
			}
			// 在建工程数
			Long zjgcs = convertUtil.toLong(dao.search(
					"select count(*) from Td01_xmxx where sgdw='"
							+ ((Tf01_wxdw) objects[0]).getMc()
							+ "' and sjjgsj is null").get(0));
			// 最大工程数
			Double zdgcs = 0D;
			List<Double> zdgcsList = (List<Double>) dao
					.search("select v1 from Tf05_wxdw_dygx tf05 where tf05.wxdw_id="
							+ ((Tf01_wxdw) objects[0]).getId()
							+ " and tf05.zy='"
							+ gclb
							+ "' and tf05.lb='zdgcs' and tf05.v1>0 and tf05.nd=to_char(sysdate,'yyyy')");
			if (zdgcsList != null && !zdgcsList.isEmpty()) {
				zdgcs = convertUtil.toDouble(zdgcsList.get(0), 0D);
			}
			if (zjgcs >= zdgcs) {
				objects[10] = 2;
				flag++;
			}
		}
		if (flag2 == objectsList.size() - flag) {
			for (Object[] objects : objectsList) {
				if ((Integer) objects[10] == 2)
					objects[10] = null;
			}
		}
		// 置施工单位综合评分和决算率
		for (Object[] objects : objectsList) {
			Tf01_wxdw tf01 = (Tf01_wxdw) objects[0];
			List<Long> tmpList = (List<Long>) dao
					.search("select zhdf from Tf27_wxdwzhpf where wxdw_id="
							+ tf01.getId() + " order by cjrq desc");
			if (!tmpList.isEmpty()) {
				objects[2] = convertUtil.toLong(tmpList.get(0));
			} else {
				objects[2] = 0L;
			}
			// objs[3] 决算率
			// 决算率默认100%
			objects[3] = 0D;
			// 总项目数量
			long xmsl = ((List<Long>) dao
					.search("select count(*) from Td01_xmxx where sgdw='"
							+ tf01.getMc() + "'")).get(0);
			if (xmsl != 0) {
				// 决算项目数量
				long jssl = ((List<Long>) dao
						.search("select count(*) from Td01_xmxx where jssj is not null and sgdw='"
								+ tf01.getMc() + "'")).get(0);
				// 决算率=决算数/总数
				objects[3] = (double) jssl / (double) xmsl;
			}
		}
		// 置偏差率和偏差率档级
		for (Object[] o : objectsList) {
			o[8] = ((Double) o[6] - (Double) o[7]) / (Double) o[6] * 100;
			o[9] = convertUtil.toString(dao.search(
					"select dj from Tf11_fepcl where qzsx>=" + o[8]
							+ " and (qzxx<" + o[8] + " or qzxx is null)")
					.get(0));
		}
		List<Object[]> allList = new ArrayList<Object[]>(objectsList);
		modelMap.put("allList", allList);
		for (int i = objectsList.size() - 1; i >= 0; i--) {
			if (objectsList.get(i)[10] != null) {
				objectsList.remove(i);
			}
		}
		// 将objs[2]objs[3]分别冒泡排序
		// 先按objs[2]排 从低到高
		Object[] temp;
		for (int i = 0; i < objectsList.size(); i++) {
			for (int j = objectsList.size() - 1; j > i; j--) {
				if ((Long) objectsList.get(j)[2] > (Long) objectsList
						.get(j - 1)[2]) {
					temp = objectsList.get(j);
					objectsList.set(j, objectsList.get(j - 1));
					objectsList.set(j - 1, temp);
				}
			}
		}
		// 综合评分排名
		for (int i = 1; i <= objectsList.size(); i++) {
			if (i == 1
					|| !objectsList.get(i - 2)[2]
							.equals(objectsList.get(i - 1)[2]))
				objectsList.get(i - 1)[4] = i;
			else {
				objectsList.get(i - 1)[4] = objectsList.get(i - 2)[4];
			}
		}
		// 决算率 从低到高
		for (int i = 0; i < objectsList.size(); i++) {
			for (int j = objectsList.size() - 1; j > i; j--) {
				if ((Double) objectsList.get(j)[3] > (Double) objectsList
						.get(j - 1)[3]) {
					temp = objectsList.get(j);
					objectsList.set(j, objectsList.get(j - 1));
					objectsList.set(j - 1, temp);
				}
			}
		}
		// 决算率排名
		for (int i = 1; i <= objectsList.size(); i++) {
			if (i == 1
					|| !objectsList.get(i - 2)[3]
							.equals(objectsList.get(i - 1)[3])) {
				objectsList.get(i - 1)[5] = i;
			} else {
				objectsList.get(i - 1)[5] = objectsList.get(i - 2)[5];
			}
		}
		// 利用数据库做排序
		Session session = dao.getHibernateSession();
		Transaction tx = session.beginTransaction();
		Long nextval = -1L;
		try {

			// o[0]:tf01;o[1]:tf05;o[2]:zhdf(综合得分);o[3]:决算率;o[4]:综合得分排名;o[5]:决算率排名;o[6]:计划份额;o[7]:实际份额;o[8]:份额偏差率;o[9]:份额偏差率档级
			tx.begin();
			nextval = ((BigDecimal) (session
					.createSQLQuery("select batch_num.nextval from dual")
					.uniqueResult())).longValue();
			for (Object[] o : objectsList) {
				Tmp_zdxp zdxp = new Tmp_zdxp();
				zdxp.setDj((String) o[9]);
				zdxp.setPm((Integer) o[4] * 0.6 + (Integer) o[5] * 0.4);
				zdxp.setZhdf((Long) o[2]);
				zdxp.setJsl((Double) o[3]);
				zdxp.setJhfezb((Double) o[6]);
				zdxp.setWxdw_id((Long) ((Tf01_wxdw) o[0]).getId());
				zdxp.setBatch_no(nextval);
				session.save(zdxp);
			}
			session.flush();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		List<Long> wxdw_ids = (List<Long>) dao
				.search("select wxdw_id from Tmp_zdxp where batch_no="
						+ nextval
						+ " order by dj asc,pm asc,zhdf desc,jsl desc,jhfezb desc");
		// dao.update("delete from Tmp_zdxp where batch_no=" + nextval);
		List<Object[]> tmpList = new ArrayList<Object[]>();
		int i = 0;
		// 只保留前3名
		for (Long wxdw_id : wxdw_ids) {
			for (Object[] o : objectsList) {
				if (((Tf01_wxdw) o[0]).getId().longValue() == wxdw_id
						.longValue()) {
					tmpList.add(o);
					break;
				}
			}
			if (++i == 3) {
				break;
			}
		}
		objectsList = new ArrayList<Object[]>(tmpList);
		// objectsList已经是排前三名的施工单位了
		// 判断有否关联交易额 交易额度是否超过关联交易额
		Tf01_wxdw result = null;
		for (Object[] o : objectsList) {
			// gljye:关联交易额
			List<Double> tmpGljyeList = (List<Double>) dao
					.search("select v1 from Tf05_wxdw_dygx tf05 where wxdw_id="
							+ ((Tf01_wxdw) o[0]).getId()
							+ " and tf05.lb='gljye' and tf05.v1>0 and tf05.nd=to_char(sysdate,'yyyy')");
			Double gljye = 0D;
			if (!tmpGljyeList.isEmpty()) {
				gljye = convertUtil.toDouble(tmpGljyeList.get(0), 0D);
			}
			// jyed:交易额度
			List<Double> tmpJyedList = (List<Double>) dao
					.search("select sum(ys_sgf) from Td01_xmxx td00 where sgdw='"
							+ ((Tf01_wxdw) o[0]).getMc()
							+ "' and ssnd=to_char(sysdate,'yyyy')");
			Double jyed = 0D;
			if (!tmpJyedList.isEmpty()) {
				jyed = convertUtil.toDouble(tmpJyedList.get(0), 0D);
			}
			if (gljye > jyed) {
				result = (Tf01_wxdw) o[0];
				break;
			}
		}
		if (result == null) {
			result = (Tf01_wxdw) objectsList.get(0)[0];
		}
		modelMap.put("zdxp", result);
		return new ModelAndView("/WEB-INF/jsp/form/selectSgdw.jsp", modelMap);
	}

	@RequestMapping("/sgpftst.do")
	public ModelAndView sgpftst(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "mc");
		if (orderField.equals("")) {
			orderField = "mc";
		}
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		// 查询条件
		String mc = convertUtil.toString(request.getParameter("mc"));
		String zy = convertUtil.toString(request.getParameter("zy"));
		String dq = convertUtil.toString(request.getParameter("dq"));

		StringBuffer hsql = new StringBuffer();
		hsql.append("select vc1 from Vc1_sgpftst vc1 where 1=1");
		// where条件
		// 专业
		if (!zy.equals("")) {
			hsql.append(" and zy='" + zy + "'");
		}
		// 地区
		if (!dq.equals("")) {
			hsql.append(" and dq='" + dq + "'");
		}
		// 名称
		if (!mc.equals("")) {
			hsql.append(" and mc like '%" + mc + "%'");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		// 获取结果集
		List<Vc1_sgpftst> vc1List = new ArrayList<Vc1_sgpftst>();
		// 导EXCEL
		while (ro.next()) {
			vc1List.add((Vc1_sgpftst) ro.get("vc1"));
		}
		modelMap.put("vc1List", vc1List);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		modelMap.put("dqList", dao.search("from Tc02_area"));
		modelMap.put("zyList", dao
				.search("from Tc01_property where type='工程类别'"));
		return new ModelAndView("/WEB-INF/jsp/form/sgpftst.jsp", modelMap);
	}

	@RequestMapping("/sgpd/sgpfCompany.do")
	public ModelAndView sgpdfCompany(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "/WEB-INF/jsp/form/sgpf_company.jsp";
		ModelMap modelMap = new ModelMap();
		List ysryList = null;
		Ta03_user ysry = null;
		ResultObject ro = null;
		int totalCount = 0;
		int totalPages = 0;
		int pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		int numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "mc");
		String searchStr = convertUtil.toString(request
				.getParameter("searchStr"));
		String names = convertUtil.toString(request.getParameter("names"));
		String ids = convertUtil.toString(request.getParameter("ids"));
		String xm_id=convertUtil.toString(request.getParameter("xm_id"));
		List objList = new LinkedList();
		StringBuffer hql = new StringBuffer();
		hql.append("select w.id,w.mc from Tf01_wxdw w where 1=1 ");
		// 条件
		if (!searchStr.equals("")) {
			hql.append("and w.mc like '%");
			hql.append(searchStr);
			hql.append("%' ");
		}
		hql.append("order by w.");
		hql.append(orderField + " ");
		hql.append(orderDirection);
		ro = queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		if (ro != null) {
			totalCount = ro.getTotalRows();
			totalPages = ro.getTotalPages();
		}
		while (ro.next()) {
			Object obj[] = new Object[2];
			obj[0] = ro.get("w.id");
			obj[1] = ro.get("w.mc");
			objList.add(obj);
		}

		modelMap.put("objList", objList);
		modelMap.put("xm_id", xm_id);
		modelMap.put("names", names);
		modelMap.put("ids", ids);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("searchStr", searchStr);
		modelMap.put("orderDirection", orderDirection);
		modelMap.put("totalCount", totalCount);
		modelMap.put("totalPages", totalPages);
		return new ModelAndView(view, modelMap);

	}

	@RequestMapping("/sgdw/checkProject.do")
	public void checkProject(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		StringBuffer hql = new StringBuffer();
		List objList = new ArrayList();;
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String projectFlag = null;
		String name = convertUtil.toString(request.getParameter("name"));
		hql.append("select p.flag from Tc01_property p where type='工程类别' ");
		hql.append("and p.name='");
		hql.append(name);
		hql.append("' ");
		if (!(name.equals("") || name == null)) {
			objList = queryService.searchList(hql.toString());
			if (objList != null && objList.size() != 0) {
				for (Object object : objList) {
					if (object!=null) {
						projectFlag = object.toString();
					}
				}
			}
			out.print(projectFlag);
		}

	}
	 
}

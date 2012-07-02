package com.rms.controller.form;

import java.util.ArrayList;
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
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
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

	@SuppressWarnings("unchecked")
	@RequestMapping("/sgpd.do")
	public ModelAndView sgpd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(request.getParameter("project_id"),-10L);
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
			//System.out.println("找不到工程或项目");
			return new ModelAndView("/WEB-INF/jsp/form/selectSgdw.jsp?errormsg=tdnotfound");
		}
		// 获得 所有相关地区专业 未停工 类别为施工的外协单位
		List<Object[]> wxdwList = (List<Object[]>) dao
				.search("select tf01,tf05 from Tf01_wxdw tf01,Tf05_wxdw_dygx tf05 where tf01.id=tf05.wxdw_id and tf05.zy='"
						+ gclb
						+ "' and tf05.dq='"
						+ dq
						+ "' and tf05.lb='fezb' and tf05.v1>0 and tf05.nd=to_char(sysdate,'yyyy') and tf01.lb='施工' and tf01.zt<>'停工'");
		if (wxdwList == null || wxdwList.size() == 0) {
			//System.out.println("没有符合的外协单位");
			return new ModelAndView("/WEB-INF/jsp/form/selectSgdw.jsp?errormsg=tfnotfound");
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
		long zgr = convertUtil.toLong(dao.search(
				"select sum(ys_pggr + ys_jggr) from Td00_gcxx where sgdw is not null and ssdq='" + dq + "' and gclb='"
						+ gclb + "'").get(0), 0L);
		// flag:未通过检测的个数
		int flag = 0;
		// passedList 通过条件的外协单位 暂存入该LIST
		for (Object[] objects : objectsList) {
			Tf01_wxdw tf01 = (Tf01_wxdw) objects[0];
			Tf05_wxdw_dygx tf05 = (Tf05_wxdw_dygx) objects[1];
			// fezb:实际份额占比
			Double fezb = 0D;
			if (zgr != 0L) {
				long gr = convertUtil.toLong(dao.search(
						"select sum(ys_pggr + ys_jggr) from Td00_gcxx where sgdw='" + tf01.getMc() + "' and ssdq='"
								+ dq + "' and gclb='" + gclb + "'").get(0), 0L);
				fezb = (double) gr / (double) zgr;
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
					"select count(*) from Td01_xmxx where sgdw='" + ((Tf01_wxdw) objects[0]).getMc()
							+ "' and sjjgsj is null").get(0));
			// 最大工程数
			Long zdgcs = convertUtil.toLong(dao.search(
					"select v1 from Tf05_wxdw_dygx tf05 where tf05.wxdw_id=" + ((Tf01_wxdw) objects[0]).getId()
							+ " and tf05.zy='" + gclb
							+ "' and tf05.lb='zdgcs' and tf05.v1>0 and tf05.nd=to_char(sysdate,'yyyy')").get(0), 0L);
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
			List<Long> tmpList = (List<Long>) dao.search("select zhdf from Tf27_wxdwzhpf where wxdw_id=" + tf01.getId()
					+ " order by cjrq desc");
			if (!tmpList.isEmpty()) {
				objects[2] = convertUtil.toLong(tmpList.get(0));
			} else {
				objects[2] = 0L;
			}
			// objs[3] 决算率
			// 决算率默认100%
			objects[3] = 1D;
			// 总项目数量
			long xmsl = ((List<Long>) dao.search("select count(*) from Td01_xmxx where sgdw='" + tf01.getMc() + "'"))
					.get(0);
			if (xmsl != 0) {
				// 决算项目数量
				long jssl = ((List<Long>) dao.search("select count(*) from Td01_xmxx where jssj is not null and sgdw='"
						+ tf01.getMc() + "'")).get(0);
				// 决算率=决算数/总数
				objects[3] = (double) jssl / (double) xmsl;
			}
		}
		// 置偏差率和偏差率档级
		for (Object[] o : objectsList) {
			o[8] = ((Double) o[6] - (Double) o[7]) / (Double) o[6] * 100;
			o[9] = convertUtil.toString(dao.search(
					"select dj from Tf11_fepcl where qzsx>=" + o[8] + " and (qzxx<" + o[8] + " or qzxx is null)")
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
				if ((Long) objectsList.get(j)[2] > (Long) objectsList.get(j - 1)[2]) {
					temp = objectsList.get(j);
					objectsList.set(j, objectsList.get(j - 1));
					objectsList.set(j, temp);
				}
			}
		}
		// 综合评分排名
		for (int i = 1; i <= objectsList.size(); i++) {
			objectsList.get(i - 1)[4] = i;
		}
		// 决算率 从低到高
		for (int i = 0; i < objectsList.size(); i++) {
			for (int j = objectsList.size() - 1; j > i; j--) {
				if ((Long) objectsList.get(j)[3] > (Long) objectsList.get(j - 1)[3]) {
					temp = objectsList.get(j);
					objectsList.set(j, objectsList.get(j - 1));
					objectsList.set(j, temp);
				}
			}
		}
		// 决算率排名
		for (int i = 1; i <= objectsList.size(); i++) {
			objectsList.get(i - 1)[5] = i;
		}
		// 利用数据库做排序
		Session session = dao.getHibernateSession();
		Transaction tx = session.beginTransaction();
		try {
			// o[0]:tf01;o[1]:tf05;o[2]:zhdf(综合得分);o[3]:决算率;o[4]:综合得分排名;o[5]:决算率排名;o[6]:计划份额;o[7]:实际份额;o[8]:份额偏差率;o[9]:份额偏差率档级
			tx.begin();
			for (Object[] o : objectsList) {
				Tmp_zdxp zdxp = new Tmp_zdxp();
				zdxp.setDj((String) o[9]);
				zdxp.setPm((Integer) o[4] * 0.6 + (Integer) o[5] * 0.4);
				zdxp.setZhdf((Long) o[2]);
				zdxp.setJsl((Double) o[3]);
				zdxp.setJhfezb((Double) o[6]);
				zdxp.setWxdw_id((Long) ((Tf01_wxdw) o[0]).getId());
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
				.search("select wxdw_id from Tmp_zdxp order by dj asc,pm desc,zhdf desc,jsl desc,jhfezb desc");
		dao.update("delete from Tmp_zdxp");
		List<Object[]> tmpList = new ArrayList<Object[]>();
		int i = 0;
		// 只保留前3名
		for (Long wxdw_id : wxdw_ids) {
			for (Object[] o : objectsList) {
				if (((Tf01_wxdw) o[0]).getId().longValue() == wxdw_id.longValue()) {
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
			List<Double> tmpGljyeList = (List<Double>) dao.search("select v1 from Tf05_wxdw_dygx tf05 where wxdw_id="
					+ ((Tf01_wxdw) o[0]).getId()
					+ " and tf05.lb='gljye' and tf05.v1>0 and tf05.nd=to_char(sysdate,'yyyy')");
			Double gljye = 0D;
			if (!tmpGljyeList.isEmpty()) {
				gljye = convertUtil.toDouble(tmpGljyeList.get(0), 0D);
			}
			// jyed:交易额度
			List<Double> tmpJyedList = (List<Double>) dao.search("select sum(ys_je) from Td00_gcxx td00 where sgdw='"
					+ ((Tf01_wxdw) o[0]).getMc() + "' and ssnd=to_char(sysdate,'yyyy')");
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
}

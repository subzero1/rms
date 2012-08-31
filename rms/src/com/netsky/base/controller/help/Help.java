package com.netsky.base.controller.help;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Properties;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.RegExp;
import com.netsky.base.controller.OperFile;

/**
 * @description: ֪ʶ�����
 * @class name:com.crht.controller.business.Repository
 * @author lee.xiangyu Apr. 2, 2011
 */
@Controller
public class Help  {

	/**
	 * ���ݷ���
	 */
	@Autowired
	private Dao dao;

	@Autowired
	private ExceptionService exceptionService;

	/**
	 * ��ѯ����
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * ��ѯ����
	 */
	@Autowired
	private SaveService saveService;


	/**
	 * ֪ʶ���б�
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/business/repositoryList.do")
	public ModelAndView repositoryList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// ��ҳ����
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// �������
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		String orderType = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");

		// ��ѯ����
		String check_status = convertUtil.toString(request
				.getParameter("check_status"), "-1");
		String keywords = convertUtil.toString(
				request.getParameter("keywords"), "");
		String question = convertUtil.toString(
				request.getParameter("question"), "");
		String type = convertUtil.toString(request.getParameter("type"), "");
		String[] types = null;
		if (!type.equals("")) {
			types = type.split(",");
		}

		// ��ʾ����
		String quote = convertUtil.toString(request.getParameter("quote"), "");

		// ���ݿ���ر���
		QueryBuilder queryBuilder = null;
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		Class<?> clazz = null;

		// �������״̬�����б�
		List<Object> statusList = new LinkedList<Object>();
		Properties p = new Properties();
		p.setProperty("show", "δ���");
		p.setProperty("value", "");
		statusList.add(p);
		p = new Properties();
		p.setProperty("show", "���ͨ��");
		p.setProperty("value", "1");
		statusList.add(p);
		p = new Properties();
		p.setProperty("show", "���δͨ��");
		p.setProperty("value", "0");
		statusList.add(p);
		modelMap.put("statusList", statusList);

		// ���֪ʶ���б�
		//clazz = B07_repository.class;
		sql.delete(0, sql.length());
		sql.append("from B07_repository obj ");
		sql.append("where 1 = 1 ");

		// ���״̬������ʱֻ��ʾ���ͨ����
		if (!quote.equals("")) {
			sql.append("and status = 1 ");
		} else if (!check_status.equals("-1")) {
			sql.append("and status = ");
			sql.append(check_status);
			sql.append(" ");
		} else if (check_status.equals("-1")) {
			sql.append("and (status = -1 or status is null)");
		}

		// ����
		if (types != null && types.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < types.length; i++) {
				sql.append(" type like " + "'%");
				sql.append(types[i]);
				sql.append("%' or ");
			}
			sql.delete(sql.length() - 3, sql.length());
			sql.append(")");
			// sql.d
		}

		// �ؼ��֡������������������
		if (!keywords.equals("")) {
			sql.append("and key like '%");
			sql.append(keywords);
			sql.append("%' ");
		}

		// ��������
		if (!question.equals("")) {
			sql.append(" and  question like '%");
			sql.append(question);
			sql.append("%'  ");
		}
		sql.append(" order by ");
		sql.append(orderField);
		sql.append(" ");
		sql.append(orderType);

		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum,
				numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderType", orderType);

		List<Object> repositoryList = new LinkedList<Object>();
		while (ro.next()) {
			repositoryList.add(ro.get("obj"));
		}

		modelMap.put("repositoryList", repositoryList);

		if (quote.equals("")) {
			return new ModelAndView("/jsp/business/repositoryList.jsp",
					modelMap);
		} else {
			return new ModelAndView("/jsp/business/repositoryQuote.jsp",
					modelMap);
		}
	}

	/**
	 * ֪ʶ��༭
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/business/repositoryEdit.do")
	public ModelAndView repositoryEdit(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// ���ݿ���ر���
//		QueryBuilder queryBuilder = null;
//		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
//		Class<?> clazz = null;
//
//		// ��ѯ����
//		Long id = convertUtil.toLong(request.getParameter("id"), new Long(-1));
//
//		// ����֪ʶ����������б�
//		List<?> typeList = null;
//		clazz = B05_property.class;
//		queryBuilder = new HibernateQueryBuilder(clazz);
//		queryBuilder.eq("type", "֪ʶ�����");
//		queryBuilder.addOrderBy(Order.asc("id"));
//		typeList = queryService.searchList(queryBuilder);
//		modelMap.put("typeList", typeList);
//
//		// ��ǰʱ��
//		modelMap.put("now", DateGetUtil.getCurTime());
//
//		// ��ȡ֪ʶ�����
//		B07_repository b07 = null;
//		clazz = B07_repository.class;
//		b07 = (B07_repository) dao.getObject(clazz, id);
//		modelMap.put("repository", b07);

		return new ModelAndView("/jsp/business/repositoryEdit.jsp", modelMap);
	}

	/**
	 * ֪ʶ��ɾ��ajaxʵ��
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/business/ajaxRepositoryDel.do")
	public void ajaxRepositoryDel(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);

		// ��ȡ�û�����
		try {
//			out = response.getWriter();
//			dao.removeObject(B07_repository.class, id);
//
//			/**
//			 * ɾ������
//			 */
//			OperFile of = new OperFile();
//			of.setQueryService(queryService);
//			of.setSaveService(saveService);
//
//			String sql = "select id from D20_slave where doc_id = " + id;
//			ResultObject ro = queryService.search(sql);
//			while (ro.next()) {
//				request.setAttribute("slave_id", ro.get("id"));
//				of.delfile(request, response);
//				dao.update("delete from D20_slave where doc_id = "
//						+ (Long) ro.get("id"));
//			}
//
//			out
//					.print("{\"statusCode\":\"200\", \"message\":\"֪ʶ��ɾ���ɹ�\", \"navTabId\":\"\", \"forwardUrl\":\"business/repositoryList.do\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.crht.controller.business.Repository", "֪ʶ��ɾ��ʧ��", e);
		}
	}

	/**
	 * ֪ʶ�Ᵽ��ajaxʵ��
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/business/ajaxRepositorySave.do")
	public void ajaxRepositorySave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		StringBuffer hsql = new StringBuffer("");

		//S02_user user = (S02_user) session.getAttribute("user");

		try {

//			if (user != null) {
//
//				Long id = convertUtil.toLong(request
//						.getParameter("B07_repository.ID"), -1L);
//				String key = request.getParameter("B07_repository.KEY");
//				String type = request.getParameter("B07_repository.TYPE");
//				String status = request.getParameter("B07.STATUS");
//				String question = request
//						.getParameter("B07_repository.QUESTION");
//				String creator = request.getParameter("B07_repository.CREATOR");
//				String create_time = request
//						.getParameter("B07_repository.CREATE_TIME");
//				Transaction tx = null;
//
//				/**
//				 * ����֪ʶ��
//				 */
//				B07_repository b07 = (B07_repository) dao.getObject(
//						B07_repository.class, id);
//				if (b07 == null) {
//					b07 = new B07_repository();
//				}
//				b07.setKey(key);
//				b07.setQuestion(question);
//				b07.setStatus(convertUtil.toInteger(status));
//				b07.setType(type);
//				b07.setCreate_time(new SimpleDateFormat("yyyy-MM-dd")
//						.parse(create_time));
//				b07.setCreator(creator);
//
//				if (b07.getStatus() == 1 && b07.getCode() == null) {
//
//					/**
//					 * ����֪ʶ����
//					 */
//					String code = null;
//					hsql.append("select max(code) ");
//					hsql.append("from B07_repository ");
//					List list = dao.search(hsql.toString());
//					String max_code = (String) list.get(0);
//
//					if (max_code == null) {
//						code = "0001";
//					} else {
//						Long tmpSerialCode = new Long(max_code) + 1;
//						code = StringFormatUtil.getCompleteString(tmpSerialCode
//								.toString(), 4);
//					}
//					b07.setCode(code);
//				}
//				dao.saveObject(b07);
//				Long b07_id = b07.getId();
//
//				out
//						.print("{\"statusCode\":\"200\",\"message\":\"����֪ʶ����Ϣ�ɹ�\",\"navTabId\":\"\",\"callbackType\":\"forward\",\"forwardUrl\":\"business/repositoryEdit.do?id="
//								+ b07_id + "\"}");
//			} else {
//				out
//						.print("{\"statusCode\":\"301\",\"message\":\"�Ự��ʱ�����µ�¼\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
//			}

		} catch (Exception e) {
			e.printStackTrace();
			out
					.print("{\"statusCode\":\"300\",\"message\":\"����֪ʶ����Ϣʧ��\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
		} finally {
			// session.flush();
			// tx.commit();
			// session.close();
		}
	}

	/**
	 * ֪ʶ�Ᵽ��ajaxʵ��
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/business/ajaxRepDetailSave.do")
	public void ajaxRepDetailSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		out = response.getWriter();
		StringBuffer hsql = new StringBuffer("");

		//S02_user user = (S02_user) session.getAttribute("user");

		try {

//			if (user != null) {
//
//				Long id = convertUtil.toLong(request
//						.getParameter("B08_rep_detail.ID"), -1L);
//				String title = convertUtil.toString(request
//						.getParameter("B08_rep_detail.TITLE"));
//				String remark = convertUtil.toString(request
//						.getParameter("B08_rep_detail.REMARK"));
//				String seq = convertUtil.toString(request
//						.getParameter("B08_rep_detail.SEQ"));
//				String rep_id = convertUtil.toString(request
//						.getParameter("rep_id"));
//				String up_id = convertUtil.toString(request
//						.getParameter("up_id"));
//
//				Transaction tx = null;
//
//				/**
//				 * ����֪ʶ��
//				 */
//				B08_rep_detail b08 = (B08_rep_detail) dao.getObject(
//						B08_rep_detail.class, id);
//				if (b08 == null) {
//					b08 = new B08_rep_detail();
//				}
//				B07_repository t_b07 = new B07_repository();
//				if (rep_id != null && !rep_id.equals("")) {
//					t_b07.setId(new Long(rep_id));
//				} else {
//					t_b07 = null;
//				}
//
//				B08_rep_detail t_b08 = new B08_rep_detail();
//				if (up_id != null && !up_id.equals("")) {
//					t_b08.setId(new Long(up_id));
//				} else {
//					t_b08 = null;
//				}
//
//				/*
//				 * ���������ַ�
//				 */
//				remark = remark.replaceAll("</p><p\\s*[^>]*>", "<br>");// �س�
//				remark = remark.replaceAll("</p>", "");// �ֶ�
//				remark = remark.replaceAll("<p\\s*[^>]*>", "");// �ֶ�
//				remark = remark.replaceAll("<\\s*a\\s+[^>]*>", "");// ����
//				remark = remark.replaceAll("<a>", "");// ����
//				remark = remark.replaceAll("</a>", "");// ����
//
//				b08.setTitle(title);
//				b08.setRemark(remark);
//				b08.setRepository(t_b07);
//				b08.setRepDetail(t_b08);
//				b08.setSeq(new Integer(-1));
//				dao.saveObject(b08);
//
//				/**
//				 * ����˳��
//				 */
//				StringBuffer sql = new StringBuffer("");
//				sql.delete(0, sql.length());
//				sql.append("select b08 from B08_rep_detail b08 ");
//				sql.append(" where seq >= ");
//				sql.append(seq);
//				if (up_id == null || up_id.equals("")) {
//					sql.append(" and b08.repDetail.id is null ");
//				} else {
//					sql.append(" and b08.repDetail.id = ");
//					sql.append(up_id);
//				}
//				sql.append(" order by seq ");
//				ResultObject ro = queryService.search(sql.toString());
//				while (ro.next()) {
//					B08_rep_detail o = (B08_rep_detail) ro.get("b08");
//					o.setSeq(o.getSeq() + 1);
//					saveService.save(o);
//				}
//				b08.setSeq(convertUtil.toInteger(seq));
//				dao.saveObject(b08);
//
//				resetSeq(b08.getId());
//
//				/**
//				 * ����֪ʶ�⸽��
//				 */
//				RegExp regExp = new RegExp();
//				Vector v_slave_id = regExp
//						.pickupAll(
//								"<img(\\s+[a-z]+=\"[^\"]*\")*\\s+src=\"/[a-zA-Z]+_[a-zA-Z]+/download.do\\?slave_id=(\\d+)\"(\\s+[a-z]+=\"[^\"]*\")*\\s+/>",
//								remark, 2);
//				if (v_slave_id != null && v_slave_id.size() > 0) {
//					for (int i = 0; i < v_slave_id.size(); i++) {
//						dao.update("update D20_slave set doc_id = "
//								+ b08.getId() + " where id = "
//								+ v_slave_id.get(i));
//					}
//				}
//
//				out
//						.print("{\"statusCode\":\"200\",\"message\":\"����֪ʶ����Ϣ�ɹ�\",\"navTabId\":\"repositoryEdit\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
//			} else {
//				out
//						.print("{\"statusCode\":\"301\",\"message\":\"�Ự��ʱ�����µ�¼\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
//			}

		} catch (Exception e) {
			e.printStackTrace();
			out
					.print("{\"statusCode\":\"300\",\"message\":\"����֪ʶ����Ϣʧ��\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
		} finally {
			// session.flush();
			// tx.commit();
			// session.close();
		}
	}

	
	/**
	 * ֪ʶ����Ϣ��ʾ
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/business/repositoryDisp.do")
	public ModelAndView repositoryDisp(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

//		// ���ݿ���ر���
//		QueryBuilder queryBuilder = null;
//		StringBuffer sql = new StringBuffer("");
//		ModelMap modelMap = new ModelMap();
//		Class<?> clazz = null;
//
//		// ��ѯ����
//		Long id = convertUtil.toLong(request.getParameter("id"), new Long(-1));
//
//		// ��ȡ֪ʶ�����
//		B07_repository b07 = null;
//		clazz = B07_repository.class;
//		b07 = (B07_repository) dao.getObject(clazz, id);
//		modelMap.put("repository", b07);

		//return new ModelAndView("/jsp/business/repositoryDisp.jsp", modelMap);
		return null;
	}

	
	
}

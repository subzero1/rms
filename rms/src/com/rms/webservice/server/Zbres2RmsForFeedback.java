package com.rms.webservice.server;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateFormatUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.rms.dataObjects.form.Td01_xmxx;

@Service("zbres2RmsForFeedback")
public class Zbres2RmsForFeedback {
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SaveService saveService;
	
	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	public String feedback(String inParam)throws Exception{
		
	  try {
			if(inParam == null){
				throw new Exception ("not find inParam");
			}
			
			// 分析入参
			SAXBuilder builder = null;
			Document doc = null;
			Element root = null;
			builder = new SAXBuilder();
			Reader in = new StringReader(inParam);
			try {
				doc = builder.build(in);
			} catch (IOException ioe) {
				throw new Exception(ioe);
			} catch (JDOMException jdome) {
				throw new Exception(jdome);
			}
			in.close();

			root = doc.getRootElement();
			String xmbh = root.getChildText("xmbh");
			if(xmbh == null){
				throw new Exception ("not find xmbh node in inParam");
			}
			String checkType = root.getChild("yslx").getText();
			String checkResult = root.getChild("ysjg").getText();
			String checkDate = root.getChild("yssj").getText();
			QueryBuilder queryBuilder = new HibernateQueryBuilder(Td01_xmxx.class);
			queryBuilder.eq("xmbh", xmbh);
			ResultObject ro = queryService.search(queryBuilder);
			if(ro.next()){
				Td01_xmxx td01 = (Td01_xmxx)ro.get(Td01_xmxx.class.getName());
				if(checkType.equals("zyys")){
					td01.setYczyyssj(DateFormatUtil.ForamteString(checkDate, "yyyy-MM-dd"));
					td01.setXmzt("资源验收完成");
				}
				else{
					td01.setYssj(DateFormatUtil.ForamteString(checkDate, "yyyy-MM-dd"));
					td01.setXmzt("现场验收完成");
				}
				saveService.save(td01);
			}
			return "success";
		} catch (Exception e) {
			log.error("Zbres2RmsForFeedback.error="+e+e.getMessage());
			return "fail";
		}
	}
}

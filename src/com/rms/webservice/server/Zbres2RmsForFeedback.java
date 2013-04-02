package com.rms.webservice.server;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.rms.dataObjects.form.Td01_xmxx;

@Service("zbres2RmsForFeedback")
public class Zbres2RmsForFeedback {
	
	@Autowired
	private QueryService queryService;
	
	public String feedback(String inParam)throws Exception{
		if(inParam == null){
			
		}
		// 分析出参，并获得 $processInstId
//		SAXBuilder builder = null;
//		Document doc = null;
//		Element root = null;
//
//		builder = new SAXBuilder();
//		Reader in = new StringReader(inParam);
//		try {
//			doc = builder.build(in);
//		} catch (IOException ioe) {
//			throw new Exception(ioe);
//		} catch (JDOMException jdome) {
//			throw new Exception(jdome);
//		}
//		in.close();

		try {
//			root = doc.getRootElement();
//			String xmbh = root.getChildText("xmbh");
//			String checkType = root.getChild("yslx").getText();
//			String checkResult = root.getChild("ysjg").getText();
			QueryBuilder queryBuilder = new HibernateQueryBuilder(Td01_xmxx.class);
			queryBuilder.eq("xmbh", "aa");
			ResultObject ro = queryService.search(queryBuilder);
			return inParam;
			
		} catch (Exception e) {
			return e+e.getMessage();
		}
	}
}

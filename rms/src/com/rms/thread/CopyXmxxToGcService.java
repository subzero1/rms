package com.rms.thread;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netsky.base.dataObjects.interfaces.ThreadServiceInterface;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.dataObjects.form.Td01_xmxx;

@Service("copyXmxxToGcService")
public class CopyXmxxToGcService implements ThreadServiceInterface {
	
	/**
	 * 数据库保存操作服务
	 */
	@Autowired
	private SaveService saveService;
	
	@Autowired
	private QueryService queryService;
	
	public void handle(Map<String, String> paramMap) throws Exception {
		
		Long xm_id = convertUtil.toLong(paramMap.get("xm_id"));
		if(xm_id == null || xm_id == -1){
			throw new Exception(";xm_id is null");
		}
		Td01_xmxx td01 = (Td01_xmxx)queryService.searchById(Td01_xmxx.class, xm_id);
		if(td01 == null){
			throw new Exception("xm_id="+xm_id+":未找到此项目");
		}
		else{
			
			QueryBuilder queryBuilder = new HibernateQueryBuilder(Td00_gcxx.class);
			queryBuilder.eq("xm_id", xm_id);
			ResultObject ro = queryService.search(queryBuilder);
			while(ro.next()){
				Td00_gcxx td00 = (Td00_gcxx)ro.get(Td00_gcxx.class.getName());
				
				/*
				 * 同步工程信息和项目信息一致
				 */
				td00.setSgdw(td01.getSgdw());//施工单位
				td00.setSgd(td01.getSgd());//施工队
				td00.setSgfzr(td01.getSgfzr());//施工负责人
				td00.setSgfzrdh(td01.getSgfzrdh());//施工负责人电话
				td00.setSgpfsj(td01.getSgpfsj());//施工派发时间
				td00.setSjkgsj(td01.getSjkgsj());//实际开工时间
				td00.setSjjgsj(td01.getSjjgsj());//实际竣工时间
				
				td00.setJldw(td01.getJldw());//监理单位
				td00.setJlgcs(td01.getJlgcs());//监理工程师
				td00.setJlgcsdh(td01.getJlgcsdh());//监理工程师电话
				td00.setJlpfsj(td01.getJlpfsj());//监理派发时间
				saveService.save(td00);
				
				/*
				 * 办结工程流程节点
				 */
				saveService.updateByHSql("update Tb15_docflow set doc_status=8 where doc_status <> 8 and project_id = "+td00.getId());
				saveService.updateByHSql("update tb12_opernode set node_status=8 where node_status <> 8 and project_id = "+td00.getId());
			}
		}
	}
}

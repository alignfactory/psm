package com.psmis.server.app.acc;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.psmis.client.service.ServiceRequest;
import com.psmis.client.service.ServiceResult;
import com.psmis.client.ui.AbstractDataModel;
import com.psmis.server.com.data.UpdateDataModel;

public class Season {

	private String mapperName  = "acc01_season"; 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long seasionId = request.getLong("sessonId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectById", seasionId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", companyId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<AbstractDataModel> updateModel = new UpdateDataModel<AbstractDataModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<AbstractDataModel> updateModel = new UpdateDataModel<AbstractDataModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}

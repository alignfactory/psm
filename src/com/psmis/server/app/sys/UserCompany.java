package com.psmis.server.app.sys;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.psmis.client.app.sys.model.UserCompanyModel;
import com.psmis.client.service.ServiceRequest;
import com.psmis.client.service.ServiceResult;
import com.psmis.client.ui.AbstractDataModel;
import com.psmis.server.com.data.UpdateDataModel;

public class UserCompany { 
	
	String mapperName = "sys07_user_company"; 
	
	public void selectByUserId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long userId = request.getLong("userId"); 
		List<AbstractDataModel> userCompanyList = sqlSession.selectList(mapperName + ".selectByUserId", userId) ;
		result.setRetrieveResult(1, "사용자별 고객권한 조회", userCompanyList);
	}
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<UserCompanyModel> updateModel = new UpdateDataModel<UserCompanyModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}
	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<UserCompanyModel> updateModel = new UpdateDataModel<UserCompanyModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
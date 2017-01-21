package com.psmis.server.app.sys;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.psmis.client.app.sys.model.UserModel;
import com.psmis.client.app.sys.model.UserRoleModel;
import com.psmis.client.service.ServiceRequest;
import com.psmis.client.service.ServiceResult;
import com.psmis.client.ui.AbstractDataModel;
import com.psmis.server.com.data.UpdateDataModel;

public class UserRole { 
	
	String mapperName = "sys06_user_role"; 
	
	public void selectByUserId(SqlSession sqlSession, ServiceRequest request,
			ServiceResult result) {

		Long userId = request.getLong("userId"); 
		System.out.println("user id is " + userId); 
		
		List<AbstractDataModel> userRoleList = sqlSession.selectList(mapperName + ".selectByUserId", userId) ;
		result.setRetrieveResult(1, "사용자별 메뉴권한 조회", userRoleList);
	}

//	public void insert(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
//		UpdateDataModel<UserRoleModel> updateModel = new UpdateDataModel<UserRoleModel>();
//		updateModel.insertModel(sqlSession, request.getList(), mapperName, result);
//	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<UserModel> updateModel = new UpdateDataModel<UserModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}
	
	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<UserRoleModel> updateModel = new UpdateDataModel<UserRoleModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
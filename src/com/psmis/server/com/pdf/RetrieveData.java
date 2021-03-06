package com.psmis.server.com.pdf;

import java.lang.reflect.Method;
import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.apache.ibatis.session.SqlSession;

import com.psmis.client.service.ServiceRequest;
import com.psmis.client.service.ServiceResult;
import com.psmis.server.com.DatabaseFactory;


public class RetrieveData  {

	public RetrieveData(){
	}
	
	public ServiceResult retrieve(ServiceRequest request) throws IllegalArgumentException {
		
		SqlSession sqlSession = null;
		ServiceResult result = new ServiceResult(); 
	    result.setServiceName(request.getServiceName());
	    
		try {   
		    sqlSession = DatabaseFactory.openSession();
		}
		catch(RuntimeSqlException e ){
			result.fail(-1, "RuntimeSqlException fail: " + request.getServiceName());
		    e.printStackTrace();
		}
		catch(Exception e ){
			result.fail(-1, "Database session fail: " + request.getServiceName());
		    e.printStackTrace();

		    if(sqlSession != null){
				sqlSession.clearCache();
			    sqlSession.close();
		    }
		    return result ;
		}
		
		try { 
			
			String serviceName = request.getServiceName();
			
			String className = serviceName.substring(0,  serviceName.lastIndexOf("."));  
			String methodName =  serviceName.substring(serviceName.lastIndexOf(".") + 1);

			Class<?> loadClass = Class.forName("com.psmis.server.app." + className);
			Object executor = loadClass.newInstance(); 

			Method method 
		    	= executor.getClass().getMethod(methodName, new Class[]{SqlSession.class, ServiceRequest.class, ServiceResult.class}); 
			method.invoke(executor, sqlSession, request, result);

		    if(result.getStatus() < 0){
 				sqlSession.rollback(true);
 			}
 			else {
 				sqlSession.commit(true);
 			}

			return result ;
		}
	    catch(ClassNotFoundException e){
	    	result.fail(-1, "service not found:" + request.getServiceName());
	    	e.printStackTrace();
			return result ;
	    }
		catch(Exception e ){
		    e.printStackTrace();
			return result ;
		}
		finally {
			if(sqlSession != null){
				sqlSession.rollback(true);
				sqlSession.clearCache();
			    sqlSession.close();
		    }
		} 
	}
}

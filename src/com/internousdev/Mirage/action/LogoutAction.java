package com.internousdev.Mirage.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.Mirage.dao.CartInfoDAO;
import com.internousdev.Mirage.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {
	private String categoryId;
	private Map<String,Object> session;

	public String execute(){
		String result = ERROR;
		UserInfoDAO userInfoDao = new UserInfoDAO();
		CartInfoDAO cartInfoDao = new CartInfoDAO();
		String loginId = String.valueOf(session.get("loginId"));
		String tempUserId = String.valueOf(session.get("tempUserId"));
		boolean savedLoginId = Boolean.valueOf(String.valueOf(session.get("savedLoginId")));
		int i = cartInfoDao.linkToTempUserId(String.valueOf(session.get("tempUserId")),String.valueOf(session.get("loginId")));
		String.valueOf(session.get("loginId"));
		int count = userInfoDao.logout(loginId);
        if(count > 0){
        	if(i >= 0){
        		session.clear();
        		session.put("savedLoginId", savedLoginId);
        		session.put("loginId", loginId);
        		session.put("tempUserId", tempUserId);
        	result = SUCCESS;
        	}
        }
        return result;
	}

	public String getCategoryId(){
		return categoryId;
	}
	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public Map<String, Object> getSession(){
		return session;
	}
	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}

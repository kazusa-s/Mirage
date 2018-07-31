package com.internousdev.Mirage.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.Mirage.dao.MCategoryDAO;
import com.internousdev.Mirage.dto.MCategoryDTO;
import com.internousdev.Mirage.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware {
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private String categoryId;
	private Map<String,Object> session;

	public String execute(){

		if(!(session.containsKey("loginId")) && !(session.containsKey("tempUserId"))){
			CommonUtility commonUtility = new CommonUtility();
			session.put("tempUserId", commonUtility.getRandomValue());
		}

		if(!session.containsKey("logined")){
			session.put("logined", 0);
		}

		if(!session.containsKey("mCategoryList")){
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}
        return SUCCESS;
	}

public String getCategoryId(){
	return categoryId;
}

public void setCategoryId(String categoryId){
	this.categoryId = categoryId;
}

public List<MCategoryDTO> getMCategoryDtoList(){
	return mCategoryDtoList;
}

public void setCategoryDtoList(List<MCategoryDTO> mCategoryDtoList){
	this.mCategoryDtoList = mCategoryDtoList;
}

public Map<String,Object> getSession(){
	return session;
}

public void setSession(Map<String,Object> session){
	this.session = session;
}



}

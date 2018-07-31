package com.internousdev.Mirage.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.Mirage.dao.CartInfoDAO;
import com.internousdev.Mirage.dao.DestinationInfoDAO;
import com.internousdev.Mirage.dao.MCategoryDAO;
import com.internousdev.Mirage.dao.ProductInfoDAO;
import com.internousdev.Mirage.dao.UserInfoDAO;
import com.internousdev.Mirage.dto.DestinationDTO;
import com.internousdev.Mirage.dto.MCategoryDTO;
import com.internousdev.Mirage.dto.ProductInfoDTO;
import com.internousdev.Mirage.dto.UserInfoDTO;
import com.internousdev.Mirage.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class LoginActioin extends ActionSupport implements SessionAware{
	private String categoryId;
	private String loginId;
	private String password;
	private boolean savedLoginId;

	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();

	private List<String> loginIdErrorMessageList = new ArrayList<String>();
	private List<String> passwordErrorMessageList = new ArrayList<String>();

	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();

	private Map<String,Object> session;

	public String execute(){
		String result = ERROR;
		session.put("loginIdErrorMessageList", "");
		session.put("passwordErrorMessageList", "");
		session.put("passwordIncorrectMessage", "");

		//ID保管チェック
		if(savedLoginId == true){
			session.put("savedLoginId", true);
			session.put("loginId", loginId);
		}else{
			session.put("savedLoginId", false);
			session.put("loginId", loginId);
		}

		//正規表現チェック
		InputChecker inputChecker = new InputChecker();
		loginIdErrorMessageList = inputChecker.doCheck("ログインID",loginId,1,8,true,false,false,true,false,false,false);
		passwordErrorMessageList = inputChecker.doCheck("パスワード",password,1,16,true,false,false,true,false,false,false);

		if(loginIdErrorMessageList.size()!=0 || passwordErrorMessageList.size()!=0){
			session.put("loginIdErrorMessageList", loginIdErrorMessageList);
			session.put("passwordErrorMessageList", passwordErrorMessageList);
			session.put("logined", 0);
		}

		//カテゴリリストチェック
		if(!session.containsKey("mCategoryList")){
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}

		//ログイン認証
		UserInfoDAO userInfoDao = new UserInfoDAO();
		if(userInfoDao.isExistsUserInfo(loginId,password)){
			//管理者チェック
			if(userInfoDao.adminCheck(loginId) > 0){
				ProductInfoDAO productInfoDao = new ProductInfoDAO();
				productInfoDtoList = productInfoDao.getProductInfoList();
				session.put("productInfoDtoList", productInfoDtoList);
				session.put("adminLogined", 1);
				result = "admin";
			}else if(userInfoDao.login(loginId,password) > 0){
				UserInfoDTO userInfoDto = userInfoDao.getUserInfo(loginId,password);
				session.put("logined", userInfoDto.getUserId());
				int count = 0;
				CartInfoDAO cartInfoDao = new CartInfoDAO();

				count = cartInfoDao.linkToLoginId(String.valueOf(session.get("tempUserId")),loginId);
				if(count > 0){






					DestinationInfoDAO destinationInfoDao = new DestinationInfoDAO();
					try{
						List<DestinationDTO> destinationInfoDtoList = new ArrayList<DestinationDTO>();
						Iterator<DestinationDTO> iterator = destinationInfoDtoList.iterator();
						destinationInfoDtoList = destinationInfoDao.getDestinationInfo(loginId);
						if(!(iterator.hasNext())){
							destinationInfoDtoList = null;
						}
						session.put("destinationInfoDtoList", destinationInfoDtoList);
					}catch(SQLException e){
						e.printStackTrace();
					}
					result = "settlement";
				}else{
					result = SUCCESS;
				}
			}
				session.put("logined", 1);
				session.put("passwordInccorectMessage", "");
			}else{
				session.put("passwordIncorrectMessage", "パスワードが間違っています。");
			}
			return result;



	}


	public String getCategoryId(){
		return categoryId;
	}
	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getLoginId(){
		return loginId;
	}
	public void setLoginId(String loginId){
		this.loginId = loginId;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public boolean isSavedLoginId(){
		return savedLoginId;
	}
	public void setSavedLoginId(boolean savedLoginId){
		this.savedLoginId = savedLoginId;
	}

	public List<MCategoryDTO> getMCategoryDtoList(){
		return mCategoryDtoList;
	}
	public void setMCategoryDtoList(List<MCategoryDTO> mCategoryDtoList){
		this.mCategoryDtoList = mCategoryDtoList;
	}

	public List<String> getLoginIdErrorMessageList(){
		return loginIdErrorMessageList;
	}
	public void setLoginIdErrorMessageList(List<String> loginIdErrorMessageList){
		this.loginIdErrorMessageList = loginIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList(){
		return passwordErrorMessageList;
	}
	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList){
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public List<ProductInfoDTO> getProductInfoDtoList(){
		return productInfoDtoList;
	}
	public void setProductInfoDtoList(List<ProductInfoDTO> productInfoDtoList){
		this.productInfoDtoList = productInfoDtoList;
	}

	public Map<String, Object> getSession(){
		return session;
	}
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

}

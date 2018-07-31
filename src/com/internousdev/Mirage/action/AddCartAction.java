package com.internousdev.Mirage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.Mirage.dao.CartInfoDAO;
import com.internousdev.Mirage.dao.MCategoryDAO;
import com.internousdev.Mirage.dto.CartInfoDTO;
import com.internousdev.Mirage.dto.MCategoryDTO;
import com.internousdev.Mirage.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware {

	private int productId;
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;
	private int price;
	private String productCount;
	private String releaseCompany;
	private Date releaseDate;
	private String productDescription;

	private String categoryId;
	private String priceOver;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private Map<String, Object> session;

	public String execute(){
		String result = ERROR;
		String userId = null;
		String tempUserId = null;
		session.put("checkListErrorMessageList", null);

		if(session.get("logined")==null){
			session.put("logined", 0);
		}
		//loginId tempUserId　を取得していない場合
		if(!(session.containsKey("loginId")) && !(session.containsKey("tempUserId"))){
			CommonUtility commonUtility = new CommonUtility();
			session.put("tempUserId", commonUtility.getRandomValue());
		}
		//ログインしている場合　loginId が userId
		if(session.get("logined").equals(1)){
			userId = String.valueOf(session.get("loginId"));
		}
		//ログインしていないが、一時ログインはしている場合  tempUserId が　userId
		if(!(session.get("logined").equals(1)) && session.containsKey("tempUserId")){
			userId = String.valueOf(session.get("tempUserId"));
			tempUserId = String.valueOf(session.get("tempUserId"));
		}

		if(productCount != null){
			productCount = String.valueOf((productCount.split(",",0))[0]);
		}

		CartInfoDAO cartInfoDao = new CartInfoDAO();
		int i = Integer.parseInt(String.valueOf(cartInfoDao.getTotalPrice(userId)));
		int count = 0;
		if(i<=1547483653){
			count = cartInfoDao.register(userId, tempUserId, productId, productCount, price);

			if(count > 0){
				session.put("createdestinationflg",false);
				result = SUCCESS;
			}
		}else{
			session.put("createdestinationflg", false);
			priceOver = "最大購入価格を越えるためキャンセルされました。";
			result = SUCCESS;
		}

		if(count > 0){
			session.put("createdestinationflg", false);
			result = SUCCESS;
		}else{
			if(!(session.containsKey("loginId")) && !(session.containsKey("tempUserId"))){
			   CommonUtility commonUtility = new CommonUtility();
			   session.put("tempUserId", commonUtility.getRandomValue());
			}

			if(!session.containsKey("logined")){
				session.put("logined",0);
			}

			if(!session.containsKey("mCategoryList")){
				MCategoryDAO mCategoryDao = new MCategoryDAO();
				mCategoryDtoList = mCategoryDao.getMCategoryList();
				session.put("mCategoryDtoList", mCategoryDtoList);
			}
		}

		List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();
		cartInfoDtoList = cartInfoDao.getCartInfoDtoList(userId);
		//イテレーターObjectの作成
		Iterator<CartInfoDTO> iterator = cartInfoDtoList.iterator();
		//cartDTOListが空の場合nullにする。
		if(!(iterator.hasNext())) {
			cartInfoDtoList = null;
		}
		session.put("cartInfoDtoList", cartInfoDtoList);
		//ユーザーごとのトータルプライスを求める
		int totalPrice = Integer.parseInt(String.valueOf(cartInfoDao.getTotalPrice(userId)));
		session.put("totalPrice", totalPrice);
		return result;
	}



	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNameKana() {
		return productNameKana;
	}
	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductCount() {
		return productCount;
	}
	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}
	public String getReleaseCompany() {
		return releaseCompany;
	}
	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getPriceOver() {
	    return priceOver;
	}

	public void setPriceOver(String priceOver) {
	    this.priceOver = priceOver;
	}


}

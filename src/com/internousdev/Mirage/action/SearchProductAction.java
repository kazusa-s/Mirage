package com.internousdev.Mirage.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.Mirage.dao.MCategoryDAO;
import com.internousdev.Mirage.dao.ProductInfoDAO;
import com.internousdev.Mirage.dto.MCategoryDTO;
import com.internousdev.Mirage.dto.PaginationDTO;
import com.internousdev.Mirage.dto.ProductInfoDTO;
import com.internousdev.Mirage.util.InputChecker;
import com.internousdev.Mirage.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class SearchProductAction extends ActionSupport implements SessionAware {
	private int categoryId;
	private String keywords;
	private String pageNo;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private List<String> keywordsErrorMessageList = new ArrayList<String>();
	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
	private Map<String,Object> session;
	ProductInfoDAO productInfoDAO = new ProductInfoDAO();

	public String execute() throws SQLException{
		String result = ERROR;

		InputChecker inputChecker = new InputChecker();
		if(keywords == null){
			keywords ="";
		}
		keywordsErrorMessageList = inputChecker.doCheck("SEARCH",keywords,0, 16, true, true, true, true, false, true, false);
	    session.put("keywordsErrorMessageList", keywordsErrorMessageList);

	    if(keywordsErrorMessageList.isEmpty()){
	    //categoryIdが 0 or 1 の時、すべてのカテゴリーからキーワード検索
	    	if(categoryId == 0 || categoryId == 1){
	    		result = SUCCESS;
	    		productInfoDtoList = productInfoDAO.getProductInfoListAll(keywords.replaceAll("　", " ").split(" "));
	    	}else{
	    		//categoryIdとキーワードから検索
	    		result = SUCCESS;
	    		productInfoDtoList = productInfoDAO.getProductInfoListByKeyWords(keywords.replaceAll("　", " ").split(" "),
						categoryId);
	    	}
	    }

	    Iterator<ProductInfoDTO> iterator = productInfoDtoList.iterator();

	    if(!(iterator.hasNext())){
	    	productInfoDtoList = null;
	    }
	    if(!session.containsKey("mCategoryList")){
	        MCategoryDAO mCategoryDao = new MCategoryDAO();
	        mCategoryDtoList = mCategoryDao.getMCategoryList();
	        session.put("mCategoryDtoList", mCategoryDtoList);
	    }
	    if(!(productInfoDtoList == null)){
	    	Pagination pagination = new Pagination();
	    	PaginationDTO paginationDTO = new PaginationDTO();
	    	if(pageNo == null){
	    		paginationDTO = pagination.initialize(productInfoDtoList, 6);
	    	}else{
	    		paginationDTO = pagination.getPage(productInfoDtoList, 6, pageNo);
	    	}
			session.put("productInfoDtoList", paginationDTO.getCurrentProductInfoPage());
			session.put("totalPageSize", paginationDTO.getTotalPageSize());
			session.put("currentPageNo", paginationDTO.getCurrentPageNo());
			session.put("totalRecordSize", paginationDTO.getTotalRecordSize());
			session.put("startRecordNo", paginationDTO.getStartRecordNo());
			session.put("endRecordNo", paginationDTO.getEndRecordNo());
			session.put("previousPage", paginationDTO.isPreviousPage());
			session.put("previousPageNo", paginationDTO.getPreviousPageNo());
			session.put("nextPage", paginationDTO.isNextPage());
			session.put("nextPageNo",paginationDTO.getNextPageNo());
	    }else{
	    	session.put("productInfoDtoList", null);
	    }

		if(keywordsErrorMessageList.size() > 0){
		session.put("keywordsErrorMessageList", keywordsErrorMessageList);
	    productInfoDtoList = null;
		session.put("productInfoDtoList",null);
		result = ERROR;
	}

	    return result;
	}
	public String getPageNo(){
		return pageNo;
	}

	public void setPageNo(String pageNo){
		this.pageNo =pageNo;
	}

	public List<MCategoryDTO> getmCategoryDtoList(){
		return mCategoryDtoList;
	}

	public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList){
		this.mCategoryDtoList = mCategoryDtoList;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public String getKeywords(){
		return keywords;
	}

	public void setKeywords(String keywords){
		this.keywords = keywords;
	}

	public List<String> getKeywordsErrorMessageList(){
		return keywordsErrorMessageList;
	}

	public void setKeywordsErrorMessageList(List<String> keywordsErrorMessageList){
		this.keywordsErrorMessageList = keywordsErrorMessageList;
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

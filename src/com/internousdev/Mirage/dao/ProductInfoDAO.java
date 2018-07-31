package com.internousdev.Mirage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.Mirage.dto.ProductInfoDTO;
import com.internousdev.Mirage.util.DBConnector;
import com.internousdev.Mirage.util.DateUtil;

public class ProductInfoDAO {

    //商品一覧取得メソッド

	public List<ProductInfoDTO> getProductInfoList(){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				ProductInfoDTO productInfoDto = new ProductInfoDTO();

				productInfoDto.setId(resultSet.getInt("id"));
				productInfoDto.setProductId(resultSet.getInt("product_id"));
				productInfoDto.setProductName(resultSet.getString("product_name"));
				productInfoDto.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDto.setProductDescription(resultSet.getString("product_description"));
				productInfoDto.setCategoryId(resultSet.getInt("category_id"));
				productInfoDto.setPrice(resultSet.getInt("price"));
				productInfoDto.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDto.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDto.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDto.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDto.setStatus(resultSet.getInt("status"));
				productInfoDto.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDto.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDto);


			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}

		return productInfoDtoList;

	}

	//商品詳細取得メソッド

	public ProductInfoDTO getProductInfo(int productId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();
		String sql = "select * from product_info where product_id=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));

			}

		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return productInfoDTO;
	}

	//詳細画面下部の商品を表示するメソッド

	public List<ProductInfoDTO> getProductInfoListByCategoryId(int categoryId,int productId,int limitOffset,int limitRowCount){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info where category_id=? and product_id not in(?) order by rand() limit ?,?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, limitOffset);
			preparedStatement.setInt(4, limitRowCount);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDTO);

			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return productInfoDtoList;
	}

	//キーワード検索メソッド

	public List<ProductInfoDTO> getProductInfoListAll(String[] keywordsList){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info where";
		boolean initializeFlag = true;
		for(String keyword : keywordsList){
			if(initializeFlag){
				sql += " (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			    initializeFlag = false;
			}else{
				sql += " and (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDTO);

			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return productInfoDtoList;
	}

	//キーワード + カテゴリー検索メソッド

	public List<ProductInfoDTO> getProductInfoListByKeyWords(String[] keywordsList,String categoryId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
		String sql ="select * from product_info where";

		boolean initializeFlag = true;
		for (String keyword : keywordsList){
			if(initializeFlag){
				sql += " category_id=" + categoryId + " and (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag = false;
			} else{
				sql += " and (product_name like '%" + keyword +"%' or product_name_kana like '%" + keyword + "%')";

			}
		}
		try{
		   PreparedStatement preparedStatement = connection.prepareStatement(sql);
		   ResultSet resultSet = preparedStatement.executeQuery();
		   while(resultSet.next()){
			    ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDTO);
		   }
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return productInfoDtoList;
	}

	//商品削除メソッド

	public int delete(int productId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		int count = 0;
		String sql = "delete from product_info where product_id=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);

			count = preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	//商品追加メソッド


	public int addProduct(String addproductId,String addproductName,String addproductNameKana,String addproductDescription,String addcategoryId,String addprice,String image_file_path,String image_file_name,String addreleaseDate,String addreleaseCompany) throws SQLException{
		final DateUtil dateUtil=new DateUtil();
		int result = 0;

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql="INSERT INTO product_info(product_id,product_name,product_name_kana,product_description,category_Id,price,image_file_path,image_file_name,release_date,release_company,regist_date)VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try{
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, addproductId);
			preparedStatement.setString(2, addproductName);
			preparedStatement.setString(3, addproductNameKana);
			preparedStatement.setString(4, addproductDescription);
			preparedStatement.setString(5, addcategoryId);
			preparedStatement.setString(6, addprice);
			preparedStatement.setString(7, image_file_path);
			preparedStatement.setString(8, image_file_name);
			preparedStatement.setString(9, addreleaseDate);
			preparedStatement.setString(10, addreleaseCompany);
			preparedStatement.setString(11, dateUtil.getDate());

			result=preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return result;
	}

	//商品更新
	public int updateProduct(String productName,String productNameKana,String productDescription, String categoryId, String price, String imageFilePath, String imageFileName, String releaseDate, String releaseCompany, int productId){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			String sql = "update product_info set product_name=?, product_name_kana=?,product_description=?,category_id=?,price=?,image_file_path=?,image_file_name=?,release_date=?,release_company=?,update_date=now() where product_id=?";
			int result = 0;

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, productName);
				preparedStatement.setString(2, productNameKana);
				preparedStatement.setString(3, productDescription);
				preparedStatement.setString(4, categoryId);
				preparedStatement.setString(5, price);
				preparedStatement.setString(6, imageFilePath);
				preparedStatement.setString(7, imageFileName);
				preparedStatement.setString(8, releaseDate);
				preparedStatement.setString(9, releaseCompany);
				preparedStatement.setInt(10, productId);


				result = preparedStatement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}

	//エラーメッセージ
	public String checkProductId(String productId){


		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String productIdError ="";
		String sql = "select * from product_info where product_id=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()){
				productIdError="すでに存在しているIDです。";
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}

		return productIdError;

	}

	public String checkProductName(String productName){


		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String productNameError ="";
		String sql = "select * from product_info where product_name=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				productNameError="すでに存在している商品名です。";
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}

		return productNameError;

	}


	public String checkProductNameKana(String productNameKana){


		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String productNameKanaError ="";
		String sql = "select * from product_info where product_name_kana=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNameKana);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()){
				productNameKanaError="すでに存在している商品名かなです。";
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}

		return productNameKanaError;

	}



}
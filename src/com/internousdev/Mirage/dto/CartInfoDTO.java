package com.internousdev.Mirage.dto;

import java.util.Date;

public class CartInfoDTO {

		private int id;
		private String userId;
		private String tempUserId;
		private int productId;
		private int productCount;
		private int price;
		private Date registDate;
		private Date updateDate;

		private String productName;
		private String productNameKana;
		private String productDescription;
		private int categoryId;
		private String imageFilePath;
		private String imageFileName;
		private Date releaseDate;
		private String releaseCompany;
		private String status;
		private int subtotal;


		/**
		 * idを取得します。
		 * @return id
		 */
		public int getId() {
		    return id;
		}
		/**
		 * idを設定します。
		 * @param id id
		 */
		public void setId(int id) {
		    this.id = id;
		}
		/**
		 * userIdを取得します。
		 * @return userId
		 */
		public String getUserId() {
		    return userId;
		}
		/**
		 * userIdを設定します。
		 * @param userId userId
		 */
		public void setUserId(String userId) {
		    this.userId = userId;
		}
		/**
		 * tempUserIdを取得します。
		 * @return tempUserId
		 */
		public String getTempUserId() {
		    return tempUserId;
		}
		/**
		 * tempUserIdを設定します。
		 * @param tempUserId tempUserId
		 */
		public void setTempUserId(String tempUserId) {
		    this.tempUserId = tempUserId;
		}
		/**
		 * productIdを取得します。
		 * @return productId
		 */
		public int getProductId() {
		    return productId;
		}
		/**
		 * productIdを設定します。
		 * @param productId productId
		 */
		public void setProductId(int productId) {
		    this.productId = productId;
		}
		/**
		 * productCountを取得します。
		 * @return productCount
		 */
		public int getProductCount() {
		    return productCount;
		}
		/**
		 * productCountを設定します。
		 * @param productCount productCount
		 */
		public void setProductCount(int productCount) {
		    this.productCount = productCount;
		}
		/**
		 * priceを取得します。
		 * @return price
		 */
		public int getPrice() {
		    return price;
		}
		/**
		 * priceを設定します。
		 * @param price price
		 */
		public void setPrice(int price) {
		    this.price = price;
		}
		/**
		 * registDateを取得します。
		 * @return registDate
		 */
		public Date getRegistDate() {
		    return registDate;
		}
		/**
		 * registDateを設定します。
		 * @param registDate registDate
		 */
		public void setRegistDate(Date registDate) {
		    this.registDate = registDate;
		}
		/**
		 * updateDateを取得します。
		 * @return updateDate
		 */
		public Date getUpdateDate() {
		    return updateDate;
		}
		/**
		 * updateDateを設定します。
		 * @param updateDate updateDate
		 */
		public void setUpdateDate(Date updateDate) {
		    this.updateDate = updateDate;
		}
		/**
		 * productNameを取得します。
		 * @return productName
		 */
		public String getProductName() {
		    return productName;
		}
		/**
		 * productNameを設定します。
		 * @param productName productName
		 */
		public void setProductName(String productName) {
		    this.productName = productName;
		}
		/**
		 * productNameKanaを取得します。
		 * @return productNameKana
		 */
		public String getProductNameKana() {
		    return productNameKana;
		}
		/**
		 * productNameKanaを設定します。
		 * @param productNameKana productNameKana
		 */
		public void setProductNameKana(String productNameKana) {
		    this.productNameKana = productNameKana;
		}
		/**
		 * productDescriptionを取得します。
		 * @return productDescription
		 */
		public String getProductDescription() {
		    return productDescription;
		}
		/**
		 * productDescriptionを設定します。
		 * @param productDescription productDescription
		 */
		public void setProductDescription(String productDescription) {
		    this.productDescription = productDescription;
		}
		/**
		 * categoryIdを取得します。
		 * @return categoryId
		 */
		public int getCategoryId() {
		    return categoryId;
		}
		/**
		 * categoryIdを設定します。
		 * @param categoryId categoryId
		 */
		public void setCategoryId(int categoryId) {
		    this.categoryId = categoryId;
		}
		/**
		 * imageFilePathを取得します。
		 * @return imageFilePath
		 */
		public String getImageFilePath() {
		    return imageFilePath;
		}
		/**
		 * imageFilePathを設定します。
		 * @param imageFilePath imageFilePath
		 */
		public void setImageFilePath(String imageFilePath) {
		    this.imageFilePath = imageFilePath;
		}
		/**
		 * imageFileNameを取得します。
		 * @return imageFileName
		 */
		public String getImageFileName() {
		    return imageFileName;
		}
		/**
		 * imageFileNameを設定します。
		 * @param imageFileName imageFileName
		 */
		public void setImageFileName(String imageFileName) {
		    this.imageFileName = imageFileName;
		}
		/**
		 * releaseDateを取得します。
		 * @return releaseDate
		 */
		public Date getReleaseDate() {
		    return releaseDate;
		}
		/**
		 * releaseDateを設定します。
		 * @param releaseDate releaseDate
		 */
		public void setReleaseDate(Date releaseDate) {
		    this.releaseDate = releaseDate;
		}
		/**
		 * releaseCompanyを取得します。
		 * @return releaseCompany
		 */
		public String getReleaseCompany() {
		    return releaseCompany;
		}
		/**
		 * releaseCompanyを設定します。
		 * @param releaseCompany releaseCompany
		 */
		public void setReleaseCompany(String releaseCompany) {
		    this.releaseCompany = releaseCompany;
		}
		/**
		 * statusを取得します。
		 * @return status
		 */
		public String getStatus() {
		    return status;
		}
		/**
		 * statusを設定します。
		 * @param status status
		 */
		public void setStatus(String status) {
		    this.status = status;
		}
		/**
		 * subtotalを取得します。
		 * @return subtotal
		 */
		public int getSubtotal() {
		    return subtotal;
		}
		/**
		 * subtotalを設定します。
		 * @param subtotal subtotal
		 */
		public void setSubtotal(int subtotal) {
		    this.subtotal = subtotal;
		}


}

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ヘッダー</title>
<script>
function goLoginAction(){
	document.getElementById("form").action="GoLoginAction";
}
function goMyPageAction(){
	document.getElementById("form").action="MyPageAction";
}
function goCartAction(){
	document.getElementById("form").action="CartAction";
}
function goProductListAction(){
	document.getElementById("categoryId").value=1;
	document.getElementById("form").action="ProductListAction";
}
function goLogoutAction(){
	document.getElementById("form").action="LogoutAction";
}
function goSearchItemAction(){
	document.getElementById("form").action="SearchProductAction";
}
function goAdminPageAction(){
	document.getElementById("form").action="AdminPageAction";
}
</script>
</head>

<body>
<header>
<div id="header">
<s:if test="#session.adminLogined==1">
	<img src="./images/title.jpg" width="130px" height="100px" id="header-title"/>
</s:if>
<s:else>
	<a href='<s:url action="HomeAction"></s:url>'><img src="./images/title.jpg" width="130px" height="100px" id="header-title"/></a>
</s:else>

<div id="header-menu">
<ul>
  <s:if test="#session.adminLogined==1">
  <s:form id="form" name="form">
        <li><s:submit value="ADMIN" class="submit-btn-admin" onclick="goAdminPageAction();"/></li>
        <li><s:submit value="LOGOUT" class="submit-btn-logout" onclick="goLogoutAction();"/></li>
  </s:form>
  </s:if>
  <s:else>
  <s:form id="form" name="form">
        <li><s:if test='#session.containsKey("mCategoryDtoList")'>
        		<s:select name="categoryId" list="#session.mCategoryDtoList" listValue="categoryName" listKey="categoryId" class="cs-div" id="categoryId"/>
    		</s:if>
    			<s:textfield name="keywords" class="txt-keywords" placeholder="SEARCH"/>
    			<s:submit value="" class="submit-btn-search" onclick="goSearchItemAction();"/>
    	</li>
    <s:if test="#session.logined==1">
        <li><s:submit value="LOGOUT" class="submit-btn-logout" onclick="goLogoutAction();"/></li>
    </s:if>
    <s:else>
        <li><s:submit value="LOGIN" class="submit-btn-login" onclick="goLoginAction();"/></li>
    </s:else>
        <li><s:submit value="CART" class="submit-btn-cart" onclick="goCartAction();"/></li>
        <li><s:submit value="PRODUCT" class="submit-btn-product" onclick="goProductListAction();"/></li>
    <s:if test="#session.logined==1">
        <li><s:submit value="MYPAGE" class="submit-btn-mypage" onclick="goMyPageAction();"/></li>
    </s:if>
  </s:form>
  </s:else>
</ul>
</div>

</div>
</header>

</body>
</html>
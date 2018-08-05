<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/productList.css">
<link rel="stylesheet" href="./css/style.css">
<link rel="javacsript" href="./js/header.js">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>productList</title>
</head>
<body>

<jsp:include page="header.jsp" />

<div class="main">
<div id="box-wide">

<!-- 商品一覧画面 -->


<div id="product-list">
<s:iterator value="#session.productInfoDtoList">
<div class="product-list-box">
<ul>
   <li>
     <a href='<s:url action="ProductDetailsAction">
              <s:param name="productId" value="%{productId}"/>
            </s:url>'>
            <img src='<s:property value="%{imageFilePath}"/>/<s:property value="%{imageFileName}"/>'
            class="item-image-box"/>
     </a><br>
     <s:property value="productName"/><br>
     $<s:property value="price"/><br>
   </li>
</ul>
</div>
</s:iterator>
</div>

<!-- 商品一覧画面 おわり -->
<div class="clear-fix"></div>

<!-- ページング -->
<div class="pager">
<s:iterator begin="1" end="#session.totalPageSize" status="pageNo">
  <s:if test="#session.currentPageNo == #pageNo.count">
    <s:property value="%{#pageNo.count}"/>
  </s:if>
  <s:else>
    <a href="<s:url action='ProductListAction'>
               <s:param name='pageNo' value='%{#pageNo.count}'/>
               <s:param name='categoryId' value='%{categoryId}'/>
               <s:param name='keywords' value='%{keywords}'/>
             </s:url>">
             <s:property value="%{#pageNo.count}"/>
   </a>
  </s:else>
</s:iterator>
</div>

<!-- ページングおわり -->


</div>
</div>


	<script type="text/javascript" src="./js/header.js">
	</script>


<s:include value="footer.jsp"/>


</body>
</html>
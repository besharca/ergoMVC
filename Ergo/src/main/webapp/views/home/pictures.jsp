<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.lil.demo.model.*" %>
<%@ page import="java.util.Base64" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
	<style type="text/css">
		<%@include file="/views/home/style.css" %>			  
	</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
			<c:if test="${sessionScope.login==null}">
				<c:redirect url="${contextPath}/new/login"/>
			</c:if>
			
		<header>
			<%@include file="/views/home/TopBorder.jsp" %> 
		</header> 
	
		<div class="form">
			<c:if test="${sessionScope.login!=null}">
				<form action="${contextPath}/new/pictures" method="post" enctype="multipart/form-data">
				  	Upload image file <br/> 
					  <input id="field" type="file" name="pikture" accept="image/*">  
					  <input type="submit" value="upload"> ${uploadStatus} 
				</form>
			</c:if> 
			<br/>
		</div> <!-- end form div -->
		
				 		
		<div class="images">
			<c:forEach	items="${imageList}" var="link">
				<div class="img">
					<img alt="" src="${link}">
				</div>
			</c:forEach>
		</div>
</body>
</html>
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
		<header>
			<%@include file="/views/home/TopBorder.jsp" %> 
		</header>  
		<div class="form">
		<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
			<c:if test="${sessionScope.login!=null}">
				<form action="${contextPath}/new/images" method="post" enctype="multipart/form-data">
				  	Upload image file <br/> 
					  <input id="field" type="file" name="pic" accept="image/*">  
					  <input type="submit" value="upload"> ${uploadStatus} 
				</form>
			</c:if> 
			<br/>
		</div> <!-- end form div -->
		
		<div class="pics">
			<c:if test="${pics!=null}">
				<ol id="picList">
					<c:forEach items="${pics}" var="pic">
						<li><a href="${contextPath}/new/images/${pic.key}">
								 ${pic.value}
							</a>
						</li>
					</c:forEach>
				</ol>
			</c:if>
			
			<c:if test="${onePic!=null}">
				<img style="max-width: 400px;" src="data:image/jpg;base64,${onePic.base64Value}"/>
				<p class="fileName">${onePic.fileName}</p>
				<a class="goBack" href="${contextPath}/new/images">Go Back</a>
			</c:if>
					
			<c:if test="${sessionScope.login==null}">
				<c:redirect url="${contextPath}/new/login"/>
		 	</c:if>
		</div>
</body>
</html>
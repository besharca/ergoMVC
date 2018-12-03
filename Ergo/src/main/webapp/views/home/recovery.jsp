<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<style><%@include file="/views/home/cssStyles/loginStyle.css" %>
			<%@include file="/views/home/style.css" %>
			<%@include file="/views/home/cssStyles/registrationStyle.css" %>
		 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<c:if test="${sessionScope.login!=null}">
				<c:redirect url="${contextPath}/new/main"/>
	</c:if>
	
	<header>
		<%@include file="/views/home/TopBorder.jsp" %>
	</header>
	
	<div class="formBox">
		<form action="recovery" method="post">
			
			<div class="containerLogin"> 
			  <div class="form-wrapper">
			  	<input type="text" class="form-control" placeholder="Enter Email" name="recoveryEmail" required>
			  </div> 
			  
			  <div>
			  	<button type="submit" id="registerButton">Recover</button>
			  </div>
			  <p id="registerStatus">${status}</p>
			</div> <!--end loginContainer div-->
	 
		</form>
	</div>
	
</body>
</html>
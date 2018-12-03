<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	<%@include file="/views/home/style.css" %>
	<%@include file="/views/home/cssStyles/registrationStyle.css"%>
				
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<c:if test="${sessionScope.login!=null}">
		 <c:redirect url="${contextPath}/new/main"/>
	</c:if>

<header>
	<%@include file="/views/home/TopBorder.jsp" %> 
</header>
	 
	 <div class="wrapperRegistration">
			<div class="inner">
				<form action="/new/register" method="post">
					<h3>Registration Form</h3>
					<div class="form-group"> 
						<input type="text" placeholder="First Name" name="firstName" value="${userReturn.firstName}" class="form-control" required="required">
						<input type="text" placeholder="Last Name" name="lastName" value="${userReturn.lastName}" class="form-control" required="required">
					</div>
					<div class="form-wrapper">
						<input type="text" placeholder="Username" name="username" value="${userReturn.username}" class="form-control" required="required">
					</div>
					<div class="form-wrapper">
						<input type="text" placeholder="Email Address" name="email" value="${userReturn.email}" class="form-control" required="required">
					</div>
					<div class="form-wrapper">
						<select name="gender" id="" class="form-control" required="required">
							<c:if test="${userReturn.gender==null}">
								<option value="" disabled selected>Gender</option>
								<option value="male">Male</option>
	 							<option value="female">Female</option>
	 						</c:if>
	 						<c:if test="${userReturn.gender=='male'}">
								<option value="" disabled selected>Gender</option>
								<option value="male" selected="selected">Male</option>
	 							<option value="female">Female</option>
	 						</c:if>
	 						<c:if test="${userReturn.gender=='female'}">
								<option value="" disabled selected>Gender</option>
								<option value="male" >Male</option>
	 							<option value="female" selected="selected">Female</option>
	 						</c:if>
						</select>
					</div>
					<div class="form-wrapper"> 
						<input type="password"  placeholder="Password" name="password"  class="form-control" required="required">
					</div>
					<div class="form-wrapper">
						<input type="password" placeholder="Confirm Password" name="confirmPassword"  class="form-control" required="required">
					</div>
					<button id="registerButton" type="submit">Register</button>
					<p id="registerStatus">${status}</p>
				</form>
			</div>
		</div>
	 
</body>
</html>
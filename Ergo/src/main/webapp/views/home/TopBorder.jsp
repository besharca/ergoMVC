<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html  >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<ul class="headerDropdown">
			<li ><a href="${contextPath}/new/main"><span class="currentHighlight">Home</span></a></li>
			<li><a>YouTube</a>
					<ul>
						<li><a href="/views/home/hi.jsp">Kitty</a></li>
						<li><a href="${contextPath}/new/video">Search</a></li>
					</ul>	
			</li>
			<li><a href="${contextPath}/new/images">Images</a></li>
			<li><a href="${contextPath}/new/pictures">Pictures</a></li>
			<li><a>Email</a>
					<ul>
						<li><a href="${contextPath}/new/send">SendEmail</a></li>
						<li><a>Tuna</a></li>
					</ul>	
			</li>
			<li><a>Account</a>
					<ul> 
						<c:if test="${sessionScope.login!=null}">
							<li><a>Logged as  ${sessionScope.login}</a></li>
							<li> <a href="${contextPath}/new/logout">Logout</a></li>
						</c:if>
						<c:if test="${sessionScope.login==null}">
							<li><a href="${contextPath}/new/login">Login</a></li>
							<li><a href="${contextPath}/new/register">Register</a></li>
						</c:if>
					</ul>
			</li>
		</ul>
</body>
</html>
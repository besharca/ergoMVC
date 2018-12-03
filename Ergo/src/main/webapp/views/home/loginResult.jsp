<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags"  prefix="s"%>
<!DOCTYPE html >
<html>
<head>
<style><%@include file="/views/home/cssStyles/loginStyle.css" %>
			<%@include file="/views/home/style.css" %> 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<header>
		<%@include file="/views/home/TopBorder.jsp" %>
	</header>

			<h1>${result}</h1>
	 
				<div class="youtube">
					<iframe width="560" height="315" src="https://www.youtube.com/embed/jUy9_0M3bVk?autoplay=1&controls=0&rel=0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
				</div>
</body>
</html>
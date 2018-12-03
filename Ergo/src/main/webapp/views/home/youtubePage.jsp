<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri = "http://www.springframework.org/tags" prefix="spring" %>
    
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	<%@include file="/views/home/style.css" %>
	<%@include file="/views/home/cssStyles/youtubeSearchStyle.css"%>			
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<header>
		<%@include file="/views/home/TopBorder.jsp" %> 
	</header> 
	
	<div class="youtube-container">
		<div id="youtubeSearchForm" class="inner">
			<form action="/new/video" method="get">
				<div class="form-wrapper-youtube">
					<input type="text" placeholder="Search video" name="searchInputQuery" class="form-control" required="required">
				</div> 
				
				<button id="searchButton" type="submit">Search</button> 
			</form>
		</div>
		<div class="video-container">
			<c:forEach items="${searchList}" var="videoKey">
				<iframe width="560" height="315" src="https://www.youtube.com/embed/${videoKey}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
			</c:forEach>
		</div>
	</div>  
</body>
</html>
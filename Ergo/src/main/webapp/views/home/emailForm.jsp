<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
<style type="text/css"><%@include file="/views/home/style.css" %>
				
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<header>
	<%@include file="/views/home/TopBorder.jsp" %> 
</header>
	<div>
		<form action="/new/send" method="post">
			
			<div>
				<p style="color: black;">Receiver</p>
				<input type="text" name="receiver" >
			</div>
			<div>
				<p style="color: black;">Content</p>
				<input type="text" name="content" >
			</div>
			<div>
				<button type="submit">Send</button>
			</div>
		</form>
		<p style="color: black;">${status}</p>
	</div>
</body>
</html>
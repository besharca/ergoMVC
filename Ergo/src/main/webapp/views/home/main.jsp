<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri = "http://www.springframework.org/tags" prefix="spring" %>
    
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		<%@include file="/views/home/style.css" %>			
	</style>
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<header>
	<%@include file="/views/home/TopBorder.jsp" %> 
</header>
		
<%-- 	<h1> 	 <spring:message code="username.error"/> 	</h1> 	--%>
	<div class="chatRoom">
		<ol> 
				<li><div class="comment" ><div class="message"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum scelerisque, ex non pulvinar sollicitudin, mauris nisl fermentum nulla, et sodales urna tortor vel nunc. Proin in accumsan massa, at tincidunt metus. Nam mollis eros est, et malesuada nisi consectetur lacinia. Quisque neque sem, varius vitae justo vel, pharetra vestibulum leo. Etiam sed nibh posuere ex pharetra blandit. Etiam ullamcorper blandit tempor. Mauris imperdiet nec ipsum ac sagittis. Praesent in tellus et nunc commodo pulvinar quis quis quam. Maecenas tristique ipsum eu ultricies mollis. Pellentesque erat risus, dictum eget justo semper, sagittis lobortis urna. Aenean tempor gravida ligula in vestibulum.Cras eu ligula nisi. Proin massa nibh, ullamcorper imperdiet dolor a, convallis ultrices mi. Proin ac justo eget nisl sodales vulputate. Sed lorem metus, sodales vel molestie sed, pulvinar id justo. Nulla porta odio vitae nibh hendrerit ultrices. Nullam eu nibh non metus maximus efficitur. Aenean vel tortor nisl. Maecenas semper tempus odio sit amet euismod. Phasellus nec tempor augue, vel fermentum felis. Sed ut rhoncus urna, nec suscipit eros. Mauris molestie lectus eget posuere malesuada. Fusce sed massa ut ipsum tempor venenatis. Vestibulum consequat est in imperdiet lacinia. Praesent vitae viverra est. Maecenas maximus arcu vel metus sagittis elementum.
					  </p></div><div class="userComment">Posted on xx-xx-yyyy <br/>AURELMARCUS</div></div>
				</li> 
			<c:forEach items="${comments}" var="comment">
				<li><div class="comment" ><div class="message"><p> ${comment.content} </p>
					  </div><div class="userComment">Posted on ${comment.timeStamp} <br/>${comment.username}</div></div>
				</li>
			</c:forEach>	
	 
		</ol> 
	</div> <!-- End div chat room -->
	<div id="postComment">
		<textarea rows="5" cols="5" name="content" form="txt" placeholder="Add comment">${comment}</textarea>
		<form  action="${contextPath}/new/main" method="post" id="txt"> 
			<input id="postButton"  type="submit"  value="Post">  
						<c:if test="${sessionScope.login==null}">
							You must <span class="mustLogin"><a href="login">login</a></span> before posting!
						</c:if>
						<c:if test="${status!=null }">
							<c:if test="${status eq 'posted' }">
								 <span class="mustLogin" style="color:green;"> Posted Successfully. </span> 
							</c:if>
							<c:if test="${status eq 'notposted' }">
								<span class="mustLogin" style="color:red;"> Message over 1000 characters! </span> 
							</c:if>
						</c:if>
		</form>
	</div>
</body>
</html>
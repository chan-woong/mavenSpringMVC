<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header.jsp -->
	<tiles:insertAttribute name="header"/>
	<hr color="red">
	
	<!-- menu.jsp -->
	<tiles:insertAttribute name="menu"/>
	<hr color="blue">
	<!-- 
		content
		-- member.jsp / board.jsp
	 -->
	 <tiles:insertAttribute name="content"/>
	 <hr color="pink">
	<!-- foot.jsp -->
	<tiles:insertAttribute name="foot"/>
</body>
</html>
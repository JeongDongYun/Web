<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="exam.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div align="left">
		<h2>${requestScope.news.title}</h2>
		<hr>
		기자: ${requestScope.news.writer}<br> <br> 기사내용 : <br>
		${requestScope.news.body}<br> 
			<a href="<c:url value='/news/update_clk.do?id=${news.id}'/>">[기사 수정]</a>
			<a href="list.do">[목록 보기]</a>
	</div>
</body>
</html>
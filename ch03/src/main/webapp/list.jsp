<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="exam.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스</title>
</head>
<body>
	<div align="center">
		<h2>전체 뉴스 리스트</h2>
		<hr>
		<table border="1">
			<tr>
				<th>제목</th>
				<th>기자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="news" items="${requestScope.newsList }">
				<tr>
					<td><a
						href="<c:url value="/news/read.do?id=${news.id}" />">${news.title}</a>
					</td>
					<td>${news.writer }</td>
					<td>${news.regdate }</td>
					<td>${news.readcnt }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
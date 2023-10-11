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
		<h2>기사 수정</h2>
		<hr>
		<form name="form1" action="<c:url value="/news/update.do" />">
			기자 : <input type="text" name="writer"
				value="${requestScope.news.writer}"><br> <br> 제목 :
			<input type="text" name="title" value="${requestScope.news.title}"><br>
			<br>내용 :
			<textarea name="body" rows="5" cols="40">${requestScope.news.body}</textarea>
			<br> <br> <input type="submit" value="수정"> <input
				type="reset" value="취소" onclick="history.back();">
		</form>
	</div>
</body>
</html>
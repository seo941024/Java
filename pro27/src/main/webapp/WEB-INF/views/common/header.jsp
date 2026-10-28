<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상단부</title>
</head>
<body>
	<table border="0" width="100%" bgcolor="lightgreen">
		<tr>
			<td width="120">
				<a href="${contextPath}/main.do">🏠</a>
			</td>
			<td align="center">
				<h1><font size="30">스프링실습 홈페이지!!</font></h1>
			</td>
			<td width="150" align="right">
				<c:choose>
					<c:when test="${isLogOn == true && member != null}">
						<h3>환영합니다. ${member.name}님!</h3>
						<a href="${contextPath}/member/logout.do"><h3>로그아웃</h3></a>
					</c:when>
					<c:otherwise>
						<a href="${contextPath}/member/loginForm.do"><h3>로그인</h3></a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
  request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정창</title>
    <style>
        .text_center {
            text-align: center;
        }
        .cls1 {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1 class="cls1">회원 정보 수정창</h1>
    <form method="post" action="${contextPath}/modMember.do?id=${memInfo.id}">
        <table align="center" border="1">
            <%-- 아이디 (수정 불가) --%>
            <tr>
                <td width="200">
                    <p align="right">아이디</p>
                </td>
                <td width="400">
                    <input type="text" value="${memInfo.id}" disabled>
                </td>
            </tr>
            <%-- 비밀번호 --%>
            <tr>
                <td width="200">
                    <p align="right">비밀번호</p>
                </td>
                <td width="400">
                    <input type="password" name="pwd" value="${memInfo.pwd}">
                </td>
            </tr>
            <%-- 이름 --%>
            <tr>
                <td width="200">
                    <p align="right">이름</p>
                </td>
                <td width="400">
                    <input type="text" name="name" value="${memInfo.name}">
                </td>
            </tr>
            <%-- 이메일 --%>
            <tr>
                <td width="200">
                    <p align="right">이메일</p>
                </td>
                <td width="400">
                    <input type="text" name="email" value="${memInfo.email}">
                </td>
            </tr>
            <%-- 가입일 (조회용, 수정 불가) --%>
            <tr>
                <td width="200">
                    <p align="right">가입일</p>
                </td>
                <td width="400">
                    <input type="text" value="${memInfo.joinDate}" disabled>
                </td>
            </tr>
            <%-- 버튼 영역 --%>
            <tr align="center">
                <td colspan="2">
                    <input type="submit" value="수정하기">
                    <input type="reset" value="다시입력">
                </td>
            </tr>
        </table>
    </form>
    
    <div class="text_center" style="margin-top: 20px;">
        <a href="${contextPath}/listMembers.do"><b>회원 목록으로 돌아가기</b></a>
    </div>
</body>
</html>
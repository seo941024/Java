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
    <c:choose>
        <c:when test="${msg=='addMember'}">
            <script>
                window.onload = function() {
                    alert("회원을 등록했습니다.");
                }
            </script>
        </c:when>
        <c:when test="${msg=='modified'}">
            <script>
                window.onload = function() {
                    alert("회원 정보를 수정했습니다.");
                }
            </script>
        </c:when>
        <c:when test="${msg=='deleted'}">
            <script>
                window.onload = function() {
                    alert("회원 정보를 삭제했습니다.");
                }
            </script>
        </c:when>
    </c:choose>

    <meta charset="UTF-8">
    <title>회원 정보 출력창</title>
    <style>
        .text_center {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1 class="text_center">회원 정보 출력창</h1>
    <table align="center" border="1">
        <tr align="center" bgcolor="lightgreen">
            <td width="7%"><b>아이디</b></td>
            <td width="7%"><b>비밀번호</b></td>
            <td width="7%"><b>이름</b></td>
            <td width="15%"><b>이메일</b></td>
            <td width="15%"><b>가입일</b></td>
            <td width="7%"><b>수정</b></td>
            <td width="7%"><b>삭제</b></td>
        </tr>

        <c:choose>
            <%-- 회원 목록 데이터가 없는 경우 --%>
            <c:when test="${membersList == null}">
                <tr>
                    <td colspan="7" align="center">
                        <b>등록된 회원이 없습니다.</b>
                    </td>
                </tr>
            </c:when>
            
            <%-- 회원 목록 데이터가 존재하는 경우 반복문 돌며 출력 --%>
            <c:when test="${membersList != null}">
                <c:forEach var="mem" items="${membersList}">
                    <tr align="center">
                        <td>${mem.id}</td>
                        <td>${mem.pwd}</td>
                        <td>${mem.name}</td>
                        <td>${mem.email}</td>
                        <td>${mem.joinDate}</td>
                        <td><a href="${contextPath}/modMemberForm.do?id=${mem.id}">수정</a></td>
                        <td><a href="${contextPath}/delMember.do?id=${mem.id}">삭제</a></td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>
    </table>
    
    <div class="text_center" style="margin-top: 20px;">
        <a href="${contextPath}/memberForm.do"><b>회원 가입창 가기</b></a>
    </div>
</body>
</html>
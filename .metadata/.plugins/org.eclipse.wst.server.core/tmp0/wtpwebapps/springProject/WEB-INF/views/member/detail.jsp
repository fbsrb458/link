<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>
<c:set value="${mvo.mvo }" var="mvo" > </c:set>
<table class="table table-hover">
	<thead>
		<tr>
			<th>이메일</th>
			<td>${mvo.email }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${mvo.nickName }</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${mvo.pwd }</td>
		</tr>
	</thead>
</table>




<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
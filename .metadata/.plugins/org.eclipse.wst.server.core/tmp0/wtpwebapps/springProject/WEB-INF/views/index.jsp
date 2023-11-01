<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
	<title>Home</title>
</head>
<body>
<jsp:include page="common/header.jsp" />
<jsp:include page="common/nav.jsp" />



<h3>바로 프로덕션이 가능한 Bootstrap의 CSS와 JavaScript를 CDN을 사용해서 빌드 단계를 거치지 않고 </h3>

<script type="text/javascript">
const isOk = `<c:out value="${isOkDel}" />`;
if(isOk > 0){
	alert("회원탈퇴 완료~!! 안녕히가세요.");
}
</script>

<jsp:include page="common/footer.jsp" />

</body>
</html>

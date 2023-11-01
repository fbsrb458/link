<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
 <jsp:include page="../common/header.jsp" />
 <jsp:include page="../common/nav.jsp" />
 <sec:authentication property="principal.mvo.email" var="authEamil"/>
<body>

<form action="/board/register" method="post" enctype="multipart/form-data">
<div class="mb-3">
  <label for="t" class="form-label">title</label>
  <input type="text" class="form-control" name="title" id="t" placeholder="title">
</div>
<div class="mb-3">
  <label for="w" class="form-label">writer</label>
  <textarea class="form-control" name="writer"  id="w" value = "${authEmail }" readonly="readonly"></textarea>
</div>
<div class="mb-3">
  <label for="c" class="form-label">content</label>
  <textarea class="form-control" name="content" id="c" rows="3"></textarea>
</div>
<div class="mb-3">
  <input type="file" class="form-control" name="files" id="files" style="display:none;" multiple="multiple">
	<!-- input button trigger 용도의 button -->
	<button type="button" id="trigger" class="btn btn-info" >File upload</button>
</div>
<div class="mb-3" id="fileZone">
<!-- 첨부파일 표시될 영역 -->
</div>
	<button type="submit" class="btn btn-outline-dark" id="regBtn">등록</button>
</form>

<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
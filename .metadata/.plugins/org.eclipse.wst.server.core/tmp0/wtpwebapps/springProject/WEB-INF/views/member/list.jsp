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
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<table class="table table-hover">

<tbody>
<c:forEach items="${memList }" var="mvo">
<div class="col">
	<!-- <div class="card" style="width: 18rem;"> -->
	<div class="card">

	<div class="card" >
	 <!--  <img src="/resources/image/dog1.jpg" class="card-img-top" alt="..."> -->
	  <div class="card-body">
	    <h5 class="card-title">${mvo.nickName }</h5>
	    <p class="card-text">${mvo.email } </p>
		   <p>( <c:forEach items="${mvo.authList }" var="authList">
		     ${authList.auth }  
		    </c:forEach> )</p>
	    <a href="/member/modify?email=${mvo.email }" class="btn btn-primary">Go somewhere</a>
	  </div>
	</div>
</div>
</div>
</c:forEach>
</tbody>
</table>
<jsp:include page="../common/footer.jsp" />
</body>



</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="/board/list">List</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="#">Board Link</a>
        </li>
        <!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open  -->
        <!-- 사용자 정보는 principal -->
        <!-- admin ROLE_ADMIN 계정만 할 수 있는 일을 처리 -->
        <sec:authorize access="isAuthenticated()"> <!-- authorize : 해당권한이 있을떄  ,  isAuthenticated : 자기 권한에 맞게 따라간다.-->

        <sec:authentication property="principal.mvo.email" var="authEmail"/>
        <sec:authentication property="principal.mvo.nickName" var="authNick"/>
        <sec:authentication property="principal.mvo.authList" var="auths"/>
        <c:choose>
        	<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
       		
       		<li class="nav-item">
       			<a class="nav-line" href="/member/list">UserList</a>
       		</li>
       		
       		
		    <li class="nav-item">
		 	  <a class="nav-line" href="/member/detail?email=${authEmail }">${authNick }(${authEmail})</a>
	  	    </li>
	  	    
	  	    <li class="nav-item">
	          <a class="nav-link" href="/board/register">Board Reg</a>
	        </li>
	  	    
	
			</c:when>
	
			<c:otherwise>
			
			  <li class="nav-item">
		 	  <a class="nav-line" href="/member/detail?email=${authEmail }">${authNick }(${authEmail})</a>
	  	    </li>
	  	    
	  	    <li class="nav-item">
	          <a class="nav-link" href="/board/register">Board Reg</a>
	        </li>
			
			</c:otherwise>
	  	</c:choose>
	  
        
        <!-- 로그인해야 open 되는 메뉴들... -->
        
       <li class="nav-item">
          <a class="nav-link" href="" id="logoutLink">logOut</a>
  	</li>
        <form action="/member/logout" method="post" id="logoutForm">
	        <input type="hidden" name="email" value="${authEmail }">
        </form>
  		 </sec:authorize>
  		 
  		 
       	<!-- 아직 로그인 전 상태에서 open 되어야 할 메뉴 -->
  		<sec:authorize access="isAnonymous()"> <!-- 권한이 아무것도없는 손님  -->
        <li class="nav-item ">
          <a class="nav-link" href="/member/register">SignUp</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="/member/login">logIn</a>
        </li>
        
  		</sec:authorize>        
      </ul>
      
     
    </div>
  </div>
</nav>
</header>
<script type="text/javascript">
document.getElementById('logoutLink').addEventListener('click', (e) => {
	e.preventDefault();
	document.getElementById('logoutForm').submit();
})

</script>
</body>
</html>
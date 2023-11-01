<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdel'w' ? 'selected' : ''  }">writer</option>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>
<!-- 검색라인 -->
<div class="cil-sm-12 col-md-6">
  <form class="d-flex" action="/board/list" method="get">
  	<div class="input-group mb-3">
  		<c:set value="${ph.pgvo.type }" var="typed"></c:set>
		  <select class="form-select" name="type" id="inputGroupSelect01">
		    <option ${typed eq null ? 'selected' : '' }>Choose...</option><!-- eq : ==  같은 뜻 -->
		    <option value="t" ${typed eq 't' ? 'selected' : ''  }>Title</option>
		    <option value="w" ${typed eq  'c' ? 'selected' : ''  }>Content</option>
		    <option value="tw" ${typed eq 'tw' ? 'selected' : ''  }>Title or writer</option>
		    <option value="tc" ${typed eq 'tc' ? 'selected' : ''  }>Title or Content</option>
		    <option value="cw" ${typed eq 'cw' ? 'selected' : ''  }>Content or writer</option>
		    <option value="twc" ${typed eq 'twc' ? 'selected' : ''  }>all</option>
		  </select>
		<!-- 기본값을위해 value 설정 -->
        <input class="form-control me-2" type="search" name="keyword" value="${ph.pgvo.keyword }" placeholder="Search" aria-label="Search">
        <input type="hidden" name="pageNo" value="1"> <!-- 1로하는 이유는 검색하면 무저건 첫번쨰 페이지로 돌아감 -->
        <input type="hidden" name="qty" value="${ph.pgvo.qty }">
        <button class="btn btn-outline-success" type="submit">
        Search
        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    	${ph.totalCount }
   		<span class="visually-hidden">unread messages</span>
  </span>
        </button>
 		</div>
  	</form>
  </div>
<table class="table table-hover">
<thead>
<tr>
   <th>#</th>
   <th>제목</th>
   <th>작성자</th>
   <th>작성일</th>
   <th>조회수</th>
   <th>댓글수</th>
   <th>파일수</th>
</tr>
</thead>
<tbody>
<c:forEach items="${list }" var="bvo">
   <tr>
      <td>${bvo.bno }</td>
      <td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
      <td>${bvo.writer }</td>
      <td>${bvo.regAt }</td>
      <td>${bvo.readCount }</td>
      <td>${bvo.cmtQty }</td>
      <td>${bvo.hasFile }</td>
   </tr>
</c:forEach>
</tbody>
</table>
<!-- 페이징 라인 -->
<nav aria-label="Page navigation example">
  <ul class="pagination">
  <!-- 이전 -->
  <c:if test="${ph.prev}">
    <li class="page-item ${(ph.prev eq false) ? 'disabled' : '' }">
      <a class="page-link" href="/board/list?pageNo=${ph.startPage - 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous" >
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
        
    <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
    	<li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
    </c:forEach>
    
   
    <!-- 다음 -->
    <li class="page-item ${(ph.next eq false)? 'disabled' : '' }">
      <a class="page-link" href="/board/list?pageNo=${ph.endPage + 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
</nav>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>

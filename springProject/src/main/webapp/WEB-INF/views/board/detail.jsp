<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
  
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

<table class="table table-hover">
<c:set value="${bvoDTO.bvo }" var="bvo" > </c:set>
	<thead>
		<tr>
			<th>#</th>
			<td>${bvo.bno }</td>
		</tr>
		<tr>
			<th>title</th>
			<td>${bvo.title }</td>
		</tr>
		<tr>
			<th>writer</th>
			<td>${bvo.writer }</td>
		</tr>
		<tr>
			<th>regAt</th>
			<td>${bvo.regAt }</td>
		</tr>
		<tr>
			<th>readCount</th>
			<td>${bvo.readCount }</td>
		</tr>
		<tr>
			<th>content</th>
			<td>${bvo.content }</td>
		</tr>
	</thead>
</table>
<!-- file 표시 영역 -->
<c:set value="${bvoDTO.flist }" var="flist"></c:set>
<div>
	<ul>
	<c:forEach items="${flist }" var="fvo">
		<li>
			<c:choose>
				<c:when test="${fvo.fileType > 0}">
					<div>
					<img alt="없음"src="/upload/${fn: replace(fvo.saveDir, '\\','/')}/
					${fvo.uuid}_th_${fvo.fileName}">
					</div> 
				</c:when>
				<c:otherwise>
					<div>
							<!-- file 아이콘 같은 모양 값으로 넣을 수 있음 -->
					</div>
				</c:otherwise>
			</c:choose>
			<div class="ms-2 me-auto">
				<div>${fvo.fileName }</div>
			</div>
				<span class="badge bg-secondary rounded-pill">${fvo.fileSize }Byte</span>
				</li>
			</c:forEach>
			<li>
		</li>
	</ul>
</div>

 <sec:authorize access="isAuthenticated()">
 <sec:authentication property="principal.mvo.email" var="authEmail"/>
<c:if test="${bvo.writer eq  authEmail}">

<a href="/board/modify?bno=${bvoDTO.bvo.bno }">
	<button type="button" class="btn btn-dark">수정</button>
</a>
<a href="/board/remove?bno=${bvoDTO.bvo.bno }">
	<button type="button" class="btn btn-dark">삭제</button>
</a>
</c:if>
</sec:authorize>


<a href="/board/list">
	<button type="button" class="btn btn-dark">리스트</button>
</a>


<!-- 댓글라인 -->
<div class="container">
 <sec:authorize access="isAuthenticated()">
 <sec:authentication property="principal.mvo.email" var="authEmail"/>
	<!-- 댓글 표시 라인 -->
	<div class="input-group flex-nowrap">
	  <span class="input-group-text" id="cmtWriter">${authEmail }</span>
	  <input type="text" class="form-control" placeholder="Test Comment"  id="cmtText" >
	  <button type="button" class="btn btn-success" id="cmtPostBtn">Post</button>
	</div>
	</sec:authorize>


	
	<!-- 댓글 표시 라인 -->
	<ul class="list-group list-group-flush">
  		<li class="list-group-item">
  			<div class="mb-3">
  				<div class="col-sm-10">Wirter</div>
  				Content
  			</div>
  			<span class="badge rounded-pill text-bg-dark">modAt</span>
	  		</li>
		</ul>
		
		<!-- 댓글 표시 라인 -->
		<ul class="list-group list-group-flush" id="cmtlistArea">
  		<li class="list-group-item">
  			<div class="mb-3">
  				<div class="col-sm-10">Wirter</div>
  				Content
  			</div>
  			<span class="badge rounded-pill text-bg-dark">modAt</span>
	  		</li>
		</ul>
	</div>
	<!-- 댓글 페이징 라인  -->
	<!-- data-page="1" : 1페이지 부터라는 뜻 -->
		<!-- visibility:hidden : 숨김속성 -->
	<div>
		<div>
			<button type="button" id="moreBtn" data-page="1"
			class="btn btn-warning" style="visibility:hidden">MORE+</button>
		</div>
	</div>
	
	<!-- 모달창 라인 -->
<!-- Modal -->
<div class="modal" id="myModal" tabindex="-1" >
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <h1 class="modal-title">wirter</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
      <div class="modal-body">
     <input type="text" class="form-control" placeholder="내용"  id="cmtTextMod" >
	  <button type="button" class="btn btn-success" id="cmtModBtn">Post</button>
      </div>
      
      <div class="modal-footer">
	  <button type="button" class="btn btn-secondary" id="Modal">Post</button>
      </div>
    </div>
  </div>
</div>
	
	
<script type="text/javascript">
let isMed = `<c:out value="${isMod}" />`;
if(parseInt(isMed)) {
	alart('수정완료')
}
let bnoVal = `<c:out value="${bvoDTO.bvo.bno}" />`;
console.log(bnoVal)

let email = `<c:out value = "${authEmail}" />`;
console.log(email)
</script>
<script type="text/javascript"src="/resources/js/boardComment.js"></script>
<script type="text/javascript"> 
getCommentList(bnoVal);
</script>
<!-- getCommentList : 댓글 호출 (보이게끔) -->

<br>
<br>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
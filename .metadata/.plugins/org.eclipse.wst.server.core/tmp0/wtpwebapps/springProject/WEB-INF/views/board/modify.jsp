<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
     
     
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
<c:set value="${bvoDTO.bvo }" var="bvo"></c:set>
<form action="/board/modify" method="post" enctype="multipart/form-data">
<table class="table table-hover">
	<thead>
		<tr>
			<th>#</th>
			<td><input type="text" name="bno" value="${bvo.bno }" readonly="readonly"> </td>
		</tr>
		<tr>
			<th>title</th>
			<td><input type="text" name="title" value="${bvo.title }"></td>
		</tr>
		<tr>
			<th>writer</th>
			<td><input type="text" name="writer" value="${bvo.writer }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>registerDate</th>
			<td><input type="text" name="regAt" value="${bvo.regAt }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>content</th>
			<td><textarea name="content">${bvo.content }</textarea></td>
		</tr>
		
	</thead>
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
				
				<div>${fvo.uuid} _ ${fvo.fileName }</div>
				<button type="button" class="file-x" data-uuid=${fvo.uuid }>X</button>
			
				</li>
			</c:forEach>
			<li>
		</li>
	</ul>
</div>
	
	<!-- 파일 수정 등록 라인 -->
file : <input type="file" id="files" name="files" multiple="multiple" style="display:none"> 
	<button type="button" id="trigger">FileUpload</button><br>
	<div id="fileZone">
	
	</div>
	
	<button type="submit" id="regBtn">수정</button>
</table>
</form>
<script type="text/javascript">
let isMed = `<c:out value="${isMod}" />`;
if(parseInt(isMed)) {
	alart('수정완료')
}
let bnoVal = `<c:out value="${bvo.bno}" />`;
console.log(bnoVal)
</script>

<a href="/board/list">
	<button type="button">리스트</button>
</a>
<script type="text/javascript"src="/resources/js/boardModify.js"></script>
<script type="text/javascript"src="/resources/js/boardRegister.js"></script>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
 <c:import url="/common/base.jsp">
 	<c:param name="title">
 		得点管理システム
 	</c:param>
 	
 	<c:param name="scripts"></c:param>
 	
 	<c:param name="content">
 		<section class="me-4">
 			<h2 class="h2 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス削除</h2>
 			
 			<div class="mt-4 mb-4">
 				<p class="fs-5 btn-secondary">「${delete_class.classNum}(${delete_class.className})」を削除してもよろしいですか</p>

 			</div>
 			
 			<form action="ClassDeleteExecute.action" method="post">
 				<input type="hidden" name="class_num" value="${delete_class.classNum}">
 				<input type="hidden" name="class_name" value="${delete_class.className}">
 				
 				<div class="d-flex align-items-center gap-2 mt-2">
 					<button type="submit" class="btn btn-danger">削除</button>
 				</div>
 				
 				<div class="d-flex align-items-center gap-2 mt-5">
 					<a href="ClassList.action">戻る</a>
 				</div>
 			</form>
 		</section>
 	</c:param>
 </c:import>
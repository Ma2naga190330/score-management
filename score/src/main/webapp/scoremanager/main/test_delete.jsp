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
 			<h2 class="h2 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績情報削除</h2>
 			
 			<div class="mt-4 mb-4">
 				<p class="fs-5 btn-secondary">「学生番号:${student_no}、科目:${subject_cd}、第${no}回」の成績を削除してもよろしいですか</p>
 			</div>
 			
 			<form action="TestDeleteExecute.action" method="post">
 				<input type="hidden" name="student_no" value="${student_no}">
 				<input type="hidden" name="subject_cd" value="${subject_cd}">
 				<input type="hidden" name="no" value="${no}">
 				
 				<div class="d-flex align-items-center gap-2 mt-2">
 					<button type="submit" class="btn btn-danger">削除</button>
 				</div>
 				
 				<div class="d-flex align-items-center gap-2 mt-5">
 					<a href="TestList.action">戻る</a>
 				</div>
 			</form>
 		</section>
 	</c:param>
 </c:import>
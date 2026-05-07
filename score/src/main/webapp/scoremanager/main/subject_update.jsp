<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
			<h2 class="h2 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
			
			<!-- servletに合わせて変更 -->
			<form action="SubjectUpdateExecute.action" method="post">
			
				<div class="mb-3">
					<label class="form-label" for="subject-cd-input">科目コード</label><br>
					<input type="text" class="border-0" style="outline: none;" name="cd" value="${cd}"
						id="subject-cd-input" readonly>
				</div>
				
				<div class="mb-3">
					<label class="form-label" for="subject-name-input">科目名</label>
					<input type="text" class="form-control" name="name" value="${name}"
						id="subject-name-input" maxlenght="20" required>
				</div>
				
				<div class="d-flex align-items-center gap-2 mt-3">
					<button type="submit" class="btn btn-primary">変更</button>
				</div>
				
				<div class="d-flex align-items-center gap-3 mt-2">
					<a href="SubjectList.action">戻る</a>
				</div>
			</form>
		</section>
	</c:param>
</c:import> 
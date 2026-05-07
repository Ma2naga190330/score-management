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
				<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
			
			<!-- servletに合わせて変更 -->
			<form action="SubjectCreateExecute.action" method="post">
				<div class="mb-3">
					<label class="form-label" for="subject-cd-input">科目コード</label>
					<input type="text" class="form-control" name="cd" value="${cd}"
						id="subject-cd-input" placeholder="科目コードを入力してください"
						maxlength="3" minlength="3" required>
				</div>
				
				<div class="mb-3">
					<label class="form-label" for="subject-name-input">科目名</label>
					<input type="text" class="form-control" name="name" value="${name}"
						id="subject-name-input" placeholder="科目名を入力してください"
						maxlength="20" required>
				</div>
				
				<div class="d-flex align-items-center gap-3 mt-4">
					<button type="submit" class="btn btn-primary">登録</button>
				</div>
				
				<div class="d-flex align-items-center gap-3 mt-2">
					<a href="SubjectList.action">戻る</a>
					
				</div>
				<div class="mt-2 text-warning">${error}</div>
			</form>
			</section>
	</c:param>
</c:import>
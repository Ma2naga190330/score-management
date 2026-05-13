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
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス情報登録</h2>
			
		<form action="ClassCreateExecute.action" method="post">
			<div class="mb-3">
				<label class="form-label" for="class-num-input">クラス番号</label>
				<input type="text" class="form-control" name="cd" value="${cd}"
					id="class-num-input" placeholder="クラス番号を入力してください"
					maxlength="5" minlength="3" required>
			</div>
			<div class="mt-2 text-warning">${error}</div>
			
			<div class="mb-3">
					<label class="form-label" for="class-name-input">クラス名</label>
					<input type="text" class="form-control" name="name" value="${name}"
						id="class-name-input" placeholder=クラス名を入力してください"
						maxlength="20" required>
			</div>
			
			<div class="d-flex align-items-center gap-3 mt-4">
					<button type="submit" class="btn btn-primary">登録</button>
			</div>
			
			<div class="d-flex align-items-center gap-3 mt-2">
					<a href="ClassList.action">戻る</a>	
			</div>
		</form>
		</section>
	</c:param>
</c:import>
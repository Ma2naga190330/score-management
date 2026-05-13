<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		クラス管理
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
			
			<div class="bg-success p-1" style="--bs-bg-opacity: .5;">
				<label class="d-flex justify-content-center align-items-center">
					削除が完了しました
				</label>
			</div>
			
			<div class="d-flex alian-items-center gap-5 mt-2">
				<a href="ClassList.action">戻る</a>
			</div>
		</section>
	</c:param>
</c:import>
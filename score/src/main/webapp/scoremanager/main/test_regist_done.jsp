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
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績情報変更</h2>
			
		<div class="bg-success p-1" style="--bs-bg-opacity: .5;">
			<label class="d-flex justify-content-center align-items-center">
				登録が完了しました
			</label>
		</div>
		
		<div class="d-flex align-itmes-center gap-5 mt-2">
			<a href="TestRegist.action">戻る</a>
			<a href="TestList.action">成績参照</a>
		</div>
		</section>
	</c:param>
</c:import>
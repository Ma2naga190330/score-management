<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		ログアウト
	</c:param>
	
	<c:param name="content">
		<div class="row mx-3 mb-3 py-2 align-items-center rounded">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">ログアウト</h2><br>
			<div class="">
				<p>ログアウトしました</p>
			</div>
			<a href="Login.action">ログイン</a>
		</div>
	</c:param>
</c:import>
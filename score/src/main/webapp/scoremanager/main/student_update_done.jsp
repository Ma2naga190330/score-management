<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="content">
		<div class="row mx-3 mb-3 py-2 align-items-center rounded">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2><br>
			<div class="bg-success p-1" style="--bs-bg-opacity: .5;">
				<label class="d-flex justify-content-center align-items-center">
					変更が完了しました。
				</label>
			</div>
			<div class="pt-5">
				<a href="StudentList.action">学生一覧</a>
			</div>
		</div>
	</c:param>
</c:import>
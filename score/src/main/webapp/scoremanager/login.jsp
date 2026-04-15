<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts">
	
	</c:param>
	
	<c:param name="content">
		<div class="border align-items-center rounded">
			<form action="LoginExecute.action" method="get" name="login">
			<div class="h3 fw-norma bg-secondary bg-opacity-10 pt-2 pb-2">
				<h2 class="d-flex justify-content-center align-items-center">ログイン</h2>
			</div>
				<div class="row mx-5 my-5">
					<c:if test="${error != null}">${error}</c:if>
					<label id="login-id" class="border"> 
						ID<br><input type="text" name="id" value="${id}" for="login-id" class="border-0">
					</label><br>
					<label id="login-password">
						Password<br><input type="password" name="password" id="password">
					</label><br>
					<div class="d-flex justify-content-center align-items-center">
						<label id="password-view">
							<input type="checkbox" name="chk_d_ps" for="password-view" id='preview'>
							パスワードの表示
						</label>
					</div>
					<div class="d-flex justify-content-center">
						<button class="btn btn-primary" name="login" type="submit">ログイン</button>
					</div>
				</div></div>
			</form>
		</div>
	</c:param>
	
</c:import>
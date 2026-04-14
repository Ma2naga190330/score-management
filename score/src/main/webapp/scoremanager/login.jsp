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
		<div class="row border mx-3 mb-3 py-2 align-items-center rounded">
			<form action="LoginExecute.action" method="get" name="login">
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">ログイン</h2><br>
				<div class="col-2 text-center">
					<c:if test="${error != null}">${error}</c:if>
					<label id="login-id">
						ID<br><input type="text" name="id" value="${id}" for="login-id">
					</label>
					<label id="login-password">
						Password<br><input type="password" name="password" id="password">
					</label>
						<label id="password-view">
							<input type="checkbox" name="chk_d_ps" for="password-view" id='preview'>
							パスワードの表示
						</label>
					<button class="btn btn-primary" name="login" type="submit">ログイン</button>
				</div>
			</form>
		</div>
	</c:param>
	
</c:import>
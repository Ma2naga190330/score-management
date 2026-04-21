<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts">
	<script>
	document.addEventListener('DOMContentLoaded', function (event) {
	    const input = document.getElementById("password");
	    const check = document.getElementById("preview");
	    check.addEventListener('change', function () {
	        if (check.checked) {
	            input.type = 'text';
	        } else {
	            input.type = 'password';
	        }
	    });
	});
	</script>
	</c:param>
	
	<c:param name="content">
		<div class="border align-items-center rounded">
			<form action="LoginExecute.action" method="get" name="login">
			<div class="h3 fw-norma bg-secondary bg-opacity-10 pt-2 pb-2">
				<h2 class="d-flex justify-content-center align-items-center">ログイン</h2>
			</div>
				<div class="row mx-5 my-3">
					<c:if test="${error != null}">
						<div class="text-center mt-1 mb-3">${error}</div>
					</c:if>
					<label id="login-id" class="mx-3 mb-3 border rounded-2" style="color: gray;"> 
						ＩＤ<br><input type="text" name="id" value="${id}" for="login-id" class="border-0" style="outline: none;" placeholder="半角でご入力ください" maxlength="10"size="40">
					</label>
					<label id="login-password" class="mb-3 mx-3 border rounded-2" style="color: gray;">
						パスワード<br><input type="password" name="password" id="password" class="border-0" style="outline: none;"placeholder="30文字以内の半角英数字でご入力ください" maxlength="30"size="40">
					</label>
					<div class="d-flex justify-content-center align-items-center">
						<label id="password-view">
							<input type="checkbox" name="chk_d_ps" for="password-view" id='preview'>
							パスワードの表示
						</label><br>
					</div>
					<div class="d-flex justify-content-center">
						<button class="btn btn-primary mt-3" name="login" type="submit">ログイン</button>
					</div>
			</form>
		</div>
	</c:param>
	
</c:import>
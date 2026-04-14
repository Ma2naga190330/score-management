<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	<c:param name="content">
			<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
			<form method="get">
				<!-- 入学年度の入力 -->
				<label class="form-label" for="ent-year-select">入学年度</label>
					<!-- 今年を基準に10年前から10年後までの年リストを取得 -->
					<select class="form-select" id="ent-year-select" name="ent_year">
						<option value="0">------------</option>
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}"<c:if test="${year==f1}">selected</c:if>>${year}</option>
						</c:forEach>
					</select>
				<!-- クラス番号の入力 -->
				<label class="form-label" for="student-num-input">学生番号</label>
					<input type="text" name="no" value="${no}" placeholder="学生番号を入力してください" id="student-num-input">
				<!-- 氏名の入力 -->
				<label class="form-label" for="student-name-input">氏名</label>
					<input type="text" name="name" value="${name}" placeholder="氏名を入力して下さい" id="student-name-input">
				<!-- クラスの入力 -->
				<label class="form-label" for="student-class-select">クラス</label>
					<select class="form-select" id="student-class-select" name="class_num">
						<option value="0">------------</option>
						<!-- ログインユーザーの学校コードのクラス一覧を取得 -->
						<c:forEach var="year" items="${}">
							<option value="${year}"<c:if test="${year==f1}">selected</c:if>>${year}</option>
						</c:forEach>
					</select>
					
			</form>
		</section>
	</c:param>
</c:import>
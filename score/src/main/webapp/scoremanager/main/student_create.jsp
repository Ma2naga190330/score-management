<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<c:import url="/common/base.jsp">
	<c:param name="title">
		学生登録
	</c:param>
	<c:param name="content">
			<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
			<form action="StudentCreateExecute.action" method="get">
				<!-- 入学年度の入力 -->
				<label class="form-label" for="ent-year-select">入学年度</label>
					<!-- 今年を基準に10年前から10年後までの年リストを取得 -->
					<select class="form-select" id="ent-year-select" name="ent_year">
						<option value="0">------------</option>
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}"<c:if test="${year==f1}">selected</c:if>>${year}</option>
						</c:forEach>
					</select><br>
				<!-- 学生番号の入力 -->
				<div class="mb-3">
                    <label class="form-label" for="student-num-input">学生番号</label>
                    <input type="text" name="no" class="form-control"
                           value="${no}" id="student-num-input"
                           maxlength="30" placeholder="学生番号を入力してください" required>
                </div>
				<!-- 氏名の入力 -->
				<div class="mb-3">
                    <label class="form-label" for="student-name-input">氏名</label>
                    <input type="text" name="name" class="form-control"
                           value="${name}" id="student-name-input"
                           maxlength="30" placeholder="氏名を入力してください" required>
                </div>
				<!-- クラスの入力 -->
				<label class="form-label" for="student-class-select">クラス</label>
				<select class="form-select" id="student-class-select" name="class_num">
						<option value="0">------------</option>
						<c:forEach var="num" items ="${class_num_set}">
							<option value ="${num}">${num}</option>
						</c:forEach>
				</select>
				<!-- 登録して終了するボタン -->
				<div class="mt-3 mb-3">
					<button class="btn btn-secondary p-2" id="filter-end-button" type="submit">登録して終了</button>
				</div>
				<!-- 戻るボタン -->
				<a href="StudentList.action">戻る</a>
			</form>
		</section>
	</c:param>
</c:import>

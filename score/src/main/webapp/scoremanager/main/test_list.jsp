<<<<<<< HEAD
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績参照
            </h2>

            <h4 class="mt-4">科目情報</h4>

            <form action="TestListSubjectExecute.action" method="post">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select name="ent_year" class="form-select">
                            <option value="">------------</option>
                            <c:forEach var="y" items="${entYearList}">
                                <option value="${y}">${y}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select name="class_num" class="form-select">
						    <option value="">------------</option>
						    <c:forEach var="c" items="${classList}">
						        <option value="${c}">${c}</option>
						    </c:forEach>
						</select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select name="subject" class="form-select">
                            <option value="">------------</option>
                            <c:forEach var="s" items="${subjectList}">
                                <option value="${s.name}">${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2 text-center">
                        <button type="submit" class="btn btn-secondary mt-4">検索</button>
                    </div>

                </div>
            </form>

            <h4 class="mt-4">学生情報</h4>

            <form action="TestListStudentExecute.action" method="post">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                    <div class="col-4">
                        <label class="form-label">学生番号</label>
                        <input type="text" name="student_no" class="form-control"
                               placeholder="学生番号を入力してください">
                    </div>

                    <div class="col-2 text-center">
                        <button type="submit" class="btn btn-secondary mt-4">検索</button>
                    </div>

                </div>
            </form>

			<p class="text-primary mx-3">
			    科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
			</p>


        </section>
    </c:param>
</c:import>

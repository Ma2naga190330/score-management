<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">学生別成績一覧</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧(学生)</h2>
			<form action="TestListSubjectExecute.action" method="post">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select name="f1" class="form-select">
                            <option value="">------------</option>
                            <c:forEach var="y" items="${entYearList}">
                                <option value="${y}">${y}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select name="f2" class="form-select">
						    <option value="">------------</option>
						    <c:forEach var="c" items="${classList}">
						        <option value="${c}">${c}</option>
						    </c:forEach>
						</select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select name="f3" class="form-select">
                            <option value="">------------</option>
                            <c:forEach var="s" items="${subjectList}">
                                <option value="${s.cd}">${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2 text-center">
                        <button type="submit" class="btn btn-secondary mt-4">検索</button>
                    </div>
            </form>
            <form action="TestListStudentExecute.action" method="post">

                    <div class="col-3">
                        <label class="form-label">学生番号</label>
                        <input type="text" name="f4" class="form-control"
                               placeholder="学生番号を入力してください" <c:if test="${ not empty student }">value = "${ student.no }"</c:if>>
                    </div>

                    <div class="col-2 text-center">
                        <button type="submit" class="btn btn-secondary mt-4">検索</button>
                    </div>

                </div>
            </form>

            <c:if test="${not empty message}">
                <div class="alert alert-info mx-3">${message}</div>
            </c:if>

            <c:if test="${empty test_student}">
                <div>氏名：${ student.name }(${ student.no })</div>
                <div>成績情報が存在しませんでした</div>
            </c:if>

            <c:if test="${not empty test_student}">
                <div class="table-responsive mx-3">
                	<div>氏名：${ student.name }(${ student.no })</div>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>科目名</th>
                                <th>科目コード</th>
                                <th>回数</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${test_student}">
                                <tr>
                                    <td>${s.subjectName}</td>
                                    <td>${s.subjectCd}</td>
                                    <td>${s.num}</td>
                                    <td>${s.point}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

        </section>
    </c:param>
</c:import>

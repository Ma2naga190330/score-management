<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">科目別成績一覧</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目別成績一覧</h2>

            <c:if test="${not empty message}">
                <div class="alert alert-info mx-3">${message}</div>
            </c:if>

            <c:if test="${empty subjectResults}">
                <p class="mx-3">該当する成績データはありません。</p>
            </c:if>

            <c:if test="${not empty subjectResults}">
                <div class="table-responsive mx-3">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>テスト名</th>
                                <th>点数</th>
                                <th>科目名</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="r" items="${subjectResults}">
                                <tr>
                                    <td>${r.studentNo}</td>
                                    <td>${r.studentName}</td>
                                    <td>${r.testName}</td>
                                    <td>${r.score}</td>
                                    <td>${r.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <div class="mx-3">
                <a href="TestList.action" class="btn btn-secondary">検索に戻る</a>
            </div>
        </section>
    </c:param>
</c:import>

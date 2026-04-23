<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">学生別成績一覧</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生別成績一覧</h2>

            <c:if test="${not empty message}">
                <div class="alert alert-info mx-3">${message}</div>
            </c:if>

            <c:if test="${empty studentResults}">
                <p class="mx-3">該当する学生情報はありません。</p>
            </c:if>

            <c:if test="${not empty studentResults}">
                <div class="table-responsive mx-3">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>在籍</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${studentResults}">
                                <tr>
                                    <td>${s.no}</td>
                                    <td>${s.name}</td>
                                    <td>${s.entYear}</td>
                                    <td>${s.classNum}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${s.isAttend}">在籍</c:when>
                                            <c:otherwise>退学</c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <div class="mx-3">
                <a href="/score/scoremanager/main/TestList.action" class="btn btn-secondary">検索に戻る</a>

            </div>
        </section>
    </c:param>
</c:import>

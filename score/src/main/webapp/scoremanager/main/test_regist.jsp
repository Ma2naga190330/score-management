<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績登録</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績登録
            </h2>

            <!-- 検索フォーム -->
            <form action="TestRegist.action" method="get">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                    <!-- 入学年度 -->
                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="ent_year">
                            <option value="0">------------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}"
                                    <c:if test="${year == ent_year}">selected</c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- クラス -->
                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="class_num">
                            <option value="0">------------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}"
                                    <c:if test="${num == class_num}">selected</c:if>>
                                    ${num}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 科目 -->
                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="subject_cd">
                            <option value="0">------------</option>
                            <c:forEach var="sub" items="${subject_list}">
                                <option value="${sub.cd}"
                                    <c:if test="${sub.cd == subject_cd}">selected</c:if>>
                                    ${sub.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 回数 -->
                    <div class="col-2">
                        <label class="form-label">回数</label>
                        <input type="number" class="form-control" name="no"
                               value="${no}" min="1" max="10">
                    </div>

                    <!-- ボタン -->
                    <div class="col-1 text-center">
                        <button class="btn btn-secondary mt-4" type="submit">検索</button>
                    </div>

                    <!-- エラー表示 -->
                    <div class="mt-2 text-warning">${errors.get("search")}</div>
                </div>
            </form>

            <!-- 検索結果 -->
            <c:choose>
                <c:when test="${tests.size() > 0}">
                    <div>検索結果：${tests.size()}件</div>

                    <form action="TestRegistExecute.action" method="post">
                        <table class="table table-hover">
                            <tr>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>

                            <c:forEach var="t" items="${tests}">
                                <tr>
                                    <td>${t.student.no}</td>
                                    <td>${t.student.name}</td>
                                    <td>
                                        <input type="number" name="point_${t.student.no}"
                                               class="form-control"
                                               value="${t.point}"
                                               min="0" max="100" required>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">登録</button>
                        </div>
                    </form>

                </c:when>

                <c:otherwise>
                    <div>成績データが存在しませんでした。</div>
                </c:otherwise>
            </c:choose>

        </section>
    </c:param>
</c:import>

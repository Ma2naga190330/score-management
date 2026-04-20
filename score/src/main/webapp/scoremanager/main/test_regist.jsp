<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績登録</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績登録
            </h2>

            <form action="TestRegist.action" method="get">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="ent_year">
                            <option value="0">------------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="class_num">
                            <option value="0">------------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>
                                    ${num}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="subject_cd">
                            <option value="0">------------</option>
                            <c:forEach var="sub" items="${subject_list}">
                                <option value="${sub.cd}" <c:if test="${sub.cd == subject_cd}">selected</c:if>>
                                    ${sub.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2">
                        <label class="form-label">回数</label>
                        <input type="number" class="form-control" name="no" value="${no}" min="1" max="10">
                    </div>

                    <div class="col-1 text-center">
                        <button class="btn btn-secondary mt-4" type="submit">検索</button>
                    </div>

                    <div class="mt-2 text-warning">${errors.get("search")}</div>
                </div>
            </form>

            <c:if test="${not empty tests}">
                <h4 class="ms-3 mb-3">
                    科目：${tests[0].subject.name}（${tests[0].no}回）
                </h4>

                <form action="TestRegistExecute.action" method="post">
                <input type="hidden" name="subject_cd" value="${subject_cd}">
				<input type="hidden" name="class_num" value="${class_num}">
				<input type="hidden" name="no" value="${no}">
                    <table class="table table-hover mx-3">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>

                        <c:forEach var="t" items="${tests}">
                            <tr>
                                <td>${t.student.entYear}</td>
                                <td>${t.student.classNum}</td>
                                <td>${t.student.no}</td>
                                <td>${t.student.name}</td>
                                <td>
                                	<input type="hidden" name="student_no" values="${t.student.no }">
                                    <input type="number"
                                           name="point"
                                           class="form-control"
                                           value="${t.point}"
                                           min="0" max="100" required>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div class="text-end mx-3">
                        <button type="submit" class="btn btn-primary">登録して終了</button>
                    </div>
                </form>
            </c:if>

        </section>
    </c:param>
</c:import>

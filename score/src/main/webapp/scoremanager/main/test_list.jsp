<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

            <div class="bg-light border rounded mb-4">
                <div class="p-3 border-bottom">
                    <p class="fw-bold">科目情報</p>
                    <form action="TestListSubjectExecute.action" method="get">
                        <div class="row g-3 align-items-end">
                            <div class="col-md-3">
                                <label class="form-label">入学年度</label>
                                <select name="f1" class="form-select">
                                    <option value="0">--------</option>
                                    <c:forEach var="year" items="${ent_year_set}">
                                        <option value="${year}">${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label class="form-label">クラス</label>
                                <select name="f2" class="form-select">
                                    <option value="0">--------</option>
                                    <c:forEach var="c" items="${class_num_set}">
                                        <option value="${c}">${c}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label class="form-label">科目</label>
                                <select name="f3" class="form-select">
                                    <option value="0">--------</option>
                                    <c:forEach var="sub" items="${subject_set}">
                                        <option value="${sub.cd}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-secondary">検索</button>
                            </div>
                        </div>
                    </form>
                </div>

                <%-- 下：学生情報 --%>
                <div class="p-3">
                    <p class="fw-bold">学生情報</p>
                    <form action="TestListStudentExecute.action" method="get">
                        <div class="row g-3 align-items-end">
                            <div class="col-md-4">
                                <label class="form-label">学生番号</label>
                                <input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください">
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-secondary">検索</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            </c:if>
        </section>
    </c:param>
</c:import>
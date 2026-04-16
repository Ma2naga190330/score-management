<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">学生情報変更</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生情報変更
            </h2>

            <form action="StudentUpdateExecute.action" method="post" class="px-4">

                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <input type="text" name="ent_year" class="form-control-plaintext"
                           value="${student.entYear}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <input type="text" name="no" class="form-control-plaintext"
                           value="${student.no}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input type="text" name="name" class="form-control"
                           value="${student.name}"
                           maxlength="30" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select name="class_num" class="form-select">
                        <c:forEach var="c" items="${classList}">
                            <option value="${c}">${c }
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-check mb-4">
                    <input class="form-check-input" type="checkbox"
                           name="is_attend" value="1"
                           <c:if test="${student.isAttend}">checked</c:if>>
                    <label class="form-check-label">在学中</label>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">変更</button>
                    <a href="StudentList.action" class="btn btn-secondary ms-2">戻る</a>
                </div>

            </form>

        </section>
    </c:param>
</c:import>

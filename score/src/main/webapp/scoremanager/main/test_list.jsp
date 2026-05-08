<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績参照
            </h2>

            <div class="border mx-3 mb-3 p-3 rounded">
                
                <form action="TestListSubjectExecute.action" method="post" class="row align-items-center mb-3">
                    <div class="col-1">
                        <span>科目情報</span> </div>
                    <div class="col-11">
                        <div class="row align-items-center">
                            <div class="col-3">
                                <label class="form-label mb-0 ">入学年度</label> <select name="f1" class="form-select mb-0"> <option value="">------------</option>
                                    <c:forEach var="y" items="${entYearList}">
                                        <option value="${y}">${y}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-3">
                                <label class="form-label mb-0">クラス</label> <select name="f2" class="form-select mb-0"> <option value="">------------</option>
                                    <c:forEach var="c" items="${classList}">
                                        <option value="${c}">${c}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-4">
                                <label class="form-label mb-0">科目</label> <select name="f3" class="form-select mb-10"> <option value="">------------</option>
                                    <c:forEach var="s" items="${subjectList}">
                                        <option value="${s.cd}">${s.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-2">
                                <button type="submit" class="btn btn-secondary mt-4">検索</button> </div>
                        </div>
                    </div>
                </form>

                <hr> <form action="TestListStudentExecute.action" method="post" class="row align-items-center">
                    <div class="col-1">
                        <span>学生情報</span> </div>
                    <div class="col-11">
                        <div class="row align-items-center">
                            <div class="col-4">
                                <label class="form-label mb-0">学生番号</label> <input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください"> </div>
                            <div class="col-2">
                                <button type="submit" class="btn btn-secondary mt-4">検索</button> </div>
                        </div>
                    </div>
                </form>
            </div>
            <p class="text-primary mx-3">
                科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
            </p>

        </section>
    </c:param>
</c:import>
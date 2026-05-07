<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">科目別成績一覧</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目別成績一覧</h2>
			
			<form action="TestListSubjectExecute.action" method="post">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select name="f1" class="form-select">
                            <option value=""></option>
                            <c:forEach var="y" items="${entYearList}">
                                <option value="${y}">${y}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select name="f2" class="form-select">
						    <c:forEach var="c" items="${classList}">
						        <option value="${c}">${c}</option>
						    </c:forEach>
						</select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select name="f3" class="form-select">
                            <option selected>${f3.name }</option>
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
                               placeholder="学生番号を入力してください">
                    </div>

                    <div class="col-2 text-center">
                        <button type="submit" class="btn btn-secondary mt-4">検索</button>
                    </div>

                </div>
            </form>
            <c:if test="${not empty message}">
                <div class="alert alert-info mx-3">${message}</div>
            </c:if>
            
            <c:if test="${empty subjectResults}">
                <p class="mx-3">該当する成績データはありません。</p>
            </c:if>

            <c:if test="${not empty subjectResults}">
                <div class="table-responsive mx-3">
                	<div>
                		科目:${f3.name}
                	</div>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>1回</th>
                                <th>2回</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="r" items="${subjectResults}">
                                <tr>
                                    <td>${r.entYear}</td>
                                    <td>${r.classNum}</td>
                                    <td>${r.studentNo}</td>
                                    <td>${r.studentName}</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${ r.getPoint(1) != null }">${ r.getPoint(1) }</c:when>
                                    		<c:otherwise>-</c:otherwise>
                                    	</c:choose>
                                    </td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${ r.getPoint(2) != null}">${ r.getPoint(2) }</c:when>
                                    		<c:otherwise>-</c:otherwise>
                                    	</c:choose>
                                    </td>
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

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績参照
            </h2>   	
            <form action="TestListSubjectExecute.action" method="post">
                <div class="row border mx-1 py-1 align-items-center rounded">
                	<div class="p-2">科目情報</div>
                	<div class="hstack gap-4">
					<!-- 科目情報入力欄 -->
                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select name="f1" class="form-select">
                            <option value="0">------------</option>
                            <c:forEach var="y" items="${entYearList}">
                                <option value="${y}">${y}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select name="f2" class="form-select">
						    <option value="0">------------</option>
						    <c:forEach var="c" items="${classList}">
						        <option value="${c}">${c}</option>
						    </c:forEach>
						</select>
                    </div>

                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select name="f3" class="form-select">
                            <option value="0">------------</option>
                            <c:forEach var="s" items="${subjectList}">
                                <option value="${s.cd}">${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 text-center px-1">
                        <button type="submit" class="btn btn-secondary mt-4 mx-1">検索</button>
                    </div>
                    </div>
                    <c:if test="${!empty errors }">
                    	<div class="mt-2 text-warning">
                    		${errors }
                    	</div>
                    </c:if>
            </form>
            
				<div class="col-12 my-3">
					<hr class="my-0" style="border-top: opacity: 10;">
				</div>
			<form action="TestListStudentExecute.action" method="post">
			<div class="p-2">学生情報</div>
			<div class="hstack gap-4">
                    <div class="p-2 col-3">
                        <label class="form-label">学生番号</label>
                        <input type="text" name="f4" class="form-control"
                               placeholder="学生番号を入力してください" required>
                    </div>
                    <div class="p-2 col-2 text-center">
                        <button type="submit" class="btn btn-secondary mt-4">検索</button>
                    </div>
			</div>
            </form>
			</div>
			<p class="text-primary mx-3">
			    科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
			</p>
			
			<c:if test="${!empty error }">
				<div>
                    ${error }
                </div>
            </c:if>
        </section>
    </c:param>
</c:import>

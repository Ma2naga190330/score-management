<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    
    <c:param name="scripts"></c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h2 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
            <div class="text-end mb-3">
                <a href="ClassCreate.action">新規登録</a>
            </div>
            <div>学校コード:${school.cd}</div>
			<div>学校名:${school.name}</div>

            <c:choose>
                <c:when test="${c_list.size()>0}">
                    <table class="table table-hover">
                            <tr>
                                <th>クラス番号</th>
                                <th>クラス名</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <c:forEach var="cl" items="${c_list}">
                                <tr>
                                    <td>${cl.classNum}</td>
                                    <td>${cl.className}</td>
                                    <td><a href="ClassUpdate.action?cd=${cl.classNum}">変更</a></td>
                                    <td><a href="ClassDelete.action?cd=${cl.classNum}">削除</a></td>
                                </tr>
                            </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>クラスが登録されていません。</p>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
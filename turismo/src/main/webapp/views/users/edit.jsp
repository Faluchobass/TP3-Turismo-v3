<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<c:if test="${user != null && !user.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear el usuario.</p>
			</div>
		</c:if>

		<form action="/turismo/users/edit.do" method="post">
		<%--XXX: Me gustaria hacer un post con el id del usuario modificado --%>
		<input type="hidden" id="user_id" name="user_id" value="${tmp_user.id}"></input>
		<input type="hidden" id="admin" name="admin" value="${tmp_user.isAdmin()}"></input>
		<%-- No me gusta de esta manera, se puede editar desde el html --%>
		
			<jsp:include page="/views/users/form.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>
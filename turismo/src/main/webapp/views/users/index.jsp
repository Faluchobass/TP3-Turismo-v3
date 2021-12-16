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

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>

		<div class="bg-light p-4 mb-3 rounded"
			style="background-image: url(/turismo/img/TierraMedia2.jpg);">
			<h1 style="color: silver";>Usuarios</h1>
		</div>

		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/turismo/users/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nuevo Usuario
				</a>
			</div>
		</c:if>
		<table class="table table-dark">
			<thead>
				<tr>
					<th scope="col">Nombre</th>
					<th scope="col">Monedas</th>
					<th scope="col">Tiempo</th>
					<th scope="col">Rol</th>
					<th scope="col">Estado</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="tmp_user">
					<tr>
						<td width="300"><strong><c:out
									value="${tmp_user.name}"></c:out></strong></td>
						<td><c:out value="${tmp_user.money}"></c:out></td>
						<td><c:out value="${tmp_user.time}"></c:out></td>
						<td><c:choose>
								<c:when test="${tmp_user.isAdmin()}">
									Admin
								</c:when>
								<c:otherwise>
									Normal
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${tmp_user.state}">

									<i class="fa fa-circle text-success"></i>
								</c:when>
								<c:otherwise>
									<i class="fa fa-circle" style="color: red;"></i>
								</c:otherwise>
							</c:choose></td>
						<td><c:if
								test="${user.isAdmin() && (!tmp_user.isAdmin() || tmp_user.isAdmin() == user.isAdmin())}">

								<a href="/turismo/users/alta.do?id=${tmp_user.id}" method="post"> <i
									class="fa fa-arrow-up"></i></a>
								<a href="/turismo/users/baja.do?id=${tmp_user.id}"> 
								<i class="fa fa-arrow-down"></i></a>
								<a href="/turismo/users/edit.do?id=${tmp_user.id}"> 
								<i class="fas fa-pencil-alt"></i></a>
								<a href="/turismo/users/delete.do?id=${tmp_user.id}"> 
								<i class="fa fa-ban"></i></a>

							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="mb-3">
			<a href="/turismo/index.jsp" class="btn btn-primary my-2">Volver</a>
		</div>
	</main>

</body>
</html>
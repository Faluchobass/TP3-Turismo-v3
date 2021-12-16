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

		<div class="bg-light p-4 mb-3 rounded">
			<h1>Itinerario</h1>
		</div>

		<table class="table table-dark">
			<thead>
				<tr>
					<th>Producto</th>
					<th>Costo</th>
					<th>Duraci&oacute;n</th>
<!-- 					<th>Acciones</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${offers}" var="offer">
					<tr>
						<td><strong><c:out value="${offer.getName()}"></c:out></strong>
						<p><c:out value="${offer.toString() }"></c:out></p>
							<p><c:out value="${offer.getDescription()}"></c:out></p></td>
						<td><c:out value="${offer.getVisitCost()}"></c:out></td>
						<td><c:out value="${offer.getTimeRequired() }"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>
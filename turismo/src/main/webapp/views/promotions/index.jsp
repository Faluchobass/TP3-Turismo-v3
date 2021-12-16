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
			<h1 style="color: silver";>Estos son los packs turísticos de la
				Tierra Media</h1>
		</div>
		<br>
		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/turismo/promotions/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Promoción
				</a>
			</div>
		</c:if>
		<table class="table table-dark">
			<thead>
				<tr>
					<th scope="col">Paquete turístico</th>
					<!--<th>Atracci&oacute;n</th> -->
					<th scope="col">Costo</th>
					<th scope="col">Duración</th>
<!-- 					<th scope="col">Cupo</th> -->
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${promotions}" var="promotion">
					<tr>
						<td>
						
							<button type="button" class="btn btn-link btn-sm" role="button" style="text-align:right;"
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								<strong><c:out value="${promotion.name}"></c:out></strong></button> <!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" style= "color: black !important" id="exampleModalLabel">Descripción
												del Producto</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-sm-9" style="text-align:middle;">
													<img src="/turismo/img/tierraMedia.jpg" class="img-fluid" alt="moria" />
												</div>
											</div>

											<div >
											<div class="col-sm-9 ms-auto" style="text-align: bottom;">

												<p style= "color: black !important">Lorem ipsum dolor sit amet, </p>
											</div>
											</div>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Close</button>

										</div>
									</div>
								</div>
							</div></td></td>
						<td><c:out value="${promotion.visitCost}"></c:out></td>
						<td><c:out value="${promotion.timeRequired}"></c:out></td>
<%-- 						<td><c:out value="${promotion.quota}"></c:out></td> --%>

						<td><c:if test="${user.isAdmin()}">
								<a href="/turismo/promotions/edit.do?id=${promotion.id}"><i
									class="fas fa-pencil-alt"></i></a>

								<a href="/turismo/promotions/delete.do?id=${promotion.id}">
									<i class="fa fa-ban"></i>
								</a>
							</c:if> <c:choose>
								<c:when
									test="${user.canAfford(promotion.visitCost) && user.canAttend(promotion.timeRequired) && promotion.checkVacancy()}">
									<a href="/turismo/promotions/buy.do?id=${promotion.id}"
										class="btn btn-primary">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-link btn-sm disabled"
										role="button">Comprar</a>
								</c:otherwise>
							</c:choose></td>
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
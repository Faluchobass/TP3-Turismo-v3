<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
</head>

<body>
	<header id="home-section">
		<div class="dark-overlay">
			<div class="home-inner">
				<div class="container">
					<div class="row">
						<div class="col-lg-8">
							<h1 class="display-4" style="color: black";>
								Bienvenidos al <strong>MULTIVERSO</strong>
							</h1>

							<!-- ITEM -->
							<div class="d-flex flex-row" style="color: silver";>
								<div class="p-4">
									<i class="fas fa-shield-alt"></i>
								</div>
								<div class="p-4" style="color: silver;">Disfruta de todas nuestras atracciones.</div>
							</div>

							<!-- ITEM -->
							<div class="d-flex flex-row" style="color: silver";>
								<div class="p-4">
									<i class="fas fa-shield-alt"></i>
								</div>
								<div class="p-4"style="color: silver";>Horas infinitas de diversión.</div>
							</div>

							<!-- ITEM -->
							<div class="d-flex flex-row" style="color: silver";>
								<div class="p-4">
									<i class="fas fa-shield-alt"></i>
								</div>
								<div class="p-4" style="color: silver";>Para quienes aman el fantástico mundo de
									la tierra media.</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="card text-center" style="background-color: #c0392b">
								<div class="card-body">
									<h3>Iniciar sesión</h3>
									<p>Ingresa tu usuario y contraseña para acceder a nuestros
										registros:</p>



									<form action="login" method="post">
										<c:if test="${flash != null}">
											<div class="alert alert-danger">
												<p>
													<c:out value="${flash}" />
												</p>
											</div>
										</c:if>

										<c:if test="${success != null}">
											<div class="alert alert-success">
												<p>
													<c:out value="${success}" />
												</p>
											</div>
										</c:if>
										<div class="form-group">
											<input type="text" class="form-control form-control-lg"
												name="username" placeholder="Usuario" required />
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-lg"
												name="password" placeholder="Contraseña" required />
										</div>

										<input type="submit" class="btn btn-outline-light btn-block"
											value="Enviar" />
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- BOOTSTRAP SCRIPT (JS) CDN -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>

</body>
</html>

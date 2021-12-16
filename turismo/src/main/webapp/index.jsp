<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="partials/nav.jsp"></jsp:include>

	<main class="container">
		<div class="bg-light p-4 rounded">
			<h1>
				¡Bienvenido,
				<c:out value="${user.getName()}" />
				!
			</h1>
		</div>
		<!-- 	CAROUSEL -->

		<div id="carouselExampleCaptions" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleCaptions"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleCaptions"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleCaptions"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
					data-bs-slide-to="3" aria-label="Slide 4"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
					data-bs-slide-to="4" aria-label="Slide 1"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="/turismo/img/pack_Aventura.jpg" class="d-block w-100"
						alt="..." width="auto" height="400">
					<div class="carousel-caption d-none d-md-block">
						<h5>Pack Aventura</h5>
						<p>Some representative placeholder content for the first
							slide.</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="/turismo/img/minas.jpg" class="d-block w-100" alt="..."
						width="auto" height="400">
					<div class="carousel-caption d-none d-md-block">
						<h5>Pack Aventura Plus</h5>
						<p>Some representative placeholder content for the second
							slide.</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="/turismo/img/lacomarca.jpg" class="d-block w-100"
						alt="..." width="auto" height="400">
					<div class="carousel-caption d-none d-md-block">
						<h5>Pack Degustación</h5>
						<p>Some representative placeholder content for the third
							slide.</p>
					</div>
					</div>
					<div class="carousel-item">
						<img src="/turismo/img/Lorien.jpg" class="d-block w-100"
							alt="..." width="auto" height="400">
						<div class="carousel-caption d-none d-md-block">
							<h5>Pack Degustación Gold</h5>
							<p>Some representative placeholder content for the third
								slide.</p>
						</div>
						</div>
						<div class="carousel-item">
							<img src="/turismo/img/mordor.jpg" class="d-block w-100"
								alt="..." width="auto" height="400">
							<div class="carousel-caption d-none d-md-block">
								<h5>Pack Aventura Extrema</h5>
								<p>Some representative placeholder content for the third
									slide.</p>
							</div>
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
	</main>
</body>
</html>

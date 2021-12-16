<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="name" class="col-form-label">Nombre:</label> <input
		type="text" class="form-control" id="name" name="name"
		required value="${attraction.getName()}">
</div>
<div class="mb-3">
	<label for="cost"
		class='col-form-label ${attraction.getErrors().get("cost") != null ? "is-invalid" : "" }'>Costo:</label>
	<input class="form-control" type="number" id="cost" name="cost"
		required value="${attraction.getVisitCost()}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.getErrors().get("cost")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="duration"
		class='col-form-label ${attraction.getErrors().get("duration") != null ? "is-invalid" : "" }'>Duration:</label>
	<input class="form-control" type="number" id="duration" name="duration"
		required value="${attraction.getDuration()}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.getErrors().get("duration")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="capacity"
		class='col-form-label ${attraction.getErrors().get("capacity") != null ? "is-invalid" : "" }'>Capacity:</label>
	<input class="form-control" type="number" id="capacity" name="capacity"
		required value="${attraction.getQuota()}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.getErrors().get("capacity")}'></c:out>
	</div>
</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>

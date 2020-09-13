<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<link rel="stylesheet" type="text/css" href="/formstyle.css">



<div class="container">
	<form action="/add-poll" method="get">
		<div class="formsection">
			<div class="form-group row">
				<label for="title" class="col-sm-2 col-form-label">Title</label>
				<div class="col-sm-10">
					<input type="text" class="form-control half-width" name="title" />
				</div>
			</div>
			<div class="form-group row">
				<label for="option" class="col-sm-2 col-form-label">Option</label>
				<div class="col-sm-10">
					<textarea type="text" class="form-control half-width" name="option"
						placeholder="A options comma seperated"></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label for="" class="col-sm-2 col-form-label"></label>
				<div class="col-sm-10">

					<button type="submit" class="btn btn-primary">Add</button>


				</div>
			</div>
		</div>

	</form>

	<div class="section">
		<h4>Poll List</h4>

		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>TITLE</th>
					<th>VIEW</th>
					<th>REMOVE</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${votes}" var="vote">
					<tr>
						<td>${vote}</td>
						<td><a type="button" class="btn btn-success"
							href="/view-vote?title=${vote}">View</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-vote?title=${vote}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>

<%@ include file="common/footer.jspf"%>
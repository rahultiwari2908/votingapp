<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<link rel="stylesheet" type="text/css" href="/formstyle.css">


<div class="container">
	<div class="inline optionform">
		<form action="/add-vote" method="get">
			<div class="form-group row">
				<label for="title" class="col-sm-2 col-form-label">Title</label>
				<div class="col-sm-10">
					<input type="text" readonly class="form-control-plaintext"
						value="${title}" name="title" />
				</div>
			</div>
			<div class="form-group row">
				<h5>I would like to vote for</h5>
			</div>
			<div class="form-group row">
				<label for="option" class="col-sm-2 col-form-label">Options</label>
				<div class="col-sm-10">
					<select class="form-control" id="optiondropdown"
						name="optiondropdown">
						<c:forEach items="${vote}" var="vote">
							<option value="${vote.id}">${vote.option}</option>

						</c:forEach>

					</select>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>

		</form>
	</div>

	<div class="inline chart">
		<%@ include file="common/chart.jsp"%>

	</div>

</div>



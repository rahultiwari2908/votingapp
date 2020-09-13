<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link rel="stylesheet"
	href="https://code.highcharts.com/css/highcharts.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "/get-data",
			success : function(result) {
				var series = [];
				var data = [];
				console.log(result);
				for (var i = 0; i < result.length; i++) {
					var object = {};
					object.name = result[i].option;
					object.y = result[i].count;

					data.push(object);
				}
				var seriesObj = {
					name : 'Options',
					colorByPoint : true,
					data : data

				}
				series.push(seriesObj);
				drawChart(series);
			}
		});
	});

	function drawChart(series) {
		Highcharts
				.chart(
						'container',
						{
							chart : {
								plotBackgroundColor : null,
								plotBorderWidth : null,
								plotShadow : false,
								type : 'pie'
							},
							title : {
								text : 'Datails of voting done so far'
							},
							tooltip : {
								pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
							},
							accessibility : {
								point : {
									valueSuffix : '%'
								}
							},
							plotOptions : {
								pie : {
									allowPointSelect : true,
									cursor : 'pointer',
									dataLabels : {
										enabled : true,
										format : '<b>{point.name}</b>: {point.percentage:.1f} %'
									}
								}
							},
							series : series
						});

	}
</script>
</head>
<body>
	<div id="container"
		style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>
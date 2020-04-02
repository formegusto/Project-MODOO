<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<title>chartConfirm.jsp</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<canvas id="myLineChart" width="400" height="400"></canvas>
<script>
var ctx = document.getElementById('myLineChart').getContext('2d');
var myLineChart = new Chart(ctx, {
	// The type of chart we want to create
    type: 'line',
    // The data for our dataset
    data: {
    	labels: ${stringList},
        datasets: [{
        	data: ${numList},
            label: "My First dataset",
            backgroundColor: 'rgb(255, 99, 132)',
            fill:false, // line의 아래쪽을 색칠할 것인가? 
            borderColor: 'rgb(255, 99, 132)',
            lineTension:0.1, // 값을 높이면, line의 장력이 커짐.
        }]
    },
    // Configuration options go here
    options: {}
});
</script>
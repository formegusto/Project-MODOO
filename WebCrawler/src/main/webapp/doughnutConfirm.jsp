<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<title>chartConfirm.jsp</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<canvas id="myDoughnut" width="400" height="400"></canvas>
<script>
var ctx = document.getElementById('myDoughnut').getContext('2d');
var myDoughnutChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
    	labels:${stringList},
    	datasets: [{
            data: ${numList},
            backgroundColor: ${bgList},
            borderColor: ${boList}
        }]
    },
    options: {}
});
</script>
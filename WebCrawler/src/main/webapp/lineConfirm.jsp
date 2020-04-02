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
            fill:false, // line�� �Ʒ����� ��ĥ�� ���ΰ�? 
            borderColor: 'rgb(255, 99, 132)',
            lineTension:0.1, // ���� ���̸�, line�� ����� Ŀ��.
        }]
    },
    // Configuration options go here
    options: {}
});
</script>
<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<title>chartConfirm.jsp</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<canvas id="myPolarArea" width="400" height="400"></canvas>
<script>
var ctx = document.getElementById('myPolarArea').getContext('2d');
new Chart(ctx, {
	type: 'polarArea',
    data: {
    	labels: ${stringList},
    	datasets: [{
    		data: ${numList},
            backgroundColor: ${bgList},
            borderColor: ${boList}
        }]
    },
    options: {}
});
</script>
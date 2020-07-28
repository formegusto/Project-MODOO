function visualDraw(ctx,labels,datas) {
	new Chart(ctx, {
    // The type of chart we want to create
    type: 'line',

    // The data for our dataset
    data: {
        labels: labels,
        datasets: [{
            label: '���� �̸��� �����ּ���!',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(0,0,0)',
            data: datas
        }]
    },
	});
}
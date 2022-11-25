


function start() {
	var apiURL = 'http://localhost:8088/product'
	getOrderInfo(function(data) {
		renderOrderinfo(data)
	}, apiURL);
}

start()

function getOrderInfo(callback, apiURL) {
	fetch(apiURL, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		}
	})
		.then(function(res) {
			return res.json();
		})
		.then(callback);
}

function renderOrderinfo(data) {
	var list = document.querySelector('#product');


	var info_product = data.map((value) => {
		return {
			id: value.id,
			name: value.productName,
			categoryName: value.category.categoryName,
			quantity: value.quantity,
			price: value.price,
		};
	});

	info_product.map(function(items) {
		var formatSum = items.price.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });

		$(list).append(`                                
                        <tr>
                            <td>${items.id}</td>
                            <td>${items.name}</td>
                            <td>${items.categoryName}</td>
                            <td>${items.quantity}</td>
                            <td>${formatSum}</td>
                            <td><a href="#" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a></td>
                        </tr>                                      
			`);
	}
	);

}
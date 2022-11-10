var apiURL = 'http://localhost:8088/orderDetail/delivering/7/1'

function start(){
	getOrderInfo(renderOrderinfo);
}
start();

function getOrderInfo(callback){
	fetch(apiURL, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	})
		.then(function(res){
			return res.json();
		})
		.then(callback);
}

function renderOrderinfo(order){
	var list = document.querySelector('#list_order')
	console.log(order)
	var htmls = order.data.items.map(function(orderInfo){
		return `
			<div class="item_order" id="list_order">
						<div class="col-md-12 header_order">Chờ xác nhận</div>
						<div class="col-md-12 body_order">
							<div class="col-md-2">
								<img class="body_img"
									src="https://cdn0.fahasa.com/media/catalog/product/8/9/8936071674593_1_1.jpg"
									alt="">
							</div>
							<div class="col-md-10 body_content">
								<span class="content_item body_name">${orderInfo.name}</span> <span class="content_item body_quantity">x${orderInfo.quantity}</span>
							</div>
						</div>
						
					</div>
				
		`;
	});
	list.innerHTML = htmls.join('');
}
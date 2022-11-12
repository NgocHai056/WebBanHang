function start(statusId, id) {
	var apiURL = 'http://localhost:8088/orderDetail/delivering/' + '7' + '/' + statusId
	getOrderInfo(function(data){
		renderOrderinfo(data, id)
	}, apiURL);
}

start(1, '.waiting_order');
start(2, '.shipping_order');
start(3, '.shipped_order');
start(4, '.cancel_order');

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

function renderOrderinfo(data, id) {
	var list = document.querySelector(id);
	console.log(data);
	
	if (data.length == 0) {
		
		list.innerHTML = `<div class="no_order">
               <img class="img-no_order" src="./assets/img/no_order.png" alt="">
               <span class="titler-no_order">Chưa có đơn hàng</span>
      		</div>`	
	}
	else {
		var info_product = data.map((value) => {
			return {
				product: value.product["0"],
				amount: value.quantiry,
			};
		});
		var htmls = info_product.map((items) => {
			return `
			<div class="item_order">
						<div class="col-md-12 header_order"></div>
						<div class="col-md-12 body_order">
							<div class="col-md-2">
								<img class="body_img"
									src="${items.product.image}"
									alt="">
							</div>
							<div class="col-md-10 body_content">
								<span class="content_item body_name">${items.product.productName}</span> 
								<span class="content_item body_quantity">x${items.amount}</span>
							</div>
						</div>
						<div class="col-md-12 footer_order">
							<div class="col-md- footer_item">
								Tổng số tiền:
								<div class="total">${items.product.price}đ</div>
							</div>
						</div>
					</div>
		`;
		});
		list.innerHTML = htmls.join('');
	}
}
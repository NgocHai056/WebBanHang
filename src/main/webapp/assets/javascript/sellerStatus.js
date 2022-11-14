function start(statusId, id) {
	var apiURL = 'http://localhost:8088/orderDetail/allOrder/?idStore=1&idStatus=' + statusId
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
				user: value.order.user,
				order: value.order,
				status: value.order.status,
				
			};
		});
		
		var htmls = info_product.map((items) => {
			return `
			<div class="item_order">
					<div class="col-md-12 header_order">
						<div class="header-item client_name col-md-7">
							<span style="color: #fff;">Khách hàng: </span> ${items.user.users_first_name} 
							${items.user.users_last_name} 							
						</div>
						<div class="header-item day_set col-md-2">${items.order.create_at}</div>
						<div class="header-item status_order col-md-3">${items.status.statusName}
						</div>
						<div class="header-item client_name col-md-7">
							<span style="color: #fff;">Địa chỉ: </span>${items.order.address}
						</div>
					</div>
					<div class="col-md-12 body_order">
						<div class="col-md-12 body_content">
							<span class="content_item body_name col-md-7">${items.product.productName}</span> 
							<span class="content_item body_quantity col-md-2">x${items.amount}</span> 
							<span class="content_item body_total col-md-3">${items.product.price}đ</span>
						</div>
					</div>
					<div class="col-md-12 footer_order">
						<div class="footer_item">
							Tổng số tiền:
							<div class="total">100.000đ</div>
						</div>
						<span class="col-md-12 footer_item footer_item-btn"> <a
							href="" class="btn footer_btn">Xác nhận</a> <a href=""
							class="btn footer_btn">Huỷ</a>
						</span>
					</div>
				</div>
		`;
		});
		list.innerHTML = htmls.join('');
	}
}
const arrId = []
const arrQuantity = []
const arrPrice = []
var flag = 0

var id = document.querySelectorAll("#id")
var valueToken = document.cookie
  .split('; ')
  .find(row => row.startsWith('Authorization='))
  .split('=')[1];

id.forEach(function(value) {
	arrId.push(value.defaultValue)
})

var quantity = document.querySelectorAll("#quantity")

quantity.forEach(function(value) {
	arrQuantity.push(value.defaultValue)
})

var price = document.querySelectorAll("#price")

price.forEach(function(value) {
	arrPrice.push(value.defaultValue)
})

const arrOrder = []




var currentDate = new Date().toJSON().slice(0, 10);

const or = {
	"address": "",
	"createAt": currentDate,
	"phone": "",
	"priceTotal": "",
	"updateAt": currentDate,
	"delivery": {
		"id": 1
	},
	"orderStatus": {
		"id": 1
	},
	"store": {
		"id": 1
	}
}

var totalPr = document.querySelector("#totalPrice")
or.priceTotal = totalPr.defaultValue

var tel = document.querySelector("#tel")
or.phone = tel.defaultValue
tel.addEventListener('change', function(e) {
	or.phone = e.target.value
})

var address = document.querySelector("#address")
or.address = address.defaultValue
address.addEventListener('change', function(e) {
	or.address = e.target.value

})

var idCart = document.querySelector("#idCart")
var btn = document.querySelector("#paymentMethod")
btn.addEventListener('click', function() {
	if (or.phone != "" && or.address != "") {
		fetch('http://localhost:8088/order', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': valueToken
			},
			body: JSON.stringify(or),
		})
			.then(function(res) {

				return res.json();
			})
			.then((data) => {
				for (let i = 0; i < arrId.length; i++) {
					const order = {
						"createAt": currentDate,
						"quantiry": arrQuantity[i],
						"totalPrice": arrPrice[i],
						"updateAt": currentDate,
						"order": {
							"id": data.id
						},
						"paymentMethod": {
							"id": 1
						},
						"product": {
							"id": arrId[i]
						}
					}
					arrOrder.push(order)
				}
				fetch('http://localhost:8088/orderDetail/order', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json',
						'Authorization': valueToken
					},
					body: JSON.stringify(arrOrder),
				})
					.then(function(res) {
						window.location.href = 'http://' + window.location.host + '/WebBanHang/home'
						return res.json();
					})
					.then((data) => {
						console.log(data)
						alert("Đặt hàng thành công!!!");
					});
			});
	}
	else{
		alert("Vui lòng điền đầy đủ thông tin")
	}
})
function createOrderItems() {
	if (flag == 1) {

	}
}

function removeCartItems(id) {
	if (flag == 1) {
		fetch('http://localhost:8088/cartItems/' + id, {
			method: 'DELETE',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': valueToken
			},
		})
			.then(function(res) {
				return res.json();
			})
			.then((data) => {
				console.log(data)
			});
	}
}

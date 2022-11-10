const input = document.querySelectorAll('input')

input.forEach(function(input){
	input.addEventListener('change', cartTotal)
})

function cartTotal(){
	var cartItems = document.querySelectorAll('div .mask');
	var totalPrice = document.querySelector('.total span')
	
	var total = 0
	for(var i=0; i<cartItems.length; i++){
		var inputValue = cartItems[i].querySelector('input').value
		var productPrice = cartItems[i].querySelector('span').innerHTML
		total = total + (inputValue * productPrice)			
	}	
	totalPrice.innerHTML = total.toLocaleString('de-DE')
}
var idUser = document.querySelector('#idUser')
var IDUser = idUser.defaultValue

var idname = document.querySelector('#name')
var idtel = document.querySelector('#tel')
var idemail = document.querySelector('#email')
var idtext = document.querySelector('#text')
var valueToken = document.cookie
  .split('; ')
  .find(row => row.startsWith('Authorization='))
  .split('=')[1];
  
function getStore() {
	fetch('http://localhost:8088/store/?idUser=' + IDUser, {
		method: 'GET',		
		headers: {
			'Content-Type': 'application/json',
			'Authorization': valueToken
		},
	})
		.then(function(res) {
			return res.json();
		})
		.then((data) => {
			
			idname.value = data.storeName,
			idtel.value = data.user.users_phone,
			idemail.value = data.user.users_email,
			idtext.value = data.addressStore
		});
}
getStore()
function addToCart(filmId) {
	console.log(filmId);
	$.ajax({
		url : 'CartServlet',
		data : {
			film_id : filmId
		},
		success: function(){
			$("#cart-size").load("header.jsp #cart-size");
			
		}
	})	
}

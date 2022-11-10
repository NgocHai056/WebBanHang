<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<head>
<title>Chi tiết sản phẩm</title>
<!--Important link from https://bootsnipp.com/snippets/XqvZr-->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
<link rel="stylesheet"
	href="<c:url value="./assets/css/product_detail.css"></c:url>">

</head>

<body>
	<div class="pd-wrap">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div id="slider" class="owl-carousel product-slider">
						<div class="item">
							<img src="${productDetail.image}" />
						</div>
						<c:forEach items="${pdImage}" var="img">
							<div class="item">
								<img src="${img.product_image}" />
							</div>
						</c:forEach>
					</div>
					<div id="thumb" class="owl-carousel product-thumb">
						<div class="item thumb-item">
							<img src="${productDetail.image}" />
						</div>
						<c:forEach items="${pdImage}" var="img">
							<div class="item thumb-item">
								<img src="${img.product_image}" />
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="col-md-6">
					<div class="product-dtl">
						<div class="product-info">
							<div class="product-name">${productDetail.productName}</div>
							<div class="reviews-counter">
								<div class="rate">
									<input type="radio" id="star5" name="rate" value="5" checked />
									<label for="star5" title="text">5 stars</label> <input
										type="radio" id="star4" name="rate" value="4" checked /> <label
										for="star4" title="text">4 stars</label> <input type="radio"
										id="star3" name="rate" value="3" checked /> <label
										for="star3" title="text">3 stars</label> <input type="radio"
										id="star2" name="rate" value="2" /> <label for="star2"
										title="text">2 stars</label> <input type="radio" id="star1"
										name="rate" value="1" /> <label for="star1" title="text">1
										star</label>
								</div>
								<span>3 Đánh giá</span>
							</div>
							<div class="product-price-discount">
								<span style="color: var(- -primary-color);"><fmt:formatNumber
										type="number" pattern="#,###"
										value="${productDetail.price}"></fmt:formatNumber>đ</span>
								<span class="line-through"><fmt:formatNumber
										type="number" pattern="#,###"
										value="${productDetail.price - (productDetail.price * productDetail.id_discount) / 100}"></fmt:formatNumber>đ</span>
							</div>
						</div>
						<p></p>

						<div class="row">
							<div class="col-md-6">
								<label for="size">Hình thức bìa: ${cover.type_name}</label>
							</div>
							<div class="col-md-6">
								<label for="color"></label>
							</div>
						</div>

						<div class="product-count">
							<label for="size">Số lượng</label>
							<form action="#" class="display-flex">
								<div class="qtyminus">-</div>
								<input type="text" name="quantity" value="1" class="qty">
								<div class="qtyplus">+</div>
							</form>
							<a href="#" class="round-pink-btn">Thêm vào giỏ hàng</a> <a
								href="#" class="round-pink-btn">Mua ngay</a>
						</div>
					</div>
				</div>
				<div class="product-info-tabs col-md-12">
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							id="description-tab" data-toggle="tab" href="#description"
							role="tab" aria-controls="description" aria-selected="true">Mô
								tả</a></li>
						<li class="nav-item"><a class="nav-link" id="review-tab"
							data-toggle="tab" href="#review" role="tab"
							aria-controls="review" aria-selected="false">Đánh giá (0)</a></li>
					</ul>
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="description"
							role="tabpanel" aria-labelledby="description-tab">${productDetail.description}</div>
						<div class="tab-pane fade" id="review" role="tabpanel"
							aria-labelledby="review-tab">
							<div style="color: var(- -primary-color);" class="review-heading">Đánh
								giá</div>
							<p class="mb-20">Chưa có đánh giá nào.</p>
							<form class="review-form">
								<div class="form-group">
									<label>Đánh giá của bạn</label>
									<div class="reviews-counter">
										<div class="rate">
											<input type="radio" id="star5" name="rate" value="5" /> <label
												for="star5" title="text">5 stars</label> <input type="radio"
												id="star4" name="rate" value="4" /> <label for="star4"
												title="text">4 stars</label> <input type="radio" id="star3"
												name="rate" value="3" /> <label for="star3" title="text">3
												stars</label> <input type="radio" id="star2" name="rate" value="2" />
											<label for="star2" title="text">2 stars</label> <input
												type="radio" id="star1" name="rate" value="1" /> <label
												for="star1" title="text">1 star</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>Bình luận</label>
									<textarea class="form-control" rows="10"></textarea>
								</div>
								<button class="round-pink-btn">Gửi</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

	<!--Import jQuery before materialize.js-->

	<script defer src="plugins/OwlCarousel2.3/owl.carousel.min.js"></script>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.14.0/sweetalert2.all.min.js"></script>

	<script>
		$("#owl-demo").owlCarousel({
			navigation : true
		});
		
		jQuery(function(slider.owlCarousel){
			items: 1,
	        slideSpeed: 2000,
	        nav: false,
	        autoplay: false, 
	        dots: false,
	        loop: true,
	        responsiveRefreshRate: 200
	        });
	</script>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="	sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="	sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script
		src="<c:url value="./assets/javascript/product_detail.js"></c:url>"></script>
</body>
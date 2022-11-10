<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
    <title>Mua sắm online</title>
    <link rel="stylesheet" href='<c:url value="./assets/css/category.css"></c:url>'>
</head>

<body>
	<div class="container">
		<!-- Content -->
		<div class="content">
			<div class="content-item">
				<div class="content_header content_header--fCategory">
					<a href="" class="">${categoryName.category_name}</a> <a
						class="dropdown-toggle dropdown-toggle_category dropdown-toggle--fCategory"
						href="#" role="button" data-toggle="dropdown" aria-expanded="true">
						Giá </a>
					<div class="dropdown-menu sort-list">
						<a class="sort-item" href="">Giá: Giảm dần</a> <a
							class="sort-item" href="">Giá: Tăng dần</a>
					</div>
				</div>
				<div class="row">
					<c:forEach items="${listAllProducts}" var="pd">									
						<div class="col-2-4">
								<div class="card">
									<a href="PdsDetail?psdID=${pd.id}" class="card-item"> <img
										src="${pd.image}" class="card-img-top" alt="${pd.productName}">
										<div class="card-body">
											<h6 class="card-title home-product-item__name">${pd.productName}</h6>
											<div class="home-product-item__price">
												<span class="home-product-item__price-old"><fmt:formatNumber
														type="number" pattern="#,###" value="${pd.price}"></fmt:formatNumber>đ</span>
												<span class="home-product-item__price-current"> <fmt:formatNumber
														type="number" pattern="#,###"
														value="${pd.price - (pd.price * pd.id_discount) / 100}"></fmt:formatNumber>đ
												</span>
											</div>
										</div> <c:if test="${pd.id_discount != 0}">
											<div class="home-product-item__sale-off">${pd.id_discount}%</div>
										</c:if>
									</a>

									<div class="card-body card-btn">
										<c:if test="${sessionScope.account != null}">
											<a type="submit" onclick="buy('${pd.id}')" class="card-link">Thêm
												vào giỏ hàng</a>
										</c:if>
										<c:if test="${sessionScope.account == null}">
											<a href="layout-Login?mask=login" class="card-link">Thêm vào giỏ
												hàng</a>
										</c:if>
									</div>
								</div>
							</div>
					</c:forEach>
				</div>
				<div class="content_footer">
					<ul class="content_paging row">
						<li class="paging-item col"><i
							class="fa-sharp fa-solid fa-angle-left pagination-item__link"></i>
						</li>
						<li class="paging-item col"><a href=""
							class="pagination-item__link paging_active">1</a></li>
						<li class="paging-item col"><a href=""
							class="pagination-item__link">2</a></li>
						<li class="paging-item col"><a href=""
							class="pagination-item__link">3</a></li>
						<li class="paging-item col"><a href=""
							class="pagination-item__link">4</a></li>
						<li class="paging-item col"><a href=""
							class="pagination-item__link">...</a></li>
						<li class="paging-item col"><a href=""
							class="pagination-item__link">10</a></li>
						<li class="paging-item col"><i
							class="fa-sharp fa-solid fa-angle-right pagination-item__link"></i>
						</li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	
</body>

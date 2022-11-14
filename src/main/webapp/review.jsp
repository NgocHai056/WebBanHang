<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
<link rel="stylesheet" href="./assets/css/review.css">
</head>
<body>
<div align="center">
    <h1 class="title_header">Vui lòng xem lại trước khi thanh toán</h1>
    <form action="execute_payment" method="get">
    <table>
        <tr>
            <td class="title_sc" colspan="2"><b>Chi tiết giao dịch:</b></td>
            <td>
                <input type="hidden" name="paymentId" value="${param.paymentId}" />
                <input type="hidden" name="PayerID" value="${param.PayerID}" />
            </td>
        </tr>
        <tr>
            <td>Mô tả:</td>
            <td>${transaction.description}</td>
        </tr>
        <tr>
            <td>Tổng giá:</td>
            <td>${transaction.amount.details.subtotal} USD</td>
        </tr>
        <tr>
            <td>Tiền vận chuyển:</td>
            <td>${transaction.amount.details.shipping} USD</td>
        </tr>
        <tr>
            <td>Thuế:</td>
            <td>${transaction.amount.details.tax} USD</td>
        </tr>
        <tr>
            <td>Tổng:</td>
            <td>${transaction.amount.total} USD</td>
        </tr>
        <tr><td><br/></td></tr>
        <tr>
            <td class="title_sc" colspan="2"><b>Thông tin người thanh toán:</b></td>
        </tr>
        <tr>
            <td>Họ:</td>
            <td>${payer.firstName}</td>
        </tr>
        <tr>
            <td>Tên:</td>
            <td>${payer.lastName}</td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>${payer.email}</td>
        </tr>
        <tr><td><br/></td></tr>
        <tr>
            <td class="title_sc" colspan="2"><b>Địa chỉ giao hàng:</b></td>
        </tr>
        <tr>
            <td>Tên người nhận:</td>
            <td>${shippingAddress.recipientName}</td>
        </tr>
        <tr>
            <td>Địa chỉ:</td>
            <td>${shippingAddress.line1}</td>
        </tr>
        <tr>
            <td>Tỉnh/Thành phố:</td>
            <td>${shippingAddress.city}</td>
        </tr>
        <tr>
            <td>Quận/Huyện:</td>
            <td>${shippingAddress.state}</td>
        </tr>
        <tr>
            <td>Mã quốc gia:</td>
            <td>${shippingAddress.countryCode}</td>
        </tr>
        <tr>
            <td>Mã bưu điện:</td>
            <td>${shippingAddress.postalCode}</td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input class="btn" type="submit" value="Thanh toán" />
            </td>
        </tr>    
    </table>
    </form>
</div>
</body>
</html>
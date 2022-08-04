<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 7/26/2022
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Delete customer</title>
    <style>
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>

<h1>Xóa sản phẩm</h1>
<p>
    <button><a href="/product-servlet">Quay lại quản lý sản phẩm</a></button>

</p>
<form method="post" action="">
    <h3>Bạn có chắc chắn muốn xóa?</h3>
    <p>
        <c:if test='${requestScope["message"] != null}'>
            <span class="message"> ${requestScope["message"]}</span>
        </c:if>
    </p>
    <fieldset>
        <legend>Thông tin sản phẩm</legend>
        <table>
            <tr>
                <td>Id: </td>
                <td>${requestScope["product"].getId()}</td>
            </tr>
            <tr>
                <td>Tên: </td>
                <td>${requestScope["product"].getName()}</td>
            </tr>
            <tr>
                <td>Giá: </td>
                <td>${requestScope["product"].changePrice()}</td>
            </tr>
            <tr>
                <td>Màu: </td>
                <td>${requestScope["product"].getColor()}</td>
            </tr>
            <tr>
                <td>Mô tả: </td>
                <td>${requestScope["product"].getDescription()}</td>
            </tr>
            <tr>
                <td>Loại: </td>
                <td>${requestScope["product"].getCategory().getName()}</td>
            </tr>
            <tr>
                <td><input type="submit" value="Xóa"></td>
                <td><button><a href="/product-servlet">Quay lại</a></button>  </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>

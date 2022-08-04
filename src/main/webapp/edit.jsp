<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 7/26/2022
  Time: 8:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Edit product</title>
  <style>
    a {
      text-decoration: none;
    }
  </style>
</head>
<body>
<h1>Chỉnh sửa sản phẩm</h1>
<p>
  <c:if test='${requestScope["message"] != null}'>
    <span class="message"> ${requestScope["message"]}</span>
  </c:if>
</p>

<p>
  <button><a href="/product-servlet">Quay lại quản lý sản phẩm</a></button>

</p>
<form method="post" action="">
  <fieldset>
    <legend>
      Thông tin sản phẩm
    </legend>
    <table>
      <tr>
        <td>Tên: </td>
        <td><input type="text" name="name" id="name" value="${requestScope["product"].getName()}"></td>
      </tr>
      <tr>
        <td>Giá: </td>
        <td><input type="text" name="price" id="price" value="${requestScope["product"].getPrice()}"></td>
      </tr>
      <tr>
        <td>Số lượng: </td>
        <td><input type="text" name="quantity" id="quantity" value="${requestScope["product"].getQuantity()}"></td>
      </tr>
      <tr>
        <td>Màu: </td>
        <td><input type="text" name="color" id="color" value="${requestScope["product"].getColor()}"></td>
      </tr>
      <tr>
        <td>Mô tả:: </td>
        <td><input type="text" name="description" id="ds" value="${requestScope["product"].getDescription()}"></td>
      </tr>
      <tr>
        <td>Loại </td>
        <td>
          <select name="category" id="category">
            <c:forEach items="${categories}" var="c">
              <option value="${c.getId()}">${c.getName()}</option>
            </c:forEach>
          </select>
        </td>

      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Update customer"></td>
      </tr>
    </table>
  </fieldset>
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>--%>
<html>
<head>
    <title>details of added purchase</title>
</head>
<body>
<a href="home">home</a>



<h4>You added following purchase:</h4>
<p>

  product name: ${purchase.productName}
</p>
<p>

    quantity: ${purchase.quantity}
</p>
<p>

    purchase date: ${purchase.purchaseDate}
</p>

</body>
</html>

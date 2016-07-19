<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>details of added purchase</title>
</head>
<body>
<a href="home"><b>home</b></a>
<h4>You added following purchase:</h4>
<p>

  product name: ${purchase.product.name}
</p>
<p>

    quantity: ${purchase.quantity}
</p>
<p>

    purchase date:<fmt:formatDate pattern="dd.MM.yyyy hh:mm:ss Z zzzz" value= "${purchase.purchaseDate}" />
</p>

</body>
</html>

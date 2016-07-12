<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>add_purchase</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
</head>
<body onload="createSelectInput()">
<a href="home">home</a>
<form:form modelAttribute="purchase" action="purchase_added">
    <table>
        <tr>
            <td>select product :<form:select path="productName" items="${products}"/> </td>
            <td> <form:errors path="productName" cssClass="error"/> </td>

        </tr>

        <tr>
            <td>enter quantity : <form:input type="text" path="quantity"/></td>
            <td> <form:errors path="quantity" cssClass="error"/></td>

        </tr>
        <tr>
            <td>enter date(dd.mm.yyyy) : <form:input type="text" path="purchaseDate"/></td>
            <td><form:errors path="purchaseDate" cssClass="error"/></td>
        </tr>
    </table>
    <input type="submit" value="Add purchase">
</form:form>

</body>

</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>report</title>
</head>
<style>
    table {
        border-collapse: collapse;
    }

    table, th, td {
        border: 1px solid black;
    }
</style>
<body>
<a href="home"><b>home</b></a>
<div  >

        <div>
            <table>
                <tr>
                    <td>PRODUCT_NAME</td>
                    <td>PRODUCT_COUNT</td>
                    <td>PURCHASE_DATE</td>
                    <td>PURCHASE_SUM</td>
                </tr>
            <c:forEach items="${report}" var="item">
                <tr>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
                    <td>${item.purchaseDate}</td>
                    <td>${item.sum}</td>
                </tr>
            </c:forEach>
            </table>
        </div>
</div>
</body>

</html>

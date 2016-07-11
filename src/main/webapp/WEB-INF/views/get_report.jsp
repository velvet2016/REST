<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>get_report</title>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<a href="home"><b>home</b></a>

<form:form method="post" modelAttribute="reportArgs" action="report_list_purchases">

    <table>
        <tr>
            <td>enter number of months : <form:input type="text" path="monthCount"/></td>
            <td><form:errors path="monthCount" cssClass="error"/></td>
        </tr>
    </table>

    <input type="submit" value="Get Report">
</form:form>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>get_report</title>
</head>
<body>
<a href="home"><b>home</b></a>
<div>
    <p>
        <form:errors path="reportArgs.*"></form:errors>
    </p>
</div>
<form method="post" action="report_list_purchases">

    <div>
        <p>
            enter number of months : <input type="text" name="monthCount">
        </p>
    </div>

    <input type="submit" value="Get Report">
</form>
</body>
</html>

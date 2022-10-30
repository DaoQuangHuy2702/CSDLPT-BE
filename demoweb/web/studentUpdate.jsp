<%-- 
    Document   : studentUpdate
    Created on : Oct 31, 2022, 12:44:14 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Update Student</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <h1>Update Student</h1>
        <form action="StudentServlet" method="post">
            <input type="hidden" name="action" value="update_student">        
            <label class="pad_top">ID:</label>
            <input type="text" name="text" value="${student.id}" disabled><br>
            <label class="pad_top">Name:</label>
            <input type="text" name="name" value="${student.name}" required><br>
            <label class="pad_top">Branch ID:</label>
            <input type="text" name="branchId" value="${student.branchId}" required><br>        
            <label>&nbsp;</label>
            <input type="submit" value="Update" class="margin_left">
        </form>
            <p><a href="./StudentServlet">Back</a></p>
    </body>
</html>

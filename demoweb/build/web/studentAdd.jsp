<%-- 
    Document   : studentAdd.jsp
    Created on : Oct 31, 2022, 12:58:26 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Add Student</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <h1>Add Student</h1>
        <form action="StudentServlet" method="post">
            <input type="hidden" name="action" value="add_student">        
            <label class="pad_top">ID:</label>
            <input type="text" name="id" required><br>
            <label class="pad_top">Name:</label>
            <input type="text" name="name" required><br>
            <label class="pad_top">Branch ID:</label>
            <input type="text" name="branchId" required><br>        
            <label>&nbsp;</label>
            <input type="submit" value="Add" class="margin_left">
        </form>
            <p><a href="./StudentServlet">Back</a></p>
    </body>
</html>

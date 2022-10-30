<%-- 
    Document   : index.jsp
    Created on : Oct 30, 2022, 10:07:16 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <h1>List Student</h1>

        <table>

          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Branch ID</th>
            <th colspan="2">Action</th>
          </tr>

          <c:forEach var="student" items="${students}">
          <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.branchId}</td>
            <td><a href="StudentServlet?action=display_student&amp;id=${student.id}">Update</a></td>
            <td><a href="StudentServlet?action=delete_student&amp;id=${student.id}">Delete</a></td>
          </tr>
          </c:forEach>

        </table>

        <p><a href="StudentServlet">Refresh</a></p>
        <p><a href="StudentServlet?action=new_student">Add Student</a></p>
    </body>
</html>


<%-- 
    Document   : header
    Created on : May 16, 2018, 10:27:51 PM
    Author     : nhtoan
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
    </head>
    <body>
        <div class="header">
            <div class="title"><a href="/ParkingSystem">Parking System </a></div>
            <%
                Users users = (Users) session.getAttribute("users");
                if (users == null) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);
                }
            %>
            <div class="header_right">Welcome  <%= users.getUserName()%>, <a href="#" class="settings">Settings</a> <a href="UsersServlet?command=logout" class="logout">Logout</a> </div>

            <div class="menu">
                <ul>
                    <li><a href="#" class="selected">Main page</a></li>
                    <li><a href="#">Settings</a></li>
                    <li><a href="#">Add a category</a></li>
                    <li><a href="#">Edit categories</a></li>
                    <li><a href="#">Categories</a></li>
                    <li><a href="#">Options</a></li>
                    <li><a href="#">Admin settings</a></li>
                    <li><a href="#">Help</a></li>
                </ul>
            </div>

        </div>
    </body>
</html>

<%-- 
    Document   : login
    Created on : May 16, 2018, 3:05:11 PM
    Author     : nhtoan
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Parking System</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
    </head>
    <body>
            <%
                Users users = (Users) session.getAttribute("users");
                if (users != null) {
                    response.sendRedirect("/ParkingSystem/index.jsp");
                }
            %>
        <div id="loginpanelwrap">

            <div class="loginheader">
                <div class="logintitle"><a href="#">Parking System</a></div>
            </div>


            <form class="loginform" action="UsersServlet" method="POST">

                <div class="loginform_row">
                    <label>Username:</label>
                    <input type="text" class="loginform_input" name="username" />
                </div>
                <div class="loginform_row">
                    <label>Password:</label>
                    <input type="password" class="loginform_input" name="password" />
                </div>
                <div class="loginform_row">
                    <input type="submit" class="loginform_submit" value="Login" />
                </div> 
                <% String error = "";
                    if(request.getAttribute("error")!=null){
                        error= (String)request.getAttribute("error");
                    }
                %>
                <p> <%=error %></p>
                <div class="clear"></div>
            </form>


        </div>
    </body>
</html>

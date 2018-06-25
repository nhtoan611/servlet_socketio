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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.1.1/socket.io.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script>
            $(document).ready(function(){
                $(".menu a").click(function(){
                    $(".menu a").removeClass("selected");
                    $(this).addClass("selected");
                });
            });
        </script>
    </head>
    <body>
        <div class="header">
            <div class="title"><a href="/ParkingSystem/index.jsp">Parking System </a></div>
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
                    <li><a href="/ParkingSystem/index.jsp" >Danh sách xe vào</a></li>
                    <li><a href="/ParkingSystem/outcar.jsp">Danh sách xe ra</a></li>
                    <li><a href="/ParkingSystem/price.jsp">Quản lí biểu phí</a></li>
                    <li><a href="/ParkingSystem/statistic.jsp">Báo cáo thống kê</a></li>
                    <li><a href="/ParkingSystem/follow.jsp">Danh sách xe theo dõi</a></li>
                    <li><a href="/ParkingSystem/monthlycars.jsp">Xe gửi theo tháng</a></li>
                </ul>
            </div>

        </div>
    </body>
</html>

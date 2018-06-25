<%-- 
    Document   : content
    Created on : May 16, 2018, 3:03:12 PM
    Author     : nhtoan
--%>

<%@page import="model.Owners"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cars"%>
<%@page import="dao.CarsDAO"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Content</title>
        <style>
            /* Full-width input fields */
            .container input[type=text]{
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            /* Set a style for all buttons */
            .container button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }

            .container button:hover {
                opacity: 0.8;
            }

            /* Center the image and position the close button */
            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
                position: relative;
            }

            img.avatar {
                width: 100%;
            }

            .container {
                padding: 16px;
            }

            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
                padding-top: 60px;
            }

            /* Modal Content/Box */
            .modal-content {
                background-color: #fefefe;
                margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 60%; /* Could be more or less, depending on screen size */
            }
            .modal h2{
                text-align: center;
            }
            /* The Close Button (x) */
            .close {
                position: absolute;
                right: 25px;
                top: 0;
                color: #000;
                font-size: 35px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: red;
                cursor: pointer;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.1.1/socket.io.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
        <script>
            var socket = io.connect('http://localhost:9092');
//		socket.on('connect', function() {
//                    socket.emit('test', {
//                        name: 'toan',
//                        age: 20
//                    })
//		});

            socket.on('hello', function (data) {
                console.log(data);
                $(document).ready(function () {
                    $('.modal').fadeIn();
                    $('#numberPlate').val(data);
                    $('#imagePath').val("images/in/" + data + ".jpg");
                    $('img').attr("src", "images/in/" + data + ".jpg");
                })
            })
        </script>
    </head>
    <body>
        <%
            CarsDAO carsDao = new CarsDAO();
            ArrayList<Cars> list = (ArrayList<Cars>) request.getAttribute("list");
        %>
        <div class="center_content">  

            <div id="right_wrap">
                <div id="right_content">             
                    <h2>
                        <form action="CarsServlet" method="POST">
                            Chon ngay:
                            <input type="date" name="date">
                            <input type="hidden" value="findByDate" name="command">
                            <input type="submit" value="Tim kiem">
                        </form>
                    </h2> 


                    <table id="rounded-corner">
                        <thead>
                            <tr>
                                <th>Bien so xe</th>
                                <th>Thoi gian vao</th>
                                <th>Kieu xe</th>
                            </tr>
                        </thead>

                        <tbody>
                            <% if (request.getAttribute("list") == null) {
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date();
                            %>

                            <%for (Cars c : carsDao.getListInCarByDate(dateFormat.format(date))) {%>
                            <tr class="odd">
                                <td><a href="CarsServlet?command=getDetail&carIndex=<%=c.getCarIndex()%>"><%=c.getCarNumberPlate()%></a></td>
                                <td><%=c.getCarInTime()%></td>
                                <% if (c.getCarType() == 1) {%>
                                <td>Xe gui theo thang</td>
                                <%} else {%>
                                <td>Xe gui theo luot</td>
                                <%}%>
                            </tr>
                            <%}%>
                            <%} else {%>
                            <%if (list.isEmpty()) { %>
                            <tr class="odd">
                                <td>Danh sach trong</td>
                            </tr>
                            <%} else {%>
                            <%for (Cars c : list) {%>
                            <tr class="odd">
                                <td><a href="CarsServlet?command=getDetail&carIndex=<%=c.getCarIndex()%>"><%=c.getCarNumberPlate()%></a></td>
                                <td><%=c.getCarInTime()%></td>
                                <% if (c.getCarType() == 1) {%>
                                <td>Xe gui theo thang</td>
                                <%} else {%>
                                <td>Xe gui theo luot</td>
                                <%}%>
                            </tr>
                            <%}%>
                            <%}%>
                            <%}%>
                        </tbody>
                    </table>

                </div>
            </div><!-- end of right content-->


            <div class="sidebar" id="sidebar">
                <h2>Thông tin bãi đỗ xe</h2>

                <ul>
                    <li><a href="#">Template settings</a></li>
                    <li><a href="#">Add page</a></li>
                    <li><a href="#">Edit section</a></li>
                    <li><a href="#">Templates</a></li>
                    <li><a href="#">Clients</a></li>
                </ul>
            </div>             


            <div class="clear"></div>
        </div> <!--end of center_content-->
        
        
<!--        Modal In car-->
        <div id="id01" class="modal">
            
            <form class="modal-content " action="CarsServlet" method="GET">
                <h2>Thong tin xe vao</h2>
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    <img src="images/in/1.jpg" alt="Avatar" class="avatar">
                </div>

                <div class="container">
                    <label for="uname"><b>Bien so xe</b></label>
                    <input id ="numberPlate" type="text" name="inCarNumberPlate" required>
                    <input id ="imagePath" type="hidden" name="imagePath">
                    <input type="hidden" name="command" value="saveIn">
                    <button type="submit">Xac nhan</button>
                </div>
            </form>
        </div>

    </body>
</html>

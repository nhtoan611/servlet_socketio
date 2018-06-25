<%-- 
    Document   : outcar
    Created on : May 20, 2018, 5:16:47 PM
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
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Parking System</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
        <!-- jQuery file -->
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.tabify.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript">
            var $ = jQuery.noConflict();
            $(function () {
                $('#tabsmenu').tabify();
                $(".toggle_container").hide();
                $(".trigger").click(function () {
                    $(this).toggleClass("active").next().slideToggle("slow");
                    return false;
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $(".menu a").click(function () {
                    $(".menu a").removeClass("selected");
                    $(this).addClass("selected");
                });
            });
        </script>
        <style>
            
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
                    $('#imagePath').val("images/out/" + data + ".jpg");
                    $('img').attr("src", "images/out/" + data + ".jpg");
                })
            })
        </script>
    </head>
    <body>
        <div id="panelwrap">
            <jsp:include page="header.jsp"></jsp:include>
            <%
                CarsDAO carsDao = new CarsDAO();
                ArrayList<Cars> listOut = (ArrayList<Cars>) request.getAttribute("listOut");
            %>
            <div class="center_content">  

                <div id="right_wrap">
                    <div id="right_content">             
                        <h2>
                            <form action="CarsServlet" method="POST">
                                Chon ngay:
                                <input type="date" name="date">
                                <input type="hidden" value="findOutByDate" name="command">
                                <input type="submit" value="Tim kiem">
                            </form>
                        </h2> 


                        <table id="rounded-corner">
                            <thead>
                                <tr>
                                    <th>Bien so xe</th>
                                    <th>Thoi gian ra</th>
                                    <th>Kieu xe</th>
                                </tr>
                            </thead>

                            <tbody>
                                <% if (request.getAttribute("listOut") == null) {
                                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = new Date();
                                %>

                                <%for (Cars c : carsDao.getListOutCarByDate(dateFormat.format(date))) {%>
                                <tr class="odd">
                                    <td><a href="CarsServlet?command=getoDetail&carIndex=<%=c.getCarIndex()%>"><%=c.getCarNumberPlate()%></a></td>
                                    <td><%=c.getCarOutTime()%></td>
                                    <% if (c.getCarType() == 1) {%>
                                    <td>Xe gui theo thang</td>
                                    <%} else {%>
                                    <td>Xe gui theo luot</td>
                                    <%}%>
                                </tr>
                                <%}%>
                                <%} else {%>
                                <%if (listOut.isEmpty()) { %>
                                <tr class="odd">
                                    <td>Danh sach trong</td>
                                </tr>
                                <%} else {%>
                                <%for (Cars c : listOut) {%>
                                <tr class="odd">
                                    <td><a href="CarsServlet?command=getoDetail&carIndex=<%=c.getCarIndex()%>"><%=c.getCarNumberPlate()%></a></td>
                                    <td><%=c.getCarOutTime()%></td>
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


            <!--        Modal Out car-->
            <div id="id01" class="modal">

                <form class="modal-content " action="CarsServlet" method="GET">
                    <h2>Thong tin xe ra</h2>
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                        <img src="images/in/1.jpg" alt="Avatar" class="avatar">
                    </div>

                    <div class="container">
                        <label for="uname"><b>Bien so xe</b></label>
                        <input id ="numberPlate" type="text" name="outCarNumberPlate" required>
                        <input id ="imagePath" type="hidden" name="oimagePath">
                        <input type="hidden" name="command" value="caculate">
                        <button type="submit">Xac nhan</button>
                    </div>
                </form>
            </div>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>

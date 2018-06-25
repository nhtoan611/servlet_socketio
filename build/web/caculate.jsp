<%-- 
    Document   : caculate
    Created on : May 21, 2018, 12:27:13 PM
    Author     : nhtoan
--%>

<%@page import="dao.MonthlyCarsDAO"%>
<%@page import="dao.FollowDAO"%>
<%@page import="dao.CostsDAO"%>
<%@page import="model.Cars"%>
<%@page import="dao.CarsDAO"%>
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

    </head>
    <body>
        <%
            CarsDAO carsDao = new CarsDAO();
            FollowDAO followDao = new FollowDAO();
            MonthlyCarsDAO mcd = new MonthlyCarsDAO();
            CostsDAO costsDao = new CostsDAO();
            String outCarNumberPlate = (String) request.getAttribute("outCarNumberPlate");
            Cars car = carsDao.getCars(outCarNumberPlate);
        %>
        <div id="panelwrap">
            <jsp:include page="header.jsp"></jsp:include>
                <div class="center_content">  

                    <div id="right_wrap">
                        <div id="right_content">             
                            <h2>Tables section</h2> 


                            <table id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th>Bien so xe</th>
                                        <th>Thoi gian vao</th>
                                        <th>Thoi gian ra</th>
                                        <th>Chi phi phai tra</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                <%if (followDao.getFollowCar(outCarNumberPlate) == 1) {%>
                                <tr>
                                    <td colspan="12">Xe trong danh sach theo doi</td>
                                </tr>
                                <%}%>
                            </tfoot>
                            <tbody>
                                <tr class="odd">
                                    <td><%= car.getCarNumberPlate()%></td>
                                    <td><%= car.getCarInTime()%></td>
                                    <td><%= car.getCarOutTime()%></td>
                                    <%
                                        float a;
                                        if (mcd.getOwnerIdByCar(outCarNumberPlate) == 0) {
                                            a = carsDao.getPrice(car, costsDao.getListCosts());
                                    %>
                                    <td><%= carsDao.getPrice(car, costsDao.getListCosts())%></td>
                                    <%   } else {
                                        a = 0;
                                    %>
                                    <td> Xe gui theo thang</td>
                                    <%}%>

                                </tr>
                            </tbody>
                        </table>

                        <div class="form_sub_buttons">
                            <a href="CarsServlet?command=saveOut&price=<%= a%>" class="button green">Xac nhan</a>
                        </div>




                    </div>
                </div><!-- end of right content-->


                <div class="sidebar" id="sidebar">
                    <h2>Browse categories</h2>

                    <ul>
                        <li><a href="#" class="selected">Main page</a></li>
                        <li><a href="#">Template settings</a></li>
                        <li><a href="#">Add page</a></li>
                        <li><a href="#">Edit section</a></li>
                        <li><a href="#">Templates</a></li>
                        <li><a href="#">Clients</a></li>
                    </ul>    
                </div>             


                <div class="clear"></div>
            </div> 
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>

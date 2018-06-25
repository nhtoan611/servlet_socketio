<%-- 
    Document   : monthlycars
    Created on : May 22, 2018, 9:52:02 AM
    Author     : nhtoan
--%>

<%@page import="model.Owners"%>
<%@page import="dao.OwnersDAO"%>
<%@page import="model.MonthlyCars"%>
<%@page import="dao.MonthlyCarsDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            MonthlyCarsDAO mcd = new MonthlyCarsDAO();
            OwnersDAO ownersDao = new OwnersDAO();
        %>
        <div id="panelwrap">
            <jsp:include page="header.jsp"></jsp:include>
                <div class="center_content">  

                    <div id="right_wrap">
                        <div id="right_content">             
                            <h2>Xe gui theo thang</h2> 


                            <table id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th>Bien so xe</th>
                                        <th>Ten chu xe</th>
                                        <th>So dien thoai</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>

                                <tbody>

                                <% for (MonthlyCars mc : mcd.getMonthlyCarsList()) {%>
                                <tr class="odd">
                                    <td><%= mc.getCarNumberPlate()%></td>
                                    <% Owners owner = ownersDao.getOwners(mc.getOwnerId());%>
                                    <td><%= owner.getOwnerName()%></td>
                                    <td><%= owner.getOwnerPhoneNumber()%></td>
                                    <td><a href="MonthlyCarsServlet?command=delete&downerId=<%=mc.getOwnerId()%>&dmcIndex=<%= mc.getMonthlyCarIndex()%>"><img src="images/trash.gif" alt="" title="" border="0" /></a></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>

                        <h2>Them moi xe gui theo thang</h2> 
                        <form class="form" action="MonthlyCarsServlet" method="GET">

                            <div class="form_row">
                                <label>Ten chu xe</label>
                                <input type="text" class="form_input" name="iownerName" />
                            </div>

                            <div class="form_row">
                                <label>Bien so xe </label>
                                <input type="text" class="form_input" name="inumberPlate" />
                            </div>
                            
                            <div class="form_row">
                                <label>So dien thoai </label>
                                <input type="text" class="form_input" name="iownerPhoneNumber" />
                            </div>

                            <div class="form_row">
                                <input type="hidden" name="command" value ="insert"/>
                                <input type="submit" class="form_submit" value="Them moi"/>
                            </div> 
                            <div class="clear"></div>
                        </form>
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



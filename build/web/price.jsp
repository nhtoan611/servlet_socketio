<%-- 
    Document   : price
    Created on : May 21, 2018, 12:32:01 AM
    Author     : nhtoan
--%>

<%@page import="model.Costs"%>
<%@page import="dao.CostsDAO"%>
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
            CostsDAO cd = new CostsDAO();
        %>
        <div id="panelwrap">
            <jsp:include page="header.jsp"></jsp:include>
                <div class="center_content">  

                    <div id="right_wrap">
                        <div id="right_content">             
                            <h2>Quan li bieu phi</h2> 


                            <table id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th>Thoi gian</th>
                                        <th>Bieu phi</th>
                                        <th>Edit</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    
                                    <% for (Costs c : cd.getListCosts()) {%> 
                                    <tr class="odd">
                                    <td><%=c.getTime() %></td>
                                    <td><%=c.getUnitPrice() %></td>
                                    <td><a href="CostsServlet?command=detail&costId=<%=c.getCostId()%>"><img src="images/edit.png" alt="" title="" border="0" /></a></td>
                                    <td><a href="CostsServlet?command=delete&costId=<%=c.getCostId()%>"><img src="images/trash.gif" alt="" title="" border="0" /></a></td>
                                    </tr>
                                    <%}%>  
                            </tbody>
                        </table>

                        <h2>Them moi bieu phi</h2> 
                        <form class="form" action="CostsServlet" method="GET">

                            <div class="form_row">
                                <label>Thoi gian: </label>
                                <input type="Number" class="form_input" name="itime" />
                            </div>

                            <div class="form_row">
                                <label>Gia tien: </label>
                                <input type="Number" step="1000" class="form_input" name="iunitPrice" />
                            </div>

                            <div class="form_row">
                                <input type="hidden" name="command" value ="insert"/>
                                <input type="submit" class="form_submit" value="Submit"/>
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


<%-- 
    Document   : detailfollow
    Created on : May 21, 2018, 11:19:17 AM
    Author     : nhtoan
--%>

<%@page import="dao.FollowDAO"%>
<%@page import="model.Follow"%>
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
            Follow follow = (Follow) request.getAttribute("follow");
        %>
        <div id="panelwrap">
            <jsp:include page="header.jsp"></jsp:include>
                <div class="center_content">  

                    <div id="right_wrap">
                        <div id="right_content">             
                            <h2>Tables section</h2> 

                            <form class="form" action="FollowServlet" method="GET">

                                <div class="form_row">
                                    <label>Bien so xe: </label>
                                    <input type="text" class="form_input" name="unumberPlate" value="<%=follow.getCarNumberPlate()%>"/>
                            </div>                               
                            <div class="form_row">
                                <input type="hidden" name="command" value ="update"/>
                                <input type="hidden" name="ufollowId" value ="<%=follow.getFollowId()%>"/>
                                <input type="submit" class="form_submit" value="Cap nhat"/>
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

<%-- 
    Document   : result
    Created on : May 21, 2018, 10:36:53 AM
    Author     : nhtoan
--%>

<%@page import="java.util.ArrayList"%>
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
            ArrayList<String> listResult = null;
            if(request.getAttribute("listResult")!=null){
            listResult = (ArrayList<String>)request.getAttribute("listResult");
        }%>
        <div id="panelwrap">
            <jsp:include page="header.jsp"></jsp:include>
                <div class="center_content">  

                    <div id="right_wrap">
                        <div id="right_content">             
                            <h2>Danh sach tim kiem</h2> 


                            <table id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th>Bien so xe</th>
                                        <th>Them vao danh sach theo doi</th>
                                    </tr>
                                </thead>

                                <tbody>
                                <% for(String result: listResult){%>
                                <tr class="odd">
                                    <td><%= result %></td>
                                    <td><a href="FollowServlet?command=insert&inumberPlate=<%=result%>"><img src="images/add.png" alt="" title="" border="0" /></a></td>                                  
                                </tr>
                                <%}%>
                            </tbody>
                        </table>

                    </div>
                </div><!-- end of right content-->


                <div class="sidebar" id="sidebar">
                    <h2>Tim kiem thong tin xe</h2>
                    <form action="FollowServlet" method="GET">
                    <ul>
                        <li><input type ="text" name="snumberPlate" placeholder="Nhap vao thong tin bien so xe"/></li>    
                        <input type="hidden" name="command" value="search"/>
                        <li> <input type="submit" value="Tim kiem"/></li>
                    </ul>   
                    </form>

                </div>             


                <div class="clear"></div>
            </div>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>

    </body>
</html>

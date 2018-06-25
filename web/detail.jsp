<%-- 
    Document   : detail
    Created on : May 20, 2018, 12:41:17 AM
    Author     : nhtoan
--%>

<%@page import="dao.OwnersDAO"%>
<%@page import="model.Owners"%>
<%@page import="model.Cars"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Car Details</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
        <!-- jQuery file -->
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.tabify.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript">
        var $ = jQuery.noConflict();
        $(function() {
        $('#tabsmenu').tabify();
        $(".toggle_container").hide(); 
        $(".trigger").click(function(){
                $(this).toggleClass("active").next().slideToggle("slow");
                return false;
        });
        });
        </script>
    </head>
    <body>
        
        <% 
            Cars cars = null;
            if((Cars)request.getAttribute("cars")!=null){
                cars = (Cars)request.getAttribute("cars");
            }
            OwnersDAO ownersDao = new OwnersDAO();
            
        %>
        <div id="panelwrap">
           <jsp:include page="header.jsp"></jsp:include>
           <div class="center_content">  

            <div id="right_wrap">
                <div id="right_content">             
                    <h2>Thong tin xe <%= cars.getCarNumberPlate()%></h2> 

                    <table id="rounded-corner">
                        <thead>
                            <tr>
                                <th>Thoi gian vao</th>
                                <th>Kieu xe</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="odd">
                                <td><%=cars.getCarInTime() %></td>
                                <% if (cars.getCarType() == 1) {%>
                                <td>Xe gui theo thang</td>
                                <%} else {%>
                                <td>Xe gui theo luot</td>
                                <%}%>
                                
                            </tr>
                        </tbody>
                    </table>

                    <ul id="tabsmenu" class="tabsmenu">
                        <li class="active"><a>Hình ảnh phương tiện</a></li>
                    </ul>

                    <img src="<%= cars.getCarInImage()%>" width="700px" alt="" border="0" />

                </div>
            </div><!-- end of right content-->


            <div class="sidebar" id="sidebar">
                <h2>Thông tin chủ xe</h2>

                <ul>
                    <% if(cars.getCarType()==1){
                        Owners owners = ownersDao.getOwners(cars.getOwnerId());
                    %>
                    <li><p><%= owners.getOwnerName()%></p></li>
                    <li><p><%= owners.getOwnerPhoneNumber()%></p></li>
                    <%
                    }else{%>
                    <li><p>Khong co du lieu xe</p></li>
                    <%}%>        
                </ul>
            </div>             


            <div class="clear"></div>
        </div> <!--end of center_content-->
           <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>

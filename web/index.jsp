<%-- 
    Document   : index
    Created on : May 16, 2018, 5:08:40 PM
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
        <div id="panelwrap">
           <jsp:include page="header.jsp"></jsp:include>
           <jsp:include page="content.jsp"></jsp:include>
           <jsp:include page="footer.jsp"></jsp:include>
        </div>
        
    </body>
</html>

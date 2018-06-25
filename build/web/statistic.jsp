<%-- 
    Document   : statistic
    Created on : May 22, 2018, 11:29:16 AM
    Author     : nhtoan
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="model.Statistic"%>
<%@page import="dao.StatisticDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {packages: ['corechart']});
            google.charts.setOnLoadCallback(drawStuff);

            function drawStuff() {
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Country');
                data.addColumn('number', 'Doanh thu');
                data.addRows([
                    <% StatisticDAO sd = new StatisticDAO();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    for (Statistic st : sd.getStatistic()) {%>
                        ['<%= dateFormat.format(st.getStatisticDate())%>',<%=st.getRevenue()%>],
                    <%}%>
                ]);

                var options = {
                    title: 'Doanh thu bai do xe',
                    width: 700,
                    height: 500,
                    legend: 'none',
                    bar: {groupWidth: '35%'},
                    vAxis: {gridlines: {count: 4}}
                };

                var chart = new google.visualization.ColumnChart(document.getElementById('chart'));
                chart.draw(data, options);

                document.getElementById('format-select').onchange = function () {
                    options['vAxis']['format'] = this.value;
                    chart.draw(data, options);
                };
            }
            ;
        </script>
    </head>
    <body>

        <div id="panelwrap">
            <jsp:include page="header.jsp"></jsp:include>
                <div class="center_content">  

                    <div id="right_wrap">
                        <div id="right_content">             
                            <h2>Bao cao thong ke</h2>
                            <div id="chart"></div>
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


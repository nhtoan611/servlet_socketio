<%-- 
    Document   : test
    Created on : May 17, 2018, 4:21:09 PM
    Author     : nhtoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.1.1/socket.io.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
        
        <script>
            	var socket =  io.connect('http://localhost:9092');
//		socket.on('connect', function() {
//                    socket.emit('test', {
//                        name: 'toan',
//                        age: 20
//                    })
//		});
                
                socket.on('hello', function(data) {
                    console.log(data);
                    $(document).ready(function() {
                        $('#message').append(data);
                    })
                })
        </script>

    </head>
    <body>
        <h1>Hello World!</h1>
        <p id="message"></p>
    </body>
</html>

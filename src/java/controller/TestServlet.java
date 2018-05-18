/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.VoidAckCallback;
import com.corundumstudio.socketio.listener.DataListener;

import socket.WebSocket;



/**
 *
 * @author nhtoan
 */
public class TestServlet extends HttpServlet {
//sao luc chay terminal co loi gi the
    private SocketIOServer server;
    public TestServlet(){
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WebSocket socket = WebSocket.getInstance();
        String  hello = request.getParameter("hello");
        System.out.println(hello);
        socket.sendEvent(hello);
        
//        request.setAttribute("hello", hello);
//        request.setAttribute("goodbye", "Goodbye");
//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/test.jsp");
//        rd.forward(request, response);
        
 
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }



}
//phai gui request tu c++ da
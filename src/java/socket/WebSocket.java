/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

/**
 *
 * @author nhtoan
 */
import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.VoidAckCallback;
import com.corundumstudio.socketio.listener.DataListener;
public class WebSocket {
    private static final WebSocket sharedInstance = new WebSocket();
    
    private SocketIOServer server;
    
    public static WebSocket getInstance() {
        return sharedInstance;
    }
    
    private WebSocket(){
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);
        this.server = new SocketIOServer(config);
//        this.server.addEventListener("test", Object.class, new DataListener<Object>() {
//            @Override
//            public void onData(final SocketIOClient client, Object data, final AckRequest ackRequest) {
//                System.out.println(data);
//            }
//        });
        this.server.start();
    }
    
    public void sendEvent(String data){
        server.getBroadcastOperations().sendEvent("hello", data);
    }
    
    public static void main(String[] args){
        System.out.println("done");
    }
    
    
}

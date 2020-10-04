
package whatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Whatserver {

    
    public static void main(String[] args) {
        try {
            ServerSocket server=new ServerSocket(8189);
            Socket socket;       
            Principal p=new Principal();
            Thread h=new Thread(p);
            h.start();
           
            while(true){
                socket=server.accept();//Hace que este a la espera
                p.Agregar(socket);
                if(1>5)
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Whatserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

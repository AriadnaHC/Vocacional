
 
package whatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tablita implements Runnable{
    Socket soc;
    Scanner scan;
    boolean mensaje;
    String love,user;        
    PrintWriter writer;
    public boolean isMensaje() {
        return mensaje;
    }

    public String getUser() {
        return user;
    }

    public String getLove() {
        return love;
    }
    public void enviar(String s){
        writer.println(s);
    }
    
    public Tablita(Socket socket) {
        mensaje=false;
        love=user="";       
        soc=socket;
        try {
            InputStream in = soc.getInputStream();
            OutputStream out=soc.getOutputStream();
            scan=new Scanner(in);//grapping?            
            writer=new PrintWriter(out,true);           
        } catch (IOException ex) {
            Logger.getLogger(Tablita.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public void run() {
        while(scan.hasNextLine()){            
            love=scan.nextLine();
            String[] items=love.split(",");
            if(items.length==1){//Si es uno, solo seteó el usuario
                user=love;
            }else{//Ya esta usando la app para mandar mensaje
                mensaje=true;
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tablita.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setMensaje(boolean mensaje) {
        this.mensaje = mensaje;
    }
    
}


package whatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.in;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal implements Runnable{
    Thread[] t=new Thread[4];;
    Tablita[] ta=new Tablita[4];
    int i;    
    String todo;
    public void Principal(){
        i=0;     
        todo="";
    }
    
    public void Agregar(Socket soc){  
        ta[i]=new Tablita(soc);
        t[i]=new Thread(ta[i]);
        t[i].start();
        i++;                
    }

    @Override
    public void run() {
        while(true){
            if(i>=1){//Cuando ya haya al menos un usuario
                for(int z=0;z<i;z++){
                    if(ta[z].isMensaje()){
                        todo=ta[z].getLove(); //mensaje                       
                        String[] items=todo.split(",");
                        for(int y=0;y<i;y++){
                            if(ta[y].getUser().equals(items[0])){//Busca el objeto ta donde esta el usuario destinatario
                                ta[y].enviar(ta[z].getUser()+","+items[1]+","+ta[y].getUser());                    
                                //ta y es usado para enviar mensaje de ta z
                                ta[z].setMensaje(false);//Pone el mensaje en false para no enviar varias veces el mismo mensaje
                            }
                            
                        }
                        
                    }
                }
                
            }
               System.out.println(i);
        }
    }
}

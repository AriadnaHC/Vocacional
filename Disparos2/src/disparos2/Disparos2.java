
package disparos2;

import javax.swing.JFrame;

public class Disparos2 {
    public static void main(String[] args) {
        JFrame frmGrafico=new JFrame();//Crea pantalla
        frmGrafico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Cuando press x se deja de ejecutar
        Canvas c=new Canvas();//Crea componente canvas
        frmGrafico.add(c);//Agrega a la ventana la clase canvas
        frmGrafico.pack();//lo hace de tamaño shido
        frmGrafico.setVisible(true);//Que se vea// TODO code application logic here
    }
    
}

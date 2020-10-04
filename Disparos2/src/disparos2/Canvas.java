package disparos2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.Timer;

class Canvas extends JPanel implements KeyListener{
    private Timer timer;
    private AffineTransform tr;
    private AffineTransform trx;
    private AffineTransform[] trey=new AffineTransform[5];
    private Shape rec;
    private boolean aba,arr,recarga,gano;
    private boolean[] espacio=new boolean[6];
    private boolean[] toco=new boolean[5];
    private Shape[] eli=new Shape[5];
    private Shape[] obj=new Shape[5];
    int j,e,num;
    double bb,ba;
    
    public Canvas(){
        gano=false;
        j=0;
        e=0;
        num=0;
        tr=new AffineTransform();
        trx=new AffineTransform();
        for (int i=0;i<5;i++){
            trey[i]=new AffineTransform();
        }
        
        for(int i=0;i<5;i++){
            bb=-Math.random()*485;
            ba=Math.random()*200;
            obj[i]=new Rectangle2D.Double(ba,bb,30,30);
            obj[i]=trey[i].createTransformedShape(obj[i]);
        }
        rec=new Rectangle2D.Double(400,0,90,40);
        rec=trx.createTransformedShape(rec);
        
        
        
        aba=false;
        arr=false;
        recarga=true;
        for(int i=0;i<5;i++)
            toco[i]=true;
        for(int i=0;i<6;i++)
            espacio[i]=false;        
        for(int i=0;i<5;i++)
            eli[i]=new Ellipse2D.Double(400, 0, 40,40 );        

        timer = new Timer(15,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    for(int i=0;i<5;i++){
                       if(obj[i].getBounds2D().getY()>470){
                            trey[i].setToTranslation(0, -.5);
                        }else if(obj[i].getBounds2D().getY()<=0){
                            trey[i].setToTranslation(0, .5);
                        }
                    }
                    
                    if(aba){                        
                        if(rec.getBounds2D().getY()<460)
                            tr.setToTranslation(0, 5);
                        else
                            tr.setToTranslation(0, 0);
                        
                    }
                    if(arr){
                        if(rec.getBounds2D().getY()>0)
                            tr.setToTranslation(0, -5);
                        else
                            tr.setToTranslation(0, 0);
                    }
                    
                    
                        
                    
                    if(!gano){
                       repaint();
                    }
                }
                
            });
        timer.start();
        this.setPreferredSize(new Dimension(500,500));
        this.setBackground(Color.WHITE);
        this.addKeyListener(this);
        this.setFocusable(true);
    }
    
    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;   
        Ellipse2D e=new Ellipse2D.Double(0, 0, 40,40 );
        g2.setPaint(new Color(200,198,50));
        rec=tr.createTransformedShape(rec);
        g2.fill(rec);

            for(int i=0;i<5;i++){
                if(!espacio[i]){
                    eli[i]=new Ellipse2D.Double(400, rec.getBounds2D().getY(), 30,30 );        
                }else{
                    trx.setToTranslation(-5, 0);
                    eli[i]=trx.createTransformedShape(eli[i]);
                    g2.fill(eli[i]);
                }
                 if(eli[i].getBounds2D().getX()<=0){
                    eli[i]=new Ellipse2D.Double(400, rec.getBounds2D().getY(),30,30);        
                    espacio[i]=false;
                }
                for(int t=0;t<5;t++){
                    if (eli[i].intersects(obj[t].getBounds2D())){
                        toco[t]=false;
                    }
                }
                for(int t=0;t<5;t++){
                    if(toco[t]){
                        obj[t]=trey[t].createTransformedShape(obj[t]);
                        g2.fill(obj[t]);
                    }                
                }
                
                        

            }
            for(int i=0;i<5;i++){
                if(!toco[i])
                    num++;
            }
            if(num==5){
                gano=true;
                g2.drawString("GANASTE", 60, 40);
                arr=false;
                aba=false;
            }
            num=0;
        }
            
            
            
            
            
    
    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_SPACE){
            if (recarga){
                espacio[j]=true;
            }
            j++;
                if(j>=6){
                    recarga=false;
                    System.out.println("Recarga");
                }
            
        }
        if(ke.getKeyCode()==KeyEvent.VK_DOWN){
            aba=true;
            arr=false;
        }
        if(ke.getKeyCode()==KeyEvent.VK_UP){
            aba=false;
            arr=true;
        }
        if(ke.getKeyCode()==KeyEvent.VK_R){
            recarga=true;
            j=0;
        }
    }    

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}



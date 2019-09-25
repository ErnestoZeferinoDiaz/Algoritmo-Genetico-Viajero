package agv2.view;

import agv2.model.City;
import agv2.model.Cromosoma;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JPanel;

public class Lienzo extends JPanel{
    private LinkedList<City> ciudades;
    private Cromosoma indices;
    private boolean calc;
    public Lienzo(){
        this.setBackground(Color.BLACK);
        this.calc=false;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D)g;
        int w,h;
        int iX1,iY1;
        int iX2,iY2;
        
        w=(int) City.ref.getWidth();
        h=(int) City.ref.getHeight();
        
        //g2.setColor(Color.WHITE);
        //g2.drawLine(0, h/2, w, h/2);
        //g2.drawLine(w/2, 0, w/2, h);
        
        if(this.ciudades!=null){
            g.setColor(Color.WHITE);
            for(int x=0; x<this.ciudades.size(); x++){
                g2.fill(ciudades.get(x).getCity());
                g2.drawString(x+"", (int)(ciudades.get(x).getCity().getX()-10),(int)ciudades.get(x).getCity().getY()-10);
            }
            if(this.isCalc()){
                for(int x=0; x<this.ciudades.size(); x++){
                    iX1=indices.get(x).getVal();
                    iX2=indices.get((x+1)%(this.ciudades.size())).getVal();
                    if(x==0){
                        g2.setColor(Color.RED);
                    }else{
                        g2.setColor(Color.WHITE);
                    }
                    g2.drawLine((int)ciudades.get(iX1).getCity().getCenterX(),
                                (int)ciudades.get(iX1).getCity().getCenterY(),
                                (int)ciudades.get(iX2).getCity().getCenterX(),
                                (int)ciudades.get(iX2).getCity().getCenterY());
                }
            }
            
        }
        
    }

    public LinkedList<City> getCiudades() {
        return ciudades;
    }

    public void setCiudades(LinkedList<City> ciudades) {
        this.ciudades = ciudades;
    }

    public boolean isCalc() {
        return calc;
    }

    public void setCalc(boolean calc) {
        this.calc = calc;
    }

    public Cromosoma getIndices() {
        return indices;
    }

    public void setIndices(Cromosoma indices) {
        this.indices = indices;
    }
    
    
}

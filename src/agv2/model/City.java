package agv2.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class City extends Point{
    public static Rectangle ref;
    
    public City(){
        this.setLocation(Configuracion.srand(-300, 300),Configuracion.srand(-300, 300));
    }
    
    public City(double c,double y){
        this.setLocation(x,y);
    }
    
    public Ellipse2D getCity(){
        double dx,dy;
        double radio=5;
        
        dx=(City.ref.getWidth()/2)+this.getX();
        dy=(City.ref.getHeight()/2)-this.getY();
        
        dx=dx-radio/2;
        dy=dy-radio/2;
        return new Ellipse2D.Double(dx,dy,radio,radio);
    }
    
    
}

package agv2.model.Fitness;

import agv2.model.City;
import agv2.model.Cromosoma;
import agv2.model.Gen;
import java.awt.Point;
import java.util.LinkedList;

public class MyFunction extends FuncionEvaluacion{
    private LinkedList<City> points;
    
    public MyFunction(){
        this.points = new LinkedList();
    }
    
    public void addCity(double x,double y){
        this.points.add(new City(x,y));
    }
    
    public void rand(int n){
        this.points.clear();
        for(int x=0; x<n; x++){
            this.points.add(new City());
        }
    }
    
    protected double evaluate(Cromosoma cromo) {
        double r=0;
        int size=cromo.size();
        Gen ind1,ind2;
        for(int x=0; x<size; x++){
            ind1=cromo.get(x);
            if(x+1<size){
                ind2=cromo.get(x+1);
            }else{
                ind2=cromo.get(0);
            }
            r=r+this.points.get(ind1.getVal()).distance(this.points.get(ind2.getVal()));
        }
        return r;
    }

    public LinkedList<City> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<City> points) {
        this.points = points;
    }

    
    
    
    
}

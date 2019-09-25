package agv2.model.Operadores;

import agv2.model.Configuracion;
import agv2.model.Cromosoma;
import agv2.model.Gen;
import agv2.model.Poblacion;
import java.util.LinkedList;

public class Cruza implements Operator{
    private Configuracion conf;
    private Poblacion pob;
    private Poblacion pob_resul;
    
    public Cruza(){
        this.pob_resul = new Poblacion(this.conf);
    }
    
    @Override
    public void execute() {
        int s=pob.size();
        Cromosoma a,b;
        this.pob_resul.clear();
        for(int x=0; x<s; x=x+2){
            a=this.pob.get(x);
            b=this.pob.get(x+1);
            cruza(a,b);
        }
        this.pob.setPoblacion(pob_resul);
    }
    
    
    private void cruza(Cromosoma a,Cromosoma b){
        Gen tmp1,tmp2;
        Cromosoma r1,r2;
        int x,y,z,x1,x2,index;
        int siz=a.size();
        int puntoDeCorte=this.conf.randInt(1, siz);
        
        r1 = new Cromosoma(this.conf);
        r2 = new Cromosoma(this.conf);
        
        r1.rand();
        r2.rand();
        
        for(x=0; x<puntoDeCorte; x++){
            r1.set(x, a.get(x));
            r2.set(x, b.get(x));
        }
//        System.out.println("A: "+a);
//        System.out.println("B: "+b);
//        System.out.println("Punto corte: "+puntoDeCorte);
//        System.out.println("R1: "+r1);
//        //System.out.println("R2: "+r2);
        y=puntoDeCorte;
        x1=0;
        while(y<siz){
            tmp1 = b.get(x1);
            index=-1;
            for(int j=0; j<y; j++){
                if(r1.get(j).equals(tmp1)){
                    index=j;
                    break;
                }
            }
            if(index==-1){
                r1.set(y, tmp1);
                y++;
            }
            x1++;
        }
        y=puntoDeCorte;
        x1=0;
        while(y<siz){
            tmp1 = a.get(x1);
            index=-1;
            for(int j=0; j<y; j++){
                if(r2.get(j).equals(tmp1)){
                    index=j;
                    break;
                }
            }
            if(index==-1){
                r2.set(y, tmp1);
                y++;
            }
            x1++;
        }
        
        this.pob_resul.add(r1);
        this.pob_resul.add(r2);
        
    }

    @Override
    public void setPoblacion(Poblacion p) {
        this.pob=p;
    }

    @Override
    public Poblacion getPoblacionResultante() {
        return this.pob_resul;
    }

    @Override
    public void setConfiguracion(Configuracion conf) {
        this.conf=conf;
    }
    
}

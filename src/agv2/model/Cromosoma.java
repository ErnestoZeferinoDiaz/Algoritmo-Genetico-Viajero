package agv2.model;

import java.util.LinkedList;

public class Cromosoma implements Cloneable{
    public static int noGenes;
    private Configuracion conf;
    private LinkedList<Gen> genes;
    
    private double fitnessvalue;
    private boolean b_fitnessvalue;
    
    public Cromosoma(Configuracion conf){
        this.genes = new LinkedList();
        this.conf=conf;
        this.fitnessvalue=-1;
        this.b_fitnessvalue=false;
    }
    
    public int size(){
        return this.genes.size();
    }
    
    public void set(int i,Gen val){
        this.genes.set(i, val);
        this.b_fitnessvalue=false;
    }
    
    public Gen get(int i){
        return this.genes.get(i);
    }
    
    public void remove(int i){
        this.genes.remove(i);
    }
    
    public void remove(Object obj){
        this.genes.remove(obj);
    }
    
    public void rand(){
        Gen tmp;
        int index;
        this.genes.clear();
        for(int i=0; i<Cromosoma.noGenes; i++) {
            do{
                tmp=new Gen(this.conf);
                index=this.genes.indexOf(tmp);
            }while(index!=-1);
            this.genes.add(tmp);
        }
        this.b_fitnessvalue=false;
    }
    
    
    public double getFitnessValue(){
        if(this.b_fitnessvalue){
            return this.fitnessvalue;
        }else{
            this.fitnessvalue = this.conf.getFitness().getFitnessValue(this);
            this.b_fitnessvalue = true;
        }
        return this.fitnessvalue;
    }
    

    @Override
    public String toString() {
        String cad="";
        for (int i = 0; i < Cromosoma.noGenes; i++) {
            cad+=this.genes.get(i).toString()+",";
        }
        //cad+=": "+this.getFitnessValue();
        return cad;
    }

    @Override
    public boolean equals(Object obj) {
        Cromosoma o = (Cromosoma)obj;
        boolean resp=true;
        int x,y,z;
        int s=this.size();
        for(x=0; x<s; x++){
            if(o.get(x).equals(this.get(0))){
                break;
            }
        }
        for(y=1; y<s; y++){
            z=(x+y)%s;
            if(!o.get(z).equals(this.get(y))){
                resp=false;
                break;
            }
        }
        return resp;
    }

    public LinkedList<Gen> getGenes() {
        return genes;
    }

    public void setGenes(LinkedList<Gen> genes) {
        this.genes = genes;
    }
    
    
}

package agv2.model;

public class Gen {
    private int val;
    private Configuracion conf;
    
    public Gen(Configuracion conf){
        this.conf=conf;
        this.rand();
    }
    
    public void setVal(int val){
        this.val=val;
    }
    
    public int getVal(){
        return this.val;
    }
    
    public void rand(){
        int t=this.conf.getNoGenes();
        this.val=Configuracion.srandInt(0,t);
    }

    @Override
    public boolean equals(Object obj) {
        Gen o = (Gen)obj;
        return this.getVal()==o.getVal(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return ""+this.getVal();
    }

    public Configuracion getConf() {
        return conf;
    }

    public void setConf(Configuracion conf) {
        this.conf = conf;
    }
    
    
    
    
    
}

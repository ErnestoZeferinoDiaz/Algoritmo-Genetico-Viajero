package agv2.model.Operadores;

import agv2.model.Configuracion;
import agv2.model.Gen;
import agv2.model.Poblacion;

public class Mutacion implements Operator{
    private Configuracion conf;
    private Poblacion pob;
    
    @Override
    public void execute() {
        int s=this.pob.size();
        int n=(int) (this.conf.getPorcentajeMutacion()*s);
        int index;
        int genIndex1,genIndex2;
        Gen tmp;
        for(int x=0; x<n; x++){
            index=this.conf.randInt(0, s);
            genIndex1=this.conf.randInt(0, this.conf.getNoGenes());
            do{
                genIndex2=this.conf.randInt(0, this.conf.getNoGenes());
            }while(genIndex2==genIndex1);
            tmp=this.pob.get(index).get(genIndex1);
            this.pob.get(index).set(genIndex1,this.pob.get(index).get(genIndex2));
            this.pob.get(index).set(genIndex2,tmp);
        }
    }

    @Override
    public void setPoblacion(Poblacion p) {
        this.pob=p;
    }

    @Override
    public Poblacion getPoblacionResultante() {
        return this.pob;
    }

    @Override
    public void setConfiguracion(Configuracion conf) {
        this.conf=conf;
    }
    
}

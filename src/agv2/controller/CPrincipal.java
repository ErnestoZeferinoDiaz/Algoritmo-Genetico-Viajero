package agv2.controller;

import agv2.model.City;
import agv2.model.Configuracion;
import agv2.model.Cromosoma;
import agv2.model.Fitness.MyComparador;
import agv2.model.Fitness.MyFunction;
import agv2.model.Genotipo;
import agv2.model.Operadores.Cruza;
import agv2.model.Operadores.Mutacion;
import agv2.model.Seleccion.Elitismo;
import agv2.view.VPlano;
import agv2.view.VPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CPrincipal implements ActionListener,MouseListener{
    private VPrincipal vp;
    private VPlano vpl;
    
    private Configuracion conf;
    private Genotipo g;
    private MyFunction f;
    private MyComparador com;
    private Elitismo e;
    
    public CPrincipal(){
        this.vp = new VPrincipal();
        this.vpl = new VPlano();
        
        this.vpl.setLocation(this.vp.getWidth(), 0);
        this.vpl.setVisible(true);
        this.vp.setVisible(true);
        
        this.conf = new Configuracion();
        this.e = new Elitismo();
        this.f = new MyFunction();
        this.com = new MyComparador();
        
        this.vpl.l.setCiudades(this.f.getPoints());
        this.conf.setFitness(this.f);
        this.conf.setfComparador(this.com);
        
        this.conf.addOperator(new Cruza());
        this.conf.addOperator(new Mutacion());
        this.conf.setSelect(this.e);
        
        this.g = new Genotipo(this.conf);
        
        this.vp.btn_calc.addActionListener(this);
        this.vp.btn_noRand.addActionListener(this);
        this.vp.btn_xyRand.addActionListener(this);
        this.vpl.l.addMouseListener(this);
    }
    
    public void getDataView(){
        this.conf.setNoGenes(this.f.getPoints().size());
        this.conf.setNoPoblacion(Integer.parseInt(this.vp.txt_noPoblacion.getText()));
        this.conf.setPorcentajeMutacion(Double.parseDouble(this.vp.txt_noPorcentaje.getText()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x,z;
        int iter;
        boolean isIguals=false;
        int noMuestra=Integer.parseInt(this.vp.txt_noMuestras.getText());
        double[] muestra= new double[noMuestra];
        if(e.getSource().equals(this.vp.btn_noRand)){
            this.f.rand(Integer.parseInt(this.vp.txt_noRand.getText()));
            this.vpl.l.setCalc(false);
            this.vpl.l.repaint();
        }else if(e.getSource().equals(this.vp.btn_xyRand)){
            this.f.addCity(Integer.parseInt(this.vp.txt_xRand.getText()),
                           Integer.parseInt(this.vp.txt_yRand.getText()));
            this.vpl.l.setCalc(false);
            this.vpl.l.repaint();
        }else if(e.getSource().equals(this.vp.btn_calc)){
            this.getDataView();
            iter=Integer.parseInt(this.vp.txt_noIteraciones.getText());
            this.g.init();
            x=0;
            isIguals=false;
            do{
                z=x;
                this.g.evolucion();
                System.out.println(x+" Mejor: "+this.g.getPoblacionActual().getMejor()+"\t fit: "+this.g.getPoblacionActual().getMejor().getFitnessValue());
                muestra[z%noMuestra]=this.g.getPoblacionActual().getMejor().getFitnessValue();
                if(z%noMuestra==0 && x!=0){
                    isIguals=true;
                    for(int i=0; i<noMuestra-1; i++){
                        for(int j=i+1; j<noMuestra; j++){
                            if(muestra[i]!=muestra[j]){
                                isIguals=false;
                                break;
                            }
                        }
                        if(!isIguals){
                            break;
                        }
                    }
                    
                }
                x++;
            }while(x<iter && !isIguals);
            this.vpl.l.setCalc(true);
            this.vpl.l.setIndices(g.getPoblacionActual().getMejor());
            this.vpl.l.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double dx,dy;
        dx=(-City.ref.getWidth()/2)+e.getX();
        dy=(City.ref.getHeight()/2)-e.getY();
        this.f.addCity(dx,dy);
        this.vpl.l.setCalc(false);
        this.vpl.l.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}

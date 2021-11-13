package main_package;
import java.util.ArrayList;

import chart.Chart;

public class simulateur {

    private double t;
    private double tfin;
    private ArrayList<composant> C;
    private ArrayList<Chart> graphes;
    double trmin= 0;
    private double sum;
    double tr = 0;


    public simulateur(ArrayList<composant> X, double tfin,ArrayList<Chart> graphes)
    {
        this.C = X;
        this.graphes=graphes;
        this.t = 0; //début de simulation 0 forcément
        this.tfin = tfin; //valeur de la sortie 
        System.out.println("Construction du Simulateur : ");
    }

    public void run() {
        t=0;
        tr=0;
        trmin=Double.POSITIVE_INFINITY;
        ArrayList<composant> CImms = new ArrayList<composant>();

        for(composant c : C) {
            c.init(); 
            if(c instanceof buffer) // afficher q et t au démarrage
            {
                System.out.println("t : " +t);
                System.out.println("q : "+((buffer) c).get_q());
                graphes.get(0).addDataToSeries(this.t, ((buffer) c).get_q());
            }
            
            if(c instanceof Adder)
            {
            	
            	sum=((Adder) c).getSum();
            	System.out.println("S="+  ((Adder) c).getSum());
            	graphes.get(0).addDataToSeries(t, ((Adder) c).getSum());
            }
            if(c instanceof IntegrateurED)
            {
            	System.out.println("x="+((IntegrateurED) c).getX());
            }
            	
            
            
        }
        while(t<tfin) {
            trmin=Double.POSITIVE_INFINITY;
            for(composant c : C) {
                if(c.get_tr()<trmin) {
                    trmin = c.get_tr();
                }
            }
            for(composant c : C) {
                if(c.get_tr()==trmin) {
                    CImms.add(c);
                    //System.out.println("Composant imminent ="+c);
                }
            }
            t = t + trmin;
            //System.out.println("t : " +t);

            for(composant c : C) {
                c.set_e(c.get_e()+trmin);
                c.set_tr(c.advance()-c.get_e());
            }

            for(composant c : CImms) {
                c.sortie();
            }

            for(composant c : C) {
                if(c.get_tr()==0) { 
                    c.interne();
                    c.set_e(0);
                    c.set_tr(c.advance());
                    c.set_tl(t);
                    c.set_tn(t+c.advance());
                    
                
                }
                //double ext=c.externe();
                else if(c.externe()==1)
                {
                    c.set_e(0);
                    c.set_tr(c.advance());
                    c.set_tl(t);
                    c.set_tn(t+c.advance());
                }

                if(c instanceof Adder)
                {
                	
                	//System.out.println("S="+  ((Adder) c).getSum());
                	sum=((Adder) c).getSum();
                	System.out.println("la valeur de la hash dans adder"+  ((Adder)c).adder.get("somme") );
                	
                	//graphes.get(0).addDataToSeries(this.t, c.getSum());
                	
                }
                
                if(t<Double.POSITIVE_INFINITY)
                {
                	graphes.get(0).addDataToSeries(t, sum);
                	
                }
                if(c instanceof IntegrateurED)
                {
                	
                	if(c.getName()=="iED1")
                	{
                		System.out.println("la valeur dans la hash de integre="+(((IntegrateurED)c).adder.get("somme")));
                    	graphes.get(1).addDataToSeries(t, ((IntegrateurED) c).getX());
                    	System.out.println("X="+ ((IntegrateurED) c).getX() );
                	}
                	
                	
                }
                if(c instanceof IntegrateurED)
                {
                	
                	if(c.getName()=="iED2")
                	{
                		System.out.println("la valeur dans la hash de integre="+(((IntegrateurED)c).adder.get("somme")));
                    	graphes.get(2).addDataToSeries(t, ((IntegrateurED) c).getX());
                    	System.out.println("X="+ ((IntegrateurED) c).getX() );
                	}
                	
                	
                }
                
                
                
                if(c instanceof buffer) // afficher q et t au démarrage
                {
                	
                    //System.out.println("q : "+((buffer) c).get_q());
                    graphes.get(0).addDataToSeries(this.t, ((buffer) c).get_q());
                }
            }
            CImms.clear();
        }
    }
}
package main_package;

import java.util.HashMap;

public abstract class composant{
	
	protected double e;// temps écoulé dans un état depuis le dernier changement d'état 
    protected double tl;//date du dernier changement d'état du composant
    protected double tn;//date du prochain changement d'état du composant
    protected double tr;
    
	protected enum etats {etat_courant, etat_suivant, a, b, c, s, 
		free, busy,step_init,init,integ,step_final,somme,integreTD,integreED};
	HashMap<String,Boolean> in_out_ = null;
		
	HashMap<String,Double> in_out = null;//changer le type de hashmap
	

	public composant(HashMap<String, Double> in_out2){//changer le type de hashmap
		this.in_out = in_out2;
	}
	public composant(HashMap<String, Boolean> in_out2,int a) {
		this.in_out_=in_out2;
	}

	
	public void hash_put(String name, boolean value) {//hashmap type changé
		in_out_.put(name, value);
	}
	 
	public double get_e()
    {
        return e;
    }

    public void set_e(double e )
    {
        this.e=e;
    }

    public double get_tl()
    {
        return tl;
    }

    public void set_tl(double t)
    {
        this.tl=t;
    }



    public double get_tn()
    {
        return tn;
    }

    public void set_tn(double tn)
    {
        this.tn=tn;
    }


    public double get_tr()
    {
        return this.tr;
    }

    public void set_tr(double tr)
    {
        this.tr=tr;
    }
    
    public abstract void init();
    public abstract double externe();
    public abstract String getName();
    public abstract double interne();
    public abstract double advance();
    public abstract void sortie();
}

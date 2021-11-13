package main_package;

import java.util.HashMap;

public class step extends composant {
	double xi;
	double xf;
	double ts;
	double so;
	HashMap<String, Double> in_out;
	String nom_Step;
	private composant.etats courant = composant.etats.etat_courant;
	private composant.etats step_init = composant.etats.step_init;
	private composant.etats step_final = composant.etats.step_final;
	
	
	public step(HashMap<String, Double> in_out,double xi,
			double xf, double ts,String nom_Step) {
		super(in_out);
		// TODO Auto-generated constructor stub
		this.xi=xi;
		this.xf=xf;
		this.ts=ts;
		this.in_out=in_out;
		this.nom_Step=nom_Step;
		in_out.put(nom_Step,this.xi);
	}

	
	//hash_put
	@Override
	public void init() {
		// TODO Auto-generated method stub
		courant=step_init;
		this.set_e(0);
		this.set_tr(advance());
		
	}

	@Override
	public double externe() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public double interne() {
		// TODO Auto-generated method stub
		if(courant==step_init)
		{
			courant=step_final;
			in_out.put(nom_Step,this.xf);
			return 1;
		}
		else return -1;
	}

	@Override
	public double advance() {
		if(courant==step_init) {return ts;}
		else return Double.POSITIVE_INFINITY;
	}

	@Override
	public void sortie() {
        // TODO Auto-generated method stub
        //if(courant == step_init) in_out.put(nom_Step,xi);
        if (courant == step_final) in_out.put(nom_Step,xf);
    }


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}

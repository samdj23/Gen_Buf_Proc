package main_package;

import java.util.HashMap;

public class IntegrateurTD extends composant{

	
	private composant.etats courant = composant.etats.etat_courant;
	private composant.etats integreTD = composant.etats.integreTD;
	double hstep;
	double ta;
	HashMap<String, Double> steps;
	double x;
	double old_somme;
	double cond_Init;
	
	public IntegrateurTD(HashMap<String, Double> in_out,double hstep,
			double cond_Init) {
		super(in_out);
		this.hstep=hstep;
		this.steps=in_out;
		this.cond_Init =cond_Init;
		ta=hstep;
		courant=integreTD;
		
	}

	@Override
	public void init() {
		this.set_e(0);
		this.set_tr(advance());
		this.x=this.cond_Init;
		old_somme=this.steps.get("somme");
		
	}

	@Override
	public double externe() {
		if(this.steps.get("somme")!=old_somme)
		{
			this.old_somme=this.steps.get("somme");
			x=x+steps.get("somme")*this.get_e();
			return 1;
		}
		else return -1;
	}

	@Override
	public double interne() {
		if(courant==integreTD)
		{
			x=x+steps.get("somme")*hstep;
			return 1;
		}
		
		else return -1;
	}

	@Override
	public double advance() {
		// TODO Auto-generated method stub
		return ta;
	}

	@Override
	public void sortie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
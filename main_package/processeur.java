package main_package;

import java.util.HashMap;

public class processeur extends composant{
	
	
	private double tp = 3;
	protected composant.etats courant = composant.etats.etat_courant;
	private composant.etats libre = composant.etats.free;
	private composant.etats occupe = composant.etats.busy;
	
	public processeur(HashMap<String,Boolean> in_out,int x) {
		super(in_out,x); 
		//this.in_out.put("req","input");
		//this.in_out.put("done","output");
		System.out.println("Processeur crée");
	}
	
	public void hash_put(String name, boolean value) {
		super.hash_put(name, value);
	}
	
	public void init() {
		courant = libre;
		this.set_e(0);
		this.set_tr(advance());
	}
	
	public double advance() {
		if(courant == libre) {
			return Double.POSITIVE_INFINITY;
			}
		else if(courant == occupe) {
			return tp;
			}
		else return 0.0;
	}
	
	public double externe() {
		if(courant == libre && in_out.get("req")==true) {
			courant = occupe;
			//this.in_out.put("done",true);
			return 1;
		}
		else return -1;
	}
	
	public double interne() {
		if(courant == occupe) {
			courant = libre;
			return 1;
		}
		else return -1;
		//System.out.println("courant = " + courant);
	}

	@Override
	public void sortie() {
		// TODO Auto-generated method stub
		if(courant == occupe) {
			hash_put("done",true);
		}
		else if(courant == libre) {
			hash_put("done",false);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
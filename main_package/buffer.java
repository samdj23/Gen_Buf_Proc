package main_package;


import java.util.HashMap;

public class buffer extends composant {
	private composant.etats courant = composant.etats.etat_courant;
	private composant.etats a = composant.etats.a;
	private composant.etats b = composant.etats.b;
	private composant.etats c = composant.etats.c;
	private double q=0;
	
	public buffer(HashMap<String,Boolean> in_out) {
		super(in_out);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		courant = a;
		this.set_e(0);
		this.set_tr(advance());
	}

	@Override
	public double externe() {
		// TODO Auto-generated method stub
		if(courant==a && in_out.get("job")==true) {
			q++;
			courant = b;
			return 1;
		}
		else if(courant==c) {
			if(in_out.get("job")==true) {
				courant = c;
				q++;
				return 1;
			} 
			else if(in_out.get("done")==true && q==0) {
				courant = a;
				return 1;
			}
			else if(in_out.get("done")==true && q>0) {
				courant = b;
				return 1;
			}
		return 1;
		}
		else return -1;
	}

	@Override
	public double interne() {
		// TODO Auto-generated method stub
		if(courant==b) {
			q--;
			courant = c;
			hash_put("req",false);
			return 1;
		}
		else return -1;
	}

	@Override
	public double advance() {
		// TODO Auto-generated method stub
		if(courant==a || courant==c) {
			return Double.POSITIVE_INFINITY;
		}
		else return 0;
	}

	@Override
	public void sortie() {
		// TODO Auto-generated method stub
		if(courant==b) {
			hash_put("req",true);
		}
		else hash_put("req",false);
	}
	
	public double get_q() {
		return q;
	}
	
	public void set_q(double a) {
		this.q=a;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
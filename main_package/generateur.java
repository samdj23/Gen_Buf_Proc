package main_package;


import java.util.HashMap;

public class generateur extends composant {
	private double tg=2;
	private composant.etats courant = composant.etats.etat_courant;
	private composant.etats s = composant.etats.s;
	
	public generateur(HashMap<String,Boolean> in_out){
		super(in_out);
		
		//this.in_out.put("job","output");
	}
	
	public void hash_put(String name, boolean value) {
		super.hash_put(name, value);
	}
	
	public void init() {
		courant = s;
		this.set_e(0);
		this.set_tr(advance());
	}
	
	public double interne() {
		courant = s;
		hash_put("job",false);
		return 1;
	}
	
	public double advance() {
		return tg;
	}

	@Override
	public double externe() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sortie() {
		// TODO Auto-generated method stub
		hash_put("job",true);
	}

}
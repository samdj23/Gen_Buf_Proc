package main_package;

import java.util.HashMap;

public class IntegrateurED extends composant {
	
	//private double hstep;
	private double deltaX,deltaT;
	
	private double x;
	private double CI;
	private double old_sum;
	private composant.etats courant = composant.etats.etat_courant;
	private composant.etats integ = composant.etats.integ;
	HashMap<String,Double> adder;
	HashMap<String,Double> integrale;
	String name;
	
	public IntegrateurED(HashMap<String, Double> adder,HashMap<String,Double> integrale,String name, double deltaX, double CI) {
		super(adder);
		this.adder = adder;
		this.deltaX =deltaX;
		this.integrale=integrale;
		this.name=name;
		
	}
	
	public void init() {
			courant = integ;
			this.set_e(0);
			this.set_tr(advance());
			deltaT=0.01;
			x = CI;
			old_sum  = adder.get("somme");
			integrale.put("somme",x);
			
	}
	
	public double interne() {
		courant = integ;
		if(old_sum==adder.get("somme")){
			
			x=x+Math.signum(adder.get("somme"))*deltaX;
			integrale.put("somme",x);
			//x = x + hstep*old_sum;
			return 1;
		}
		else return -1;
	}

	@Override
	public double externe() {
		// TODO Auto-generated method stub
		
		
		if(old_sum!=adder.get("somme")){
			deltaT=deltaX/Math.abs(adder.get("somme"));
			//x = x + hstep*old_sum;
			x=x+Math.signum(adder.get("somme"))*deltaX;
			old_sum=adder.get("somme");
			integrale.put("somme",x);
			return 1;
		}
		else return -1;
	}

	@Override
	public double advance() {
		return deltaT;
	}

	@Override
	public void sortie() {
		// TODO Auto-generated method stub
		
	}


	public double getX()
	{
		return x;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
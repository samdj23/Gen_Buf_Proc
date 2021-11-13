package main_package;

import java.util.HashMap;
import java.util.Map;

public class Adder extends composant {

	private composant.etats courant = composant.etats.etat_courant;
	private composant.etats add = composant.etats.somme;
	private composant.etats init = composant.etats.init;
	private double[] old_steps=new double[5];
	private double[] new_steps=new double[5];
	int nb_Steps=4;
	HashMap<String,Double> steps;
	HashMap<String,Double> adder; 
	
	public double sum;
	
	public Adder(HashMap<String, Double> steps, HashMap<String,Double> adder) {
		super(steps);
		this.steps=steps;
		this.adder=adder;
		
		
	}

	@Override
	public void init() {
		courant=init;
		sum=0;
		this.set_e(0);
		this.set_tr(advance());
		int i=0;
		for (Map.Entry<String,Double> me : steps.entrySet()) {
	          old_steps[i]=(double)me.getValue();
	          sum=old_steps[i]+sum;
	          System.out.println(sum);
	          i++;
		}
		adder.put("somme",sum);
		//old_steps[4]=sum;
	}

	@Override
	public double externe() {
		int i=0;
		for (Map.Entry<String,Double> me : steps.entrySet()) {
			if(i<4){
				new_steps[i]=me.getValue();
				//System.out.println(me.getValue());
				i++;
			}
		}
		//System.out.println("i = "+i);
		for(int j=0;j<i;j++){
			if(new_steps[j]!=old_steps[j])
			{
				courant = add;
				sum=sum+new_steps[j]-old_steps[j];
			}
			else courant = init;
		}
		//new_steps[4]=sum;
		//steps.put("somme",sum);
		for(int j=0;j<4;j++)
		{
			old_steps[j]=new_steps[j];	
		}
		//old_steps=new_steps;
		return 1;
	}

	@Override
	public double interne() {
		if(courant == add) {
			courant = init;
			//adder.put("somme",sum);
			return 1;
		}
		else return -1;
	}

	@Override
	public double advance() {
		// TODO Auto-generated method stub
		if(courant == init) {
			return Double.POSITIVE_INFINITY;
		}
		else if(courant == add) {
			return 0;
		}
		else return 0;
	}

	@Override
	public void sortie() {
		//System.out.println("S="+sum);
		adder.put("somme",sum);
	}
	
	public double getSum()
	{
		return sum;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
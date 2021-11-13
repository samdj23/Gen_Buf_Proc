package main_package;

import java.util.ArrayList;
import java.util.HashMap;

import chart.Chart;
import chart.ChartFrame;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

 
		ArrayList<composant> C = new ArrayList<composant>();
				
		//Somme 

//		Chart somme=new Chart("Somme");
//		Chart integrale_graph=new Chart("Integrale");
//		ArrayList<Chart> graphes=new ArrayList<Chart>();
//		graphes.add(somme);
//		graphes.add(integrale_graph);
//
//
//		HashMap<String,Double> in_out = new HashMap<String, Double>();
//		HashMap<String, Double> adder=new HashMap<String, Double>();
//		HashMap<String, Double> integrale=new HashMap<String, Double>();
//
//		step s1=new step(in_out,1,-3,0.65,"s1");
//		step s2=new step(in_out,0,1,0.35,"s2");
//		step s3=new step(in_out,0,1,1,"s3");
//		step s4=new step(in_out,0,4,1.5,"s4");
//		Adder add=new Adder(in_out,adder);
//
//
//		C.add(s1);
//		C.add(s2);
//		C.add(s3);
//		C.add(s4);
//		C.add(add);

				
				
				
				
				
				
		// ODE Integration
				
		HashMap<String, Double> in_out=new HashMap<String, Double>();
		HashMap<String, Double> adder=new HashMap<String, Double>();
		HashMap<String, Double> integrale=new HashMap<String, Double>();
		HashMap<String, Double> integrale2=new HashMap<String, Double>();

		step s=new step(in_out, -9.81, -9.81, 0.1,"step");

		Adder add=new Adder(in_out,adder);
		IntegrateurED iED1=new IntegrateurED(adder,integrale,"iED1", 0.01, 0);
		IntegrateurED iED2=new IntegrateurED(integrale,integrale2,"iED2", 0.01, 0);

		C.add(s);
		C.add(add);
		C.add(iED1);
		C.add(iED2);




		Chart plot_t_q=new Chart("acceleration");
		Chart plot_t_job=new Chart("Vitesse");
		Chart plot_position =new Chart("position");
		ArrayList<Chart> graphes=new ArrayList<Chart>();
		graphes.add(plot_t_q);
		graphes.add(plot_t_job);
		graphes.add(plot_position);
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		simulateur sim = new simulateur(C,10,graphes);
		sim.run();
		ChartFrame frame=new ChartFrame("Graph de TP COO", "somme");

		for(Chart c:graphes)
		{
			frame.addToLineChartPane(c);
			c.setIsVisible(false);
		}
		
	}

}
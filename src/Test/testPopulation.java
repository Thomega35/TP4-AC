package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Algo_Genetiques.Individu;
import Algo_Genetiques.Population;

public class testPopulation {

	//une classe qui implemente Individu et qui permet juste de spécifier adaptation
	class Individu_Test implements Algo_Genetiques.Individu{
		private double adapt;
		
		Individu_Test(double a){
			adapt = a;
		}
		@Override
		public double adaptation() {
			return adapt;
		}

		@Override
		public Individu[] croisement(Individu conjoint) { return null;}

		@Override
		public void mutation(double prob) {}
	}
	
	@Test
	public void testSelection() {
		
		Individu_Test[] t_test ={ new Individu_Test(1),new Individu_Test(2),new Individu_Test(3)};
		Population<Individu_Test> popu_test = new Population<Individu_Test>(t_test);
		
		double adapt_totale = 1+2+3;
		int[] cpt = {0,0,0};
		int n=1000000;
		for(int k=0;k<n;k++){
			cpt[popu_test.selection(adapt_totale)]++;
		}
		
		double ecart = 0;
		for(int i=0; i<3; i++){
			double valeur_theorique = t_test[i].adaptation()/adapt_totale;
			double valeur_observee  = cpt[i]/(double) n;
			System.out.println(valeur_theorique + " <-> " + valeur_observee	);
			ecart += Math.abs(valeur_theorique - valeur_observee);
		}
		assertEquals(0,ecart,0.05);

	}
	
	@Test
	public void testAdaptation_moyenne(){
		Individu_Test[] t_test ={ new Individu_Test(1),new Individu_Test(2),new Individu_Test(5),new Individu_Test(0)};
		Population<Individu_Test> popu_test = new Population<Individu_Test>(t_test);
		assertEquals(2., popu_test.adaptation_moyenne(), 0.0001);
	}
	
	@Test
	public void testAdaptation_maximale(){
		Individu_Test[] t_test ={ new Individu_Test(1),new Individu_Test(2),new Individu_Test(5),new Individu_Test(0)};
		Population<Individu_Test> popu_test = new Population<Individu_Test>(t_test);
		assertEquals(5., popu_test.adaptation_maximale(), 0.0001);
	}
	
	@Test
	public void testIndividu_maximal(){
		Individu_Test ind_max = new Individu_Test(5);
		Individu_Test[] t_test ={ new Individu_Test(1),new Individu_Test(2),ind_max,new Individu_Test(0)};
		Population<Individu_Test> popu_test = new Population<Individu_Test>(t_test);
		assertEquals(ind_max, popu_test.individu_maximal());
	}

}

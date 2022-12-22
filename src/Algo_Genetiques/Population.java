package Algo_Genetiques;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Population<Indiv extends Individu> {
	
	// Liste contenant les différents individus d'une génération
	private List<Indiv> population;
	
	
	/**
	 * construit une population à partir d'un tableau d'individu
	 */
	public Population(Indiv[] popu){
		population = new ArrayList<Indiv>();
		for (int i=0; i<popu.length; i++){
			population.add(popu[i]);
		}
	}
	
	/**
	 * sélectionne un individu (sélection par roulette par exemple, cf TD)
	 * @param adapt_totale somme des adaptations de tous les individus (pour ne pas avoir à la recalculer)
	 * @return indice de l'individu sélectionné
	 */
	public int selection(double adapt_totale){
		Random r = new Random();
		double alea = r.nextDouble()*adapt_totale;
		double somme = 0;
		int i=0;
		while (somme<alea){
			somme += population.get(i).adaptation();
			i++;
		}
		return somme>=alea ? i-1 : -1;
	}
	
	/**
	 * remplace la génération par la suivante
	 * (croisement + mutation)
	 * @param prob_mut probabilité de mutation
	 */
	@SuppressWarnings("unchecked")
	public void reproduction(double prob_mut) {
		
		// on calcule l'adaptation totale de la population
		double adapt_totale = 0;
		for (Indiv i : population){
			adapt_totale += i.adaptation();
		}
		
		/***** on construit la nouvelle génération ****/
		List<Indiv> new_generation = new ArrayList<Indiv>();
		
		/* élitisme */
		new_generation.add(individu_maximal());

		// tant qu'on n'a pas le bon nombre 
		while (new_generation.size()<population.size()){
			// on sélectionne les parents
			int indexElemSelec = selection(adapt_totale);
			Indiv parent1 = population.get(indexElemSelec);
			indexElemSelec = selection(adapt_totale);
			Indiv parent2 = population.get(indexElemSelec);
			
			// ils se reproduisent
			Individu[] enfants = parent1.croisement(parent2);
			
			// on les ajoute à la nouvelle génération
			new_generation.add((Indiv)enfants[0]);
			new_generation.add((Indiv)enfants[1]);
		}
		
		// on applique une éventuelle mutation à toute la nouvelle génération
		for (Indiv i : new_generation){
			i.mutation(prob_mut);
		}

		//on remplace l'ancienne par la nouvelle
		population = new_generation;
	}
	
	/**
	 * renvoie l'individu de la population ayant l'adaptation maximale
	 */	
	public Indiv individu_maximal(){
		Indiv max = population.get(0);
		for (Indiv i : population){
			if (i.adaptation()>max.adaptation()){
				max = i;
			}
		}
		return max;
	}

	/**
	 * renvoie l'adaptation moyenne de la population
	 */
	public double adaptation_moyenne(){
		double somme = 0;
		for (Indiv i : population){
			somme += i.adaptation();
		}
		return somme/population.size();
	}
	
	/**
	 * renvoie l'adaptation maximale de la population
	 */	
	public double adaptation_maximale(){
		return individu_maximal().adaptation();
	}
}

package Algo_Genetiques;

import java.util.List;
import java.util.ArrayList;

public class Population<Indiv extends Individu> {
	
	// Liste contenant les différents individus d'une génération
	private List<Indiv> population;
	
	
	/**
	 * construit une population à partir d'un tableau d'individu
	 */
	public  Population(Indiv[] popu){
		//TODO
	}
	
	/**
	 * sélectionne un individu (sélection par roulette par exemple, cf TD)
	 * @param adapt_totale somme des adaptations de tous les individus (pour ne pas avoir à la recalculer)
	 * @return indice de l'individu sélectionné
	 */
	public int selection(double adapt_totale){
		//TODO
		return -1;
	}
	
	/**
	 * remplace la génération par la suivante
	 * (croisement + mutation)
	 * @param prob_mut probabilité de mutation
	 */
	@SuppressWarnings("unchecked")
	public void reproduction(double prob_mut) {
		
		/***** on construit la nouvelle génération ****/
		List<Indiv> new_generation = new ArrayList<Indiv>();
		
		/* élitisme */
		//TODO (dans un second temps)
		

		// tant qu'on n'a pas le bon nombre 
		while (new_generation.size()<population.size()){
			// on sélectionne les parents
			//TODO
			
			// ils se reproduisent
			//TODO
			
			// on les ajoute à la nouvelle génération
			//TODO
		}
		
		// on applique une éventuelle mutation à toute la nouvelle génération
		//TODO

		//on remplace l'ancienne par la nouvelle
		population = new_generation;
	}
	
	/**
	 * renvoie l'individu de la population ayant l'adaptation maximale
	 */	
	public Indiv individu_maximal(){
		//TODO
		return null;
	}

	/**
	 * renvoie l'adaptation moyenne de la population
	 */
	public double adaptation_moyenne(){
		//TODO
		return -1;
	}
	
	/**
	 * renvoie l'adaptation maximale de la population
	 */	
	public double adaptation_maximale(){
		//TODO
		return -1;
	}
}

package Algo_Genetiques;

public interface Individu{

	/**
	 * renvoie l'adaptation de cet individu
	 */
	public double adaptation();
	
	/**
	 * renvoie un tableau de 2 individus constituant les
	 * enfants de la reproduction entre this et conjoint
	 * @param conjoint à accoupler avec l'objet courant
	 * @return tableau des 2 enfants
	 */
	public Individu[] croisement(Individu conjoint);
	
	/**
	 * applique l'opérateur de mutation
	 * associé à la probabilité prob
	 */
	public void mutation(double prob);
}

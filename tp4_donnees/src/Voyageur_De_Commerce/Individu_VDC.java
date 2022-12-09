package Voyageur_De_Commerce;

import Algo_Genetiques.Individu

public class Individu_VDC implements Individu {

	//Constructeur
	public Individu_VDC(double[] coord_x, double[] coord_y) {
		// TODO 
	}

	/* Classes de l'interface Individu
	 */
	@Override
	public double adaptation() {
		// TODO 
		return 0;
	}
	@Override
	public Individu[] croisement(Individu conjoint) {
		// Une possibilité: croisement "prudent"
		// A COMPLETER ET A ADAPTER A VOS CHOIX DE REPRESENTATION

		// boolean[] b1 = new boolean[parcours.length];
		// boolean[] b2 = new boolean[parcours.length];
		// for(int i=0;i<parcours.length;i++){
		// 	b1[i]=false;
		// 	b2[i]=false;
		// }
		// Random r = new Random();
		// int ind = r.nextInt(parcours.length);
		
		// // on regarde les villes qu'on rencontre dans la premiere partie
		// for(int i=0;i<ind;i++){
		// 	enfants[0].parcours[i] = this.parcours[i];
		// 	b1[this.parcours[i]] = true;

		// 	enfants[1].parcours[i] = conjoint_vdc.parcours[i];
		// 	b2[conjoint_vdc.parcours[i]] = true;
		// }
		
		// //deuxieme partie : si la ville n'a pas été visitée dans la premiere partie, on prend
		
		// //fin : on complète avec les villes non rencontrées 

		return null;
	}
	@Override
	public void mutation(double prob) {
		// TODO 
		
	}
	
	/* Accesseurs (pour Display_VDC)
	 */
	public int[] get_parcours(){
		return null;
	}
	public double[] get_coord_x(){
		return null;
	}
	
	public double[] get_coord_y(){
		return null;
	}	
}

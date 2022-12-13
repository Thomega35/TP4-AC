
package Voyageur_De_Commerce;

import java.util.Random;

import Algo_Genetiques.Individu;

public class Individu_VDC implements Individu {

	private double cities_x[];
	private double cities_y[];

	private int parcours[];

	//Constructeur
	public Individu_VDC(double[] coord_x, double[] coord_y) {
		
		this.cities_x = coord_x;
		this.cities_y = coord_y;

		this.parcours = new int[coord_x.length];

		for(int i = 0; i < coord_x.length; i++){
			parcours[i] = i;
		}

		Random rand = new Random();

		for (int i = 0; i < this.parcours.length; i++) {
			int randomIndexToSwap = rand.nextInt(this.parcours.length);
			int temp = this.parcours[randomIndexToSwap];
			this.parcours[randomIndexToSwap] = this.parcours[i];
			this.parcours[i] = temp;
		}

	}

	/* Classes de l'interface Individu
	 */
	@Override
	public double adaptation() {

		double distance = 0;

		for(int i = 0; i < parcours.length - 1; i++){
			
			distance += Math.sqrt(Math.pow(cities_x[parcours[i]] - cities_x[parcours[i+1]], 2) + Math.pow(cities_y[parcours[i]] - cities_y[parcours[i+1]], 2));

		}

		distance += Math.sqrt(Math.pow(cities_x[parcours[parcours.length-1]] - cities_x[parcours[0]], 2) + Math.pow(cities_y[parcours[parcours.length-1]] - cities_y[parcours[0]], 2));

		return distance;

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
		
		return parcours;

	}

	public double[] get_coord_x(){
		
		return cities_x;

	}
	
	public double[] get_coord_y(){
		
		return cities_y;

	}	
}
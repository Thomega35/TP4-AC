
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

		return 1/distance;

	}

	@Override
	public Individu[] croisement(Individu conjoint) {

		Individu_VDC conjoint_VDC = (Individu_VDC) conjoint;

		Random r = new Random();
		int random_ind = r.nextInt(parcours.length);

		Individu_VDC enfant1 = new Individu_VDC(this.cities_x, this.cities_y);
		Individu_VDC enfant2 = new Individu_VDC(conjoint_VDC.get_coord_x(), conjoint_VDC.get_coord_y());

		boolean[] b1 = new boolean[parcours.length];
		boolean[] b2 = new boolean[parcours.length];

		for(int i = 0 ; i < parcours.length ; i++){
			b1[i] = false;
			b2[i] = false;
		}

		for(int i = 0 ; i < random_ind ; i++){
			enfant1.set_parcours(i, this.get_parcours(i));
			b1[this.get_parcours(i)] = true;

			enfant2.set_parcours(i, conjoint_VDC.get_parcours(i));
			b2[conjoint_VDC.get_parcours(i)] = true;
		}

		int indice_enfant1 = random_ind;
		int indice_enfant2 = random_ind;

		for(int i = 0 ; i < parcours.length ; i++){
			if(!b1[i]){
				enfant1.set_parcours(indice_enfant1, i);
				indice_enfant1++;
			}
			if(!b2[i]){
				enfant2.set_parcours(indice_enfant2, i);
				indice_enfant2++;
			}
		}

		Individu[] enfants = new Individu[2];
		enfants[0] = enfant1;
		enfants[1] = enfant2;

		return enfants;
	}

	@Override
	public void mutation(double prob) {
		
		for(int i = 0 ; i < parcours.length ; i++){
			if(Math.random() < prob){
				
				int random_ind = (int) (Math.random() * parcours.length);
				int save = parcours[i];
				parcours[i] = parcours[random_ind];
				parcours[random_ind] = save;

			}
		}
		
	}
	
	/* Accesseurs (pour Display_VDC)
	 */
	public int[] get_parcours(){
		
		return parcours;

	}

	public int get_parcours(int i){
		
		return parcours[i];

	}

	public void set_parcours(int i, int val){
		
		this.parcours[i] = val;

	}

	public double[] get_coord_x(){
		
		return cities_x;

	}
	
	public double[] get_coord_y(){
		
		return cities_y;

	}	
}
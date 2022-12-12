package Voyageur_De_Commerce;

import java.io.*;
import Util.Lecture;
import Algo_Genetiques.Population;

public class Client_Voyageur_De_Commerce {

	/**
	 * lit une liste de poids dans un fichier
	 * 
	 * @param nomFichier nom du fichier texte contenant les coordonnées des villes
	 * @param nbr_villes nombre de villes
	 * @param coord_x    et coord_y : les 2 tableaux que la fonction remplit et qui
	 *                   vont contenir les coordonnées des villes
	 */
	public static void charge_coords(String nomFichier, int nbr_villes, double[] coord_x, double[] coord_y) {
		assert (coord_x.length == coord_y.length) : "charge_coords : coord_x et coord_y n'ont pas la même taille ?";
		InputStream IS = Lecture.ouvrir(nomFichier);
		if (IS == null) {
			System.err.println("pb d'ouverture du fichier " + nomFichier);
		}
		int i = 0;
		while (!Lecture.finFichier(IS) && i < coord_x.length) {
			coord_x[i] = Lecture.lireDouble(IS);
			coord_y[i] = Lecture.lireDouble(IS);
			i++;
		}
		Lecture.fermer(IS);
	}

	public static void main(String[] args) throws InterruptedException {

		/*
		 * Paramères du problème du voyageur de commerce
		 * 16 villes
		 * coordonnées des villes dans le fichier data_vdc/16coords.txt
		 */
		int nbr_villes = 16;
		int nbr_individus = 100;
		double prob_mut = 0.01;

		/*
		 * on initialise les coordonnées des villes en les lisant ds un fichier
		 */

		double[] coord_x = new double[nbr_villes];
		double[] coord_y = new double[nbr_villes];
		charge_coords("data_vdc/" + nbr_villes + "coords.txt", nbr_villes, coord_x, coord_y);

		/*
		 * On crée une population de 100 individus associés au problème du voyageur de
		 * commerce
		 */
		Individu_VDC[] indiv = new Individu_VDC[nbr_individus];
		for (int i = 0; i < nbr_individus; i++) {
			indiv[i] = new Individu_VDC(coord_x, coord_y);
		}

		/*
		 * on crée une population (aléatoire)
		 * de nbr_indiv individus associés au problème du voyageur de commerce
		 */
		Population<Individu_VDC> pop = new Population<Individu_VDC>(indiv);


		/* on génére les générations successives
		 * en faisant se reproduire la population
		 * et on affiche l'adaptation moyenne et maximale de chaque génération
		 * on s'arrête si on a atteint la capacité ou si on fait un nombre donné (paramètre) d'itérations
		 * le résultat est alors donné par l'individu maximal de la dernière génération
		 */
		int nb_iter = 0;
		int nb_iter_max = 1000;
		int precision = 1000;
		System.out.println(pop.adaptation_maximale());
		Display_VDC disp = new Display_VDC(pop.individu_maximal());
		while (nb_iter < nb_iter_max && pop.adaptation_maximale() < precision) {
			pop.reproduction(prob_mut);
			System.out.println("génération " + nb_iter + " : adaptation moyenne = " + pop.adaptation_moyenne()
					+ " adaptation maximale = " + pop.adaptation_maximale());
			nb_iter++;
			disp.refresh(pop.individu_maximal()); // on met à jour l'affichage avec le nouveau
		}
		if (nb_iter == nb_iter_max) {
			System.out.println("nombre d'itérations max atteint");
		} else {
			System.out.println("capacité maximale atteinte");
		}
	}
}
package Voyageur_De_Commerce;

import java.awt.*;
import javax.swing.*;
//librement inspiré de http://labo.algo.free.fr/code/DisplayTsp/DisplayTsp.html

class Display_VDC extends JFrame {
	private static final long serialVersionUID = 8098500165132099812L;
 
	//constructeur (affiche le parcours correspondant à Ind)
	public Display_VDC(Individu_VDC Ind){
		super("Affichage d'un parcours");
		setBounds(100,100,600,630);
		Canvas_VDC canvas=new Canvas_VDC(Ind,getBounds());
		init(canvas);
	}
	//constructeur (affiche le parcours correspondant à Ind + le numéro de chaque ville)
	public Display_VDC(Individu_VDC Ind, boolean affiche_num_villes){
		super("Affichage d'un parcours");
		setBounds(100,100,600,630);
		Canvas_VDC canvas=new Canvas_VDC(Ind,getBounds(),affiche_num_villes);
		init(canvas);
	}

	private void init(Canvas_VDC canvas){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con=this.getContentPane();
		con.add(canvas);setVisible(true);
	}
	
	//met à jour le parcours actuel par celui correspondant à Ind
	public void refresh(Individu_VDC Ind){
		Canvas_VDC canvas=new Canvas_VDC(Ind,getBounds());
		this.getContentPane().removeAll();
		init(canvas);
	}
	
	//met à jour le parcours actuel par celui correspondant à Ind (et affiche le numéro des villes)
	public void refresh(Individu_VDC Ind, boolean affiche_num_villes){
		Canvas_VDC canvas=new Canvas_VDC(Ind,getBounds(),affiche_num_villes);
		this.getContentPane().removeAll();
		init(canvas);
	}
}

class Canvas_VDC extends Canvas{
	private Rectangle bounds;
	private boolean bShowCityNumber;
	private double[] CitiesPosX;
	private double[] CitiesPosY;
	private int iNbCities;
	private int[] Parcours;
	private double distance;
	private int iMaxX, iMaxY;
	private double fXMinFen, fXMaxFen, fYMinFen, fYMaxFen;
	private static final long serialVersionUID = -2252795703355780492L;

	public Canvas_VDC(Individu_VDC Ind, Rectangle _bounds, boolean affiche_num_villes){
		this(Ind, _bounds);
		bShowCityNumber = affiche_num_villes;
	}

	public Canvas_VDC(Individu_VDC Ind, Rectangle _bounds){
		this.setBackground(Color.white);
		bounds = _bounds;
		CitiesPosX = Ind.get_coord_x();
		CitiesPosY = Ind.get_coord_y();
		assert(CitiesPosX.length==CitiesPosY.length) : "CitiesPosX et CitiesPosY n'ont pas la même taille ?";
		Parcours = Ind.get_parcours();
		iNbCities = CitiesPosX.length;
		bShowCityNumber = false;
		CalculeDistance();
		DefFenetre();
	}

	/*========================================================================
	  Fonction : Définition de la résolution la fenêtre d'affichage
	  ==========================================================================*/
	public void DefFenetre(){
		int i;
		double fMinParcours = CitiesPosX[0];
		double fMaxParcours = CitiesPosX[0];
		for (i=0; i<iNbCities-1; i++){
			if (CitiesPosX[i] > fMaxParcours)
				fMaxParcours = CitiesPosX[i];
			if (CitiesPosY[i] > fMaxParcours)
				fMaxParcours = CitiesPosY[i];
			if (CitiesPosX[i] < fMinParcours)
				fMinParcours = CitiesPosX[i];
			if (CitiesPosY[i] < fMinParcours)
				fMinParcours = CitiesPosY[i];
		}
		fMinParcours -= (fMaxParcours - fMinParcours)/25;
		fMaxParcours += (fMaxParcours - fMinParcours)/25;
		fXMinFen = fMinParcours;
		fXMaxFen = fMaxParcours;
		fYMinFen = fMinParcours;
		fYMaxFen = fMaxParcours;
		iMaxX = bounds.width;
		iMaxY = bounds.height-30; // moins 30 pour laisser la place pour la distance
	}

	/*========================================================================
	  Fonction : Calcul de la distance totale
	  ==========================================================================*/
	public void CalculeDistance(){
		distance = 0;
		for (int i=0; i<iNbCities-1; i++){
				distance += Math.sqrt( (CitiesPosX[Parcours[i]]-CitiesPosX[Parcours[i+1]])*(CitiesPosX[Parcours[i]]-CitiesPosX[Parcours[i+1]])
						+ (CitiesPosY[Parcours[i]]-CitiesPosY[Parcours[i+1]])*(CitiesPosY[Parcours[i]]-CitiesPosY[Parcours[i+1]]) );
		}
		distance += Math.sqrt( (CitiesPosX[Parcours[iNbCities-1]]-CitiesPosX[Parcours[0]])*(CitiesPosX[Parcours[iNbCities-1]]-CitiesPosX[Parcours[0]])
					+ (CitiesPosY[Parcours[iNbCities-1]]-CitiesPosY[Parcours[0]])*(CitiesPosY[Parcours[iNbCities-1]]-CitiesPosY[Parcours[0]]) );

	}
	/*========================================================================
	  Fonction : Affichage de l'applet
	  ==========================================================================*/
	public void paint(Graphics g){
		// Tracé du parcours (trajet) entre les villes
		g.setColor(Color.blue);
		for (int i=0; i<iNbCities - 1; i++){
			TraceLigne(g, CitiesPosX[Parcours[i]],  CitiesPosY[Parcours[i]],
					CitiesPosX[Parcours[i+1]],  CitiesPosY[Parcours[i+1]] );
		}
		TraceLigne(g, CitiesPosX[Parcours[iNbCities-1]],  CitiesPosY[Parcours[iNbCities-1]],
				CitiesPosX[Parcours[0]],  CitiesPosY[Parcours[0]] );

		// Tracé des villes
		g.setColor(Color.red);

		for (int i=0; i<iNbCities; i++)
			TracePoint(g, CitiesPosX[i],  CitiesPosY[i], i);

		// Distance totale
		g.setColor(Color.black);
		g.drawString("d = " + distance, 5, iMaxY);
	}

	/*========================================================================
	  Fonction : Dessine un "point" de 4x4 pixels
	  ==========================================================================*/
	public void TracePoint(Graphics g, double x, double y, int i){
		int x0, y0;
		x0 = (int)((x-fXMinFen)/(fXMaxFen-fXMinFen)*iMaxX);
		y0 = (int)(iMaxY-(y-fYMinFen)/(fYMaxFen-fYMinFen)*iMaxY);
		g.fillRect(x0-2, y0-2, 4, 4);
		if (bShowCityNumber)
			g.drawString(""+i, x0+2, y0-2);
	}

	/*========================================================================
	  Fonction : Dessine une ligne entre deux points
	  ==========================================================================*/
	public void TraceLigne(Graphics g, double x0, double y0, double x, double y){
		if ( (x0 != x) || (y0 != y) ) {
			int x1 = (int)((x0-fXMinFen)/(fXMaxFen-fXMinFen)*iMaxX);
			int y1 = (int)(iMaxY-(y0-fYMinFen)/(fYMaxFen-fYMinFen)*iMaxY);
			int x2 = (int)((x-fXMinFen)/(fXMaxFen-fXMinFen)*iMaxX);
			int y2 = (int)(iMaxY-(y-fYMinFen)/(fYMaxFen-fYMinFen)*iMaxY);
			g.drawLine(x1, y1, x2, y2);
		}
	}

}

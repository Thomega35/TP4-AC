package Sac_A_Dos;

import Algo_Genetiques.Individu;

public class Individu_SAD implements Individu{

    private boolean[] sad;
    private double poids_Max;
    private double[] poids;

    private int length;

    public Individu_SAD(double[] poids, double poids_Max) {
        
        this.poids_Max = poids_Max;
        this.poids = poids;
        this.sad = new boolean[poids.length];
        for (int i = 0; i < poids.length; i++) {
            this.sad[i] = Math.random() > 0.5 ? true : false;
        }
        this.length = poids.length;

    }

    public boolean[] getSad() {
        return sad;
    }

    @Override
    public double adaptation() {
        
        double poids_Total = 0;

        for(int i = 0 ; i < poids.length ; i++){
            if(sad[i] == true){
                poids_Total += poids[i];
            }
        }

        return poids_Total > poids_Max ? 0 : poids_Total;

    }

    @Override
    public Individu[] croisement(Individu conjoint) {

        Individu_SAD conjoint_SAD = (Individu_SAD)conjoint;
        boolean[] poids_conjoint = conjoint_SAD.getSad(); 

        int random_val = (int)Math.random()*length;

        Individu_SAD enfant1 = new Individu_SAD(poids, poids_Max);
        Individu_SAD enfant2 = new Individu_SAD(poids, poids_Max); 

        for(int i = 0 ; i < random_val ; i++){
            boolean valp1 = sad[i];
            boolean valp2 = poids_conjoint[i];
            enfant1.sad[i] = valp1;
            enfant2.sad[i] = valp2;
        }

        for (int i = random_val; i < length; i++) {
            boolean valp1 = sad[i];
            boolean valp2 = poids_conjoint[i];
            enfant1.sad[i] = valp2;
            enfant2.sad[i] = valp1;
        }

        Individu[] enfants = new Individu_SAD[2];
        enfants[0] = enfant1;
        enfants[1] = enfant2;

        return enfants;
    }

    @Override
    public void mutation(double prob) {
        
        for(int i = 0 ; i < length ; i++){
            if(Math.random() < prob){
                sad[i] = !sad[i];
            }
        }

    }
    
    

}
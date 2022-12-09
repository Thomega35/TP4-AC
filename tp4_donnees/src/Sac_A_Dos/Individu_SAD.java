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

        return Math.abs(poids_Total - poids_Max);

    }

    @Override
    public Individu[] croisement(Individu conjoint) {

        Individu_SAD conjoint_SAD = (Individu_SAD)conjoint;
        boolean[] poids_conjoint = conjoint_SAD.getSad(); 

        int random_val = (int)Math.random()*length;

        for(int i = 0 ; i < random_val ; i++){
            boolean save = sad[i];
            sad[i] = poids_conjoint[i];
            poids_conjoint[i] = save;
        }

        Individu[] enfants = new Individu_SAD[2];
        enfants[0] = conjoint_SAD;
        enfants[1] = this;

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
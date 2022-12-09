package Sac_A_Dos;

import java.util.Random;

import Algo_Genetiques.Individu;

public class Individu_SAD implements Individu{

    private boolean[] sad;
    private int poids_Max;
    private int[] poids;

    private int length;

    public Individu_SAD(int poids[], int poids_Max) {
        
        this.poids_Max = poids_Max;
        this.poids = poids;
        this.sad = new boolean[poids.length];
        this.length = poids.length;

    }

    public int[] getPoids() {
        return poids;
    }

    @Override
    public double adaptation() {
        
        int poids_Total = 0;

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
        conjoint_SAD.getPoids(); 

        int random_val = (int)Math.random()*length;

        for(int i = 0 ; i < random_val ; i++){
            boolean save = sad[i];
            
        }

        for(int i = random_val ; i < length ; i++){

        }

        return null;
    }

    @Override
    public void mutation(double prob) {
        
        
    }
    
    

}
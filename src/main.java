import Voyageur_De_Commerce.*;
import Sac_A_Dos.*;;

public class main {
    
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Démo pour Sac à dos avec 28 objets et 1581 de capacité");
        
        String[] args1_1 = {"28", "1581"};
        Client_Sac_A_Dos.main(args1_1);

        System.out.println("Démo pour Sac à dos avec 70 objets et 350 de capacité");

        String[] args1_2 = {"70", "350"};
        Client_Sac_A_Dos.main(args1_2);

        System.out.println("Démo pour Voyageur de commerce avec 4 villes");
        
        String[] args2_1 = {"4", "50"};
        Client_Voyageur_De_Commerce.main(args2_1);

        System.out.println("Démo pour Voyageur de commerce avec 16 villes");

        String[] args2_2 = {"16", "200"};
        Client_Voyageur_De_Commerce.main(args2_2);

        System.out.println("Démo pour Voyageur de commerce avec 64 villes");

        String[] args2_3 = {"64", "100"};
        Client_Voyageur_De_Commerce.main(args2_3);

        System.out.println("Démo pour Voyageur de commerce avec 250 villes");

        String[] args2_4 = {"250", "100"};
        Client_Voyageur_De_Commerce.main(args2_4);

    }

}

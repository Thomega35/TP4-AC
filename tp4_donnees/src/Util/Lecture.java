package Util;

import java.io.*;

/**
 * Quelques opérations de lecture au clavier ou dans un fichier.<br>
 * Le paramètre <b>idf</b> a toujours la même signification :<br>
 * <b>idf</b> : identifiant qui désigne un <em>fichier ouvert en lecture</em><br>
 * <ul>
 *  <li>si ce paramètre est présent, la lecture se fait <em>depuis un fichier</em>, et
 *      dans ce cas <em>la fin du fichier <b>idf</b> ne doit pas être atteinte</em> </li>
 *  <li>si ce paramètre est absent, la lecture se fait <em>au clavier</em> </li>
 * </ul>
 */

public class Lecture {   
  /**
   * Ouvre un fichier en lecture
   * @param nomFich : nom de fichier
   * @return identifiant associé au fichier (null si erreur)
   */
  public static InputStream ouvrir(String nomFich) {
    InputStream idf;
    try {idf = new DataInputStream(new FileInputStream(nomFich));}
    catch (IOException e) {idf = null;}
    return idf;
  }

  /**
   * détermine si la fin du fichier a été atteinte
   * @return vrai si la fin du fichier f a été atteinte
   */
  public static boolean finFichier(InputStream idf) {
    try {return (idf != System.in && idf.available() == 0);}
    catch(IOException e) {
      System.out.println("pb test finFichier");
      System.exit(1);
    }
    return true;
  }

  /**
   * Ferme un fichier
   */
  public static void fermer(InputStream idf) {
    try {idf.close();}
    catch (IOException e) {System.out.println("pb fermeture fichier");}
  }


  /**
   * lecture dans un fichier d'un caractère
   * @return le caractère lu
   */
  public static char lireChar(InputStream idf) {
    char carSuiv = ' ';
    try {int x = idf.read();
      if (x == -1) {
	System.out.println("lecture après fin de fichier");
	System.exit(2);
      }
      carSuiv = (char) x;
    }
    catch(IOException e) {
      System.out.println(e.getMessage());
      System.out.println("Erreur fatale");
      System.exit(3);
    }
    return carSuiv;
  }
  /**
   * lecture au clavier d'un caractère
   * @return le caractère lu
   */
  public static char lireChar() { return lireChar(System.in); }


  /**
   * lecture dans un fichier d'une chaîne terminée par une fin de ligne.
   * @return la chaîne lue
   */
  public static String lireString(InputStream idf) {
    String chainelue = "";
    char carSuiv = lireChar(idf);
    while (carSuiv != '\n') {
      if (carSuiv != '\r') chainelue = chainelue + carSuiv;
      carSuiv = (finFichier(idf)) ? '\n' : lireChar(idf);
    }
    return chainelue;
  }
  /**
   * lecture au clavier d'une chaîne terminée par une fin de ligne.
   * @return la chaîne lue
   */
  public static String lireString() { return lireString(System.in); }

  /**
   * Lecture dans un fichier d'un entier éventuellement précédé par des
   * espaces et suivi d'un espace.
   * @return l'entier lu
   */
  public static int lireInt(InputStream idf) 
    throws NumberFormatException {
    return Integer.valueOf(lireUnite(idf, false)).intValue();
  }
  /**
   * Lecture au clavier d'un entier éventuellement précédé par des
   * espaces et suivi d'un espace.
   * @return l'entier lu
   */
  public static int lireInt() throws NumberFormatException {
    return lireInt(System.in);
  }

  /**
   * Lecture dans un fichier d'un entier éventuellement précédé par des
   * espaces et suivi d'un espace. Le reste de la ligne est ignoré.
   * @return l'entier lu
   */
  public static int lireIntln(InputStream idf) 
    throws NumberFormatException {
    return Integer.valueOf(lireUnite(idf, true)).intValue();
  }
  /**
   * Lecture au clavier d'un entier éventuellement précédé par des
   * espaces et suivi d'un espace. Le reste de la ligne est ignoré.
   * @return l'entier lu
   */
  public static int lireIntln() throws NumberFormatException {
    return lireIntln(System.in);
  }

  /**
   * lecture dans un fichier d'un réel double éventuellement précédé par des espaces et suivi
   * d'un espace.
   * @return le réel lu
   */
  public static double lireDouble(InputStream idf) 
    throws NumberFormatException {
    return Double.valueOf(lireUnite(idf, false)).doubleValue();
  }
  /**
   * lecture au clavier d'un réel double éventuellement précédé par des espaces et suivi
   * d'un espace.
   * @return le réel lu
   */
  public static double lireDouble() throws NumberFormatException {
    return lireDouble(System.in);
  }

  /**
   * lecture dans un fichier d'un réel double éventuellement précédé par des espaces et suivi
   * d'un espace. le reste de la ligne est ignoré
   * @return le réel lu
   */
  public static double lireDoubleln(InputStream idf) 
    throws NumberFormatException {
    return Double.valueOf(lireUnite(idf, true)).doubleValue();
  }
  /**
   * lecture au clavier d'un réel double éventuellement précédé par des espaces et suivi
   * d'un espace. le reste de la ligne est ignoré
   * @return le réel lu
   */
  public static double lireDoubleln() throws NumberFormatException {
    return lireDoubleln(System.in);
  }

  /**
   * lecture d'une unité : séquence de caractères encadrée par des espaces.
   * @param alaligne : si vrai, ignorer le reste de la ligne en entrée
   * @return l'unité lue
   */
  public static String lireUnite(InputStream idf, boolean alaligne){
    String unite = "";char carlu = lireChar(idf);
    // on avale les blancs de debut 
    while (Character.isWhitespace(carlu)) carlu = lireChar(idf);
    // puis on agglomère jusqu'a trouver un blanc
    while (!(Character.isWhitespace(carlu))) {
      unite = unite + carlu;
      carlu = (finFichier(idf)) ? '\n' : lireChar(idf);
    }
    if (carlu =='\r') carlu = lireChar(idf);
    if (alaligne) 
      while ((!finFichier(idf)) && (carlu!= '\n')) carlu = lireChar(idf); 
    return unite;
  }
  public static String lireUnite(boolean alaligne) {
    return lireUnite(System.in, alaligne);
  }

}//class Lecture



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class main {

    public static ArrayList<Mots> lire(int nb_lettres) {
        ArrayList<Mots> motslus = new ArrayList<Mots>();
        String mot_string = ""; //Pour stocker la ligne lue
        BufferedReader br = null;
        File savescore;
        int nbSommet=0,nbArete=0;
        if(nb_lettres<10) {
            savescore = new File("mots/mots0" + nb_lettres + "lettres.txt");
        }else{
            savescore = new File("mots/mots" + nb_lettres + "lettres.txt");

        }
        try {
            br = new BufferedReader(new FileReader(savescore));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mot_string = br.readLine(); //On lit la ligne dans le fichier
            while (mot_string != null){
                nbSommet+=1;
                Mots nouvMot = new Mots(mot_string, nb_lettres);
                for (int i = 0; i < motslus.size(); i++) {
                    if(nouvMot.addVoisin(motslus.get(i)))
                        nbArete+=1;
                }
                motslus.add(nouvMot);
                mot_string = br.readLine();
            }
            br.close(); //fermeture du flux
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("nbLettre : "+nb_lettres);
        System.out.println("nbSommets : "+nbSommet+"   nbAretes :"+nbArete+"\n");
        return motslus; //on renvoie le mot
    }

    public static void main (String[] args) {
        //File savescore = new File("savescore.txt");
        ArrayList<Mots> mots4lettres = lire(4);
    }
}

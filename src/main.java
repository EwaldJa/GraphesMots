
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class main {

    public String lire(int nb_lettres) {
        ArrayList<Mots> motslus = new ArrayList<Mots>();
        String mot_string = ""; //Pour stocker la ligne lue
        BufferedReader br = null;
        File savescore = new File("mots" + nb_lettres + "lettres.txt");
        try {
            br = new BufferedReader(new FileReader(savescore));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mot_string = br.readLine(); //On lit la ligne dans le fichier
            while (mot_string != null){
                Mots nouvMot = new Mots(mot_string, nb_lettres);
                for (int i = 0; i < motslus.size(); i++) {
                    nouvMot.addVoisin(motslus.get(i));
                }
            }
            br.close(); //fermeture du flux
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mot_string; //on renvoie le mot
    }

    public static void main (String[] args) {

    }
}

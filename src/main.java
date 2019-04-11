
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class main {

    public static ArrayList<Mots> lire(int nb_lettres) {
        ArrayList<Mots> motslus = new ArrayList<Mots>();
        ArrayList<Aretes> aretes=new ArrayList<Aretes>();;
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
                    if(nouvMot.isVoisin(motslus.get(i))){
                        aretes.add(new Aretes(nouvMot,motslus.get(i)));
                        nbArete+=1;
                    }

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

    public static void sauvegarder(ArrayList<Mots> graphe, int nb_lettres) {
        //Fichier de sauvegarde
        File save = new File("save" + nb_lettres + ".obj");
            try {
                save.createNewFile(); //Au cas où le fichier n'existe pas déjà (qu'il ait été supprimé)
                //Sérialization du modèle
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(save));
                oos.writeObject(graphe);
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static ArrayList<Mots> recuperer(int nb_lettres) {
        //Variable qui servira de retour
        ArrayList<Mots> graphe = null;
        //Fichier de sauvegarde
        File save = new File("save" + nb_lettres + ".obj");
        //Si la taille est supérieure à 0, cela signifie qu'il y a une sauvegarde
        if (save.length() > 0) {
            try {
                //Lecture et instanciation de la sauvegarde
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(save));
                graphe = (ArrayList<Mots>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return graphe;
    }



    public static void main (String[] args) {
        ArrayList<Mots> mots4lettres = lire(4);
        ArrayList<Mots> mots5lettres = lire(5);
        ArrayList<Mots> mots6lettres = lire(6);
        ArrayList<Mots> mots7lettres = lire(7);
        ArrayList<Mots> mots8lettres = lire(8);
        ArrayList<Mots> mots9lettres = lire(9);
        ArrayList<Mots> mots10lettres = lire(10);
        ArrayList<Mots> mots11lettres = lire(11);
        ArrayList<Mots> mots12lettres = lire(12);
        ArrayList<Mots> mots13lettres = lire(13);
        ArrayList<Mots> mots14lettres = lire(14);
        ArrayList<Mots> mots15lettres = lire(15);
        ArrayList<Mots> mots16lettres = lire(16);
        ArrayList<Mots> mots17lettres = lire(17);
        ArrayList<Mots> mots18lettres = lire(18);


    }
}

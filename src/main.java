
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class main {

    public static Graphe lire(int nb_lettres) {
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
        return new Graphe(motslus, aretes); //on renvoie le mot
    }

    public static void sauvegarder(Graphe graphe, int nb_lettres) {
        //Fichier de sauvegarde
        File save = new File("saveGraphes/save" + nb_lettres + ".obj");
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

    public static Graphe recuperer(int nb_lettres) {
        //Variable qui servira de retour
        Graphe graphe = null;
        //Fichier de sauvegarde
        File save = new File("saveGraphes/save" + nb_lettres + ".obj");
        //Si la taille est supérieure à 0, cela signifie qu'il y a une sauvegarde
        if (save.length() > 0) {
            try {
                //Lecture et instanciation de la sauvegarde
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(save));
                graphe = (Graphe) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return graphe;
    }



    public static void main (String[] args) {
        Graphe motDeNLettres;
        for (int n =4;n<19;n++) {
            System.out.println("\n\nnombre de lettres : "+n);
            motDeNLettres = recuperer(n);
            System.out.println("Nombre de sommet : "+motDeNLettres.mes_noeuds.size()+
                    "   Nombre d'aretes : "+motDeNLettres.mes_aretes.size());
            System.out.println("NbMotsSansVoisins " + motDeNLettres.getNbMotsSansVoisins());
            motDeNLettres.nbComposante();
            motDeNLettres.getNbComposanteDeDeux();
            int max = motDeNLettres.getDegreMax();
            for (int i = 0; i <= max; i++) {
                motDeNLettres.getNbSommetKVoisin(i);
            }
        }


    }
}

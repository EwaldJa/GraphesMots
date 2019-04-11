import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Graphe implements Serializable {
    private static final long serialVersionUID = 1L;
    public ArrayList<Mots> mes_noeuds;
    public ArrayList<Aretes> mes_aretes;

    public Graphe(ArrayList<Mots> noeuds, ArrayList<Aretes> aretes) {
        mes_noeuds = noeuds;
        mes_aretes = aretes;
    }

    public ArrayList<Aretes> getAretesMots(Mots un_mot) {
        ArrayList<Aretes> res = new ArrayList<Aretes>();
        for (int i = 0; i < mes_aretes.size(); i++) {
            Aretes arete = mes_aretes.get(i);
            if ( (arete.noeud1.isSameas(un_mot)) || (arete.noeud2.isSameas(un_mot)) ) {
                res.add(arete);
            }
        }
        return res;
    }
    public ArrayList<Mots> getVoisin(Mots un_mot){
        ArrayList<Mots> voisins=new ArrayList<>();
        ArrayList<Aretes> aretes=getAretesMots(un_mot);
        for (Aretes arete : aretes){
            if(arete.noeud1.isSameas(un_mot)){
                voisins.add(arete.noeud2);
            }else{
                voisins.add(arete.noeud1);
            }
        }
        return  voisins;
    }
    int [] connex;
    int couleur;
    public int nbComposante(){

         connex = new int[mes_noeuds.size()];
        for (int i=0;i<mes_noeuds.size();i++){
            connex[i]=0;
        }
        couleur=1;
        int i=0;
        for( i=0;i<mes_noeuds.size();i++){
            if(connex[i]==0){
                rempliComposante( mes_noeuds.get(i));
                couleur=couleur+1;
            }
        }
        System.out.println("NB composante :"+couleur);
        return couleur;
    }
    private void rempliComposante(Mots mot){
        ArrayList <Mots> voisinsAExplorer=new ArrayList<>();
        for (Mots  voisin :getVoisin(mot)){
            if(connex[mes_noeuds.indexOf(voisin)]==0){
                connex[mes_noeuds.indexOf(voisin)]=couleur;
                voisinsAExplorer.add(voisin);
            }
        }
        for (Mots voisin :voisinsAExplorer){
            rempliComposante(voisin);
        }
    }


    public int getNbMotsSansVoisins() {
        int res = 0;
        for (int i = 0; i < mes_noeuds.size(); i++) {
            if (getAretesMots(mes_noeuds.get(i)).size() == 0) {
                res++;
            }
        }
        return res;
    }

    public int getNbComposanteDeDeux(){
        int nombre=0;
        int taille=0;
        int j=0;
        for (int i =1; i<couleur; i++){
            taille=0;
            j=0;
            while (j<mes_noeuds.size() && taille<=2){
                if(connex[j]==i){
                    taille=taille+1;
                }
                j=j+1;
            }
            if (taille==2){
                nombre=nombre+1;
            }

        }
        System.out.println("Nb Composante de 2 : "+nombre);
        return nombre;
    }
    public  int getDegreMax(){
        int max =-1;
        for (int i = 0; i < mes_noeuds.size(); i++) {
            if (getAretesMots(mes_noeuds.get(i)).size() > max) {
                max=getAretesMots(mes_noeuds.get(i)).size();
            }
        }
        System.out.println("DegreMax :"+max);
        return max;
    }

    public  int getNbSommetKVoisin(int k){
        int nombre =0;
        for (int i = 0; i < mes_noeuds.size(); i++) {
            if (getAretesMots(mes_noeuds.get(i)).size() == k ) {
                nombre+=1;
            }
        }
        System.out.println("degre de "+k+" voisin : "+nombre);
        return nombre;
    }

}
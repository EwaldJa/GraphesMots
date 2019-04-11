import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Graphe implements Serializable {
    public ArrayList<Mots> mes_noeuds;
    public ArrayList<Aretes> mes_aretes;

    public Graphe(ArrayList<Mots> noeuds, ArrayList<Aretes> aretes) {
        mes_noeuds = noeuds;
        mes_aretes = aretes;
    }

    public ArrayList<Mots> getNoeuds() {
        return mes_noeuds;
    }

    public ArrayList<Aretes> getAretes() {
        return mes_aretes;
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
    public int nbComposante(){

        int [] connex = new int[mes_noeuds.size()];
        for (int i=0;i<mes_noeuds.size();i++){
            connex[i]=0;
        }
        int couleur=1;
        int i=0;
        for( i=0;i<mes_noeuds.size();i++){
            if(connex[i]==0){
                rempliComposante(connex,couleur, mes_noeuds.get(i));
                couleur=couleur+1;
            }
        }
        System.out.println("NB composante :"+couleur);
        return couleur;
    }
    public void rempliComposante(int [] connex,int couleur,Mots mot){
        for (Mots  voisin :getVoisin(mot)){
            if(connex[mes_noeuds.indexOf(voisin)]==0){
                connex[mes_noeuds.indexOf(voisin)]=couleur;
                rempliComposante(connex,couleur,voisin);
            }
        }
    }


}
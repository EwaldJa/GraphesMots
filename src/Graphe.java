import java.io.Serializable;
import java.util.ArrayList;

public class Graphe implements Serializable {
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

}
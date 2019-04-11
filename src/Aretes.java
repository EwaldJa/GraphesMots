import java.io.Serializable;

public class Aretes implements Serializable {
    public Mots noeud1;
    public Mots noeud2;

    public Aretes(Mots noeud1, Mots noeud2) {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
    }
}

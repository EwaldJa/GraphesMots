import java.io.Serializable;

public class Aretes implements Serializable {
    private static final long serialVersionUID = 1L;
    public Mots noeud1;
    public Mots noeud2;

    public Aretes(Mots noeud1, Mots noeud2) {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
    }

    public String toString() {
        return (noeud1.toString() + " " + noeud2.toString());
    }

}

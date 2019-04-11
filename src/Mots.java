import java.util.ArrayList;

public class Mots {
    public String value;
    public int lenght;
    public ArrayList<Mots> voisins;

    public Mots(String value,int lenght) {
        this.value = value;
        this.lenght =lenght;
    }
    public boolean addVoisin(Mots voisin) {
        int i = 0;
        int difference = 0;
        while (i < lenght && difference <= 1) {
            if (value.charAt(i)!=voisin.value.charAt(i))
                difference+=1;
            i = i + 1;
        }
        voisins.add(voisin);
        voisin.voisins.add(this);
        return difference <= 1;
    }
}

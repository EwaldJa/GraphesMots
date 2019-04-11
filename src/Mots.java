import java.util.ArrayList;
import java.io.Serializable;

public class Mots implements Serializable {
    public String value;
    public int lenght;

    public Mots(String value,int lenght) {
        this.value = value;
        this.lenght =lenght;
    }
    public boolean isVoisin(Mots voisin) {
        int i = 0;
        int difference = 0;
        while (i < lenght && difference <= 1) {
            if (value.charAt(i)!=voisin.value.charAt(i))
                difference+=1;
            i = i + 1;
        }
        return difference <= 1;
    }
}

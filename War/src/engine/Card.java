package engine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
class Card implements Comparable<Card>{
    //lista immutabile
    private static final List<String> valori= Collections.unmodifiableList(
            new ArrayList<>(Arrays.asList(
                    "dummy","dummy","2","3",
                    "4","5","6","7","8","9","10",
                    "Jack","Regina","Re","Asso"))
    );
    //lista immutabile
    private static final List<String> semi = Collections.unmodifiableList(
            new ArrayList<>(Arrays.asList(
                    "Picche", // piu' debole
                    "Quadri",
                    "Fiori",
                    "Cuori" // piu' forte 
            ))
    );
    
    private final int valore;
    private final int seme;

    public Card(int valore, int seme){
        this.valore = valore;
        this.seme = seme;
    }

    @Override
    public String toString() {
        return valori.get(valore) + " di " + semi.get(seme);
    }
    
    @Override
    public int compareTo(Card o) {
        if(valore < o.valore)
            return -1;
        else if (valore > o.valore)
            return 1;
        // se hanno lo stesso valore guardo i semi
        else return Integer.compare(seme, o.seme);
    }
    
}

import engine.Gioco;
public class Main {
    public static void main(String[] args) {
        Gioco primaPartita = new Gioco("Pietro", "Sivelli");
        String vincitore = primaPartita.giocaPartita();
        System.out.println("Il vincitore e': " + vincitore);
    }
}

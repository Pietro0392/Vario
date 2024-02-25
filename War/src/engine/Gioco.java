package engine;
import java.util.Scanner;

public class Gioco {
    private final Player player1;
    private final Player player2;
    private final Deck mazzoDiGioco;
    private final Scanner tastiera;

    public Gioco(String nomePlayer1, String nomePlayer2){
        player1 = new Player(nomePlayer1);
        player2 = new Player(nomePlayer2);
        mazzoDiGioco = new Deck();
        tastiera = new Scanner(System.in);
    }
    // la funzione returna il nome del player che ha vinto oppure "Patta" se la partita finisce in parita'
    public String giocaPartita(){
        mazzoDiGioco.mescola();
        // fintanto che il mazzo ha le carte da pescare
        while (mazzoDiGioco.size() >=2){

            // ogni tanto chiede all'utente se vuole uscire dal gioco o continuare
            if (mazzoDiGioco.size() % 5 == 0 && uscitaVolontaria())
                break;
            else giocaMatch();

        }
        //chiusura scanner
        tastiera.close();
        return chiHaVinto();
    }
    private void giocaMatch(){
        // i due giocatori pescano le carte
        player1.pescaCarta(mazzoDiGioco.rimuoviCartaInCima());
        player2.pescaCarta(mazzoDiGioco.rimuoviCartaInCima());
        // si controlla chi ha vinto il match
        controllaVittoriaMatch(player1.getCartaPosseduta(),player2.getCartaPosseduta());
        faiUnaPausa();
    }
    private void controllaVittoriaMatch(Card cartaPlayer1, Card cartaPlayer2){
        if (cartaPlayer1.compareTo(cartaPlayer2) >= 1){
            player1.incrementaVittorie();
            System.out.println("\n" + player1.getNome() + " vince questo match" + "\n");
        }
        else{
            player2.incrementaVittorie();
            System.out.println("\n" + player2.getNome() + " vince questo match" + "\n");
        }
    }
    // funzione che controlla se l'utente e' intenzionato ad uscire
    private boolean uscitaVolontaria(){
        //Scanner tastiera = new Scanner(System.in);

        System.out.println("Se vuoi uscire dal gioco premi q (altrimenti qualunque altro tasto va bene)");
        String rispostaPlayer = tastiera.nextLine();

        //tastiera.close();

        if(rispostaPlayer.equals("q"))
            return true;
        else return false;
    }
    private String chiHaVinto(){
        if (player1.getVittorie() > player2.getVittorie())
            return player1.getNome();
        else if (player1.getVittorie() < player2.getVittorie())
            return player2.getNome();
        else return "Patta";
    }
    private void faiUnaPausa(){
        try {
            // Pausa di 1 secondo
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // public static void main(String[] args) {
    //     Deck myDeck = new Deck();
    //     myDeck.mescola();
    //     for(Card carta : myDeck)
    //         System.out.println(carta);
    // }
}

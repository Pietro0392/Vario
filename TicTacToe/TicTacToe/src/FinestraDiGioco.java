import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraDiGioco extends JFrame implements ActionListener{

    private static final int LARGHEZZA = 500;
    private static final int ALTEZZA = 600;
    private final Game gioco;
    private final PulsanteDiGioco uno = new PulsanteDiGioco("uno");
    private final PulsanteDiGioco due = new PulsanteDiGioco("due");
    private final PulsanteDiGioco tre = new PulsanteDiGioco("tre");
    private final PulsanteDiGioco quattro = new PulsanteDiGioco("quattro");
    private final PulsanteDiGioco cinque = new PulsanteDiGioco("cinque");
    private final PulsanteDiGioco sei = new PulsanteDiGioco("sei");
    private final PulsanteDiGioco sette = new PulsanteDiGioco("sette");
    private final PulsanteDiGioco otto = new PulsanteDiGioco("otto");
    private final PulsanteDiGioco nove = new PulsanteDiGioco("nove");
    private final ArrayList<PulsanteDiGioco> pulsanti = new ArrayList<>(Arrays.asList(uno, due, tre, quattro, cinque, sei, sette, otto, nove));
    private final JTextField risultato;
    private boolean player = true;  // true per le croci false per i cerchi
    private int counter = 0;
    private boolean fine = false;

    public FinestraDiGioco(){
        // operazioni di inizializazzione del gioco e della finestra di gioco
        gioco = new Game();
        setTitle("Tic tac toe");
        setSize(LARGHEZZA,ALTEZZA);
        setDefaultCloseOperation(3);
        setLayout(new BorderLayout());

        // binding pulsanti e azioni
        JPanel pannellopulsanti = new JPanel();
        for(final PulsanteDiGioco pulsante : pulsanti){
            pulsante.addActionListener(this);
            pannellopulsanti.add(pulsante);
        }

        pannellopulsanti.setLayout(new GridLayout(3,3));
        pannellopulsanti.setSize(LARGHEZZA, ALTEZZA-100);
        add(pannellopulsanti,BorderLayout.CENTER);

        JPanel pannellorisultati = new JPanel();
        pannellorisultati.setSize(LARGHEZZA, ALTEZZA-500);
        pannellorisultati.setLayout(new GridLayout(1,2));
        
        risultato = new JTextField("Partita iniziata");
        final JButton reset = new JButton("Reset");
        reset.addActionListener(this);
        pannellorisultati.add(risultato);
        pannellorisultati.add(reset);
        risultato.setEditable(false);
        add(pannellorisultati,BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String comando = e.getActionCommand();
        if(comando == "Reset"){
            gioco.reset();
            fine = false;
            risultato.setText("Partita resettata");
            for(final PulsanteDiGioco pulsante : pulsanti)
                pulsante.setText("");   // resetto tutti i pulsanti
            counter = 0;
            player = true;  // rimetto la croce come prima mossa
        }
        // come se fosse una autoritenuta, fine serve ad evitare di chiamare checkwin quando la partita finisce
        if(fine || gioco.checkWin()){ 
            fine=true;
            return; // evito di chiamare il codice dopo inutilmente
        }
        int i=0;
        int j=0;
        for(final PulsanteDiGioco pulsante : pulsanti){
            if(comando.equals(pulsante.getNome()) && pulsante.getText().equals("")){
               if(player){
                    pulsante.setText("X");
                    gioco.setGriglia(i, j, 'X');
                }
                else{
                    pulsante.setText("O");
                    gioco.setGriglia(i, j, 'O');
                }
                player=!player;
                counter++;
                break;      // non obbligatorio ma per velocizzare -> complessita': O(n) invece che ThetaGrande(n)
            }
            if(j==2){
                j=0;
                i++;
            }
            else j++;
        }
        if(counter == 9)
            risultato.setText("Patta");
        else if(gioco.checkWin()){
            System.out.println("Partita terminata");
            risultato.setText("Il " + gioco.getWinner() + " Vince!");
        }
    }
}
import java.awt.Color;
import javax.swing.JButton;

public class PulsanteDiGioco extends JButton{
    private final String nome;
    public PulsanteDiGioco(String nome){
        super();
        this.nome = nome;
        super.setActionCommand(nome);
        super.setBackground(Color.LIGHT_GRAY);
    }
    public String getNome(){
        return nome;
    }
}

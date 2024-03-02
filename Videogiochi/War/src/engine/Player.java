package engine;

class Player {
    private final String nome;
    private Card cartaPosseduta;
    private int vittorie;

    public Player(String nome){
        this.nome = nome;
        this.vittorie = 0;
    }
    public void incrementaVittorie(){
        vittorie++;
    }
    public void pescaCarta(Card cartaPosseduta) {
        System.out.println(nome + " Ha pescato " + cartaPosseduta);
        this.cartaPosseduta = cartaPosseduta;
    }
    public String getNome() {
        return nome;
    }
    public Card getCartaPosseduta() {
        return cartaPosseduta;
    }
    public int getVittorie() {
        return vittorie;
    }
}

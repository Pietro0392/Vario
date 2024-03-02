public class Game {
    private String winner;
    private final char[][] griglia = new char[3][3];    // final???
    public Game(){  // togliere numeri magici ???
        winner = new String("");
        // la matrice e' gia inizializzata con i caratteri: ''
   } 
   public boolean checkWin(){
      if(
        (griglia[0][0]=='X' && griglia[1][0]=='X' && griglia[2][0]=='X') || // verticale 1
        (griglia[0][1]=='X' && griglia[1][1]=='X' && griglia[2][1]=='X') || // verticale 2
        (griglia[0][0]=='X' && griglia[0][1]=='X' && griglia[0][2]=='X') || // orizzontale 1
        (griglia[1][0]=='X' && griglia[1][1]=='X' && griglia[1][2]=='X') || // orizzontale 2
        (griglia[2][0]=='X' && griglia[2][1]=='X' && griglia[2][2]=='X') || // orizzontale 3
        (griglia[0][0]=='X' && griglia[1][1]=='X' && griglia[2][2]=='X') || // diagonale 1
        (griglia[0][2]=='X' && griglia[1][1]=='X' && griglia[2][0]=='X')){    // diagonale 2

          winner = "Giocatore 1";
          return true;
          
      }
      else if(
        (griglia[0][0]=='O' && griglia[1][0]=='O' && griglia[2][0]=='O') || // verticale 1
        (griglia[0][1]=='O' && griglia[1][1]=='O' && griglia[2][1]=='O') || // verticale 2
        (griglia[0][0]=='O' && griglia[0][1]=='O' && griglia[0][2]=='O') || // orizzontale 1
        (griglia[1][0]=='O' && griglia[1][1]=='O' && griglia[1][2]=='O') || // orizzontale 2
        (griglia[2][0]=='O' && griglia[2][1]=='O' && griglia[2][2]=='O') || // orizzontale 3
        (griglia[0][0]=='O' && griglia[1][1]=='O' && griglia[2][2]=='O') || // diagonale 1
        (griglia[0][2]=='O' && griglia[1][1]=='O' && griglia[2][0]=='O')){    // diagonale 2

            winner = "Giocatore 2";
            return true;
      }
      else return false;
   }
   public String getWinner(){
        return winner;
   }
   public void setGriglia(final int firstindex, final int secondindex, final char value){
        this.griglia[firstindex][secondindex] = value;
   }
   public void reset(){
        for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    griglia[i][j] = '\0';
   }
}

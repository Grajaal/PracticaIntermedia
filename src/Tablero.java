public class Tablero {
    private Ficha[][] fichas; 
    private int rows; 
    private int columns; 

    public Tablero(Ficha[][] fichas, int rows, int columns){
        this.rows = rows; 
        this.columns = columns; 
        this.fichas = fichas; 
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                sb.append(fichas[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

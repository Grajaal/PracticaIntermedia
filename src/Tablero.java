public class Tablero {
    private char[][] fichas; 
    private int filas; 
    private int columnas; 

    public Tablero(char[][] fichas, int rows, int columns){
        this.filas = rows; 
        this.columnas = columns; 
        this.fichas = fichas; 
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                sb.append(fichas[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

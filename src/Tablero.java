public class Tablero {
    private char[][] fichas; 
    private int filas; 
    private int columnas; 
    private int numFichas; 

    public Tablero(char[][] fichas, int rows, int columns){
        this.filas = rows; 
        this.columnas = columns; 
        this.fichas = fichas; 
        this.numFichas = filas * columnas; 
    }

    public int getFilas(){
        return this.filas;
    }

    public int getColumnas(){
        return this.columnas; 
    }

    public void realizarMovimiento(int fila, int columna){
        char color = this.fichas[fila][columna];

        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                if(this.fichas[i][j] == color){
                    this.fichas[i][j] = ' '; 
                }
            }
        }

        bajarFichas();

        for(int j = 0; j < this.columnas - 1; j++){
            if(esColumnaVacia(j)){
                contraerFichas(j + 1);
            }
        }

        System.out.println(this.toString());

    }

    public void bajarFichas(){
        for (int j = 0; j < this.columnas; j++) {
            for (int i = this.filas - 2; i >= 0; i--) {
                if(this.fichas[i][j] != ' '){
                    int fila = i; 
                    while(fila < this.filas - 1 && !hayFichaAbajo(fila, j)){
                        moverAbajo(fila, j); 
                        fila++; 
                    }
                }
            }
        }
    }

    public void contraerFichas(int columna){
        for(int i = 0; i < this.filas; i++){
            for(int j = columna; j < this.columnas; j++){
                this.fichas[i][j - 1] = this.fichas[i][j];  
                this.fichas[i][j] = ' '; 
            }
        }
    }
    
    public boolean esColumnaVacia(int columna){
        for(int i = 0; i < this.filas; i++){
            if(this.fichas[i][columna] != ' '){
                return false; 
            }
        }
        return true; 
    }

    public boolean hayFichaAbajo(int fila, int columna){
        return this.fichas[fila + 1][columna] != ' '; 
    }

    public void moverAbajo(int fila, int columna){
        this.fichas[fila + 1][columna] = this.fichas[fila][columna]; 
        this.fichas[fila][columna] = ' ';     
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

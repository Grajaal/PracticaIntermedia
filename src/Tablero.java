public class Tablero {
    private char[][] fichas; 
    private int filas; 
    private int columnas; 
    private int numFichas; 
    private int score; 

    public Tablero(char[][] fichas, int rows, int columns){
        this.filas = rows; 
        this.columnas = columns; 
        this.fichas = fichas; 
        this.numFichas = filas * columnas; 
        this.score = 0; 
    }

    public Tablero(Tablero otroTablero){
        this.filas = otroTablero.getFilas();
        this.columnas = otroTablero.getColumnas(); 
        this.fichas = new char[this.filas][this.columnas]; 
        this.score = otroTablero.getScore(); 
        this.numFichas = otroTablero.getNumFichas(); 
        char[][] otroTableroFichas = otroTablero.getFichas(); 
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                this.fichas[i][j] = otroTableroFichas[i][j]; 
            }
        }
    }

    public int getFilas(){
        return this.filas;
    }

    public int getColumnas(){
        return this.columnas; 
    }

    public char[][] getFichas(){
        return this.fichas; 
    }

    public int getScore(){
        return this.score; 
    }

    public int getNumFichas(){
        return this.numFichas; 
    }

    public void setNumFichas(int numFichas){
        this.numFichas = numFichas; 
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setFichas(char[][] fichas){
        this.fichas = fichas; 
    }

    public boolean fin(){
        return this.getNumFichas() < 4 && this.comprobarNumeroFicharDeColor('A') < 2 && 
        this.comprobarNumeroFicharDeColor('R') < 2 && this.comprobarNumeroFicharDeColor('V') < 2; 
    }

    public int realizarMovimiento(int fila, int columna){
        char color = this.fichas[fila][columna];
        boolean[][] grupoFichas = new boolean[this.filas][this.columnas]; 

        encontrarGrupo(grupoFichas, fila, columna, color);
        int numFichasMismoGrupo = contarFichasMismoGrupo(grupoFichas); 
    
        if(numFichasMismoGrupo > 1){
            eliminarFichasGrupo(grupoFichas);
            calcularScore(numFichasMismoGrupo); 
            bajarFichas();
            contraerColumnasVacias(); 
            return numFichasMismoGrupo; 
        }
        return 0; 

    }

    public int contarFichasMismoGrupo(boolean[][] grupoFichas){
        int numFichasMismoGrupo = 0; 
        for(int i = 0; i < this.filas; i++){
            for(int j = 0; j < this.columnas; j++){
                if(grupoFichas[i][j] == true){
                    numFichasMismoGrupo++; 
                }
            }
        }
        return numFichasMismoGrupo; 
    }

    public void encontrarGrupo(boolean[][] grupoFichas, int fila, int columna, char color) {
        if (fila < 0 || fila == this.filas || columna < 0 || columna == this.columnas || grupoFichas[fila][columna]) {
            return;
        }

        if(this.fichas[fila][columna] != color){
            return; 
        }

        grupoFichas[fila][columna] = true;

        encontrarGrupo(grupoFichas, fila - 1, columna, color); 
        encontrarGrupo(grupoFichas, fila + 1, columna, color); 
        encontrarGrupo(grupoFichas, fila, columna - 1, color); 
        encontrarGrupo(grupoFichas, fila, columna + 1, color); 
    }

    public void eliminarFichasGrupo(boolean[][] grupoFichas){
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                if(grupoFichas[i][j] == true){
                    this.fichas[i][j] = ' '; 
                    this.numFichas--; 
                }
            }
        }
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

    public void contraerColumnasVacias(){
        for(int j = this.columnas - 2; j >= 0; j--){
             if(esColumnaVacia(j)){
                contraerFichas(j + 1);
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

    public void calcularScore(int m){
         this.score += (int) Math.pow(m - 2, 2); 
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

    public boolean noSePuedenEliminarMasFichas(){
        return this.numFichas < 2; 
    }

    public int comprobarNumeroFicharDeColor(char color){
        int fichas = 0; 
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                if(this.fichas[i][j] == color)
                    fichas++;
            }
        }
        return fichas; 
    }

    public Tablero copyOf(){
        char[][] original = this.getFichas(); 
        char[][] copy = new char[this.filas][this.columnas]; 
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                copy[i][j] = original[i][j]; 
            }
        }
        return new Tablero(copy, this.filas, this.columnas); 
    }
}

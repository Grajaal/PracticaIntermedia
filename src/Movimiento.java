public class Movimiento {
    private Tablero tablero; 
    private int numFichasEliminadas, score; 
    private char color; 
    private int fila, columna; 

    public Movimiento(Tablero tablero, int fila, int columna, char color, int numFichasEliminadas){
        this.tablero = tablero; 
        this.fila = fila; 
        this.columna = columna; 
        this.color = color; 
        this.numFichasEliminadas = numFichasEliminadas; 
        this.score = calcularScore(); 
    }

    public int getFila(){
        return this.fila; 
    }

    public int getColumna(){
        return this.columna; 
    }

    public char getColor(){
        return this.color; 
    }

    public int getNumFichasEliminadas(){
        return this.numFichasEliminadas; 
    }

    public int getScore(){
        return this.score; 
    }

    public int calcularScore(){
        return (int) Math.pow(this.numFichasEliminadas - 2, 2); 
    }
}

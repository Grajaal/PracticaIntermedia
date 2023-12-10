import java.util.List; 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FichasIguales {
    
    private Tablero[] juegos;
    private FichasIgualesUI ui; 
    int numJuegos; 

    public FichasIguales(Tablero[] juegos, FichasIgualesUI ui){
        this.juegos = juegos; 
        this.ui = ui; 
        this.numJuegos = juegos.length;
    }
    
    public void jugar(){ 
        for(int i = 1; i <= this.numJuegos; i++){
            System.out.println("Juego " + i + ":");
            Tablero tableroFinal = buscarMejorMovimiento(this.juegos[i - 1], 1);
            System.out.println("Puntuación final: " + tableroFinal.getScore() + ", quedando " + tableroFinal.getNumFichas() + " fichas.");
            System.out.println();
        }
    }

    public Tablero buscarMejorMovimiento(Tablero tablero, int numMovimiento){

        if(tablero.fin()){
            if(tablero.getNumFichas() == 0)
                tablero.setScore(tablero.getScore() + 1000);
            return tablero; 
        }
        char color; 
        int score = -1; 
        int numFichasEliminadas; 
        Tablero nuevoTablero = new Tablero(tablero); 
        Tablero mejorTablero = null; 
        Movimiento movimiento = null; 
        for(int i = tablero.getFilas() - 1; i >= 0; i--){
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if(tablero.getFichas()[i][j] != ' '){
                    color = nuevoTablero.getFichas()[i][j];
                    numFichasEliminadas = nuevoTablero.realizarMovimiento(i, j);
                    if(nuevoTablero.comprobarNumeroFicharDeColor(color) == 1 && numFichasEliminadas > 0){ 
                        if(mejorTablero == null){
                            mejorTablero = new Tablero(nuevoTablero); 
                            movimiento = new Movimiento(mejorTablero, i, j, color, numFichasEliminadas); 
                        }
                        setTablero(tablero, nuevoTablero);
                    }
                    else if(numFichasEliminadas == 0){
                        setTablero(tablero, nuevoTablero);
                    }
                    else{
                        if(nuevoTablero.getScore() > score){
                            score = nuevoTablero.getScore(); 
                            mejorTablero = new Tablero(nuevoTablero); 
                            movimiento = new Movimiento(mejorTablero, i, j, color, numFichasEliminadas); 
                            setTablero(tablero, nuevoTablero); 
                        }
                        else    
                            setTablero(tablero, nuevoTablero);
                    }
                }
            }
        }
        //System.out.println(mejorTablero.toString());
        int filaSimetrica = mejorTablero.getFilas() - 1 - movimiento.getFila(); 
        System.out.println("Movimiento " + numMovimiento + " en (" + (filaSimetrica + 1) + ", " + (movimiento.getColumna() + 1) + "): eliminó " +
        movimiento.getNumFichasEliminadas() + " de color " + movimiento.getColor() + " y obtuvo " + movimiento.getScore() + " puntos.");
        return buscarMejorMovimiento(mejorTablero, numMovimiento + 1);    
    }

    public void setTablero(Tablero origenTablero, Tablero nuevoTablero){
        nuevoTablero.setFichas(origenTablero.copyOf().getFichas());
        nuevoTablero.setScore(origenTablero.getScore());
        nuevoTablero.setNumFichas(origenTablero.getNumFichas());
    }
}

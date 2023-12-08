import java.util.List; 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FichasIguales {
    
    private Tablero[] juegos; 
    int numJuegos; 

    public FichasIguales(Tablero[] juegos){
        this.juegos = juegos; 
        numJuegos = juegos.length; 
    }
    
    public void jugar(){ 
        for(Tablero tablero : this.juegos){
            Tablero tableroInicial = tablero; 

            tablero.realizarMovimiento(2, 0); 
            
        }
    }
}

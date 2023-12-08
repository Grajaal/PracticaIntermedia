import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FichasIgualesUI {
    public Tablero[] init(){
        Scanner scanner = new Scanner(System.in);  

        // Lee el número de juegos que el jugador quiere jugar. 
        int numJuegos = scanner.nextInt(); 
        if(numJuegos < 1)
            throw new InputMismatchException("El número de juegos tiene que ser positivo.");
        scanner.nextLine(); 
        scanner.nextLine(); 

        String fila; 
        int columnasObjetivo; 
        ArrayList<ArrayList<String>> entradasUsuario = new ArrayList<>(numJuegos); 
        for(int i = 0; i < numJuegos; i++)
            entradasUsuario.add(new ArrayList<>()); 
        Tablero[] juegos = new Tablero[numJuegos]; 

        for(int i = 0; i < numJuegos; i++){
            fila = scanner.nextLine(); 
            if(!coloresCorrectos(fila)) throw new InputMismatchException("Las fichas introducidas en el tablero no son rojas, verdes o azules."); 
            columnasObjetivo = fila.length(); 
            if(columnasObjetivo > 20) throw new InputMismatchException("El número de columnas debe ser menor o igual a 20.");
            entradasUsuario.get(i).add(fila); 

            while(!(fila = scanner.nextLine()).isEmpty()){
                if(fila.length() != columnasObjetivo) throw new InputMismatchException("Las columnas no son las mismas en todas las filas del tablero introducido.");
                if(!coloresCorrectos(fila)) throw new InputMismatchException("Las fichas introducidas en el tablero no son rojas, verdes o azules."); 

                entradasUsuario.get(i).add(fila); 
            }
            juegos[i] = new Tablero(convertirArrayChar(entradasUsuario.get(i)), entradasUsuario.size(), columnasObjetivo); 
        }
        return juegos;         
    }

    public boolean coloresCorrectos(String fila){
        for(char color : fila.toCharArray()){
            if(color != 'A' && color != 'V' && color != 'R') 
                return false; 
        }
        return true; 
    }

    public char[][] convertirArrayChar(ArrayList<String> entradaUsuario){
        int filas = entradaUsuario.size(); 
        int columnas = entradaUsuario.get(0).length(); 
        char[][] tablero = new char[filas][columnas]; 

        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                tablero[i][j] = entradaUsuario.get(i).charAt(j); 
            }
        }

        return tablero; 
    }

    
}

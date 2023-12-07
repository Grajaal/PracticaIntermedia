import java.util.List; 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {
    public static void main(String[] args) throws Exception {
        Juego juego = new Juego(); 
        juego.iniciarJuego(); 
    }
    
    public void iniciarJuego() throws InputMismatchException{
        int nGames = leerJuegos();
        inicializarTableros(nGames); // Lee los tableros del usuario. 
        

        for(int i = 0; i < nGames; i++)
            play(games.get(i));
    }

    public int leerJuegos(){
        Scanner scanner = new Scanner(System.in); 

        // Lee el número de juegos que el jugador quiere jugar. 
        int nGames = scanner.nextInt(); 
        if(nGames < 1){
            scanner.close(); 
            throw new InputMismatchException("El número de juegos tiene que ser positivo."); 
        }
        scanner.close(); 

        return nGames; 
    }

    public void inicializarTableros(int nGames){
        List<ArrayList<String>> games = new ArrayList<>(nGames); 
        for(int i = 0 ; i < nGames; i++)
            games.add(new ArrayList<String>());

        for(int i = 0; i < nGames; i++){
            leerTablero(games.get(i)); 
            convertToFichas(games.get(i));
        }
    }

    public void leerTablero(ArrayList<String> game){
        
        Scanner scanner = new Scanner(System.in); 
        
        String row = scanner.nextLine();
        int c = row.length();
        addRow(row, game, c);

        do{ 
            row = scanner.nextLine(); 
            addRow(row, game, c);
        }while(!row.isEmpty()); 
        
        scanner.close(); 
    }

    public static void play(ArrayList<String> gameMatrix){
        int c = gameMatrix.get(0).length(); 
        int f = gameMatrix.size(); 
        char[][] gameChar = new char[f][c]; 

        for(int i = 0; i < f; i ++){
            for(int j = 0; j < c; j++){
                game[i][j] = gameMatrix.get(i).charAt(j); 
            }
        }

        Tablero game = new Tablero(gameChar, f, c); 
    }

    public static void addRow(String input, ArrayList<String> game, int nColumns){
        if(!input.isEmpty()){
            char[] rowToChar = input.toCharArray(); 
            if(nColumns != rowToChar.length)
                throw new InputMismatchException("Numero de columnas incorrecto en el juego."); 
            for(char c : rowToChar){
                if(c != 'A' && c != 'V' && c != 'R')
                    throw new InputMismatchException("Solo hay fichaf rojas: R, verdes: V y azules: A.");
                
            }
            convertToFichas(input); 
            game.add(input); 
        }
    }

    public static void convertToFichas(ArrayList<String> tablero){
        int columnas = tablero.get(0).length(); 
        int filas = tablero.size(); 
        Ficha[][] fichas = new Ficha[filas][columnas]; 
        for(int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++) {
                char color = tablero.get(i).charAt(j); 
                fichas[i][j] = new Ficha(color, i, j);
            }
        }
    }
}

import java.util.List; 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FichasIguales {
    
    private Tablero[] juegos; 
    int numJuegos; 

    public FichasIguales(Tablero[] juegos){
        this.juegos = juegos; 
    }
    
    public void init() throws InputMismatchException{
        int nGames = leerJuegos();
        crearTableros(nGames);
        

        
    }

    public int leerJuegos(){
        Scanner scanner = new Scanner(System.in);  

        // Lee el número de juegos que el jugador quiere jugar. 
        int nGames = scanner.nextInt(); 
        if(nGames < 1){
            throw new InputMismatchException("El número de juegos tiene que ser positivo."); 
        } 
        scanner.nextLine();
        scanner.nextLine(); 

        return nGames; 
    }

    public ArrayList<Ficha[][]> inicializarTableros(int nGames){
        List<ArrayList<String>> games = new ArrayList<>(nGames); 
        for(int i = 0 ; i < nGames; i++)
            games.add(new ArrayList<String>());
 
        ArrayList<Ficha[][]> juegosFichas = new ArrayList<>(nGames); 
        for(int i = 0; i < nGames; i++){
            leerTablero(games.get(i)); 
            juegosFichas.add(convertToFichas(games.get(i)));
            
        }
        return juegosFichas; 
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
            game.add(input); 
        }
    }

    public Ficha[][] convertToFichas(ArrayList<String> tablero){
        int columnas = tablero.get(0).length(); 
        int filas = tablero.size(); 
        Ficha[][] fichas = new Ficha[filas][columnas]; 
        for(int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++) {
                char color = tablero.get(i).charAt(j); 
                fichas[i][j] = new Ficha(color, i, j);
            }
        }
        return fichas; 
    }

    public void crearTableros(int nGames){
        ArrayList<Ficha[][]> juegosFicha = inicializarTableros(nGames); // Lee los tableros del usuario. 
        for(Ficha[][] fichas : juegosFicha){
            int filas = fichas.length; 
            int columnas = fichas[0].length; 
            Tablero tablero = new Tablero(fichas, filas, columnas); 
        }
    }

    public void jugar(){

    }
}

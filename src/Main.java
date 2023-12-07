import java.util.List; 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); 

        int nGames = scanner.nextInt(); 
        if(nGames < 1)
            throw new Exception("El número de juegos tiene que ser positivo."); 
        List<ArrayList<String>> games = new ArrayList<>(nGames); 
        for(int i = 0 ; i < nGames; i++)
            games.add(new ArrayList<String>());
        scanner.nextLine(); 

        for (int i = 0; i < nGames; i++) {
            String row = scanner.nextLine();
            int c = row.length();
            addRow(row, games.get(i), c);

            do{ 
                row = scanner.nextLine(); 
                addRow(row, games.get(i), c);
            }while(!row.isEmpty()); 
        }
        

        for(int i = 0; i < nGames; i++)
            play(games.get(i));
        
    }

    public static void play(ArrayList<String> gameMatrix){
        int c = gameMatrix.get(0).length(); 
        int f = gameMatrix.size(); 
        char[][] game = new char[f][c]; 

        for(int i = 0; i < f; i ++){
            for(int j = 0; j < c; j++){
                game[i][j] = gameMatrix.get(i).charAt(j); 
            }
        }

        for(int i = 0; i < f; i ++){
            for(int j = 0; j < c; j++){
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void addRow(String input, ArrayList<String> game, int nColumns){
        if(!input.isEmpty()){
            char[] rowToChar = input.toCharArray(); 
            for(char c : rowToChar){
                if(c != 'A' && c != 'V' && c != 'R')
                    throw new InputMismatchException("Solo hay fichaf rojas: R, verdes: V y azules: A.");
            }
            if(nColumns != rowToChar.length)
                throw new InputMismatchException("Numero de columnas incorrecto en el juego."); 
            game.add(input); 
        }
    }
}

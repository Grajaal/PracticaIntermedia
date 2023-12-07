import java.util.List; 
import java.util.ArrayList;
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

        for(int i = 0; i < nGames; i++){
            String row = scanner.nextLine(); 
            while (!(row = scanner.nextLine().trim()).isEmpty())
                games.get(i).add(row); 
        }

        for(int i = 0; i < nGames; i++)
            play(games.get(i));
        
    }

    public static void play(ArrayList<String> game){
        System.out.println("hola");
    }
}

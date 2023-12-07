public class Ficha {
    private char color; 
    private int row; 
    private int column; 

    public Ficha(char color, int row, int column){
        this.color = color; 
        this.row = row; 
        this.column = column; 
    }

    public char getColor(){
        return this.color; 
    }

    public int getRow(){
        return this.row; 
    }

    public int getColumn(){
        return this.column; 
    }

}

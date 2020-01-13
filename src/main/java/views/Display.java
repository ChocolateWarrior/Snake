package views;

public class Display {

    public void view(String viewed){
        System.out.print(viewed);
    }
    public void viewAsNewLine(String viewed){
        System.out.println(viewed);
    }
    public void viewEmpty() {
        System.out.println();
    }
    public void flush(){
        System.out.print("\033[H\033[2J");
    }
}

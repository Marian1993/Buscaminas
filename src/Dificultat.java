import java.util.Scanner;

public class Dificultat {

    private static final Scanner sc = new Scanner(System.in);
    public static final int PRINCIPIANT = 1;
    public static final int INTERMITG = 2;
    public static final int EXPERT = 3;
    public static final int PERSONALITZAT = 0;



    public Dificultat(){

    }



    public static int nivellDificultat(int dificultat){


        if(dificultat == PRINCIPIANT){
           return 10;
        }else if(dificultat == INTERMITG){
            return 40;
        }else if(dificultat == EXPERT){
            return 99;
        }else {
            return 0;
        }
    }


}

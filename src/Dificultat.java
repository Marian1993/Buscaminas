import java.util.Scanner;

public class Dificultat {

    private static final Scanner sc = new Scanner(System.in);
    private static final int principiant = 0;
    private static final int intermitg = 1;
    private static final int expert = 2;
    private static final int personalitzat = 3;

    public Dificultat(){

    }



    public static int principiant(int dificultat){

        if(dificultat == 0){
           return 10;
        }else if(dificultat == 1){
            return 40;
        }else if(dificultat == 2){
            return 99;
        }else if(dificultat == 3){
            return sc.nextInt();
        }else {
            return 0;
        }
    }


}

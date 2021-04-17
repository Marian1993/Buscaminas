import java.util.Scanner;

public class Joc {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[]args){


        /*Tablero tablero = new Tablero();
        System.out.println("Prinicipiant = 1" + "\nIntermitg = 2" + "\nExpert = 3" + "\nPersonalitzat = 0");
        tablero.inicialitzarTablero(sc.nextInt());*/

        int dasd = 0;
        do{
            Tablero tablero = new Tablero();
            System.out.println("Prinicipiant = 1" + "\nIntermitg = 2" + "\nExpert = 3" + "\nPersonalitzat = 0");
            tablero.inicialitzarTablero(sc.nextInt());

            dasd++;

        }while(dasd == 10);
    }
}

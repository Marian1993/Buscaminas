import java.util.InputMismatchException;
import java.util.Scanner;

public class Joc {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[]args){


        Tablero tablero = new Tablero();
        System.out.println("Prinicipiant = 1" + "\nIntermitg = 2" + "\nExpert = 3" + "\nPersonalitzat = 0");
        tablero.inicialitzarTablero(Errors.esNecessitaNumero());

        do{

            tablero.imprimirTablero();
            tablero.desteparCelda(tablero.entradaTablero());

        }while(!tablero.minadestepada);

        tablero.imprimirTablero();
        tablero.fiDelJoc();


    }



}

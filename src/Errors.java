import java.util.InputMismatchException;
import java.util.Scanner;

public class Errors extends Exception {

    private static Scanner sc = new Scanner(System.in);
    public static int esNecessitaNumero(){

        while(true) {
            String paraula = sc.nextLine();
            int num = 0;
            try {

                num = Integer.parseInt(paraula);

                return num;

            } catch (NumberFormatException e) {

                System.err.println("Has de posar un número");
            }
        }
    }
}

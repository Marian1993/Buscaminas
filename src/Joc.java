import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Joc {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[]args){

        Taulell taulell;

        do {

            taulell = new Taulell();
            System.out.println(Colores.ANSI_CYAN_BACKGROUND + "                                   " + Colores.ANSI_RESET);
            System.out.println(Colores.ANSI_CYAN_BACKGROUND + "                                   " + Colores.ANSI_RESET);
            System.out.println(Colores.ANSI_CYAN_BACKGROUND + "          " + Colores.ANSI_BLACK + "ᴘʀɪɴᴄɪᴘɪᴀɴᴛ ► 1" + "          " + Colores.ANSI_RESET);
            System.out.println(Colores.ANSI_CYAN_BACKGROUND + "          " + Colores.ANSI_BLACK + "ɪɴᴛᴇʀᴍᴇᴅɪ ► 2" + "            " + Colores.ANSI_RESET);
            System.out.println(Colores.ANSI_CYAN_BACKGROUND + "          " + Colores.ANSI_BLACK + "ᴇxᴘᴇʀᴛ ► 3" + "               " + Colores.ANSI_RESET);
            System.out.println(Colores.ANSI_CYAN_BACKGROUND + "          " + Colores.ANSI_BLACK + "ᴘᴇʀsᴏɴᴀʟɪᴛᴢᴀᴛ ► 0" + "        " + Colores.ANSI_RESET);
            System.out.println(Colores.ANSI_CYAN_BACKGROUND + "                                   " + Colores.ANSI_RESET);
            System.out.println(Colores.ANSI_CYAN_BACKGROUND + "                                   " + Colores.ANSI_RESET);
            System.out.println();
            System.out.print(Colores.ANSI_GREEN + "Elegueix una opció: " + Colores.ANSI_RESET);
            taulell.crearTaulell(Error.esNecessitaNumero());

            if (!taulell.partidaGuanyada()) {

                do {

                    taulell.imprimirTablero();
                    taulell.desteparCelda(taulell.entradaTablero());

                    if (taulell.partidaGuanyada()) {
                        break;
                    }

                } while (!taulell.minaDestepada);
            }

            taulell.imprimirTablero();

        }while (taulell.reiniciaJoc(taulell.fiDelJoc()));
    }
}

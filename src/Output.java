public class Output {

    //public Output(){}

    public void textDificultats(){

        System.out.println(Color.ANSI_CYAN_BACKGROUND + "                                   " + Color.ANSI_RESET);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + "                                   " + Color.ANSI_RESET);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + "          " + Color.ANSI_BLACK + "ᴘʀɪɴᴄɪᴘɪᴀɴᴛ ► 1" + "          " + Color.ANSI_RESET);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + "          " + Color.ANSI_BLACK + "ɪɴᴛᴇʀᴍᴇᴅɪ ► 2" + "            " + Color.ANSI_RESET);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + "          " + Color.ANSI_BLACK + "ᴇxᴘᴇʀᴛ ► 3" + "               " + Color.ANSI_RESET);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + "          " + Color.ANSI_BLACK + "ᴘᴇʀsᴏɴᴀʟɪᴛᴢᴀᴛ ► 0" + "        " + Color.ANSI_RESET);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + "                                   " + Color.ANSI_RESET);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + "                                   " + Color.ANSI_RESET);
        System.out.println();
        System.out.print(Color.ANSI_GREEN + "Elegueix una opció: " + Color.ANSI_RESET);
    }

    public void imprimirTaulell(Taulell taulell, Casella[][] casella){

        int contadorMines = 0;

        posarNumerosAdaltAbaix(taulell);

        for (int x = 0; x < taulell.getNumFiles(); x++) {

            System.out.print( x >= 10 ? x + " " : x + "  ");

            for (int y = 0; y < taulell.getNumColumnes() ; y++) {


                if(casella[x][y].isMines() && taulell.isMinaDestepada()){
                    System.out.print("×");
                }else if(casella[x][y].isBandera()){
                    System.out.print("¶");
                    contadorMines++;
                }else if(!casella[x][y].isVisible()) {
                    System.out.print("■");
                }else if (casella[x][y].getMinesVeines() == 0) {
                    System.out.print(" ");
                }else if(casella[x][y].getMinesVeines() < 9) {
                    Color.assignarColors(casella[x][y].getMinesVeines());
                }
                System.out.print("  ");

                if(y == taulell.getNumColumnes() - 1){
                    System.out.print(x);
                }

            }
            System.out.println();
        }
        taulell.setIntents(1);
        posarNumerosAdaltAbaix(taulell);
        if(contadorMines > taulell.getQuantitatMines()){
            System.out.println(Color.ANSI_RED + "Tens més banderes que de quantitat de mines d'es taulell!");
        }
    }
    private void posarNumerosAdaltAbaix(Taulell taulell){

        for (int i = 0; i < taulell.getNumColumnes(); i++) {
            if(i == 0){
                System.out.print("   " + i + "  ");
            } else if(i < 10){
                System.out.print(i + "  ");
            }else{
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public void fiDelJoc(boolean resultat, int intents){

        if(resultat){
            System.out.println("     _____               .---...-.");
            System.out.println("   ,'  -. `.           ,' _____...'");
            System.out.println("  /   - _ - \\         : .' _   _ \\\\");
            System.out.println(" :    ' _)'  :        | :-(_).(_)::");
            System.out.println("(_           ;)       | |    -'  ||");
            System.out.println("  \\     _   /         ; |    _   ||");
            System.out.println("   `..___..'          `-'..____.'`'");
            System.out.println("      ;._:                _; :_");
            System.out.println("     /    \\ Has perdut  ,'  `' `. Es un perdedor");

        }else {
            System.out.println("                       __");
            System.out.println("                     .'  '.");
            System.out.println("                 _.-'/  |  \\");
            System.out.println("    ,        _.-\"  ,|  /  0 `-.");
            System.out.println("    |\\    .-\"       `--\"\"-.__.'=====================-,");
            System.out.println("    \\ '-'`        .___.--._)=========================|");
            System.out.println("     \\             .'      |                         |");
            System.out.println("       |     /,_.-'        |                         |");
            System.out.println("     _/   _.'(             |       Has guanyat!      |");
            System.out.println("   /  ,-' \\  \\             |                         |");
            System.out.println("   \\  \\    `-'             |                         |");
            System.out.println("    `-'                    '-------------------------'");
            System.out.println();
            System.out.println("Ho has aconsegit amb " + intents + " intents");

        }
    }
}

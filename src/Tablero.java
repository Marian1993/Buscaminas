public class Tablero {

    private Casella[][] tablero;
    private int x;
    private int y;
    private int quantitatMines = 0;

    public Tablero(int x, int y) {
        this.tablero = new Casella[x][y];
    }


    public void inicialitzarTablero() {

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new Casella();
            }
        }
        mines();

    }

    public void mines() {


        while (quantitatMines < 10) {

            int x = (int) (Math.random() * 8);
            int y = (int) (Math.random() * 8);

            if (!tablero[x][y].isMina()) {
                tablero[x][y].setMina(true);

                quantitatMines++;
            }
        }
    }

    public void imprimirTablero() {

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                if (!tablero[i][j].isMina()) {
                    System.out.print(0);
                }
                if (tablero[i][j].isMina()) {
                    System.out.print('*');
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public void numeros() {

        for (int i =0 ; i < tablero.length; i++) {

            for (int j = 0; j < tablero[j].length; j++) {
                int numero = 0;

                if(i != 0){
                    if(tablero[i-1][j].isMina() ){
                        numero++;
                        tablero[i-1][j].setMinasCercanas(numero);
                        numero = 0;

                    }
                    if(tablero[i-1][j-1].isMina()) {
                        numero++;
                        numero = 0;
                    }
                    if(tablero[i-1][j+1].isMina()){
                        numero++;
                        numero = 0;
                    }
                }

                if(tablero[i][j-1].isMina()){
                    numero++;
                    numero = 0;
                }
                if(i == -1){

                    if(tablero[i+1][j-1].isMina()){
                        numero++;
                        numero = 0;
                    }if(tablero[i+1][j].isMina()){
                        numero++;
                        numero = 0;
                    }

                }
                if( i == -1 || j == -1){
                    if(tablero[i+1][j+1].isMina()){
                        numero++;
                        numero = 0;
                    }
                    if(tablero[i][j+1].isMina()) {
                        numero++;
                        numero = 0;
                    }
                }


            }
        }
    }
}
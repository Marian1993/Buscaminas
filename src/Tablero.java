public class Tablero{

    private Casella [][] tablero;
    private int x;
    private int y;
    private int quantitatMines = 0;

    public Tablero(int x, int y){
        this.tablero = new Casella[x][y];
    }


    public void inicialitzarTablero(){

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new Casella(false,false);
            }
        }
        mines();
    }

    public void mines() {

        while (quantitatMines < 10) {

            int x = (int) (Math.random() * 8);
            int horizontal = (int) (Math.random() * 8);

            if (!tablero[x][horizontal].isMina()) {
                tablero[x][horizontal] = new Casella(true, false);
                quantitatMines++;
            }
        }
    }
    public void imprimirTablero(){

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                if(!tablero[i][j].isMina()) {
                    System.out.print(0);
                }
                if (tablero[i][j].isMina()){
                    System.out.print('*');
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }
    public void numeros(){




    }
}

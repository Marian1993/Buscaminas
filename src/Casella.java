public class Casella {


    private boolean mina = false;
    private boolean bandera = false;
    private boolean visible = false;

    public Casella(boolean mina, boolean visible){

        this.mina = mina;
        this.visible = visible;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean buida) {
        this.visible = buida;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }
}

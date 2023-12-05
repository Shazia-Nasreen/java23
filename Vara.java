
public class Vara {
    private String namn;
    private double pris;
    private int lagerSaldo;

    public Vara(String namn, double pris, int lagerSaldo) {
        this.namn = namn;
        this.pris = pris;
        this.lagerSaldo = lagerSaldo;
    }

    public String getNamn() {
        return namn;
    }

    public double getPris() {
        return pris;
    }

    public int getLagerSaldo() {
        return lagerSaldo;
    }

    public void kopVara(int antal) {
        if (antal > 0 && antal <= lagerSaldo) {
            lagerSaldo -= antal;
        }
    }
}




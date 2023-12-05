public class RabatteradVara extends Vara {
    private double rabatt;

    public RabatteradVara(String namn, double pris, int lagerSaldo, double rabatt) {
        super(namn, pris, lagerSaldo);
        this.rabatt = rabatt;
    }

    @Override
    public double getPris() {
        // Beräkna och returnera det rabatterade priset
        return super.getPris() * (1 - rabatt);
    }
}


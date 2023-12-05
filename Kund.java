public class Kund {
    private int antalVarorKopta;
    private double totalKostnad;

    public Kund() {
        antalVarorKopta = 0;
        totalKostnad = 0.0;
    }

    public int getAntalVarorKopta() {
        return antalVarorKopta;
    }

    public double getTotalKostnad() {
        return totalKostnad;
    }

    public void kopVara(Vara vara, int antal) {
        if (antal > 0) {
            antalVarorKopta += antal;
            totalKostnad += vara.getPris() * antal;
        }
    }
}





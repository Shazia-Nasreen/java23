import java.util.Scanner;
public class ButikProgram {
    public static void main(String[] args) {
        Kund kund = new Kund();
        Vara vara1 = new Vara("Mask", 235.50, 14);//vara 1,vara 2 are variaables and rabatteradvara är också variable
        Vara vara2 = new Vara("Plastic Knife", 45.50, 3);// Vara och RabatteradVara är CONSTructors
        RabatteradVara rabatteradVara = new RabatteradVara("Fake Blood", 89.10, 44, 0.10);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("MAIN MENY:");
            System.out.println("0. " + vara1.getNamn() + " - Pris: " + vara1.getPris() + " kr - Lager Saldo: " + vara1.getLagerSaldo());
            System.out.println("1. " + vara2.getNamn() + " - Pris: " + vara2.getPris() + " kr - Lager Saldo: " + vara2.getLagerSaldo());
            System.out.println("2. " + rabatteradVara.getNamn() + " - Pris: " + rabatteradVara.getPris() + " kr - Lager Saldo: " + rabatteradVara.getLagerSaldo());
            System.out.println("Ange varunummer du vill köpa (eller 4 för att avsluta): ");
            int varunummer = scanner.nextInt();

            if (varunummer == 4) {// exit with nummer 4
                break;
            }

            Vara valdVara = null;// new variable is assigned to get value from customer and to keep uppdation

            if (varunummer == 0) {
                valdVara = vara1;
            } else if (varunummer == 1) {
                valdVara = vara2;
            } else if (varunummer == 2) {
                valdVara = rabatteradVara;
            } else {
                System.out.println("Ogiltigt varunumbmer.");
                continue;
            }

            System.out.println("Ange antal  hur många du vill köpa: ");
            int antal = scanner.nextInt();

            if (antal <= 0) {
                System.out.println("Ogilltigt antal.");
                continue;
            }

            if (antal > valdVara.getLagerSaldo()) {
                System.out.println("Inte tillräckligt med lager " + valdVara.getNamn() + ".");
                continue;
            }

            valdVara.kopVara(antal);
            kund.kopVara(valdVara, antal);

            System.out.println("Du har  köpt " + antal + " " + valdVara.getNamn() + "(s).");
            System.out.println("Total kostnad: " + kund.getTotalKostnad() + " kr");
        }

        System.out.println("Tack för att du handlade hos oss! välkommenåter");
        System.out.println("Totalt antal köpta varor: " + kund.getAntalVarorKopta());
    }
}
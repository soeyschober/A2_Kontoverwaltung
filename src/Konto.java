import java.util.concurrent.atomic.AtomicLong;

public class Konto {

    private String kontoinhaber;
    private int bankleitzahl;
    private String kontonummer;
    private double kontostand;
    private String kontoart;

    public Konto(String kontoinhaber, int bankleitzahl, String kontonummer, double kontostand, String kontoart) {
        this.kontoinhaber = kontoinhaber;
        this.bankleitzahl = bankleitzahl;
        this.kontonummer = kontonummer;
        this.kontostand = kontostand;
        this.kontoart = kontoart;
    }

    @Override
    public String toString() {
        return "Kontoinhaber: " + kontoinhaber +
                "\nBankleitzahl: " + bankleitzahl +
                "\nKontonummer: " + kontonummer +
                "\nKontostand: " + kontostand +
                "\nKontoart: " + kontoart;
    }

    public static void kontoAnlegen(String kontoinhaber, String kontoart){
        final AtomicLong counter = new AtomicLong(10_000_000);
        String kontonummer = String.format("%08d", counter.getAndIncrement());

        switch(kontoart){
            case "Girokonto":
                Girokonto gk1 = new Girokonto(kontoinhaber, kontonummer);
                System.out.println("\nIhr neues Girokonto wurde angelegt!");
                System.out.printf("-------------------------------------------------\n" +
                        gk1.toString() +
                        "\n-------------------------------------------------\n\n");
            break;
        }
    }

    public double einzahlen(){
        return kontostand;
    }

    //#region Getter und Setter
    public String getKontoinhaber() {
        return kontoinhaber;
    }

    public void setKontoinhaber(String kontoinhaber) {
        this.kontoinhaber = kontoinhaber;
    }

    public int getBankleitzahl() {
        return bankleitzahl;
    }

    public void setBankleitzahl(int bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public double getKontostand() {
        return kontostand;
    }

    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    public String getKontoart() {
        return kontoart;
    }

    public void setKontoart(String kontoart) {
        this.kontoart = kontoart;
    }

    //endregion
}

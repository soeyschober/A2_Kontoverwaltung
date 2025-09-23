public abstract class Konto {
    private String kontoinhaber;
    private int bankleitzahl;
    private String kontonummer;
    public double kontostand;
    private String kontoart;

    public Konto(String kontoinhaber, int bankleitzahl, String kontonummer, double startguthaben, String kontoart) {
        this.kontoinhaber = kontoinhaber;
        this.bankleitzahl = bankleitzahl;
        this.kontonummer = kontonummer;
        this.kontostand = startguthaben;
        this.kontoart = kontoart;
    }

    public void einzahlen(double betrag) {
        if (betrag > 0) {
            kontostand += betrag;
        } else  {
            System.out.println("Invalider Betrag");
        }
    }

    public boolean abheben(double betrag) {
        if (betrag > 0 && darfAbheben(betrag)) {
            kontostand -= betrag;
            return true;
        }else {
            return false;
        }
    }

    public boolean ueberweisen(Konto ziel, double betrag) {
        if (ziel == null) return false;
        if (abheben(betrag)) {
            ziel.einzahlen(betrag);
            return true;
        }
        return false;
    }


    public String kontoauszug() {
        return "---- Kontoauszug ----" +
                "Inhaber: " + kontoinhaber +
                "Kontoart: " + kontoart +
                "Bankleitzahl: " + bankleitzahl +
                "Kontonummer: " + kontonummer +
                "Kontostand: " + kontostand +
                "---------------------";
    }

    public abstract boolean darfAbheben(double betrag);

    public String getKontonummer() {
        return kontonummer;
    }

    public double getKontostand() {
        return kontostand;
    }
}

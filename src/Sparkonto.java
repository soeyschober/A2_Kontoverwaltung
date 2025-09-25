public class Sparkonto extends Konto {
    public Sparkonto(String kontoinhaber, int bankleitzahl, int kontonummer, double startguthaben) {
        super(kontoinhaber, bankleitzahl, kontonummer, startguthaben, "Sparkonto");
    }

    @Override
    public boolean darfAbheben(double betrag) {
        return (getKontostand() - betrag) >= 0;
    }
}

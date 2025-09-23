public class Sparkonto extends Konto {
    public Sparkonto(String kontoinhaber, int bankleitzahl, String kontonummer, double startguthaben) {
        super(kontoinhaber, bankleitzahl, kontonummer, startguthaben, "Sparkonto");
    }

    @Override
    public boolean darfAbheben(double betrag) {
        return (getKontostand() - betrag) >= 0;
    }
}

public class Girokonto extends Konto {

    private double ueberziehungsrahmen = 500;

    public Girokonto(String kontoinhaber, int bankleitzahl, String kontonummer, double startguthaben, double ueberziehungsrahmen) {
        super(kontoinhaber, bankleitzahl, kontonummer, startguthaben, "Girokonto");
        this.ueberziehungsrahmen = ueberziehungsrahmen;
    }

    @Override
    public boolean darfAbheben(double betrag) {
        return (getKontostand() - betrag) >= -ueberziehungsrahmen;
    }
}

public class Kreditkonto extends Konto {
    private double kreditlimit = 5000;

    public Kreditkonto(String kontoinhaber, int bankleitzahl, String kontonummer, double startsaldo, double kreditlimit) {
        super(kontoinhaber, bankleitzahl, kontonummer, startsaldo, "Kreditkonto");
        this.kreditlimit = kreditlimit;
    }

    @Override
    public boolean darfAbheben(double betrag) {
        return (getKontostand() - betrag) >= -kreditlimit;
    }
}

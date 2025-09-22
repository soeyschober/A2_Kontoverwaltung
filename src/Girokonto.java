public class Girokonto extends Konto{

    private int ueberziehungsrahmen;

    public Girokonto(String kontoinhaber, String kontonummer) {
        super(kontoinhaber, 10, kontonummer, 0, "Girokonto");
        this.ueberziehungsrahmen = 500;
    }
}

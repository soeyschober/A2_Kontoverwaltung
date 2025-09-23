import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Konto> konten = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int wahl = readInt("Auswahl: ");

            switch (wahl) {
                case 1:
                    kontoAnlegen();
                    break;
                case 2:
                    kontoAufloesen();
                    break;
                case 3:
                    einzahlen();
                    break;
                case 4:
                    abheben();
                    break;
                case 5:
                    kontoauszug();
                    break;
                case 6:
                    ueberweisen();
                    break;
                case 7:
                    alleKontenAnzeigen();
                    break;
                case 0:
                    System.out.println("Ciao. Programm beendet.");
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Kontoverwaltung =====");
        System.out.println("1 - Konto anlegen");
        System.out.println("2 - Konto auflösen");
        System.out.println("3 - Einzahlen");
        System.out.println("4 - Abheben");
        System.out.println("5 - Kontoauszug");
        System.out.println("6 - Überweisen");
        System.out.println("7 - Alle Konten anzeigen");
        System.out.println("0 - Beenden");
    }

    private static void kontoAnlegen() {
        System.out.println("Kontoart: 1=Giro, 2=Sparkonto, 3=Kreditkonto");
        int art = readInt("Art: ");
        String inhaber = readString("Kontoinhaber: ");
        int blz = readInt("Bankleitzahl: ");
        String kto = readString("Kontonummer: ");
        double start = readDouble("Startguthaben: ");

        Konto konto;

        if (art == 1) {
            double rahmen = readDouble("Überziehungsrahmen: ");
            konto = new Girokonto(inhaber, blz, kto, start, rahmen);
        } else if (art == 2) {
            konto = new Sparkonto(inhaber, blz, kto, start);
        } else if (art == 3) {
            double limit = readDouble("Kreditlimit: ");
            konto = new Kreditkonto(inhaber, blz, kto, start, limit);
        } else {
            System.out.println("Unbekannte Kontoart.");
            return;
        }

        konten.add(konto);
        System.out.println("Konto angelegt.");
        System.out.println(konto.kontoauszug());
    }

    private static void kontoAufloesen() {
        String nr = readString("Kontonummer zum Löschen: ");
        Konto k = findeKonto(nr);
        if (k == null) {
            System.out.println("Konto nicht gefunden.");
            return;
        }
        konten.remove(k);
        System.out.println("Konto entfernt.");
    }

    private static void einzahlen() {
        Konto k = findeKonto(readString("Kontonummer: "));
        if (k == null) {
            System.out.println("Konto nicht gefunden.");
            return;
        }
        double betrag = readDouble("Betrag zum Einzahlen: ");
        k.einzahlen(betrag);
        System.out.println("Neuer Stand: " + k.getKontostand());
    }

    private static void abheben() {
        Konto k = findeKonto(readString("Kontonummer: "));
        if (k == null) {
            System.out.println("Konto nicht gefunden.");
            return;
        }
        double betrag = readDouble("Betrag zum Abheben: ");
        boolean ok = k.abheben(betrag);
        if (ok) {
            System.out.println("Auszahlung erfolgt. Neuer Stand: " + k.getKontostand());
        } else {
            System.out.println("Abhebung nicht erlaubt.");
        }
    }

    private static void kontoauszug() {
        Konto k = findeKonto(readString("Kontonummer: "));
        if (k == null) {
            System.out.println("Konto nicht gefunden.");
            return;
        }
        System.out.println(k.kontoauszug());
    }

    private static void ueberweisen() {
        Konto von = findeKonto(readString("Von Kontonummer: "));
        if (von == null) {
            System.out.println("Quellkonto nicht gefunden.");
            return;
        }
        Konto nach = findeKonto(readString("An Kontonummer: "));
        if (nach == null) {
            System.out.println("Zielkonto nicht gefunden.");
            return;
        }
        double betrag = readDouble("Betrag zum Überweisen: ");
        boolean ok = von.ueberweisen(nach, betrag);
        if (ok) {
            System.out.println("Überweisung ausgeführt.");
        } else {
            System.out.println("Überweisung fehlgeschlagen.");
        }
    }

    private static void alleKontenAnzeigen() {
        if (konten.isEmpty()) {
            System.out.println("Keine Konten vorhanden.");
            return;
        }
        for (Konto k : konten) {
            System.out.println(k.kontoauszug());
        }
    }

    private static Konto findeKonto(String kontonummer) {
        for (Konto k : konten) {
            if (k.getKontonummer().equals(kontonummer)) {
                return k;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine ganze Zahl eingeben.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine Zahl eingeben.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}

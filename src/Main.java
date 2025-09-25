import java.util.*;
import javax.swing.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    static final List<Konto> konten = new ArrayList<>();

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Kontoverwaltung");
            Kontoverwaltung ui = new Kontoverwaltung();
            frame.setContentPane(ui.getOverviewPanel());
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static String kontoAnlegenFromUI(String art, String inhaber, int blz, int ktn, double start) {
        Konto neu;
        String a = art == null ? "" : art.trim().toLowerCase(Locale.ROOT);

        if (a.startsWith("giro")) {
            neu = new Girokonto(inhaber, blz, ktn, start, 500);
        } else if (a.startsWith("spar")) {
            neu = new Sparkonto(inhaber, blz, ktn, start);
        } else if (a.startsWith("kredit")) {
            neu = new Kreditkonto(inhaber, blz, ktn, start, 5000);
        } else {
            return "Unbekannte Kontoart: " + art;
        }

        konten.add(neu);

        return "Konto angelegt";
    }

    private static void kontoAnlegen() {
        System.out.println("Kontoart: 1=Giro, 2=Sparkonto, 3=Kreditkonto");
        int art = readInt("Art: ");
        String inhaber = readString("Kontoinhaber: ");
        int blz = readInt("Bankleitzahl: ");
        int kto = readInt("Kontonummer: ");
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
        int nr = readInt("Kontonummer zum Löschen: ");
        Konto k = findeKonto(nr);
        if (k == null) {
            System.out.println("Konto nicht gefunden.");
            return;
        }
        konten.remove(k);
        System.out.println("Konto entfernt.");
    }

    private static void einzahlen() {
        Konto k = findeKonto(readInt("Kontonummer: "));
        if (k == null) {
            System.out.println("Konto nicht gefunden.");
            return;
        }
        double betrag = readDouble("Betrag zum Einzahlen: ");
        k.einzahlen(betrag);
        System.out.println("Neuer Stand: " + k.getKontostand());
    }

    private static void abheben() {
        Konto k = findeKonto(readInt("Kontonummer: "));
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
        Konto k = findeKonto(readInt("Kontonummer: "));
        if (k == null) {
            System.out.println("Konto nicht gefunden.");
            return;
        }
        System.out.println(k.kontoauszug());
    }

    private static void ueberweisen() {
        Konto von = findeKonto(readInt("Von Kontonummer: "));
        if (von == null) {
            System.out.println("Quellkonto nicht gefunden.");
            return;
        }
        Konto nach = findeKonto(readInt("An Kontonummer: "));
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

    private static Konto findeKonto(int kontonummer) {
        for (Konto k : konten) {
            if (k.getKontonummer() == kontonummer) {
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

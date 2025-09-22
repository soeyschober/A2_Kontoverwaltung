import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int kontonummer = 0;

        while (!exit) {
            try {
                System.out.printf("Welche Aktion moechten Sie durchfuehren?" +
                        "\n1 - Konto anlegen" +
                        "\n2 - einzahlen" +
                        "\n3 - abheben" +
                        "\n4 - Kontoauszug" +
                        "\n5 - Konto aufloesen" +
                        "\n0 - Programm beenden");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        ++kontonummer;
                        System.out.println("\nKonto wird angelegt...");
                        System.out.println("Bitte geben Sie den Namen des Kontoinhabers an:   (Vorname Nachname)");
                        String kontoinhaber = scanner.nextLine();
                        System.out.printf("Waehlen Sie eine Kontoart:" +
                                "\n1 - Girokonto" +
                                "\n0 - Konto anlegen beenden");

                        boolean kontoartExit = false;
                        while (!kontoartExit) {
                            try {
                                int kontoartChoice = scanner.nextInt();

                                if (kontoartChoice == 0) {
                                    kontoartExit = true;
                                } else if (kontoartChoice == 1) {
                                    kontoartExit = true;
                                    String kontoart = "Girokonto";
                                    //Konto.kontoAnlegen(kontoinhaber, kontonummer, kontoart);
                                } else {
                                    System.out.println("Bitte waehlen sie eine der oben genannten Moeglichkeiten: (0-1)");
                                }
                                } catch(Exception e) {
                                    System.out.println("\nThere was a problem during the input process, try again");
                                    scanner.nextLine();
                                }
                            }
                        break;

                    case 2:
                        System.out.println("Betrag einzahlen...");
                        // einzahlen Logik
                        break;

                    case 3:
                        System.out.println("Betrag abheben...");
                        // abheben Logik
                        break;

                    case 4:
                        System.out.println("Kontoauszug wird ausgegeben...");
                        // Kontoauszug Logik
                        break;

                    case 5:
                        System.out.println("Konto wird aufgeloest...");
                        // Konto aufloesen Logik
                        break;

                    case 0:
                        System.out.println("Programm wird beendet...");
                        break;

                    default:
                        System.out.println("Ungueltige Eingabe!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("\nThere was a problem during the input process, try again");
                scanner.nextLine();
            }
        }
    }
}

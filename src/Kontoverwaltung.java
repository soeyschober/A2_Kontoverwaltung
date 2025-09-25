import javax.swing.*;
import java.awt.*;

public class Kontoverwaltung {
    private JButton anlegenBtn;
    private JButton aufloesenBtn;
    private JButton einzahlnBtn;
    private JButton auszahlenBtn;
    private JButton kontoauszugBtn;
    private JButton ueberweisenBtn;
    private JButton anzeigenBtn;
    private JButton beendenBtn;
    private JLabel fehlerLbl;
    private JPanel overviewPnl;
    private JComboBox<Konto> aktuellCBox;
    private JTextArea aktuellArea;
    private JTextArea outputTxt;
    private JScrollPane outputSPnl;

    public Kontoverwaltung() {
        if (outputTxt != null) {
            outputTxt.setEditable(false);
            outputTxt.setLineWrap(true);
            outputTxt.setWrapStyleWord(true);
        }

        if (anlegenBtn != null) {
            anlegenBtn.addActionListener(e -> onAnlegen());
        }

        initCombo();

        attachUnavailable(aufloesenBtn, "Auflösen");
        attachUnavailable(einzahlnBtn, "Einzahlen");
        attachUnavailable(auszahlenBtn, "Auszahlen");
        attachUnavailable(kontoauszugBtn, "Kontoauszug");
        attachUnavailable(ueberweisenBtn, "Überweisen");

        if (anzeigenBtn != null) {
            anzeigenBtn.addActionListener(e -> onAnzeigen());
        }
        if (beendenBtn != null) {
            beendenBtn.addActionListener(e -> System.exit(0));
        }
    }


    private void initCombo() {
        if (aktuellCBox == null) return;

        refreshComboModel();

        aktuellCBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Konto) {
                    lbl.setText(formatComboText((Konto) value));
                } else if (value == null) {
                    lbl.setText("—");
                }
                return lbl;
            }
        });

        // Auswahl -> Kontoauszug in Area
        aktuellCBox.addActionListener(e -> {
            clearError();
            Konto sel = getSelectedKonto();
            if (sel == null) {
                setAreaText("Kein Konto ausgewählt.");
            } else {
                setAreaText(buildKontoauszug(sel));
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void refreshComboModel() {
        if (aktuellCBox == null) return;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Konto k : Main.konten) model.addElement(k);
        aktuellCBox.setModel(model);

        if (model.getSize() > 0) {
            aktuellCBox.setSelectedIndex(0);
            setAreaText(buildKontoauszug((Konto) model.getElementAt(0)));
        } else {
            setAreaText("Noch keine Konten vorhanden.");
        }
    }

    private Konto getSelectedKonto() {
        if (aktuellCBox == null) return null;
        Object val = aktuellCBox.getSelectedItem();
        return (val instanceof Konto) ? (Konto) val : null;
    }

    private String formatComboText(Konto k) {
        String name = k != null && k.getKontoinhaber() != null ? k.getKontoinhaber() : "Unbekannt";
        String nr   = "—";
        try {
            nr = k != null ? String.valueOf(k.getKontonummer()) : "—";
        } catch (Throwable ignored) { /* falls Getter fehlt/knallt */ }
        return name + " – " + nr;
    }

    private String buildKontoauszug(Konto k) {
        String name = k.getKontoinhaber() != null ? k.getKontoinhaber() : "Unbekannt";
        String nr   = "—";
        String blz  = "—";
        String art  = "Konto";
        String stand= "0.00";

        try { nr    = String.valueOf(k.getKontonummer()); } catch (Throwable ignored) {}
        try { blz   = String.valueOf(k.getBankleitzahl()); } catch (Throwable ignored) {}
        try { art   = k.getKontoart() != null ? k.getKontoart() : "Konto"; } catch (Throwable ignored) {}
        try { stand = String.format("%.2f", k.getKontostand()); } catch (Throwable ignored) {}

        String ls = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("Inhaber:    ").append(name).append(ls)
                .append("Konto-Nr.:  ").append(nr).append(ls)
                .append("BLZ:        ").append(blz).append(ls)
                .append("Kontoart:   ").append(art).append(ls)
                .append("Kontostand: ").append(stand).append(" €");
        return sb.toString();
    }

    private void setAreaText(String text) {
        if (aktuellArea == null) return;
        SwingUtilities.invokeLater(() -> {
            aktuellArea.setText(text == null ? "" : text);
            aktuellArea.setCaretPosition(0);
        });
    }


    private void onAnlegen() {
        Frame owner = JOptionPane.getFrameForComponent(overviewPnl);
        KontoAnlegenWin dlg = new KontoAnlegenWin(owner);
        dlg.setVisible(true);

        Konto k = dlg.getResult();
        if (k != null) {
            Main.konten.add(k);
            refreshComboModel();
            clearError();
            printOutput("✅ Konto angelegt und gespeichert: \n" + k.getKontoinhaber() + "@" + k.getKontonummer()
                    + "\nGesamt: " + Main.konten.size() + " Konto(s).\n");
        } else {
            showError("Anlegen abgebrochen.");
        }
    }

    private void onAnzeigen() {
        clearError();
        if (Main.konten.isEmpty()) {
            showError("Keine Konten vorhanden.");
            return;
        }
        printOutput("Vorhandene Konten:");
        for (Konto k : Main.konten) {
            printOutput(k.toString());
        }
    }

    private void attachUnavailable(JButton btn, String feature) {
        if (btn == null) return;
        btn.addActionListener(e -> {
            clearError();
            showError(feature + " ist in der GUI noch nicht verfügbar.");
        });
    }

    public JPanel getOverviewPanel() {
        return overviewPnl;
    }

    private void printOutput(String text) {
        if (outputTxt == null) return;
        SwingUtilities.invokeLater(() -> {
            if (outputTxt.getDocument().getLength() > 0) {
                outputTxt.append(System.lineSeparator());
            }
            outputTxt.append(text);
            outputTxt.setCaretPosition(outputTxt.getDocument().getLength());
        });
    }

    private void showError(String message) {
        if (fehlerLbl == null) return;
        SwingUtilities.invokeLater(() -> fehlerLbl.setText(message == null ? "" : message));
    }

    private void clearError() {
        showError("");
    }
}

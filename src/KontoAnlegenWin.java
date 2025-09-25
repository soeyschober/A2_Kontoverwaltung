import javax.swing.*;
import java.awt.*;

public class KontoAnlegenWin  extends JDialog {
    private JComboBox artCBox;
    private JTextField inhaberTBox;
    private JTextField blzTBox;
    private JTextField ktnTBox;
    private JTextField startTBox;
    private JLabel artLbl;
    private JLabel inhaberLbl;
    private JLabel blzLbl;
    private JLabel ktnLbl;
    private JLabel startLbl;
    private JPanel anlegenOverviewPnl;
    private JButton okBtn;
    private JButton beendenBtn;

    // hold the result
    private Konto result;

    public KontoAnlegenWin(Frame owner) {
        super(owner, "Konto anlegen", true);
        setContentPane(anlegenOverviewPnl);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(owner);

        okBtn.addActionListener(e -> {
            try {
                result = buildKontoFromInputs();
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Eingabefehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        if (beendenBtn != null) {
            beendenBtn.addActionListener(e -> {
                result = null;
                dispose();
            });
        }

        if (artCBox != null && artCBox.getItemCount() == 0) {
            artCBox.addItem("Girokonto");
            artCBox.addItem("Sparkonto");
            artCBox.addItem("Kreditkonto");
        }
    }

    public Konto getResult() {
        return result;
    }

    private Konto buildKontoFromInputs() {
        final String inhaber = getInhaber();
        final int blz = getBlz();
        final int ktn = getKtn();
        final double start = getStart();

        if (inhaber == null || inhaber.isBlank()) {
            throw new IllegalArgumentException("Bitte Kontoinhaber angeben.");
        }
        if (blz <= 0) {
            throw new IllegalArgumentException("Bitte gültige BLZ angeben.");
        }
        if (ktn == 0) {
            throw new IllegalArgumentException("Bitte gültige Kontonummer angeben.");
        }

        final String art = getArt();
        switch (art) {
            case "Girokonto":
                return new Girokonto(inhaber, blz, ktn, start, 500);
            case "Sparkonto":
                return new Sparkonto(inhaber, blz, ktn, start);
            case "Kreditkonto":
                return new Kreditkonto(inhaber, blz, ktn, start, 5000);
            default:
                throw new IllegalArgumentException("Unbekannte Kontoart: " + art);
        }
    }

    public String getArt() {
        return artCBox == null ? "" : String.valueOf(artCBox.getSelectedItem());
    }

    public String getInhaber() {
        return inhaberTBox == null ? "" : inhaberTBox.getText().trim();
    }

    public int getBlz() {
        try {
            return blzTBox == null ? 0 : Integer.parseInt(blzTBox.getText().trim());
        } catch (Exception ignored) { return 0; }
    }

    public int getKtn() {
        return ktnTBox == null ? 0 : Integer.parseInt(ktnTBox.getText().trim());
    }

    public double getStart() {
        try {
            return startTBox == null ? 0.0 : Double.parseDouble(startTBox.getText().trim().replace(',', '.'));
        } catch (Exception ignored) { return 0.0; }
    }
}

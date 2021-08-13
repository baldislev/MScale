import javax.swing.*;
import java.awt.*;

/**This class defines the frame that will contain all view elements of the application**/
class MScaleFrame extends JFrame {
    static final int FRAME_WIDTH = EnumPanel.STR_ENUM_WIDTH + Instrument.INSTR_WIDTH;
    static Instrument instrument;
    private JPanel mainPanel;
    private FretEnumPanel fretEnumPanel;

    /**@param strTune  the tune for instrument initialization**/
    MScaleFrame(String strTune) throws MScaleException {
        super();
        this.setTitle("MScale");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        BorderLayout bLayout = new BorderLayout(1,1);
        this.setLayout(bLayout);

        instrument = new Instrument(strTune);
        /*MAINPANEL: consists of instrument panel with attached to the left string enumeration panel*/
        mainPanel = new JPanel();
        StrEnumPanel enumPanel = new StrEnumPanel(EnumPanel.STR_ENUM_WIDTH,
                instrument.getInstrHeight(), instrument.getNumOfStr());
        mainPanel.add(enumPanel);
        mainPanel.add(instrument);

        /*MENUPANEL: consists of buttons to manipulate settings*/
        MenuPanel menu = new MenuPanel();

        /*FRETPANEL: for fret enumeration*/
        fretEnumPanel = new FretEnumPanel(EnumPanel.FRET_ENUM_WIDTH,
                EnumPanel.FRET_ENUM_HEIGHT);

        this.add(menu, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(fretEnumPanel, BorderLayout.SOUTH);

        this.pack();
    }

    /**Will render a new version of an instrument according to the new Tune by removing the
     * old instrument from the frame and inserting a new one with the corresponding enumeration panels.**/
    void retune(String strTune) throws MScaleException {
        this.getContentPane().remove(mainPanel);
        this.getContentPane().remove(fretEnumPanel);
        this.getContentPane().invalidate();

        instrument = new Instrument(strTune);
        /*MAINPANEL: consists of instrument panel with attached to the left string enumeration panel*/
        mainPanel = new JPanel();
        StrEnumPanel enumPanel = new StrEnumPanel(EnumPanel.STR_ENUM_WIDTH,
                instrument.getInstrHeight(), instrument.getNumOfStr());
        mainPanel.add(enumPanel);
        mainPanel.add(instrument);

        /*FRETPANEL: for fret enumeration*/
        fretEnumPanel = new FretEnumPanel(EnumPanel.FRET_ENUM_WIDTH,
                EnumPanel.FRET_ENUM_HEIGHT);

        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.getContentPane().add(fretEnumPanel, BorderLayout.SOUTH);

        this.pack();
        this.getContentPane().validate();
        this.getContentPane().repaint();
    }
}

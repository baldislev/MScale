import javax.swing.*;
import java.awt.*;

/**This class defines the frame that will contain all view elements of the application**/
class MScaleFrame extends JFrame {
    static final int FRAME_WIDTH = EnumPanel.STR_ENUM_WIDTH + Guitar.GUITAR_WIDTH;
    static Guitar guitar;

    /**@param strTune  the tune for guitar initialization**/
    MScaleFrame(String[] strTune) throws MScaleException {
        super();
        this.setTitle("MScale");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        BorderLayout bLayout = new BorderLayout(1,1);
        this.setLayout(bLayout);

        /*MAINPANEL: consists of guitar panel with attached to the left string enumeration panel*/
        JPanel mainPanel = new JPanel();
        StrEnumPanel enumPanel = new StrEnumPanel(EnumPanel.STR_ENUM_WIDTH,
                EnumPanel.STR_ENUM_HEIGHT, EnumPanel.STR_ENUM_COLOR);
        guitar = new Guitar(strTune);
        mainPanel.add(enumPanel);
        mainPanel.add(guitar);

        /*MENUPANEL: consists of buttons to manipulate settings*/
        MenuPanel menu = new MenuPanel();

        /*FRETPANEL: for fret enumeration*/
        FretEnumPanel fretEnumPanel = new FretEnumPanel(EnumPanel.FRET_ENUM_WIDTH,
                EnumPanel.FRET_ENUM_HEIGHT, EnumPanel.FRET_ENUM_COLOR);

        this.add(menu, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(fretEnumPanel, BorderLayout.SOUTH);

        this.pack();
    }
}

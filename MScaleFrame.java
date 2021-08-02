import javax.swing.*;
import java.awt.*;

class MScaleFrame extends JFrame {
    static final int FRAME_WIDTH = EnumPanel.STR_ENUM_WIDTH + Guitar.GUITAR_WIDTH;

    static Guitar guitar;

    MScaleFrame(String[] strTune) throws Exception {
        super();
        this.setTitle("just another humble effort");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        //MAINPANEL:
        JPanel mainPanel = new JPanel();
        StrEnumPanel enumPanel = new StrEnumPanel(EnumPanel.STR_ENUM_WIDTH,
                EnumPanel.STR_ENUM_HEIGHT, EnumPanel.STR_ENUM_COLOR);
        guitar = new Guitar(strTune);
        mainPanel.add(enumPanel);
        mainPanel.add(guitar);

        //MENUPANEL:
        MenuPanel menu = new MenuPanel();

        //FRET_ENUM:
        FretEnumPanel fretEnumPanel = new FretEnumPanel(EnumPanel.FRET_ENUM_WIDTH,
                EnumPanel.FRET_ENUM_HEIGHT, EnumPanel.FRET_ENUM_COLOR);
        this.add(menu, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(fretEnumPanel, BorderLayout.SOUTH);

        this.pack();
    }
}

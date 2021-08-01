import javax.swing.*;
import java.awt.*;

public class MScaleFrame extends JFrame {
    static final int FRAME_WIDTH = EnumPanel.ENUM_WIDTH + Guitar.GUITAR_WIDTH;

    static Guitar guitar;

    MScaleFrame(String[] strTune){
        super();
        this.setTitle("just another humble effort");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        //MAINPANEL:
        JPanel mainPanel = new JPanel();
        EnumPanel enumPanel = new EnumPanel();
        guitar = new Guitar(strTune);
        mainPanel.add(enumPanel);
        mainPanel.add(guitar);

        //MENUPANEL:
        MenuPanel menu = new MenuPanel();

        //FRET_ENUM:
        FretEnumPanel fretEnumPanel = new FretEnumPanel();
        this.add(menu, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(fretEnumPanel, BorderLayout.SOUTH);

        this.pack();
    }
}

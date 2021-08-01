import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EnumPanel extends JPanel {
    static final int ENUM_WIDTH = Guitar.FRET_PAD;
    static final int ENUM_HEIGHT = Guitar.GUITAR_HEIGHT;
    static final Dimension ENUM_SIZE = new Dimension(ENUM_WIDTH, ENUM_HEIGHT);

    ArrayList<PressLabel> enums;

    EnumPanel(){
        super();
        this.setPreferredSize(ENUM_SIZE);
        this.setLayout(new GridLayout());
        this.setBackground(Color.darkGray);
        enums = new ArrayList<>();
        PressLabel label;
        int y = Guitar.EXTERNAL_STRING_PAD + 3;
        for(int i = 0; i < Guitar.NUM_OF_STRINGS; i++){
            label = new PressLabel(ENUM_WIDTH/2 - 5, y, i, Integer.toString(i+1));
            y += Guitar.INTERNAL_STRING_PAD;
            enums.add(label);
            this.add(label);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if(!enums.isEmpty()) {
            for (PressLabel anEnum : enums) {
                anEnum.drawLabel(g);
            }
        }
    }

}

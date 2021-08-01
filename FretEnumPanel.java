import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FretEnumPanel extends JPanel {
    static final int ENUM_WIDTH = MScaleFrame.FRAME_WIDTH;
    static final int ENUM_HEIGHT = Guitar.INTERNAL_STRING_PAD;
    static final Dimension ENUM_SIZE = new Dimension(ENUM_WIDTH, ENUM_HEIGHT);

    ArrayList<PressLabel> enums;

    FretEnumPanel(){
        super();
        this.setPreferredSize(ENUM_SIZE);
        this.setLayout(new GridLayout());
        this.setBackground(new Color(157, 126, 104));
        enums = new ArrayList<>();
        PressLabel label;
        int y = Guitar.EXTERNAL_STRING_PAD + 3;
        int x = EnumPanel.ENUM_WIDTH + Guitar.FRET_WIDTH + Guitar.FRET_PAD/2;
        for(int i = 0; i < Guitar.NUM_OF_FRETS; i++){
            if(x>=10) x-=5;
            label = new PressLabel(x, y, i, Integer.toString(i));
            if(x>=10) x+=5;
            x += Guitar.FRET_PAD + Guitar.FRET_WIDTH;
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

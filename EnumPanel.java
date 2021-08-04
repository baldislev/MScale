import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**Base class for panels that will contain enumeration labels: frets, strings and maybe other things.
 * @enumLabels will hold all drawable string objects that are needed to be displayed**/
public class EnumPanel extends JPanel {
    static final int FRET_ENUM_WIDTH = MScaleFrame.FRAME_WIDTH;
    static final int FRET_ENUM_HEIGHT = Guitar.INTERNAL_STRING_PAD;
    static final int STR_ENUM_WIDTH = Guitar.FRET_PAD;
    static final int STR_ENUM_HEIGHT = Guitar.GUITAR_HEIGHT;
    static final Color FRET_ENUM_COLOR = new Color(157, 126, 104);
    static final Color STR_ENUM_COLOR = new Color(153, 153, 102);

    ArrayList<StrLabel> enumLabels;

    EnumPanel(int width, int height, Color color){
        super();
        Dimension dim = new Dimension(width, height);
        this.setPreferredSize(dim);
        this.setBackground(color);
        enumLabels = new ArrayList<>();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if(!enumLabels.isEmpty()) {
            for (StrLabel anEnum : enumLabels) {
                anEnum.drawLabel(g);
            }
        }
    }

}

class FretEnumPanel extends EnumPanel {
    /**Derived class for fret enumeration**/
    FretEnumPanel(int width, int height, Color color){
        super(width, height, color);
        int y = Guitar.EXTERNAL_STRING_PAD;
        int x = EnumPanel.STR_ENUM_WIDTH + 2*Guitar.FRET_WIDTH + Guitar.FRET_PAD/2;

        StrLabel label;
        for(int i = 0; i < Guitar.NUM_OF_FRETS; i++){
            label = new StrLabel(x, y, Integer.toString(i));
            x += Guitar.FRET_PAD + Guitar.FRET_WIDTH;
            enumLabels.add(label);
            this.add(label);
        }
    }
}

class StrEnumPanel extends EnumPanel {
    /**Derived class for string enumeration**/
    StrEnumPanel(int width, int height, Color color){
        super(width, height, color);
        StrLabel label;
        int y = Guitar.EXTERNAL_STRING_PAD;
        for(int i = 0; i < Guitar.NUM_OF_STRINGS; i++){
            label = new StrLabel(EnumPanel.STR_ENUM_WIDTH/2, y, Integer.toString(i+1));
            y += Guitar.INTERNAL_STRING_PAD;
            enumLabels.add(label);
            this.add(label);
        }
    }
}

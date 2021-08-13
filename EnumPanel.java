import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**Base class for panels that will contain enumeration labels: frets, strings and maybe other things.
 * @enumLabels will hold all drawable string objects that are needed to be displayed**/
public class EnumPanel extends JPanel {
    static final int FRET_ENUM_WIDTH = MScaleFrame.FRAME_WIDTH;
    static final int FRET_ENUM_HEIGHT = Instrument.EXTERNAL_STRING_PAD*2;
    static final int STR_ENUM_WIDTH = Instrument.FRET_PAD;

    ArrayList<StrLabel> enumLabels;

    EnumPanel(int width, int height){
        super();
        Dimension dim = new Dimension(width, height);
        this.setPreferredSize(dim);
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
    FretEnumPanel(int width, int height){
        super(width, height);
        this.setBackground(new Color(157, 126, 104));
        int y = Instrument.EXTERNAL_STRING_PAD;
        int x = EnumPanel.STR_ENUM_WIDTH + 2*Instrument.FRET_WIDTH + Instrument.FRET_PAD/2;

        StrLabel label;
        for(int i = 0; i < Instrument.NUM_OF_FRETS; i++){
            label = new StrLabel(x, y, Integer.toString(i));
            x += Instrument.FRET_PAD + Instrument.FRET_WIDTH;
            enumLabels.add(label);
            this.add(label);
        }
    }
}

class StrEnumPanel extends EnumPanel {
    /**Derived class for string enumeration**/
    StrEnumPanel(int width, int height, int numOfStr){
        super(width, height);
        this.setBackground(new Color(153, 153, 102));

        StrLabel label;
        int y = Instrument.EXTERNAL_STRING_PAD;
        for(int i = 0; i < numOfStr; i++){
            label = new StrLabel(EnumPanel.STR_ENUM_WIDTH/2, y, Integer.toString(i+1));
            y += Instrument.EXTERNAL_STRING_PAD*2;
            enumLabels.add(label);
            this.add(label);
        }
    }
}

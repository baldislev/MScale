import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    JPopupMenu baseMenu;
    JPopupMenu scaleMenu;

    JButton baseButton;
    JButton scaleButton;

    JMenuItem majItem, minItem;
    JMenuItem cItem, cshItem, dItem, dshItem, eItem, fItem, fshItem, gItem, gshItem, aItem, ashItem, bItem;

    MenuPanel(){
        super();

        this.setPreferredSize(new Dimension(MScaleFrame.FRAME_WIDTH, Guitar.INTERNAL_STRING_PAD));
        this.setBackground(new Color(157, 126, 104));
        baseMenu = new JPopupMenu();
        scaleMenu = new JPopupMenu();

        baseButton = new JButton("Base");
        scaleButton = new JButton("Scale");

        majItem = new JMenuItem(MScale.majString);
        majItem.addActionListener(new ScaleActionListener(MScale.majString));
        minItem = new JMenuItem("Min");
        minItem.addActionListener(new ScaleActionListener(MScale.minString));

        scaleMenu.add(majItem);
        scaleMenu.add(minItem);


        cItem = new JMenuItem("C");
        cItem.addActionListener(new BaseActionListener("C"));
        cshItem = new JMenuItem("C#");
        cshItem.addActionListener(new BaseActionListener("C#"));
        dItem = new JMenuItem("D");
        dItem.addActionListener(new BaseActionListener("D"));
        dshItem = new JMenuItem("D#");
        dshItem.addActionListener(new BaseActionListener("D#"));
        eItem = new JMenuItem("E");
        eItem.addActionListener(new BaseActionListener("E"));
        fItem = new JMenuItem("F");
        fItem.addActionListener(new BaseActionListener("F"));
        fshItem = new JMenuItem("F#");
        fshItem.addActionListener(new BaseActionListener("F#"));
        gItem = new JMenuItem("G");
        gItem.addActionListener(new BaseActionListener("G"));
        gshItem = new JMenuItem("G#");
        gshItem.addActionListener(new BaseActionListener("G#"));
        aItem = new JMenuItem("A");
        aItem.addActionListener(new BaseActionListener("A"));
        ashItem = new JMenuItem("A#");
        ashItem.addActionListener(new BaseActionListener("A#"));
        bItem = new JMenuItem("B");
        bItem.addActionListener(new BaseActionListener("B"));

        baseMenu.add(cItem);
        baseMenu.add(cshItem);
        baseMenu.add(dItem);
        baseMenu.add(dshItem);
        baseMenu.add(eItem);
        baseMenu.add(fItem);
        baseMenu.add(fshItem);
        baseMenu.add(gItem);
        baseMenu.add(gshItem);
        baseMenu.add(aItem);
        baseMenu.add(ashItem);
        baseMenu.add(bItem);

        scaleButton.setComponentPopupMenu(scaleMenu);
        baseButton.setComponentPopupMenu(baseMenu);

        scaleButton.addActionListener(new ButtonActionListener(scaleMenu, scaleButton));
        baseButton.addActionListener(new ButtonActionListener(baseMenu, baseButton));
        this.add(baseButton);
        this.add(scaleButton);
    }

    class ScaleActionListener implements ActionListener {
        String scale;

        ScaleActionListener(String scale){
            this.scale = scale;
        }

        public void actionPerformed(ActionEvent ev) {
            try {
                MScaleFrame.guitar.clearScale();
                MScaleFrame.guitar.setScale(scale, Guitar.currentBase);
                MScaleFrame.guitar.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class BaseActionListener implements ActionListener {
        String base;

        BaseActionListener(String base){
            this.base = base;
        }

        public void actionPerformed(ActionEvent ev) {
            try {
                MScaleFrame.guitar.clearScale();
                MScaleFrame.guitar.setScale(Guitar.currentScale, MTune.strNoteToInt(base));
                MScaleFrame.guitar.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class ButtonActionListener implements ActionListener{
        JPopupMenu menu;
        JButton button;
        ButtonActionListener(JPopupMenu menu, JButton button){
            this.menu = menu;
            this.button = button;
        }
        public void actionPerformed(ActionEvent ev) {
            menu.show(button, 0, button.getBounds().y
                    + button.getBounds().height);
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuPanel extends JPanel {
    /**This class is a panel for holding the menu of this app. Includes setting buttons and
     * displaying the current parameters of a guitar and a scale.
     * @baseButton will be responsible for the base note of a scale, while
     * @scaleButton is for the scale type.**/
    private JPopupMenu baseMenu;
    private JPopupMenu scaleMenu;

    private JLabel displayScale;

    private JButton baseButton;
    private JButton scaleButton;

    private JMenuItem majItem, minItem;
    private JMenuItem cItem, cshItem, dItem, dshItem, eItem, fItem, fshItem, gItem, gshItem, aItem, ashItem, bItem;

    MenuPanel(){
        super();

        this.setPreferredSize(new Dimension(MScaleFrame.FRAME_WIDTH, Guitar.INTERNAL_STRING_PAD));
        this.setBackground(new Color(157, 126, 104));
        //MENU:
        baseMenu = new JPopupMenu();
        scaleMenu = new JPopupMenu();
        //AREA THAT WILL DISPLAY THE CURRENT SCALE:
        displayScale = new JLabel();
        displayScale.setFont(new Font("Arial", Font.BOLD, 15));
        displayScale.setPreferredSize(new Dimension(200,20));
        setDisplayScale();

        //BUTTONS:
        baseButton = new JButton("Base");
        scaleButton = new JButton("Scale");
        //ITEMS FOR SCALE MENU
        majItem = new JMenuItem(MScale.majString);
        majItem.addActionListener(new ScaleBaseActionListener(MScale.majString, false));
        minItem = new JMenuItem("Min");
        minItem.addActionListener(new ScaleBaseActionListener(MScale.minString, false));

        scaleMenu.add(majItem);
        scaleMenu.add(minItem);
        scaleMenu.setSize(scaleButton.getWidth(), scaleMenu.getHeight());
        scaleMenu.setPreferredSize(new Dimension(scaleButton.getPreferredSize().width,
                scaleMenu.getPreferredSize().height));

        //ITEMS FOR BASE MENU:
        cItem = new JMenuItem("C");
        cItem.addActionListener(new ScaleBaseActionListener("C", true));
        cshItem = new JMenuItem("C#");
        cshItem.addActionListener(new ScaleBaseActionListener("C#", true));
        dItem = new JMenuItem("D");
        dItem.addActionListener(new ScaleBaseActionListener("D", true));
        dshItem = new JMenuItem("D#");
        dshItem.addActionListener(new ScaleBaseActionListener("D#", true));
        eItem = new JMenuItem("E");
        eItem.addActionListener(new ScaleBaseActionListener("E", true));
        fItem = new JMenuItem("F");
        fItem.addActionListener(new ScaleBaseActionListener("F", true));
        fshItem = new JMenuItem("F#");
        fshItem.addActionListener(new ScaleBaseActionListener("F#", true));
        gItem = new JMenuItem("G");
        gItem.addActionListener(new ScaleBaseActionListener("G", true));
        gshItem = new JMenuItem("G#");
        gshItem.addActionListener(new ScaleBaseActionListener("G#", true));
        aItem = new JMenuItem("A");
        aItem.addActionListener(new ScaleBaseActionListener("A", true));
        ashItem = new JMenuItem("A#");
        ashItem.addActionListener(new ScaleBaseActionListener("A#", true));
        bItem = new JMenuItem("B");
        bItem.addActionListener(new ScaleBaseActionListener("B", true));

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
        baseMenu.setPreferredSize(new Dimension(baseButton.getPreferredSize().width,
                baseMenu.getPreferredSize().height));
        
        scaleButton.addActionListener(new ButtonActionListener(scaleMenu, scaleButton));
        baseButton.addActionListener(new ButtonActionListener(baseMenu, baseButton));
        this.add(baseButton);
        this.add(scaleButton);
        this.add(displayScale);

    }

    private void  setDisplayScale(){
        String strNote = MScale.getNoteStrFromInt(MScaleFrame.guitar.currentBase);
        displayScale.setText("Current Scale: " + strNote + " " + MScaleFrame.guitar.currentScale);
        displayScale.repaint();
    }

    class ScaleBaseActionListener implements ActionListener {
        /**Action listener for scaleButton and or baseButton
         * @isForBase is a boolean that helps differentiate between parameters of a scale that needed
         * to be modified - either base or scaleType. This decision is made inside actionPerformed method**/
        String scaleSetting;
        boolean isForBase;
        ScaleBaseActionListener(String scaleSetting, boolean isForBase) {
            this.scaleSetting = scaleSetting;
            this.isForBase = isForBase;
        }

        public void actionPerformed(ActionEvent ev) {
            try {
                MScaleFrame.guitar.clearScale();
                if(isForBase){
                    MScaleFrame.guitar.setScale(MScaleFrame.guitar.currentScale, MScale.strNoteToInt(scaleSetting));
                } else {
                    MScaleFrame.guitar.setScale(scaleSetting, MScaleFrame.guitar.currentBase);
                }
                MScaleFrame.guitar.repaint();
                setDisplayScale();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        private void setDisplayScale(){
            String strNote = MScale.getNoteStrFromInt(MScaleFrame.guitar.currentBase);
            displayScale.setText("Current Scale: " + strNote + " " + MScaleFrame.guitar.currentScale);
            displayScale.repaint();
        }
    }


    static class ButtonActionListener implements ActionListener{
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

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
    private JPopupMenu stringsMenu;
    private JPopupMenu sixStrMenu;
    private JPopupMenu fourStrMenu;

    private JLabel displayScale;

    private JButton baseButton;
    private JButton scaleButton;
    private JButton stringsButton;
    private JButton tuneButton;

    MenuPanel(){
        super();

        this.setPreferredSize(new Dimension(MScaleFrame.FRAME_WIDTH, Instrument.EXTERNAL_STRING_PAD*2));
        this.setBackground(new Color(157, 126, 104));

        //BUTTONS:
        baseButton = new JButton("Base");
        scaleButton = new JButton("Scale");
        stringsButton = new JButton("#Strings");
        tuneButton = new JButton("Tune");

        //AREA THAT WILL DISPLAY THE CURRENT SCALE:
        displayScale = new JLabel();
        displayScale.setFont(new Font("Arial", Font.BOLD, 15));
        displayScale.setPreferredSize(new Dimension(200,20));
        setDisplayScale();

        //MENUs:
        baseMenu = new JPopupMenu();
        scaleMenu = new JPopupMenu();
        stringsMenu = new JPopupMenu();
        sixStrMenu = new JPopupMenu();
        fourStrMenu = new JPopupMenu();


        //ITEMS FOR SCALE MENU
        JMenuItem majItem = new JMenuItem(MScale.majString);
        majItem.addActionListener(new ScaleBaseActionListener(MScale.majString, false));
        JMenuItem minItem = new JMenuItem("Min");
        minItem.addActionListener(new ScaleBaseActionListener(MScale.minString, false));

        scaleMenu.add(majItem);
        scaleMenu.add(minItem);
        scaleMenu.setPreferredSize(new Dimension(scaleButton.getPreferredSize().width,
                scaleMenu.getPreferredSize().height));

        //ITEMS FOR BASE MENU:
        JMenuItem cItem = new JMenuItem("C");
        cItem.addActionListener(new ScaleBaseActionListener("C", true));
        JMenuItem cshItem = new JMenuItem("C#");
        cshItem.addActionListener(new ScaleBaseActionListener("C#", true));
        JMenuItem dItem = new JMenuItem("D");
        dItem.addActionListener(new ScaleBaseActionListener("D", true));
        JMenuItem dshItem = new JMenuItem("D#");
        dshItem.addActionListener(new ScaleBaseActionListener("D#", true));
        JMenuItem eItem = new JMenuItem("E");
        eItem.addActionListener(new ScaleBaseActionListener("E", true));
        JMenuItem fItem = new JMenuItem("F");
        fItem.addActionListener(new ScaleBaseActionListener("F", true));
        JMenuItem fshItem = new JMenuItem("F#");
        fshItem.addActionListener(new ScaleBaseActionListener("F#", true));
        JMenuItem gItem = new JMenuItem("G");
        gItem.addActionListener(new ScaleBaseActionListener("G", true));
        JMenuItem gshItem = new JMenuItem("G#");
        gshItem.addActionListener(new ScaleBaseActionListener("G#", true));
        JMenuItem aItem = new JMenuItem("A");
        aItem.addActionListener(new ScaleBaseActionListener("A", true));
        JMenuItem ashItem = new JMenuItem("A#");
        ashItem.addActionListener(new ScaleBaseActionListener("A#", true));
        JMenuItem bItem = new JMenuItem("B");
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
        baseMenu.setPreferredSize(new Dimension(baseButton.getPreferredSize().width,
                baseMenu.getPreferredSize().height));

        //ITEMS FOR STRINGSMENU:
        JMenuItem sixStrItem = new JMenuItem("6 strings");
        sixStrItem.addActionListener(new NumOfStringsActionListener(6));
        JMenuItem fourStrItem = new JMenuItem("4 strings");
        fourStrItem.addActionListener(new NumOfStringsActionListener(4));

        stringsMenu.add(sixStrItem);
        stringsMenu.add(fourStrItem);

        //ITEMS FOR SIXSTRINGMENU:
        JMenuItem classGtrItem = new JMenuItem(MTune.classicGuitarStr);
        classGtrItem.addActionListener(new TuneItemActionListener());
        JMenuItem dropDGtrItem = new JMenuItem(MTune.dropDStr);
        dropDGtrItem.addActionListener(new TuneItemActionListener());

        sixStrMenu.add(classGtrItem);
        sixStrMenu.add(dropDGtrItem);
        //ITEMS FOR FOURSTRMENU:
        JMenuItem classUkeItem = new JMenuItem(MTune.classicUkeStr);
        classUkeItem.addActionListener(new TuneItemActionListener());
        JMenuItem mandolinItem = new JMenuItem(MTune.mandolinStr);
        mandolinItem.addActionListener(new TuneItemActionListener());

        fourStrMenu.add(classUkeItem);
        fourStrMenu.add(mandolinItem);


        //Connecting buttons with their corresponding menus and setting actionListeners:
        scaleButton.setComponentPopupMenu(scaleMenu);
        baseButton.setComponentPopupMenu(baseMenu);
        stringsButton.setComponentPopupMenu(stringsMenu);
        switch(MScaleFrame.instrument.getNumOfStr()){
            case 6:
                tuneButton.setComponentPopupMenu(sixStrMenu);
                break;
            case 4:
                tuneButton.setComponentPopupMenu(fourStrMenu);
                break;
        }
        
        scaleButton.addActionListener(new ButtonActionListener(scaleButton));
        baseButton.addActionListener(new ButtonActionListener(baseButton));
        stringsButton.addActionListener(new ButtonActionListener(stringsButton));
        tuneButton.addActionListener(new ButtonActionListener(tuneButton));


        this.add(stringsButton);
        this.add(tuneButton);
        this.add(baseButton);
        this.add(scaleButton);
        this.add(displayScale);

    }

    private void  setDisplayScale(){
        String strNote = MScale.getNoteStrFromInt(MScaleFrame.instrument.currentBase);
        displayScale.setText(strNote + " " + MScaleFrame.instrument.currentScale +
                " " + MScaleFrame.instrument.currentTune);
        displayScale.repaint();
    }

    class ScaleBaseActionListener implements ActionListener {
        /**Action listener for event of choosing a menu item of ScaleButton or BaseButton.
         * Responsible for modifying the display of a scale of current instrument.
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
                MScaleFrame.instrument.clearScale();
                if(isForBase){
                    MScaleFrame.instrument.setScale(MScaleFrame.instrument.currentScale, MScale.strNoteToInt(scaleSetting));
                } else {
                    MScaleFrame.instrument.setScale(scaleSetting, MScaleFrame.instrument.currentBase);
                }
                MScaleFrame.instrument.repaint();
                setDisplayScale();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class NumOfStringsActionListener implements ActionListener{
        /**Handler for numOfStrings button which renders a new instrument with desired number of
         * strings with a classic tune for each variant. The handler also changing the content
         * of the tuneMenu.**/
        int numOfStrings;
        NumOfStringsActionListener(int numOfStrings){
            this.numOfStrings = numOfStrings;
        }
        public void actionPerformed(ActionEvent ev){
            if(MScaleFrame.instrument.getNumOfStr() == numOfStrings)
                return;

            switch (numOfStrings){
                case 6:
                    try {
                        MScaleApp.frame.retune(MTune.classicGuitarStr);
                    } catch (MScaleException e) {
                        e.printStackTrace();
                    }
                    tuneButton.setComponentPopupMenu(sixStrMenu);
                    break;
                case 4:
                    try {
                        MScaleApp.frame.retune(MTune.classicUkeStr);
                    } catch (MScaleException e) {
                        e.printStackTrace();
                    }
                    tuneButton.setComponentPopupMenu(fourStrMenu);
                    break;
                default:
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
            setDisplayScale();
        }
    }

    class TuneItemActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            String context = ((JMenuItem)ev.getSource()).getText();

            try {
                MScaleApp.frame.retune(context);
            } catch (MScaleException e) {
                e.printStackTrace();
            }
            setDisplayScale();
        }
    }


    static class ButtonActionListener implements ActionListener{
        /**Action listener for displaying the menu content of each button**/
        JButton button;
        ButtonActionListener(JButton button){
            this.button = button;
        }

        public void actionPerformed(ActionEvent ev) {
            JPopupMenu menu = button.getComponentPopupMenu();
            menu.show(button, 0, button.getBounds().y
                    + button.getBounds().height);
        }
    }
}

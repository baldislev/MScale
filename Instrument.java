import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Instrument extends JPanel {
/**sizes of instrument elements that are constant between all instances of instruments**/
    static final int INSTR_WIDTH = 1200;
    static final int NUM_OF_FRETS = 20;
    static final int EXTERNAL_STRING_PAD = 20;
    static final int STR_HEIGHT = 4;
    static final int FRET_WIDTH = STR_HEIGHT;
    static final int FRET_PAD = (INSTR_WIDTH - NUM_OF_FRETS*FRET_WIDTH)/NUM_OF_FRETS;

    int numOfStrings;
    int instrHeight;
    int internalStrPad;

    String currentScale;
    int currentBase;
    String currentTune;

    private MString[] mStrings;
    private Mfret[] mFrets;
    private ArrayList<StrLabel> StrLabels;

    Instrument(String strTune) throws MScaleException {
        super();
        /*initializing the dynamic sizes of the instrument elements.*/
        numOfStrings = MTune.tuneMap.get(strTune).length;
        currentTune = strTune;
        internalStrPad = 2*EXTERNAL_STRING_PAD;
        instrHeight = 2*numOfStrings*EXTERNAL_STRING_PAD;

        Dimension instr_dim = new Dimension(INSTR_WIDTH, instrHeight);
        MTune tune = new MTune(strTune);
        createMStrings(tune);
        createMFrets();
        StrLabels = new ArrayList<StrLabel>();
        this.setBackground(new Color(212,168,83));
        this.setPreferredSize(instr_dim);
        this.setLayout(new GridLayout());
        this.setScale(MScale.majString, 0);
    }

    int getNumOfStr(){
        return this.numOfStrings;
    }

    int getInstrHeight(){
        return this.instrHeight;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g){
        drawMStrings(g);
        drawStrLabels(g);
        drawMFrets(g);
    }

    private void createMStrings(MTune gtrTune){
        mStrings = new MString[numOfStrings];
        int y = EXTERNAL_STRING_PAD;
        int baseNote;
        for(int i = 0; i < numOfStrings; i++){
            baseNote = gtrTune.tune[i];
            mStrings[i] = new MString(0, y, INSTR_WIDTH, STR_HEIGHT, baseNote);
            y += internalStrPad;
        }
    }

    private void drawMStrings(Graphics g){
        for(int i = 0; i < numOfStrings; i++){
            mStrings[i].draw(g);
        }
    }

    private void drawStrLabels(Graphics g){
        if(!StrLabels.isEmpty())
            for (StrLabel StrLabel : StrLabels) {
                StrLabel.drawLabel(g);
            }
    }

    /**the most important method of this class and probably the whole app.
     * Here the neck of a guitar gets labeled in relevant places that will show strings and frets
     * that create the desired notes with respect to a current sCale which is set by:
     * @param scaleType - the type of a scale: minor, major...
     * @param baseNote - the base note upon which the scale is built: C, D...**/
    void setScale(String scaleType, int baseNote) throws MScaleException {
        currentBase = baseNote;
        currentScale = scaleType;

        MScale scale = new MScale(scaleType, baseNote);
        int testNote;
        StrLabel label;
        int x;
        int y = EXTERNAL_STRING_PAD;
        String note;

        for(int i = 0; i < numOfStrings; i++){
            x = FRET_PAD/2 ;
            for(int j = 0; j < NUM_OF_FRETS; j++){
                testNote = (mStrings[i].getBaseNote() + j) % MScale.NUM_OF_NOTES;
                if(scale.isInScale(testNote)){
                    note = MScale.getNoteStrFromInt(testNote);
                    label = new StrLabel(x, y, note);
                    StrLabels.add(label);
                    this.add(label);
                }
                x += FRET_PAD + FRET_WIDTH;
            }
            y += internalStrPad;
        }
    }
    /**This method will clear all the labels of a scale and will leave the neck
     * of a guitar clear - just strings and frets. **/
    void clearScale(){
        if(!StrLabels.isEmpty()) {
            for (StrLabel StrLabel : StrLabels) {
                this.remove(StrLabel);
            }
            StrLabels.clear();
        }
    }

    private void createMFrets(){
        mFrets = new Mfret[NUM_OF_FRETS];
        int x = FRET_PAD;
        for(int i = 0; i < NUM_OF_FRETS; i++){
            mFrets[i] = new Mfret(x, 0, FRET_WIDTH, instrHeight);
            x += FRET_PAD + FRET_WIDTH;
        }
    }

    private void drawMFrets(Graphics g){
        for(int i = 0; i < NUM_OF_FRETS; i++){
            mFrets[i].draw(g);
        }
    }
}

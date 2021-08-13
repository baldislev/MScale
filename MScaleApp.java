
public class MScaleApp {
    /**Main class of the application, responsible for initiating the most global environment for the
     * such as note mapping and a frame what are independent on what instrument we want to display.**/

    static MScaleFrame frame;

    public static void main(String[] args)  {
        try {
            MScale.initNoteMap();
            MTune.initTuneMap();
            frame = new MScaleFrame(MTune.classicGuitarStr);
        } catch (MScaleException e){
            System.out.println(e.getMessage());
        }
    }
}

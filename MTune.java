import java.util.HashMap;

class MTune {
    /**This class is an abstraction of guitar tune. Meanwhile only the
     * classical tune is implemented. Possibly other guitar tune settings will be added later**/
    static final String classicGuitarStr = "Classic Guitar";
    static final String dropDStr = "Drop D";
    static final String classicUkeStr = "Classic Uke";
    static final String mandolinStr = "Mandolin";

    static final String[] gtrClassicTune = {"E", "B", "G", "D", "A", "E"};
    static final String[] gtrDropDTune = {"E", "B", "G", "D", "A", "D"};
    static final String[] ukeClassicTune = {"A", "E", "C", "G"};
    static final String[] mandolinTune = {"E", "A", "D", "G"};

    static HashMap<String, String[]> tuneMap;

    int[] tune;

    MTune(String strTune){
        String[] tuneArray = tuneMap.get(strTune);

        tune = new int[tuneArray.length];
        for(int i = 0; i < tuneArray.length; i++){
            tune[i] = MScale.strNoteToInt(tuneArray[i]);
        }
    }

    static void initTuneMap(){
        tuneMap = new HashMap<>();
        tuneMap.put(classicGuitarStr, gtrClassicTune);
        tuneMap.put(dropDStr, gtrDropDTune);
        tuneMap.put(mandolinStr, mandolinTune);
        tuneMap.put(classicUkeStr, ukeClassicTune);
    }
}

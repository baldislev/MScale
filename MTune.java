import java.util.HashMap;

class MTune {
    static HashMap<String, Integer> noteMap;
    static final String[] classicTune = {"E", "B", "G", "D", "A", "E"};

    int[] tune;

    MTune(String[] strTune){
        tune = new int[strTune.length];
        for(int i = 0; i < strTune.length; i++){
            tune[i] = strNoteToInt(strTune[i]);
        }
    }

    static public int strNoteToInt(String note){
        return noteMap.get(note);
    }

    static String getNoteStrFromInt(int noteVal) {
        for(HashMap.Entry<String, Integer> entry : noteMap.entrySet()){
            if(noteVal == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    static void initNoteMap() {
        noteMap = new HashMap<String, Integer>();
        noteMap.put("C", 0);
        noteMap.put("D", 2);
        noteMap.put("E", 4);
        noteMap.put("F", 5);
        noteMap.put("G", 7);
        noteMap.put("A", 9);
        noteMap.put("B", 11);

        noteMap.put("C#", 1);
        noteMap.put("D#", 3);
        //noteMap.put("E#", 5);
        noteMap.put("F#", 6);
        noteMap.put("G#", 8);
        noteMap.put("A#", 10);
        //noteMap.put("B#", 0);

        /*
        noteMap.put("Cb", 11);
        noteMap.put("Db", 1);
        noteMap.put("Eb", 3);
        noteMap.put("Fb", 4);
        noteMap.put("Gb", 6);
        noteMap.put("Ab", 8);
        noteMap.put("Bb", 10);
        */
    }
}

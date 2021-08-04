
class MTune {
    /**This class is an abstraction of guitar tune. Meanwhile only the
     * classical tune is implemented. Possibly other guitar tune settings will be added later**/
    static final String[] classicTune = {"E", "B", "G", "D", "A", "E"};

    int[] tune;

    MTune(String[] strTune){
        tune = new int[strTune.length];
        for(int i = 0; i < strTune.length; i++){
            tune[i] = MScale.strNoteToInt(strTune[i]);
        }
    }



}

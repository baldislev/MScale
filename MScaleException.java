class MScaleException extends Exception {

    static String invScaleInitStr = "MScale Constructor received an invalid parameter for scaleType";

    MScaleException(String message){
        super(message);
    }
}

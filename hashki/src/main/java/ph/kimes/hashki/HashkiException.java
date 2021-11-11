package ph.kimes.hashki;

public class HashkiException extends Exception {

    public HashkiException(String errorMessage) { super(errorMessage); }
    public HashkiException(String errorMessage,Throwable err) { super(errorMessage, err); }
}

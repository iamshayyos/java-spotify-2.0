package mainPackage.custom_exceptions;

public class MaxSongsException extends Exception {
	private static final long serialVersionUID = 1L;
	public MaxSongsException(String message) {
        super(message);
    }
}
package quartet;

public class CardDeckException extends RuntimeException{


	private static final long serialVersionUID = 8897798110143299252L;

	public CardDeckException(String message) {
		super(message);
	}

	public CardDeckException(Throwable cause) {
		super(cause);
	}

	public CardDeckException(String message, Throwable cause) {
		super(message, cause);
	}



}

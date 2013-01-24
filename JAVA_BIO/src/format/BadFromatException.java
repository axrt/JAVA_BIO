package format;

public abstract class BadFromatException extends Exception {

	public BadFromatException() {
	}

	public BadFromatException(String message) {
		super(message);
	}

	public BadFromatException(Throwable cause) {
		super(cause);
	}

	public BadFromatException(String message, Throwable cause) {
		super(message, cause);
	}

}

package lexer;

public class LexerException extends Exception {
	private String message;
	
	public LexerException ( String message ) {
		this.message = message;
	}
	
	public String toString ( ) {
		return message;
	}
}
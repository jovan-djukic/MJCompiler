package rs.ac.bg.etf.pp1;

public class LexerException extends Exception {
	private String message = null;
	
	public LexerException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return message;
	}
}
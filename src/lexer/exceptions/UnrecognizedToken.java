package lexer.exceptions;

public class UnrecognizedToken extends Exception {
	private String c;
	private int pos;
	
	public UnrecognizedToken(String c, int pos) {
		this.c = c;
		this.pos = pos;
	}

	@Override
	public String toString() {
		return "LEXER ERROR: Unrecognized token in position " + pos + ": \"" + c + "\"";
	}
}

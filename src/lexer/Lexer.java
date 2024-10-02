package lexer;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lexer.exceptions.UnrecognizedToken;

public class Lexer {
	// This enum contains all the possible tokens accepted by the language and their respective regular expression
	private enum PossibleTokens {
		NUM("\\d+"),
		SUM("\\+"),
		SUB("-"),
		MULT("\\*"),
		DIV("/"),
		LPAREN("\\("),
		RPAREN("\\)"),
		ERROR(".+");
		
		public Pattern pattern;
		
		private PossibleTokens (String ptrnString) {
			pattern = Pattern.compile(ptrnString);
		}
	}
	private String input;
	private Pattern tokensPattern;
	
	// Constructor for the Lexer class
	public Lexer(String input) {
		this.input = input;
		System.out.println("Brute input: " + this.input);
		setupString();
		System.out.println("Processed input: " + this.input);
		this.tokensPattern = createTokensPattern();
	}

	private Pattern createTokensPattern() {
		StringBuilder pattern = new StringBuilder();
	
		// Creating tokens pattern using the token's name for a named-capturing group
		for (PossibleTokens token : PossibleTokens.values()) {
			pattern.append(String.format("|(?<%s>%s)", token.name(), token.pattern));
		}
	
		return Pattern.compile(pattern.substring(1));
	}

	// This function takes care of eliminating whitespaces and identation from the input
	private void setupString() {
		Pattern thingsToDeletePtrn = Pattern.compile("\\s");
		Matcher matcher = thingsToDeletePtrn.matcher(input);

		this.input = matcher.replaceAll("");
	}

	public LinkedList<Token> tokenize() throws UnrecognizedToken {
		// Create a matcher for the input string
		Matcher matcher = tokensPattern.matcher(input);

		// Create an empty list for the result
		LinkedList<Token> tokensList = new LinkedList<>();

		// While matches are found, keep looping		
		while (matcher.find()) {
			// Get match
			String matchStr = matcher.group();
			
			// Check witch token matched to create the apropriate Token object
			for(PossibleTokens t : PossibleTokens.values()) {
				if (matcher.group(t.name()) != null) {
					if (t.name() == PossibleTokens.ERROR.name())
						throw new UnrecognizedToken(matchStr, matcher.start() + 1);
				
					tokensList.add(new Token(t.name(), matchStr));
					break;
				}
			}
		}
		
		// Return list of Tokens
		return tokensList;
	}

	public static void main(String[] args) {
		String input = "1   + 		1 * 22 +	";

		var lexer = new Lexer(input);
		
		try {
			System.out.println(lexer.tokenize());
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}

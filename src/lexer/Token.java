package lexer;

public class Token {
	private String name;
	private String content;
	
	public Token(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "(" + this.name + ";" +  this.content + ")";
	}
}

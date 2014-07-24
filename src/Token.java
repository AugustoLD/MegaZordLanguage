public class Token {
  
	public enum TokenType {
		ATTR,
		INT_TYPE, CHAR_TYPE, BOOL_TYPE, 
		IF_DECL, ELSE_DECL, 
		WHILE_DECL,
		NUMBER, CHAR, BOOL, STRING,
		ID, PROG_NAME, PRINT,
		PLUS, MINUS, MULT, DIV,
		EQ, NE, LT, GT, LE, GE,
		COMMA, SEMICOLLON,
		L_PAR, R_PAR,L_BRACE, R_BRACE;
	};

	TokenType tipo;
	int position;
	String key;
	Double numValue;
	int line;

	Token(TokenType t, String key, Double numValue, int line){
		this.tipo = t;
		this.position=-1;
		this.key = key;
		this.numValue = numValue;
		this.line = line;
	}
	
	Token(TokenType t, int position, String key, Double numValue, int line){
		this.tipo = t;
		this.position = position;
		this.key = key;
		this.numValue = numValue;
		this.line = line;
	}

	public TokenType getType() {
		return(this.tipo);
	}

	public String toString() {
		return("<" + tipo +", " + key + ", " + numValue +", " + line + ">");
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
}

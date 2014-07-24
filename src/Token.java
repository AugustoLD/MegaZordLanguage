import java.io.*;

public class Token {
  
	public enum TokenType {
		PROG_NAME,
		ATTR, //Attribution
		INT_TYPE, CHAR_TYPE, BOOL_TYPE, 
		IF_DECL, ELSE_DECL, 
		WHILE_DECL,
		NUMBER, CHAR, BOOL,
		ID, 
		SUM, DIF, MULT, DIV,
		//Equal, LesserThan, GreaterThan, LesserOrEqual, GreaterOrEqual, NotEqual
		EQ, LT, GT, LE, GE, NE,
		SEMICOLLON,
		OPEN_PAR, CLOSE_PAR,OPEN_KEY, CLOSE_KEY;
	};

	TokenType tipo;
	String key;
	Double numValue;
	int position;

	Token(TokenType t, String key, Double numValue, int position){
		this.tipo = t;
		this.key = key;
		this.numValue = numValue;
		this.position = position;
	}

	public TokenType getType() {
		return(this.tipo);
	}

	public String toString() {
		return("<" + tipo + ", " + key + ", " + numValue +", " + position + ">");
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
}

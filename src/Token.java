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
		EQUAL, LESSERTHAN, GREATERTHAN, LESSEREQUAL, GREATEREQUAL, NOTEQUAL,
		SEMICOLLON,
		OPEN_PAR, CLOSE_PAR,OPEN_KEY, CLOSE_KEY;
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
		return("<" + tipo +", "+ position +", " + key + ", " + numValue +", " + line + ">");
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
}

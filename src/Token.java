import java.util.ArrayList;

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
	int numValue;
	int line;
	
	Token(TokenType t, String key, int numValue, int line){
		this.tipo = t;
		this.key = key;
		this.numValue = numValue;
		this.line = line;
	}

	public static ArrayList<String> getAttributesNames() {
		ArrayList<String> attributesNames = new ArrayList<>();
		
		attributesNames.add("Token Type");
		attributesNames.add("Key");
		attributesNames.add("Numeric Value");
		attributesNames.add("Line");
		
		return attributesNames; 
	}
	
	public TokenType getType() {
		return(this.tipo);
	}

	public String toString() {
		return("<" + tipo +", " + key + ", " + numValue +", " + line + ">");
	}
	
	public ArrayList<String> getStringVector() {
		ArrayList<String> attributesToVector = new ArrayList<>();

		attributesToVector.add(tipo.toString());
		attributesToVector.add(key);
		attributesToVector.add(String.valueOf(numValue));
		attributesToVector.add(String.valueOf(line));
		
		return attributesToVector;
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
}

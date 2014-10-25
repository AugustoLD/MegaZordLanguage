import java.util.ArrayList;

public class Token {
  
	private TokenType type;
	private int position;
	private String key;
	private int numValue;
	private int line;
	
	Token(TokenType t, String key, int numValue, int line){
		this.type = t;
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
	
	public String toString() {
		return("<" + type +", " + key + ", " + numValue +", " + line + ">");
	}
	
	public ArrayList<String> getStringVector() {
		ArrayList<String> attributesToVector = new ArrayList<>();

		attributesToVector.add(type.toString());
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
	
	public String getKey() {
		return this.key;
	}
	
	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	public int getNumValue() {
		return numValue;
	}

	public void setNumValue(int numValue) {
		this.numValue = numValue;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}

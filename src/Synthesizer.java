import java.util.ArrayList;
import java.util.List;

public class Synthesizer {
	private int pos = 0;
	List<Token> tokenList;
	List<TokenType> braceStack = new ArrayList<TokenType>();
	List<TokenType> parenthesisStack = new ArrayList<TokenType>();
	
	public void run(List<Token> tokenList) {
		this.tokenList = tokenList;
		checkOutset();
		Token token;
		
		while(pos < tokenList.size()) {
			token = tokenList.get(pos++);
			if(token.getType() == TokenType.BOOL_TYPE || token.getType() == TokenType.CHAR_TYPE ||
					token.getType() == TokenType.INT_TYPE) {
				
			}
		}
	}
	
	public void checkOutset() {
		if(tokenList.get(pos++).getType() != TokenType.ID) {
			// erro nome do programa
		} 
		if(tokenList.get(pos++).getType() != TokenType.L_PAR) {
			// erro parentese não aberto
		}
		if(tokenList.get(pos++).getType() != TokenType.R_PAR) {
			// erro parentese não fechado
		}
		if(tokenList.get(pos++).getType() != TokenType.L_BRACE) {
			// erro chave não aberta
		} else {
			braceStack.add(TokenType.L_BRACE);
		}
	}
	
	public void checkVarDeclaration() {
		if(tokenList.get(pos++).getType() != TokenType.ID) {
			// erro declaração variável
		}
		if(tokenList.get(pos++).getType() != TokenType.SEMICOLLON) {
			// erro ; expected
		}
	}
}


import java.util.ArrayList;
import java.util.List;

public class Synthesizer {
	private int iterator = 0;
	List<Token> tokenList;
	int braceStack;
	
	public Synthesizer(List<Token> tokenList) {
		this.tokenList = tokenList;
		this.iterator = 0;
		this.braceStack = 0;
	}
	
	public void run() throws Exception {
		/* Checking program beginning
		 * Example: program_name() {
		 */
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.ID) {
			// missing program name
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected program name and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		} 
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.L_PAR) {
			// missing opening parenthesis
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ( and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.R_PAR) {
			// missing closing parenthesis
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ) and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.L_BRACE) {
			// missing opening brace
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected { and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		} else {
			braceStack++;
		}
		
		checkBody();
		
		if(braceStack != 0) {
			// missing closing brace
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected } and got EOF instead");
		}
		if(iterator < tokenList.size()) {
			// exceeding closing brace
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected EOF and got \"" + tokenList.get(iterator).getKey() + "\" instead");
		}	
	}
	
	private void checkBody() throws Exception {
		Token token;
		
		while(iterator < tokenList.size()) {
			token = tokenList.get(iterator++);
			if(token.getType() == TokenType.BOOL_TYPE || token.getType() == TokenType.CHAR_TYPE ||
					token.getType() == TokenType.INT_TYPE) {
				checkVarDeclaration();
			} else if(token.getType() == TokenType.IF_DECL) {
				checkIfDeclaration();
			} else if(token.getType() == TokenType.WHILE_DECL) {
				checkWhileDeclaration();
			} else if(token.getType() == TokenType.ID) {
				checkAttribution();
			} else if(token.getType() == TokenType.PRINT) {
				checkPrintFunction();
			} else if(token.getType() == TokenType.R_BRACE) {
				braceStack--;
				return;
			}
		}
	}
	
	private void checkVarDeclaration() throws Exception {
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.ID) {
			// missing identificator
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected identificator and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.SEMICOLLON) {
			// missing semicollon
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ; and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
	}
	
	private void checkIfDeclaration() throws Exception {
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.L_PAR) {
			// missing opening parenthesis
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ( and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		
		checkLogicExpression();
		
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.R_PAR) {
			// missing closing parenthesis
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ) and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.L_BRACE) {
			// missing opening brace
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected { and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		} else {
			braceStack++;
		}
		
		checkBody();
		// checking if there is an else statement in case of a IF_DECL
		if(iterator < tokenList.size() && tokenList.get(iterator).getType() == TokenType.ELSE_DECL) {
			iterator++;
			checkBody();
		}
	}
	
	private void checkWhileDeclaration() throws Exception {
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.L_PAR) {
			// missing opening parenthesis
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ( and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		
		checkLogicExpression();
		
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.R_PAR) {
			// missing closing parenthesis
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ) and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.L_BRACE) {
			// missing opening brace
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected { and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		} else {
			braceStack++;
		}
		checkBody();
	}
	
	private void checkAttribution() throws Exception {
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.ATTR) {
			// missing attribution symbol
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected = and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		
		checkArithmeticExpression();
		
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.SEMICOLLON) {
			// missing semicollon
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ; and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
	}
	private void checkArithmeticExpression() throws Exception {
		List<Token> expression = new ArrayList<Token>();
		
		while(iterator < tokenList.size() && isValidArithmeticElement(tokenList.get(iterator))) {
			expression.add(tokenList.get(iterator));
			iterator++;
		}

		ShuntingYard shuntingYard = new ShuntingYard(expression);
		expression = shuntingYard.run();
	}
	
	private void checkLogicExpression() throws Exception {
		List<Token> expression = new ArrayList<Token>();
		
		while(iterator < tokenList.size() && isValidArithmeticElement(tokenList.get(iterator))) {
			expression.add(tokenList.get(iterator));
			iterator++;
		}
		if(expression.get(expression.size()-1).getType() == TokenType.R_PAR) {
			expression.remove(expression.size()-1);
			iterator--;
		}

		ShuntingYard shuntingYard = new ShuntingYard(expression);
		expression = shuntingYard.run();
		
		if(iterator < tokenList.size() && isLogicOperator(tokenList.get(iterator))) {
			iterator++;
			checkLogicExpression();
		}
	}
	
	private boolean isLogicOperator(Token token) {
		if(token.getType() == TokenType.EQ || token.getType() == TokenType.GE || 
			token.getType() == TokenType.GT || token.getType() == TokenType.LE ||
			token.getType() == TokenType.LT || token.getType() == TokenType.NE) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isValidArithmeticElement(Token token) {
		if(token.getType() == TokenType.ID || token.getType() == TokenType.NUMBER ||
				token.getType() == TokenType.CHAR || token.getType() == TokenType.PLUS || 
				token.getType() == TokenType.MINUS || token.getType() == TokenType.MULT ||
				token.getType() == TokenType.DIV || token.getType() == TokenType.L_PAR ||
				token.getType() == TokenType.R_PAR) {
			return true;
		} else {
			return false;
		}
	}
	
	private void checkPrintFunction() throws Exception {
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.L_PAR) {
			// missing opening parenthesis
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ( and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		
		checkMessage();
		
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.R_PAR) {
			// missing closing parenthesis
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ) and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.SEMICOLLON) {
			// missing semicollon
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected ; and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
	}
	
	private void checkMessage() throws Exception {
		if(iterator < tokenList.size() && tokenList.get(iterator++).getType() != TokenType.ID &&
				tokenList.get(iterator-1).getType() != TokenType.STRING) {
			// missing print arguments
			throw new Exception("Syntax Error at line " + tokenList.get(iterator-1).getLine() + 
					": Expected print argument and got \"" + tokenList.get(iterator-1).getKey() + "\" instead");
		}
		
		if(iterator < tokenList.size() && tokenList.get(iterator).getType() == TokenType.COMMA) {
			iterator++;
			checkMessage();
		}
	}
}


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShuntingYard {
	private int operands, operators;
	private List<Token> expression;
	
	public ShuntingYard(List<Token> expression) {
		this.expression = expression;
		this.operands = 0;
		this.operators = 0;
	}

	public List<Token> run() throws Exception {
		Stack<Token> operatorStack = new Stack<Token>();
		List<Token> output = new ArrayList<Token>();

		for(Token token : expression) {
			// OPERAND:
			if(token.getType() == TokenType.ID || token.getType() == TokenType.NUMBER ||
					token.getType() == TokenType.CHAR) {
				output.add(token);
				operands++;
			// OPERATOR:
			} else if(token.getType() == TokenType.PLUS || token.getType() == TokenType.MINUS ||
					token.getType() == TokenType.MULT || token.getType() == TokenType.DIV) {
				operators++;
				while(!operatorStack.isEmpty() && operatorStack.peek().getType() != TokenType.L_PAR && 
						token.getType().ordinal() < operatorStack.peek().getType().ordinal()) {
					output.add(operatorStack.peek());
					operatorStack.pop();

				}
				operatorStack.push(token);
			// LEFT PARENTHESIS:
			} else if(token.getType() == TokenType.L_PAR) {
				operatorStack.push(token);
			// RIGTH PARENTHESIS:
			} else if(token.getType() == TokenType.R_PAR) {
				while(!operatorStack.isEmpty() && operatorStack.peek().getType() != TokenType.L_PAR) {
					output.add(operatorStack.peek());
					operatorStack.pop();
				}
				if(operatorStack.isEmpty()) {
					throw new Exception("Syntax Error at line " + token.getLine() + 
							": There are \")\" exceeding");
				}
				// deleting left parenthesis from stack
				operatorStack.pop();
			}
		}

		while(!operatorStack.isEmpty()) {
			if(operatorStack.peek().getType() == TokenType.L_PAR) {
				throw new Exception("Syntax Error at line " + operatorStack.peek().getLine() + 
						": There are \")\" missing");
			}
			output.add(operatorStack.peek());
			operatorStack.pop();
		}
		if(operands - operators <= 0) {
			throw new Exception("Syntax Error at line " + expression.get(0).getLine() + 
					": There are operators exceeding");
		} else if(operands - operators > 1) {
			throw new Exception("Syntax Error at line " + expression.get(0).getLine() + 
					": There are operands exceeding");
		}
		return output;
	}
	
}

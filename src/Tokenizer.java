import java.io.*;
import java.util.*;

public class Tokenizer {
	BufferedReader br;
	StreamTokenizer st = null;

	Tokenizer(BufferedReader br) {
		this.br = br;
	}

	public void run(Vector<Token> tokens, SymbolTable sb){
		try {
			st = new StreamTokenizer(br);

			if(st.nextToken() == StreamTokenizer.TT_WORD && st.ttype != StreamTokenizer.TT_EOF) {
				tokens.add(new Token(Token.TokenType.PROG_NAME, st.sval, 0.0, st.lineno()));
			}
			
			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				if (String.valueOf((char) st.ttype).equals("\'")) {
					tokens.add(new Token(Token.TokenType.CHAR, st.sval, 0.0, st.lineno()));
				} else if (st.ttype == StreamTokenizer.TT_NUMBER) {
					tokens.add(new Token(Token.TokenType.NUMBER, "", st.nval, st.lineno()));
				} else if(st.ttype == StreamTokenizer.TT_WORD) {
					tokens.add(buildWordToken(st.sval));
				} else if ((st.ttype != StreamTokenizer.TT_EOF) || (st.ttype != StreamTokenizer.TT_EOL)) {
					tokens.add(buildSimbolToken(st.ttype));
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Token buildWordToken(String word){
		Token.TokenType tt = null;
		String sval = "";

		switch(word.toUpperCase()) {
		case "IF": 
			tt = Token.TokenType.IF_DECL;
			break;
		case "ELSE": 
			tt = Token.TokenType.ELSE_DECL; 
			break;
		case "WHILE": 
			tt = Token.TokenType.WHILE_DECL; 
			break;
		case "INT": 
			tt = Token.TokenType.INT_TYPE; 
			break;
		case "CHAR": 
			tt = Token.TokenType.CHAR_TYPE; 
			break;
		case "BOOL": 
			tt = Token.TokenType.BOOL_TYPE; 
			break;
		case "TRUE": 
			tt = Token.TokenType.BOOL; 
			break;
		case "FALSE": 
			tt = Token.TokenType.BOOL; 
			break;
		default:
			tt = Token.TokenType.ID;
        	break;
		}
		sval = word;

		return(new Token(tt, sval, 0.0, st.lineno()));
	}

	private Token buildSimbolToken(int val){
		Token.TokenType tt = null;
		String simbolo = String.valueOf((char) val);

		switch(simbolo) {
		case "=": 
				tt = Token.TokenType.ATTR; 
				break;
		case "+": 
			tt = Token.TokenType.SUM; 
			break;
		case "-": 
			tt = Token.TokenType.DIF; 
			break;
		case "*":
			tt = Token.TokenType.MULT; 
			break;
		case "/": 
			tt = Token.TokenType.DIV;
			break;
		case "==": 
			tt = Token.TokenType.EQ; 
			break;
		case ">": 
			tt = Token.TokenType.GT; 
			break;
		case "<": 
			tt = Token.TokenType.LT; 
			break;
		case ">=": 
			tt = Token.TokenType.GE; 
			break;
		case "<=": 
			tt = Token.TokenType.LE; 
			break;
		case "!=": 
			tt = Token.TokenType.NE; 
			break;
		case "(": 
			tt = Token.TokenType.OPEN_PAR; 
			break;
		case ")": 
			tt = Token.TokenType.CLOSE_PAR; 
			break;
		case "{": 
			tt = Token.TokenType.OPEN_KEY;
			break;
		case "}": 
			tt = Token.TokenType.CLOSE_KEY; 
			break;
		case ";": 
			tt = Token.TokenType.SEMICOLLON;
			break;
		}
		return(new Token(tt, simbolo, 0.0, st.lineno()));
	}
	
}
import java.io.*;
import java.util.*;

public class Tokenizer {
	BufferedReader br;
	StreamTokenizer st = null;

	Tokenizer(BufferedReader br) {
		this.br = br;
	}

	public void run(ArrayList<Token> tokens, SymbolTable sb){
		try {
			st = new StreamTokenizer(br);

			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				
				if (String.valueOf((char) st.ttype).equals("\'")) {
					tokens.add(new Token(TokenType.CHAR, st.sval, 0, st.lineno()));
				} else if (String.valueOf((char) st.ttype).equals("\"")) {
					tokens.add(new Token(TokenType.STRING, st.sval, 0, st.lineno()));
				} else if (st.ttype == StreamTokenizer.TT_NUMBER) {
					tokens.add(new Token(TokenType.NUMBER, Integer.toString((int)st.nval), (int)st.nval, st.lineno()));
				} else if(st.ttype == StreamTokenizer.TT_WORD) {
					tokens.add(buildWordToken(st.sval));
				} else if ((st.ttype != StreamTokenizer.TT_EOF) || (st.ttype != StreamTokenizer.TT_EOL)) {
					tokens.add(buildSimbolToken(st.ttype));
				}
				
				if (tokens.get(tokens.size()-1).getType() == TokenType.ID) {
					sb.put(tokens.get(tokens.size()-1).getKey(), new TableEntry());
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String buildIdName(String word){
		try {
			while(st.nextToken() == '_' || st.ttype == StreamTokenizer.TT_WORD || st.ttype == StreamTokenizer.TT_NUMBER) {
				if(st.ttype == StreamTokenizer.TT_WORD)
					word += st.sval;
				else if(st.ttype == StreamTokenizer.TT_NUMBER)
					word += String.valueOf((int)st.nval);
				else if(st.ttype == '_')
					word += '_';
			}
			st.pushBack();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return word;
	}

	private Token buildWordToken(String word){
		TokenType tt = null;

		switch(word.toUpperCase()) {
		case "IF": 
			tt = TokenType.IF_DECL;
			break;
		case "ELSE":
			tt = TokenType.ELSE_DECL; 
			break;
		case "WHILE":
			tt = TokenType.WHILE_DECL; 
			break;
		case "INT":
			tt = TokenType.INT_TYPE; 
			break;
		case "CHAR":
			tt = TokenType.CHAR_TYPE; 
			break;
		case "BOOL":
			tt = TokenType.BOOL_TYPE; 
			break;
		case "TRUE": 
			tt = TokenType.BOOL; 
			break;
		case "FALSE": 
			tt = TokenType.BOOL; 
			break;
		case "PRINT": 
			tt = TokenType.PRINT; 
			break;
		default:
			tt = TokenType.ID;
			word = buildIdName(word);
		}
		
		return(new Token(tt, word, 0, st.lineno()));
	}

	private Token buildSimbolToken(int val) throws IOException{
		TokenType tt = null;
		String simbolo = String.valueOf((char) val);

		switch(simbolo) {
		case "=": 
				if(st.nextToken() == '=') {
					tt = TokenType.EQ;
					simbolo += '=';
				} else {
					tt = TokenType.ATTR;
					st.pushBack();
				}
				break;
		case "+": 
			tt = TokenType.PLUS; 
			break;
		case "-": 
			tt = TokenType.MINUS; 
			break;
		case "*":
			tt = TokenType.MULT; 
			break;
		case "/": 
			tt = TokenType.DIV;
			break;
		case ">":
			if(st.nextToken() == '=') {
				tt = TokenType.GE;
				simbolo += '=';
			} else {
				tt = TokenType.GT;
				st.pushBack();
			}
			break;
		case "<":
			if(st.nextToken() == '=') {
				tt = TokenType.LE;
				simbolo += '=';
			} else {
				tt = TokenType.LT;
				st.pushBack();
			}
			break;
		case "!": 
			if(st.nextToken() == '=') {
				tt = TokenType.NE;
				simbolo += '=';
			} else {
				st.pushBack();
			}
			break;
		case "(": 
			tt = TokenType.L_PAR; 
			break;
		case ")": 
			tt = TokenType.R_PAR; 
			break;
		case "{": 
			tt = TokenType.L_BRACE;
			break;
		case "}": 
			tt = TokenType.R_BRACE; 
			break;
		case ";": 
			tt = TokenType.SEMICOLLON;
			break;
		case ",": 
			tt = TokenType.COMMA;
			break;
		case "_":
			tt = TokenType.ID;
			return(new Token(tt, buildIdName("_"), 0, st.lineno()));			
		}
		
		return(new Token(tt, simbolo, 0, st.lineno()));
	}
	
}
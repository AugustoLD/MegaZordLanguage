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
					tokens.add(new Token(Token.TokenType.CHAR, st.sval, 0, st.lineno()));
				} else if (String.valueOf((char) st.ttype).equals("\"")) {
					tokens.add(new Token(Token.TokenType.STRING, st.sval, 0, st.lineno()));
				} else if (st.ttype == StreamTokenizer.TT_NUMBER) {
					tokens.add(new Token(Token.TokenType.NUMBER, "", (int)st.nval, st.lineno()));
				} else if(st.ttype == StreamTokenizer.TT_WORD) {
					tokens.add(buildWordToken(st.sval));
				} else if ((st.ttype != StreamTokenizer.TT_EOF) || (st.ttype != StreamTokenizer.TT_EOL)) {
					tokens.add(buildSimbolToken(st.ttype));
				}
				
				if (tokens.get(tokens.size()-1).tipo == Token.TokenType.ID) {
					sb.put(tokens.get(tokens.size()-1).key, new TableEntry());
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
		Token.TokenType tt = null;

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
		case "PRINT": 
			tt = Token.TokenType.PRINT; 
			break;
		default:
			tt = Token.TokenType.ID;
			word = buildIdName(word);
		}
		
		return(new Token(tt, word, 0, st.lineno()));
	}

	private Token buildSimbolToken(int val) throws IOException{
		Token.TokenType tt = null;
		String simbolo = String.valueOf((char) val);

		switch(simbolo) {
		case "=": 
				if(st.nextToken() == '=') {
					tt = Token.TokenType.EQ;
					simbolo += '=';
				} else {
					tt = Token.TokenType.ATTR;
					st.pushBack();
				}
				break;
		case "+": 
			tt = Token.TokenType.PLUS; 
			break;
		case "-": 
			tt = Token.TokenType.MINUS; 
			break;
		case "*":
			tt = Token.TokenType.MULT; 
			break;
		case "/": 
			tt = Token.TokenType.DIV;
			break;
		case ">":
			if(st.nextToken() == '=') {
				tt = Token.TokenType.GE;
				simbolo += '=';
			} else {
				tt = Token.TokenType.GT;
				st.pushBack();
			}
			break;
		case "<":
			if(st.nextToken() == '=') {
				tt = Token.TokenType.LE;
				simbolo += '=';
			} else {
				tt = Token.TokenType.LT;
				st.pushBack();
			}
			break;
		case "!": 
			if(st.nextToken() == '=') {
				tt = Token.TokenType.NE;
				simbolo += '=';
			} else {
				st.pushBack();
			}
			break;
		case "(": 
			tt = Token.TokenType.L_PAR; 
			break;
		case ")": 
			tt = Token.TokenType.R_PAR; 
			break;
		case "{": 
			tt = Token.TokenType.L_BRACE;
			break;
		case "}": 
			tt = Token.TokenType.R_BRACE; 
			break;
		case ";": 
			tt = Token.TokenType.SEMICOLLON;
			break;
		case ",": 
			tt = Token.TokenType.COMMA;
			break;
		case "_":
			tt = Token.TokenType.ID;
			return(new Token(tt, buildIdName("_"), 0, st.lineno()));			
		}
		
		return(new Token(tt, simbolo, 0, st.lineno()));
	}
	
}
import java.io.*;
import java.util.*;

public class Tokenizer {

	BufferedReader br;

	Tokenizer(BufferedReader br) {
		this.br = br;
	}

	public void run(Vector<Token> tokens, SymbolTable sb){
		StreamTokenizer st = null;

		try {
			st = new StreamTokenizer(br);
			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				if (String.valueOf((char) st.ttype).equals("\"")) {
					tokens.add(new Token(Token.TokenType.STRING, st.sval, 0.0));
				} else if (st.ttype == StreamTokenizer.TT_NUMBER) {
					tokens.add(new Token(Token.TokenType.NUMERIC, "", st.nval));
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
		default:
			tt = Token.TokenType.ID;
        	sval = word;
        	break;
		}
		return(new Token(tt, sval, 0.0));
	}

	private Token buildSimbolToken(int val){
		Token.TokenType tt = null;
		String simbolo = String.valueOf((char) val);

		switch(simbolo) {
		case "+": 
			tt = Token.TokenType.SUM; 
			break;
		default:
			tt = Token.TokenType.ID;
			break;
		}
		return(new Token(tt, simbolo, 0.0));
	}
	
}


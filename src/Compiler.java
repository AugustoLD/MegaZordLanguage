import java.io.*;
import java.util.*;

public class Compiler {

	public static void main(String args[]) {
		BufferedReader br = null;
		Tokenizer tok = null;
		Vector<Token> tokenList = new Vector<Token>();
		SymbolTable tabelaSimbolos = new SymbolTable();
	  
		try {
			br = new BufferedReader(new FileReader(args[0]));
			tok = new Tokenizer(br);
			tok.run(tokenList, tabelaSimbolos);

			Iterator<Token> it = tokenList.iterator();
	  
			while(it.hasNext()){
				System.out.println(it.next().toString());
			}
			
			System.out.println(tabelaSimbolos.toString());
  
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
  
}

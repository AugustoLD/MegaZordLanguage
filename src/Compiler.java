import java.io.*;
import java.util.*;

public class Compiler {
	
	public static void main(String args[]) {
		BufferedReader br = null;
		Tokenizer tok = null;
		Vector<Token> tokenList = new Vector<Token>();
		SymbolTable tabelaSimbolos = null;
	  
		try {
			br = new BufferedReader(new FileReader("example.mz"));
			tok = new Tokenizer(br);
			tok.run(tokenList, tabelaSimbolos);

			Iterator it = tokenList.iterator();
	  
			while(it.hasNext()){
				System.out.println(it.next().toString());
			}
  
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
  
}

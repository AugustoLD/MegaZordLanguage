import java.io.*;
import java.util.*;

public class Compiler {

	public static void main(String args[]) {
		BufferedReader br;
		Tokenizer tok;
		ArrayList<Token> tokenList = new ArrayList<>();
		SymbolTable symbolTable = new SymbolTable();
	  
		try {
			br = new BufferedReader(new FileReader(args[0]));
			tok = new Tokenizer(br);
			tok.run(tokenList, symbolTable);

			if(args.length > 1 && args[1].equals("simple")){
				System.out.println("==Token List==");
				Iterator<Token> it = tokenList.iterator();
				while(it.hasNext()){
					System.out.println(it.next().toString());
				}
			} else {
				TablePrinter.printTable("Token List", Token.getAttributesNames(), tokenList);
			}

			System.out.println("\n\n==Symbol Table (HashTable)==");
			System.out.println(symbolTable.toString());
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
  
}

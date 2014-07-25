import java.util.ArrayList;

public class TablePrinter {	
	
	public static void printTable(String title, ArrayList<String> head, ArrayList<Token> body) {
		int columnLength = biggestLength(head) + 10;
		
		String titleLine = setTitleLine(title, columnLength*head.size()+head.size()+1);
		String simpleLine = setSimpleLine(head.size(), columnLength);
		
		System.out.println(simpleLine);
		System.out.println(titleLine);
		
		System.out.println(simpleLine);
		System.out.println(valueLine(head, columnLength));
		System.out.println(simpleLine);
		
		for(int i = 0; i < body.size(); i++) {
			System.out.println(valueLine(body.get(i).getStringVector(), columnLength));
			System.out.println(simpleLine);
		}
	}

	private static int biggestLength(ArrayList<String> string) {
		int biggerLength = string.get(0).length();
		
		for(int i = 1; i < string.size(); i++) {
			if(string.get(i).length() > biggerLength) {
				biggerLength = string.get(i).length();
			}
		}
		
		return biggerLength;
	}
	
	private static String setTitleLine(String title, int lineSize) {
		String titleLine = "|";
		
		while(titleLine.length() < (lineSize - title.length())/2) {
			titleLine += ' ';
		}
		titleLine += title;
		while(titleLine.length() < lineSize - 1) {
			titleLine += ' ';
		}
		titleLine += '|';
		
		return titleLine;
	}
	
	private static String setSimpleLine(int divisions, int columnSize) {
		String simpleLine  = "+";
		
		for(int i = 0; i < divisions; i++) {
			for(int j = 0; j < columnSize; j++) {
				simpleLine += '-';
			}
			simpleLine += '+';
		}

		return simpleLine;
	}
	
	private static String valueLine(ArrayList<String> values, int columnSize) {
		String line = "|";
		
		for(int i = 0; i < values.size(); i++) {
			String cell = " ";
			
			if(values.get(i).length() > columnSize-1) {
				cell += values.get(i).substring(0, columnSize-4);
				cell += ">> ";
			} else {
				cell += values.get(i);
				while(cell.length() < columnSize) {
					cell += ' ';
				}
			}
			
			line += cell + '|';
		}
		
		return line;
	}
}

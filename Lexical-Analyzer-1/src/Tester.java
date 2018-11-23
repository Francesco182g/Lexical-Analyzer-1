import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
	
	public static ArrayList<Token> tokens = new ArrayList<Token>();
	private static Scanner in;
	public static void main (String[] args) {
		String lessema = null;
		try {
			lessema = leggiFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		LexicalAnalyzer lexer = new LexicalAnalyzer();

			Token token = lexer.getToken(lessema);		
		/*
		System.out.println("Stampo i token trovati:");
		
		for(int i=0; i<tokens.size(); i++) {
			System.out.println(tokens.get(i).toString());
		}
		System.out.println("Tabella dei Simboli: " +LexicalAnalyzer.tabellasimboli.toString());
		*/
	}
	
	public static String leggiFile() throws FileNotFoundException {
	    File file= new File("C:\\Users\\Francesco\\Desktop\\Compilatori\\"+"Testo.txt");
	    in = new Scanner (file);
	    String lessema = "";
	    while (in.hasNext()) {
	      lessema += "" + in.nextLine();
	      lessema = lessema + "\n";
	    }

	    System.out.println(lessema);
		return lessema;
	  }
	
	public static void printToken(Token token) {
		System.out.println(token.toString());
	}
}

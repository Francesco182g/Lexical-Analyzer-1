import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
	
	private static Scanner in;
	public static void main (String[] args) {
		String lessema = null;
		try {
			lessema = leggiFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		LexicalAnalyzer lexer = new LexicalAnalyzer();
		lexer.getNextToken(lessema);
	}

	public static void getNextToken(Token token) {
		System.out.println(token);
	}
	
	public static String leggiFile() throws FileNotFoundException {
	    File file= new File("C:\\Users\\Francesco\\Desktop\\Compilatori\\"+"Testo.txt");
	    in = new Scanner (file);
	    String lessema = "";
	    while (in.hasNext()) {
	      lessema += "" + in.nextLine();
	      lessema = lessema + "\n";
	    }
		return lessema;
	  }
}

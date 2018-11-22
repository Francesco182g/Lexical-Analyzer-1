import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
	
	public static ArrayList<Token> token = new ArrayList<Token>();
	private static Scanner in;
	public static void main (String[] args) {
		String lessema = null;
		try {
			lessema = leggiFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		token = LexicalAnalyzer.getToken(lessema);
		System.out.println("Stampo i token trovati:");
		for(int i=0; i<token.size(); i++) {
			System.out.println(token.get(i).toString());
		}
		System.out.println("Tabella dei Simboli: " +LexicalAnalyzer.tabellasimboli.toString());
	}
	
	public static String leggiFile() throws FileNotFoundException {
	    File file= new File("C:\\Users\\Francesco\\Desktop\\Compilatori\\"+"Testo.txt");
	    in = new Scanner (file);
	    String lessema = "";
	    while (in.hasNextLine()) {
	      lessema += "" + in.nextLine();
	    }

	    System.out.println(lessema);
		return lessema;
	  }
}

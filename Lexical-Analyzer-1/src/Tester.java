import java.util.ArrayList;

public class Tester {
	
	public static ArrayList<Token> token = new ArrayList<Token>();
	public static void main (String[] args) {
		String lessema = "fdsfs fsfds";
		token = LexicalAnalyzer.getToken(lessema);
		for(int i=0; i<token.size(); i++) {
			System.out.println(token.get(i).toString());
		}
	}
}

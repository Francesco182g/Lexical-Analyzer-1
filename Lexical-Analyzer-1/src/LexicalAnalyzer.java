
import java.util.ArrayList;
import java.util.logging.Logger;

/*
 * LexicalAnalyzer:
 * 1. Legge l'input inviatogli dal tester getToken(String lessema)
 * 2. Contiene tutti i metodi per riconoscere i lessemi
 * 3. Prepara il token 
 * 4. Lo invia al Tester
 * 
 * Riconoscitore:
 * " "Space
 * "\t"Tab
 * "\f"Invio
 * 
 */
public class LexicalAnalyzer {
	public final static Logger log = Logger.getLogger(LexicalAnalyzer.class.getName());
	public static String ritorna;
	public static String token = "";
	public static int lTesto = 0;
	public static ArrayList tokens = new ArrayList<Token>();
	public static int i = 0;
	public static int id = 1;

	public static ArrayList getToken(String lessema){
		while(i<=lessema.length()) {
		token = findToken(lessema);
		Token to = new Token();
		if(token == "if") {
			token = "if";
			to.setId(token);
			to.setAttribute("Keyword");
		} else if (token == "then") {
			token = "then";
			to.setId(token);
			to.setAttribute("Keyword");
		} else if (token == "else") {
			token = "else";
			to.setId(token);
			to.setAttribute("Keyword");
		} else if (token == "while") {
			token = "while";
			to.setId(token);
			to.setAttribute("Keyword");
		} else if (token == "for") {
			token = "for";
			to.setId(token);
			to.setAttribute("Keyword");
		}
		else {
			for(int k=0; k<tokens.size(); k++) {
				Token t = new Token();
				if(t.getAttribute()== token) {
					tokens.add(t);
				} else {
					t.setId(""+id);
					t.setAttribute(token);
					id++;
				}
			}
		}
		tokens.add(token);
		token = "";
		}
		return tokens;
	}

	/*
	 * Trova il lessema passatogli da readLessema
	 */
	public static String findToken(String testo) {
		int stato = 0;
		lTesto = testo.length();
		//int backword = 0;
		char c;
		boolean active = true;
		while(active) {
			switch(stato) {


			/*
			 * CASE 0: IF Riconosci delimitator ELSE go stato 1
			 */
			case 0:
				c = testo.charAt(i);
				if(Character.isLetter(c)) {
					token = token + c;
					log.info("Case 0: isLetter");
					stato = 1;
				} else {
					System.out.println("Case 0 else 0");
				}
				//Controllo lunghezza testo
				if(i <= lTesto) {
					i++;
				} else {
					active = false;
					System.out.println("case 0 fine lunghezza");
				}
				break;


				/*
				 * CASE 1:
				 */
			case 1: 
				c = testo.charAt(i);
				if((Character.isLetter(c)) | (Character.isDigit(c))) {
					token = token + c;
					log.info("Case 1: isLetter OR isDigit");
				} else {
					i--;
				}
				//Controllo lunghezza testo
				if(i <= lTesto) {
					i++;
				} else {
					active = false;
					System.out.println("case 0 fine lunghezza");
				}
				break;

			case 2: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 2: isDigit");
				}else {
					
					
				}
				
				
				/*
				 * Default: Not WORK
				 */
			default:
				System.out.println("Finish");
				break;
			}
		}
		return token;
	}


	/*
	 * DELIMITERS AUTOMA
	 */
	public static void Delimiters(char c) {
		int stato = 0;
		if(c == ' ') {
			log.info("Delimiters - Delimiters - Case 0: Space find");
		} else if(c == '\t') {
			log.info("Delimiters - Case 0: Tab find");
		} else if(c == '\n') {
			log.info("Delimiters - Case 0: new line find");
		} else {
			log.info("Delimiters - Case 0: Not recognized");
		} 
	}
}

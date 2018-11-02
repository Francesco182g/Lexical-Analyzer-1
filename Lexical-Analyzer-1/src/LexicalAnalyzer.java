
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
	public static ArrayList <Token> tokens = new ArrayList<Token>();
	public static ArrayList <Token> tabellasimboli = new ArrayList<Token>();
	public static int i = 0;
	public static int id = 1;

	public static ArrayList getToken(String lessema){
		lTesto = lessema.length()-1;
		System.out.println(lTesto);
		while(i<lTesto) {
			System.out.println("Chiamo token");
			token = findToken(lessema);
			boolean isPres = false;
			if(token == "") {

			}else {
				Token to = new Token();
				to.setId(""+id);
				to.setAttribute(token);
				tokens.add(to);
				id++;	
			}
			token = "";
		}

		return tokens;
	}

	public static void addToArray() {

	}

	/*
	 * Trova il lessema passatogli da readLessema
	 */
	public static String findToken(String testo) {
		int stato = 0;
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

				//CASE 1 isLetter
				if(Character.isLetter(c)) {
					token = token + c;
					log.info("Case 0: isLetter");
					stato = 1;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo

					//CASE 2 isDigit
				} else if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 0: isDigit");
					stato = 2;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo

					//CASE 3 isBracket
				}else if(c == '(' | c == ')'| c == '[' | c == ']') {
					token = token + c;
					log.info("Case 0: isDigit");
					stato = 3;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo

				} else {
					System.out.println("Case 0 not recognized");
					active = false;
					if(i <lTesto) {
						i++;
						System.out.println("i++");
					}
					break;

				}
				//Controllo lunghezza testo
				break;

				/*
				 * CASE 1: Riconosce isLetter | isDigit else si ferma
				 */
			case 1: 
				c = testo.charAt(i);
				if((Character.isLetter(c)) | (Character.isDigit(c))) {
					token = token + c;
					log.info("Case 1: isLetter OR isDigit");
					if(i < lTesto) {
						i++;
					} else {
						active = false;
						System.out.println("case 1: fine lunghezza");
					}
				} else {
					active = false;
					break;
				}
				break;

				/*
				 * CASE 2: Riconosce isDigit else si ferma
				 */
			case 2: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 2: isDigit");
					if(i < lTesto) {
						i++;
					} else {
						active = false;
						System.out.println("case 2: fine lunghezza");
					}
				} else {
					active = false;
					break;
				}
				break;

				/*
				 * CASE 3: Si ferma a prescindere dall'input
				 */
			case 3: 
				active = false;
				if(i < lTesto) {
					i++;
				} else {
					active = false;
					System.out.println("case 2: fine lunghezza");
				}
				break;


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

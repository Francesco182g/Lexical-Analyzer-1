
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
				if(Character.isLetter(c)) {
					token = token + c;
					log.info("Case 0: isLetter");
					stato = 1;
					if(i < lTesto) {
						i++;
						System.out.println("i++");
						//CASE 4
					} else if(Character.isDigit(c)) {
						token = token + c;
						log.info("Case 0: isDigit");
						stato = 4;
						if(i < lTesto) {
							i++;
							System.out.println("i++");

						} else {
							active = false;
							System.out.println("case 0 fine lunghezza");
						}
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
				}

				/*
				 * CASE 1:
				 */
			case 1: 
				c = testo.charAt(i);
				if((Character.isLetter(c)) | (Character.isDigit(c))) {
					token = token + c;
					log.info("Case 1: isLetter OR isDigit");
					if(i < lTesto) {
						System.out.println(i);
						i++;
					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
				} else {
					active = false;
					break;
				}
				break;

			case 2: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 2: isDigit");
					if(i < lTesto) {
						System.out.println("pass");
						System.out.println(i);
						i++;
					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
				} else {
					active = false;
					break;
				}
				break;

			case 3:
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case3: ");
				} else {
					i--;
				}
				//Controllo lunghezza testo
				if(i <= lTesto) {
					i++;
				} else {
					active = false;
					System.out.println("case 3 fine lunghezza");
				}
				break;


			case 4:
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case4: ");
				} else {
					i--;
				}
				//Controllo lunghezza testo
				if(i <= lTesto) {
					i++;
				} else {
					active = false;
					System.out.println("case 3 fine lunghezza");
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

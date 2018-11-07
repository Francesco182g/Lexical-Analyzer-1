
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
			token = "";
		}

		return tokens;
	}

	/*
	 * Trova il lessema passatogli da readLessema e lo aggiunge all'array
	 */
	public static String findToken(String testo) {
		int stato = 0;
		//int backword = 0;
		char c;
		boolean active = true;
		Token to = new Token();
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

					//CASE 4 Relop<
				}else if(c == '<') {
					token = token + c;
					log.info("Case 0: isRelop <");
					stato = 4;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo

					//CASE 5 Relop>
				}else if(c == '>') {
					token = token + c;
					log.info("Case 0: isRelop >");
					stato = 5;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo

					//CASE 6 Relop=
				}else if(c == '=') {
					token = token + c;
					log.info("Case 0: isRelop =");
					stato = 6;
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo
					
					//CASE 3 Separat;
				}else if(c ==';') {
					token = token + c;
					log.info("Case 0: isSeparat ;");
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
					
					//CASE 3 Separat,
				}else if(c ==',') {
					token = token + c;
					log.info("Case 0: isSeparat ,");
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
					
					//Whitespace
				}else if(c ==' ') {
					log.info("Case 0: Space ");
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");
 
					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo

					//TAB
				}else if(c =='\t') {
					log.info("Case 0:tab ");
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
						System.out.println("i++");

					} else {
						active = false;
						System.out.println("case 0 fine lunghezza");
					}
					//Fine controllo lunghezza testo
					
					//New Line
				}else if(c =='\n') {
					log.info("Case 0: New Line ");
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
					Token toKey = new Token();
					toKey = CheckIsKeywords();
					if(toKey.getId() == null) {
						int ide = CheckIsPresent(token);
						if(ide == 0) {
							to.setId(""+id);
							to.setAttribute(token);
							tabellasimboli.add(to);
							tokens.add(to);
							id++;
						} else {
							to.setId(""+ide);
							to.setAttribute(token);
							tokens.add(to);
						}
						
					} else {
						tokens.add(toKey);
					}
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
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
					} else {
						active = false;
						System.out.println("case 2: fine lunghezza");
					}
					//Fine Cotrollo Lunghezza testo
				} else {
					to.setId("N_Const");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;

				/*
				 * CASE 3: Si ferma a prescindere dall'input
				 */
			case 3:
				to.setId("Separator");
				to.setAttribute(token);
				tokens.add(to);
				active = false;
				break;

				/*
				 * CASE 4: Riconosce Relop= o Relop> altrimenti si ferma
				 */
			case 4: 
				c = testo.charAt(i);
				if(c == '=') {
					token = token + c;
					log.info("Case 4: is Relop=");
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
					} else {
						active = false;
						System.out.println("case 4: fine lunghezza");
					}
					// Fine Cotrollo Lunghezza testo
				} else if(c == '>') {
					token = token + c;
					log.info("Case 4: is Relop>");
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
					} else {
						active = false;
						System.out.println("case 4: fine lunghezza");
					}
					//Fine Cotrollo Lunghezza testo
				} else {
					to.setId("Relop");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;
				
				/*
				 * CASE 5: Riconosciuto Relop< o trova Relop= o si ferma
				 */
			case 5: 
				c = testo.charAt(i);
				if(c == '=') {
					token = token + c;
					log.info("Case 5: is Relop=");
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
					} else {
						active = false;
						System.out.println("case 5: fine lunghezza");
					}
					// Fine Cotrollo Lunghezza testo
				} else {
					to.setId("Relop");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;

				/*
				 * CASE 6: Riconosciuto Relop= o trova Relop= o  si ferma
				 */
			case 6: 
				c = testo.charAt(i);
				if(c == '=') {
					token = token + c;
					log.info("Case 6: is Relop=");
					//Cotrollo Lunghezza testo
					if(i < lTesto) {
						i++;
					} else {
						active = false;
						System.out.println("case 6: fine lunghezza");
					}
					// Fine Cotrollo Lunghezza testo
				} else {
					to.setId("Relop");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;

				/*
				 * Default: Not WORK
				 */
			default:
				System.out.println("Default: null");
				break;
			}
		}
		return token;
	}


	public static Token CheckIsKeywords() {
		Token to = new Token();
		if(token.equals("if")) {
			to.setId("Keywords");
			to.setAttribute("IF");
		} else if(token.equals("then")) {
			to.setId("Keywords");
			to.setAttribute("THEN");
		} else if(token.equals("else")) {
			to.setId("Keywords");
			to.setAttribute("ELSE");
		} else if(token.equals("while")) {
			to.setId("Keywords");
			to.setAttribute("WHILE");
		} else if(token.equals("for")) {
			to.setId("Keywords");
			to.setAttribute("FOR");
		} else {
			log.severe("SEZIONE CheckIsKeywords: NESSUNA KEY TROVATA!");
		}
		return to;
	}
	
	

	public static int CheckIsPresent(String token) {
		int ide = 0;
		System.out.println("Stampo token: "+token);
		for(int k=0; k<tabellasimboli.size(); k++) {
			if(tabellasimboli.get(k).getAttribute().equals(token)){
				ide = Integer.parseInt(tabellasimboli.get(k).getId());
				System.out.println(ide);
			} else {
				log.info("CheckIsPresent: nothing");
			}
		}
		return ide;
	}
	
	/*
	 * DELIMITERS AUTOMA NOT USED
	 * 
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
		 */
}

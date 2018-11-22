
import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/*
 * LexicalAnalyzer:
 * 1. Legge l'input inviatogli dal tester getToken(String lessema)
 * 2. Contiene tutti i metodi per riconoscere i lessemi
 * 3. Riconosce il token e lo aggiunge alla tabella dei simboli e al flusso di token 
 * 4. Lo invia al Tester
 */

public class LexicalAnalyzer {
	public final static Logger log = Logger.getLogger(LexicalAnalyzer.class.getName());
	public String ritorna;
	public static String token;
	public static int lTesto = 0;
	public static ArrayList <Token> tokens = new ArrayList<Token>();
	public static ArrayList <Token> tabellasimboli = new ArrayList<Token>();
	public static int i = 0;
	public static int id = 1;


	public static ArrayList getToken(String lessema){
		LogManager.getLogManager().reset();
		lTesto = lessema.length()-1;
		System.out.println("Lunghezza Testo: " +lTesto +" Caratteri");
		while(i<lTesto) {
			token = findToken(lessema);
		}
		return tokens;
	}

	/*
	 * Trova il lessema passatogli da readLessema e lo aggiunge all'array
	 */
	public static String findToken(String testo) {
		int stato = 0;
		char c;
		boolean active = true;
		token = "";
		Token to = new Token();
		while(active) {
			switch(stato) {

			/*
			 * CASE 0
			 */
			case 0:
				c = testo.charAt(i);

				/*
				 * CASE 0-1: If c is letter goto stato1
				 */
				if(Character.isLetter(c)) {
					token = token + c;
					log.info("Case 0: isLetter");
					stato = 1;
					active = checkLengthText();

					/*
					 * CASE 0-2: else if c is digit goto stato2
					 */
				} else if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 0: isDigit");
					stato = 2;
					active = checkLengthText();


					/*
					 * CASE 0-3: c is ( or ) or [ or ]
					 */
				}else if(c == '(' | c == ')'| c == '[' | c == ']') {
					token = token + c;
					log.info("Case 0: isSeparator");
					stato = 3;
					active = checkLengthText();


					/*
					 * CASE 0-4: c is relop < 
					 */
				}else if(c == '<') {
					token = token + c;
					log.info("Case 0: isRelop <");
					stato = 4;
					active = checkLengthText();


					/*
					 * CASE 0-5: c is relop > 
					 */
				}else if(c == '>') {
					token = token + c;
					log.info("Case 0: isRelop >");
					stato = 5;
					active = checkLengthText();

					/*
					 * CASE 0-6: c is relop =
					 */
				}else if(c == '=') {
					token = token + c;
					log.info("Case 0: isRelop =");
					stato = 6;
					active = checkLengthText();


					/*
					 * CASE 0-3: c is Separator ;
					 */
				}else if(c ==';') {
					token = ";";
					log.info("Case 0: isSeparat ;");
					stato = 3;
					active = checkLengthText();


					/*
					 * CASE 0-3: c is Separator ,
					 */
				}else if(c ==',') {
					token = ",";
					log.info("Case 0: isSeparat ,");
					stato = 3;
					active = checkLengthText();


					/*
					 * CASE 0-Stop: WhiteSpace - Ignore it
					 */				
				}else if(c ==' ') {
					log.info("Case 0: Space ");
					active = checkLengthText();


					/*
					 * CASE 0-Stop: WhiteSpace - Tab it
					 */							
					}else if(c =='\t') {
					log.info("Case 0:tab ");
					active = checkLengthText();


					/*
					 * CASE 0-Stop: NewLine - Ignore it
					 */			
				}else if(c =='\n') {
					log.info("Case 0: New Line ");
					active = checkLengthText();

				} else {
					log.info("Case 0 not recognized");
					active = false;
					if(i <lTesto) {
						i++;
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
					active = checkLengthText();

				} else {
					Token toKey = new Token();
					toKey = checkIsKeywords();
					if(toKey.getId() == null) {
						int ide = checkIsPresent(token);
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
					stato = 9; //goto Stato 9
					active = checkLengthText();

				} else {
					to.setId("Nconst");
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
					token = "<=";
					log.info("Case 4: is Relop=");
					active = checkLengthText();

				} else if(c == '>') {
					token = "<>";
					log.info("Case 4: is Relop>");
					active = checkLengthText();

				}else if(c == '-') {
					if(i < lTesto) {
						if(testo.charAt(i+1) == '-') {
							token = "<--";
							log.info("Case 4: is Relop<-");
							active = checkLengthText();

						}
					}
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
					active = checkLengthText();

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
					active = checkLengthText();

				} else {
					to.setId("Relop");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;

				/*
				 * CASE 9: Riconosciuto Digit, riconosce . | E | altri digits
				 */
			case 9: 
				c = testo.charAt(i);
				/*
				 * Controlla se il char è un digit
				 */
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 9: is .");
					stato = 9;
					active = checkLengthText();


					/*
					 * Controlla se il char è un 'E'
					 * Controlla se dopo la E c'è un digit, un + e un -,
					 * TRUE: Aggiungi E al token e vai avanti allo stato 12
					 * FALSE: Torna indietro con il puntatore, vai allo stato 0
					 */
				} else if(c == 'E'){
					char q = testo.charAt(i+1);
					if((Character.isDigit(q)) | (q=='+') | (q=='-')) {
						token = token + c;
						log.info("Case 9: is .");
						stato = 12;
						active = checkLengthText();

					} else {
						i--;
						stato = 0;
					}
					/*
					 * Controlla se il char è un '.'
					 * Controlla se dopo la . c'è un digit
					 * TRUE: Aggiungi '.' al token e vai avanti allo stato 10
					 * FALSE: Torna indietro con il puntatore, vai allo stato 0
					 */
				} else if(c == '.') {
					if(Character.isDigit(testo.charAt(i+1))) {
						token = token + c;
						log.info("Case 9: is .");
						stato = 10;
						active = checkLengthText();

					} else {
						i--;
						stato = 0;
					}

				} else {
					to.setId("Nconst");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;

				/*
				 * CASE 10: Riconosciuto ., riconosce digit
				 */
			case 10: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 10: is digit");
					stato = 11;
					active = checkLengthText();

				} else {
					to.setId("Nconst");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;

				/*
				 * CASE 11: Riconosciuto digit, riconosce digits
				 */
			case 11: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 11: is digit");
					stato = 11;
					active = checkLengthText();

				}else if(c == 'E') {
					token = token + c;
					log.info("Case 11: is E");
					stato = 12;
					active = checkLengthText();
				
				}else{
					to.setId("Nconst");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;

				/*
				 * CASE 12: Riconosciuto digit, riconosce E
				 */
			case 12: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 12: is E");
					stato = 12;
					active = checkLengthText();

				} else if((c=='+')|(c=='-')) {
					if(Character.isDigit(testo.charAt(i+1))) {
						token = token + c;
						log.info("Case 12: is +|-");
						stato = 13;
						active = checkLengthText();

					}
				}else{
					to.setId("Nconst");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;

				/*
				 * CASE 13: Riconosciuto +|-, riconosce digits
				 */
			case 13: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 12: is E");
					stato = 13;
					active = checkLengthText();

				}else{
					to.setId("Nconst");
					to.setAttribute(token);
					tokens.add(to);
					active = false;
					break;
				}
				break;

				/*
				 * Default: Null
				 */
			default:
				System.out.println("Default: null");
				break;
			}
		}
		return token;
	}


	public static Token checkIsKeywords() {
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
			log.severe("SEZIONE checkIsKeywords: NESSUNA KEY TROVATA!");
		}
		return to;
	}

	public static int checkIsPresent(String token) {
		int ide = 0;
		for(int k=0; k<tabellasimboli.size(); k++) {
			if(tabellasimboli.get(k).getAttribute().equals(token)){
				ide = Integer.parseInt(tabellasimboli.get(k).getId());
			} else {
				log.info("CheckIsPresent: nothing");
			}
		}
		return ide;
	}
	
	public static boolean checkLengthText() {
		boolean active = true;
		if(i < lTesto) {
			i++;
		} else {
			active = false;
		}
		return active;
	}
}


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
	public static ArrayList <Token> tabellasimboli = new ArrayList<Token>();
	public static int i = 0;
	public static int id = 1;

	public Token getToken(String lessema){
		//LogManager.getLogManager().reset();
		int lTesto = lessema.length()-1;
		System.out.println("Lunghezza Testo: " +lTesto +" Caratteri");
		Token token = new Token();
		while(i<lTesto) {
			token = findToken(lessema, lTesto);
			System.out.println(token.toString());
		}
		return token;
	}

	/*
	 * Trova il lessema passatogli da readLessema e lo aggiunge all'array
	 */
	public Token findToken(String testo, int lTesto) {
		int stato = 0;
		char c;
		boolean active = true;
		String token = "";
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
					active = checkLengthText(lTesto);

					/*
					 * CASE 0-2: else if c is digit goto stato2
					 */
				} else if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 0: isDigit");
					stato = 2;
					active = checkLengthText(lTesto);

					/*
					 * CASE 0-3: c is ( or ) or [ or ]
					 */
				}else if(c == '(' | c == ')'| c == '[' | c == ']' | c == ';' | c == ',') {
					token = token + c;
					log.info("Case 0: isSeparator");
					stato = 3;
					active = checkLengthText(lTesto);

					/*
					 * CASE 0-4: c is relop < 
					 */
				}else if(c == '<') {
					token = token + c;
					log.info("Case 0: isRelop <");
					stato = 4;
					active = checkLengthText(lTesto);

					/*
					 * CASE 0-5: c is relop > 
					 */
				}else if(c == '>') {
					token = token + c;
					log.info("Case 0: isRelop >");
					stato = 5;
					active = checkLengthText(lTesto);

					/*
					 * CASE 0-6: c is relop =
					 */
				}else if(c == '=') {
					token = token + c;
					log.info("Case 0: isRelop =");
					stato = 6;
					active = checkLengthText(lTesto);

					/*
					 * CASE 0-Stop: WhiteSpace - Ignore it
					 */				
				}else if(c ==' ') {
					log.info("Case 0: Space ");
					active = checkLengthText(lTesto);

					/*
					 * CASE 0-Stop: WhiteSpace - Tab it
					 */							
				}else if(c =='\t') {
					log.info("Case 0:tab ");
					active = checkLengthText(lTesto);

					/*
					 * CASE 0-Stop: NewLine - Ignore it
					 */			
				}else if(c =='\n') {
					log.info("Case 0: New Line ");
					active = checkLengthText(lTesto);

					/*
					 * CASE 0-Stop: Charcater not recognized
					 */	
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
				 * CASE 1: Recognized isLetter | isDigit else stop
				 */
			case 1: 
				c = testo.charAt(i);
				if((Character.isLetter(c)) | (Character.isDigit(c))) {
					token = token + c;
					log.info("Case 1: isLetter OR isDigit");
					active = checkLengthText(lTesto);

				} else {
					Token toKey = new Token();
					toKey = checkIsKeywords(token);
					if(toKey.getId() == null) {
						int ide = checkIsPresent(token);
						if(ide == 0) {
							to.setId(""+id);
							to.setAttribute(token);
							tabellasimboli.add(to);
							id++;
							return to;
						} else {
							to.setId(""+ide);
							to.setAttribute(token);
							return to;
						}
					} else {
						return toKey;
					}
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
					active = checkLengthText(lTesto);

				} else {
					to.setId("Nconst");
					to.setAttribute(token);
					return to;
				}
				break;

				/*
				 * CASE 3: Separator - Stop
				 */
			case 3:
				to.setId("Separator");
				to.setAttribute(token);
				return to;

				/*
				 * CASE 4: Recognized Relop= o Relop> altrimenti si ferma
				 */
			case 4: 
				c = testo.charAt(i);
				/*
				 * CASE 4: Recognized Relop= 
				 */
				if(c == '=') {
					token = "<=";
					log.info("Case 4: is Relop=");
					active = checkLengthText(lTesto);

				} else if(c == '>') {
					token = "<>";
					log.info("Case 4: is Relop>");
					active = checkLengthText(lTesto);

				}else if(c == '-') {
					if(i < lTesto) {
						if(testo.charAt(i+1) == '-') {
							token = "<--";
							log.info("Case 4: is Relop<-");
							active = checkLengthText(lTesto);
						}
					}
				} else {
					to.setId("Relop");
					to.setAttribute(token);
					return to;
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
					active = checkLengthText(lTesto);

				} else {
					to.setId("Relop");
					to.setAttribute(token);
					return to;
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
					active = checkLengthText(lTesto);

				} else {
					to.setId("Relop");
					to.setAttribute(token);
					return to;
				}
				break;

				/*
				 * CASE 9: Riconosciuto Digit, riconosce . | E | altri digits
				 */
			case 9: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 9: is digit");
					stato = 9;
					active = checkLengthText(lTesto);

					//Cotrolla se è . e se dopo c'è digit
				} else if(c == '.'){
					if(i+1 < lTesto & Character.isDigit(testo.charAt(i+1))) {
						token = token + c;
						log.info("Case 9: is . and after there is digit");
						stato = 10;
						active = checkLengthText(lTesto);
					}

					//Cotrolla se è E e se dopo c'è digit | + | -					
				} else if(c == 'E'){
					if(i+1< lTesto) {
						char t = testo.charAt(i+1);
						if((Character.isDigit(t)) | (t == '+') | (t == '-')) {
							token = token + c;
							log.info("Case 9: is E and after there is digit or + or -");
							stato = 12;
							active = checkLengthText(lTesto);
						}
					} else {
						to.setId("Nconst");
						to.setAttribute(token);
						log.info("Stato 9 ritorna digit");
						return to;
					}
				} else {
					to.setId("Nconst");
					to.setAttribute(token);
					log.info("Stato 9 ritorna digit");
					return to;
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
					stato = 10;
					active = checkLengthText(lTesto);

				} else {
					to.setId("Nconst");
					to.setAttribute(token);
					return to;
				}
				break;

				/*
				 * CASE 11: Riconosciuto digit, riconosce digits
				 */
			case 11: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {

				}else{
					to.setId("Nconst");
					to.setAttribute(token);
					return to;
				}
				break;

				/*
				 * CASE 12: Riconosciuto digit, riconosce E
				 */
			case 12: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 12: is digit");
					stato = 12;
					active = checkLengthText(lTesto);

				} else if((c=='+')|(c=='-')) {
					char t = testo.charAt(i+1);
					token = token + c;
					log.info("Case 12: is +|-");
					stato = 13;
					active = checkLengthText(lTesto);
				}else{
					to.setId("Nconst");
					to.setAttribute(token);
					return to;
				}
				break;

				/*
				 * CASE 13: Riconosciuto +|-, riconosce digits
				 */
			case 13: 
				c = testo.charAt(i);
				if(Character.isDigit(c)) {
					token = token + c;
					log.info("Case 13: is digit");
					stato = 13;
					active = checkLengthText(lTesto);

				}else{
					to.setId("Nconst");
					to.setAttribute(token);
					return to;
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
		return to;
	}



	public static Token checkIsKeywords(String token) {
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

	public boolean checkLengthText(int lTesto) {
		boolean active = true;
		if(i < lTesto) {
			i++;
		} else {
			active = false;
		}
		return active;
	}
}

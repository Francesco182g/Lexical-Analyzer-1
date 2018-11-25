import java.util.HashMap;
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
	private static HashMap<String, Token> symbolTable = new HashMap<>();
	public int i = 0;
	public int id = 1;

	public void getNextToken(String lessema){
		LogManager.getLogManager().reset();
		int lTesto = lessema.length()-1;
		System.out.println("Lunghezza Testo: " +lTesto +" Caratteri");
		Token token = new Token();
		while(i<lTesto) {
			token = findToken(lessema, lTesto);
			if(token.getId() != "NULL")
			Tester.getNextToken(token);
		}
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
						int ide = installID(token);
						if(ide == 0) {
							to.setId(""+id);
							to.setAttribute(token);
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
					stato = 2;
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
							to.setId("Relop");
							to.setAttribute(token);
							return to;
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
				 * Default: Null
				 */
			default:
				System.out.println("Default: null");
				break;
			}
		}
		to.setAttribute("END TEXT");
		to.setId("NULL");
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

	public int installID(String token) {
		int ide = 0;
		Token to = new Token();	
		if (symbolTable.containsKey(token)) {
			to = symbolTable.get(token);
			ide = Integer.parseInt(to.getId());
		}else {
			to = new Token(""+id, token);
			symbolTable.put(token, to);
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

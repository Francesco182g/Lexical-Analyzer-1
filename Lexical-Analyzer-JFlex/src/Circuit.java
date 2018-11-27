import java.util.HashMap;

// Lexical Analyzer based on Ian Stark


public class Circuit {
	private static HashMap<String, Token> symbolTable = new HashMap<>();

	public static void main(String[] args) throws Exception {

		System.out.println("Type in input, hit Return");
		System.out.println("To finish hit Return then Ctrl-Z");
		System.out.println("(if it does not finish, before Ctrl-Z/D give focus to another window first)");
		Yylex yy = new Yylex(System.in);

		for(int tokenId= yy.next_token().sym; tokenId != CircuitSym.EOF; tokenId  = yy.next_token().sym) {
			String attributo = CircuitSym.terminalNames[tokenId];
			String id = yy.yytext().toString();
			System.out.println("token returned is "+ 
					id +" "+  "Attribute: " + attributo +"\n");
		}
		System.out.println("The end, thank you! ");
	}

	
	/*
	 * Verify if the token is already in SymbolTable else add it
	 */
	public static int installID(String id, String attributo) {
		int ide = 0;
		Token to = new Token();	
		if (symbolTable.containsKey(attributo)) {
			to = symbolTable.get(attributo);
			ide = Integer.parseInt(to.getId());
		}else {
			to = new Token(""+id, attributo);
			symbolTable.put(attributo, to);
		}
		return ide;
	}
}


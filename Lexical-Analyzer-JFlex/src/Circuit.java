import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

// Lexical Analyzer based on Ian Stark Example


public class Circuit {

	private static HashMap<String, Token> symbolTable = new HashMap<>();
	public static int ide = 0;
	public static void main(String[] args) throws Exception{
		System.out.println("To finish hit Return then Ctrl-Z");
		getToken();
	}

	/*
	 * Scanner and print token
	 */
	public static void getToken() throws Exception {

		Yylex yy = new Yylex(System.in);

		for(int tokenId= yy.next_token().sym; tokenId != CircuitSym.EOF; tokenId  = yy.next_token().sym) {

			String attribute = CircuitSym.terminalNames[tokenId];
			String id1 = yy.yytext().toString();
			String id = id1;
			if(attribute == "ID") {
				id = installID(attribute);
			}
			System.out.println("token: "+ 
					id +" "+  "Class: " + attribute +"\n");
		}
	}

	/*
	 * Verify if the token is already in SymbolTable else add it
	 */
	public static String installID(String attributo) {
		String id2 = "";
		Token to = new Token();	
		if (symbolTable.containsKey(attributo)) {
			to = symbolTable.get(attributo);
			id2 = to.getId();
		}else {
			to = new Token(""+id2, attributo);
			symbolTable.put(attributo, to);
			id2 = ""+ide;
			ide++;
		}
		return id2;
	}


	public static String checkIsPresent(String id1) {
		return id1;

	}
}


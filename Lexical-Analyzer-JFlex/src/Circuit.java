import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

// Lexical Analyzer based on Ian Stark Example
//Created by Francesco Garofalo (francesco182g on git)

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
			String token_name = CircuitSym.terminalNames[tokenId];
			String attribute = yy.yytext().toString();
			
			if(token_name == "ID") {
				attribute = installID(attribute);
			}else {
				System.out.println("Tipo: "+token_name+" Attributo: "+attribute);
			}
		}
	}

	
	/*
	 * Verify if the token is already in SymbolTable else add it
	 */
	public static String installID(String attribute) {
		Token to = new Token();	
		if (symbolTable.containsKey(attribute)) {
			to = symbolTable.get(attribute);
			System.out.println("ID: "+to.getId()+" Attributo: "+to.getAttribute()+"Presente");
		}else {
			to = new Token(""+ide, ""+attribute);
			symbolTable.put(attribute, to);
			System.out.println("ID: "+ide+" Attributo: "+to.getAttribute()+"Nuovo Elemento");
			ide++;
		}
		return attribute;
	}
	
}


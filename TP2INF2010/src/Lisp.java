import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class Lisp {


	/*
	 * cette fonction permet de resoudre  une expresion Lisp   
	 * le paramètre peut être transformer en token à l'aide de la fonction getTokens(expresion) 
	 * NB une seule pile peut être utilisée
	 * retourn "double" le resultat de l'expresion 
	 */
	static public  double solve(String expression){
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0; i<expression.length();i++)
			stack.push(expression.charAt(i));
		switch(stack.pop()){

		}

	}			
	/*
	 * cette fonction vérifier si une expression est équilibree 
	 * i.e. toutes parenthèse ouverte à une parenthèse fermante
	 * N.B: une seule pile peut être utilisée 
	 * return true si equilibree, false sionon
	 */

	static public boolean isEquilibre(String expression){		
		//Stack<String> stack = new Stack<String>();
		int amountLeft = 0, amountRight = 0;

		for(int i = 0; i<expression.length();i++){
			if(expression.charAt(i) == '(')
				amountRight++;
			if(expression.charAt(i) == ')')
				amountLeft++;
		}

		return amountRight == amountLeft;

		//	for(int i = 0; i<expression.length();i++)
		//		stack.push(expression.substring(i, i+1));
		//	while(!stack.isEmpty()){
		//		
		//	}
	}

	/*
	 * fonction transforme une expresion (String) lisp en tokens (Vector<String>)
	 */
	static public Vector<String> getTokens(String expression){

		Vector<String> vectorOfTokens=new Vector<String>();
		int lenght=expression.length();
		String token="",number="";
		//==
		for(int i=lenght-1;i>=0;i--) {
			//
			token=String.valueOf(expression.charAt(i));
			if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
				if(!number.isEmpty()){
					vectorOfTokens.addElement(number);
					number="";	
				}
				vectorOfTokens.addElement(token);
			}else if(token.equals(")")|| token.equals("(") ){
				if(!number.isEmpty()){
					vectorOfTokens.addElement(number);
					number="";	
				}	
				vectorOfTokens.addElement(token);				
			}else if(token.equals(" ")){
				if(!number.isEmpty()){
					vectorOfTokens.addElement(number);
					number="";	
				}	
			}else if(!token.equals(" ")){
				number=token+number;		
			}
		}
		// invert vectorOfTokens;
		Collections.reverse(vectorOfTokens);

		return vectorOfTokens;
	}


}

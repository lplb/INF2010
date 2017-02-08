import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class Lisp {


	/*
	 * cette fonction permet de resoudre  une expresion Lisp   
	 * le paramètre peut être transformer en token à l'aide de la fonction getTokens(expression) 
	 * NB une seule pile peut être utilisée
	 * retourn "double" le resultat de l'expresion 
	 */
	static public  double solve(String expression){
		Stack<Character> stack = new Stack<Character>();
		String nombre = "";
		double res = 0;
		for(int i = expression.length()-1; i>=0;i--){
			switch(expression.charAt(i)){
			case ' ':
			case '+':
			case '-':
			case '*':
			case '/':
			case ')':
				stack.push(expression.charAt(i));
				System.out.println(stack);
				break;
			case '(':
				res = calculerResultatPartiel(stack);
				mettreNombreDansPile(res,stack);
				break;
			default:
				while((expression.charAt(i)<'9'&&expression.charAt(i)>'0')|expression.charAt(i)=='.'){
					stack.push(expression.charAt(i));
					i--;
				}
			}
		}
		return sortirNombrePile(stack);
	}
		/*
		Stack<Double> stack = new Stack<Double>();
		String nombre = "";
		for(int i = 0; i<expression.length();i++){
			
*/

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
	
	static private double calculerResultatPartiel(Stack<Character> stack){
		double res = 0;
		switch(stack.pop()){
		case '+':
			res = sortirNombrePile(stack);
			while(stack.peek()!=')'){
				stack.pop();
				res += sortirNombrePile(stack);
			}
			break;
		case '-':
			res = sortirNombrePile(stack);
			while(stack.peek()!=')'){
				stack.pop();
				res -= sortirNombrePile(stack);						
			}
			break;
		case '*':
			res = sortirNombrePile(stack);
			while(stack.peek()!=')'){
				stack.pop();
				res *= sortirNombrePile(stack);
			}
			break;
		case '/':
			res = sortirNombrePile(stack);
			while(stack.peek()!=')'){
				stack.pop();
				res /= sortirNombrePile(stack);
			}
			break;
		}
		return res;
	}
	
	static private double sortirNombrePile(Stack<Character> stack){
		String nombre = "";
		while(stack.peek()!=' '&&stack.peek()!=')'){
			nombre+=stack.pop();
		}
		return Double.parseDouble(nombre);
	}
	
	private static void mettreNombreDansPile(double res, Stack<Character> stack) {
		if(stack.peek()<'9'&&stack.peek()>'0'){
			stack.push(' ');
		}
		String nombre = res+"";
		for(int i = nombre.length()-1;i>=0;i--){
			stack.push(nombre.charAt(i));
		}
		//System.out.println(nombre);
		//System.out.println(stack);
	}
}

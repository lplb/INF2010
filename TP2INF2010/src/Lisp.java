import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class Lisp {


	/**
	 * cette fonction permet de resoudre  une expresion Lisp   
	 * le paramètre peut être transformer en token à l'aide de la fonction getTokens(expression) 
	 * NB une seule pile peut être utilisée
	 * retourne "double" le resultat de l'expresion 
	 */
	static public  double solve(String expression){
		Stack<String> stack = new Stack<String>();
		String nombre = "";
		for(int i = expression.length()-1; i>=0;i--){
			switch(expression.charAt(i)){
			case ' ':
				break;
			case '+':
			case '-':
			case '*':
			case '/':
			case ')':
				stack.push(expression.charAt(i)+"");
				break;
			case '(':
				calculerResultatPartiel(stack);
				break;
			default:
				while((expression.charAt(i)<='9'&&expression.charAt(i)>='0')|expression.charAt(i)=='.'){
					nombre += expression.charAt(i);
					i--;
				}
				char[] nb = nombre.toCharArray();
				for(int j = 0, k = nb.length - 1; j < k; j++, k--){
					char temp = nb[j];
					nb[j] = nb[k];
					nb[k] = temp;
				}
				stack.push(new String (nb));
				nombre = "";
				i++;
			}
			System.out.println(stack);
		}
		return Double.parseDouble(stack.pop());
	}


	/**
	 * cette fonction vérifier si une expression est équilibree 
	 * i.e. toutes parenthèse ouverte à une parenthèse fermante
	 * N.B: une seule pile peut être utilisée 
	 * return true si equilibree, false sionon
	 */

	static public boolean isEquilibre(String expression){		
		int amountLeft = 0, amountRight = 0;

		for(int i = 0; i<expression.length();i++){
			if(expression.charAt(i) == '(')
				amountRight++;
			if(expression.charAt(i) == ')')
				amountLeft++;
		}
		return amountRight == amountLeft;
	}

	/**
	 * fonction transforme une expresion (String) lisp en tokens (Vector<String>)
	 * 
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
	
	/**
	 * Cette fonction calcule un le resultat d'une operation et pousse le resultat dans la pile
	 * @param stack La pile dans laquelle se situe l'expression a calculer
	 */
	static private void calculerResultatPartiel(Stack<String> stack){
		double res = 0;
		switch(stack.pop()){
		case "+":
			res = Double.parseDouble(stack.pop());
			while(!stack.peek().equals(")")){
				res += Double.parseDouble(stack.pop());;
			}
			break;
		case "-":
			res = Double.parseDouble(stack.pop());
			while(!stack.peek().equals(")")){
				res -= Double.parseDouble(stack.pop());;
			}
			break;
		case "*":
			res = Double.parseDouble(stack.pop());
			while(!stack.peek().equals(")")){
				res *= Double.parseDouble(stack.pop());
			}
			break;
		case "/":
			res = Double.parseDouble(stack.pop());
			while(!stack.peek().equals(")")){
				res /= Double.parseDouble(stack.pop());
			}
			break;
		}
		stack.pop();
		stack.push(res+"");
	}
	
}

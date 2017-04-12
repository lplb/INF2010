
import java.util.ArrayList;

public class Monceau {
	ArrayList<Node> arbres;

	public Monceau() {
		arbres = new ArrayList<Node>();
	}

	public void fusion(Monceau autre) {
		ArrayList<Node> arbreTemp = new ArrayList<Node>();
		int indexThis = 0;
		int indexAutre = 0;
		while((indexThis < arbres.size()) || (indexAutre < autre.arbres.size())){
			if (indexThis == arbres.size()){
				arbreTemp.add(autre.arbres.get(indexAutre));
				indexAutre ++;
			}else if (indexAutre == autre.arbres.size()){
				arbreTemp.add(arbres.get(indexThis));
				indexThis++;
			}else if (arbres.get(indexThis).ordre <= autre.arbres.get(indexAutre).ordre){
				arbreTemp.add(arbres.get(indexThis));
				indexThis++;
			}else{
				arbreTemp.add(autre.arbres.get(indexAutre));
				indexAutre++;

			}
		}
		
		int indexChange = 0;
		while(indexChange < arbreTemp.size() -1){
			Node temp = arbreTemp.get(indexChange);
			Node compareValue = arbreTemp.get(indexChange + 1);
			if(temp.ordre > compareValue.ordre){
				arbreTemp.set(indexChange, compareValue);
				arbreTemp.set(indexChange + 1, temp);
			} else if (temp.ordre == compareValue.ordre){
				try{
					arbreTemp.set(indexChange, temp.fusion(compareValue));
				}catch(DifferentOrderTrees e){}
				arbreTemp.remove(indexChange + 1); 
			} else {
				indexChange++;
			}
		}
		
		arbres = arbreTemp;
		autre.arbres = arbreTemp;

	}

	public void insert(int val) {
		Node nouvelArbre = new Node(val);
		Monceau nouveauMonceau = new Monceau();
		nouveauMonceau.arbres.add(nouvelArbre);
		fusion(nouveauMonceau);
	}

	public boolean delete (int val) {

		boolean deleted = false;
		for(int i = 0; i < arbres.size(); i++){
			if(arbres.get(i).findValue(val) != null){
				ArrayList<Node> arbreTemp = arbres.get(i).findValue(val).delete();
				Monceau monceauTemp = new Monceau();
				monceauTemp.arbres = arbreTemp;
				arbres.remove(arbres.get(i));
				fusion(monceauTemp);
				deleted = true;
				i = 0;
			}
		}
		return deleted;


	}

	public void print() {
		for(Node arbre: arbres){
			System.out.println("Arbre d'ordre " + arbre.ordre);
			arbre.print("  ");
		}
		System.out.println("__________________________________\n");
	}

}

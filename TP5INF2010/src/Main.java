import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Node noeud = new Node(2);
		noeud.print("  ");
		System.out.println("_________________________________________");
		try {
			noeud = noeud.fusion(new Node(4));
		} catch (DifferentOrderTrees e) {
			e.printStackTrace();
		}
		noeud.print("  ");
		System.out.println("_________________________________________");
		
		try {
			noeud = noeud.fusion(new Node(5));
		} catch (DifferentOrderTrees e) {
			System.out.println("Arbres de differents ordres");
		}
		
		Node noeud1 = new Node(1), noeud5 = new Node(5);
		try {
			noeud = noeud.fusion(noeud1.fusion(noeud5));
		} catch (DifferentOrderTrees e) {
			e.printStackTrace();
		}
		noeud.print("  ");
		System.out.println("_________________________________________");
		
		noeud.findValue(5).print("  ");
		System.out.println("_________________________________________");
		
		noeud.findValue(2).print("  ");
		System.out.println("_________________________________________");
		try {
			noeud = noeud.fusion(noeud);
		} catch (DifferentOrderTrees e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		noeud.print("  ");
		System.out.println("_________________________________________");
		
		ArrayList<Node> restants = noeud.findValue(4).delete();
		for(Node n: restants){
			n.print("  ");
		}
		System.out.println(restants);
		
		System.out.println("_________________________________________");
		
		Monceau monceau = new Monceau();
		monceau.print();
		
		//test insertion
		monceau.insert(0);
		monceau.insert(7);
		monceau.insert(9);
		monceau.insert(4);
		monceau.print();
		
		
		monceau.insert(4);
		monceau.print();
		
		monceau.delete(7);
		monceau.delete(4);
		monceau.print();
		
		monceau.delete(3);
		monceau.print();
		
		Monceau monceau2 = new Monceau();
		for(int i = 0; i < 10; i++){
			monceau2.insert((int)(Math.random()*100) + 1);
		}
		monceau2.print();
		
		monceau.fusion(monceau2);
		monceau.print();
	}

}
